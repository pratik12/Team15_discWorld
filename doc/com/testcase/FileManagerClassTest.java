package com.testcase;

import java.io.FileNotFoundException;

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
        assertTrue(FileManager.isFileNameValid(" test.txt"));
        assertTrue(FileManager.isFileNameValid("test.txt"));
        assertTrue(FileManager.isFileNameValid("test123.txt"));
        assertTrue(FileManager.isFileNameValid("test_.txt"));
        assertFalse(FileManager.isFileNameValid(" "));
    }

	@Test
    public void checkFileExistance() {
        //Exception thrown = null;
        try{
        	FileManager.loadFile("C:\\fakeFile.txt","fakeFile.txt");
        	fail("Should throw file not found");
        }catch(Exception e){
        System.out.println("Error: Could not find database/storage.");
        System.out.println(e.getMessage()); 
        //throw e;
        }
        //Assert.assertNotNull(thrown);
    }
}
