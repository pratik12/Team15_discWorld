package com.app;

import java.util.ArrayList;
import java.util.HashMap;

import com.app.Player;

/**
 * 
 * @author Pratik
 * Singleton Class to ensure that there exists only 1 board for game to be played on 
 * Start point of the game. This class will have main function
 */
public class BoardGmae {
	
	public ArrayList<String> personality_cards;
	public ArrayList<String> random_event_cards;
	private ArrayList<Area> board_areas;
	
	public HashMap<String,Integer> area_details = new HashMap<String,Integer>();
	
	// single static instance
	//private static BoardGmae board_Game_Object = new BoardGmae();
	
	// private constructor allowing creation of only 1 object
	public BoardGmae(){
		
		board_areas = new ArrayList<Area>(); 
		int count = 1;
		init();
		for(String key : area_details.keySet()){
			board_areas.add(new Area(key.toString(),area_details.get(key),count));
			count++;
		}
		
	}
	
	private void init() {
		
		area_details.put("Dolly Sisters", 6);
		area_details.put("Unreal Estate", 18);
		area_details.put("Dragons Landing", 12);
		area_details.put("Small Gods", 18);
		area_details.put("The Scours", 6);
		area_details.put("The Hippo", 12);
		area_details.put("The Shades", 6);
		area_details.put("Dimwell", 6);
		area_details.put("Longwell", 12);
		area_details.put("Isle of Gods", 12);
		area_details.put("Seven Sleepers", 18);
		area_details.put("Nap Hill", 12);
		
		
	}

	
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
		
		BoardGmae game = new BoardGmae();
		Player p1 = new Player("Red");
		System.out.println(p1.toString());
	}
}
