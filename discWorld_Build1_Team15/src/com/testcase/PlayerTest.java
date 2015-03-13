package com.testcase;

import com.app.Area;
import com.app.Player;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/3/15
 * Time: 8:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerTest {
	
    @Test(expected=NullPointerException.class)
    public void checkTroubleMarkerValidity() {
        Player playerObject = new Player("K");
        boolean thrown = false;
        assertEquals(Area.class, playerObject.getAreaInstanceFromAreaName("Dolly Sisters"));
        assertEquals(null, playerObject.getAreaInstanceFromAreaName(" "));
        assertNotNull(playerObject);
        try{
        	playerObject.setPlayerAreas(null);
        }
        catch(NullPointerException e){
        	thrown = true;
        }
        assertTrue(thrown);
    }


}
