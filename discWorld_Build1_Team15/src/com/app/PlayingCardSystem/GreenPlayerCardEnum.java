package com.app.PlayingCardSystem;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONException;

import userInputUtility.UserInputUtility;

import com.app.Area;
import com.app.BoardGame;
import com.app.Player;

/**
 * The Class PlayerCard.
 *
 * @author Pratik
 * Class of player cards that will be given out to players when the game starts
 */
public enum GreenPlayerCardEnum implements PlayingCardRulesInterface {
	
	
	BOGGIS("BOGGIS","green","DeckPile", new String[]{"scroll","minion"} ){
		
		@Override
		public void performTasks(Player currentPlayingPlayer) {
		
			
		String res = askSymbolsInOrder(this ,"0");
		
		while(!(res.split(":")[0].trim().equalsIgnoreCase("exit")) || 
			(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			if(res.split(":")[0].trim().equalsIgnoreCase("scroll")){
				PlayingCardRuleEngine.TEST.takeMoney(2, currentPlayingPlayer);
				res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}
			else if(res.split(":")[0].trim().equalsIgnoreCase("minion")){
				// call appropriate function
				String minionLocation = questionsToAsk("Enter area name where you want to place a minion? : nul");
				for(Area a : BoardGame.board_areas){
					if(a.getAreaName().equalsIgnoreCase(minionLocation)){
						currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
						break;
					}
					else{
						System.out.println("you have entered a wrong area name.");
					}
				}
			}
		}
		System.out.println("You hae finished playing this card. Place it on discard deck");
		}
		
		
		// place this card on the discard pile
	},//take 2$ from all players
	
	BEGGARSGUILD("BEGGARSGUILD","green","DeckPile") {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) {
			
			String result = questionsToAsk("Enter the color of player you want to select (R/G/Y/B) in this format : nul");
			Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
			this.takePlayingCards(currentPlayingPlayer,selectedPlayer, 2);
			
		}
		
		@Override
		public void takePlayingCards(Player currrentPlayer, Player fromPlayer, int i){
			
			if(!(i > fromPlayer.getPlayersPlayingCard().size())){
				while(i!=0){
					String result = questionsToAsk("Enter the card name you want to give with no spaces");
					for(GreenPlayerCardEnum gc : fromPlayer.getPlayersPlayingCard()){
						if(result.equalsIgnoreCase(gc.name())){
							currrentPlayer.setPlayersPlayingCard(gc);
							fromPlayer.getPlayersPlayingCard().remove(gc);
							i--;
							break;
						}
					}
					//currrentPlayer.setPlayersPlayingCard(gc);
					
				}
			}
		}
	},// sleect 1 player. take 2 cards
	
