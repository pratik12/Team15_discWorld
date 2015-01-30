package com.testcase;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 1/27/15
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test;
 import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 http://java2novice.com/junit-examples/
 */

public class SuitTest {

    @Test
    public void testEvenOddNumber() {

        MyEvenOdd meo = new MyEvenOdd();

        assertEquals("10 is a even number", true, meo.isEvenNumber(10));

    }

    @Test
    public void myTestMethod() {

        String[] expectedOutput = {"apple", "mango", "grape"};

        String[] methodOutput = {"apple", "mango", "grape"};

        assertArrayEquals(expectedOutput, methodOutput);
    }
}
