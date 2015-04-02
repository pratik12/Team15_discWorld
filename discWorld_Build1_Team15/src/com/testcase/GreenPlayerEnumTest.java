package com.testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.app.Player;
import com.app.PlayerCardUtility;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;

public class GreenPlayerEnumTest {
	PlayerCardUtility obj ;
	@Test
	public void testForNullReutrnENUM() {
		boolean msg = false;
		try {
			
			GreenPlayerCardEnum temp = (PlayerCardUtility.getEnumInstance(null));
			assertNull(temp);
		} catch (Exception e) {
			msg = true;
		}
		assertTrue(msg);
	}
	
	
	@Test
	public void testForEnumInstanceReturn() {
			
			assertTrue(PlayerCardUtility.getEnumInstance("gaspode") instanceof GreenPlayerCardEnum);
	}
	
	@Test
	public void testForNotNullENUMInstance() {
		assertNotNull(PlayerCardUtility.getEnumInstance("gaspode") instanceof GreenPlayerCardEnum);
		assertNotNull(PlayerCardUtility.getEnumInstance("modo") instanceof GreenPlayerCardEnum);
		assertNotNull(PlayerCardUtility.getEnumInstance("hex") instanceof GreenPlayerCardEnum);
	}
	
	@Test
	public void testForTakeMoney() {
			Player obj = new Player("R");
			boolean msg = false;
			try {
				
				msg = (GreenPlayerCardEnum.GLOBALOBJ.takeMoneyFromPlayer(5, obj, null));
			} catch (Exception e) {
				msg = true;
			}
			assertTrue(!msg);
	}
	
	@Test
	public void testForSelectPlayer() {
			Player obj = new Player("R");
			Player msg ;
			try {
				
				msg = (GreenPlayerCardEnum.GLOBALOBJ.selectPlayer(obj, "bjhbhsjfg"));
				msg = (GreenPlayerCardEnum.GLOBALOBJ.selectPlayer(obj, "1325456"));
				msg = (GreenPlayerCardEnum.GLOBALOBJ.selectPlayer(obj, null));
				assertNull(msg);
				assertTrue(!(msg instanceof Player));
			} catch (Exception e) {
				
			}
	}
	
	@Test
	public void testForNullSelectPlayer() {
			Player msg ;
			try {
				
				msg = (GreenPlayerCardEnum.GLOBALOBJ.selectPlayer(null, "R"));
				msg = (GreenPlayerCardEnum.GLOBALOBJ.selectPlayer(null, "G"));
				msg = (GreenPlayerCardEnum.GLOBALOBJ.selectPlayer(null, null));
				assertNull(msg);
				assertTrue(!(msg instanceof Player));
			} catch (Exception e) {
				
			}
	}

}
