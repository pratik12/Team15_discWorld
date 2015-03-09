package com.app;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Player class holding properties of a player.
 * Board will have Players, hence Player is good candidate as a class
 * Also every Player will have a state as the game progresses.
 * @author Pratik
 *
 */
public class Player {

	/** The player_color. */
	private String player_color;

	// data structure to store minion data
	/** The minions. */
	private HashMap<String,ArrayList<String>> minions = new HashMap<String, ArrayList<String>>();
	// data structure to hold players playing card
	/** The players playing card. */
	private ArrayList<GreenPlayerCardEnum> playersPlayingCard = new ArrayList<GreenPlayerCardEnum>();

	/** The minion_ quantity. */
	private int minion_Quantity;

	/** The number_of_buildings. */
	private int number_of_buildings;

	/** The winning_condition. */
	private String winning_condition;

	/** The random_event_card. */
	public String random_event_card;

	/** The player_amount. */
	private int player_amount;

	/** The player_aid_card. */
	private String player_aid_card;

	/** The player_areas. */
	private ArrayList<Area> player_areas = new ArrayList<Area>();

    private int playerLoan;

	/**
	 * Constructor to set the properties of player.
	 *
	 * @param player_color the player_color
	 */	
	public Player(String player_color){

		setPlayerColor(player_color);
		setMinions(getPlayerColor(), "Players Pile");
		setMinionQuantity(12);
		setPlayerAmount(100);
		setNumberOfBuildings(6);
	}
	/**
	 * adds a building taking into consideration all the rules for setting a building
	 * updates the following data:
	 * 1.City area card vlaue from the area class
	 * 2.which area belongs to which player and vice versa.
	 * This function checks for if any area already has a building placed or not.
	 * Also for current player if he has placed any building in an area. This condition was added so that I can use 
	 * the same function to add buildings when the existing game is loaded
	 *
	 * @param area_name the area_name
	 * @return the string
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

		/**
		 * This loop checks for if any area already has a building placed or not.
		 * Also for current player if he has placed any building in an area. This condition was added so that I can use 
		 * the same function to add buildings when the existing game is loaded
		 */
		if(!(temp.isEmpty()) && (this.getPlayerAreas().size()!=0)){
			// always check that if you want to place a building in an area then that area should not have a trouble marker 
			for(Area area : temp){

				//calls for the method which will give you the object at runtime for the area where building has to be placed
				if(!(area.getAreaName().equals(area_name)) && !(checkForTroubleMarkers(area_name).isTroubleMarkers()) 
					&&	!(isThisAreaWithAnyOtherPlayer(area_name)) ){
					// setting the player to the area that he wants to place a building
					// setting the buildings attribute for that area to be true
					// thus setting up the dependency of WHICH PLAYER HAS BUILDING IN WHICH AREA
					this.setPlayerAreas(checkForTroubleMarkers(area_name));
					checkForTroubleMarkers(area_name).setBuildngs(true);
					checkForTroubleMarkers(area_name).setPlayersInThisAreas(this);
					checkForTroubleMarkers(area_name).setAreaCityCards(true);

					this.setNumberOfBuildings(this.getNumberOfBuildings()-1);
					// update players own amount and deposit the cost of constructing building in the bank
					BoardGame.setBank(BoardGame.getBank() + checkForTroubleMarkers(area_name).getCostOfArea());
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
				
				if(area.getAreaName().equals(area_name) && checkForTroubleMarkers(area_name).isTroubleMarkers()==false 
				&&	!(isThisAreaWithAnyOtherPlayer(area_name))	){
					// set corresponding building attributes of player
					this.setPlayerAreas(checkForTroubleMarkers(area_name));
					this.setNumberOfBuildings(this.getNumberOfBuildings()-1);
					// set area's building attribute
					checkForTroubleMarkers(area_name).setBuildngs(true);
					checkForTroubleMarkers(area_name).setPlayersInThisAreas(this);
					checkForTroubleMarkers(area_name).setAreaCityCards(true);

					// update players own amount and deposit the cost of constructing building in the bank
					BoardGame.setBank(BoardGame.getBank() + checkForTroubleMarkers(area_name).getCostOfArea());
					this.setPlayerAmount(getPlayerAmount() -checkForTroubleMarkers(area_name).getCostOfArea());
					break;
				}
				
			}
		}
		return "";
	}

