package com.testcase;

import com.app.FileManager;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/1/15
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileManagerClassTest {

    @Test
    public void checkFilaNameValidity() {
        assertFalse(FileManager.isFileNameValid(" test.txt"));
        assertTrue(FileManager.isFileNameValid("test.txt"));
    }

    @Test
    public void checkArrayListValidity() {
        Exception thrown = null;
        try {
            FileManager.loadFile("fake","");
        } catch (Exception e) {
            thrown = e;
        }

        Assert.assertNotNull(thrown);
    }
}
