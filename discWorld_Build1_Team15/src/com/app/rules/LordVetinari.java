package com.app.rules;

import com.app.BoardGame;
import com.app.Player;

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
            if (player.getWinningCondition().equals("")) {
                currentPlayer = player;
            }
        }
        if (currentPlayer != null && currentPlayer.getPlayerAreas() != null && !currentPlayer.getPlayerAreas().isEmpty()) {
            if (numberOfPlayers == 2 && currentPlayer.getPlayerAreas().size() == 11)
                return Boolean.TRUE;
            else if (numberOfPlayers == 3 && currentPlayer.getPlayerAreas().size() == 10)
                return Boolean.TRUE;
            else if (numberOfPlayers == 4 && currentPlayer.getPlayerAreas().size() == 9)
                return Boolean.TRUE;
            else
                return Boolean.FALSE;
        } else
            return Boolean.FALSE;
    }
}
