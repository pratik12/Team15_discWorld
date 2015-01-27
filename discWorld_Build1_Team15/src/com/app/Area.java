package com.app;

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
	public String get_Area_name() {
		return area_name;
	}
	/**
	 * @param area_name the area_name to set
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
	
	public String toString(){
		return  "Area Number : " + Integer.toString(this.get_Area_number()) +
				" Area Name : " + this.get_Area_name() +
				" Area Cost : " + Integer.toString(this.get_Cost_of_area()) +
				" Has a Trouble Marker : " + this.is_Trouble_markers();
				
	}
}
