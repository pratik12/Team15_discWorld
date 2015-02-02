package com.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * adding modularity for separation of concerns.
 * file management module
 * @author Pratik
 *
 */
public class FileManager {
   
	
	/**
     * functionality to save state of game
     *
     * @param filePath
     * @throws IOException
     */
    public static void saveMap(String filePath) throws IOException {

        FileWriter writeFile = new FileWriter(filePath);
       // String eol = System.getProperty("line.separator");
        BufferedWriter out = new BufferedWriter(writeFile);
        out.write("Players : " + BoardGame.playersInGame.size() + "\n");//total number of players
        for(Area area : BoardGame.board_areas)
        	if(area.isTroubleMarkers())
        		out.write("TroubleMarkers-"+area.getAreaName()+":");
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

                            out.write("MINION-"+str.get(i) + ":");
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
            // player amount 
            if(!count)
            	out.write("BUILDING-None" + ":");
            out.write(player.getPlayerAmount() + "\n");


        }

        out.write("BankAmount-" + BoardGame.getBank());
        out.flush();
        out.close();
    }

   /**
    * functionality to load the arbitrary game  
    */
    public static ArrayList<String> loadFile(String filePath) {
        BufferedReader br = null;
        ArrayList<String> playersRecords = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
        initializeGameState(playersRecords);
        return playersRecords;
    }

    
	private static void initializeGameState(ArrayList<String> playersRecords) {
	
		String[] firstLine = playersRecords.get(0).split(":");
		int noOfPlayers = Integer.parseInt(firstLine[1].trim());
		playersRecords.remove(0);
		// initializing the board
		BoardGame.startGame();
		// creating number of players
		System.out.println(noOfPlayers);
		System.out.println(playersRecords.get(0));
		
		initializeTroubleMarkerOnGameBoard(playersRecords);
			
		createPlayers(playersRecords);
	}
	
	private static void initializeTroubleMarkerOnGameBoard(ArrayList<String> playersRecords) {
		
		String[] tempTroubleMarker = null;
		tempTroubleMarker = playersRecords.get(0).split(":");
		for(String str : tempTroubleMarker){
			String areaName = str.split("-")[1];
			for(Area a : BoardGame.board_areas){
				if(a.getAreaName().equalsIgnoreCase(areaName)){
					a.setTroubleMarkerArea(areaName);
					break;					
				}
			}
		}
		playersRecords.remove(0);
	}
	
	private static void createPlayers(ArrayList<String> playersRecords) {
	
		String[] playerInfo = null;
		for(String str : playersRecords){
			if(!str.startsWith("BankAmount")){
				playerInfo = str.split(":");
				
					
					Player player = new Player(playerInfo[0]); // setting players color
					player.setWinningCondition(playerInfo[1]); // setting personality card
					player.setMinionQuantity(Integer.parseInt(playerInfo[2])); // setting number of minions
					int index = 3 ;
					int countMinion = 12 - player.getMinionQuantity();
					// setting minions in respective areas
					if(countMinion!=0){
						
						do{
							player.setMinions(player.getPlayerColor(), playerInfo[index].split("-")[1]);
							index++;
							countMinion--;
						}while(countMinion != 0);
						
					}

					//player.setNumberOfBuildings(Integer.parseInt(playerInfo[index]));
					// setting buildings in respective areas
					
					int countBuilding = 6 - Integer.parseInt(playerInfo[index]);
					index++;
					BoardGame.playersInGame.add(player);
					
					for(Player playerInBoardGame : BoardGame.playersInGame){
						if(player.getPlayerColor().equalsIgnoreCase(playerInBoardGame.getPlayerColor())){
							
							if(countBuilding!=0){
								
								do{
									playerInBoardGame.addBuilding(playerInfo[index].split("-")[1]);
									index++;
									countBuilding--;
								}while(countBuilding != 0);
							}
							
							// setting players bank account balance
							playerInBoardGame.setPlayerAmount(Integer.parseInt(playerInfo[index]));
							// finally adding player to global data structure
							ConsoleOutput.printOutPlayerState(playerInBoardGame);
							ConsoleOutput.printOutInventory(playerInBoardGame);
							
						}
					}
					
				}
			else{
				// write bank amount
				BoardGame.setBank(Integer.parseInt(str.split("-")[1]));
			}
			}
		ConsoleOutput.printOutGameBoardState();
	}

}
