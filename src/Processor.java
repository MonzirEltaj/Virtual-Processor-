public class Processor {
    private Word PC = new Word();
    private Word SP = new Word();
    private Word currentInstruction = new Word();
    private Bit halted = new Bit();
    private ALU alu;
    private int currentClockCycle =0;

   public static Word[] Registers = new Word[32];

    private Word function;
    private Word opCode;
    private Word immediate;
    private Word rd1R;

    private Word rd2R;
    private Word rs1;
    private Word rd3R;

    private Word rs2r;

    private Word rs23R;

    private Word result;


    /**
     * Initializes Processor
     */
    public Processor() {
        // Initialize Program Counter (PC) and Stack Pointer (SP)
        PC = new Word();
        PC.set(0);
        SP = new Word();
        SP.set(1023);
        currentClockCycle+=43612;
        // Initialize current instruction and halt status
        currentInstruction = new Word();
        halted = new Bit(false);

        // Initialize ALU
        alu = new ALU(new Word(), new Word());

        // Initialize Registers array
        Registers = new Word[32];
        for (int i = 0; i < 32; i++) {
            Registers[i] = new Word();
        }

        // Initialize additional variables
        function = new Word();
        opCode = new Word();
        immediate = new Word();
        rd1R = new Word();
        rd2R = new Word();
        rd3R = new Word();
        rs1 = new Word();
        rs23R = new Word();
        rs2r = new Word();
        result = new Word();
    }


    /**
     * Runs all the methods
     */
    public void run() {
        while (!halted.getValue()) {
            fetch();
            decode();
            execute();
            store();
        }
        System.out.println("The clock cycle is "+currentClockCycle);
    }

    /**
     * LONG METHOD uses different masks to decode depedning on the location of bits
     */
    public void decode() {
        Word mask = new Word();
        mask.setBit(0, new Bit(true));
        mask.setBit(1, new Bit(true));
        mask.setBit(2, new Bit(true));
        mask.setBit(3, new Bit(true));
        mask.setBit(4, new Bit(true));
        opCode = currentInstruction.and(mask);
        if (!opCode.getBit(0).getValue() && opCode.getBit(1).getValue()) {
            //00000000000000111111111111111111
            mask = new Word();
            for (int x = 14; x < 32; x++) {
                mask.setBit(x, new Bit(true));
            }
            immediate = currentInstruction.and(mask);
            immediate = immediate.leftShift(14);
            //00000000001111000000000000000000;
            mask = new Word();
            mask.setBit(10, new Bit(true));
            mask.setBit(11, new Bit(true));
            mask.setBit(12, new Bit(true));
            mask.setBit(13, new Bit(true));
            function = currentInstruction.and(mask);
            function = function.leftShift(10);
            //00000111110000000000000000000000
            mask = new Word();
            mask.setBit(5, new Bit(true));
            mask.setBit(6, new Bit(true));
            mask.setBit(7, new Bit(true));
            mask.setBit(8, new Bit(true));
            mask.setBit(9, new Bit(true));
            rd1R = currentInstruction.and(mask);
            rd1R = rd1R.leftShift(5);

        } else if (opCode.getBit(0).getValue() && !opCode.getBit(1).getValue()) {
            //00000000000000000001111111111111
            mask = new Word();
            for (int x = 24; x < 32; x++) {
                mask.setBit(x, new Bit(true));
            }
            immediate = currentInstruction.and(mask);
            immediate = immediate.leftShift(19);
            //00000000000000111110000000000000
            mask = new Word();
            mask.setBit(14, new Bit(true));
            mask.setBit(15, new Bit(true));
            mask.setBit(16, new Bit(true));
            mask.setBit(17, new Bit(true));
            mask.setBit(18, new Bit(true));
            rs2r = currentInstruction.and(mask);
            rs2r = rs2r.leftShift(14);
            mask = new Word();
            mask.setBit(10, new Bit(true));
            //00000111110000000000000000000000
            mask.setBit(5, new Bit(true));
            mask.setBit(6, new Bit(true));
            mask.setBit(7, new Bit(true));
            mask.setBit(8, new Bit(true));
            mask.setBit(9, new Bit(true));
            rd3R = currentInstruction.and(mask);
            rd3R = rd3R.leftShift(5);
        } else if (!opCode.getBit(0).getValue() && !opCode.getBit(1).getValue()) {
            mask = new Word();
            for (int x = 5; x < 32; x++) {
                mask.setBit(x, new Bit(true));
            }
            immediate = currentInstruction.and(mask);
            immediate = immediate.leftShift(5);
        }
    }

    public boolean booleanOperation(boolean op ) {
        switch (currentInstruction.getSigned()) {
            case 0000: // Equals
                return PC == SP;
            case 0001: // Not Equal
                return PC != SP;
            case 0010: // Less than
                return PC.getUnsigned()< SP.getUnsigned();
            case 0011: // Greater than or equal
                return PC.getUnsigned()>= SP.getUnsigned();
            case 0100: // Greater than
                return PC.getUnsigned()> SP.getUnsigned();
            case 0101: // Less than or equal
                return PC.getUnsigned()<= SP.getUnsigned();
            default:
                return false ;
        }
    }



    /**
     * Executes the instruction based on opcode and operands.
     */
    public void execute() {
        // Check if specific bits of opCode are not set
        if (!opCode.getBit(2).getValue() && !opCode.getBit(3).getValue() && !opCode.getBit(4).getValue()) {
            updateClockCycle(2);

            if (!opCode.getBit(0).getValue() && opCode.getBit(1).getValue()) {
                // Copy immediate value to rd1R
                rd1R.copy(immediate);
            } else if (opCode.getBit(0).getValue() && !opCode.getBit(1).getValue()) {
                updateClockCycle(2);

                // Perform ALU operation with rs1 and rs2r
                alu = new ALU(rs1, rs2r);
                // Extract function bits and store in array
                Bit[] functionArray = new Bit[4];
                for (int i = 0; i < 4; i++) {
                    functionArray[i] = function.getBit(i);
                }
                alu.doOperation(functionArray);
                result = alu.result;
            } else if (opCode.getBit(0).getValue() && opCode.getBit(1).getValue()) {
                updateClockCycle(2);

                // Perform ALU operation with rs23R and rd3R
                alu = new ALU(rs23R, rd3R);
                // Extract function bits then store
                Bit[] functionArray = new Bit[4];
                for (int i = 0; i < 4; i++) {
                    functionArray[i] = function.getBit(i);
                }
                alu.doOperation(functionArray);
                // Store result of ALU operation
                result = alu.result;
            } else {
                // Set halted to true if none of the above conditions are met
                halted.set(true);
            }
        }
        // Check if the opcode corresponds to Branch instruction
        if (opCode.getBit(2).getValue() && !opCode.getBit(3).getValue() && !opCode.getBit(4).getValue()) {
            updateClockCycle(2);
            // Check the condition for branch
            if (rs1.equals(rs2r)) {
                // Calculate the branch target address
                Word branchTarget = PC.and(immediate);
                // Update the Program Counter (PC) to the branch target address
                PC.copy(branchTarget);
            } else {
                // No branch, continue to the next instruction
                PC.increment();
            }
        }
        // Check if specific bits of opCode are not set
        if (!opCode.getBit(2).getValue() && opCode.getBit(3).getValue() && !opCode.getBit(4).getValue()) {
            updateClockCycle(2);

            if (!opCode.getBit(0).getValue() && opCode.getBit(1).getValue()) {

                L2Cache.write(SP, PC);
                // Update PC to the new address
                alu.add(immediate,PC);
            } else if (opCode.getBit(0).getValue() && !opCode.getBit(1).getValue()) {
                // Call instruction with ALU operation result
                // Perform ALU operation with rs1 and rs2r
                alu = new ALU(rs1, rs2r);
                // getbits and store in array
                Bit[] functionArray = new Bit[4];
                for (int i = 0; i < 4; i++) {
                    functionArray[i] = function.getBit(i);
                }
                alu.doOperation(functionArray);
                // Push the current PC onto the stack
                SP.decrement();
                L2Cache.write(SP, PC);
                // Update PC to the new address
                alu.add(alu.result,immediate);
            } else if (!opCode.getBit(0).getValue() && !opCode.getBit(1).getValue()) {
                updateClockCycle(2);

                // Call instruction with register value
                // Push the current PC onto the stack
                SP.decrement();
                L2Cache.write(SP, PC);
                // Update PC to the new address
                alu.add(rd1R,immediate);
            } else {
                // Call instruction with immediate
                // Push the current PC onto the stack
                SP.decrement();
                L2Cache.write(SP, PC);
                // Update PC to the new address
                alu.add(PC,immediate);
            }
        }
        if (opCode.getBit(0).getValue() && !opCode.getBit(1).getValue() && opCode.getBit(2).getValue()) {
            updateClockCycle(2);

            // Push operation
            if (!opCode.getBit(3).getValue() && !opCode.getBit(4).getValue()) {
                updateClockCycle(2);

                // Push Rd ALU Rs
                // Perform ALU operation with Rd and Rs
                alu = new ALU(rd1R, rs2r);
                // Extract bits and store in array
                Bit[] functionArray = new Bit[4];
                for (int i = 0; i < 4; i++) {
                    functionArray[i] = function.getBit(i);
                }
                alu.doOperation(functionArray);
                // Push the result onto the stack
                SP.decrement();
                L2Cache.write(SP, alu.result);
            } else if (opCode.getBit(3).getValue() && opCode.getBit(4).getValue()) {
                updateClockCycle(2);

                // Push Rd MOP imm
                // Push immediate value onto the stack
                SP.decrement();

                L2Cache.write(SP, immediate);
            } else {
                // UNUSED
                //according to instructions  we do this later
            }
        }
        // Check if the opcode corresponds to Load instruction
        if (!opCode.getBit(0).getValue() && opCode.getBit(1).getValue() && opCode.getBit(2).getValue() &&
                !opCode.getBit(3).getValue() && !opCode.getBit(4).getValue()) {
            updateClockCycle(2);

            if (!opCode.getBit(5).getValue()) {
                // Rd  mem[Rs + immideate]
                Word address = alu.add(rs1, immediate);
                rd1R.copy(L2Cache.read(address));
            } else {
                // Rd  mem [Rs1+ Rs2]
                Word address = alu.add(rs1, rs2r);
                rd1R.copy(L2Cache.read(address));
            }
        } else if (opCode.getBit(0).getValue() && !opCode.getBit(1).getValue() && !opCode.getBit(2).getValue() &&
                !opCode.getBit(3).getValue() && !opCode.getBit(4).getValue()) {
            updateClockCycle(2);

            // Rd  mem [Rd + immideate]
            updateClockCycle(2);
            Word address = alu.add(rd1R, immediate);
            rd1R.copy(L2Cache.read(address));
        } else if (opCode.getBit(0).getValue() && !opCode.getBit(1).getValue() && opCode.getBit(2).getValue() &&
                !opCode.getBit(3).getValue() && !opCode.getBit(4).getValue()) {
            updateClockCycle(2);

            // RETURN (pc  pop)
            SP.increment();
            PC.copy(L2Cache.read(SP));
        }
// Check if the opcode corresponds to Store instruction
        if (!opCode.getBit(0).getValue() && opCode.getBit(1).getValue() && !opCode.getBit(2).getValue() &&
                opCode.getBit(3).getValue() && !opCode.getBit(4).getValue()) {
            if (!opCode.getBit(5).getValue()) {
                // mem[Rd + imm]  Rs
                updateClockCycle(2);

                Word address = alu.add(rd1R, immediate);
                L2Cache.write(address, rs1);
            } else {
                updateClockCycle(2);

                // mem[Rd + Rs1]  Rs2
                Word address = alu.add(rd1R, rs1);
                L2Cache.write(address, rs2r);
            }
        } else if (opCode.getBit(0).getValue() && !opCode.getBit(1).getValue() && !opCode.getBit(2).getValue() &&
                opCode.getBit(3).getValue() && !opCode.getBit(4).getValue()) {
            updateClockCycle(2);

            // Mem[Rd]  imm
            L2Cache.write(rd1R, immediate);
        } else {
            updateClockCycle(2);
        }
// Check if the opcode corresponds to op/interrupt instruction
        if (!opCode.getBit(0).getValue() && !opCode.getBit(1).getValue() && opCode.getBit(2).getValue() &&
                opCode.getBit(3).getValue() && opCode.getBit(4).getValue()) {
            updateClockCycle(2);

            if (!opCode.getBit(5).getValue()) {
                // PEEK: Rd  mem[sp – (Rs + imm)]
                Word address = alu.subtract(SP, alu.add(rs1, immediate));
                rd1R.copy(L2Cache.read(address));
            } else {
                // PEEK: Rd  mem [sp – (Rs1+ Rs2)]
                Word address = alu.subtract(SP, alu.add(rs1, rs2r));
                rd1R.copy(L2Cache.read(address));
            }
        } else if (opCode.getBit(0).getValue() && !opCode.getBit(1).getValue() && !opCode.getBit(2).getValue() &&
                opCode.getBit(3).getValue() && opCode.getBit(4).getValue()) {
            updateClockCycle(2);

            // POP: Rd  mem[sp++]
            SP.increment();
            Word address = alu.subtract(SP, alu.add(rs1, rs2r));

            rd1R.copy(L2Cache.read(address));
        }


    }


    /**
     * Stores value into the Registers
     */
    public void store() {
        opCode = currentInstruction;
        if ((int) rd1R.getUnsigned() == 0 || (int) rd2R.getUnsigned() == 0 || (int) rd3R.getUnsigned() == 0) {
            // If the index is 0, do nothing
            return;
        }
        if (!opCode.getBit(0).getValue() && opCode.getBit(1).getValue()) {
            Registers[(int) rd1R.getUnsigned()] = result;
            currentInstruction.copy(result);
            // Print for debug
            // System.out.println("Stored in Register " + rd1R.getUnsigned() + " (Input: " + rd1R + ", Output: " + result + ")");
        } else if (opCode.getBit(0).getValue() && !opCode.getBit(1).getValue()) {
            Registers[(int) rd2R.getUnsigned()] = result;
            currentInstruction.copy(result);
            // Print for debug
            //System.out.println("Stored in Register " + rd2R.getUnsigned() + " (Input: " + rd2R + ", Output: " + result + ")");
        } else if (opCode.getBit(0).getValue() && opCode.getBit(1).getValue()) {
            Registers[(int) rd3R.getUnsigned()] = result;
            currentInstruction.copy(result);
            // Print for debug
            //  System.out.println("Stored in Register " + rd3R.getUnsigned() + " (Input: " + rd3R + ", Output: " + result + ")");
        }
    }


    /**
     * Calls read then copies ,increments.
     */
    public void fetch() {
        updateClockCycle(300);
        Word instruction = InstructionCache.read(PC);
         instruction = L2Cache.read(PC);
        currentInstruction.copy(instruction);
        PC.increment();
    }
    public void updateClockCycle(int cyclesToAdd) {
        currentClockCycle += cyclesToAdd;
    }
    public  void resetRegsiter(int index, int val){
        Registers[index].set(val);
    }
    public void resetClockCycle(int x){
        currentClockCycle+=x;
    }

}
