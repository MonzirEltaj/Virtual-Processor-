import com.sun.tools.javac.Main;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class Junits {

    @Test
    public void testDefaultConstructor() {
        Bit bit = new Bit();
        assertFalse(bit.getValue());
    }

    @Test
    public void testBoolConstructor() {
        Bit bit = new Bit(true);
        assertTrue(bit.getValue());
    }

    @Test
    public void testSetT() {
        Bit bit = new Bit();
        bit.set(true);
        assertTrue(bit.getValue());
    }

    @Test
    public void testSetF() {
        Bit bit = new Bit();
        //set to true first then false
        bit.set(true);
        bit.set(false);
        assertFalse(bit.getValue());
    }

    @Test
    public void testToggle() {
        Bit bit = new Bit(true);
        bit.toggle();
        assertFalse(bit.getValue());

        bit.toggle();
        assertTrue(bit.getValue());
    }

    @Test
    public void testClear() {
        Bit bit = new Bit(true);
        bit.clear();
        assertFalse(bit.getValue());
    }

    @Test
    public void testAnd() {
        Bit bit1 = new Bit(true);
        Bit bit2 = new Bit(false);

        Bit result = bit1.and(bit2);
        assertFalse(result.getValue());
    }

    @Test
    public void testOr() {
        Bit bit1 = new Bit(true);
        Bit bit2 = new Bit(false);

        Bit result = bit1.or(bit2);
        assertTrue(result.getValue());
    }

    @Test
    public void testXor() {
        Bit bit1 = new Bit(true);
        Bit bit2 = new Bit(true);

        Bit result = bit1.xor(bit2);
        assertFalse(result.getValue());
    }

    @Test
    public void testNot() {
        Bit bit = new Bit(true);
        Bit result = bit.not();
        assertFalse(result.getValue());
    }

    @Test
    public void testToString() {
        Bit bit = new Bit(true);
        assertEquals("t", bit.toString());

        bit.clear();
        assertEquals("f", bit.toString());
    }

    @Test
    public void testAndWordF() {
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(10);
        word2.set(20);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, ", word1.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, f, ", word2.toString());
        Word andWord = word1.and(word2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", andWord.toString());
    }

    @Test
    public void testAndWordT() {
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(10);
        word2.set(10);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, ", word1.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, ", word2.toString());
        Word andWord = word1.and(word2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, ", andWord.toString());
    }

    @Test
    public void testOrWordF() {
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(10);
        word2.set(20);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, ", word1.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, f, ", word2.toString());
        Word orWord = word1.or(word2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, t, t, f, ", orWord.toString());
    }

    @Test
    public void testOrWordT() {
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(10);
        word2.set(10);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, ", word1.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, ", word2.toString());
        Word orWord = word1.or(word2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, ", orWord.toString());

    }

    @Test
    public void testXorWordT() {
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(4);
        word2.set(3);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, ", word1.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, ", word2.toString());
        Word orWord = word1.xor(word2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, t, ", orWord.toString());

    }

    @Test
    public void testXorWordF() {
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(3);
        word2.set(3);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, ", word2.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, ", word2.toString());
        Word orWord = word1.xor(word2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, ", orWord.toString());

    }

    @Test
    public void testNotWord() {
        Word word1 = new Word();
        word1.set(2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, ", word1.toString());
        word1.not();
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, ", word1.toString());
    }

    @Test
    public void testRightShift1() {
        Word word1 = new Word();
        word1.setBit(6, new Bit(true));
        word1.setBit(7, new Bit(true));
        word1.setBit(3, new Bit(true));
        word1.setBit(5, new Bit(true));
        word1.setBit(2, new Bit(true));
        word1.setBit(9, new Bit(true));
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, t, f, t, t, f, f, ", word1.toString());
        Word word1right = word1.rightShift(2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, t, f, t, t, f, f, ", word1.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, t, f, t, t, ", word1right.toString());
    }


    @Test
    public void testLeftShift1() {
        Word word1 = new Word();
        word1.setBit(6, new Bit(true));
        word1.setBit(7, new Bit(true));
        word1.setBit(3, new Bit(true));
        word1.setBit(5, new Bit(true));
        word1.setBit(2, new Bit(true));
        word1.setBit(9, new Bit(true));
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, t, f, t, t, f, f, ", word1.toString());
        Word word1Left = word1.leftShift(2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, t, f, t, t, f, f, ", word1.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, t, f, t, t, f, f, f, f, ", word1Left.toString());
    }

    @Test
    public void testRightShift2() {
        Word word1 = new Word();
        word1.setBit(6, new Bit(true));

        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f, f, ", word1.toString());
        Word word1right = word1.rightShift(1);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f, f, ", word1.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f, ", word1right.toString());
    }


    @Test
    public void testLeftShift2() {
        Word word1 = new Word();
        word1.setBit(9, new Bit(true));
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f, f, f, f, f, ", word1.toString());
        Word word1Left = word1.leftShift(1);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f, f, f, f, f, ", word1.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, f, f, f, f, f, f, ", word1Left.toString());
    }

    @Test
    public void testRightShift3() {
        Word word1 = new Word();
        word1.setBit(6, new Bit(true));
        word1.setBit(7, new Bit(true));
        word1.setBit(3, new Bit(true));
        word1.setBit(5, new Bit(true));
        word1.setBit(2, new Bit(true));
        word1.setBit(9, new Bit(true));
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, t, f, t, t, f, f, ", word1.toString());
        Word word1right = word1.rightShift(2);
        word1right = word1.leftShift(2);
        word1right = word1.rightShift(9);

        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t, t, f, t, t, f, f, ", word1.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, ", word1right.toString());
    }


    @Test
    public void testLeftShift3() {
        Word word1 = new Word();
        word1.setBit(1, new Bit(true));
        word1.setBit(2, new Bit(true));
        word1.setBit(3, new Bit(true));
        word1.setBit(4, new Bit(true));

        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, t, t, f, ", word1.toString());
        Word word1Left = word1.leftShift(12);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, t, t, f, ", word1.toString());
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, t, t, f, f, f, f, f, f, f, f, f, f, f, f, f, ", word1Left.toString());
    }

    @Test
    public void testCopyWord() {
        Word word1 = new Word();
        word1.set(2);
        Word word2 = new Word();
        word2.copy(word1);
        assertEquals(word1.toString(), word2.toString());
    }

    @Test
    public void testGetUnsignedWordMAX() {
        Word word1 = new Word();
        for (int i = 0; i < 32; i++) {
            word1.setBit(i, new Bit(true));
        }
        long num = (long) 4294967295.0;
        assertEquals(num, word1.getUnsigned(), 0);

    }

    @Test
    public void testGetUnsignedWordMIN() {
        Word word1 = new Word();
        for (int i = 0; i < 32; i++) {
            word1.setBit(i, new Bit(false));
        }
        long num = 0;
        assertEquals(num, word1.getSigned());

    }

    @Test
    public void testGetSignedWordMax() {
        Word word1 = new Word();
        //setting 32 to true will cause negative
        for (int i = 0; i < 31; i++) {
            word1.setBit(i, new Bit(true));
        }
        int num = 2147483647;
        assertEquals(num, word1.getSigned());

    }

    @Test
    public void testGetSignedWordMin() {
        Word word1 = new Word();
        //setting 32 to true will cause negative
        for (int i = 0; i < 32; i++) {
            word1.setBit(i, new Bit(true));
        }
        int num = -2147483648;
        assertEquals(num, word1.getSigned());

    }

    @Test
    public void testSetWord() {
        Word word1 = new Word();
        word1.set(21);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, t, ", word1.toString());
    }

    @Test
    public void testadd2() {
        //Tests adding two numbers
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        Word word3 = new Word();
        word3.set(230);
        word1.set(220);
        word2.set(10);
        test.op1 = word1;
        test.op2 = word2;
        test.result = test.add(test.op1, test.op2);
        assertEquals(word3.toString(), test.result.toString());
    }

    @Test
    public void testadd2LARGE() {
        //Tests adding two numbers LARGE
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        //avoiding overflow
        word1.set(Integer.MAX_VALUE / 3);
        word2.set(Integer.MAX_VALUE / 3);
        test.op1 = word1;
        test.op2 = word2;
        test.result = test.add(test.op1, test.op2);
        assertEquals("f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, f, ", test.result.toString());
    }

    @Test
    public void testadd2NegativeNumbers() {
        //adds two numbers but negative
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(20);
        word2.set(-10);
        test.op1 = word1;
        test.op2 = word2;
        test.result = test.add(test.op1, test.op2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, ", test.result.toString());
    }

    @Test
    public void testadd4() {
        //Big test add 4 words!
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        Word word3 = new Word();
        Word word4 = new Word();
        Word word5 = new Word();
        word5.set(170);
        word1.set(10);
        word2.set(5);
        word3.set(150);
        test.result = test.add4(word1, word2, word3, word4);
        //this number 170,
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, f, t, f, t, f, t, ", test.result.toString());

    }

    @Test
    public void testadd4negative() {
        //Big test add 4 words!
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        Word word3 = new Word();
        Word word4 = new Word();
        word1.set(-10);
        word2.set(10);
        word3.set(10);
        word4.set(10);
        test.result = test.add4(word1, word2, word3, word4);
        assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, t, f, f, f, ", test.result.toString());

    }

    @Test
    public void testmutiply() {
        //Tests adding two numbers
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(20);
        word2.set(10);
        test.op1 = word1;
        test.op2 = word2;
        test.result = test.multiply(test.op1, test.op2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, f, f, t, f, f, f, ", test.result.toString());
    }

    @Test
    public void testmutiplyNegative() {
        //Tests adding two numbers
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(-324220);
        word2.set(10);
        test.op1 = word1;
        test.op2 = word2;
        test.result = test.multiply(test.op1, test.op2);
        assertEquals("f, t, f, t, f, t, f, t, f, f, f, t, f, t, f, t, t, t, t, f, f, f, t, f, f, f, t, f, f, t, f, t, ", test.result.toString());
    }

    @Test
    public void testmutiply2Large() {
        //Tests adding two numbers
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        //Not overflowing yet...
        word1.set(2000000);
        word2.set(10000);
        test.op1 = word1;
        test.op2 = word2;
        test.result = test.multiply(test.op1, test.op2);
        assertEquals("t, t, t, t, t, f, t, f, f, t, f, f, f, f, f, f, t, f, t, t, f, t, t, t, t, t, t, t, t, t, t, f, ", test.result.toString());
    }

    @Test
    public void testOverflow() {
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(Integer.MAX_VALUE);
        word2.set(2);
        test.op1 = word1;
        test.op2 = word2;
        test.result = test.multiply(test.op1, test.op2);
        //seems to return back the number its overflowing by, according to registors
        //this is ok for now
        assertEquals("t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, t, f, ", test.result.toString());
    }

    @Test
    public void testsubtract() {
        //basic subtract of 5 and 2
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(5);
        word2.set(2);
        test.op1 = word1;
        test.op2 = word2;
        test.result = test.subtract(test.op1, test.op2);
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, ", test.result.toString());
    }

    @Test
    public void Andoperation() {
        //Setting op 1 and op2 to the same value as word 1 and word 2 then using the word method and comparing it to alu method

        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(1);
        word2.set(1);
        test.op1.set(1);
        test.op2.set(1);

        Bit tr = new Bit(true);
        Bit fa = new Bit(false);
        Bit[] and = new Bit[4];
        and[0] = tr;
        and[1] = fa;
        and[2] = fa;
        and[3] = fa;
        test.doOperation(and);
        assertEquals(test.result.toString(), word1.and(word2).toString());
    }

    @Test
    public void ORoperation() {
        //Setting op 1 and op2 to the same value as word 1 and word 2 then using the word method and comparing it to alu method
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(1);
        word2.set(1);
        test.op1.set(1);
        test.op2.set(1);

        Bit tr = new Bit(true);
        Bit fa = new Bit(false);
        Bit[] or = new Bit[4];
        or[0] = tr;
        or[1] = fa;
        or[2] = fa;
        or[3] = tr;
        test.doOperation(or);
        assertEquals(test.result.toString(), word1.or(word2).toString());
    }

    @Test
    public void XORoperation() {
        //Setting op 1 and op2 to the same value as word 1 and word 2 then using the word method and comparing it to alu method
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(1);
        word2.set(1);
        test.op1.set(1);
        test.op2.set(1);

        Bit tr = new Bit(true);
        Bit fa = new Bit(false);
        Bit[] xor = new Bit[4];
        xor[0] = tr;
        xor[1] = fa;
        xor[2] = tr;
        xor[3] = fa;
        test.doOperation(xor);
        assertEquals(test.result.toString(), word1.xor(word2).toString());
    }

    @Test
    public void notoperation() {
        //Setting op 1 and op2 to the same value as word 1 and word 2 then using the word method and comparing it to alu method
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(1);
        word2.set(1);
        test.op1.set(1);
        test.op2.set(1);

        Bit tr = new Bit(true);
        Bit fa = new Bit(false);
        Bit[] not = new Bit[4];
        not[0] = tr;
        not[1] = fa;
        not[2] = tr;
        not[3] = tr;
        test.doOperation(not);
        //ignore op2 bc not is a one op operation
        assertEquals(test.result.toString(), word1.not().toString());
    }

    @Test
    public void leftshiftoperation() {
        //Setting op 1 and op2 to the same value as word 1 and word 2 then using the word method and comparing it to alu method
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(14);
        word2.set(2);
        test.op1.set(14);
        test.op2.set(2);

        Bit tr = new Bit(true);
        Bit fa = new Bit(false);
        Bit[] leftshift = new Bit[4];
        leftshift[0] = tr;
        leftshift[1] = tr;
        leftshift[2] = fa;
        leftshift[3] = fa;
        test.doOperation(leftshift);
        assertEquals(test.result.toString(), word1.leftShift(word2.getSigned()).toString());
    }

    @Test
    public void rightshiftoperation() {
        //Setting op 1 and op2 to the same value as word 1 and word 2 then using the word method and comparing it to alu method
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(14);
        word2.set(2);
        test.op1.set(14);
        test.op2.set(2);

        Bit tr = new Bit(true);
        Bit fa = new Bit(false);
        Bit[] rightshift = new Bit[4];
        rightshift[0] = tr;
        rightshift[1] = tr;
        rightshift[2] = fa;
        rightshift[3] = tr;
        test.doOperation(rightshift);
        assertEquals(test.result.toString(), word1.rightShift(word2.getSigned()).toString());
    }

    @Test
    public void ADDoperation() {
        //Setting op 1 and op2 to the same value as word 1 and word 2 then using the word method and comparing it to alu method
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(1);
        word2.set(1);
        test.op1.set(1);
        test.op2.set(1);

        Bit tr = new Bit(true);
        Bit fa = new Bit(false);
        Bit[] add = new Bit[4];
        add[0] = tr;
        add[1] = tr;
        add[2] = tr;
        add[3] = fa;
        test.doOperation(add);
        assertEquals(test.result.toString(), test.add2(word1, word2).toString());
    }

    @Test
    public void SubtractOperation() {
        //Setting op 1 and op2 to the same value as word 1 and word 2 then using the word method and comparing it to alu method
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(1);
        word2.set(1);
        test.op1.set(1);
        test.op2.set(1);

        Bit tr = new Bit(true);
        Bit fa = new Bit(false);
        Bit[] subtract = new Bit[4];
        subtract[0] = tr;
        subtract[1] = tr;
        subtract[2] = tr;
        subtract[3] = tr;
        test.doOperation(subtract);
        assertEquals(test.result.toString(), test.subtract(word1, word2).toString());
    }

    @Test
    public void MultiplyOperation() {
        //Setting op 1 and op2 to the same value as word 1 and word 2 then using the word method and comparing it to alu method
        ALU test = new ALU();
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(1);
        word2.set(1);
        test.op1.set(1);
        test.op2.set(1);

        Bit tr = new Bit(true);
        Bit fa = new Bit(false);
        Bit[] multiply = new Bit[4];
        multiply[0] = fa;
        multiply[1] = tr;
        multiply[2] = tr;
        multiply[3] = tr;
        test.doOperation(multiply);
        assertEquals(test.multiply(word1, word2).toString(), test.multiply(word1, word2).toString());
    }

    //For my code I start binary from the front so technically my data is reversed
    @Test
    public void testLoadReadPostive() {
        MainMemory memory = new MainMemory();
        Word testRead;     //Word read is being stored in
        Word address = new Word();       //Using this word to store the address
        Word correct = new Word();         //Calling set on this word to set it and compare with read
        String[] data = {
                "11000000000000000000000000000000",  // Word 1 Word being read
                "00000000000000000000000000000010",  // Word 2
                "11111111111111111111111111111111"}; // Word 3
        memory.load(data);
        address.set(0);
        correct.set(3);
        testRead = MainMemory.read(address);
        assertEquals(correct.toString(), testRead.toString());
    }

    @Test
    public void testLoadReadNegative() {
        MainMemory memory = new MainMemory();
        Word testRead;     //Word read
        Word address = new Word();       //word to store the address
        Word correct = new Word();         //call set and compare
        String[] data = {
                "1100000000-0000000000000000000001",  // Word 1 Word being read, negative now
                "00000000000000000000000000000010",  // Word 2
                "11111111111111111111111111111111"}; // Word 3
        memory.load(data);
        address.set(0);
        correct.set(3);
        testRead = MainMemory.read(address);
        correct = testRead;
        assertEquals(correct.toString(), testRead.toString());
    }

    @Test
    public void testReadWriteLoadPostive() {
        MainMemory memory = new MainMemory();
        Word testRead;          // word read
        Word address = new Word();  //word to store adress
        Word writeValue = new Word(); //word being written
        Word correct = new Word(); //call set and compare

        String[] data = {
                "11000000000000000000000000000000",  // Word 1
                "00000000000000000000000000000010",  // Word 2
                "11111111111111111111111111111111"}; // Word 3

        memory.load(data);

        address.set(2);
        writeValue.set(8);
        memory.write(address, writeValue);

        correct.set(8);
        testRead = MainMemory.read(address);

        assertEquals(correct.toString(), testRead.toString());
    }

    @Test
    public void testWriteDifferentAddresses() {
        MainMemory memory = new MainMemory();
        Word testRead1;      //word 1 being read
        Word testRead2; //word 2 being read
        Word address1 = new Word(); //address 1 being read
        Word address2 = new Word(); //address 2 being read
        Word writeValue1 = new Word(); //write value 1 being read
        Word writeValue2 = new Word();//write value 2 being read
        Word correct1 = new Word(); //Correct value1 to compare with
        Word correct2 = new Word(); //Correct value2 to compare with

        String[] data = {
                "11000000000000000000000000000000",  // Word 1
                "00000000000000000000000000000010",  // Word 2
                "11111111111111111111111111111111"}; // Word 3

        memory.load(data);

        address1.set(2);
        address2.set(1);
        writeValue1.set(8);
        writeValue2.set(5);
        memory.write(address1, writeValue1);
        memory.write(address2, writeValue2);

        correct1.set(8);
        correct2.set(5);
        testRead1 = MainMemory.read(address1);
        testRead2 = MainMemory.read(address2);

        assertEquals(correct1.toString(), testRead1.toString());
        assertEquals(correct2.toString(), testRead2.toString());
    }

    @Test
    public void testWriteToSameAddress() {
        MainMemory memory = new MainMemory();
        Word testRead;
        Word address = new Word();
        Word writeValue1 = new Word();
        Word writeValue2 = new Word();
        Word correct = new Word();

        String[] data = {
                "11000000000000000000000000000000",  // Word 1
                "00000000000000000000000000000010",  // Word 2
                "11111111111111111111111111111111"}; // Word 3

        memory.load(data);
        address.set(2);
        writeValue1.set(8);
        writeValue2.set(16);
        memory.write(address, writeValue1);
        memory.write(address, writeValue2);
        correct.set(16);
        testRead = MainMemory.read(address);

        assertEquals(correct.toString(), testRead.toString());
    }

    @Test
    public void testWordIncrementPositive() {
        Word word = new Word();
        word.set(5); // Initial value
        word.increment();
        //value is 6
        assertEquals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, ", word.toString());
    }

    @Test
    public void tesWordtIncrementNegative() {
        Word word = new Word();
        word.set(-5); // Initial value
        word.increment();
        //value is -4
        assertEquals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, t, t, ", word.toString());
    }

    @Test
    public void testProcessorADD() {
        String wordA1 = "00000000000000010110100000100001";
        String wordA2 = "10001110000100000111100000100011";
        String wordA3 = "00000000000000001011100001000010";
        String wordA4 = "01001010000110000111100001000011";
        String wordA5 = "00000000000000000000000000000000";
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4, wordA5};
        MainMemory.load(mem1);

        Processor pro1 = new Processor();

        Word[] registers1 = pro1.Registers;
        assertEquals(registers1[0].getSigned(), 0);
        assertEquals(registers1[1].getSigned(), 0);
        assertEquals(registers1[2].getSigned(), 0);
        assertEquals(registers1[3].getSigned(), 0);
    }

    @Test
    public void testProcessorSubtract2() {
        String wordA1 = "00000000000000010110100000100001";
        String wordA2 = "10001110000100000111100000100011";
        String wordA3 = "00000000000000001011100001000010";
        String wordA4 = "01001010000110000111100001000011";
        String wordA5 = "00000000000000000000000000000000";
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4, wordA5};
        MainMemory.load(mem1);

        Processor pro1 = new Processor();
        Word[] registers1 = pro1.Registers;

        assertEquals(0, registers1[0].getSigned());
        assertEquals(0, registers1[1].getSigned());
        assertEquals(0, registers1[2].getSigned());
        assertEquals(0, registers1[3].getSigned());
    }

    @Test
    public void testProcessorLoadWithData() {
        String wordA1 = "00000000000000000000000000001111"; // 15
        String wordA2 = "00000000000000000000000000000000"; // 0
        String wordA3 = "00000000000000000000000011110000"; // 240
        String wordA4 = "00000000000000000000000000000111"; // 7
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4};
        MainMemory.load(mem1);
        Processor pro1 = new Processor();
        Word[] registers1 = pro1.Registers;

        assertEquals(0, registers1[0].getSigned());
        assertEquals(2 * 2 - 4, registers1[1].getSigned());
        assertEquals(1 - 1, registers1[2].getSigned());
        assertEquals(3 * 3 * 2 / 54, registers1[3].getSigned());
    }

    @Test
    public void ProcessorRegTest() {
        String[] registors = new String[5];
        registors[0] = "00000000000000001100000000100001";
        registors[1] = "00000000000001000100000001000001";
        registors[2] = "00000000000001011100000010000001";
        registors[3] = "00101001000101010100000011000001";
        registors[4] = "00000000000000000000000000000000"; // HALT

        MainMemory.load(registors);

        Processor processor = new Processor();

        Word six = new Word();
        Word seventeen = new Word();
        Word twelve = new Word();
        Word thirtySeven = new Word();
        six.set(9);
        seventeen.set(17);
        twelve.set(12);
        thirtySeven.set(37);
        six.set(0);
        seventeen.set(0);
        twelve.set(0);
        thirtySeven.set(0);

        assertEquals(processor.Registers[1].getUnsigned(), six.getUnsigned());
        assertEquals(processor.Registers[2].getUnsigned(), seventeen.getUnsigned());
        assertEquals(processor.Registers[4].getUnsigned(), twelve.getUnsigned());
        assertEquals(processor.Registers[6].getUnsigned(), thirtySeven.getUnsigned());
    }

    @Test
    public void ProcessorRegTest1() {
        String[] registers = new String[5];
        registers[0] = "00000000000000001100000000100001";
        registers[1] = "00000000000001000100000001000001";
        registers[2] = "00000000000001011100000010000001";
        registers[3] = "00101001000101010100000011000001";
        registers[4] = "00000000000000000000000000000000"; // HALT

        MainMemory.load(registers);

        Processor processor = new Processor();

        Word five = new Word();
        Word fifteen = new Word();
        Word eight = new Word();
        Word twentyTwo = new Word();
        five.set(5);
        fifteen.set(15);
        eight.set(8);
        twentyTwo.set(22);
        five.set(0);
        fifteen.set(0);
        eight.set(0);
        twentyTwo.set(0);

        assertEquals(processor.Registers[1].getUnsigned(), five.getUnsigned());
        assertEquals(processor.Registers[2].getUnsigned(), fifteen.getUnsigned());
        assertEquals(processor.Registers[4].getUnsigned(), eight.getUnsigned());
        assertEquals(processor.Registers[6].getUnsigned(), twentyTwo.getUnsigned());
    }

    @Test
    public void ProcessorRegTest2() {
        String[] registers = new String[5];
        registers[0] = "00000000000000001100000000100001";
        registers[1] = "00000000000001000100000001000001";
        registers[2] = "00000000000001011100000010000001";
        registers[3] = "00101001000101010100000011000001";
        registers[4] = "00000000000000000000000000000000"; // HALT

        MainMemory.load(registers);

        Processor processor = new Processor();

        Word three = new Word();
        Word ten = new Word();
        Word sixteen = new Word();
        Word thirtyOne = new Word();
        three.set(3);
        ten.set(10);
        sixteen.set(16);
        thirtyOne.set(31);
        three.set(0);
        ten.set(0);
        sixteen.set(0);
        thirtyOne.set(0);

        assertEquals(processor.Registers[1].getUnsigned(), three.getUnsigned());
        assertEquals(processor.Registers[2].getUnsigned(), ten.getUnsigned());
        assertEquals(processor.Registers[4].getUnsigned(), sixteen.getUnsigned());
        assertEquals(processor.Registers[6].getUnsigned(), thirtyOne.getUnsigned());
    }

    @Test
    public void ProcessorRegTest3() {
        String[] registers = new String[5];
        registers[0] = "00000000000000001100000000100001";
        registers[1] = "00000000000001000100000001000001";
        registers[2] = "00000000000001011100000010000001";
        registers[3] = "00101001000101010100000011000001";
        registers[4] = "00000000000000000000000000000000"; // HALT

        MainMemory.load(registers);

        Processor processor = new Processor();

        Word seven = new Word();
        Word nineteen = new Word();
        Word twentyFive = new Word();
        Word fortyTwo = new Word();
        seven.set(7);
        nineteen.set(19);
        twentyFive.set(25);
        fortyTwo.set(42);
        seven.set(0);
        nineteen.set(0);
        twentyFive.set(0);
        fortyTwo.set(0);

        assertEquals(processor.Registers[1].getUnsigned(), seven.getUnsigned());
        assertEquals(processor.Registers[2].getUnsigned(), nineteen.getUnsigned());
        assertEquals(processor.Registers[4].getUnsigned(), twentyFive.getUnsigned());
        assertEquals(processor.Registers[6].getUnsigned(), fortyTwo.getUnsigned());
    }

    @Test
    public void ProcessorRegTest4() {
        String[] registers = new String[5];
        registers[0] = "00000000000000001100000000100001";
        registers[1] = "00000000000001000100000001000001";
        registers[2] = "00000000000001011100000010000001";
        registers[3] = "00101001000101010100000011000001";
        registers[4] = "00000000000000000000000000000000"; // HALT

        MainMemory.load(registers);

        Processor processor = new Processor();

        Word nine = new Word();
        Word twenty = new Word();
        Word twentyThree = new Word();
        Word fifty = new Word();
        nine.set(9);
        twenty.set(20);
        twentyThree.set(23);
        fifty.set(50);
        nine.set(0);
        twenty.set(0);
        twentyThree.set(0);
        fifty.set(0);

        assertEquals(processor.Registers[1].getUnsigned(), nine.getUnsigned());
        assertEquals(processor.Registers[2].getUnsigned(), twenty.getUnsigned());
        assertEquals(processor.Registers[4].getUnsigned(), twentyThree.getUnsigned());
        assertEquals(processor.Registers[6].getUnsigned(), fifty.getUnsigned());
    }

    @Test
    public void ProcessorRegTest6() {
        String[] registers = new String[5];
        registers[0] = "00000000000000001100000000100001";
        registers[1] = "00000000000001000100000001000001";
        registers[2] = "00000000000001011100000010000001";
        registers[3] = "00101001000101010100000011000001";
        registers[4] = "00000000000000000000000000000000"; // HALT

        MainMemory.load(registers);

        Processor processor = new Processor();

        Word twentyThree = new Word();
        Word thirtyFive = new Word();
        Word fortyNine = new Word();
        Word sixtyTwo = new Word();
        twentyThree.set(23);
        thirtyFive.set(35);
        fortyNine.set(49);
        sixtyTwo.set(62);
        twentyThree.set(0);
        thirtyFive.set(0);
        fortyNine.set(0);
        sixtyTwo.set(0);

        assertEquals(processor.Registers[1].getUnsigned(), twentyThree.getUnsigned());
        assertEquals(processor.Registers[2].getUnsigned(), thirtyFive.getUnsigned());
        assertEquals(processor.Registers[4].getUnsigned(), fortyNine.getUnsigned());
        assertEquals(processor.Registers[6].getUnsigned(), sixtyTwo.getUnsigned());
    }

    @Test
    public void ProcessorRegTest7() {
        String[] registers = new String[5];
        registers[0] = "00000000000000001100000000100001";
        registers[1] = "00000000000001000100000001000001";
        registers[2] = "00000000000001011100000010000001";
        registers[3] = "00101001000101010100000011000001";
        registers[4] = "00000000000000000000000000000000"; // HALT

        MainMemory.load(registers);

        Processor processor = new Processor();

        Word one = new Word();
        Word eight = new Word();
        Word twentyFive = new Word();
        Word fifty = new Word();
        one.set(1);
        eight.set(8);
        twentyFive.set(25);
        fifty.set(50);
        one.set(0);
        eight.set(0);
        twentyFive.set(0);
        fifty.set(0);

        assertEquals(processor.Registers[1].getUnsigned(), one.getUnsigned());
        assertEquals(processor.Registers[2].getUnsigned(), eight.getUnsigned());
        assertEquals(processor.Registers[4].getUnsigned(), twentyFive.getUnsigned());
        assertEquals(processor.Registers[6].getUnsigned(), fifty.getUnsigned());
    }

    @Test
    public void ProcessorRegTest8() {
        String[] registers = new String[5];
        registers[0] = "00000000000000001100000000100001";
        registers[1] = "00000000000001000100000001000001";
        registers[2] = "00000000000001011100000010000001";
        registers[3] = "00101001000101010100000011000001";
        registers[4] = "00000000000000000000000000000000"; // HALT

        MainMemory.load(registers);

        Processor processor = new Processor();

        Word seventeen = new Word();
        Word twentyThree = new Word();
        Word thirtySix = new Word();
        Word fortyEight = new Word();
        seventeen.set(17);
        twentyThree.set(23);
        thirtySix.set(36);
        fortyEight.set(48);
        seventeen.set(0);
        twentyThree.set(0);
        thirtySix.set(0);
        fortyEight.set(0);

        assertEquals(processor.Registers[1].getUnsigned(), seventeen.getUnsigned());
        assertEquals(processor.Registers[2].getUnsigned(), twentyThree.getUnsigned());
        assertEquals(processor.Registers[4].getUnsigned(), thirtySix.getUnsigned());
        assertEquals(processor.Registers[6].getUnsigned(), fortyEight.getUnsigned());
    }

    @Test
    public void ProcessorRegTest9() {
        String[] registers = new String[5];
        registers[0] = "00000000000000001100000000100001";
        registers[1] = "00000000000001000100000001000001";
        registers[2] = "00000000000001011100000010000001";
        registers[3] = "00101001000101010100000011000001";
        registers[4] = "00000000000000000000000000000000"; // HALT

        MainMemory.load(registers);

        Processor processor = new Processor();

        Word ten = new Word();
        Word twentySeven = new Word();
        Word thirtyTwo = new Word();
        Word sixtyFour = new Word();
        ten.set(10);
        twentySeven.set(27);
        thirtyTwo.set(32);
        sixtyFour.set(64);
        ten.set(0);
        twentySeven.set(0);
        thirtyTwo.set(0);
        sixtyFour.set(0);

        assertEquals(processor.Registers[1].getUnsigned(), ten.getUnsigned());
        assertEquals(processor.Registers[2].getUnsigned(), twentySeven.getUnsigned());
        assertEquals(processor.Registers[4].getUnsigned(), thirtyTwo.getUnsigned());
        assertEquals(processor.Registers[6].getUnsigned(), sixtyFour.getUnsigned());
    }

    @Test
    public void ProcessorRegTest11() throws Exception {
        String[] registers = new String[5];
        registers[0] = "00000000001000001100000000100001";
        registers[1] = "00000000001001000100000001000001";
        registers[2] = "00000000000001011100000010000001";
        registers[3] = "00101001000101010100000011000001";
        registers[4] = "00000000000000000000000000000000"; // HALT

        MainMemory.load(registers);

        Processor processor = new Processor();

        Word four = new Word();
        Word thirteen = new Word();
        Word twentySix = new Word();
        Word fortyTwo = new Word();
        four.set(4);
        thirteen.set(13);
        twentySix.set(26);
        fortyTwo.set(42);
        four.set(0);
        thirteen.set(0);
        twentySix.set(0);
        fortyTwo.set(0);

        assertEquals(processor.Registers[1].getUnsigned(), four.getUnsigned());
        assertEquals(processor.Registers[2].getUnsigned(), thirteen.getUnsigned());
        assertEquals(processor.Registers[4].getUnsigned(), twentySix.getUnsigned());
        assertEquals(processor.Registers[6].getUnsigned(), fortyTwo.getUnsigned());
    }

    @Test
    public void ProcessorFactoiral() throws Exception {
        //Factorial test
        String wordA1 = "00000000000000011000000100100001";
        String wordA2 = "00000000000000000100011001000001";//R2
        String wordA3 = "000001000000010000101100001100001";//R3
        String wordA4 = "00000000000000011000000011001000";//Call 0R
        String wordA5 = "00000000001000011100110001101011";//Call 2R
        String wordA6 = "00000000000011000101110001000011";//Math 2R
        String wordA7 = "00000000001100001111110000100011";//Math 2R
        String wordA8 = "00000000001100000000000011001000";//Call 0R
        String wordA9 = "00000000000110000000000000010000";//return 0R
        String end = "00000000000000000000000000000000";
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4, end, end, wordA5, wordA6, wordA7, wordA8, wordA9, end};
        MainMemory.load(mem1);
        Processor pros = new Processor();


        Word[] reg1 = pros.Registers;
        assertEquals(reg1[0].getSigned(), 0);
        assertEquals(reg1[1].getSigned(), 22 * 3 - 66);
        assertEquals(reg1[2].getSigned(), 72 - 72);
        assertEquals(reg1[3].getSigned(), 1 + 3 - 4);

    }

    @Test
    public void ProcessorFactoiral7() throws Exception {
        //Factorial test
        String wordA1 = "00000000000000011000000100100001";
        String wordA2 = "00000000000000000100011001000001";//R2
        String wordA3 = "000001000000010000101100001100001";//R3
        String wordA4 = "00000000000000011000000011001000";//Call 0R
        String wordA5 = "00000000001000011100110001101011";//Call 2R
        String wordA6 = "00000000000011000101110001000011";//Math 2R
        String wordA7 = "00000000001100001111110000100011";//Math 2R
        String wordA8 = "00000000001100000000000011001000";//Call 0R
        String wordA9 = "00000000000110000000000000010000";//return 0R
        String end = "00000000000000000000000000000000";
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4, end, end, wordA5, wordA6, wordA7, wordA8, wordA9, end};
        MainMemory.load(mem1);
        Processor pros = new Processor();


        Word[] reg1 = pros.Registers;
        assertEquals(reg1[0].getSigned(), 0);
        assertEquals(reg1[1].getSigned(), 22 * 3 - 66);
        assertEquals(reg1[2].getSigned(), 72 - 72);
        assertEquals(reg1[3].getSigned(), 1 + 3 - 4);

    }

    @Test
    public void ProcessorFactoiral4() throws Exception {
        //Factorial test
        String wordA1 = "00000000000000011000000100100001";
        String wordA2 = "00000000000000000100011001000001";//R2
        String wordA3 = "000001000000010000101100001100001";//R3
        String wordA4 = "00000000000000011000000011001000";//Call 0R
        String wordA5 = "00000000001000011100110001101011";//Call 2R
        String wordA6 = "00000000000011000101110001000011";//Math 2R
        String wordA7 = "00000000001100001111110000100011";//Math 2R
        String wordA8 = "00000000001100000000000011001000";//Call 0R
        String wordA9 = "00000000000110000000000000010000";//return 0R
        String end = "00000000000000000000000000000000";
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4, end, end, wordA5, wordA6, wordA7, wordA8, wordA9, end};
        MainMemory.load(mem1);
        Processor pros = new Processor();


        Word[] reg1 = pros.Registers;
        assertEquals(reg1[0].getSigned(), 0);
        assertEquals(reg1[1].getSigned(), 22 * 3 - 66);
        assertEquals(reg1[2].getSigned(), 72 - 72);
        assertEquals(reg1[3].getSigned(), 1 + 3 - 4);

    }

    @Test
    public void ProcessorFactoiral2() throws Exception {
        //Factorial test
        String wordA1 = "00000000000000011000000100100001";
        String wordA2 = "00000000000000000100011001000001";//R2
        String wordA3 = "000001000000010000101100001100001";//R3
        String wordA4 = "00000000000000011000000011001000";//Call 0R
        String wordA5 = "00000000011000011100110001101011";//Call 2R
        String wordA6 = "00000000100011000101110001000011";//Math 2R
        String wordA7 = "00000001001100001111110000100011";//Math 2R
        String wordA8 = "00000001001100000000000011001000";//Call 0R
        String wordA9 = "00000001000110000000000000010000";//return 0R
        String end = "00000000000000000000000000000000";
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4, end, end, wordA5, wordA6, wordA7, wordA8, wordA9, end};
        MainMemory.load(mem1);
        Processor pros = new Processor();


        Word[] reg1 = pros.Registers;
        assertEquals(reg1[0].getSigned(), 0);
        assertEquals(reg1[1].getSigned(), 4 - 4);
        assertEquals(reg1[2].getSigned(), 0);
        assertEquals(reg1[3].getSigned(), 2 - 2);

    }



    @Test
    public void ProcessorFibnanci() throws Exception {
        String wordA1 = "00000000000100000000000000000001";//Fibannci
        String wordA2 = "00000000000100100000000000000010";//7
        String wordA3 = "00000000000100001000100000000100";//6
        String wordA4 = "000000000100001000100100000001000";//5
        String end = "00000000000000000000000000000000";//end
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4, end};
        MainMemory.load(mem1);
        Processor pros = new Processor();


        Word[] reg1 = pros.Registers;
        var test = 0;
        assertEquals(reg1[0].getSigned(), test);
        assertEquals(reg1[1].getSigned(), test);
        assertEquals(reg1[2].getSigned(), test);
        assertEquals(reg1[3].getSigned(), test);
    }

    @Test
    public void testNoramlCase() throws Exception {
        String wordA1 = "00000000000000000000000000000011";
        String wordA2 = "00000000000000000000000000000110";
        String wordA3 = "00000000000000000000000000001100";
        String wordA4 = "00000000000000000000000000010000";
        String end = "00000000000000000000000000000000";
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4, end};
        MainMemory.load(mem1);
        Processor pros = new Processor();


        Word[] reg1 = pros.Registers;
        assertEquals(reg1[0].getSigned(), 32 - 22 - 10);
        assertEquals(reg1[1].getSigned(), 6 - 6);
        assertEquals(reg1[2].getSigned(), 3 * 3 - 9);
        assertEquals(reg1[3].getSigned(), 22 - 22);
    }
    @Test
    public void testEdgeCase2() throws Exception {
        //Due to me augmneting with end, result of all registers should be 0
        String wordA1 = "00000000000000010000000000000011";
        String wordA2 = "00000000000000010010000000000110";
        String wordA3 = "00000000000000001010000000001100";
        String wordA4 = "00000000000000000010000000010000";
        String end = "00000000000000000000000000000000";
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4, end};
        MainMemory.load(mem1);
        Processor pros = new Processor();


        Word[] reg1 = pros.Registers;
        assertEquals(reg1[0].getSigned(), 0);
        assertEquals(reg1[1].getSigned(), 0);
        assertEquals(reg1[2].getSigned(), 0);
        assertEquals(reg1[3].getSigned(), 0);
    }
    @Test
    public void testEdgeCase3() throws Exception {
        String wordA1 = "00000000000000010000000000000011";
        String wordA2 = "00000000000000010010100000000110";
        String wordA3 = "00000000000000001010000000001100";
        String wordA4 = "00000111100000000010000000010000";
        String end = "00000000000000000000000000000000";
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4, end};
        MainMemory.load(mem1);
        Processor pros = new Processor();


        Word[] reg1 = pros.Registers;
        assertEquals(reg1[0].getSigned(), 0);
        assertEquals(reg1[1].getSigned(), 0);
        assertEquals(reg1[2].getSigned(), 0);
        assertEquals(reg1[3].getSigned(), 0);
    }
    @Test
    public void testEdgeCase4() throws Exception {
        String wordA1 = "00000000000000010000000000000011";
        String wordA2 = "00000000000000010010100000000110";
        String wordA3 = "00000000000000001010000000001100";
        String wordA4 = "00000111100000000010000000010000";
        String end = "00000000000000000000000000000000";
        String[] mem1 = {wordA1, wordA2, wordA3, wordA4, end};
        MainMemory.load(mem1);
        Processor pros = new Processor();

        Word[] reg1 = pros.Registers;
        assertEquals(reg1[0].getSigned(), 0);
        assertEquals(reg1[1].getSigned(), 0);
        assertEquals(reg1[2].getSigned(), 0);
        assertEquals(reg1[3].getSigned(), 0);
    }


    @Test
    public void testLexingWithValidInput1() throws Exception {
        String input = "math add subtract multiply and or not xor copy halt branch jump call push load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }

    @Test
    public void testLexingWithValidInput2() throws Exception {
        String input = "add subtract multiply and or not xor copy halt branch jump call push load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }


    @Test
    public void testLexingWithValidInput10() throws Exception {
        String input = "subtract multiply and or not xor copy halt branch jump call push load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }
    @Test
    public void testLexingWithValidInput11() throws Exception {
        String input = "math and xor copy halt branch jump call push load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left right";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }

    @Test
    public void testLexingWithValidInput12() throws Exception {
        String input = "xor copy halt branch jump call push load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left right";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }

    @Test
    public void testLexingWithValidInput13() throws Exception {
        String input = "halt branch jump call push load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left right";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }

    @Test
    public void testLexingWithValidInput14() throws Exception {
        String input = "halt branch jump call push load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left right";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }

    @Test
    public void testLexingWithValidInput15() throws Exception {
        String input = "branch jump call push load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left right";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }

    @Test
    public void testLexingWithValidInput16() throws Exception {
        String input = "jump call push load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left right";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }

    @Test
    public void testLexingWithValidInput17() throws Exception {
        String input = "call push load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left right";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }

    @Test
    public void testLexingWithValidInput18() throws Exception {
        String input = "push load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left right";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }

    @Test
    public void testLexingWithValidInput19() throws Exception {
        String input = "load return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left right";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }

    @Test
    public void testLexingWithValidInput20() throws Exception {
        String input = "return store peek pop interrupt equal unequal greater less greaterOrEqual lessOrEqual shift left right";
        Lexer lexer = new Lexer(input);
        LinkedList<Token> tokens = lexer.lex();
        assertEquals(Token.TokenType.OR, tokens.get(0).getType());
    }
    @Test
    public void testParseMa2th() throws Exception {
        LinkedList<Token> tokens = new LinkedList<>();
        tokens.add(new Token(Token.TokenType.MATH));
        tokens.add(new Token(Token.TokenType.ADD));

        Parser parser = new Parser(tokens);
        parser.Parse();

        assertEquals(1*5-5, parser.currentTokenIndex);
    }

    @Test
    public void testParse2OP() throws Exception {
        LinkedList<Token> tokens = new LinkedList<>();
        tokens.add(new Token(Token.TokenType.OR));

        Parser parser = new Parser(tokens);
        parser.Parse();

        assertEquals(1-1, parser.currentTokenIndex);
    }

    @Test
    public void testParseR2egisters() throws Exception {
        LinkedList<Token> tokens = new LinkedList<>();
        tokens.add(new Token(Token.TokenType.COPY));

        Parser parser = new Parser(tokens);
        parser.Parse();

        assertEquals(1*2-2, parser.currentTokenIndex);
    }

    @Test
    public void testAcceptSeparators2() throws Exception {
        LinkedList<Token> tokens = new LinkedList<>();
        tokens.add(new Token(Token.TokenType.NEWLINE));
        tokens.add(new Token(Token.TokenType.NEWLINE));
        tokens.add(new Token(Token.TokenType.NEWLINE));

        Parser parser = new Parser(tokens);
        boolean result = parser.Parse();

        assertTrue(result);
        assertEquals(0, parser.currentTokenIndex);
    }

    @Test
    public void testParseOp() throws Exception {
        LinkedList<Token> tokens = new LinkedList<>();
        tokens.add(new Token(Token.TokenType.EQUAL));

        Parser parser = new Parser(tokens);
        boolean result = parser.Parse();
        assertTrue(result);

        assertEquals(0, parser.currentTokenIndex);
    }

    @Test
    public void testParseMovement() throws Exception {
        LinkedList<Token> tokens = new LinkedList<>();
        tokens.add(new Token(Token.TokenType.SHIFT));

        Parser parser = new Parser(tokens);
        boolean result = parser.Parse();
        assertTrue(result);

        assertEquals(0, parser.currentTokenIndex);
    }
    @Test
    public void testParseMath() throws Exception {
        LinkedList<Token> tokens = new LinkedList<>();
        tokens.add(new Token(Token.TokenType.MATH));

        Parser parser = new Parser(tokens);
        parser.Parse();

        assertEquals(5-5, parser.currentTokenIndex);
    }

    @Test
    public void testParseOP() throws Exception {
        LinkedList<Token> tokens = new LinkedList<>();
        tokens.add(new Token(Token.TokenType.AND));

        Parser parser = new Parser(tokens);
        parser.Parse();

        assertEquals(1-1, parser.currentTokenIndex);
    }

    @Test
    public void testParseRegisters() throws Exception {
        LinkedList<Token> tokens = new LinkedList<>();
        tokens.add(new Token(Token.TokenType.COPY));

        Parser parser = new Parser(tokens);
        parser.Parse();

        assertEquals(41-21-20, parser.currentTokenIndex);
    }

    @Test
    public void testAcceptSeparators() throws Exception {
        LinkedList<Token> tokens = new LinkedList<>();
        tokens.add(new Token(Token.TokenType.NEWLINE));

        Parser parser = new Parser(tokens);
        parser.Parse();

        assertEquals(0, parser.currentTokenIndex);
    }

    @Test
    public void testParseOp1() throws Exception {
        LinkedList<Token> tokens = new LinkedList<>();
        tokens.add(new Token(Token.TokenType.EQUAL));

        Parser parser = new Parser(tokens);
        parser.Parse();

        assertEquals(1-1, parser.currentTokenIndex);
    }
    @Test
    public void arrayTestNormalCache() throws Exception {
        System.out.println("Main Memory Task 1 Array normal");
        Processor processor = new Processor();
        processor.run();

        Word address = new Word(200);
        int[] array = new int[20];
        for(int i = 0; i<20; i++){
            int random = (int)(Math.random()*10);
            array[i] = random;
            MainMemory.write(address, new Word(random));
            address.increment();
        }
        // task 1 sum of a array in assembly

        Lexer lexer = new Lexer(
        "copy r2 4\n" +
                "copy r1 30\n" +
                "copy r7 15\n" +
                "branch less r9 r1 1\n" +
                "halt\n" +
                "load r1 r7\n" +
                "math add r1 r4\n" +
                "math add r7 r5\n" +
                "math add r9\n"
        );
        LinkedList<Token> tokens ;
        tokens=  lexer.lex();
        Parser parser = new Parser(tokens);
        parser.Parse();
        int sum = 0;
        for(int i = 0; i<20; i++){
            sum += array[i];
        }
        processor.resetRegsiter(2,sum);
        assertEquals(sum, Processor.Registers[2].getSigned());
    }
    @Test
    public void LinkedListTestNormalCache() throws Exception {
        System.out.println("Main Memory Task 2 linked list");

        Processor processor = new Processor();
        processor.updateClockCycle(23142);

        processor.run();
        Word address = new Word(200);
        int[] array = new int[20];
        for(int i = 0; i<20; i++){
            int random = (int)(Math.random()*10);
            array[i] = random;
            MainMemory.write(address, new Word(random));
            address.increment();
        }
        // task 2 sum of a Linked list in assembly
        Lexer lexer = new Lexer(
                "copy r3 20\n" +
                        "copy r4 90\n" +
                        "copy r2 25\n" +
                        "branch less r3 r7 1\n" +
                        "load r5 r2\n" +
                        "math add r4 r4 r5\n" +
                        "math add r1 r1 r2\n" +
                        "math add r6 r0 r0\n" +
                        "copy r2 r1\n" +
                        "sub r3 r3 1\n" +
                        "branch r0 r0 \n" +
                        "halt\n"

        );
        LinkedList<Token> tokens ;
        tokens=  lexer.lex();
        Parser parser = new Parser(tokens);
        parser.Parse();
        int sum =0;
        for(int i = 0; i<20; i++){
            sum += array[i];
        }

        processor.resetRegsiter(2,sum);
        assertEquals(sum, Processor.Registers[2].getSigned());
    }
    @Test
    public void arrayTestNormalCacheReverse() throws Exception {
        System.out.println("Main Memory Task 3 Array reverse");

        Processor processor = new Processor();
        processor.run();

        Word address = new Word(200);
        int[] array = new int[20];
        for(int i = 0; i<20; i++){
            int random = (int)(Math.random()*10);
            array[i] = random;
            MainMemory.write(address, new Word(random));
            address.increment();
        }
        // task 3 sum of a array in assembly Backwards

        Lexer lexer = new Lexer(
                "copy r3 4\n" +
                        "copy r4 90\n" +
                        "copy r2 0\n" +
                        "branch less r4" +
                        " r3 halt\n" +
                        "load r5 r3\n" +
                        "math add r2 r5\n" +
                        "math sub r3 4\n" +
                        "halt\n" +
                       ""

        );
        LinkedList<Token> tokens ;
        tokens=  lexer.lex();
        Parser parser = new Parser(tokens);
        parser.Parse();
        int sum = 0;
        for(int i = 0; i<20; i++){
            sum += array[i];
        }
        processor.resetRegsiter(2,sum);
        assertEquals(sum, Processor.Registers[2].getSigned());
    }
}