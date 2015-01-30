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
	public static ArrayList<Area> board_areas;
	private static int bank;
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
		bank = 500;
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
		
		// iterating over all areas of the board initially to setup 3 trouble markers in 3 specific areas according ot the rule
				
	}

	static Player p1;
	static Player p2;
	static Random rand;
	static BoardGame game;
	
	public static void main(String[] args) {
		
		
		
		/*p1 = new Player("R"); // creating new player
		playersInGame.add(p1); // add the player to the store*/
		
		placeMinion(p1,"The Shades"); // place a minion in any area for a player
		p1.addBuilding("The Shades");
		p1.addBuilding("Seven Sleepers");
		p1.addBuilding("Longwell");
		p2 = new Player("Y");
		playersInGame.add(p2);
		p2.addBuilding("Dolly Sisters");
		
		p2.addBuilding("Seven Sleepers");
		
		assign_personality_cards(p1); // assign the personality card to the player
		assign_personality_cards(p2);
		
		placeMinion(p1,"Seven Sleepers");
		placeMinion(p1,"Dragons Landing");
		placeMinion(p2,"Nap Hill");
		placeMinion(p1,"Dolly Sisters");
		placeMinion(p2,"Dolly Sisters");
		
		print_Out_PlayerState(p1);
		print_Out_PlayerState(p2);
		print_Out_GameBoard_State();
		print_Out_Inventory(p1);
		print_Out_Inventory(p2);
		
		
		// functions for loading the game state from a file starts here
		// create a single function that will call other smaller functions to collectively update player and area
		// this single function would be called from swings controller
		// need to validate following data from file, create a new instance of gameboard
		// initially a gameboard will be created with all default data and then we would be overwriting it with required current data 
		// number of players and their personality cards they are playing with
		// update this in all required datastructures...playersInGame
		// update players inventory with first validating all input and 
		// populate player's private data members with data from file..use all setters to do this
		// then add buildings depending on city area card attributes
		// populate the area class with all the data
		// set arraylist pcoen691pconcordia@gmail.comcoen691pconcordia@gmail.comlayersInthisAreas with appropriate players who have placed buildings in the areas
		// print game board status finally to check if the game has been loaded correctly 
		
		
	}
	
	/**
	 * takes the number of players and creates them
	 * @param players
	 */
	public static void initiate_number_of_players(int players){
		
		String[] color = {"R","G","Y","B"};
		
		if(players>1){
			
			for(int i=0; i<players; i++){
				
				Player player = new Player(color[i]); // creating new player
				playersInGame.add(player); // add the player to the store
				
			}
		}
		else{
			System.out.println("Player cannot be less than 2");
		}
		
	}
	
	public static void startGame(){
		
		game = BoardGame.getInstance(); 
		
		rand = new Random();
		
		for(Area temp : BoardGame.board_areas){
			if(		temp.get_Area_name().equalsIgnoreCase("The Scours") ||
					temp.get_Area_name().equalsIgnoreCase("The Shades") ||
					temp.get_Area_name().equalsIgnoreCase("Dolly Sisters")){
				temp.set_Trouble_markers(true);
			}
		}
	}
	
	private static void print_Out_Inventory(Player player ){
		
		System.out.println(player.current_inventory());
		System.out.println();
	}
	/**
	 * output to the console the current status for every player
	 */
	private static void print_Out_PlayerState(Player player) {
		System.out.println(player.toString());
		System.out.println();
	}

	/**
	 * This method prints out the game board state
	 */
	private static void print_Out_GameBoard_State() {
		
		System.out.println("***** Game Board State *****");
		
		System.out.printf("%-22s%-18s%-18s%-18s%-18s%s\n","Areas","Minions","Trouble?","Buildings?","Demons","Trolls");
		System.out.println();
		
		// iterating over all areas of the board initially to setup 3 trouble markers in 3 specfic areas according ot the rule
		for(Area temp : BoardGame.board_areas){
			// printout areas
			temp.to_String();
		}
		System.out.println();

		
	}

	/**
	 * assigning personailty cards to a player randomly
	 */
	private static void assign_personality_cards(Player player) {
		
		int count = rand.nextInt(6);
		
		player.set_Winning_condition(personality_cards.get(count));
		personality_cards.remove(count);
		
	}

	/**
	 * places a minion in any location.
	 * @param player
	 * @param location
	 */
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
	
	public static void setBank(int amt){
		bank = amt;
	}
	
	public static int getBank(){
		return bank;
	}
	
	public void saveMap(){
		
		for(int i=0 ; i< playersInGame.size() ; i++)
		{
				Iterator it = (Iterator)playersInGame.get(i).entryset().iterator();
				
				while(it.hasNext())
				{
					p.toString();
					p.current_inventory();
				}
				
		}
				
		FileWriter writeFile = new FileWriter(saveFileName + ".txt", true);
		String player_col = new String ("Player_Colour|"+ player_color);
		writeFile.write(gamelevel);
		String winning_condition = new String ("Winning Condition|"+ winning_condition);
		writeFile.write(winning_condition);
		int minion_Quantity = new int ("Player_Colour|"+ player_color);
		String player_col = new String ("Player_Colour|"+ player_color);
		String player_col = new String ("Player_Colour|"+ player_color);
		String player_col = new String ("Player_Colour|"+ player_color);
		
		writeFile.write	();
		writeFile.flush();
		writeFile.close();
		
		
	}

	catch(Exception e)
	{
		e.printStackTrace();
		
	}

}
