package com.app;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Player class holding properties of a player
 * @author Pratik
 *
 */
public class Player {
	
	private String player_color;
	private String minion_Color;
	private int minion_Quantity;
	private int number_of_buildings ;
	private String winning_condition;
	public String random_event_card;
	private double player_amount;
	private ArrayList<Area> player_areas;
	/**
	 * Constructor to set the properties of player
	 * @param minion_Color
	 * @param quantity
	 */
	
	public Player(String player_color){
		
		set_Player_color(player_color);
		set_Minion_Color(player_color);
		set_Minion_Quantity(12);
		set_Player_amount(10);
		set_Number_of_buildings(6);
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

	/**
	 * @return the number_of_buildings
	 */
	public int get_Number_of_buildings() {
		return number_of_buildings;
	}

	/**
	 * @param number_of_buildings the number_of_buildings to set
	 */
	public void set_Number_of_buildings(int number_of_buildings) {
		this.number_of_buildings = number_of_buildings;
	}

	/**
	 * @return the player_color
	 */
	public String get_Player_color() {
		return player_color;
	}

	/**
	 * @param player_color the color of players piece to set
	 */
	public void set_Player_color(String player_color) {
		this.player_color = player_color;
	}

	/**
	 * String format of object
	 */
	public String toString(){
		
		return  "Players Piece Color : " + get_Player_color() + "\n" +
				"Number of Minion's : " + Integer.toString(get_Minion_Quantity()) + "\n" +
				"Number of Building's : " + Integer.toString(get_Number_of_buildings()) + "\n" +
				"Current Amount : " + Double.toString(get_Player_amount()) + "\n" +
				"Personality Card he holds : " + get_Winning_condition();
	}

	
	

}
