package com.app;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerCard.
 *
 * @author Pratik
 * Class of player cards that will be given out to players when the game starts
 */
public class PlayerCard {
	
	/** The color. */
	private String color;
	
	/** The name. */
	private String name;
	
	/** The location. */
	private String location;

	/**
	 * Instantiates a new player card.
	 *
	 * @param color the color
	 * @param name the name
	 * @param location the location
	 */
	public PlayerCard(String color, String name, String location){
		
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

	
}
