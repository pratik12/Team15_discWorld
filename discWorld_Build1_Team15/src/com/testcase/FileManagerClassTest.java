package com.testcase;

import com.app.FileManager;
import org.json.JSONException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
        assertTrue(FileManager.isFileNameValid(" test.txt"));
        assertTrue(FileManager.isFileNameValid("test.txt"));
        assertTrue(FileManager.isFileNameValid("test123.txt"));
        assertTrue(FileManager.isFileNameValid("test_.txt"));
        assertFalse(FileManager.isFileNameValid(" "));
    }

    @Test
    public void testTheDragon() {

        ArrayList<String> initializedRecords = new ArrayList<String>();
        try {
            initializedRecords = FileManager.loadFile("theDragon.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        assertTrue(!initializedRecords.isEmpty());
    }
}
