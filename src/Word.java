public class Word {
    Bit[] bits;

    /**
     * Word Constructor
     */
    public Word() {
        bits = new Bit[32];
        for (int i = 0; i < 32; i++) {
            bits[i] = new Bit();
        }
    }

    public Word(Word val) {
        bits = new Bit[32];
        for (int i = 0; i < 32; i++) {
            bits[i] = val.getBit(i);
        }
    }
    /**
     * @param i index
     * @return bit at index
     */
    public Bit getBit(int i) {
        return bits[i];
    }

    /**
     * Sets bit
     *
     * @param
     * @param value
     */
    public void setBit(int i, Bit value) {
        bits[i] = value;
    }

    //and operation
    public Word and(Word other) {
        Word newWord = new Word();
        for (int i = 0; i < 32; i++) {
            newWord.bits[i] = this.bits[i].and(other.bits[i]);
        }
        return newWord;
    }

    //or operation
    public Word or(Word other) {
        Word newWord = new Word();
        for (int i = 0; i < 32; i++) {
            newWord.bits[i] = this.bits[i].or(other.bits[i]);
        }
        return newWord;
    }

    //xor operation
    public Word xor(Word other) {
        Word newWord = new Word();
        for (int i = 0; i < 32; i++) {
            newWord.bits[i] = this.bits[i].xor(other.bits[i]);
        }
        return newWord;
    }

    //not operation
    public Word not() {
        Word newWord = new Word();
        for (int i = 0; i < 32; i++) {
            newWord.bits[i] = this.bits[i].not();
        }
        return newWord;
    }


    //shifts bit to right by amount
    public Word rightShift(int amount) {
        Word newWord = new Word();
        for (int i = 0; i < 32 - amount; i++) {
            newWord.setBit(i, new Bit(bits[i + amount].getValue()));
        }
        return newWord;
    }

    //shifts bit to left by amount
    public Word leftShift(int amount) {
        Word newWord = new Word();
        for (int i = amount; i < 32; i++) {
            newWord.setBit(i, new Bit(bits[i - amount].getValue()));
        }
        return newWord;
    }

    //copy word
    public void copy(Word other) {
        for (int i = 0; i < 32; i++) {
            this.bits[i].set(other.bits[i].getValue());
        }
    }
    // Converts binary into long
    public long getUnsigned() {
        long longValue = 0;
        for (int i = 0; i < 32; i++) {
            if (this.bits[i].getValue()) {
                longValue += (long) Math.pow(2, i);
            }
        }
        return longValue;
    }

    // Converts binary into int
    public int getSigned() {
        if (!bits[31].getValue()) {
            return (int) getUnsigned();
        } else {
            int value = 0;
            for (int x = 0; x < 31; x++) {
                value += (1 << x);
            }
            value = -1 * (value + 1);
            return value;
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 31; i >= 0; i--) {
            result += bits[i].toString();
            result += ", ";
        }
        return result;
    }

    // Set int to binary
    public void set(int value) {
        // Set all bits except the sign bit (last bit)
        for (int i = 31; i >=0; i--) {
            bits[i].set((value & (1 << i)) != 0);
        }

        // Set the sign bit based on the negativity of the value
        if(value<0){
        bits[31].set(true);
        }
        else{
            bits[31].set(false);

        }

    }



    /**
     * Increment word for fetch
     */
    public void increment() {
        Bit carry = new Bit();
        for (int i = 0; i < 32; i++) {
            Bit sum = this.bits[i].xor(carry);
            this.bits[i].set(sum.getValue());
            carry = this.bits[i].and(carry).or(this.bits[i].and(carry)).or(this.bits[i].and(carry));
        }
    }
    /**
     * Decrement   word
     */
    public void decrement() {
        Bit borrow = new Bit();
        for (int i = 0; i < 32; i++) {
            Bit diff = this.bits[i].xor(borrow);
            this.bits[i].set(diff.getValue());
            borrow = this.bits[i].and(borrow.not()).or(this.bits[i].not().and(borrow));
        }
    }
    public int compareTo(Word other) {
        // Compare each bit starting from the most significant bit
        for (int i = 31; i >= 0; i--) {
            if (this.bits[i].getValue() && !other.bits[i].getValue()) {
                return 1; // This word is greater
            } else if (!this.bits[i].getValue() && other.bits[i].getValue()) {
                return -1; // This word is less
            }
        }
        return 0; // Words are equal
    }
    public String PrintBin(){
        String result = "";
        for (int i = 31; i >= 0; i--) {
            if(bits[i].getValue()){
                result+=1;
            }
            else{
                result+=0;

            }
        }
        return result;
    }
    /**
     * Word Constructor that takes an int value
     *
     * @param value The integer value to represent as a 32-bit word
     */
    public Word(int value) {
        bits = new Bit[32];
        for (int i = 31; i >= 0; i--) {
            bits[i] = new Bit((value & (1 << i)) != 0);
        }

        if (value < 0) {
            bits[31].set(true);
        } else {
            bits[31].set(false);
        }
    }
}