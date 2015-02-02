package com.app;

import java.util.ArrayList;

/**
 * 
 * Board will have 12 areas hence area is good candidate as a class
 * Also every area will have a state as the game progresses.
 * 
 * @author Pratik
 *
 */
public class Area {

	private String area_name;
	private int area_number;
	private int cost_of_area;
	private boolean area_city_cards;
	private boolean trouble_markers;
	private String troubleMarkerArea;
	private int demons;
	private int trolls;
	private int minions;
	private boolean buildngs;
	
	// list to keep track of which players own which area
	private ArrayList<Player> playersInThisAreas = new ArrayList<Player>();
	
	/**
	 * constructor initializing the areas with its name cost and number
	 * @param name
	 * @param cost_num
	 */
	public Area(String name, String cost_num){
		
		String[] costNum = cost_num.split(":");
		this.setAreaName(name);
		this.setAreaNumber(Integer.parseInt(costNum[1]));
		this.setCostOfArea(Integer.parseInt(costNum[0]));
		this.setAreaCityCards(false);
	}
	
	/**
	 * @return the area_name
	 */
	public String getAreaName() {
		return area_name;
	}
	/**
	 * @param accepts area_name which needs to be set
	 */
	public void setAreaName(String area_name) {
		this.area_name = area_name;
	}
	/**
	 * @return the area_number
	 */
	public int getAreaNumber() {
		return area_number;
	}
	/**
	 * @param area_number the area_number to set
	 */
	public void setAreaNumber(int area_number) {
		this.area_number = area_number;
	}
	/**
	 * @return the cost_of_area
	 */
	public int getCostOfArea() {
		return cost_of_area;
	}
	/**
	 * @param cost_of_area the cost_of_area to set
	 */
	public void setCostOfArea(int cost_of_area) {
		this.cost_of_area = cost_of_area;
	}
	/**
	 * @return the area_city_cards
	 */
	public boolean isAreaCityCards() {
		return area_city_cards;
	}
	/**
	 * @param area_city_cards the area_city_cards to set
	 */
	public void setAreaCityCards(boolean area_city_cards) {
		this.area_city_cards = area_city_cards;
	}

	/**
	 * @return the trouble_markers
	 */
	public boolean isTroubleMarkers() {
		return trouble_markers;
	}

	/**
	 * @param trouble_markers the trouble_markers to set
	 */
	public void setTroubleMarkers(boolean trouble_markers) {
		this.trouble_markers = trouble_markers;
	}
	
	/**
	 * String representation of area object with its properties
	 */
	public void to_String(){
		System.out.printf("%-22s%-18s%-18s%-18s%-18s%s\n",this.getAreaName(),
				this.getMinionsForEveryPlayer(this.getAreaName()),this.isTroubleMarkers(),
				this.isBuildngs(),this.getDemons(),this.getTrolls());
		
	}

	/**
	 * 
	 * @param area
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
	 * @return the number of minions
	 */
	public int getMinions() {
		return minions;
	}

	/**
	 * @param minions the minions to set
	 */
	public void setMinions(int minions) {
		this.minions = minions;
	}


	/**
	 * @return the playersInThisAreas
	 */
	public ArrayList<Player> getPlayersInThisAreas() {
		
		if(!this.playersInThisAreas.isEmpty())
			return playersInThisAreas;
		else 
			return null;
	}

	/**
	 * @param playersInThisAreas the playersInThisAreas to set
	 */
	public void setPlayersInThisAreas(Player playersInThisAreas) {
		this.playersInThisAreas.add(playersInThisAreas);
	}

	/**
	 * @return the number of demons
	 */
	public int getDemons() {
		return demons;
	}

	/**
	 * @param demons the demons to set
	 */
	public void setDemons(int demons) {
		this.demons = demons;
	}

	/**
	 * @return the number of trolls
	 */
	public int getTrolls() {
		return trolls;
	}

	/**
	 * @param trolls the trolls to set
	 */
	public void setTrolls(int trolls) {
		this.trolls = trolls;
	}

	/**
	 * @return if the buildngs exist in this area
	 */
	public boolean isBuildngs() {
		return buildngs;
	}

	/**
	 * @param buildngs the buildngs to set
	 */
	public void setBuildngs(boolean buildngs) {
		this.buildngs = buildngs;
	}

	/**
	 * @return the troubleMarkerArea
	 */
	public String getTroubleMarkerArea() {
		return troubleMarkerArea;
	}

	/**
	 * @param troubleMarkerArea the troubleMarkerArea to set
	 */
	public void setTroubleMarkerArea(String troubleMarkerArea) {
		this.troubleMarkerArea = troubleMarkerArea;
		this.trouble_markers = true;
	}
}
