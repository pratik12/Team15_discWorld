package com.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
        String eol = System.getProperty("line.separator");
        BufferedWriter out = new BufferedWriter(writeFile);
        out.write("Players : " + BoardGame.playersInGame.size() + eol);//total number of players
        for (Player player : BoardGame.playersInGame) {
            out.write(player.getPlayerColor() + eol); // player color
            out.write(player.getWinningCondition() + eol); // personality card
            out.write(player.getMinionQuantity() + eol); // get number of minions

            // every player has a minion in hashmap datastructure
            if (player.getMinions().size() != 0) {
                // retrieving the arraylist data structure for each minion
                for (ArrayList<String> str : player.getMinions().values()) {

                    for (int i = 0; i < str.size(); i++) {
                        // only taking out those names of areas where the minion is placed
                        if (!(str.get(i).equals("Players Pile"))) {

                            out.write("MINION : "+str.get(i) + eol);
                        }
                    }
                }

            }

            out.write(player.getNumberOfBuildings() + eol);
            boolean count = false;
            // getting the area names where the player has  build a building
            for (Area area : player.getPlayerAreas()) {

                out.write("BUILDING : " + area.getAreaName() + eol);
                count = true;
            }
            // player amount 
            if(!count)
            	out.write("BUILDING : None" + eol);
            out.write(player.getPlayerAmount() + eol);


        }

        out.write("BankAmount " + BoardGame.getBank() + eol);
        out.newLine();
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
        return playersRecords;
    }

}
