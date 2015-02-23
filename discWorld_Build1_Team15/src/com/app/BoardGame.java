package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.testcase.BoardGameClassTest;

/**
 * The Class BoardGame.
 *
 * @author Pratik
 *         Singleton Class to ensure that there exists only 1 board for game to be played on
 *         Start point of the game. This class has the main function
 *         This class initializes the collection of personality cards, 12 areas and collection of random event cards.
 */
public class BoardGame {

	/** The personality_cards. */
	public static ArrayList<String> personality_cards;

	/** The random_event_cards. */
	public ArrayList<String> random_event_cards;

	/** The board_areas. */
	public static ArrayList<Area> board_areas;

	/** The player_cards. */
	public static ArrayList<PlayerCard> player_cards;

	/** The bank. */
	private static int bank;
	// this arraylist acts as a store of current players in the game
	/** The players in game. */
	public static ArrayList<Player> playersInGame = new ArrayList<Player>();

	// Map of 12 areas
	/** The area_details. */
	public HashMap<String, String> area_details = new HashMap<String, String>();

	// single static instance
	/** The board_ game_ object. */
	private static BoardGame board_Game_Object = null;

	// private constructor allowing creation of only 1 object
	/**
	 * Instantiates a new board game.
	 */
	private BoardGame() {

		board_areas = new ArrayList<Area>();
		personality_cards = new ArrayList<String>(7);
		random_event_cards = new ArrayList<String>(12);
		player_cards = new ArrayList<PlayerCard>(101);
		bank = 120;
		init();

		for (String key : area_details.keySet()) {
			board_areas.add(new Area(key.toString(), area_details.get(key)));
		}
	}
	
	public static void setInstance(){
		if(BoardGame.board_Game_Object == null)
			board_Game_Object = new BoardGame();
		else
			BoardGame.board_Game_Object = null;
		
	}
	/**
	 * Gets the single instance of BoardGame.
	 *
	 * @return single instance of BoardGame
	 */
	public static BoardGame getInstance() {
		
		return board_Game_Object;
	}

	/**
	 * initializing data structure for storing 12 area names and cost
	 * also initializes 7 personality cards which will be given 1 to each player.
	 */
	private void init() {
		
		if((area_details.isEmpty()) && (personality_cards.isEmpty())
				&& (random_event_cards.isEmpty()) && (player_cards.isEmpty())){
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

		random_event_cards.add("Flood");
		random_event_cards.add("The Dragon");
		random_event_cards.add("Mysterious Murders");
		random_event_cards.add("Fog");
		random_event_cards.add("Riots");
		random_event_cards.add("Demons from the Dungeon Dimensions");
		random_event_cards.add("Subsidence");
		random_event_cards.add("Bloody Stupid Johnson");
		random_event_cards.add("Trolls");
		random_event_cards.add("Explosion");
		random_event_cards.add("Earthquake");
		random_event_cards.add("Fire");

		for(int i = 1 ; i<102;i++){
			if(i!=48){
				player_cards.add(new PlayerCard(i,"Green", " ", "Deck Pile"));
			}else{
				player_cards.add(new PlayerCard(i, "Brown", " ", "Deck Pile"));
				}
			}
		}
	}

	/** The random object. */
	static Random rand;

	/**
	 * takes the number of players and creates them.
	 *
	 * @param players the players
	 */
	public static void initiateNumberOfPlayers(int players) {

		String[] color = {"R", "G", "Y", "B"};

		if (players > 1) {

			for (int i = 0; i < players; i++) {

				Player player = new Player(color[i]); // creating new player
				assignPersonalityCards(player);
				player.placeMinion("The Shades");
				player.placeMinion("The Scours");
				player.placeMinion("Dolly Sisters");
				playersInGame.add(player); // add the player to the store
				// every player will be assigned 5 playing cards..first only green ones are to be used
				for(int j = 1; j < 6  ; j++){
					Random rand = new Random();
					int randInt = rand.nextInt(48);
					if(randInt!=0){

						if(BoardGame.player_cards.get(randInt).getColor().equalsIgnoreCase("green")){
							player.setPlayersPlayingCard(BoardGame.player_cards.get(j));
							BoardGame.player_cards.remove(j);                			
						}
					}
				}
				// temporary printing out to console from here
				ConsoleOutput.printOutPlayerState(player);
				ConsoleOutput.printOutInventory(player);

			}
		} else {
			System.out.println("Player cannot be less than 2");
		}
		ConsoleOutput.printOutGameBoardState();

	}
	
	public static void start(){
		
		BoardGame.setInstance();
//		board_Game_Object = BoardGame.getInstance();
		startGame();
	}
	/**
	 * start the game . connected to swings
	 */
	public static void startGame() {
		
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(BoardGameClassTest.class);
		System.out.println("Are there more than 1 board in use for the current game? "+result.wasSuccessful());
		rand = new Random();

		for (Area temp : BoardGame.board_Game_Object.board_areas) {
			if (temp.getAreaName().equalsIgnoreCase("The Scours") ||
					temp.getAreaName().equalsIgnoreCase("The Shades") ||
					temp.getAreaName().equalsIgnoreCase("Dolly Sisters")) {
				temp.setTroubleMarkers(true);
			}
		}
	}


	/**
	 * assigning personality cards to a player randomly.
	 *
	 * @param player the player
	 */
	private static void assignPersonalityCards(Player player) {

		int count = rand.nextInt(personality_cards.size());

		player.setWinningCondition(personality_cards.get(count));
		personality_cards.remove(count);

	}

	/**
	 * set total amount in bank.
	 *
	 * @param amt the new bank
	 */
	public static void setBank(int amt) {
		bank = amt;
	}

	/**
	 * get amount from bank.
	 *
	 * @return the bank
	 */
	public static int getBank() {
		return bank;
	}

	
	




}



