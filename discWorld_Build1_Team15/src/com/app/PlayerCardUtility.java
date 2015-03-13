package com.app;

import com.app.PlayingCardSystem.GreenPlayerCardEnum;
/**
 * Class that is responsible to display player cards name, actions on each card.
 * Also contains methods to get GreenPlayerCardEnum instance based on string green player card enum Name
 * @author p_bidkar
 *
 */
public abstract class PlayerCardUtility {
	
	
	
	public static void displayPlayerCardDetails(Player player){
		
		
		System.out.println("*******AVAILABLE PLAYING CARD INFORMATION**************");
		for(GreenPlayerCardEnum gc : player.getPlayersPlayingCard()){
			System.out.print("PLAYING CARD NAME: ");
			System.out.printf("%-30s",gc.getName());
			System.out.print("ACTIONS TO PERFORM: ");
			for(String str : gc.getSymbols()){
				System.out.printf("%-20s", str );
				}
			System.out.println();
		}
		
	}

	
	public static GreenPlayerCardEnum getEnumInstance(String enumName){
		GreenPlayerCardEnum temp = null;
		for(GreenPlayerCardEnum gc : GreenPlayerCardEnum.values()){
			if(enumName.trim().equalsIgnoreCase(gc.getName())){
				temp  = gc;
				break;
			}
		}
		return temp;
	}
}
