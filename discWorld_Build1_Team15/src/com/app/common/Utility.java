package com.app.common;

import com.app.Area;
import com.app.BoardGame;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/26/15
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utility {


    public int rollDie() {
        Random rand = new Random();
        return rand.nextInt(12) + 1;
    }

    public int getRandNum(int num) {
        Random rand = new Random();
        return rand.nextInt(num) + 1;
    }

    public Area getAreaByNumber(int areaNumber) {
        for (Area area : BoardGame.board_areas)
            if (area.getAreaNumber() == areaNumber) {
                return area;
            }
        return null;
    }

    //todo: gameDescription page 6 of 8 last paragraph be completed and used in general controller class
    public Boolean isWinningConditionChecked() {
        return Boolean.FALSE;
    }

    public int calculateNumberOfTroubleMarkers() {
        int numberOfTroubleMarkers = 0;
        for (Area area : BoardGame.board_areas) {
            if (area.isTroubleMarkers())
                numberOfTroubleMarkers++;
        }
        return numberOfTroubleMarkers;
    }

    public Boolean giveTurnToleft(){
       return Boolean.TRUE;
    }


}
