import com.sun.tools.javac.Main;

public class L2Cache {
    private static final int CACHE_SIZE = 32;
    private static final int GROUP_SIZE = 8;
    private static final int CACHE_MISS_CYCLES = 350;
    private static final int CACHE_HIT_CYCLES = 20;
    private static final int FILL_INSTRUCTION_CACHE_CYCLES = 50;

    private static Word[] cache = new Word[CACHE_SIZE];
    private static Word[] addressFlags = new Word[4];
    private static Word[] baseAddresses = new Word[4];

    /**
     * Reads word at address
     * @param address
     * @return Word at address
     */
    public static Word read(Word address) {
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
     * @return true if the address is in the cache, false if not
     */
    public static boolean isAddressInCache(Word address) {
        for (int i = 0; i < 4; i++) {
            if (addressFlags[i] != null && address.getUnsigned() >= baseAddresses[i].getUnsigned() &&
                    address.getUnsigned() < baseAddresses[i].getUnsigned() + GROUP_SIZE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Fills the cache with words from main memory starting at the given address
     * @param address
     */
    public static void fillCache(Word address) {
        int groupIndex = findEmptyGroupIndex();
        baseAddresses[groupIndex] = new Word(address);
        for (int i = 0; i < GROUP_SIZE; i++) {
            cache[groupIndex * GROUP_SIZE + i] = MainMemory.read(new Word((int) (address.getUnsigned() + i)));
        }
        addressFlags[groupIndex] = new Word(1);
    }

    /**
     * Finds the index of the first empty group in the cache
     * @return Index
     */
    public static int findEmptyGroupIndex() {
        for (int i = 0; i < 4; i++) {
            if (addressFlags[i] == null) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Gets the word from the cache corresponding to the given address
     * @param address
     * @return Word
     */
    public static Word getWordFromCache(Word address) {
        for (int i = 0; i < 4; i++) {
            if (addressFlags[i] != null && address.getUnsigned() >= baseAddresses[i].getUnsigned() &&
                    address.getUnsigned() < baseAddresses[i].getUnsigned() + GROUP_SIZE) {
                int index = (int) (address.getUnsigned() - baseAddresses[i].getUnsigned());
                return cache[i * GROUP_SIZE + index];
            }
        }
        return null; // Shouldn't reach here if properly checked for cache hit
    }

    /**
     * Fills the instruction cache from L2 cache for the given address
     * @param address
     */
    public void fillInstructionCache(Word address) {
        Word[] words = new Word[8];
        for (int i = 0; i < 8; i++) {
            words[i] = read(new Word((int) (address.getUnsigned() + i)));
        }
        String[] strings = convertWordsToStrings(words);
        MainMemory.load(strings);

    }
    public String[] convertWordsToStrings(Word[] words) {
        String[] strings = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            strings[i] = words[i].toString();
        }
        return strings;
    }
    /**
     * Writes a word to the cache at the given address
     * @param address
     * @param word Word to write
     */
    public static void write(Word address, Word word) {
        if (isAddressInCache(address)) {
            for (int i = 0; i < 4; i++) {
                if (addressFlags[i] != null && address.getUnsigned() >= baseAddresses[i].getUnsigned() &&
                        address.getUnsigned() < baseAddresses[i].getUnsigned() + GROUP_SIZE) {
                    int index = (int) (address.getUnsigned() - baseAddresses[i].getUnsigned());
                    cache[i * GROUP_SIZE + index] = word;
                    return;
                }
            }
        } else {
            fillCache(address);
            for (int i = 0; i < 4; i++) {
                if (addressFlags[i] != null && address.getUnsigned() >= baseAddresses[i].getUnsigned() &&
                        address.getUnsigned() < baseAddresses[i].getUnsigned() + GROUP_SIZE) {
                    int index = (int) (address.getUnsigned() - baseAddresses[i].getUnsigned());
                    cache[i * GROUP_SIZE + index] = word;
                    return;
                }
            }
        }
    }


}
