package com.app;

import java.util.Scanner;

import org.json.JSONException;

import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.common.Utility;
import com.app.rules.WinningCircumstancesFactory;

/**
 * Class responsible to start playing the game
 * Implements te turn based functionality
 * @author p_bidkar
 *
 */
public class StartPlayingGame {
	
	public int currentPlayer = 0;
	//WinningCircumstancesFactory factoryObj = new WinningCircumstancesFactory();
	Scanner in = new Scanner(System.in);
	Utility playerTurn = new Utility();
	InterruptCard ic = new InterruptCard();
	
	public void start() throws JSONException{
		System.out.printf("%-30s\n","*********GAME STARTED********");
		int currTurn = BoardGame.shuffle(BoardGame.playersInGame.size()-1);
		System.out.println("Player selected with initial shuffling......");
		do{
			Player currPlayer = BoardGame.playersInGame.get(currTurn);
			System.out.println("PLAYERS INVENTORY BEFORE PLAYING TURN...");
			ConsoleOutput.printOutPlayerState(currPlayer);
			ConsoleOutput.printOutInventory(currPlayer);
			displayPlayerInstructions(currPlayer);
			String res = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Enter the card name you want to play:nul");
			GreenPlayerCardEnum gec = PlayerCardUtility.getEnumInstance(res);
			gec.performTasks(currPlayer);
			System.out.println("PLAYERS INVENTORY AFTER PLAYING TURN...");
			ConsoleOutput.printOutPlayerState(currPlayer);
			ConsoleOutput.printOutInventory(currPlayer);
			System.out.println("Next Players Turn....");
			currTurn = BoardGame.getInstance().getIndexOfPlayer(playerTurn.giveTurnToleft());
			ConsoleOutput.printOutGameBoardState();
			
		}while(BoardGame.player_cards.size()!=0 || !(checkWinningConditionEveryPlayer()));
		
			System.out.println("Hope you all enjoyed!!!!!!!!!!!!!!!");
			System.exit(0);
		
	}
	
	
	private void displayPlayerInstructions(Player player) {
		
		PlayerCardUtility.displayPlayerCardDetails(player);
	}

	public boolean checkWinningConditionEveryPlayer(){
		
		for(int i=0; i<BoardGame.playersInGame.size();i++ ){
		
			if(WinningCircumstancesFactory.getWinningCircumstance(BoardGame.playersInGame.get(i).getWinningCondition()).isWinner())
				return true;
			
		}
		return false;
	}
	
	

}
