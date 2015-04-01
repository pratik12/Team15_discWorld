package com.testcase;

import com.app.BoardGame;
import com.app.FileManager;
import com.app.Player;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.rules.RandomEventCard;
import org.json.JSONException;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 3/11/15
 * Time: 9:04 PM
 * To change this template use File | Settings | File Templates.
 */

public class RandomEventCardTest {

    @Test
    public void testTheDragon() {
        try {
            FileManager.loadFile("theDragon.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        Player currentPlayer = BoardGame.playersInGame.get(3);
        try {
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.TheDragon, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
     /*
    @Test
    public void testFlood() {
        try {
            FileManager.loadFile("C:\\fakeFile.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Player currentPlayer = new Player("");
        try {
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.Flood, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testFire() {
        assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Fire, null));
    }

    @Test
    public void testRiots() {
        assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Riots, null));
    }

    @Test
    public void testExplosion() {
        assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Explosion, null));
    }

    @Test
    public void testMysteriousDemonsFromTheDungeonDimensions() {
        assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.DemonsFromTheDungeonDimensions, null));
    }

    @Test
    public void testSubsidence() {
        assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Subsidence, null));
    }

    @Test
    public void testTrolls() {
        assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Trolls, null));
    }


    @Test
    public void testEarthquake() {
        assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Earthquake, null));
    }
    */

}
