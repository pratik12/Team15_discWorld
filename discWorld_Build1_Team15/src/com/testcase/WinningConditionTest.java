package com.testcase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.JSONException;
import org.junit.Test;

import com.app.Area;
import com.app.BoardGame;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.rules.WinningCircumstancesFactory;

public class WinningConditionTest {
	
	@Test
	public void testCommanderVimes() throws JSONException {
		BoardGame.setInstance();
		ArrayList<GreenPlayerCardEnum> temp = new ArrayList<GreenPlayerCardEnum>(0);
		BoardGame.player_cards = temp;
		boolean res = WinningCircumstancesFactory.getWinningCircumstance("CommanderVimes").isWinner();
		assertTrue(res);
	}
	

}
