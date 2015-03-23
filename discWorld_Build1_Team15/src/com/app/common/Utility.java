package com.app.common;

import com.app.Area;
import com.app.BoardGame;
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
            eachPlayerTotalPoints += player.getMinionQuantity() * 5;
            for (Area area : player.getPlayerAreas()) {
                if (!area.isAreaCityCards())
                    eachPlayerTotalPoints += area.getCostOfArea();
                if (area.getDemons() > 0)
                    eachPlayerTotalPoints = 0;
            }
            if (player.getPlayerAmount() > player.getPlayerLoan()) {
                player.setPlayerAmount(player.getPlayerAmount() - player.getPlayerLoan());
                eachPlayerTotalPoints += player.getPlayerAmount();
            } else
                eachPlayerTotalPoints += (player.getPlayerAmount() - 15);
            player.setPoints(eachPlayerTotalPoints);
        }
    }

    public void announceWinner() {
        doFinalPointsCalculation();
        System.out.println("**************GAME IS OVER**************");
        for (Player player : BoardGame.playersInGame) {
            System.out.println("******" + player.getPlayerColor() + "**********" + player.getPoints() + "POINTS");
        }
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
        System.out.println("Enter the player in your left (r/g/b/y)?");
        Scanner in = new Scanner(System.in);

        String result = in.nextLine();
        if (!result.isEmpty())
            result = result.trim();
        System.out.println("It is player with color " + result + " turn");
        return result;
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

    public String checkInputAnswer() {
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


}
