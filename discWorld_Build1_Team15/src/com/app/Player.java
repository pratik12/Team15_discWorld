package com.app;

import com.app.CityAreaCardSystem.CityAreaCardEnum;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONException;

/**
 * Player class holding properties of a player. Board will have Players, hence
 * Player is good candidate as a class Also every Player will have a state as
 * the game progresses.
 * 
 * @author Pratik
 * 
 */
public class Player {

	/** The player_color. */
	private String player_color;

	// data structure to store minion data
	/** The minions. */
	private HashMap<String, ArrayList<String>> minions = new HashMap<String, ArrayList<String>>();
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
	private int points;

	private ArrayList<CityAreaCardEnum> cityAreaCardsStore = new ArrayList<CityAreaCardEnum>();

	/**
	 * Constructor to set the properties of player.
	 * 
	 * @param player_color
	 *            the player_color
	 */
	public Player(String player_color) {

		setPlayerColor(player_color);
		setMinions(getPlayerColor(), "");
		setMinionQuantity(12);
		setPlayerAmount(10);
		setNumberOfBuildings(6);
	}

	/**
	 * adds a building taking into consideration all the rules for setting a
	 * building updates the following data: 1.City area card vlaue from the area
	 * class 2.which area belongs to which player and vice versa. This function
	 * checks for if any area already has a building placed or not. Also for
	 * current player if he has placed any building in an area. This condition
	 * was added so that I can use the same function to add buildings when the
	 * existing game is loaded
	 * 
	 * @param area_name
	 *            the area_name
	 * @return the string
	 */

	public String addBuilding(String area_name) {

		///displayAreasWithPlayersMinion(this);

		/**
		 * This loop checks for if any area already has a building placed or
		 * not. Also for current player if he has placed any building in an
		 * area. This condition was added so that I can use the same function to
		 * add buildings when the existing game is loaded
		 */
		if ((this.getPlayerAreas().size() != 0) ) {
			// always check that if you want to place a building in an area then
			// that area should not have a trouble marker
			for (Area area : BoardGame.board_areas) {

				// calls for the method which will give you the object at
				// runtime for the area where building has to be placed
				if ((area.getAreaName().equalsIgnoreCase(area_name))
						&& !(getAreaInstanceFromAreaName(area_name)
								.isTroubleMarkers())
						&& !(isThisAreaWithAnyOtherPlayer(this, area_name))
						&& doYouHaveMinionInThisArea(this, area_name)
						&& checkforMoney(area_name) 
						&& checkBldgwithyou(this,area_name)) {
					// setting the player to the area that he wants to place a
					// building
					// setting the buildings attribute for that area to be true
					// thus setting up the dependency of WHICH PLAYER HAS
					// BUILDING IN WHICH AREA
					performBuildingOperations(this, area_name, area);
					break;
				}

			}
		} else {
			// If the area does not exist with any player then just add that
			// area object to current player
			for (Area area : BoardGame.board_areas) {

				if (area.getAreaName().equalsIgnoreCase(area_name)
						&& getAreaInstanceFromAreaName(area_name)
								.isTroubleMarkers() == false
						&& !(isThisAreaWithAnyOtherPlayer(this, area_name))
						&& doYouHaveMinionInThisArea(this, area_name)
						&& checkforMoney(area_name) 
						&& checkBldgwithyou(this,area_name)) {
					// set corresponding building attributes of player
					performBuildingOperations(this, area_name, area);
					break;
				}

			}
		}
		
		return "";
	}

	private boolean checkBldgwithyou(Player player, String area_name) {
		boolean b = true;
		for(CityAreaCardEnum cec : player.getCityAreaCardsStore()){
			if(cec.getareaName().trim().equalsIgnoreCase(area_name)){
				b = false;
			}
		}
		return b;
	}

	/*
	 * public void addBuilding(String area_name){
	 *//**
	 * This loop checks for if any area already has a building placed or not.
	 * Also for current player if he has placed any building in an area. This
	 * condition was added so that I can use the same function to add buildings
	 * when the existing game is loaded
	 */
	/*
	 * 
	 * if(!(getAreaInstanceFromAreaName(area_name).isTroubleMarkers()) &&
	 * !(isThisAreaWithAnyOtherPlayer(this,area_name)) &&
	 * doYouHaveMinionInThisArea(this,area_name) && checkforMoney(area_name)){
	 * 
	 * performBuildingOperations(this,area_name); } else{ System.out.println(
	 * "You cannot place a building here.Maybe you dont have a minion yet OR there is trouble marker here OR No funds to purchase the building"
	 * ); } }
	 */

