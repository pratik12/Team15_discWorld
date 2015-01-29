package com.app;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Player class holding properties of a player
 * @author Pratik
 *
 */
public class Player {
	
	private String player_color;
	
	// data structure to store minion data
	private HashMap<String,ArrayList<String>> minions = new HashMap<String, ArrayList<String>>();
	
	private int minion_Quantity;
	private int number_of_buildings;
	private String winning_condition;
	public String random_event_card;
	private double player_amount;
	private String player_aid_card;
	
	// player can have many areas
	private ArrayList<Area> player_areas = new ArrayList<Area>();
	
	/**
	 * @return the player_areas
	 */
	public ArrayList<Area> getPlayer_areas() {
		return player_areas;
	}

	/**
	 * @param player_areas the player_areas to set
	 */
	public void setPlayer_areas(Area area_of_player) {
		
		if(area_of_player!=null){
			this.player_areas.add(area_of_player);
		}
	}

	/**
	 * Constructor to set the properties of player
	 * @param minion_Color
	 * @param quantity
	 */
	
	public Player(String player_color){
		
		set_Player_color(player_color);
		setMinions(get_Player_color(), "Players Pile");
		set_Minion_Quantity(12);
		set_Player_amount(10);
		set_Number_of_buildings(6);
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
	 * @return String representation of players personality card and his color
	 */
	public String toString(){
		
		return  "Player (" + get_Player_color()  +
				") is playing with " + get_Winning_condition();
				
	}
	
	/**
	 * 
	 * @return String representation of players collection of minions, buildings, money and his color
	 */
	public String current_inventory(){
		
		return  "Player (" +get_Player_color()+") current Inventory - " + "\n" 
				 + Integer.toString(get_Minion_Quantity()) + " Minion's , " + 
				  Integer.toString(get_Number_of_buildings()) + " Building's , " + 
				 Double.toString(get_Player_amount()) + " Ankh-Morph Dollars " + "\n" +
				  "City Area Cards : " + "\n" + getCityAreaCards();
	}

	/**
	 * 
	 * @return city area card for each player, if the player has no buildings placed the returns "Player has placed no building" 
	 */
	private String getCityAreaCards() {

		String result = "";
		
		if(!this.getPlayer_areas().isEmpty()) // getPlayerAreas returns the arraylist with area objects 
											  // only if player has placed a building in that area
			for(Area a : this.getPlayer_areas())
				return result += " " + a.get_Area_name();
			
		return "none";
		
	}

	/**
	 * @return the minions as Map
	 */
	public HashMap<String, ArrayList<String>> getMinions() {
		
		return minions;
	}

	/**
	 * @param accepts minion color and area name as location where you want to set the minion
	 */
	public void setMinions(String minion_color, String minion_location) {
		
		if(!(minion_color.isEmpty() && minion_location.isEmpty()))
			
			// checking if the minions hashmap has any entries
			if(minions.containsKey(this.get_Player_color()))
					minions.get(this.get_Player_color()).add(minion_location);
			else{ // initializing each player with 12 minions
				
				 minions.put(this.get_Player_color(), new ArrayList<String>());
				 for(int i=0; i <12; i++)
				 minions.get(this.get_Player_color()).add(minion_location);
			}
				
		else
			System.out.println("Minion color or location cannot be empty");
	}

	/**
	 * 
	 * @param player
	 * @param area_name
	 */
	public String addBuilding(String area_name){
		
		ArrayList<Area> temp = null;
		// check if for the area there already exists a building
		
		// first make a copy of all the players area in temp arraylist
		for(Player p : BoardGame.playersInGame){
			temp = new ArrayList<Area>();
			temp.addAll(p.getPlayer_areas());
			
		}
		
		// if area
		if(!(temp.isEmpty())){
			
			for(Area area : temp){
				if(!(area.get_Area_name().equals(area_name)) && !(area.is_Trouble_markers()) ){
					this.setPlayer_areas(area);
					this.set_Number_of_buildings(this.get_Number_of_buildings()-1);
					area.setBuildngs(true);

				}
				else{
					return "Cannot place a building";
				}
			}
		}
		else{
			// If the area does not exist with any player then just add that area object to current player 
			for(Area area : BoardGame.board_areas){
				if(area.get_Area_name().equals(area_name) && area.is_Trouble_markers()==false ){
					// set corresponding building attributes of player
					this.setPlayer_areas(area);
					this.set_Number_of_buildings(this.get_Number_of_buildings()-1);
					// set area's building attribute
					area.setBuildngs(true);
					break;
				}
				}
	
			
		}
		return "";
	}
}
