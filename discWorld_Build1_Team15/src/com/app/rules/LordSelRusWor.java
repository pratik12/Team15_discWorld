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
            if (player.getWinningCondition().equals(WinningCircumstancesFactory.PersonalityCards.get(1)) || player.getWinningCondition().equals(WinningCircumstancesFactory.PersonalityCards.get(3)) ||
                    player.getWinningCondition().equals(WinningCircumstancesFactory.PersonalityCards.get(7))) {
                currentPlayer = player;
                break;
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
        return Boolean.FALSE;
    }

    private Boolean checkControlledAreas(Player currentPlayer, int numberOfAreaTocheck) {
        int currentTotalProperty = 0;
        int totalPieces = 0;
        int controlledAreaCounter = 0;
        int countedCurrentAreaLength = 0;

        while (controlledAreaCounter < numberOfAreaTocheck && countedCurrentAreaLength <= currentPlayer.getPlayerAreas().size()) {

            ArrayList<Area> areas = currentPlayer.getPlayerAreas();
            if (!areas.isEmpty())
                for (Player p : areas.get(countedCurrentAreaLength).getPlayersInThisAreas()) {
                    currentTotalProperty = 0;
                    totalPieces = 0;
                    if (!(p.getPlayerColor().equalsIgnoreCase(currentPlayer.getPlayerColor()))) {
                        currentTotalProperty += p.getNumberOfBuildings() + p.getMinionQuantity();
                        totalPieces += currentPlayer.getNumberOfBuildings() + currentPlayer.getMinionQuantity();
                    } else {
                        controlledAreaCounter++;
                    }

                    if (totalPieces > currentTotalProperty && totalPieces > areas.get(countedCurrentAreaLength).getTrolls() && areas.get(countedCurrentAreaLength).getDemons() == 0)
                        controlledAreaCounter++;
                    countedCurrentAreaLength++;
                }
            else
                return Boolean.FALSE;
        }
        return controlledAreaCounter >= numberOfAreaTocheck;

    }
}
