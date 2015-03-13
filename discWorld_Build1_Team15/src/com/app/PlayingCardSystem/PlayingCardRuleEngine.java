package com.app.PlayingCardSystem;

import com.app.BoardGame;
import com.app.Player;

public enum PlayingCardRuleEngine {

	TEST;
	
	public void takeMoney(int amt, Player currentPlayingPlayer){
		System.out.println("Take money from every player...");
		for(Player temp : BoardGame.playersInGame){
			
			if(!(temp.getPlayerColor().toLowerCase().equals(currentPlayingPlayer.getPlayerColor().toLowerCase()))
					&& !(temp.getPlayerAmount()<0) && !(currentPlayingPlayer.getPlayerAmount() < 0)){
				
				temp.setPlayerAmount(temp.getPlayerAmount() - amt);
				currentPlayingPlayer.setPlayerAmount(currentPlayingPlayer.getPlayerAmount() + amt);
			}
			
		}
		
	}

	public void takePlayingCards(Player currentPlayingPlayer){
		
		for(Player temp : BoardGame.playersInGame){
			
			if(!(temp.getPlayerColor().toLowerCase().equals(currentPlayingPlayer.getPlayerColor().toLowerCase()))){
				
				
			}
		}
	}
	
	public Player selectPlayer(Player currentPlayer,String playerToSelect){
		
		Player temps = null;
		for(Player temp : BoardGame.playersInGame){
			if(!(temp.getPlayerColor().toLowerCase().equals(playerToSelect.toLowerCase()))){
				
				temps = temp;
				break;
			}else{
				System.out.println("You have entered non existing piece color.");
			}
		}
		return temps;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
