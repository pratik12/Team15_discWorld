package com.app.rules;

import com.app.Area;
import com.app.BoardGame;
import com.app.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/24/15
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class LordSelRusWor implements WinningCircumstances {

    @Override
    public Boolean isWinner() {
        int numberOfPlayers = BoardGame.playersInGame.size();
        Player currentPlayer = null;
        for (Player player : BoardGame.playersInGame) {
            if (player.getWinningCondition().equals("")) {
                currentPlayer = player;
            }
        }
        if (currentPlayer != null && currentPlayer.getPlayerAreas() != null && !currentPlayer.getPlayerAreas().isEmpty()) {
            if (numberOfPlayers == 2) {
                checkControlledAreas(currentPlayer, 7);
            } else if (numberOfPlayers == 3) {
                checkControlledAreas(currentPlayer, 5);
            } else if (numberOfPlayers == 4) {
                checkControlledAreas(currentPlayer, 4);
            }
        }
        return true;
    }

    private Boolean checkControlledAreas(Player currentPlayer, int numberOfAreaTocheck) {
        int currentTotalProperty = 0;
        int totalPieces = 0;
        int controlledAreCounter = 0;
        int countedCurrentArealength = 0;

        //todo: page 8 from 8 Demons from the Dungeon Dimensions
        if (Boolean.TRUE) {
            return Boolean.FALSE;

        } else {
            while (controlledAreCounter < numberOfAreaTocheck && countedCurrentArealength <= currentPlayer.getPlayerAreas().size()) {

                ArrayList<Area> areas = currentPlayer.getPlayerAreas();
                for (Player p : areas.get(countedCurrentArealength).getPlayersInThisAreas()) {

                    if (!(p.getPlayerColor().equalsIgnoreCase(currentPlayer.getPlayerColor()))) {
                        currentTotalProperty += p.getNumberOfBuildings() + p.getMinionQuantity();
                        totalPieces += currentPlayer.getNumberOfBuildings() + currentPlayer.getMinionQuantity();
                    } else {
                        controlledAreCounter++;
                    }

                    if (totalPieces > currentTotalProperty)
                        controlledAreCounter++;
                    countedCurrentArealength++;
                }

                return controlledAreCounter >= numberOfAreaTocheck;
            }
        }
        return Boolean.TRUE;
    }
}
