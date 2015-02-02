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
	
	private ArrayList<PlayerCard> playersPlayingCard = new ArrayList<PlayerCard>();
	
	private int minion_Quantity;
	private int number_of_buildings;
	private String winning_condition;
	public String random_event_card;
	private int player_amount;
	private String player_aid_card;
	
	// player can have many areas
	private ArrayList<Area> player_areas = new ArrayList<Area>();
	
	/**
	 * @return the player_areas
	 */
	public ArrayList<Area> getPlayerAreas() {
		return player_areas;
	}

	/**
	 * @param player_areas the player_areas to set
	 */
	public void setPlayerAreas(Area area_of_player) {
		
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
		
		setPlayerColor(player_color);
		setMinions(getPlayerColor(), "Players Pile");
		setMinionQuantity(12);
		setPlayerAmount(100);
		setNumberOfBuildings(6);
	}
	
	
	
	public int getMinionQuantity() {
		return minion_Quantity;
	}
	
	public void setMinionQuantity(int minion_Quantity) {
		this.minion_Quantity = minion_Quantity;
	}

	/**
	 * @return the winning_condition
	 */
	public String getWinningCondition() {
		return winning_condition;
	}

	/**
	 * @param winning_condition the winning_condition to set
	 */
	public void setWinningCondition(String winning_condition) {
		this.winning_condition = winning_condition;
	}

	/**
	 * @return the player_amount
	 */
	public int getPlayerAmount() {
		return player_amount;
	}

	/**
	 * @param d the player_amount to set
	 */
	public void setPlayerAmount(int d) {
		this.player_amount = d;
	}

	/**
	 * @return the number_of_buildings
	 */
	public int getNumberOfBuildings() {
		return number_of_buildings;
	}

	/**
	 * @param number_of_buildings the number_of_buildings to set
	 */
	public void setNumberOfBuildings(int number_of_buildings) {
		this.number_of_buildings = number_of_buildings;
	}

	/**
	 * @return the player_color
	 */
	public String getPlayerColor() {
		return player_color;
	}

	/**
	 * @param player_color the color of players piece to set
	 */
	public void setPlayerColor(String player_color) {
		this.player_color = player_color;
	}

	/**
	 * @return String representation of players personality card and his color
	 */
	public String toString(){
		
		return  "Player (" + getPlayerColor()  +
				") is playing with " + getWinningCondition();
				
	}
	
	/**
	 * 
	 * @return String representation of players collection of minions, buildings, money and his color
	 */
	public String currentInventory(){
		
		return  "Player (" +getPlayerColor()+") current Inventory - " + "\n" 
				 + Integer.toString(getMinionQuantity()) + " Minion's , " + 
				  Integer.toString(getNumberOfBuildings()) + " Building's , " + 
				 Double.toString(getPlayerAmount()) + " Ankh-Morph Dollars " + "\n" +
				  "City Area Cards : " + "\n" + getCityAreaCards();
	}

	/**
	 * 
	 * @return city area card for each player, if the player has no buildings placed the returns "Player has placed no building" 
	 */
	private String getCityAreaCards() {

		String result = "";
		
		if(!this.getPlayerAreas().isEmpty()) // getPlayerAreas returns the arraylist with area objects 
											  // only if player has placed a building in that area
			for(Area a : this.getPlayerAreas())
				result +=  a.getAreaName() + " : ";
			
		return result;
		
	}

	/**
	 * @return the minions as Map
	 */
	public HashMap<String, ArrayList<String>> getMinions() {
		
		return minions;
	}
	
    /**
     * places a minion in any location.
     *
     * @param player
     * @param location
     */
    public void placeMinion(String location) {

        if (!(location.isEmpty())) {

            this.setMinions(this.getPlayerColor(), location);
            // updating PLAYERS minions quantity
            this.setMinionQuantity(this.getMinionQuantity() - 1);
        } else {
            System.out.println("Provide location for minion to be placed");
        }

    }

	/**
	 * @param accepts minion color and area name as location where you want to set the minion
	 */
	public void setMinions(String minion_color, String minion_location) {
		
		if(!(minion_color.isEmpty() && minion_location.isEmpty()))
			
			// checking if the minions hashmap has any entries
			if(minions.containsKey(this.getPlayerColor()))
					minions.get(this.getPlayerColor()).add(minion_location);
			else{ // initializing each player with 12 minions
				
				 minions.put(this.getPlayerColor(), new ArrayList<String>());
				 for(int i=0; i <12; i++)
				 minions.get(this.getPlayerColor()).add(minion_location);
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
		
		ArrayList<Area> temp = new ArrayList<Area>();
		// check if for the area there already exists a building
		
		// first make a copy of all the players area in temp arraylist
		for(Player p : BoardGame.playersInGame){
			
			for(int i =0 ; i<p.getPlayerAreas().size(); i++){
				temp.add(p.getPlayerAreas().get(i));
			}		
		}
		
		
		if(!(temp.isEmpty())){
			// always check that if you want to place a building in an area then that area should not have a trouble marker 
			for(Area area : temp){
				
				
				//calls for the method which will give you the object at runtime for the area where building has to be placed
				if(!(area.getAreaName().equals(area_name)) && !(checkForTroubleMarkers(area_name).isTroubleMarkers()) ){
					// setting the player to the area that he wants to place a building
					// setting the buildings attrobute for that area to be true
					// thus setting up the dependency of WHICH PLAYER HAS BUILDING IN WHICH AREA
					this.setPlayerAreas(checkForTroubleMarkers(area_name));
					checkForTroubleMarkers(area_name).setBuildngs(true);
					checkForTroubleMarkers(area_name).setPlayersInThisAreas(this);
					
					this.setNumberOfBuildings(this.getNumberOfBuildings()-1);
					// update players own amount and deposit the cost of constructing building in the bank
					BoardGame.setBank(checkForTroubleMarkers(area_name).getCostOfArea());
					this.setPlayerAmount(getPlayerAmount() - checkForTroubleMarkers(area_name).getCostOfArea());

				}
				else{
					return "Cannot place a building";
				}
			}
		}
		else{
			// If the area does not exist with any player then just add that area object to current player 
			for(Area area : BoardGame.board_areas){
				if(area.getAreaName().equals(area_name) && checkForTroubleMarkers(area_name).isTroubleMarkers()==false ){
					// set corresponding building attributes of player
					this.setPlayerAreas(checkForTroubleMarkers(area_name));
					this.setNumberOfBuildings(this.getNumberOfBuildings()-1);
					// set area's building attribute
					checkForTroubleMarkers(area_name).setBuildngs(true);
					checkForTroubleMarkers(area_name).setPlayersInThisAreas(this);
					// update players own amount and deposit the cost of constructing building in the bank
					BoardGame.setBank(checkForTroubleMarkers(area_name).getCostOfArea());
					this.setPlayerAmount(getPlayerAmount() -checkForTroubleMarkers(area_name).getCostOfArea());
					break;
				}
			}
		}
		return "";
	}
	
	/**
	 * 
	 * @param area_name
	 * @return the current area object depending on the area name
	 */
	private Area checkForTroubleMarkers(String area_name) {
		
		
		for(Area a : BoardGame.board_areas)
			if(a.getAreaName().equals(area_name)){
				return a;
			}
		return null;
		}

	/**
	 * @return the playersPlayingCard
	 */
	public ArrayList<PlayerCard> getPlayersPlayingCard() {
		return playersPlayingCard;
	}

	/**
	 * @param playersPlayingCard the playersPlayingCard to set
	 */
	public void setPlayersPlayingCard(PlayerCard playersPlayingCard) {
		if(playersPlayingCard!=null)
			this.playersPlayingCard.add(playersPlayingCard);
		else
			System.out.println("Invalid playing card");
	}
}
