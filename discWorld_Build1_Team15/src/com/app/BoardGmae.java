package com.app;

import java.util.ArrayList;
import com.app.Player;

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
	
	
	public static void main(String[] args) {
		
		
		Player p1 = new Player("Red");
		System.out.println(p1.toString());
	}
}
