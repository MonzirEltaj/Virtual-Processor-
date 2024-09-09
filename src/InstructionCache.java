public class InstructionCache {
    private static int size = 8;
    private int Miss = 350;
    private  int Hit  = 10;

    private static Word[] cache = new Word[size];
    private static Word baseAddress = new Word();

    /**
     * Reads word at address
     * @param address being read
     * @return Word at address
     */
    public static Word read(Word address) {
        // Check if the address is in the cache
        if (isAddressInCache(address)) {
            // Cache hit
            return getWordFromCache(address);
        } else {
            // Cache miss
            fillCache(address);
            return getWordFromCache(address);
        }
    }

    /**
     * Checks if the given address is in the cache
     * @param address
     * @return true if the address is in the cache, false otherwise
     */
    private static boolean isAddressInCache(Word address) {
        return address.getUnsigned() >= baseAddress.getUnsigned() &&
                address.getUnsigned() < baseAddress.getUnsigned() + size;
    }

    /**
     * Fills the cache with words from main memory starting at the given address
     * @param address
     */
    public static void fillCache(Word address) {
        baseAddress = new Word(address);
        for (int i = 0; i < size; i++) {
            Word check = new Word();
            cache[i] = MainMemory.read(new Word((int) (address.getUnsigned() + i)));
        }
    }

    /**
     * Gets the word from the cache corresponding to the given address
     * @param address Address of the word to retrieve
     * @return Word from the cache
     */
    public static Word getWordFromCache(Word address) {
        int index = (int) (address.getUnsigned() - baseAddress.getUnsigned());
        return cache[index];
    }
}
