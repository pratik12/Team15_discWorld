package com.testcase;

import java.util.ArrayList;

import com.app.Area;
import com.app.BoardGame;
import com.app.common.Utility;
import com.app.rules.*;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 3/11/15
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class UtilityClassTest {
    Utility utility = new Utility();

    @Test
        public void testTroubleMarkerCalculation() {
            assertNotNull(utility.calculateNumberOfTroubleMarkers());
        }
    
    @Test
    public void testRandomNumber() {
        assertTrue(utility.rollDie() >= 1);
        assertTrue(utility.rollDie() < 13);
    }

    @Test
    public void testRandomNumberWithADigit() {
        assertTrue(utility.getRandNum(5) >= 1);
        assertTrue(utility.getRandNum(6) < 7);
    }

    @Test(expected = NullPointerException.class)
    public void testGetCorrectAreaByNumber() {
        
    	//Area area = new Area("Isle of Gods", 10, "12");
    	ArrayList<Area> board_areas = new ArrayList<Area>();
    	board_areas.add(new Area("Isle of Gods", 10, "12"));
        assertNull(utility.getAreaByNumber(10) instanceof Area);
    }

    @Test
    public void testNumberOfMinions() {
        assertEquals(0, utility.getNumberOfMinions("Isle of Gods"));
    }

    @Test
    public void testGetNextPlayer() {
    	System.out.println("Enter player color");
        assertTrue(utility.giveTurnToleft() instanceof String);
    }

    @Test
    public void testCheckWinningCondition() {
        String personalityCardType = "LordRust";
        assertTrue(WinningCircumstancesFactory.getWinningCircumstance(personalityCardType) instanceof LordSelRusWor);
        personalityCardType = "LordDeWorde";
        assertTrue(WinningCircumstancesFactory.getWinningCircumstance(personalityCardType) instanceof LordSelRusWor);
        personalityCardType = "LordVetinari";
        assertTrue(WinningCircumstancesFactory.getWinningCircumstance(personalityCardType) instanceof LordVetinari);
        personalityCardType = "LordSelachii";
        assertTrue(WinningCircumstancesFactory.getWinningCircumstance(personalityCardType) instanceof LordSelRusWor);
        personalityCardType = "DragonKingOfArms";
        assertTrue(WinningCircumstancesFactory.getWinningCircumstance(personalityCardType) instanceof DragonKingOfArms);
        personalityCardType = "Chrysoprase";
        assertTrue(WinningCircumstancesFactory.getWinningCircumstance(personalityCardType) instanceof Chrysoprase);
        personalityCardType = "CommanderVimes";
        assertTrue(WinningCircumstancesFactory.getWinningCircumstance(personalityCardType) instanceof CommanderVimes);
    }

}
