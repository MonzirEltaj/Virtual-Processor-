public class ALU {
    public Word op1;
    public Word op2;
    public Word result;

    public ALU() {
        op1 = new Word();
        op2 = new Word();
        result = new Word();
    }
    public ALU(Word op1, Word op2) {
        op1 = new Word();
        op2 = new Word();
        result = new Word();
    }

    /**
     * Handels operation bits(only last 4)
     * @param operation
     */
    public void doOperation(Bit[] operation) {
        String hold = "";
        for(int x=0; x<operation.length; x++){
            if(operation[x].getValue()){
                //add 1 bit for true
                hold+=1;
            }
            else{
                //add 0 bit for false

                hold+=0;
            }
        }
        int last4 = Integer.parseInt(hold);
        switch (last4) {
            case 1000:  // 1000 and
                result.copy(op1.and(op2));
                break;
            case 1001:  // 1001  or
                result.copy(op1.or(op2));
                break;
            case 1010: // 1010  xor
                result.copy(op1.xor(op2));
                break;
            case 1011: // 1011  not
                result.copy(op1.not());
                break;
            case 1100: // 1100 left shift
                result.copy(op1.leftShift((int) op2.getUnsigned()));
                break;
            case 1101: // 1101 right shift
                result.copy(op1.rightShift((int) op2.getUnsigned()));
                break;
            case 1110: // 1110  add
                result .copy(add(op1,op2));
                break;
            case 1111: // 1111 - subtract
                result .copy (subtract(op1,op2));
                break;
            case 0111:  // 0111 - multiply
                result .copy(multiply(op1,op2));
                break;

        }
    }

    /**
     * Adds two words
     * @param word1
     * @param word2
     * @return sum of two words
     */
    public Word add2(Word word1, Word word2) {
        Word result = new Word();
        Bit carry = new Bit();

        for (int i = 0; i < 32; i++) {
            result.bits[i] = word1.bits[i].xor(word2.bits[i]).xor(carry);
            carry = (word1.bits[i].and(word2.bits[i]))
                    .or((word1.bits[i].xor(word2.bits[i])).and(carry));
        }

        return result;
    }


    /**
     * Subtract two words
     * @param word1
     * @param word2
     * @return result of subtracting two words
     */
    public Word subtract(Word word1, Word word2) {
        //not the word
        Word negatedWord2 = word2.not();
        Word one = new Word();
        one.getBit(0).set(true);
        // Add the negated words
        Word result = add2(word1, negatedWord2);
        // Add 1 for twos compilment
        for (int i = 0; i < 32; i++) {
            if (!result.bits[i].getValue()) {
                result.bits[i].set(true);
                break; // Break the loop after adding 1
            } else {
                result.bits[i].set(false);
            }
        }

        return result;
    }


    /**
     * Adds FOUR words together
     * @param word1
     * @param word2
     * @param word3
     * @param word4
     * @return sum of 4 words
     */
    public Word add4(Word word1, Word word2, Word word3, Word word4) {
        Word result = new Word();
        Bit carry = new Bit();

        for (int i = 0; i < 32; i++) {
            Bit sum = word1.bits[i].xor(word2.bits[i]).xor(word3.bits[i]).xor(word4.bits[i]).xor(carry);
            result.bits[i] = sum;

            // Calculate carry bit
            Bit carryGenerate = (word1.bits[i].and(word2.bits[i]))
                    .or(word1.bits[i].and(word3.bits[i]))
                    .or(word1.bits[i].and(word4.bits[i]))
                    .or(word2.bits[i].and(word3.bits[i]))
                    .or(word2.bits[i].and(word4.bits[i]))
                    .or(word3.bits[i].and(word4.bits[i]));

            carry = carryGenerate.or(sum.and(carry));
        }

        return result;
    }



    /**
     * Mutiplies two words together
     * @param op1
     * @param op2
     * @return product of two words
     */
    public Word multiply(Word op1, Word op2) {
        Word result = new Word();
        for (int i = 0; i < 32; i++) {
            if (op1.bits[0].getValue()) {
                result = add2(result, op2);
            }
            op1 = op1.rightShift(1);
            op2 = op2.leftShift(1);
            if (op1.bits[0].getValue() && !op1.bits[1].getValue()) {
                result = add2(result, op2.not());
            }
            op1 = op1.rightShift(1);
            op2 = op2.leftShift(1);
        }
        return result;
    }


    /**
     * just calls add 2
     * @param op1
     * @param op2
     * @return sum of add2
     */
    public Word add(Word op1, Word op2){
        return result = add2(op1,op2);
    }


}