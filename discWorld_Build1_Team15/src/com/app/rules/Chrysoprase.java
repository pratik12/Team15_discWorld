package com.app.rules;

import com.app.Area;
import com.app.BoardGame;
import com.app.Player;
import com.app.rules.WinningCircumstancesFactory.PersonalityCards;

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
            if (player.getWinningCondition().equalsIgnoreCase(PersonalityCards.Chrysoprase.getName())) {
                currentPlayer = player;
            }
        }
        if (currentPlayer != null && currentPlayer.getPlayerAreas() != null && !currentPlayer.getPlayerAreas().isEmpty()) {
            for (Area area : currentPlayer.getPlayerAreas()) {
                if(area.getDemons() == 0)
            	totalProperty += area.getCostOfArea();
            }
            if(currentPlayer.getPlayerLoan()!=0){
            	int count = currentPlayer.getPlayerLoan() / 10;
            	while(count!=0){
            		if (currentPlayer.getPlayerAmount() > 10 && currentPlayer.getPlayerAmount() >= 12)
            			currentPlayer.setPlayerAmount(currentPlayer.getPlayerAmount() - 12);
            	}
            }
            totalProperty += currentPlayer.getPlayerAmount();
        }
        return totalProperty >= 50;
    }
}
