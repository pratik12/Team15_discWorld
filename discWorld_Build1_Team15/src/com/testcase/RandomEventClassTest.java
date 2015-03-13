package com.testcase;

import com.app.rules.RandomEventCard;

import org.json.JSONException;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 3/11/15
 * Time: 9:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class RandomEventClassTest {

    @Test(expected = NullPointerException.class)
    public void testRandomEventCards() throws JSONException {
        assertNull(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Flood, null));
        assertNull(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Fire, null));
        assertNull(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Riots, null));
        assertNull(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Explosion, null));
        assertNull(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.DemonsFromTheDungeonDimensions, null));
        assertNull(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Subsidence, null));
        assertNull(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Trolls, null));
        assertNull(RandomEventCard.GLOBALOBJ.doTheTasks(null, RandomEventCard.Earthquake, null));
    }

    
}
