package com.app.CityAreaCardSystem;

import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONException;

import com.app.Player;
import com.app.Area;
import com.app.BoardGame;
import com.app.PlayingCardSystem.*;

/**
 * CityAreaCardEnum
 * @author Sanchit Mehta
 *
 * @see
 * CityAreaCards representing an Area owned by a player
 */


public enum CityAreaCardEnum implements PlayingCardRulesInterface{

	// All the 12 areas represented on the map
	
	
	//take $2 from bank
	THEHIPPO("THEHIPPO")	
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			System.out.println("Taking $2 from bank.."); 
			this.takeLoanFromBank(+2,currentPlayer); 
		
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}

			}, 
	
	
	// discard one card and take $2 from bank
	THESCOURS("THESCOURS")	
	{
		@Override
		public void performTasks(Player currentPlayer)
		{
			System.out.println("Player discards one card..");
			this.discardCard(currentPlayer);
			
			System.out.println("Taking $2 from bank.."); 
			this.takeLoanFromBank(+2,currentPlayer); 
			 
		}

		@Override
		public void assasinate(Player ps) {
			  
			
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			  
			
		}

		@Override
		public void playAnotherCard(Player currentPlayingPlayer,
				GreenPlayerCardEnum enumTemp) throws JSONException {
			  
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			  
			
		}

		@Override
		public void takePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			  
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}
	},  					
	
	//whenever piece affected by random event,can pay $3 to ignore it 
	SMALLGODS("SMALLGODS")
	{
		@Override
		public void performTasks(Player currentPlayer)
		{
			
		}

		@Override
		public void assasinate(Player ps) {
			  
			
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			  
			
		}

		@Override
		public void playAnotherCard(Player currentPlayingPlayer,
				GreenPlayerCardEnum enumTemp) throws JSONException {
			  
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			  
			
		}

		@Override
		public void takePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			  
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}
	}, 
	
	
	//take $2 from bank
	DRAGONSLANDING("DRAGONSLANDING")	
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			System.out.println("Taking $2 from bank.."); 
			 this.takeLoanFromBank(+2,currentPlayer); 
		//GreenPlayerCardEnum.TEST.takeMoneyFromBank(2, currentPlayer);
		}

		@Override
		public void assasinate(Player ps) {
			  
			
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			  
			
		}

		@Override
		public void playAnotherCard(Player currentPlayingPlayer,
				GreenPlayerCardEnum enumTemp) throws JSONException {
			  
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			  
			
		}

		@Override
		public void takePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			  
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}
	}, 
	
	
	//take one card from deck and then discard one
	UNREALESTATE("UNREALESTATE")
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			System.out.println("Player draws one card..");
			GreenPlayerCardEnum.TEST.drawCardsFromDeck(1, currentPlayer);
			
			System.out.println("Player discards one card..");
			this.discardCard(currentPlayer);
		}

		@Override
		public void assasinate(Player ps) {
			  
			
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			  
			
		}

		@Override
		public void playAnotherCard(Player currentPlayingPlayer,
				GreenPlayerCardEnum enumTemp) throws JSONException {
			  
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			  
			
		}

		@Override
		public void takePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			  
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}
	}, 
	
	
	//pay $3 to bank and place one minion in Dolly Sisters or adjacent area	
	DOLLYSISTERS("DOLLYSISTERS")
	{

		@Override
		public void performTasks(Player currentPlayer ) throws JSONException 
		{
			System.out.println("Paying $3 to bank.."); 
			this.takeLoanFromBank(-3,currentPlayer); 
			//GreenPlayerCardEnum.TEST.payMoneyToBank(3, currentPlayer);
			
			placeoneMinion(currentPlayer, "Dolly Sisters");
		}

		@Override
		public void assasinate(Player ps) {
			  
			
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			  
			
		}

		@Override
		public void playAnotherCard(Player currentPlayingPlayer,
				GreenPlayerCardEnum enumTemp) throws JSONException {
			  
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			  
			
		}

		@Override
		public void takePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			  
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}
	}, 
	
	
	//take $1 from bank
	NAPHILL("NAPHILL")	
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			System.out.println("Taking $1 from bank..");  
			this.takeLoanFromBank(+1,currentPlayer); 
		//	 GreenPlayerCardEnum.TEST.takeMoneyFromBank(1, currentPlayer);
		}

		@Override
		public void assasinate(Player ps) {
			  
			
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			  
			
		}

		@Override
		public void playAnotherCard(Player currentPlayingPlayer,
				GreenPlayerCardEnum enumTemp) throws JSONException {
			  
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			  
			
		}

		@Override
		public void takePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			  
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}
	}, 
	
	
	//take $3 from bank
	SEVENSLEEPERS("SEVENSLEEPERS")	
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			 System.out.println("Taking $3 from bank.."); 
			 this.takeLoanFromBank(+3,currentPlayer); 
