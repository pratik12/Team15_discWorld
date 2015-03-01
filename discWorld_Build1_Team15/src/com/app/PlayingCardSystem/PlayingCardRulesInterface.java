package com.app.PlayingCardSystem;

import com.app.Player;

public interface PlayingCardRulesInterface {

	public void performTasks(Player player);
	
	public String askSymbolsInOrder(GreenPlayerCardEnum tempEnum, String result);
	public boolean moveMinion();
	public boolean assasinate(String pieceToRemove);
	public boolean playAnotherCard();
	public boolean interrupt();
	
	public void takeMoney(int amt, Player currentPlayingPlayer);
	public void takePlayingCards(Player currentPlayingPlayer);
	public Player selectPlayer(Player currentPlayer,String playerToSelect);
}
