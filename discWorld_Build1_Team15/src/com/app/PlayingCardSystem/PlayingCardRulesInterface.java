package com.app.PlayingCardSystem;

import org.json.JSONException;

import com.app.Player;

/**
 * This interface contains all the fumcitons need ot play a card
 * @author Pratik Bidkar
 *
 */
public interface PlayingCardRulesInterface {

	/**
	 * This should be overriden by every enum 
	 * @param player
	 * @throws JSONException 
	 */
	public void performTasks(Player player) throws JSONException;
	public String questionsToAsk(String qns);
	
	public String askSymbolsInOrder(GreenPlayerCardEnum tempEnum, String result);
	public boolean moveMinion();
	public boolean assasinate(String pieceToRemove);
	public boolean playAnotherCard();
	public boolean interrupt();
	
	public boolean takeMoneyFromPlayer(int amt, Player currentPlayingPlayer, Player fromPlayer);
	public void takeLoanFromBank(int amt, Player currentPlayer);
	public void takeMoneyFromBank(int amt, Player currentPlayer);
	public void payMoneyToBank(int amt, Player currentPlayer);
	public void removeMinion(int num, Player currentPlayer);
	public void removeMinionOFAnotherPlayer(int num, Player currentPlayer, Player fromPlayer);
	public void removeMinionOFYourOwn(int num, Player currentPlayer);
	//moveminion of OTHER player from an area to adjacent area
	public void moveMinionToOtherArea(Player currentPlayer, Player fromPlayer, String toLocation) throws JSONException;
	// remove minion from an area

	public void takePlayingCards(Player currrentPlayer, Player fromPlayer, int number);
	public Player selectPlayer(Player currentPlayer,String playerToSelect);
	public int rollDie();
	public void addToDiscardPile(GreenPlayerCardEnum gc);
	
	public void getMoneyForMinionsinArea(int amt, Player currentplayer, String areaName);
	
	public void removeBuilding(Player currentPlayer, Player fromPlayer);
	public void drawCardsFromDeck(int num , Player currentPlayer);
}
