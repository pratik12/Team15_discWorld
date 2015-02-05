package com.testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.app.BoardGame;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

public class BoardGameClassTest {

	@Test
	public void testSingletonCreation() {
		
		assertEquals(null,BoardGame.getInstance());
		
	}

}
