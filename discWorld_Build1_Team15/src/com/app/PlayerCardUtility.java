package com.app;

import com.app.PlayingCardSystem.GreenPlayerCardEnum;

public abstract class PlayerCardUtility {
	
	
	
	public static void displayPlayerCardDetails(Player player){
		
		
		System.out.println("*******AVAILABLE PLAYING CARD INFORMATION**************");
		for(GreenPlayerCardEnum gc : player.getPlayersPlayingCard()){
			System.out.print("PLAYING CARD NAME: ");
			System.out.printf("%-30s\n",gc.getName());
			System.out.print("ACTIONS TO PERFORM: ");
			for(String str : gc.getSymbols()){
				System.out.printf("%-20s ", str );
				}
			System.out.println();
		}
		
	}

	
	public static GreenPlayerCardEnum getEnumInstance(String enumName){
		GreenPlayerCardEnum temp = null;
		for(GreenPlayerCardEnum gc : BoardGame.player_cards){
			if(enumName.trim().equalsIgnoreCase(gc.name())){
				temp  = gc;
				break;
			}
			
		}
		return temp;
	}
}
