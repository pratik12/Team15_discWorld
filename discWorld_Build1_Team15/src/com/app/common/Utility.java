package com.app.common;

import com.app.Area;
import com.app.BoardGame;
import com.app.ConsoleOutput;
import com.app.CityAreaCardSystem.CityAreaCardEnum;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.Player;

import java.util.Random;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/26/15
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utility {

	ComponentUtilities cu = new ComponentUtilities();
    public int rollDie() {
        Random rand = new Random();
        return rand.nextInt(12) + 1;
    }

    public int getRandNum(int num) {
        Random rand = new Random();
        return rand.nextInt(num) + 1;
    }

    public Area getAreaByNumber(int areaNumber) {
        for (Area area : BoardGame.board_areas)
            if (area.getAreaNumber() == areaNumber) {
                return area;
            }
        return null;
    }

    public void doFinalPointsCalculation() {
        int eachPlayerTotalPoints = 0;
        for (Player player : BoardGame.playersInGame) {
            eachPlayerTotalPoints += (player.getMinionQuantity()) * 5;
            System.out.println("Player Collor::::"+player.getPlayerColor()+ "Minions"+eachPlayerTotalPoints);
            for (Area area : player.getPlayerAreas()) {
                if (area.isAreaCityCards()){
                    eachPlayerTotalPoints += area.getCostOfArea();
                    System.out.println("Player Collor::::"+player.getPlayerColor()+ "Buildings"+eachPlayerTotalPoints);
                }
                if (area.getDemons() > 0)
                    eachPlayerTotalPoints = eachPlayerTotalPoints-area.getCostOfArea();
            }

            int count = player.getPlayerLoan() / 10;
            while(count!=0){
            	if (player.getPlayerAmount() > 10 && player.getPlayerAmount() >= 12)
            		player.setPlayerAmount(player.getPlayerAmount() - 12);
            	else
            		eachPlayerTotalPoints = (eachPlayerTotalPoints - 15);
            }
            for(int i = 1 ;i<=player.getPlayerAmount();i++)
            	eachPlayerTotalPoints++;
            player.setPoints(eachPlayerTotalPoints);
            eachPlayerTotalPoints = 0;
        }
    }

    public void announceWinner() {
        doFinalPointsCalculation();
        
        System.out.println();
        System.out.println("**************GAME IS OVER**************");
        System.out.printf("%3s%25s\n","Player Color","Points");
        for (Player player : BoardGame.playersInGame) {
            System.out.printf("%3s%25s\n",player.getPlayerColor(),player.getPoints());
        }
        
        ConsoleOutput.printOutGameBoardState();
    }

    public int calculateNumberOfTroubleMarkers() {
        int numberOfTroubleMarkers = 0;
        for (Area area : BoardGame.board_areas) {
            if (area.isTroubleMarkers())
                numberOfTroubleMarkers++;
        }
        return numberOfTroubleMarkers;
    }

    public String giveTurnToleft() {
        //String player = CityAreaCardEnum.GLOBAL.questionsToAsk("Enter the player in your left (r/g/b/y)?");
    	String player = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Enter the player in your left (r/g/b/y)?");
        System.out.println("It is player with color " + player + " turn");
        
        return player;
    }

    public int getNumberOfMinions(String areaName) {
        int result = 0;

        // iterating over the NUMBER OF MINIONS THAT PLAYER HAS PLACED IN HIS AREA.
        for (Player p : BoardGame.playersInGame) {
            // checking for every minion location for all the players
            for (String minion_location : p.getMinions().get(p.getPlayerColor())) {

                if (minion_location.equals(areaName))
                    ++result;
            }

        }
        return result;
    }


   /* public String checkInputAnswer() {
    	        System.out.println();
    	        String result = null;
    	        Scanner in = new Scanner(System.in);
    	        result = in.nextLine();
    	        if (!result.isEmpty())
    	            return result;
    	        else {
    	            System.out.println("Enter a valid input");
    	            checkInputAnswer();
    	        }
    	        return result;
    	    }
*/
   }
