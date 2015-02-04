package com.testcase;

import com.app.FileManager;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/1/15
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileManagerClassTest {

    @Test
    public void checkToWriteTextFile() {
        try {
            FileManager.saveMap("../test.txt");
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void checkToReadSafeTextFile() {
        try {
            FileManager.loadFile("../test.txt");
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }


}
