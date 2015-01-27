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
	
	public ArrayList<Integer> sub_area_details = new ArrayList<Integer>();
	public HashMap<String,String> area_details = new HashMap<String,String>();
	
	// single static instance
	private static BoardGmae board_Game_Object = new BoardGmae();
	
	// private constructor allowing creation of only 1 object
	private BoardGmae(){
		
		board_areas = new ArrayList<Area>(); 
		personality_cards = new ArrayList<String>(7);
		random_event_cards = new ArrayList<String>(12);
		init();
		
		for(String key : area_details.keySet()){
			board_areas.add(new Area(key.toString(),area_details.get(key)));
		}
	}
	
	public static BoardGmae getInstance(){
		return board_Game_Object;
	}
	
	/**
	 * initializing datastructure for storing area names and cost
	 */
	private void init() {
		
		area_details.put("Dolly Sisters", "6:1");
		area_details.put("Unreal Estate", "18:2");
		area_details.put("Dragons Landing", "12:3");
		area_details.put("Small Gods", "18:4");
		area_details.put("The Scours", "6:5");
		area_details.put("The Hippo", "12:6");
		area_details.put("The Shades", "6:7");
		area_details.put("Dimwell", "6:8");
		area_details.put("Longwell", "12:9");
		area_details.put("Isle of Gods", "12:10");
		area_details.put("Seven Sleepers", "18:11");
		area_details.put("Nap Hill", "12:12");
		
		personality_cards.add("Lord Vetinari");
		personality_cards.add("Lord Selachii");
		personality_cards.add("Lord Rust");
		personality_cards.add("Lord de Worde");
		personality_cards.add("Dragon King of Arms");
		personality_cards.add("Chryoprase");
		personality_cards.add("Commander Vimes");
	}

		
	public static void main(String[] args) {
		
		BoardGmae game = BoardGmae.getInstance();
		
		// setting up 1 trouble marker in 3 pre specified areas of the board
		for(Area temp : game.board_areas){
			if(temp.get_Area_name().equalsIgnoreCase("The Scours") ||
					temp.get_Area_name().equalsIgnoreCase("The Shades") ||
					temp.get_Area_name().equalsIgnoreCase("Dolly Sisters")){
				temp.set_Trouble_markers(true);
			}
			
			System.out.println(temp.toString());
		}
		
		System.out.println("Personality Cards : ");
		for(String t : game.personality_cards)
			System.out.println(t);
		
		
		Player p1 = new Player("Red");
		System.out.println(p1.toString());
	}
}