	private void performBuildingOperations(Player player, String area_name,
			Area area) {

		// setting the player to the area that he wants to place a building
		// setting the buildings attribute for that area to be true
		// thus setting up the dependency of WHICH PLAYER HAS BUILDING IN WHICH
		// AREA
		getAreaInstanceFromAreaName(area_name).setBuildngs(true);
		getAreaInstanceFromAreaName(area_name).setPlayersInThisAreas(this);
		getAreaInstanceFromAreaName(area_name).setAreaCityCards(true);
		// setting players own players area
		this.setPlayerAreas(getAreaInstanceFromAreaName(area_name));
		// removing the city area card from board
		BoardGame.getCityAreaCardRepo().remove(
				this.getCityReaCardFromAreaName(area_name));
		// giving the player this city area card
		this.setCityAreaCardsStore(this.getCityReaCardFromAreaName(area_name));
		this.setNumberOfBuildings(getNumberOfBuildings() - 1);
		// update players own amount and deposit the cost of constructing
		// building in the bank
		BoardGame.setBank(BoardGame.getBank()
				+ getAreaInstanceFromAreaName(area_name).getCostOfArea());
		this.setPlayerAmount(getPlayerAmount()
				- getAreaInstanceFromAreaName(area_name).getCostOfArea());

	}

	public void displayAreasWithPlayersMinion(Player player) {

		System.out.printf("%-15s\n", "Area Name Having Minion");
		int count =1;
		for (Area a : BoardGame.board_areas) {
			if (doYouHaveMinionInThisArea(this, a.getAreaName())) {
				System.out.printf("%-1s%-1s%-3s%-15s\n",count," ",player.getPlayerColor().toUpperCase(), a.getAreaName());
				BoardGame.pieceNumberAreaList.add(""+count+":"+a.getAreaName());
				count++;
			}

		}

	}
	/*WorkAround Fix */
	public void removeBuildingForPlayer(int index) {
		player_areas.remove(index);
	}
	
