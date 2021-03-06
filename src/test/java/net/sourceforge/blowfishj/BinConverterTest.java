/*
 * Copyright 1997-2005 Markus Hahn 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sourceforge.blowfishj;

import net.sourceforge.blowfishj.crypt.BinConverter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the binary converters.
 */
public class BinConverterTest {
    @Test
    public final void testByteArrayToInt() {
        byte[] dat =
                {
                        (byte) 0x00, (byte) 0xcc, (byte) 0xaf, (byte) 0x43, (byte) 0x1e
                };

        assertTrue(BinConverter.byteArrayToInt(dat, 0) == 0x00ccaf43);
        assertTrue(BinConverter.byteArrayToInt(dat, 1) == 0xccaf431e);
    }


    @Test
    public final void testIntToByteArray() {
        byte[] testb = new byte[5];

        BinConverter.intToByteArray(0x01234567, testb, 0);

        assertTrue(testb[0] == 0x01);
        assertTrue(testb[1] == 0x23);
        assertTrue(testb[2] == 0x45);
        assertTrue(testb[3] == 0x67);

        BinConverter.intToByteArray(0x89abcdef, testb, 1);

        assertTrue(testb[1] == (byte) 0x89);
        assertTrue(testb[2] == (byte) 0xab);
        assertTrue(testb[3] == (byte) 0xcd);
        assertTrue(testb[4] == (byte) 0xef);
    }


    @Test
    public final void testByteArrayToLong() {
        byte[] dat =
                {
                        (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67,
                        (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef,
                        (byte) 0xcc
                };

        assertTrue(BinConverter.byteArrayToLong(dat, 0) == 0x0123456789abcdefL);
        assertTrue(BinConverter.byteArrayToLong(dat, 1) == 0x23456789abcdefccL);
    }


    @Test
    public final void testLongToByteArray() {
        byte[] testb = new byte[9];

        BinConverter.longToByteArray(0x0123456789abcdefL, testb, 0);

        assertTrue(testb[0] == (byte) 0x01);
        assertTrue(testb[1] == (byte) 0x23);
        assertTrue(testb[2] == (byte) 0x45);
        assertTrue(testb[3] == (byte) 0x67);
        assertTrue(testb[4] == (byte) 0x89);
        assertTrue(testb[5] == (byte) 0xab);
        assertTrue(testb[6] == (byte) 0xcd);
        assertTrue(testb[7] == (byte) 0xef);

        BinConverter.longToByteArray(0x0123456789abcdefL, testb, 1);

        assertTrue(testb[1] == (byte) 0x01);
        assertTrue(testb[2] == (byte) 0x23);
        assertTrue(testb[3] == (byte) 0x45);
        assertTrue(testb[4] == (byte) 0x67);
        assertTrue(testb[5] == (byte) 0x89);
        assertTrue(testb[6] == (byte) 0xab);
        assertTrue(testb[7] == (byte) 0xcd);
        assertTrue(testb[8] == (byte) 0xef);
    }


    @Test
    public final void testIntArrayToLong() {
        int[] dat =
                {
                        0x01234567, 0x89abcdef, 0xcc01aa02
                };

        assertTrue(BinConverter.intArrayToLong(dat, 0) == 0x0123456789abcdefL);
        assertTrue(BinConverter.intArrayToLong(dat, 1) == 0x89abcdefcc01aa02L);
    }


    @Test
    public final void testLongToIntArray() {
        int[] testn = new int[3];

        BinConverter.longToIntArray(0x0123456789abcdefL, testn, 0);

        assertThat(testn[0], is(0x01234567));
        assertThat(testn[1], is(0x89abcdef));

        BinConverter.longToIntArray(0x0123456789abcdefL, testn, 1);

        assertThat(testn[1], is(0x01234567));
        assertThat(testn[2], is(0x89abcdef));
    }


    @Test
    public final void testMakeLong() {
        assertTrue(BinConverter.makeLong(0x89abcdef, 0x01234567) == 0x0123456789abcdefL);
    }


    @Test
    public final void testLongLo32() {
        assertTrue(BinConverter.longLo32(0x0123456789abcdefL) == 0x89abcdef);
    }


    @Test
    public final void testLongHi32() {
        assertTrue(BinConverter.longHi32(0x0123456789abcdefL) == 0x01234567);
    }


    @Test
    public final void testBytesToHexStr() {
        byte[] dat =
                {
                        (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67,
                        (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef,
                };

        String sHex = "0123456789abcdef";

        assertThat(sHex, is(BinConverter.bytesToHexStr(dat)));

        sHex = "456789abcd";

        assertThat(sHex, is(BinConverter.bytesToHexStr(dat, 2, 5)));
    }


    @Test
    public final void testhexStrToBytes() {
        byte[] testb = new byte[9];

        BinConverter.hexStrToBytes("0123456789abcdef", testb, 0, 0, 8);

        assertTrue(testb[0] == (byte) 0x01);
        assertTrue(testb[1] == (byte) 0x23);
        assertTrue(testb[2] == (byte) 0x45);
        assertTrue(testb[3] == (byte) 0x67);
        assertTrue(testb[4] == (byte) 0x89);
        assertTrue(testb[5] == (byte) 0xab);
        assertTrue(testb[6] == (byte) 0xcd);
        assertTrue(testb[7] == (byte) 0xef);

        BinConverter.hexStrToBytes("0123456789abcdef", testb, 4, 1, 5);

        assertTrue(testb[1] == (byte) 0x45);
        assertTrue(testb[2] == (byte) 0x67);
        assertTrue(testb[3] == (byte) 0x89);
        assertTrue(testb[4] == (byte) 0xab);
        assertTrue(testb[5] == (byte) 0xcd);
    }


    @Test
    public final void testByteArrayToStr() {
        byte[] testb = new byte[52];

        for (int nI = 0; nI < testb.length; nI += 2) {
            testb[nI] = 0;
            testb[nI + 1] = (byte) (0x061 + (nI >> 1));

        }

        assertThat("abcdefghijklmnopqrstuvwxyz", is(BinConverter.byteArrayToStr(testb, 0, testb.length)));
    }
}
