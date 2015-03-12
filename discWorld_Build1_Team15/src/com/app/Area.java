package com.app;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * 
 * Board will have 12 areas hence area is good candidate as a class
 * Also every area will have a state as the game progresses.
 * 
 * @author Pratik
 *
 */
public class Area {

	/** The area_name. */
	private String area_name;

	/** The area_number. */
	private int area_number;

	/** The cost_of_area. */
	private int cost_of_area;

	/** The area_city_cards. */
	private boolean area_city_cards;

	/** The trouble_markers. */
	private boolean trouble_markers;

	/** The trouble marker area. */
	private String troubleMarkerArea;

	/** The demons. */
	private int demons;

	/** The trolls. */
	private int trolls;

	/** The minions. */
	private int minions;

	/** The buildngs. */
	private boolean buildngs;
	private ArrayList<String> minionColor = new ArrayList<String>();
	// list to keep track of which players own which area
	/** The players in this areas. */
	private ArrayList<Player> playersInThisAreas = new ArrayList<Player>();

	/**
	 * constructor initializing the areas with its name cost and number.
	 *
	 * @param name the name
	 * @param cost_num the cost_num
	 */
	public Area(String name, int areaNumber, String cost_num){

		this.setAreaName(name);
		//this.setAreaNumber(Integer.parseInt(costNum[1]));
		this.setAreaNumber(areaNumber);
		this.setCostOfArea(Integer.parseInt(cost_num));
		this.setAreaCityCards(false);
	}

	/**
	 * Gets the area name.
	 *
	 * @return the area_name
	 */
	public String getAreaName() {
		return area_name;
	}

	/**
	 * Sets the area name.
	 *
	 * @param area_name the new area name
	 */
	public void setAreaName(String area_name) {
		this.area_name = area_name;
	}

	/**
	 * Gets the area number.
	 *
	 * @return the area_number
	 */
	public int getAreaNumber() {
		return area_number;
	}

	/**
	 * Sets the area number.
	 *
	 * @param area_number the area_number to set
	 */
	public void setAreaNumber(int area_number) {
		this.area_number = area_number;
	}

	/**
	 * Gets the cost of area.
	 *
	 * @return the cost_of_area
	 */
	public int getCostOfArea() {
		return cost_of_area;
	}

	/**
	 * Sets the cost of area.
	 *
	 * @param cost_of_area the cost_of_area to set
	 */
	public void setCostOfArea(int cost_of_area) {
		this.cost_of_area = cost_of_area;
	}

	/**
	 * Checks if is area city cards.
	 *
	 * @return the area_city_cards
	 */
	public boolean isAreaCityCards() {
		return area_city_cards;
	}

	/**
	 * Sets the area city cards.
	 *
	 * @param area_city_cards the area_city_cards to set
	 */
	public void setAreaCityCards(boolean area_city_cards) {
		this.area_city_cards = area_city_cards;
	}

	/**
	 * Checks if is trouble markers.
	 *
	 * @return the trouble_markers
	 */
	public boolean isTroubleMarkers() {
		return trouble_markers;
	}

	/**
	 * Sets the trouble markers.
	 *
	 * @param trouble_markers the trouble_markers to set
	 */
	public void setTroubleMarkers(boolean trouble_markers) {
		this.trouble_markers = trouble_markers;
	}

	/**
	 * String representation of area object with its properties.
	 */
	public void to_String(){
		System.out.printf("%-22s%-18s%-18s%-18s%-18s%s\n",this.getAreaName(),
				this.getMinionsForEveryPlayer(this.getAreaName()),this.isTroubleMarkers(),
				this.isBuildngs(),this.getDemons(),this.getTrolls());

	}

	/**
	 * Gets the minions for every player.
	 *
	 * @param area the area
	 * @return the minions placed by players in any area.
	 */
	private String getMinionsForEveryPlayer(String area) {

		String result = "";

		// iterating over the NUMBER OF MINIONS THAT PLAYER HAS PLACED IN HIS AREA. 
		for(Player p : BoardGame.playersInGame){
			// checking for every minion location for all the players
			for(String minion_location : p.getMinions().get(p.getPlayerColor())){

				if(minion_location.equals(area))
					result += "  " +p.getPlayerColor();
			}

		}
		if(result!="none")
			return result;
		else
			return "none";

	}

	/**
	 * Gets the minions.
	 *
	 * @return the number of minions
	 */
	public int getMinions() {
		return minions;
	}

	/**
	 * Sets the minions.
	 *
	 * @param minions the minions to set
	 */
	public void setMinions(int minions) {
		this.minions = minions;
	}


	/**
	 * Gets the players in this areas.
	 *
	 * @return the playersInThisAreas
	 */
	public ArrayList<Player> getPlayersInThisAreas() {

		if(!this.playersInThisAreas.isEmpty())
			return playersInThisAreas;
		else 
			return null;
	}

	/**
	 * Sets the players in this areas.
	 *
	 * @param playersInThisAreas the playersInThisAreas to set
	 */
	public void setPlayersInThisAreas(Player playersInThisAreas) {
		this.playersInThisAreas.add(playersInThisAreas);
	}

	/**
	 * Gets the demons.
	 *
	 * @return the number of demons
	 */
	public int getDemons() {
		return demons;
	}

	/**
	 * Sets the demons.
	 *
	 * @param demons the demons to set
	 */
	public void setDemons(int demons) {
		this.demons = demons;
	}

	/**
	 * Gets the trolls.
	 *
	 * @return the number of trolls
	 */
	public int getTrolls() {
		return trolls;
	}

	/**
	 * Sets the number of trolls.
	 *
	 * @param trolls the trolls to set
	 */
	public void setTrolls(int trolls) {
		this.trolls = trolls;
	}

	/**
	 * Checks if area has buildngs.
	 *
	 * @return if the buildngs exist in this area
	 */
	public boolean isBuildngs() {
		return buildngs;
	}

	/**
	 * Sets the buildng variable to true/false
	 *
	 * @param buildngs the buildngs to set
	 */
	public void setBuildngs(boolean buildngs) {
		this.buildngs = buildngs;
	}

	/**
	 * Gets the trouble marker area.
	 *
	 * @return the troubleMarkerArea
	 */
	public String getTroubleMarkerArea() {
		return troubleMarkerArea;
	}

	/**
	 * Sets the trouble marker area.
	 *
	 * @param troubleMarkerArea the troubleMarkerArea to set
	 */
	public void setTroubleMarkerArea(String troubleMarkerArea) {
		this.troubleMarkerArea = troubleMarkerArea;
		this.trouble_markers = true;
	}
	
public void removeBuilding(String areaName){
		
		for(Player p : BoardGame.playersInGame){
			for(Area a : p.getPlayerAreas()){
				if(a.getAreaName().equalsIgnoreCase(areaName)){
					a.setBuildngs(false);
					p.getPlayerAreas().remove(a);
					BoardGame.setCityAreaCardRepo(p.getCityReaCardFromAreaName(areaName));
					p.getCityAreaCardsStore().remove(p.getCityReaCardFromAreaName(areaName));
					break;
				}
				
			}
		}
		
	}

public ArrayList<String> getMinionColor() {
	return minionColor;
}

/**
 * Sets the minions.
 *
 * @param minions the minions to set
 */
public void setMinionColor(String minions) {
	this.minionColor.add(minions);
}
}
