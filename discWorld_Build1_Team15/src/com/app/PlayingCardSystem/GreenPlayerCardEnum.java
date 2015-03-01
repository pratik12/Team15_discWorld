package com.app.PlayingCardSystem;

import java.io.ObjectInputStream.GetField;

import userInputUtility.UserInputUtility;

import com.app.Player;

/**
 * The Class PlayerCard.
 *
 * @author Pratik
 * Class of player cards that will be given out to players when the game starts
 */
public enum GreenPlayerCardEnum implements PlayingCardRulesInterface{
	
	
	BOGGIS("BOGGIS","green","DeckPile", new String[]{"scroll","minion"} ){
		
		@Override
		public void performTasks(Player currentPlayingPlayer) {
		String res = askSymbolsInOrder(this ,"0");
		while(!(res.split(":")[0].equalsIgnoreCase("exit")) || 
			(!res.split(":")[0].equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			if(res.split(":")[0].equalsIgnoreCase("scroll")){
				PlayingCardRuleEngine.TEST.takeMoney(2, currentPlayingPlayer);
				res = askSymbolsInOrder(this, res.split(":")[1]);
			}
			else if(res.split(":")[0].equalsIgnoreCase("minion")){
				// call appropriate function
				currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), "Unreal Estate");
			}
		}
		System.out.println("You hae finished playing this card. Place it on discard deck");
		}
		// place this card on the discard pile
	},//take 2$ from all players
	
	BEGGARSGUILD("BEGGARSGUILD","green","DeckPile") {},// sleect 1 player. take 2 cards
	
	AMBANK("AMBANK","green","DeckPile") {}, //loan of 10$ from bank. at end payback 12$ or loose 15 points
	
	AMSANCTUARY("AMSANCTUARY","green","DeckPile") {}, // take 1$ from every player or one card
	
	ANGUA("ANGUA","green","DeckPile") {},//
	
	AGONYAUNTS("AGONYAUNTS","green","DeckPile") {},
	
	DYSK("DYSK","green","DeckPile") {},
	
	DUCKMAN("DUCKMAN","green","DeckPile") {},
	
	DRUMKNOTT("DRUMKNOTT","green","DeckPile") {
	},
	CMOTDIBBLER("CMOTDIBBLER","green","DeckPile") {
	},
	DRCRUCES("DRCRUCES","green","DeckPile") {
	},
	CAPTAINCARROT("CAPTAINCARROT","green","DeckPile") {
	},
	MRSCAKE("MRSCAKE","green","DeckPile") {
	},
	GROAT("GROAT","green","DeckPile") {
	},
	GASPODE("GASPODE","green","DeckPile") {
	},
	FRESHSTARTCLUB("GROAT","green","DeckPile") {
	},
	FOULOLERON("GROAT","green","DeckPile") {
	},
	THEFOOLSGUILD("GROAT","green","DeckPile") {
	},
	THEFIREBRIGADE("GROAT","green","DeckPile") {
	},
	INIGOSKIMMER("GROAT","green","DeckPile") {
	},
	HISTORYMONKS("GROAT","green","DeckPile") {
	},
	HEX("GROAT","green","DeckPile") {
	},
	HERENOW("GROAT","green","DeckPile") {
	},
	HARRYKING("GROAT","green","DeckPile") {
	},
	GRYLE("GROAT","green","DeckPile") {
	},
	HARGAHOUSEOFRIBS("GROAT","green","DeckPile") {
	},
	THEPEELEDNUTS("THEPEELEDNUTS","green","DeckPile") {
	},
	THEOPERAHOUSE("THEOPERAHOUSEOPERAHOUSE","green","DeckPile") {
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
	public void performTasks(Player currentPlayingPlayer) {}

	@Override
	public String askSymbolsInOrder(GreenPlayerCardEnum tempEnum, String result) {
		String res = "enter";
		res = UserInputUtility.USERINPUTUTILITYENUM.getUserInput(tempEnum,result);
		return res;
	}

	@Override
	public void takeMoney(int amt, Player currentPlayingPlayer) {}

	@Override
	public void takePlayingCards(Player currentPlayingPlayer) {}

	@Override
	public Player selectPlayer(Player currentPlayer, String playerToSelect) {
		return null;
	}
	

}
