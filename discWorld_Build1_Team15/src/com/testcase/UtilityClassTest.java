package com.testcase;

import com.app.Area;
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
    public void testPointCalculation() {
        utility.announceWinner();
    }

    @Test
    public void testTroubleMarkerCalculation() {
        utility.calculateNumberOfTroubleMarkers();
    }

    @Test
    public void testNumberOfMinions() {
        utility.getNumberOfMinions("Isle of Gods");
    }

    @Test
    public void testGetNextPlayer() {
        utility.giveTurnToleft();
    }

    @Test
    public void testCheckWinningCondition() {
        String personalityCardType = "LordRust";
        assertEquals(new LordSelRusWor(), WinningCircumstancesFactory.getWinningCircumstance(personalityCardType));
        personalityCardType = "LordDeWorde";
        assertEquals(new LordSelRusWor(), WinningCircumstancesFactory.getWinningCircumstance(personalityCardType));
        personalityCardType = "LordVetinari";
        assertEquals(new LordVetinari(), WinningCircumstancesFactory.getWinningCircumstance(personalityCardType));
        personalityCardType = "LordSelachii";
        assertEquals(new LordSelRusWor(), WinningCircumstancesFactory.getWinningCircumstance(personalityCardType));
        personalityCardType = "DragonKingOfArms";
        assertEquals(new DragonKingOfArms(), WinningCircumstancesFactory.getWinningCircumstance(personalityCardType));
        personalityCardType = "Chrysoprase";
        assertEquals(new Chrysoprase(), WinningCircumstancesFactory.getWinningCircumstance(personalityCardType));
        personalityCardType = "CommanderVimes";
        assertEquals(new CommanderVimes(), WinningCircumstancesFactory.getWinningCircumstance(personalityCardType));
    }

}
