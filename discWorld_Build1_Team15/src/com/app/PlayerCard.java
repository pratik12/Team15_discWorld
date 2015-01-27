package com.app;

/**
 * 
 * @author Pratik 
 * Class of player cards that will be given out to players when the game starts
 */
public class PlayerCard {
	
	private String color;
	private String name;
	private String location;

	public PlayerCard(String color, String name, String location){
		
		setColor(color);
		setLocation(location); 
		setName(name);

	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location of the player card to set
	 * would be set to either players hand, discard pile or draw pile
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
}