//			 GreenPlayerCardEnum.TEST.takeMoneyFromBank(3, currentPlayer);
		}

		@Override
		public void assasinate(Player ps) {
			  
			
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			  
			
		}

		@Override
		public void playAnotherCard(Player currentPlayingPlayer,
				GreenPlayerCardEnum enumTemp) throws JSONException {
			  
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			  
			
		}

		@Override
		public void takePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			  
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}
	},
	
	
	//pay $2 to bank and remove one troublemarker on boards
	ISLEOFGODS("ISLEOFGODS")	
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			
			System.out.println("Paying $2 to bank..");  
			this.takeLoanFromBank(-2,currentPlayer); 
		//	GreenPlayerCardEnum.TEST.payMoneyToBank(2, currentPlayer);
			
			System.out.println("Removing troublemarker from board..");
			// show to user where trouble markers are
			String areaName = questionsToAsk("Enter area name to remove trouble marker from:nul");
			GreenPlayerCardEnum.TEST.removeTroubleMarker(areaName);
		}

		@Override
		public void assasinate(Player ps) {
			  
			
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			  
			
		}

		@Override
		public void playAnotherCard(Player currentPlayingPlayer,
				GreenPlayerCardEnum enumTemp) throws JSONException {
			  
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			  
			
		}

		@Override
		public void takePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			  
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}
	}, 
	
	
	//take $1 from bank
	LONGWELL("LONGWALL")	
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			System.out.println("Taking $1 from bank.."); 
			this.takeLoanFromBank(+1,currentPlayer); 
		//	GreenPlayerCardEnum.TEST.takeMoneyFromBank(1, currentPlayer);
		}

		@Override
		public void assasinate(Player ps) {
			  
			
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			  
			
		}

		@Override
		public void playAnotherCard(Player currentPlayingPlayer,
				GreenPlayerCardEnum enumTemp) throws JSONException {
			  
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			  
			
		}

		@Override
		public void takePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			  
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}
	}, 
	
	
	//pay $3 to bank and place the minions in Dimwell or adjacent area
	DIMWELL("DIMWELL")
	{
		@Override
		public void performTasks(Player currentPlayer) throws JSONException 
		{
			System.out.println("Paying $3 to bank.."); 
			this.takeLoanFromBank(-3,currentPlayer); 
	//	GreenPlayerCardEnum.TEST.payMoneyToBank(3, currentPlayer);
			placeoneMinion(currentPlayer, "Dimwell");
		}

		@Override
		public void assasinate(Player ps) {
			  
			
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			  
			
		}

		@Override
		public void playAnotherCard(Player currentPlayingPlayer,
				GreenPlayerCardEnum enumTemp) throws JSONException {
			  
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			  
			
		}

		@Override
		public void takePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			  
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}
	}, 
	
	
	//place one troublemarker in the Shades or adjacent area
	THESHADES("THESHADES")
	{
		public void performTasks(Player currentPlayer) throws JSONException
		{
			
			this.placeTroubleMarker(currentPlayer,"The Shades");
			
		}

		@Override
		public void assasinate(Player ps) {
			  
			
		}

		@Override
		public void removeTroubleMarker(String areaName) {
			  
			
		}

		@Override
		public void playAnotherCard(Player currentPlayingPlayer,
				GreenPlayerCardEnum enumTemp) throws JSONException {
			  
			
		}

		@Override
		public void takeMoneyFromBank(Player currPlayer, int amount) {
			  
			
		}

		@Override
		public void takePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
			  
			
		}

		@Override
		public void givePlayingCards(Player currrentPlayer, int number) {
			  
			
		}

		

		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p) {
			// TODO Auto-generated method stub
			
		}
	}; 
	
	/** areaName that identifies an area*/
	private String areaName;
	private static int bankAmt;
	private static int currentPlayerAmt;
	
	
	private CityAreaCardEnum(String area)
	{
			setareaName(areaName);
			
	}
	


	public String getareaName()
	{
		return areaName;
	}
	
	public void setareaName(String areaName)
	{
		this.areaName=areaName;
	}
	
	@Override
	public void performTasks(Player currentPlayingPlayer) throws JSONException {}

	/*
	@Override
	public void discardOneCard(Player currentPlayer)
	{
	  
		ArrayList<com.app.PlayingCardSystem.PlayerCard> p = currentPlayer.getPlayersPlayingCard();
		Iterator<com.app.PlayingCardSystem.PlayerCard> it = p.iterator();
		int i=0,num=0;
		while(it.hasNext())
		{
			System.out.println("Available options");
			System.out.println(p.get(i).getNumber());
			i++;
		
		}
		System.out.println("Select option form above displayed numbers:");
		@SuppressWarnings("resource")
		Scanner scr = new Scanner(System.in);
		int k = scr.nextInt();
		p.remove(k);
		i=0;
		while(it.hasNext())
		{
			System.out.println("Available options");
			System.out.println(p.get(i).getNumber());
			i++;
		
		}	
	}
	*/
	@Override
	public String questionsToAsk(String qns) {
		String result = null;
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String[] temp = qns.split(":"); 

		for(int i = 0 ; i < temp.length ; i++){
			if(!temp[i].equalsIgnoreCase("nul"))
			System.out.println(temp[i] + ", ");
		}
		result = in.nextLine();
		return result;
	}
	
	@Override
	public void placeTroubleMarker(Player currentPlayer , String areaLocation) throws JSONException
	{
		String res = questionsToAsk("WHere do you want to palce trouble marker. 1. "+areaLocation
				+"2. Adjacent Areas" +":nul");
		if(res.equalsIgnoreCase("1")){
			
			for(Area maparea : BoardGame.board_areas)
			{
				if(areaLocation.trim().equalsIgnoreCase(maparea.getAreaName()))
				{
						//maparea.getTroubleMarkerArea();
						maparea.setTroubleMarkers(true);
					maparea.setTroubleMarkerArea(maparea.getTroubleMarkerArea());
				}
				break;
			}		
		}
			else if(res.equalsIgnoreCase("2")){
			
			String result = BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.getInstance().areaDetails, areaLocation);
			
			BoardGame.displayAdjacentAreas(result);
			
			String res1 = questionsToAsk("Enter area adjacent to place the troublemarker");
			if(!res1.equals(null))
			{
				for(Area maparea : BoardGame.board_areas)
				{
					if(res1.trim().equalsIgnoreCase(maparea.getAreaName()))
					{
							//maparea.getTroubleMarkerArea();
							maparea.setTroubleMarkers(true);
						maparea.setTroubleMarkerArea(maparea.getTroubleMarkerArea());
					}
					break;
				}
			}else{
					System.out.println("Area name cannot be empty");
				}
			}
		  
}
	
		
	
	@Override
	public void ignoreRandomEvent(Player currentPlayer)
	{
		
	}



	@Override
	public String askSymbolsInOrder(GreenPlayerCardEnum tempEnum, String result) {
		  
		return null;
	}

	@Override
	public void moveMinion() {
		  
	}

	@Override
	public void assasinate(Player ps) {
		  
	}

	@Override
	public void playAnotherCard(Player currentPlayingPlayer, GreenPlayerCardEnum enumTemp) throws JSONException{
		  
	}

	@Override
	public void interrupt() {
		  
	}
	
	@Override
	public void takeMoneyFromBank(int amt, Player currentPlayer)
	 {
		  
		
	}

	@Override
	public void takePlayingCards(Player currrentPlayer, int number) {
		  
		
	}

	@Override
	public Player selectPlayer(Player currentPlayer, String playerToSelect) {
		  
		return null;
	}

	

	@Override
	public boolean takeMoneyFromPlayer(int amt, Player currentPlayingPlayer,
			Player fromPlayer) {
		  
		return false;
	}

	@Override
	public void takeLoanFromBank(int amt, Player currentPlayer) {

		if(amt!=0){
			currentPlayer.setPlayerAmount(currentPlayer.getPlayerAmount()+amt);
			BoardGame.setBank(BoardGame.getBank()-amt);
		}
		else{
			System.out.println("Entered amount cannot be 0");
		}
	}

	

	@Override
	public void payMoneyToBank(int amt, Player currentPlayer) {
		  
		
	}

	@Override
	public void removeMinion(int num, Player currentPlayer) {
		  
		
	}

	@Override
	public void removeMinionOFAnotherPlayer(int num, Player currentPlayer,
			Player fromPlayer) {
		  
		
	}

	@Override
	public void removeMinionOFYourOwn(int num, Player currentPlayer) {
		  
		
		
	}

	@Override
	public void moveMinionToOtherArea(Player currentPlayer, Player fromPlayer,
			String toLocation) throws JSONException {
		  
		
	}


	@Override
	public int rollDie() {
		  
		return 0;
	}

	
	@Override
	public void addToDiscardPile(int num,GreenPlayerCardEnum gc,Player p) {
			
			
		}
		
	
	@Override
	public void getMoneyForMinionsinArea(int amt, Player currentplayer,
			String areaName) {
		  
		
	}

	@Override
	public void removeBuilding(Player currentPlayer, Player fromPlayer) {
		  
		
	}

	@Override
	public void removeoneTroubleMarker(Player currentPlayer) {
		System.out.println("Removing a trouble marker from the board..");
		
			String arealocation = questionsToAsk("Enter area name to remove the troublemarker from : nul");
			
			for(Area maparea : currentPlayer.getPlayerAreas())
			{
				if(arealocation.trim().equalsIgnoreCase(maparea.getAreaName()))
				{
					if(maparea.isTroubleMarkers())
					{
						//maparea.getTroubleMarkerArea();
						maparea.setTroubleMarkers(false);
					}
					maparea.setTroubleMarkerArea(maparea.getTroubleMarkerArea());
					break;
				}
			break;	
			}
			
	}

	
	@Override
	public void discardCard(Player currentPlayer)
	{	
		System.out.println("Removing a player card..");
		String playercardname = questionsToAsk("Enter playercard name to discard");
		@SuppressWarnings("rawtypes")
		Iterator it = currentPlayer.getPlayersPlayingCard().iterator();
		GreenPlayerCardEnum temp =null;
		int i=0;
		while(it.hasNext())
		{
			if (currentPlayer.getPlayersPlayingCard().get(i).equals(playercardname))
			{
				temp = currentPlayer.getPlayersPlayingCard().get(i);
				currentPlayer.resetPlayersPlayingCard(i);
			}
			else
			{
				System.out.println("Wrong Input..");
			}
			i++;
		}
		addToDiscardPile(1,temp,currentPlayer);
		
	}

		
	@Override
	public void drawCardsFromDeck(int num , Player currentPlayer) {
	
	}
	
	@Override
	public void placeoneMinion(Player currentPlayer,String areaLocation) throws JSONException
	{
		String res = questionsToAsk("WHere do you want to palce minion. 1. " +areaLocation
				+"2. Adjacent Areas" +":nul");
		
		if(res.equalsIgnoreCase("1")){
				System.out.println("Placing a minion..");
				currentPlayer.placeMinion(areaLocation);
		}
		else{
			String temp = BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.getInstance().areaDetails, areaLocation);
			//
		}		
	}
}