	private boolean isThisAreaWithAnyOtherPlayer(String areaName) {

		
		for(Player p : BoardGame.playersInGame){
			
			if(p.getCityAreaCards().split(":")[0].trim().equals(areaName)){
				return true;
			}
		}
		return false;
	}
	/**
	 * places a minion in any location.
	 * Should be used to place a minion in any area when the game is in progress.
	 *
	 * @param location the location
	 */
	public void placeMinion(String location) {

		if (!(location.isEmpty()) && !(location.equalsIgnoreCase("Players Pile"))) {
			for(ArrayList<String> str : this.getMinions().values()){
				for(String s : str){
						this.setMinions(this.getPlayerColor(), location);
						break;
				}
				break;
			}
		} else if(!(location.isEmpty())){
			this.setMinions(this.getPlayerColor(), location);
			// updating PLAYERS minions quantity
			this.setMinionQuantity(this.getMinionQuantity() - 1);
		}
		else{
			System.out.println("Provide a minion location");
		}

	}
	/**
	 * Check for trouble markers and if you find a troublemarker in that area
	 * this method will return you that areas object reference.
	 *
	 * @param area_name the area_name
	 * @return the current area object depending on the area name
	 */
	public Area checkForTroubleMarkers(String area_name) {

		for(Area a : BoardGame.board_areas)
			if(a.getAreaName().equals(area_name)){
				return a;
			}
		return null;
	}


	/**
	 * Current inventory.
	 *
	 * @return String representation of players collection of minions, buildings, money and his color
	 */
	public String currentInventory(){

		return  "Player (" +getPlayerColor()+") current Inventory - " + "\n" 
				+ Integer.toString(getMinionQuantity()) + " Minion's , " + 
				Integer.toString(getNumberOfBuildings()) + " Building's , " + 
				Double.toString(getPlayerAmount()) + " Ankh-Morph Dollars " + "\n" +
				"City Area Cards : " + "\n" + getCityAreaCards() + "\n" +
				"Players Playing Cards :" + "\n" + getPlayingCards();
	}

	/**
	 * 
	 * @return string with names of players playing cards
	 */
	private String getPlayingCards() {
		String result = "";

		if(!(this.getPlayersPlayingCard().isEmpty())){
			result += this.playersPlayingCard.get(0).getColor();
			//for(PlayerCard p : this.getPlayersPlayingCard())
			//	result += p.getNumber() + ",";
		}
		return result;
	}

	/**
	 * Gets the city area cards.
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
	 * Gets the player areas.
	 *
	 * @return the player_areas
	 */
	public ArrayList<Area> getPlayerAreas() {
		return player_areas;
	}

	/**
	 * Sets the player areas.
	 *
	 * @param area_of_player the new player areas
	 */
	public void setPlayerAreas(Area area_of_player) {

		if(area_of_player!=null){
			this.player_areas.add(area_of_player);
		}
	}

	/**
	 * Gets the minion quantity.
	 *
	 * @return the minion quantity
	 */
	public int getMinionQuantity() {
		return minion_Quantity;
	}

	/**
	 * Sets the minion quantity.
	 *
	 * @param minion_Quantity the new minion quantity
	 */
	public void setMinionQuantity(int minion_Quantity) {
		this.minion_Quantity = minion_Quantity;
	}

	/**
	 * Gets the winning condition.
	 *
	 * @return the winning_condition
	 */
	public String getWinningCondition() {
		return winning_condition;
	}

