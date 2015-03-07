package com.app.rules;

import com.app.BoardGame;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/24/15
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommanderVimes implements WinningCircumstances {

    @Override
    public Boolean isWinner() {
        //you win if nobody else wins by the time the draw pile has been exhausted
        return BoardGame.player_cards.isEmpty();
    }
}
