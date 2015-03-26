package com.app.rules;

import com.app.Area;
import com.app.BoardGame;
import com.app.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/24/15
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Chrysoprase implements WinningCircumstances {

    @Override
    public Boolean isWinner() {
        Player currentPlayer = null;
        int totalProperty = 0;
        for (Player player : BoardGame.playersInGame) {
            if (player.getWinningCondition().equals(WinningCircumstancesFactory.PersonalityCards.get(5))) {
                currentPlayer = player;
                break;
            }
        }
        if (currentPlayer != null && currentPlayer.getPlayerAreas() != null && !currentPlayer.getPlayerAreas().isEmpty()) {
            for (Area area : currentPlayer.getPlayerAreas()) {
                totalProperty += area.getCostOfArea();
            }
            totalProperty += currentPlayer.getPlayerAmount();
            totalProperty -= 12 * currentPlayer.getPlayerLoan();
        }
        return totalProperty >= 50;
    }
}