	/**
	 * Sets the winning condition.
	 *
	 * @param winning_condition the winning_condition to set
	 */
	public void setWinningCondition(String winning_condition) {
		this.winning_condition = winning_condition;
	}

	/**
	 * Gets the player amount.
	 *
	 * @return the player_amount
	 */
	public int getPlayerAmount() {
		return player_amount;
	}

	/**
	 * Sets the player amount.
	 *
	 * @param d the player_amount to set
	 */
	public void setPlayerAmount(int d) {
		this.player_amount = d;
	}

	/**
	 * Gets the number of buildings.
	 *
	 * @return the number_of_buildings
	 */
	public int getNumberOfBuildings() {
		return number_of_buildings;
	}

	/**
	 * Sets the number of buildings.
	 *
	 * @param number_of_buildings the number_of_buildings to set
	 */
	public void setNumberOfBuildings(int number_of_buildings) {
		this.number_of_buildings = number_of_buildings;
	}

	/**
	 * Gets the player color.
	 *
	 * @return the player_color
	 */
	public String getPlayerColor() {
		return player_color;
	}

	/**
	 * Sets the player color.
	 *
	 * @param player_color the color of players piece to set
	 */
	public void setPlayerColor(String player_color) {
		this.player_color = player_color;
	}

	/**
	 * To string.
	 *
	 * @return String representation of players personality card and his color
	 */
	public String toString(){

		return  "Player (" + getPlayerColor()  +
				") is playing with " + getWinningCondition();

	}


	/**
	 * Gets the minions hashmap for each player
	 *
	 * @return the minions as Map
	 */
	public HashMap<String, ArrayList<String>> getMinions() {

		return minions;
	}


	/**
	 * Sets the minions for the player when the game starts and is used to load minions when the game is loaded
	 * from any arbitrary state.
	 *
	 * @param minion_color the minion_color
	 * @param minion_location the minion_location
	 */
	public void setMinions(String minion_color, String minion_location) {

		/**
		 * we first check that if the minion belongs to any player or not
		 */
		if(!(minion_color.isEmpty() && minion_location.isEmpty())){

			// checking if the minions hashmap has any entries
			if(minions.containsKey(this.getPlayerColor())){
				for(ArrayList<String> s : minions.values()){
					for(String str : s){
						if(str.equalsIgnoreCase("Players Pile")){
							minions.get(this.getPlayerColor()).add(minion_location);
							minions.remove(s);
							break;
						}
					}
					break;
				}
				}
			else{ // initializing each player with 12 minions

				minions.put(this.getPlayerColor(), new ArrayList<String>());
				for(int i=0; i <12; i++)
					minions.get(this.getPlayerColor()).add(minion_location);
			}
		}	
		else
			System.out.println("Minion color or location cannot be empty");
	}


	/**
	 * Gets the players playing card.
	 *
	 * @return the playersPlayingCard arraylist
	 */
	public ArrayList<GreenPlayerCardEnum> getPlayersPlayingCard() {
		return playersPlayingCard;
	}

	/**
	 * Sets the players playing card in his own playingcard arraylist
	 *
	 * @param playersPlayingCard the playersPlayingCard object to set
	 */
	public void setPlayersPlayingCard(GreenPlayerCardEnum playersPlayingCard) {
		if(playersPlayingCard!=null)
			this.playersPlayingCard.add(playersPlayingCard);
		else
			System.out.println("Invalid playing card");
	}

	/**
	 * @return the player_aid_card
	 */
	public String getPlayer_aid_card() {
		return player_aid_card;
	}

	/**
	 * @param player_aid_card the player_aid_card to set
	 */
	public void setPlayer_aid_card(String player_aid_card) {
		this.player_aid_card = player_aid_card;
	}
	
    public int getPlayerLoan() {
        return playerLoan;
    }
		
    public void setPlayerLoan(int playerLoan) {
        this.playerLoan = playerLoan;
	}
    
    public void resetPlayersPlayingCard(int i ){
		playersPlayingCard.remove(i);
	}
}
