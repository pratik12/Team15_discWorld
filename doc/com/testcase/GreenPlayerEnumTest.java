package com.testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.app.PlayerCardUtility;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;

public class GreenPlayerEnumTest {
	PlayerCardUtility obj ;
	@Test
	public void test() {
		boolean msg = false;
		try {
			
			GreenPlayerCardEnum temp = (PlayerCardUtility.getEnumInstance(null));
			assertNull(temp);
		} catch (Exception e) {
			msg = true;
		}
		assertTrue(msg);
	}

}
