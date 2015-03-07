package com.app.CityAreaCardSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONException;

import com.app.Player;
import com.app.Area;
import com.app.BoardGame;
import com.app.PlayerCard;
import com.app.PlayingCardSystem.*;

/**
 * CityAreaCardEnum
 * @author Sanchit Mehta
 *
 * @see
 * CityAreaCards representing an Area owned by a player
 */

@SuppressWarnings("unused")
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
	}, 
	
	
	// discard one card and take $2 from bank
	THESCOURS("THESCOURS")	
	{
		@Override
		public void performTasks(Player currentPlayer)
		{
			System.out.println("Player discards one card..");
			this.discardCard(currentPlayer);
			//this.addToDiscardPile(gc);
			//discardOneCard(currentPlayer);
			
			
			System.out.println("Taking $2 from bank.."); 
			this.takeLoanFromBank(+2,currentPlayer); 
			 
		}
	},  					
	
	//whenever piece affected by random event,can pay $3 to ignore it 
	SMALLGODS("SMALLGODS")
	{
		@Override
		public void performTasks(Player currentPlayer)
		{
			
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
		
		}
	}, 
	
	
	//take one card from deck and then discard one
	UNREALESTATE("UNREALESTATE")
	{
		@Override
		public void performTasks(Player currentPlayer,int num) 
		{
			System.out.println("Player draws one card..");
			this.drawCardsFromDeck(num, currentPlayer);
			
			System.out.println("Player discards one card..");
			this.discardCard(currentPlayer);
		}
	}, 
	
	
	//pay $3 to bank and place one minion in Dolly Sisters or adjacent area	
	DOLLYSISTERS("DOLLYSISTERS")
	{

		@Override
		public void performTasks(Player currentPlayer,int num , String areaLocation) 
		{
			System.out.println("Paying $3 to bank.."); 
			this.takeLoanFromBank(-3,currentPlayer); 
		
			System.out.println("Placing a minion..");
			placeoneMinion(currentPlayer, num, "Dolly Sisters");
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
		
		}
	},
	
	
	//pay $2 to bank and remove one troublemarker on boards
	ISLEOFGODS("ISLEOFGODS")	
	{
		@Override
		public void performTasks(Player currentPlayer, Area maparea) 
		{
			
			System.out.println("Paying $2 to bank..");  
			this.takeLoanFromBank(-2,currentPlayer); 
			
			System.out.println("Removing troublemarker from board..");
			this.removeoneTroubleMarker(currentPlayer);
		
			 	
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
		
		}
	}, 
	
	
	//pay $3 to bank and place the minions in Dimwell or adjacent area
	DIMWELL("DIMWELL")
	{
		@Override
		public void performTasks(Player currentPlayer,int num,String areaLocation) 
		{
			System.out.println("Paying $3 to bank.."); 
			this.takeLoanFromBank(-3,currentPlayer); 
		
			System.out.println("Placing a minion..");
			placeoneMinion(currentPlayer, num, "Dimwell");
		}
	}, 
	
	
	//place one troublemarker in the Shades or adjacent area
	THESHADES("THESHADES")
	{
		public void performTasks(Player currentPlayer,String location) throws JSONException
		{
			System.out.println("Placing troublemarker..");
			this.placeTroubleMarker(currentPlayer,"The Shades");
			
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
	
	public void performTasks(Player currentPlayer, int num, String areaLocation) {
		// TODO Auto-generated method stub
		
	}

	public void performTasks(Player currentPlayer, int num) {
		// TODO Auto-generated method stub
		
	}

	public void performTasks(Player currentPlayer, Area maparea) {
		// TODO Auto-generated method stub
		
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
			
		if((!(currentPlayer.equals(null))))
		{
			for(Area maparea : currentPlayer.getPlayerAreas())
			{
				if(areaLocation.trim().equalsIgnoreCase(maparea.getAreaName()))
				{
					if(!(maparea.isTroubleMarkers()))
					{
						//maparea.getTroubleMarkerArea();
						maparea.setTroubleMarkers(true);
					}
					maparea.setTroubleMarkerArea(maparea.getTroubleMarkerArea());
					break;
				}
					
			
			else
			{
			
			String result = BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.getInstance().areaDetails, areaLocation);
			String[] temp = result.split(":");
			System.out.println("Adjacent areas ");
			for(int i=0; i<temp.length; i++)
			{
				for(Area a: BoardGame.board_areas)
				{
					if(a.getAreaNumber()==Integer.parseInt(temp[i]))
					{
						System.out.print(a.getAreaName()+", ");
					}
				}
				
			}
			
			String res = questionsToAsk("Enter area adjacent  to place the troublemarker");
			if(!res.equals(null))
			{
				//placing the troublemarker in adjacent area
			}
			
				else
				{
					System.out.println("Something wrong..");
					break;
				}
			}
		  }
		}
	}
		
	
	@Override
	public void ignoreRandomEvent(Player currentPlayer)
	{
		
	}



	@Override
	public String askSymbolsInOrder(GreenPlayerCardEnum tempEnum, String result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean moveMinion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean assasinate(String pieceToRemove) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playAnotherCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean interrupt() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void takeMoneyFromBank(int amt, Player currentPlayer)
	 {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takePlayingCards(Player currrentPlayer, Player fromPlayer, int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player selectPlayer(Player currentPlayer, String playerToSelect) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public boolean takeMoneyFromPlayer(int amt, Player currentPlayingPlayer,
			Player fromPlayer) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMinion(int num, Player currentPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMinionOFAnotherPlayer(int num, Player currentPlayer,
			Player fromPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMinionOFYourOwn(int num, Player currentPlayer) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void moveMinionToOtherArea(Player currentPlayer, Player fromPlayer,
			String toLocation) throws JSONException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int rollDie() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	
		public void addToDiscardPile(GreenPlayerCardEnum gc) {
			
			if(gc instanceof GreenPlayerCardEnum)
				BoardGame.setDiscardPilePlayerCards(gc);
			else
				System.out.println("Unsupported playercard. Verify the type of cardbeing passed");
		}
		
	
	@Override
	public void getMoneyForMinionsinArea(int amt, Player currentplayer,
			String areaName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBuilding(Player currentPlayer, Player fromPlayer) {
		// TODO Auto-generated method stub
		
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
		ArrayList<PlayerCard> playcard = null ;
		int i=0;
		while(it.hasNext())
		{
			if (currentPlayer.getPlayersPlayingCard().get(i).equals(playercardname))
			{
				currentPlayer.resetPlayersPlayingCard(i);
			}
			else
			{
				System.out.println("Wrong Input..");
			}
			i++;
		}
		//addToDiscardPile(gc);
		BoardGame.getDiscardPilePlayerCards();
		BoardGame.setDiscardPilePlayerCards(GreenPlayerCardEnum.valueOf(playercardname));
		
	}

		
	@Override
	public void drawCardsFromDeck(int num , Player currentPlayer) {
		
		/*while(num!=0){
			Random rand = new Random();
			int n = rand.nextInt(BoardGame.player_cards.size()-1);
			if(BoardGame.player_cards.get(n) instanceof GreenPlayerCardEnum)
				currentPlayer.getPlayersPlayingCard().add(BoardGame.player_cards.get(n));
			else
				System.out.println("Not sufficient player cards are remaining in the deck");
		}
	*/	
	}
	
	@Override
	public void placeoneMinion(Player currentPlayer,int num,String areaLocation)
	{
		if((!(currentPlayer.equals(null))))
		{
			for(Area maparea : currentPlayer.getPlayerAreas())
			{
				if(areaLocation.trim().equalsIgnoreCase(maparea.getAreaName()))
				{
					while(num!=0)
					{	
						for(ArrayList<String> str : currentPlayer.getMinions().values())
						{
							for(String temp : str)
							{
								if(areaLocation.equalsIgnoreCase(temp))
								{	
									currentPlayer.getMinions().values().add(str);
									currentPlayer.setMinionQuantity(currentPlayer.getMinionQuantity()-1);
									break;
								}
							}
							break;
						}	
							num--;
		
					}
					
				}
				
			}
		
		}	
		
	}
}


