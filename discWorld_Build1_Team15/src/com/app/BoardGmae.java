package com.app;

import java.util.ArrayList;

/**
 * 
 * @author Pratik
 *
 * Start point of the game. This class will have main function
 */
public class BoardGmae {
	
	public ArrayList<String> personality_cards;
	public ArrayList<String> random_event_cards;
	private ArrayList<Area> board_areas;
	
	/**
	 * @return the board_areas
	 */
	public ArrayList<Area> get_Board_areas() {
		return board_areas;
	}
	/**
	 * @param board_areas the board_areas to set
	 */
	public void set_Board_areas(ArrayList<Area> board_areas) {
		this.board_areas = board_areas;
	}
}
