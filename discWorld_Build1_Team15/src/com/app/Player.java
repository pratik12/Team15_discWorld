package com.app;
/**
 * Player class holding properties of a player
 * @author Pratik
 *
 */
public class Player {
	
	private String minion_Color;
	private int minion_Quantity;
	public int number_of_buildings = 6;
	private String winning_condition;
	public String random_event_card;
	private double player_amount;
	
	/**
	 * Constructor to set the properties of player
	 * @param minion_Color
	 * @param quantity
	 */
	public Player(String minion_Color, int quantity){
		set_Minion_Color(minion_Color);
		set_Minion_Quantity(quantity);
		set_Player_amount(10);
	}
	
	/**
	 * 
	 * @return color of minion
	 */
	public String get_Minion_Color() {
		return minion_Color;
	}
	
	/**
	 * 
	 * @param minion_Color the color of minion to be set
	 */
	public void set_Minion_Color(String minion_Color) {
		this.minion_Color = minion_Color;
	}
	
	public int get_Minion_Quantity() {
		return minion_Quantity;
	}
	
	public void set_Minion_Quantity(int minion_Quantity) {
		this.minion_Quantity = minion_Quantity;
	}

	/**
	 * @return the winning_condition
	 */
	public String get_Winning_condition() {
		return winning_condition;
	}

	/**
	 * @param winning_condition the winning_condition to set
	 */
	public void set_Winning_condition(String winning_condition) {
		this.winning_condition = winning_condition;
	}

	/**
	 * @return the player_amount
	 */
	public double get_Player_amount() {
		return player_amount;
	}

	/**
	 * @param player_amount the player_amount to set
	 */
	public void set_Player_amount(double player_amount) {
		this.player_amount = player_amount;
	}

		
	

}