	AMBANK("AMBANK","green","DeckPile") {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) {
			
			
		}
	}, //loan of 10$ from bank. at end payback 12$ or loose 15 points
	
	AMSUNSHINEDRAGONSANCTUARY("AMSUNSHINEDRAGONSANCTUARY","green","DeckPile") {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) {
			
			for(Player p : BoardGame.playersInGame){
				if(!(p.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor()))){
					
					Player fromPlayer = null;
					String res = questionsToAsk("Enter Your piece color to Give him a playing card :"+
					"Enter 'curr' to Pay him 1$");
					if(res.equalsIgnoreCase("r") || res.equalsIgnoreCase("g")
							|| res.equalsIgnoreCase("b") || res.equalsIgnoreCase("y")){
						for(Player pc : BoardGame.playersInGame){
							if(pc.getPlayerColor().equalsIgnoreCase(res))
								fromPlayer = pc;
						}
						takePlayingCards(currentPlayingPlayer, fromPlayer, 1);
					}else if(res.equalsIgnoreCase("curr")){
						takeMoneyFromPlayer(1, currentPlayingPlayer, fromPlayer);
					}
				}
			}
		}
	}, // take 1$ from every player or one card
	
	ANGUA("ANGUA","green","DeckPile") {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) {}
	},//
	
	AGONYAUNTS("AGONYAUNTS","green","DeckPile") {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) {}
	},
	
	DYSK("DYSK","green","DeckPile") {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) {}
	},
	
	DUCKMAN("DUCKMAN","green","DeckPile") {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) {}
	},
	
	DRUMKNOTT("DRUMKNOTT","green","DeckPile") {
		
		@Override
		public void performTasks(Player currentPlayingPlayer){}
	},
	CMOTDIBBLER("CMOTDIBBLER","green","DeckPile") {
		// roll die. if 7 take 4 from bank. if 1 pay 2$ to bank or remove 1 YOUR minion.
		@Override
		public void performTasks(Player currentPlayingPlayer) {
			int res = rollDie();
			if(res==7){
				takeMoneyFromBank(4, currentPlayingPlayer);
			}
			else if(res==1){
				String result = questionsToAsk("Hit 'p' to pay money to bank : Hit 'rem' to remove your minion");
				if(result.trim().equalsIgnoreCase("p")){
					payMoneyToBank(2, currentPlayingPlayer);
				}
				else if(result.trim().equalsIgnoreCase("rem")){
					removeMinionOFYourOwn(1,currentPlayingPlayer);
				}
			}
		}
	},
	DRCRUCES("DRCRUCES","green","DeckPile") {
		@Override
		public void performTasks(Player currentPlayingPlayer) {
			
		}
	},
	CAPTAINCARROT("CAPTAINCARROT","green","DeckPile") {
		@Override
		public void performTasks(Player currentPlayingPlayer) {}
	},
	MRSCAKE("MRSCAKE","green","DeckPile") {
		@Override
		public void performTasks(Player currentPlayingPlayer) {}
	},
	MRBENT("MRBENT","green","DeckPile") {
		@Override
		public void performTasks(Player currentPlayingPlayer) {
			
			
			
		}
	},
	GROAT("GROAT","green","DeckPile") {
	},
	GASPODE("GASPODE","green","DeckPile") {
	},
	FRESHSTARTCLUB("GROAT","green","DeckPile") {
	},
	FOULOLERON("GROAT","green","DeckPile") {
		// moveminion of OTHER player from an area to adjacent area
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
		
			
		String result = questionsToAsk("Enter the color of player you want to select (R/G/Y/B) in this format : nul");
		Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
		removeMinionOFAnotherPlayer(2, currentPlayingPlayer, selectedPlayer);
		System.out.println("Areas where your selected player has minions");
		for(ArrayList<String> t : selectedPlayer.getMinions().values()){
			for(String str : t){
				if(!(str.equalsIgnoreCase("Players Pile")))
				System.out.print(str + ", ");
			}
		}
		System.out.println();
		String areaToMoveMinionFrom = questionsToAsk("Enter area name to move minion from : nul");
		moveMinionToOtherArea(currentPlayingPlayer, selectedPlayer, areaToMoveMinionFrom);
		
		}
	},
	THEFOOLSGUILD("GROAT","green","DeckPile") {
	},
	THEFIREBRIGADE("GROAT","green","DeckPile") {
		// select plaeyr if they dont give you 5$ remove 1 of his building from board
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
			String result = questionsToAsk("Enter the color of player you want to select (R/G/Y/B) in this format : nul");
			Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
			String res = questionsToAsk("Enter the maount you wish to take from player : nul");
			if(!(takeMoneyFromPlayer(Integer.parseInt(res), currentPlayingPlayer, selectedPlayer))){
				// you want to remove his building
				removeBuilding(currentPlayingPlayer, selectedPlayer);
			}
			
		}
	},
	INIGOSKIMMER("GROAT","green","DeckPile") {
	},
	HISTORYMONKS("GROAT","green","DeckPile") {
	},
	HEX("GROAT","green","DeckPile") {
		@Override
		public void performTasks(Player currentPlayer) throws JSONException{
			drawCardsFromDeck(3, currentPlayer);
		}
	},
	HERENOW("GROAT","green","DeckPile") {
		@Override
		public void performTasks(Player currentPlayer) throws JSONException{
			int res = rollDie();
			if(res >= 7){
				String result = questionsToAsk("Enter a players piece color you want to take money from : nul");
				Player selectedPlayer = selectPlayer(currentPlayer, result);
				takeMoneyFromPlayer(3, currentPlayer, selectedPlayer);
			}
			else if(res == 1){
				
				for( ArrayList<String> str : currentPlayer.getMinions().values()){
					for(String s : str){
						if(!(s.equalsIgnoreCase("Players Pile")) ){
							System.out.print(s +", ");
						}
					}
				}
				removeMinionOFYourOwn(1, currentPlayer);
			}
		}
	},
	HARRYKING("GROAT","green","DeckPile") {
		// discrad as many cards as uw ish, get 2 for each
		@Override
		public void performTasks(Player currentPlayingPlayer){
			
			while(currentPlayingPlayer.getPlayersPlayingCard().size()!=0){

				System.out.println("Your cards as of now: ");
				for(GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard())
					System.out.print(gc.name()+", ");
				
				String res = questionsToAsk("Enter the area name : Hit '0' to exit");
				if(Integer.parseInt(res)!=0){
					for(GreenPlayerCardEnum temp : currentPlayingPlayer.getPlayersPlayingCard()){
						if(currentPlayingPlayer.getPlayersPlayingCard().contains(res)){
							addToDiscardPile(temp);
							currentPlayingPlayer.getPlayersPlayingCard().remove(temp);
							takeMoneyFromBank(2, currentPlayingPlayer);
						}
					}
				}
				else if(Integer.parseInt(res)==0)
					break;
					
				}
			
		}
	},
	GRYLE("GROAT","green","DeckPile") {
		
	},
	HARGAHOUSEOFRIBS("GROAT","green","DeckPile") {
	},
	THEPEELEDNUTS("THEPEELEDNUTS","green","DeckPile") {
	},
	THEOPERAHOUSE("THEOPERAHOUSEOPERAHOUSE","green","DeckPile") {
		
		public void performTasks(Player currentPlayer) throws JSONException{
			getMoneyForMinionsinArea(1, currentPlayer, "Isle of Gods");
		}
	},
	NOBBYNOBBSS("NOBBYNOBBSS","green","DeckPile") {
	},
	MODO("MODO","green","DeckPile") {
	},
	THEMENDEDDRUM("THEMENDEDDRUM","green","DeckPile") {
	},
	LIBRARIAN("LIBRARIAN","green","DeckPile") {
	},
	LEONARDOFQUIRM("LEONARDOFQUIRM","green","DeckPile") {
	},
	SHONKYSHOP("SHONKYSHOP","green","DeckPile") {
	},
	SACHARISSACRIPSLOCK("SACHARISSACRIPSLOCK","green","DeckPile") {
	},
	ROSIEPALM("ROSIEPALM","green","DeckPile") {
	},
	RINCEWIND("RINCEWIND","green","DeckPile") {
	},
	THEROYALMINT("THEROYALMINT","green","DeckPile") {
	},
	QUEENMOLLY("QUEENMOLLY","green","DeckPile") {
	},
	PINKPUSSYCATCLUB("PINKPUSSYCATCLUB","green","DeckPile") {
	},
	ZORGOTHERETROPHRENOLOGIST("ZORGOTHERETROPHRENOLOGIST","green","DeckPile") {
	},
	DRWHITEFACE("DRWHITEFACE","green","DeckPile") {
	},
	WALLACESONKY("WALLACESONKY","green","DeckPile") {
	},
	THESEAMSTRESSESGUILD("THESEAMSTRESSESGUILD","green","DeckPile") {
	},
	MRPINANDMRTULIP("MRPINANDMRTULIP","green","DeckPile") {
	},
	THETHIEVESGUILD("THETHIEVESGUILD","green","DeckPile") {
		// take 2$ from every other player
		@Override
		  public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
			for(Player p : BoardGame.playersInGame){
				if(!(p.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor())))
					takeMoneyFromPlayer(2, currentPlayingPlayer, p);
			}
			
		}  
		
	}, GIMLETDWARFDELICATESSEN("GIMLETDWARFDELICATESSEN","green","DeckPile") ;
	
	/** The location. */
	private String location ;
	
	/** The color. */
	private String color;

	/** The name. */
	private String name;

	private String[] symbols;
	/** The id of playing card **/
	/**
	 * Instantiates a new player card.
	 *
	 * @param color the color
	 * @param name the name
	 * @param location the location
	 */
	private GreenPlayerCardEnum( String name, String color, String location, String... symbols){

		setColor(color);
		setLocation(location); 
		setName(name);
		setSymbols(symbols);
	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the location of the player card to set
	 * would be set to either players hand, discard pile or draw pile
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

	/**
	 * @return the symbols
	 */
	public String[] getSymbols() {
		return symbols;
	}
	
	/**
	 * @param symbols the symbols to set
	 */
	public void setSymbols(String[] symbols) {
		this.symbols = symbols;
	}
	
	public boolean addMoney(int amount) {
		return false;
	}
	
	public boolean moveMinion() {
		return false;
	}
	    
	public boolean assasinate(String pieceToRemove) {
		return false;
	}
	
	    
	public boolean playAnotherCard() {
		return false;
	}
	@Override    
	public boolean interrupt() {
		return false;
	}

	@Override
	public void performTasks(Player currentPlayingPlayer) throws JSONException {}

	@Override
	public String askSymbolsInOrder(GreenPlayerCardEnum tempEnum, String result) {
		String res = "enter";
		res = UserInputUtility.USERINPUTUTILITYENUM.getUserInput(tempEnum,result);
		return res;
	}

	@Override
	public boolean takeMoneyFromPlayer(int amt, Player currentPlayingPlayer, Player fromPlayer) {
		
		String res = questionsToAsk("Would you Give me $'s .Hit Y for Yes or elsewise : nul");
		if(res.equalsIgnoreCase("y")){
			currentPlayingPlayer.setPlayerAmount(currentPlayingPlayer.getPlayerAmount() + amt);
			fromPlayer.setPlayerAmount(fromPlayer.getPlayerAmount() - amt);
		}
		else{
			return false;
		}
		
		
		return true;
	}

	@Override
	public void takePlayingCards(Player currrentPlayer , Player fromPlayer, int number) {}

	@Override
	public Player selectPlayer(Player currentPlayer, String playerToSelect) {
		
		Player temp = null;
		if((playerToSelect.equalsIgnoreCase("r") || playerToSelect.equalsIgnoreCase("g") ||
				playerToSelect.equalsIgnoreCase("b") || playerToSelect.equalsIgnoreCase("y"))){
			for(Player pc : BoardGame.playersInGame){
				if(pc.getPlayerColor().equalsIgnoreCase(playerToSelect))
				temp = pc;
			}
		}
		else{
			System.out.println("Select color of existing player");
		}
		
		return temp;
	}

	

	@Override
	public String questionsToAsk(String qns) {
		String result = null;
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
	public void takeLoanFromBank( int amt, Player currentPlayer) {

		if(amt!=0){
			currentPlayer.setPlayerAmount(currentPlayer.getPlayerAmount()+amt);
			BoardGame.setBank(BoardGame.getBank()-amt);
		}
		else{
			System.out.println("Entered amount cannot be 0");
		}
	}

	@Override
	public int rollDie() {

		Random num = new Random();
		int i = num.nextInt(12);
		
		if(i!=0)
			return i; 
		else
			rollDie();
		return 0;
	}

	@Override
	public void takeMoneyFromBank(int amt, Player currentPlayer) {
		
		takeLoanFromBank(amt, currentPlayer);
	}

	@Override
	public void payMoneyToBank(int amt, Player currentPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMinion(int num, Player currentPlayer) {
		
			while(num!=0){
				String minionlocation = questionsToAsk("Enter area name to remove your minion from : nul");
				for(ArrayList<String> str : currentPlayer.getMinions().values()){
					for(String temp : str){
						if(minionlocation.equalsIgnoreCase(temp)){
							currentPlayer.getMinions().values().remove(temp);
							currentPlayer.setMinionQuantity(currentPlayer.getMinionQuantity()-1);
							break;
						}
						
					}
					break;
				}
				num--;
			}
	}
	
	/**
	 * This method moves minion of a selected player from one area to adjacent area.
	 * Takes in input the player who wants to move minion, the player who's minion he wants to move
	 * and the minion location from where it has to be moved.
	 * The method will list you all the adjacent areas for this minion and then you can 
	 * choose one area to place this minion into.
	 */
	@Override
	public void moveMinionToOtherArea(Player currentPlayer, Player fromPlayer,
			String toLocation) throws JSONException {

		if(!(fromPlayer.equals(null))){
			String result = BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.getInstance().areaDetails, toLocation);
			String[] temp = result.split(":");
			System.out.println("Adjacent areas ");
			for(int i=0; i<temp.length; i++){
				for(Area a: BoardGame.board_areas){
					if(a.getAreaNumber()==Integer.parseInt(temp[i])){
						System.out.print(a.getAreaName()+", ");
					}
				}
			}
			String res = questionsToAsk("Enter area name to whose adjacent area you need to move minion to : nul");
			// record the result and place the opposite players minion there
			if(!res.equals(null)){
				fromPlayer.setMinions(fromPlayer.getPlayerColor(), res);
			}
		}
		else{
			System.out.println("From Player is empty");
		}
	}

	@Override
	public void removeBuilding(Player currentPlayer, Player fromPlayer) {
		
		System.out.println("Opposite Players Areas where he has Building ");
		if(!(fromPlayer.getPlayerAreas().isEmpty())){
			for(Area str : fromPlayer.getPlayerAreas()){
				System.out.print(str.getAreaName() + ", ");
			}
		}
		String res = questionsToAsk("Enter the area name you want to remove building from : nul");
		for(Area area : fromPlayer.getPlayerAreas()){
			if(res.trim().equalsIgnoreCase(area.getAreaName())){
				fromPlayer.getPlayerAreas().remove(area);
				break;
			}
			break;
		}
		
	}

	@Override
	public void drawCardsFromDeck(int num , Player currentPlayer) {
		
		while(num!=0){
			Random rand = new Random();
			int n = rand.nextInt(BoardGame.player_cards.size()-1);
			if(BoardGame.player_cards.get(n) instanceof GreenPlayerCardEnum)
				currentPlayer.getPlayersPlayingCard().add(BoardGame.player_cards.get(n));
			else
				System.out.println("Not sufficient player cards are remaining in the deck");
		}
		
	}
	
	/**
	 * 
	 */
	@Override
	public void removeMinionOFAnotherPlayer(int num, Player currentPlayer,
			Player fromPlayer) {
		
		removeMinion(num, fromPlayer);
		
	}

	@Override
	public void removeMinionOFYourOwn(int num, Player currentPlayer) {
		
		removeMinion(num, currentPlayer);
	}

	/**
	 * Adds the specified players playing cards to the discard pile list on the board
	 */
	@Override
	public void addToDiscardPile(GreenPlayerCardEnum gc) {
		
		if(gc instanceof GreenPlayerCardEnum)
			BoardGame.setDiscardPilePlayerCards(gc);
		else
			System.out.println("Unsupported playercard. Verify the type of cardbeing passed");
	}

	/**
	 * This function takes in amount , current player who's turn is going on
	 * and the area from where the minions has to be counted.
	 * Adds the specified amount to the player based on the number of minions in his specified area 
	 */
	@Override
	public void getMoneyForMinionsinArea(int amt,Player currentplayer, String areaName) {
		
		if(amt!=0 && !(areaName.isEmpty())){
			
			for(Player p : BoardGame.playersInGame){
				for(ArrayList<String> str : p.getMinions().values())
				for(String s :str){
					if(areaName.equalsIgnoreCase(s)){
						currentplayer.setPlayerAmount(currentplayer.getPlayerAmount() + amt);
						
					}
				}
			}
			
			
		}
		
	}

}
