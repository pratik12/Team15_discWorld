package com.app;

import javax.swing.*;

import org.json.JSONException;

import com.app.PlayingCardSystem.GreenPlayerCardEnum;

import java.io.*;
import java.util.ArrayList;

/**
 * This class adds modularity for separation of concerns.
 * file management module
 * Contains methods for loading a game from an existing state
 * Contains methods to save a game
 *
 * @author Pratik
 */
public class FileManager {


    /**
     * functionality to save state of game
     * This method connects the GUI with the game control	.
     *
     * @param filePath to where you want to store the game
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void saveMap(String filePath) throws IOException {
        if (isFileNameValid(filePath)) {
            FileWriter writeFile = new FileWriter(filePath);
            // String eol = System.getProperty("line.separator");
            BufferedWriter out = new BufferedWriter(writeFile);
            out.write("Players : " + BoardGame.playersInGame.size() + "\n");//total number of players
            for (Area area : BoardGame.board_areas)
                if (area.isTroubleMarkers())
                    out.write("TroubleMarkers-" + area.getAreaName() + ":");
            out.write("\n");
            for (Player player : BoardGame.playersInGame) {
                out.write(player.getPlayerColor() + ":"); // player color
                out.write(player.getWinningCondition() + ":"); // personality card
                out.write(player.getMinionQuantity() + ":"); // get number of minions

                // every player has a minion in hashmap datastructure
                if (player.getMinions().size() != 0) {
                    // retrieving the arraylist data structure for each minion
                    for (ArrayList<String> str : player.getMinions().values()) {

                        for (int i = 0; i < str.size(); i++) {
                            // only taking out those names of areas where the minion is placed
                            if (!(str.get(i).equals("Players Pile"))) {
                                out.write("MINION-" + str.get(i) + ":");
                            }
                        }
                    }
                }
                out.write(player.getNumberOfBuildings() + ":");
                boolean count = false;
                // getting the area names where the player has  build a building
                for (Area area : player.getPlayerAreas()) {

                    out.write("BUILDING-" + area.getAreaName() + ":");
                    count = true;
                }
                if (!count)
                    out.write("BUILDING-None" + ":");
                // playing cards
                out.write("PLAYING CARD-" + player.getPlayersPlayingCard().get(0).getColor() + ":");
                for (int i = 0; i < player.getPlayersPlayingCard().size(); i++) {

                    out.write(player.getPlayersPlayingCard().get(i).getColor() + "-" + 
                    		player.getPlayersPlayingCard().get(i).getName()+ ":");
                }
                // player amount
                out.write("BANK-" + player.getPlayerAmount() + "\n");

            }

            out.write("BankAmount-" + BoardGame.getBank());
            out.flush();
            out.close();
        } else {
            JOptionPane.showMessageDialog(null, "Entered File Name Is not Valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method takes as input any file name and loads the game from that file
     * Initializes all the required data structures accordingly
     * functionality to load the arbitrary game.
     *
     * @param filePath the file path
     * @return the array list
     * @throws JSONException 
     */
    public static ArrayList<String> loadFile(String filePath, String fileName) throws JSONException {

        BufferedReader br = null;
        
        ArrayList<String> playersRecords = new ArrayList<String>();
        if (isFileNameValid(fileName)) {
            try {
                br = new BufferedReader(new FileReader(filePath));
            } catch (FileNotFoundException e) {
                System.out.println("File Not found");  //To change body of catch statement use File | Settings | File Templates.
            }
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    playersRecords.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } else {
            JOptionPane.showMessageDialog(null, "Entered File Name Is not Valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        initializeGameState(playersRecords);
        return playersRecords;

    }

    /**
     * this method will initialize the game board wrt the state it reads from the file.
     *
     * @param playersRecords the players records
     * @throws JSONException 
     */
    private static void initializeGameState(ArrayList<String> playersRecords) throws JSONException {
    	
    	emptyAllDataStructures();
        String[] firstLine = playersRecords.get(0).split(":");
        int noOfPlayers = Integer.parseInt(firstLine[1].trim());
        playersRecords.remove(0);
        // initializing the board
        BoardGame.start(noOfPlayers);
        // creating number of players
       // System.out.println(noOfPlayers);
      //  System.out.println(playersRecords.get(0));

        initializeTroubleMarkerOnGameBoard(playersRecords);

        createPlayers(playersRecords);
        
        
    }

    private static void emptyAllDataStructures() throws JSONException {
    	
    	if((BoardGame.playersInGame) != null){
    	for(Player p : BoardGame.playersInGame){
    		// clearing players own area list
    		p.getPlayerAreas().clear();
    		p.getMinions().clear();
    		p.getPlayersPlayingCard().clear();
    	}
    	}
    	if((BoardGame.board_areas) != null){
    		
    		for(Area a : BoardGame.board_areas){
    			if((a.getPlayersInThisAreas()!=null))
    				a.getPlayersInThisAreas().clear();
    		}
    		BoardGame.board_areas.remove(null);
    		BoardGame.personality_cards.clear();
    		BoardGame.player_cards.clear();
    		BoardGame.playersInGame.clear();
    		BoardGame.getInstance().random_event_cards.clear();
    		//BoardGame.getInstance()=null;
    		BoardGame.setInstance();
    		
    		// BoardGame.game.setInstance();
    		// need to destroy the boardgame instance
    		System.gc();
    	}
    	
    	
	}

	/**
     * Initialize trouble marker on game board.
     *
     * @param playersRecords - initializing troubleMarkers on the game board
     */
    private static void initializeTroubleMarkerOnGameBoard(ArrayList<String> playersRecords) {

        String[] tempTroubleMarker = null;
        tempTroubleMarker = playersRecords.get(0).split(":");
        for (String str : tempTroubleMarker) {
            String areaName = str.split("-")[1];
            for (Area a : BoardGame.board_areas) {
                if (a.getAreaName().equalsIgnoreCase(areaName)) {
                    a.setTroubleMarkerArea(areaName);
                    break;
                }
            }
        }
        playersRecords.remove(0);
    }

    /**
     * This method creates the players in the game and updates their inventory according to the game state
     * and data read from the file.
     *
     * @param playersRecords - An arraylist that contains the records from the file for each player's data
     */
    private static void createPlayers(ArrayList<String> playersRecords) {

        String[] playerInfo = null;
        for (String str : playersRecords) {
            if (!str.startsWith("BankAmount")) {
                playerInfo = str.split(":");


                Player player = new Player(playerInfo[0]); // setting players color
                player.setWinningCondition(playerInfo[1]); // setting personality card
                player.setMinionQuantity(Integer.parseInt(playerInfo[2])); // setting number of minions
                int index = 3;
                int countMinion = 12 - player.getMinionQuantity();
                // setting minions in respective areas
                if (countMinion != 0) {

                    do {
                        player.setMinions(player.getPlayerColor(), playerInfo[index].split("-")[1]);
                        index++;
                        countMinion--;
                    } while (countMinion != 0);

                }

                int countBuilding = 6 - Integer.parseInt(playerInfo[index]);
                index++;
                BoardGame.playersInGame.add(player);

                /**
                 * This for loop will return you the existing instance of player from the game board.
                 * every detail of the player needs to be updated so that there are no duplicate players created
                 */
                for (Player playerInBoardGame : BoardGame.playersInGame) {
                    if (player.getPlayerColor().equalsIgnoreCase(playerInBoardGame.getPlayerColor())) {

                        if (countBuilding != 0) {

                            do {
                                playerInBoardGame.addBuilding(playerInfo[index].split("-")[1]);
                                index++;
                                countBuilding--;
                            } while (countBuilding != 0);
                        }
                        else{
                        	index++;
                        }
                        // setting players playing cards
                        if (playerInfo[index].split("-")[1].equalsIgnoreCase("green")) {
                            index++;
                            if (!(playerInfo[index].split("-")[0].equalsIgnoreCase("bank"))) {
                                do {
                                    //PlayerCard p = new PlayerCard(Integer.parseInt(playerInfo[index].split("-")[1]), "Green", " ", " ");
                                	GreenPlayerCardEnum p = returnInstanceOfPlayerCard(playerInfo[index].split("-")[1]);
                                    playerInBoardGame.setPlayersPlayingCard(p);
                                    index++;
                                    BoardGame.player_cards.remove(p);
                                } while (!(playerInfo[index].split("-")[0].equalsIgnoreCase("bank")));
                            }
                        }
                        // setting players bank account balance
                        playerInBoardGame.setPlayerAmount(Integer.parseInt(playerInfo[index].split("-")[1]));

                    }
                }

            } else {
                // write bank amount
                BoardGame.setBank(Integer.parseInt(str.split("-")[1]));
            }
        }
        for(Player playerInBoardGame : BoardGame.getInstance().playersInGame){
        	      ConsoleOutput.printOutPlayerState(playerInBoardGame);
        	      System.out.println();
        	      ConsoleOutput.printOutInventory(playerInBoardGame);
        	
        }
        ConsoleOutput.printOutGameBoardState();
        System.out.println();
    }

    private static GreenPlayerCardEnum returnInstanceOfPlayerCard(String string) {

    	for(GreenPlayerCardEnum temp : BoardGame.player_cards){
    		
    		if(temp.getName().equalsIgnoreCase(string)){
    			return temp;
    		}
    		
    	}
    	return null;
	}

	public static boolean isFileNameValid(String fileName) {
        boolean validationResult = true;
        if (fileName.equals(" "))
            validationResult = false;
        return validationResult;
    }

}
