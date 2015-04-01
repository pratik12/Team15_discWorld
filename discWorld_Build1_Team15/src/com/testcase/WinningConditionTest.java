package com.testcase;

import com.app.Area;
import com.app.BoardGame;
import com.app.FileManager;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.rules.WinningCircumstancesFactory;
import org.json.JSONException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class WinningConditionTest {

    @Test
    public void testCommanderVimes() throws JSONException {
        BoardGame.setInstance();
        ArrayList<GreenPlayerCardEnum> emptyGreenPlayerCards = new ArrayList<GreenPlayerCardEnum>(0);
        BoardGame.player_cards = emptyGreenPlayerCards;
        String commanderVimes = WinningCircumstancesFactory.PersonalityCards.CommanderVimes.getName();
        assertEquals(String.valueOf(WinningCircumstancesFactory.PersonalityCards.CommanderVimes.getName()), commanderVimes);
        //PlayerCard is Empty
        boolean isCommanderVimesWinner = WinningCircumstancesFactory.getWinningCircumstance(commanderVimes).isWinner();
        assertTrue(isCommanderVimesWinner);

        for (GreenPlayerCardEnum playerCardItem : GreenPlayerCardEnum.values()) {
            if (!(playerCardItem.getName().equalsIgnoreCase("self")))
                BoardGame.player_cards.add(playerCardItem);
        }

        //PlayerCard is not Empty
        isCommanderVimesWinner = WinningCircumstancesFactory.getWinningCircumstance(String.valueOf(WinningCircumstancesFactory.PersonalityCards.CommanderVimes.getName())).isWinner();
        assertFalse(isCommanderVimesWinner);

    }

    @Test
    public void testDragonKingOfArms() throws JSONException {
        BoardGame.setInstance();
        String dragonKingOfArms = WinningCircumstancesFactory.PersonalityCards.DragonKingOfArms.getName();
        assertEquals(String.valueOf(WinningCircumstancesFactory.PersonalityCards.DragonKingOfArms.getName()), dragonKingOfArms);
        //No troublemarkers
        boolean isDragonKingOfArmsWinner = WinningCircumstancesFactory.getWinningCircumstance(dragonKingOfArms).isWinner();
        assertFalse(isDragonKingOfArmsWinner);

        for (Area maparea : BoardGame.board_areas) {
            maparea.setTroubleMarkers(true);
            maparea.setTroubleMarkerArea(maparea.getTroubleMarkerArea());
        }
        //12 troublemarkers
        isDragonKingOfArmsWinner = WinningCircumstancesFactory.getWinningCircumstance(String.valueOf(WinningCircumstancesFactory.PersonalityCards.DragonKingOfArms.getName())).isWinner();
        assertFalse(isDragonKingOfArmsWinner);

        int counter = 0;
        for (Area maparea : BoardGame.board_areas) {
            maparea.setTroubleMarkerArea(null);
            maparea.setTroubleMarkers(false);
            counter++;
            if (counter == 4)
                break;
        }

        //8 troublemarkers
        isDragonKingOfArmsWinner = WinningCircumstancesFactory.getWinningCircumstance(String.valueOf(WinningCircumstancesFactory.PersonalityCards.DragonKingOfArms.getName())).isWinner();
        assertTrue(isDragonKingOfArmsWinner);

    }


    @Test
    public void testLordSelRusWor() throws JSONException {

        String lordSelRusWorde = WinningCircumstancesFactory.PersonalityCards.LordDeWorde.getName();

        boolean isLordSelRusWorWinner = WinningCircumstancesFactory.getWinningCircumstance(lordSelRusWorde).isWinner();
        assertFalse(isLordSelRusWorWinner);

        try {
            FileManager.loadFile("lordSelRusWor.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        isLordSelRusWorWinner = WinningCircumstancesFactory.getWinningCircumstance(String.valueOf(WinningCircumstancesFactory.PersonalityCards.LordDeWorde.getName())).isWinner();
        assertTrue(isLordSelRusWorWinner);

    }


    @Test
    public void testLordVetinari() throws JSONException {

        String lordVetinari = WinningCircumstancesFactory.PersonalityCards.LordVetinari.getName();

        boolean isLordVetinariWinner = WinningCircumstancesFactory.getWinningCircumstance(lordVetinari).isWinner();
        assertFalse(isLordVetinariWinner);

        try {
            FileManager.loadFile("lordVetinari.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        isLordVetinariWinner = WinningCircumstancesFactory.getWinningCircumstance(String.valueOf(WinningCircumstancesFactory.PersonalityCards.LordVetinari.getName())).isWinner();
        assertTrue(isLordVetinariWinner);

    }

    @Test
    public void testChrysoprase() throws JSONException {

        String chrysoprase = WinningCircumstancesFactory.PersonalityCards.Chrysoprase.getName();

        boolean isChrysopraseWinner = WinningCircumstancesFactory.getWinningCircumstance(chrysoprase).isWinner();
        assertFalse(isChrysopraseWinner);

        try {
            FileManager.loadFile("chrysoprase.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        isChrysopraseWinner = WinningCircumstancesFactory.getWinningCircumstance(String.valueOf(WinningCircumstancesFactory.PersonalityCards.Chrysoprase.getName())).isWinner();
        assertTrue(isChrysopraseWinner);

    }


}
