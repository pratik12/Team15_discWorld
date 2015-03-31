package com.app.rules;

import com.app.Area;
import com.app.BoardGame;
import com.app.Player;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.rules.WinningCircumstancesFactory.PersonalityCards;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/24/15
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class LordVetinari implements WinningCircumstances {

    @Override
    public Boolean isWinner() {
        int numberOfPlayers = BoardGame.playersInGame.size();
        Player currentPlayer = null;
        for (Player player : BoardGame.playersInGame) {
            if (player.getWinningCondition().equals(PersonalityCards.LordVetinari.getName())) {
                currentPlayer = player;
                break;
            }
        }
            if (numberOfPlayers == 2 && calculateMinions(currentPlayer) >= 11)
                return Boolean.TRUE;
            else if (numberOfPlayers == 3 && calculateMinions(currentPlayer) >= 10)
                return Boolean.TRUE;
            else if (numberOfPlayers == 4 && calculateMinions(currentPlayer) >= 9)
                return Boolean.TRUE;
            else
                return Boolean.FALSE;
    }

    private int calculateMinions(Player currentPlayer){
        
    	int numberOfArea = 0;
    	for(Area a : BoardGame.board_areas){
    		if(currentPlayer.doYouHaveMinionInThisArea(currentPlayer, a.getAreaName()) && a.getDemons()==0){
    			numberOfArea++;
    		}
    	}
    	
        /*for (Area area : currentPlayer.getPlayerAreas()) {
           if (area.getDemons()== 0){
               numberOfArea++;
           }
        }*/

        return numberOfArea;
    }
}
