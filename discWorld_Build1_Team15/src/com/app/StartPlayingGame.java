package com.app;

import java.util.Scanner;

import org.json.JSONException;

import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.rules.WinningCircumstancesFactory;

public class StartPlayingGame {
	
	public int currentPlayer = 0;
	//WinningCircumstancesFactory factoryObj = new WinningCircumstancesFactory();
	Scanner in = new Scanner(System.in);
	public void start() throws JSONException{
		
		
		if(BoardGame.player_cards.size()!=0 || !(checkWinningConditionEveryPlayer()) ){
			
			// start playing cards
			// at the end of his turn pass turn to another player
			for(int i=0; i<BoardGame.playersInGame.size();i++ ){
				System.out.println("Player "+BoardGame.playersInGame.get(i).getPlayerColor()+
						". Its is your turn to play");
				//displayPersonalityCard(BoardGame.playersInGame.get(i));
				
				displayPlayerInstructions(BoardGame.playersInGame.get(i));
				String res = in.nextLine();
				PlayerCardUtility.getEnumInstance(res).performTasks(BoardGame.playersInGame.get(i));
		}
			
		}
		else{
		//	System.out.println("Player playing with"+BoardGame.playersInGame.get(i).getPlayerColor()+" has won");
			System.out.println("Hope you all enjoyed!!!!!!!!!!!!!!!");
			System.exit(0);
		}
		
		
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
