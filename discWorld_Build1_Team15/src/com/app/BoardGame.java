package com.app;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

// TODO: Auto-generated Javadoc
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
    private static BoardGame board_Game_Object = new BoardGame();

    // private constructor allowing creation of only 1 object
    /**
     * Instantiates a new board game.
     */
    private BoardGame() {

        board_areas = new ArrayList<Area>();
        personality_cards = new ArrayList<String>(7);
        random_event_cards = new ArrayList<String>(12);
        player_cards = new ArrayList<PlayerCard>(101);
        bank = 500;
        init();

        for (String key : area_details.keySet()) {
            board_areas.add(new Area(key.toString(), area_details.get(key)));
        }
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
     * initializing datastructure for storing 12 area names and cost
     * also initializes 7 personality cards which will be given 1 to each player.
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
        
        for(int i =0 ; i<101;i++){
        	if(i!=47){
        		player_cards.add(new PlayerCard("Green", " ", "Deck Pile"));
        	}else{
        		player_cards.add(new PlayerCard("Brown", " ", "Deck Pile"));
        	}
        }
        
    }

    /** The p1. */
    static Player p1;
    
    /** The p2. */
    static Player p2;
    
    /** The rand. */
    static Random rand;
    
    /** The game. */
    static BoardGame game;

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void main(String[] args) throws IOException {

        startGame();

        p1 = new Player("R"); // creating new player
        playersInGame.add(p1); // add the player to the store

        p1.placeMinion("The Shades"); // place a minion in any area for a player
        p1.addBuilding("The Shades");
        p1.addBuilding("Seven Sleepers");
        p1.addBuilding("Longwell");
        p2 = new Player("Y");
        playersInGame.add(p2);
        p2.addBuilding("Dolly Sisters");

        p2.addBuilding("Seven Sleepers");

        assignPersonalityCards(p1); // assign the personality card to the player
        assignPersonalityCards(p2);

        p1.placeMinion("Seven Sleepers");
        p1.placeMinion("Dragons Landing");
        p2.placeMinion("Nap Hill");
        p1.placeMinion("Dolly Sisters");
        p2.placeMinion("Dolly Sisters");

        ConsoleOutput.printOutPlayerState(p1);
        ConsoleOutput.printOutPlayerState(p2);
        ConsoleOutput.printOutGameBoardState();
        ConsoleOutput.printOutInventory(p1);
        ConsoleOutput.printOutInventory(p2);

        FileManager.saveMap("output.txt");

    }

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
                playersInGame.add(player); // add the player to the store
                assignPersonalityCards(player);
                player.placeMinion("Seven Sleepers");
                player.addBuilding("Seven Sleepers");
                // every player will be assigned 5 playing cards..first only green ones are to be used
                
            }
        } else {
            System.out.println("Player cannot be less than 2");
        }

    }
    
    /**
     * start the game . connected to swings
     */
    public static void startGame() {

        game = BoardGame.getInstance();

        rand = new Random();

        for (Area temp : BoardGame.board_areas) {
            if (temp.getAreaName().equalsIgnoreCase("The Scours") ||
                    temp.getAreaName().equalsIgnoreCase("The Shades") ||
                    temp.getAreaName().equalsIgnoreCase("Dolly Sisters")) {
                temp.setTroubleMarkers(true);
            }
        }
    }

    
    /**
     * assigning personailty cards to a player randomly.
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

    
/**
 * @author Sanchit
 * if we can make a separate class for load and save it would be better even in the future builds (LoadSave class)
 * mentioned all the changes I think in the comments so that the program's current state is not impacted.
 * filename contains all the information about the game i.e the current status and the details about the players
 */
    /*
    public static void save(String filename, LoadState ls)
    {
    
    	FileOutputStream fout ; 
    	
    		try{
    	
    			fout = new FileOutputStream(filename);
   				ObjectOutputStream objout = new ObjectOutputStream(fout);
    			objout.writeObject(ls);
    	
    			}
    			
    		catch(Exception e) 
    		
    			{
    				e.printStackTrace();
    			}
    			
    
    }
     
     
     
    public static void load(String filename)
     {
     	LoadSave ls = null;
     	try{
     			FileInputStream fin = new FileInputStream(filename);
     			ObjectInputStream objin = new ObjectInputStream(fin);
     			Object obj = objin.readObject();
     			if(obj instanceof LoadSave) 
     			{
     				ls= (LoadSave)obj; 
     		
     			}
     
     			objin.close();
     		}
     	catch(Exception e)
     		{
     			e.printStackTrace();
     	 	}
     	 	
     	 return ls;	
     }
     */
}



