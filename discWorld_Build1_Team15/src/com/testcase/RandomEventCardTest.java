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

//    @Test
//    public void testFlood() {
//        try {
//            FileManager.loadFile("flood.txt");
//        } catch (JSONException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//        Player currentPlayer = BoardGame.playersInGame.get(3);
//        try {
//            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.Flood, GreenPlayerCardEnum.RINCEWIND));
//        } catch (JSONException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//    }


    @Test
    public void testFire() {
        try {
            FileManager.loadFile("fire.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Player currentPlayer = BoardGame.playersInGame.get(3);
        try {
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.Fire, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testDemonsFromTheDungeonDimensions() {
        try {
            FileManager.loadFile("demonsFromTheDungeonDimensions.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Player currentPlayer = BoardGame.playersInGame.get(3);
        try {
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.DemonsFromTheDungeonDimensions, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    @Test
    public void testBloodyStupidJohnson() {
        try {
            FileManager.loadFile("bloodyStupidJohnson.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Player currentPlayer = BoardGame.playersInGame.get(3);
        try {
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.BloodyStupidJohnson, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testTrolls() {
        try {
            FileManager.loadFile("trolls.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Player currentPlayer = BoardGame.playersInGame.get(3);
        try {
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.Trolls, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

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


    @Test
    public void testFog() {

        try {
            FileManager.loadFile("fog.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        Player currentPlayer = BoardGame.playersInGame.get(3);
        try {
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.Fog, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testEarthquake() {
        try {
            FileManager.loadFile("earthquake.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Player currentPlayer = BoardGame.playersInGame.get(3);
        try {
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.Earthquake, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testRiots() {
        try {
            FileManager.loadFile("riots.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Player currentPlayer = BoardGame.playersInGame.get(3);
        try {
            //Less troublemarkers on board , the random event cannot happen
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.Riots, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void testRiotsToEndTheGame() {

        try {
            FileManager.loadFile("riotsToEndTheGame.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Player currentPlayer = BoardGame.playersInGame.get(3);

        try {
            // With 8 trouble marker ends the game
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.Riots, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Test
    public void testExplosion() {
        try {
            FileManager.loadFile("explosion.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Player currentPlayer = BoardGame.playersInGame.get(3);
        try {
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.Explosion, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


//    @Test
//    public void testMysteriousMurdor() {
//
//        try {
//            FileManager.loadFile("mysteriousMurder.txt");
//        } catch (JSONException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//
//        Player currentPlayer = BoardGame.playersInGame.get(3);
//        try {
//            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.MysteriousMurders, GreenPlayerCardEnum.RINCEWIND));
//        } catch (JSONException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//    }

    @Test
    public void testSubsidence() {
        try {
            FileManager.loadFile("subsidence.txt");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Player currentPlayer = BoardGame.playersInGame.get(3);
        try {
            assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayer, RandomEventCard.Subsidence, GreenPlayerCardEnum.RINCEWIND));
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
