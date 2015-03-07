package com.app;

import com.app.rules.WinningCircumstancesFactory;

public class StartPlayingGame {
	
	public int currentPlayer = 0;
	//WinningCircumstancesFactory factoryObj = new WinningCircumstancesFactory();
	
	public void start(){
		
		for(int i=0; i<BoardGame.playersInGame.size();i++ ){
			
		if(WinningCircumstancesFactory.getWinningCircumstance(BoardGame.playersInGame.get(i).getWinningCondition()).isWinner()){
			System.out.println("Player playing with"+BoardGame.playersInGame.get(i).getPlayerColor()+" has won");
			System.out.println("Hope you all enjoyed!!!!!!!!!!!!!!!");
			System.exit(0);
		}
		else{
			// start playing cards
			// at the end of his turn pass turn to another player
		}
		
		}
	}
	
	

}
