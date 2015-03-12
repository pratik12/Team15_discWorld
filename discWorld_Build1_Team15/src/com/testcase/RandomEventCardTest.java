package com.testcase;

import com.app.rules.RandomEventCard;
import org.junit.Test;

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
    public void testFlood() {
        assertTrue(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Flood, null));
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

}
