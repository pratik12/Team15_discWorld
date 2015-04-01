package com.app;

import com.app.CityAreaCardSystem.CityAreaCardEnum;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
/**
 * Class that is responsible to display player cards name, actions on each card.
 * Also contains methods to get GreenPlayerCardEnum instance based on string green player card enum Name
 * @author p_bidkar
 *
 */
public abstract class PlayerCardUtility {
	
	
	
	public static void displayPlayerCardDetails(Player player){
		
		
		try{
			  			System.out.printf("%-25s\n","-----AVAILABLE PLAYING CARDS-----");
			  			int count = 1 ;
			  			System.out.printf("%25s%5s%55s\n","PLAYING CARD NAME","     ","ACTIONS TO PERFORM");
			  			for(GreenPlayerCardEnum gc : player.getPlayersPlayingCard()){
			  				
			  				System.out.printf("%-1s%-1s%-25s",count,":",gc.getName());
			  				StartPlayingGame.currPlayingCards.add(""+count+":"+gc.getName());
			  				for(String str : gc.getSymbols()){
			  					System.out.printf("%20s%2s",str,"  ");
			  					
			  					}
			  				System.out.println();
			  				count++;
			  			}
			  			}catch(NullPointerException e){
			  				throw e;
						}
		
	}

	
	public static GreenPlayerCardEnum getEnumInstance(String enumName){
		GreenPlayerCardEnum temp = null;
		String s = null;
		if(enumName.matches("\\d+")){
			int i = Integer.parseInt(enumName.trim()); 
			
			for(String stre : StartPlayingGame.currPlayingCards){
				int j = Integer.parseInt(stre.split(":")[0].trim());
				if( i == j){
					s = stre.split(":")[1].trim();
					break;
				}
			}
		}
		
		for(GreenPlayerCardEnum gc : GreenPlayerCardEnum.values()){
			if(enumName.trim().equalsIgnoreCase(gc.getName()) && !(enumName.matches("\\d+"))){
				temp  = gc;
				break;
			}
			else if(enumName.matches("\\d+")){
				if(s.equalsIgnoreCase(gc.getName())){
					temp  = gc;
					break;
				}
			}
		}
		return temp;
	}
	
public static void displayCityAreaCardDetails(Player player){
		
		System.out.println();
		try{
			  			System.out.println("-----AVAILABLE CITY AREA CARDS-----" + "\n");
			  			if(!player.getCityAreaCardsStore().isEmpty()){
			  			for(CityAreaCardEnum gc : player.getCityAreaCardsStore()){
			  				int count = 1 ;
			  				System.out.print("CITY CARD NAME: ");
			  				System.out.printf("%-15s",gc.getareaName());
			  				StartPlayingGame.currCityAreaCards.add(gc.getareaName()+":"+count+":"+gc.getAction().split("."));
			  				System.out.print("ACTIONS TO PERFORM: ");
			  				System.out.printf("%-20s\n", gc.getAction() );
			  				count++;
			  				System.out.println();
			  			}
			  			
			  				
			  			}
			  			else{
			  				System.out.println("NIL");
			  			}
			  			}catch(NullPointerException e){
			  				throw e;
						}
		
	}
}
