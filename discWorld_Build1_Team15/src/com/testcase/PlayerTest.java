package com.testcase;

import com.app.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        Player playerObject = null;
        assertEquals(null, playerObject.checkForTroubleMarkers(" "));
    }


}