	public boolean isThisAreaWithAnyOtherPlayer(Player pl, String areaName) {

		ArrayList<String> temp = new ArrayList<String>();

		for (Player p : BoardGame.playersInGame) {
			if (!(p.getPlayerColor().equalsIgnoreCase(pl.getPlayerColor()))) {
				String[] t = p.getCityAreaCards().split(":");
				for (int i = 0; i < t.length; i++)
					temp.add(t[i]);
			}
		}

		for (String str : temp) {
			if (str.equalsIgnoreCase(areaName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * places a minion in any location. Should be used to place a minion in any
	 * area when the game is in progress.
	 * 
	 * @param location
	 *            the location
	 * @throws JSONException 
	 */
	public void placeMinion(String location) throws JSONException {

		if (!(location.isEmpty())) {
			
			if(doYouHaveMinionInThisArea(this, location)){
				
				ArrayList<Area> temp = BoardGame.getAdjacentAreasForAnArea(location);
				System.out.println("You can place minions in any of these areas");
				System.out.printf("%10s\n","Area Names");
				int count =1;
				System.out.printf("%2s%2s%15s\n",count,"  ",location);
				BoardGame.pieceNumberAreaList.add(""+count+":"+location);
				count++;
				for(Area a : temp){
					System.out.printf("%2s%2s%15s\n",count,"  ",a.getAreaName());
					BoardGame.pieceNumberAreaList.add(""+count+":"+a.getAreaName());
					count++;
				}
				String ar = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Choose an area to place a minion:nul");
				String a = BoardGame.getPieceNumberList(ar);
				this.setMinions(this.getPlayerColor(), a);
				Area placedarea = this.getAreaInstanceFromAreaName(a);
				placedarea.setMinionColor(a); // Sanchit Fix, Minion Count not getting updated
				if(!(placedarea.isTroubleMarkers()) && this.totalMinionsInAreaForAllPlayers(placedarea.getAreaName())>1) //Sanchit Fix
					placedarea.setTroubleMarkers(true);
				System.out.println("Minion palced in "+placedarea.getAreaName());
			}else{
				this.setMinions(this.getPlayerColor(), location);
				Area placedarea = this.getAreaInstanceFromAreaName(location);
				placedarea.setMinionColor(placedarea.getAreaName());
				if(!(placedarea.isTroubleMarkers()) && this.totalMinionsInAreaForAllPlayers(placedarea.getAreaName())>1) //Sanchit Fix
					placedarea.setTroubleMarkers(true);
				System.out.println("Minion palced in "+location);
			}
			
		} else {
			System.out.println("Provide a minion location");
			
		}

	}

	/**
	 * Check for trouble markers and if you find a troublemarker in that area
	 * this method will return you that areas object reference.
	 * 
	 * @param area_name
	 *            the area_name
	 * @return the current area object depending on the area name
	 */
	public Area getAreaInstanceFromAreaName(String area_name) {

		for (Area a : BoardGame.board_areas)
			if (a.getAreaName().equalsIgnoreCase(area_name)) {
				return a;
			}
		return null;
	}

	/**
	 * Current inventory.
	 * 
	 * @return String representation of players collection of minions,
	 *         buildings, money and his color
	 */
	public String currentInventory() {

		return "Player (" + getPlayerColor() + ") current Inventory - " + "\n"
				+ Integer.toString(12 - getMinionQuantity()) + " Minion's , "
				+ Integer.toString(getNumberOfBuildings()) + " Building's , "
				+ Double.toString(getPlayerAmount()) + " Ankh-Morph Dollars "
				+ "\n" + "City Area Cards : " + "\n" + getCityAreaCards()
				+ "\n" + "Player Loan :" + "\n" + getPlayerLoan() + "\n";

	}

	/**
	 * 
	 * @return string with names of players playing cards
	 */
	private String getPlayingCards() {
		String result = "";

		if (!(this.getPlayersPlayingCard().isEmpty())) {
			result += this.playersPlayingCard.get(0).getColor();
			// for(PlayerCard p : this.getPlayersPlayingCard())
			// result += p.getNumber() + ",";
		}
		return result;
	}

	/**
	 * Gets the city area cards.
	 * 
	 * @return city area card for each player, if the player has no buildings
	 *         placed the returns "Player has placed no building"
	 */
	private String getCityAreaCards() {

		String result = "";

		if (!this.getPlayerAreas().isEmpty()) // getPlayerAreas returns the
												// arraylist with area objects
			// only if player has placed a building in that area
			for (Area a : this.getPlayerAreas())
				result += a.getAreaName() + " : ";

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
	 * @param area_of_player
	 *            the new player areas
	 */
	public void setPlayerAreas(Area area_of_player) {

		try {
			if (area_of_player != null) {
				this.player_areas.add(area_of_player);
			}
		} catch (NullPointerException e) {
			throw e;
		}
	}

	/**
	 * Gets the minion quantity for player on board.
	 * 
	 * @return the minion quantity
	 */
	public int getMinionQuantity() {
		int temp = 0;
		for (ArrayList<String> str : minions.values())
			for (String s : str)
				if (!(s.equalsIgnoreCase("")))
					temp++;
		return temp;
	}

	/**
	 * Sets the minion quantity.
	 * 
	 * @param minion_Quantity
	 *            the new minion quantity
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
	 * @param winning_condition
	 *            the winning_condition to set
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
	 * @param d
	 *            the player_amount to set
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
	 * @param number_of_buildings
	 *            the number_of_buildings to set
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
	 * @param player_color
	 *            the color of players piece to set
	 */
	public void setPlayerColor(String player_color) {
		this.player_color = player_color;
	}

	/**
	 * To string.
	 * 
	 * @return String representation of players personality card and his color
	 */
	public String toString() {

		return "Player (" + getPlayerColor() + ") is playing with "
				+ getWinningCondition();

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
	 * Sets the minions for the player when the game starts and is used to load
	 * minions when the game is loaded from any arbitrary state.
	 * 
	 * @param minion_color
	 *            the minion_color
	 * @param minion_location
	 *            the minion_location
	 */
	public void setMinions(String minion_color, String minion_location) {

		/**
		 * we first check that if the minion belongs to any player or not
		 */
		if (!(minion_color.isEmpty())) {

			// checking if the minions hashmap has any entries
			if (this.minions.containsKey(this.getPlayerColor())) {
				for (ArrayList<String> s : this.minions.values()) {
					for (String str : s) {
						s.remove(str);
						this.minions.get(this.getPlayerColor()).add(
								minion_location);
						this.setMinionQuantity(this.getMinionQuantity() - 1);
						break;

					}
					break;
				}
			} else { // initializing each player with 12 minions

				this.minions.put(this.getPlayerColor(), new ArrayList<String>());
				for (int i = 0; i < 12; i++)
					this.minions.get(this.getPlayerColor()).add(minion_location);
			}
		} else
			System.out.println("Minion color or location cannot be empty");
	}

	/**
	 * Remove a Specific Minion from the List
	 */
	public void removeMinionInList(String minionLocation) {
		this.getMinions().get(this.getPlayerColor()).remove(minionLocation);

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
	 * @param playersPlayingCard
	 *            the playersPlayingCard object to set
	 */
	public void setPlayersPlayingCard(GreenPlayerCardEnum playersPlayingCard) {
		if (playersPlayingCard != null)
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
	 * @param player_aid_card
	 *            the player_aid_card to set
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void resetPlayersPlayingCard(int i) {
		playersPlayingCard.remove(i);
	}

	/**
	 * thos method will exchange the personality card for a player
	 */
	public void exchangePersonalityCard() {

		Random ran = new Random();
		int n = ran
				.nextInt(BoardGame.getInstance().personality_cards.size() - 1);
		this.setWinningCondition(BoardGame.getInstance().personality_cards
				.get(n));
	}

	/**
	 * @return the cityAreaCardsStore
	 */
	public ArrayList<CityAreaCardEnum> getCityAreaCardsStore() {
		return cityAreaCardsStore;
	}

	/**
	 * @param cityAreaCardsStore
	 *            the cityAreaCardsStore to set
	 */
	public void setCityAreaCardsStore(CityAreaCardEnum cityAreaCardsStore) {

		this.cityAreaCardsStore.add(cityAreaCardsStore);
	}

	/**
	 * returns the city area card instance from area name inputted in string
	 * format
	 * 
	 * @param areaName
	 * @return
	 */
	public CityAreaCardEnum getCityReaCardFromAreaName(String areaName) {

		CityAreaCardEnum cacard = null;
		for (CityAreaCardEnum a : CityAreaCardEnum.values()) {
			if (a.getareaName().equalsIgnoreCase(areaName.trim())) {
				cacard = a;
				break;
			}

		}
		return cacard;
	}

	/**
	 * Method that retruns true or false to check if a player has a minion in
	 * this area
	 * 
	 * @param cuurPlayer
	 * @param areaName
	 * @return
	 */
	public boolean doYouHaveMinionInThisArea(Player cuurPlayer, String areaName) {

		for (ArrayList<String> str : cuurPlayer.getMinions().values()) {
			for (String s : str) {
				if (!(s.equalsIgnoreCase(""))) {
					if (areaName.equalsIgnoreCase(s))
						return Boolean.TRUE;
				}
			}
		}

		return Boolean.FALSE;
	}

	/**
	 * returns the total number of minions in an area for all players
	 * 
	 * @param areaName
	 * @return
	 */

	public int totalMinionsInAreaForAllPlayers(String areaName) {
		int temp = 0;
		for (Area a : BoardGame.board_areas) {
			if (a.getAreaName().equalsIgnoreCase(areaName)) {
				return a.getMinionColor().size();
			}
		}
		return temp;
	}

	// added this functionality to make sure that the player has sufficient
	// funds - Sanchit
	private boolean checkforMoney(String areaName) {
		for (Area a : BoardGame.board_areas) {
			if (a.getAreaName().equals(areaName.trim())) {
				if (this.getPlayerAmount() > a.getCostOfArea())
					return true;
			}
		}

		return false;
	}

	public String actionsAvailableCityAreaCard(String areaname) {

		return "";

	}

}
