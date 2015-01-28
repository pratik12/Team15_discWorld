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
 * Start point of the game. This class has the main function
 * This class initializes the collection of personality cards, 12 areas and collection of random event cards.
 */
public class BoardGame {
	
	public static ArrayList<String> personality_cards;
	public ArrayList<String> random_event_cards;
	private ArrayList<Area> board_areas;
	
	// this arraylist acts as a store of current players in the game
	public static ArrayList<Player> playersInGame = new ArrayList<Player>();

	// Map of 12 areas
	public HashMap<String,String> area_details = new HashMap<String,String>();
	
	// single static instance
	private static BoardGame board_Game_Object = new BoardGame();
	
	// private constructor allowing creation of only 1 object
	private BoardGame(){
		
		board_areas = new ArrayList<Area>(); 
		personality_cards = new ArrayList<String>(7);
		random_event_cards = new ArrayList<String>(12);
		init();
		
		for(String key : area_details.keySet()){
			board_areas.add(new Area(key.toString(),area_details.get(key)));
		}
	}
	
	public static BoardGame getInstance(){
		return board_Game_Object;
	}
	
	/**
	 * initializing datastructure for storing 12 area names and cost
	 * also initializes 7 personality cards which will be given 1 to each player
	 */
	private void init() {
		
		area_details.put("Dtolly Sisers", "6:1");
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
	static Player p2;
	static Random rand;
	static BoardGame game;
	
	public static void main(String[] args) {
		
		game = BoardGame.getInstance(); 
		 
		rand = new Random();
		// setting up 1 trouble marker in 3 pre specified areas of the board
		
		//System.out.println("Personality Cards : ");
		//for(String t : game.personality_cards)
			//System.out.println(t);
		
		p1 = new Player("R"); // creating new player
		
		playersInGame.add(p1); // add the player to the store
		
		p2 = new Player("Y");
		playersInGame.add(p2);
		
		assign_personality_cards(p1); // assign the personality card to the player
		
		assign_personality_cards(p2);

		
		
		placeMinion(p1,"Seven Sleepers"); // place a minion to any area for a player
		placeMinion(p1,"Seven Sleepers");
		placeMinion(p1,"Dolly Sisters");
		placeMinion(p2,"Dolly Sisters");
		System.out.println("-----------------------------------");
		System.out.println(p1.current_inventory());
		System.out.println("-----------------------------------");
		
		//System.out.println(game.board_areas.get(1).toString());
		System.out.println(p2.toString());
		System.out.println("-----------------------------------");
		System.out.println(p2.current_inventory());
		
		print_Out_PlayerState_And_Inventory();
		
		print_Out_GameBoard_State();
	}

	private static void print_Out_PlayerState_And_Inventory() {
		
		System.out.println(p1.toString());
		System.out.println(p1.current_inventory());
		System.out.println("-----------------------------------");
	}

	/**
	 * This method prints out the game board state
	 */
	private static void print_Out_GameBoard_State() {
		
		System.out.println("***** Game Board State *****");
		
		System.out.printf("%-22s%-18s%-18s%-18s%-18s%s\n","Areas","Minions","Trouble?","Buildings?","Demons","Trolls");
		System.out.println();
		for(Area temp : game.board_areas){
			if(temp.get_Area_name().equalsIgnoreCase("The Scours") ||
					temp.get_Area_name().equalsIgnoreCase("The Shades") ||
					temp.get_Area_name().equalsIgnoreCase("Dolly Sisters")){
				temp.set_Trouble_markers(true);
			}
			
			temp.to_String();
		}
		
	}

	/**
	 * assigning personailty cards to a player randomly
	 */
	private static void assign_personality_cards(Player player) {
		
		int count = rand.nextInt(6);
		
		player.set_Winning_condition(personality_cards.get(count));
		personality_cards.remove(count);
		// this area has been et to player manually right now. will change further
		//player.setPlayer_areas(game.board_areas.get(1));
		// as soon as you set a player to the area you should set that Player to the area
		//game.board_areas.get(1).setPlayersInThisAreas(player);
		// setting 2 minions manually in the AREA that this player holds
		
/*
		p1.setPlayer_areas(game.board_areas.get(1));
		// as soon as you set a player to the area you should set that Player to the area
		game.board_areas.get(1).setPlayersInThisAreas(p1);
		// setting 2 minions manually in 2 different AREAs that this player holds
		for(int i=0; i < p1.getPlayer_areas().size() ; i++){
			
			p1.getPlayer_areas().get(i).setMinions(2);
			if(p1.getPlayer_areas().get(i).get_Area_name().equals("Unreal Estate")){
				
			}
				
		}
		// updating PLAYERS minions quantity..unable to do it...
		count = rand.nextInt(7);
		p2.set_Winning_condition(personality_cards.get(count));
		personality_cards.remove(count);
		// this area has been et to player manually right now. will change further
		p2.setPlayer_areas(game.board_areas.get(3));
		// as soon as you set a player to the area you should set that Player to the area
		game.board_areas.get(3).setPlayersInThisAreas(p2);*/
		

		
	}

	private static void placeMinion(Player player, String location) {
		
		if(!(location.isEmpty())){
			
			player.setMinions(player.get_Player_color(), location);
			// updating PLAYERS minions quantity
			player.set_Minion_Quantity(player.get_Minion_Quantity() - 1);
		}
		else{
			System.out.println("Provide location for minion to be placed");
		}
		
	}
}
