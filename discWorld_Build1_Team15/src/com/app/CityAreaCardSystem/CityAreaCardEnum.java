package com.app.CityAreaCardSystem;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;

import com.app.InterruptCard;
import com.app.Player;
import com.app.Area;
import com.app.BoardGame;
import com.app.PlayerCardUtility;
import com.app.StartPlayingGame;
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
	GLOBAL("self","Action"){},
	
	//take $2 from bank
	THEHIPPO("THE HIPPO","1.Take $2 from bank")	
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			GreenPlayerCardEnum.GLOBALOBJ.takeMoneyFromBank(2,currentPlayer);
		}
	}, 
	
	
	// discard one card and take $2 from bank
	THESCOURS("THE SCOURS","1.Discard one card and take 2$ from bank.")	
	{
		@Override
		public void performTasks(Player currentPlayer)
		{
			
			discardACard(currentPlayer,this);
			System.out.println("Player card discarded..");
			GreenPlayerCardEnum.GLOBALOBJ.takeMoneyFromBank(2,currentPlayer);
			 
		}

	},  					
	
	//whenever piece affected by random event,can pay $3 to ignore it 
	SMALLGODS("SMALL GODS","1.If your piece is affected by random event pay 3$ to ignore it")
	{
		@Override
		public void performTasks(Player currentPlayer)
		{
			System.out.println("task pending");
		}

			}, 
	
	
	//take $2 from bank
	DRAGONSLANDING("DRAGONS LANDING","1.Take 2$ from bank.")	
	{
		@Override
		public void performTasks(Player currentPlayer) 	
		{
			GreenPlayerCardEnum.GLOBALOBJ.takeMoneyFromBank(2, currentPlayer);
		}
			}, 
	
	
	//take one card from deck and then discard one
	UNREALESTATE("UNREAL ESTATE","1.Draw a card after that dicard one card")
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			System.out.println("Player will draw one card from the pile..");
			GreenPlayerCardEnum.GLOBALOBJ.drawCardsFromDeck(1, currentPlayer);
			
			System.out.println("Player will discard one card..");
			discardACard(currentPlayer, this);
		}

			}, 
	
	
	//pay $3 to bank and place one minion in Dolly Sisters or adjacent area	
	DOLLYSISTERS("DOLLY SISTERS","1.Pay $3 to bank and place your minion in Dolly Sisters or adjacent area")
	{

		@Override
		public void performTasks(Player currentPlayer ) throws JSONException 
		{
			 
			GreenPlayerCardEnum.GLOBALOBJ.payMoneyToBank(3, currentPlayer);
			//GreenPlayerCardEnum.GLOBALOBJ.pl
			placeoneMinion(currentPlayer, "Dolly Sisters");
		}

			}, 
	
	
	//take $1 from bank
	NAPHILL("NAP HILL","1.Take $1 from bank..")	
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			  
			 GreenPlayerCardEnum.GLOBALOBJ.takeMoneyFromBank(1, currentPlayer);
		}

	}, 
	
	
	//take $3 from bank
	SEVENSLEEPERS("SEVEN SLEEPERS","1.Take $3 from bank")	
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			 
			 GreenPlayerCardEnum.GLOBALOBJ.takeMoneyFromBank(3, currentPlayer);
		}

			},
	
	
	//pay $2 to bank and remove one troublemarker on boards
	ISLEOFGODS("ISLE OF GODS","1.Pay 2$ to bank and remove one troublemarker from the board")	
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
			 
			GreenPlayerCardEnum.GLOBALOBJ.payMoneyToBank(2, currentPlayer);
			
			System.out.println("Now to remove troublemarker from board..");
			// show to user where trouble markers are
			GreenPlayerCardEnum.GLOBALOBJ.removeTroubleMarker();
		}
	}, 
	
	
	//take $1 from bank
	LONGWELL("LONGWELL","1.Take 1$ from the bank")	
	{
		@Override
		public void performTasks(Player currentPlayer) 
		{
		
		GreenPlayerCardEnum.GLOBALOBJ.takeMoneyFromBank(1, currentPlayer);
		}
	}, 
	
	
	//pay $3 to bank and place the minions in Dimwell or adjacent area
	DIMWELL("DIMWELL","1.Pay 3$ and place minion in Dimwell or adjacent area")
	{
		@Override
		public void performTasks(Player currentPlayer) throws JSONException 
		{
			
			GreenPlayerCardEnum.GLOBALOBJ.payMoneyToBank(3, currentPlayer);
			placeoneMinion(currentPlayer, "Dimwell");
		}
	}, 
	
	
	//place one troublemarker in the Shades or adjacent area
	THESHADES("THE SHADES","1.Place a troublemarker in The Shades or adjacent area")
	{
		public void performTasks(Player currentPlayer) throws JSONException
		{
			this.placeTroubleMarker("The Shades");
			
		}
	}; 
	
	@Override
	public void takeMoneyExchangeCardsFromAnotherPlayer(
			Player currentPlayingPlayer, int amt) {
		     
		
	}
	
	/** areaName that identifies an area*/
	private String areaName;
	private String action;
	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	private CityAreaCardEnum(String area,String action)
	{
			setareaName(area);
			setAction(action);
			//setIsActive(true);
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

		@Override
	public String questionsToAsk(String qns) {
			return null;
		}
	
	@Override
	public void placeTroubleMarker(String areaLocation) throws JSONException
	{
		String troubleLoc = null;
		System.out.println("Troublemarker will be placed..");
		for(Area a : BoardGame.board_areas){
			if((!areaLocation.isEmpty()) && areaLocation.trim().equalsIgnoreCase(a.getAreaName())){
				System.out.println("You can place troublemarker in these area: \n");
				String result = BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.areaDetails, areaLocation);
				BoardGame.displayAdjacentAreas(result,0);
			
				troubleLoc = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Enter area name to place troublemarker :nul");
				if(troubleLoc!=null && !(troubleLoc.matches("\\d+"))){
					for(Area ar : BoardGame.board_areas){
						if((!troubleLoc.isEmpty())&& troubleLoc.trim().equalsIgnoreCase(ar.getAreaName()) 
								&& !(ar.isTroubleMarkers())){
							ar.setTroubleMarkers(true);
							System.out.println("Troublemarker placed in "+ar.getAreaName());
									}
					
								}
				}
				else{
					System.out.println("Your entered "+troubleLoc+" location is invalid.Try again!!");
					placeTroubleMarker(areaLocation);
				}
								
					}	
				}	
		
		}	
		
	@Override
	public void ignoreRandomEvent(Player currentPlayer)
	{
		
	}



	@Override
	public String askSymbolsInOrder(GreenPlayerCardEnum tempEnum, String result,Player p) {
		  
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
	public void takePlayingCards(Player currrentPlayer, int number,boolean b) {
		  
		
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

		
	}

	@Override
	public void payMoneyToBank(int amt, Player currentPlayer) {
	}

	@Override
	public void removeMinion(int num, Player currentPlayer) {
	}

	@Override
	public boolean removeMinionOFAnotherPlayer(int num, Player currentPlayer,
			Player fromPlayer) throws JSONException {
		  
		if(!(InterruptCard.wantToPlayInterruptCard(fromPlayer, currentPlayer).equalsIgnoreCase("success"))){
			removeMinion(num, fromPlayer);
			return false;
		}
		else{
			System.out.println("Other player has played an interrupt card");
		}
		return true; 
	}

	@Override
	public void removeMinionOFYourOwn(int num, Player currentPlayer) {
	}

	@Override
	public void moveMinionToOtherArea(Player currentPlayer, Player fromPlayer,
			String fromLocation, String toLocation) throws JSONException {
	}
	
	public int rollDie() {
		return 0;
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
			String arealocation = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Enter area name to remove the troublemarker from : nul");
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
		String playercardname = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Enter player card name to discard");
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
		addToDiscardPile(1,temp,currentPlayer,true);
		
	}

	

	public void discardACard(Player currentPlayingPlayer, CityAreaCardEnum tempname){
		try{
			if(currentPlayingPlayer.getPlayersPlayingCard().size()!=0){
				System.out.println("Card will be discarded as per your wish");
				
				System.out.println("Your cards as of now: ");
				ArrayList<GreenPlayerCardEnum> listOfCurrPlayerCards = currentPlayingPlayer.getPlayersPlayingCard();
				int count = 1;
				for(GreenPlayerCardEnum gc : listOfCurrPlayerCards){
					if(! (gc.getName().equalsIgnoreCase(GreenPlayerCardEnum.THEFOOLSGUILD.getName()) ||
							gc.getName().equalsIgnoreCase(GreenPlayerCardEnum.DRWHITEFACE.getName()))){
						System.out.printf("%-2s%-15s\n",count,gc.getName());
						BoardGame.pieceNumberAreaList.add(""+count+":"+gc.getName());
						count++;
					}
				}
						
				String result = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Choose a card to discard:nul");
				
				//if(!result.matches("\\w+")){
				String res = BoardGame.getPieceNumberList(result);
					
					GreenPlayerCardEnum gec = PlayerCardUtility.getEnumInstance(res);
					
					if(listOfCurrPlayerCards.contains(gec)){
						GreenPlayerCardEnum.GLOBALOBJ.addToDiscardPile(1,gec, currentPlayingPlayer, false);
					}
					//}
			//else{
					//	System.out.println("You have entered invalid input....Start Again!!!");
					//	discardACard(currentPlayingPlayer, tempname);
					//	return;
						
			//		}
			}
			System.out.println("Action completed..");
		}
		catch(Exception e){
			throw e ;
		}	
	}
		
	@Override
	public void drawCardsFromDeck(int num , Player currentPlayer) {
	
	}
	
	
	
	@Override
	public String discardCardsPerYourWish(Player currentPlayingPlayer,
			GreenPlayerCardEnum gc, int amt) {
		     
		return "";
	}
	@Override
	public void removeTroubleMarker() {
	}
	
	@Override
	public void playingCardsAction(Player currentPlayer, Player fromPlayer,
			int count) {
	}
	@Override
	public void givePlayingCards(Player currrentPlayer, int number) {
	}
	@Override
	public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player p , boolean addCard) {
	}
	@Override
	public void drawCardsFromDiscardPile(int num, Player player) {
	}
	@Override
	public void placeMinionActionPlayerCard(Player currentPlayingPlayer) {
	}
	@Override
	public void placeMinionActionPlayerCard(Player currentPlayingPlayer,String s){};

	@Override
	public void fillYourHandWIthPlayerCard(int i, Player ps) {
	}
	
	public static CityAreaCardEnum getCityAreaCardInstance(String cityAreaCardName){
		CityAreaCardEnum temp = null;
		String s = null;
		
		if(cityAreaCardName.matches("\\d+")){
			int i = Integer.parseInt(cityAreaCardName.trim()); 
			
			for(String stre : StartPlayingGame.currCityAreaCards){
				int j = Integer.parseInt(stre.split(":")[1].trim());
				if( i == j){
					s = stre.split(":")[0].trim();
					break;
				}
			}
		}
		
		for(CityAreaCardEnum gc : CityAreaCardEnum.values()){
			if(cityAreaCardName.trim().equalsIgnoreCase(gc.getareaName()) && !(cityAreaCardName.matches("\\d+"))){
				temp  = gc;
				break;
			}
			else if(cityAreaCardName.matches("\\d+")){
				if(s.equalsIgnoreCase(gc.getareaName())){
					temp  = gc;
					break;
				}
			}
		}
		return temp;
	}
	
	@Override
	public void addBuildingAction(Player p){}
	
	public void placeoneMinion(Player currentPlayer,String areaLocation) throws JSONException
	{	
		System.out.println("Minion will be placed..");
		String res = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Where do you want to place your minion.\n" + "A."+areaLocation
				+" B. Adjacent Areas" +":nul");
		
		if(res.equalsIgnoreCase("A")){
			for(Area a : BoardGame.board_areas)
				if((!areaLocation.isEmpty()) && areaLocation.trim().equalsIgnoreCase(a.getAreaName())){	
				System.out.println("Placing a minion in " +areaLocation);
				currentPlayer.setMinions(currentPlayer.getPlayerColor(), areaLocation);
				a.setMinionColor(areaLocation);
				if(!(a.isTroubleMarkers()) && currentPlayer.totalMinionsInAreaForAllPlayers(areaLocation)>1) //Sanchit Fix
					a.setTroubleMarkers(true);
				System.out.println("Minion placed in "+areaLocation);
				break;
			}
				
		}
		
		else if(res.equalsIgnoreCase("B")){
					
					System.out.println("You can place minion in following areas adjacent to "+areaLocation);
					BoardGame.displayMinionsForPlayerOnBoard(currentPlayer,0);
					System.out.println();
		
					String plMin1 = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Choose area name to place minion in:nul");
					String plMin = BoardGame.getPieceNumberList(plMin1);
					for(Area a : BoardGame.board_areas)
						if(a.getAreaName().equalsIgnoreCase(plMin)){
							currentPlayer.setMinions(currentPlayer.getPlayerColor(), plMin);
							a.setMinionColor(plMin);
						
							if(!(a.isTroubleMarkers()) && currentPlayer.totalMinionsInAreaForAllPlayers(plMin)>1) //Sanchit Fix
								a.setTroubleMarkers(true);
							System.out.println("Your Minion is placed in:"+plMin);
							break;	
						}
				}
				
			}
	
}


