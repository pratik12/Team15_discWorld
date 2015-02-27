package com.app;

import java.io.ObjectInputStream.GetField;

/**
 * The Class PlayerCard.
 *
 * @author Pratik
 * Class of player cards that will be given out to players when the game starts
 */
public enum PlayerCard implements Actions{
	

	BOGGIS("BOGGIS","green","DeckPile"),
	
	BEGGARSGUILD("BEGGARSGUILD","green","DeckPile"),
	
	AMBANK("AMBANK","green","DeckPile"),
	AMSANCTUARY("AMSANCTUARY","green","DeckPile"),
	ANGUA("ANGUA","green","DeckPile"),
	AGONYAUNTS("AGONYAUNTS","green","DeckPile"),
	DYSK("DYSK","green","DeckPile"),
	DUCKMAN("DUCKMAN","green","DeckPile"),
	DRUMKNOTT("DRUMKNOTT","green","DeckPile"),
	CMOTDIBBLER("CMOTDIBBLER","green","DeckPile"),
	DRCRUCES("DRCRUCES","green","DeckPile"),
	CAPTAINCARROT("CAPTAINCARROT","green","DeckPile"),
	MRSCAKE("MRSCAKE","green","DeckPile"),
	GROAT("GROAT","green","DeckPile"),
	GASPODE("GASPODE","green","DeckPile"),
	FRESHSTARTCLUB("GROAT","green","DeckPile"),
	FOULOLERON("GROAT","green","DeckPile"),
	THEFOOLSGUILD("GROAT","green","DeckPile"),
	THEFIREBRIGADE("GROAT","green","DeckPile"),
	INIGOSKIMMER("GROAT","green","DeckPile"),
	HISTORYMONKS("GROAT","green","DeckPile"),
	HEX("GROAT","green","DeckPile"),
	HERENOW("GROAT","green","DeckPile"),
	HARRYKING("GROAT","green","DeckPile"),
	GRYLE("GROAT","green","DeckPile"),
	HARGAHOUSEOFRIBS("GROAT","green","DeckPile"),
	THEPEELEDNUTS("THEPEELEDNUTS","green","DeckPile"),
	THEOPERAHOUSE("THEOPERAHOUSEOPERAHOUSE","green","DeckPile"),
	NOBBYNOBBSS("NOBBYNOBBSS","green","DeckPile"),
	MODO("MODO","green","DeckPile"),
	THEMENDEDDRUM("THEMENDEDDRUM","green","DeckPile"),
	LIBRARIAN("LIBRARIAN","green","DeckPile"),
	LEONARDOFQUIRM("LEONARDOFQUIRM","green","DeckPile"),
	SHONKYSHOP("SHONKYSHOP","green","DeckPile"),
	SACHARISSACRIPSLOCK("SACHARISSACRIPSLOCK","green","DeckPile"),
	ROSIEPALM("ROSIEPALM","green","DeckPile"),
	RINCEWIND("RINCEWIND","green","DeckPile"),
	THEROYALMINT("THEROYALMINT","green","DeckPile"),
	QUEENMOLLY("QUEENMOLLY","green","DeckPile"),
	PINKPUSSYCATCLUB("PINKPUSSYCATCLUB","green","DeckPile"),
	ZORGOTHERETROPHRENOLOGIST("ZORGOTHERETROPHRENOLOGIST","green","DeckPile"),
	DRWHITEFACE("DRWHITEFACE","green","DeckPile"),
	WALLACESONKY("WALLACESONKY","green","DeckPile"),
	THESEAMSTRESSESGUILD("THESEAMSTRESSESGUILD","green","DeckPile"),
	MRPINANDMRTULIP("MRPINANDMRTULIP","green","DeckPile"),
	THETHIEVESGUILD("THETHIEVESGUILD","green","DeckPile");
		
	BoardGame bg;
	private static String location ;
	
	/** The color. */
	private String color;

	/** The name. */
	private String name;

	/** The location. */

	/** The id of playing card **/
	/**
	 * Instantiates a new player card.
	 *
	 * @param color the color
	 * @param name the name
	 * @param location the location
	 */
	private PlayerCard( String name, String color, String location){

		setColor(color);
		setLocation(location); 
		setName(name);

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
	@Override
	public boolean addMoney(int amount) {
		// TODO Auto-generated method stub
		return false;
		
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
	public boolean scroll() {
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
	
	

}
