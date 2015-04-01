package com.app;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONException;

import com.app.CityAreaCardSystem.CityAreaCardEnum;
import com.app.common.Utility;
import com.app.rules.WinningCircumstancesFactory;

/**
 * Class responsible to start playing the game
 * Implements te turn based functionality
 * @author p_bidkar
 *
 */
public class StartPlayingGame {
	
	public static ArrayList<String> currPlayingCards = new ArrayList<String>(); 
	public static ArrayList<String> currCityAreaCards = new ArrayList<String>();
	
	public int currentPlayer = 0;
	//WinningCircumstancesFactory factoryObj = new WinningCircumstancesFactory();
	Scanner in = new Scanner(System.in);
	static Utility playerTurn = new Utility();
	static UserCardChoice uc = new UserCardChoice();
	InterruptCard ic = new InterruptCard();
	
	public static void start() throws JSONException{
		System.out.printf("%-30s\n","*********GAME STARTED********");
		int currTurn = BoardGame.shuffle(BoardGame.playersInGame.size()-1);
		System.out.println("Player selected with initial shuffling......");
		boolean ctrl = true;
		//while( checkWinningConditionEveryPlayer() == null ){
			do{
			Player currPlayer = BoardGame.playersInGame.get(currTurn);
			if(checkWinningConditionEveryPlayer(currPlayer) == null){
				ctrl = false;
			System.out.println("PLAYERS INVENTORY BEFORE PLAYING TURN...");
			ConsoleOutput.printOutPlayerState(currPlayer);
			ConsoleOutput.printOutInventory(currPlayer);
			//displayPlayerInstructions(currPlayer);
			addGlobalStore(currPlayer);
			uc.askUsercardchoice(currPlayer);
			//res = "DRUMKNOTT"; //Hard Coded Remove
			clearPlayersData(currPlayer);
			System.out.println("PLAYERS INVENTORY AFTER PLAYING TURN...");
			ConsoleOutput.printOutPlayerState(currPlayer);
			ConsoleOutput.printOutInventory(currPlayer);
			System.out.println("Next Players Turn....");
			currTurn = BoardGame.getInstance().getIndexOfPlayer(playerTurn.giveTurnToleft());
			ConsoleOutput.printOutGameBoardState();
			}
			
			
			
		}while(!ctrl);
		
		
	}
	
	
	private static void addGlobalStore(Player currPlayer) {
			
		if(!currPlayer.getCityAreaCardsStore().isEmpty() && currPlayer.getCityAreaCardsStore()!=null)
			for(CityAreaCardEnum c : currPlayer.getCityAreaCardsStore())
				UserCardChoice.dupCardStore.add(c);
	}


	private static void clearPlayersData(Player currPlayer) {
		
		StartPlayingGame.currCityAreaCards.clear();
		StartPlayingGame.currPlayingCards.clear();
		UserCardChoice.dupCardStore.clear();
		
	}


	public void displayPlayerInstructions(Player player) {
		
		PlayerCardUtility.displayPlayerCardDetails(player);
		PlayerCardUtility.displayCityAreaCardDetails(player);
	}

	public static Player checkWinningConditionEveryPlayer(Player pla){
		Player player = null;
			System.out.println("Checking winning condition for Player with Color "+pla.getPlayerColor());
			String wc = pla.getWinningCondition();
			if(WinningCircumstancesFactory.getWinningCircumstance(wc).isWinner()){
				player = pla;
				System.out.println("Player "+player.getPlayerColor()+" playing with " +player.getWinningCondition()+
						" has won..");
				System.out.println("Hope you all enjoyed!!!!!!!!!!!!!!!");
				System.exit(0);
			}
			else
				player = null;
		return player;
	}
	
	public static void main(String... args) throws FileNotFoundException, JSONException{
		
		FileManager.loadFile("test.txt");
		start();
	}

}
