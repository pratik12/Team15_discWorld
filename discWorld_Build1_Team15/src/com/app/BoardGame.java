package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.app.PlayingCardSystem.PlayerCard;
import com.app.PlayingCardSystem.ScrollUtility;
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
	private static ArrayList<GreenPlayerCardEnum> discardPilePlayerCards = new ArrayList<GreenPlayerCardEnum>();
	/** The area_details. */
	public JSONObject areaDetails = new JSONObject();
	// single static instance
	/** The board_ game_ object. */
	private static BoardGame board_Game_Object = null;

	// private constructor allowing creation of only 1 object
	/**
	 * Instantiates a new board game.
	 * @throws JSONException 
	 */
	private BoardGame() throws JSONException {

		board_areas = new ArrayList<Area>();
		personality_cards = new ArrayList<String>(7);
		random_event_cards = new ArrayList<String>(12);
		player_cards = new ArrayList<PlayerCard>(101);
		bank = 120;
		init();
		
		Iterator<?> keys = areaDetails.keys();
		while (keys.hasNext()) {
			String temp = getAdjacentAreaIDs(areaDetails, (String)keys.next());
			 
			board_areas.add(new Area( (String)keys.next(), temp.split(":")[0] ));
		}
	}
	
	public static void setInstance() throws JSONException{
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
	 * @throws JSONException 
	 */
	private void init() throws JSONException {
		
		if((areaDetails.length() == 0) && (personality_cards.isEmpty())
				&& (random_event_cards.isEmpty()) && (player_cards.isEmpty())){
		
		
		areaDetails.append("Dolly Sisters",new JSONObject().put("1", "6:12:3:2"));
		areaDetails.append("Unreal Estate",new JSONObject().put("2", "18:1:12:10:4"));
		areaDetails.append("Dragons Landing",new JSONObject().put("3", "12:12:3:2"));
		areaDetails.append("Small Gods",new JSONObject().put("4", "18:12:3:2"));
		areaDetails.append("The Scours",new JSONObject().put("5", "6:12:3:2"));
		areaDetails.append("The Hippo",new JSONObject().put("6", "12:12:3:2"));
		areaDetails.append("The Shades",new JSONObject().put("7", "6:12:3:2"));
		areaDetails.append("Dimwell",new JSONObject().put("8", "6:12:3:2"));
		areaDetails.append("Longwell",new JSONObject().put("9", "12:12:3:2"));
		areaDetails.append("Isle of Gods",new JSONObject().put("10", "12:12:3:2"));
		areaDetails.append("Seven Sleepers",new JSONObject().put("11", "18:12:3:2"));
		areaDetails.append("Nap Hill",new JSONObject().put("12", "12:12:3:2"));
		
		//getAdjacentAreaIDs(areaDetails, "Nap Hill");
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
		
		
		for(PlayerCard pc : PlayerCard.values()){
			player_cards.add(pc);
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
				ArrayList<Integer> temp = new ArrayList<Integer>();
				for(int j = 0; j < 5  ; j++){
					
					int randInt = shuffle(BoardGame.player_cards.size()-1);
					
					if(!(randInt > BoardGame.player_cards.size()-1)){
						
						if(BoardGame.player_cards.get(randInt).getColor().equalsIgnoreCase("green") && 
								BoardGame.player_cards.get(randInt) != null	 ){
								
							player.setPlayersPlayingCard(BoardGame.player_cards.get(randInt));
							BoardGame.player_cards.remove(randInt);                			
								}
					}
					else{
						j--;
						}
					
				//player.getPlayersPlayingCard().get(0).performAction(player.getPlayersPlayingCard().get(0).getName(),player);
				}
				// temporary printing out to console from here
				
				
				ConsoleOutput.printOutPlayerState(player);
				ConsoleOutput.printOutInventory(player);

			}
		} else {
			System.out.println("Player cannot be less than 2");
		}
		
		// this for loop is for testing playing card enum properties only purposes only 
		for(PlayerCard pc : PlayerCard.values()){
			if(pc.getName().equalsIgnoreCase("boggis"))
				ScrollUtility.TESTR.performAction(pc.getName(), BoardGame.playersInGame.get(0));
		}
		ConsoleOutput.printOutGameBoardState();

	}
	
	private static int shuffle(int i) {

		Random rand = new Random();
		return rand.nextInt(i);
		
		
	}

	public static void start() throws JSONException{
		
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

	public String getAdjacentAreaIDs(JSONObject areaJson, String areaName) throws JSONException{
		
		JSONArray jsonarray = areaJson.getJSONArray(areaName);
		
		JSONObject innerjson = jsonarray.getJSONObject(0);
		Iterator<?> keys = innerjson.keys();
		Object temp = null;
		String temps = null;
		while( keys.hasNext() ){
			
			 temp =  innerjson.get((String)keys.next());
			 temps = temp.toString();
	//		temp =  innerjson.get((String)keys.next());
			
			
		}
		return temps;
		
	}
	
	/**
	 * @return the discardPilePlayerCards
	 */
	public static ArrayList<GreenPlayerCardEnum> getDiscardPilePlayerCards() {
		return discardPilePlayerCards;
	}

	/**
	 * @param discardPilePlayerCards the discardPilePlayerCards to set
	 */
	public static void setDiscardPilePlayerCards(
			GreenPlayerCardEnum discardPilePlayerCards) {
		if(discardPilePlayerCards!=null)
			BoardGame.discardPilePlayerCards.add(discardPilePlayerCards);
	}
	
	




}



