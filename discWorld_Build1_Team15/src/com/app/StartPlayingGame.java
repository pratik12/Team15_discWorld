package com.app;

import java.util.Scanner;

import org.json.JSONException;

import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.common.Utility;
import com.app.rules.WinningCircumstancesFactory;

public class StartPlayingGame {
	
	public int currentPlayer = 0;
	//WinningCircumstancesFactory factoryObj = new WinningCircumstancesFactory();
	Scanner in = new Scanner(System.in);
	Utility playerTurn = new Utility();
	
	public void start() throws JSONException{
		
		int currTurn = BoardGame.shuffle(BoardGame.playersInGame.size()-1);
		
		do{
			displayPlayerInstructions(BoardGame.playersInGame.get(currTurn));
			String res = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Enter the card name you want to play:nul");
			PlayerCardUtility.getEnumInstance(res).performTasks(BoardGame.playersInGame.get(currTurn));
			System.out.println("Next Players Turn....");
			currTurn = BoardGame.getInstance().getIndexOfPlayer(playerTurn.giveTurnToleft());
			
		}while(BoardGame.player_cards.size()!=0 || !(checkWinningConditionEveryPlayer()));
		
		//	System.out.println("Player playing with"+BoardGame.playersInGame.get(i).getPlayerColor()+" has won");
			System.out.println("Hope you all enjoyed!!!!!!!!!!!!!!!");
			System.exit(0);
		
	}
	
	private void displayPersonalityCard(Player player) {
		
		System.out.println("Your personality card is : " +player.getWinningCondition());
		for(GreenPlayerCardEnum gc : player.getPlayersPlayingCard()){
			
		}
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
