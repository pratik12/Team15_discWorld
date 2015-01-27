package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import com.app.Player;

/**
 * 
 * @author Pratik
 * Singleton Class to ensure that there exists only 1 board for game to be played on 
 * Start point of the game. This class will have main function
 */
public class BoardGmae {
	
	public static ArrayList<String> personality_cards;
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

	static Player p1;
	static Random rand;
	static BoardGmae game;
	public static void main(String[] args) {
		
		 game = BoardGmae.getInstance();
		 rand = new Random();
		// setting up 1 trouble marker in 3 pre specified areas of the board
		System.out.println("Board Areas : ");
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
		
		 p1 = new Player("Red");

		assign_personality_cards();
		System.out.println(p1.toString());
		System.out.println(p1.current_inventory());
		System.out.println(game.board_areas.get(1).toString());
	}

	private static void assign_personality_cards() {
		
		p1.set_Winning_condition(personality_cards.get(rand.nextInt(7)));
		// this area has been et to player manually right now. will change further
		p1.setPlayer_areas(game.board_areas.get(1));
		// as soon as you set a player to the area you should set that Player to the area
		game.board_areas.get(1).setPlayersInThisAreas(p1);
		// setting 2 minions manually in the AREA that this player holds
		p1.getPlayer_areas().get(0).setMinions(2);
		// updating PLAYERS minions quantity
		p1.set_Minion_Quantity(p1.get_Minion_Quantity() - 2);
	}
}
