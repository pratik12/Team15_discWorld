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
	
	public String askSymbolsInOrder(GreenPlayerCardEnum tempEnum, String result, Player p) throws JSONException;
	public void moveMinion();
	public void assasinate(Player ps) throws JSONException;
	public void removeTroubleMarker();
	public void playAnotherCard(Player currentPlayingPlayer, GreenPlayerCardEnum enumTemp) throws JSONException;
	public void interrupt();
	
	public boolean takeMoneyFromPlayer(int amt, Player currentPlayingPlayer, Player fromPlayer);
	public void takeLoanFromBank(int amt, Player currentPlayer);
	public void takeMoneyFromBank(int amt, Player currentPlayer);
	public void payMoneyToBank(int amt, Player currentPlayer);
	public void removeMinion(int num, Player currentPlayer) throws JSONException;
	public boolean removeMinionOFAnotherPlayer(int num, Player currentPlayer, Player fromPlayer) throws JSONException;
	public void removeMinionOFYourOwn(int num, Player currentPlayer) throws JSONException;
	//moveminion of OTHER player from an area to adjacent area
	public void moveMinionToOtherArea(Player currentPlayer, Player fromPlayer, String fromLocation, String toLocation) throws JSONException;
	// remove minion from an area

	public void fillYourHandWIthPlayerCard(int i, Player ps);
	public void takePlayingCards(Player currrentPlayer, int number, boolean b);
	public void playingCardsAction(Player currentPlayer, Player fromPlayer, int count);
	public void givePlayingCards(Player currrentPlayer , int number);
	public Player selectPlayer(Player currentPlayer,String playerToSelect);
	public int rollDie();
	public void addToDiscardPile(int num, GreenPlayerCardEnum gc,Player p, boolean addCard);
	
	public void getMoneyForMinionsinArea(int amt, Player currentplayer, String areaName);
	public void drawCardsFromDiscardPile(int num, Player player);
	public void removeBuilding(Player currentPlayer, Player fromPlayer);
	public void drawCardsFromDeck(int num , Player currentPlayer);
	
	public void placeTroubleMarker(String areaLocation) throws JSONException;
	public void ignoreRandomEvent(Player currentPlayer);
	public void removeoneTroubleMarker(Player currentPlayer);
	public void discardCard(Player currentPlayer);
	public void placeoneMinion(Player currentPlayer,String areaLocation) throws JSONException;
	public void placeMinionActionPlayerCard(Player currentPlayingPlayer) throws JSONException;
	public void placeMinionActionPlayerCard(Player currentPlayingPlayer,String s)throws JSONException;
	public String discardCardsPerYourWish(Player currentPlayingPlayer, GreenPlayerCardEnum gc, int amt);
	public void takeMoneyExchangeCardsFromAnotherPlayer(Player currentPlayingPlayer,
			int amt);
	
	public void addBuildingAction(Player p);
	
}
