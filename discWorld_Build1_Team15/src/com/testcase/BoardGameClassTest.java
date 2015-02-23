package com.testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.app.BoardGame;

public class BoardGameClassTest {

	@Test
	public void testSingletonCreation() {
		
		assertEquals(null,BoardGame.getInstance());
		
	}

}
