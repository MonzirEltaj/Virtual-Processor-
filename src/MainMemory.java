
public class MainMemory {
    static Word[] memoryArray = new Word[1024];

    /**
     * Reads word at address
      * @param address being read
     * @return Word at address
     */
    public static Word read(Word address) {
        int index = (int) address.getUnsigned();
        Word readWord = new Word();
        if (memoryArray[index] != null) {
            readWord.copy(memoryArray[index]);
        }

        return readWord;
    }

    /**
     * Writes value at address
     * @param address where value is written
     * @param value being written
     */
    public static void write(Word address, Word value) {
        int index = address.getSigned();
        if (memoryArray[index] == null) {
            memoryArray[index] = new Word();
        }
        memoryArray[index].copy(value);
    }

    /**
     *  Loads data(a bunch of lines of 0 , and 1) then creates words
      * @param data
     */
    public static void load(String[] data) {
        for(int x=0; x< data.length; x++){
            Word word = new Word();
            for(int g=0; g<32; g++){
            if(data[x].charAt(g)=='0'){
                word.setBit(g,new Bit(false));
            }
            else{
                word.setBit(g,new Bit(true));

            }
            }
            if(memoryArray[x] == null)
                memoryArray[x]=new Word();
            memoryArray[x].copy(word);
        }
    }

}