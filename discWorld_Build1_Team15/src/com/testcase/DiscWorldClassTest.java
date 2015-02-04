package com.testcase;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 1/27/15
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */

import com.gui.WorldDiscDemo;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * http://java2novice.com/junit-examples/
 */

public class DiscWorldClassTest {

    @Test
    public void checkInputNumberValidity() {
        assertFalse(WorldDiscDemo.inputNumberIsValid("1"));
        assertFalse(WorldDiscDemo.inputNumberIsValid("5"));
        assertTrue(WorldDiscDemo.inputNumberIsValid("2"));
        assertTrue(WorldDiscDemo.inputNumberIsValid("3"));
        assertTrue(WorldDiscDemo.inputNumberIsValid("4"));
    }

    @Test
    public void checkToCreateGUI() {
        try {
            WorldDiscDemo.createAndShowGUI();
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
