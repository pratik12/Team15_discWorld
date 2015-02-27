package com.app.rules;

import com.app.Area;
import com.app.BoardGame;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/24/15
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class DragonKingOfArms implements WinningCircumstances {

    @Override
    public Boolean isWinner() {
        int numberOfTroubleMarkers = 0;
        for (Area area : BoardGame.board_areas) {
            if (area.isTroubleMarkers())
                numberOfTroubleMarkers++;

        }
        if (numberOfTroubleMarkers == 8)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

}
