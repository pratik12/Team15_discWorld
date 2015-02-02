package com.app;

import java.util.ArrayList;

/**
 * 
 * Board will have 12 areas
 * @author Pratik
 *
 */
public class Area {

	private String area_name;
	private int area_number;
	private int cost_of_area;
	private boolean area_city_cards;
	private boolean trouble_markers;
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
		this.set_Area_name(name);
		this.set_Area_number(Integer.parseInt(costNum[1]));
		this.set_Cost_of_area(Integer.parseInt(costNum[0]));
		this.set_Area_city_cards(false);
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
	public void set_Area_name(String area_name) {
		this.area_name = area_name;
	}
	/**
	 * @return the area_number
	 */
	public int get_Area_number() {
		return area_number;
	}
	/**
	 * @param area_number the area_number to set
	 */
	public void set_Area_number(int area_number) {
		this.area_number = area_number;
	}
	/**
	 * @return the cost_of_area
	 */
	public int get_Cost_of_area() {
		return cost_of_area;
	}
	/**
	 * @param cost_of_area the cost_of_area to set
	 */
	public void set_Cost_of_area(int cost_of_area) {
		this.cost_of_area = cost_of_area;
	}
	/**
	 * @return the area_city_cards
	 */
	public boolean isArea_city_cards() {
		return area_city_cards;
	}
	/**
	 * @param area_city_cards the area_city_cards to set
	 */
	public void set_Area_city_cards(boolean area_city_cards) {
		this.area_city_cards = area_city_cards;
	}

	/**
	 * @return the trouble_markers
	 */
	public boolean is_Trouble_markers() {
		return trouble_markers;
	}

	/**
	 * @param trouble_markers the trouble_markers to set
	 */
	public void set_Trouble_markers(boolean trouble_markers) {
		this.trouble_markers = trouble_markers;
	}
	
	/**
	 * String representation of area object with its properties
	 */
	public void to_String(){
		System.out.printf("%-22s%-18s%-18s%-18s%-18s%s\n",this.getAreaName(),
				this.getMinionsForEveryPlayer(this.getAreaName()),this.is_Trouble_markers(),
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
					for(String minion_location : p.getMinions().get(p.get_Player_color())){
						
						if(minion_location.equals(area))
							result += "  " +p.get_Player_color();
					}
								
				}
		if(result!="none")
			return result;
		else
			return "none";
			
	}

	/**
	 * @return the minions
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
	 * @return the demons
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
	 * @return the trolls
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
	 * @return the buildngs
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
}
