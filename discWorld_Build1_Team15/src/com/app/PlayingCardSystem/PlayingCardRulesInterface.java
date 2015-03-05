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
	public void moveMinion();
	public void assasinate(String pieceToRemove);
	public void removeTroubleMarker(String areaName);
	public void playAnotherCard(Player currentPlayingPlayer, GreenPlayerCardEnum enumTemp) throws JSONException;
	public void takeMoneyFromBank(Player currPlayer,int amount);
	public void interrupt();
	
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

	public void takePlayingCards(Player currrentPlayer, int number);
	public void playingCardsAction(Player currentPlayer, Player fromPlayer, int count);
	public void givePlayingCards(Player currrentPlayer , int number);
	public Player selectPlayer(Player currentPlayer,String playerToSelect);
	public int rollDie();
	public void addToDiscardPile(int num, GreenPlayerCardEnum gc);
	
	public void getMoneyForMinionsinArea(int amt, Player currentplayer, String areaName);
	
	public void removeBuilding(Player currentPlayer, Player fromPlayer);
	public void drawCardsFromDeck(int num , Player currentPlayer);
}
