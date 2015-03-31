package com.app;

import java.util.ArrayList;

import org.json.JSONException;

import com.app.CityAreaCardSystem.CityAreaCardEnum;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;

public class UserCardChoice {
	StartPlayingGame spg = new StartPlayingGame();
	public static ArrayList<CityAreaCardEnum> dupCardStore = new ArrayList<CityAreaCardEnum>();

	public void askUsercardchoice(Player currPlayer) throws JSONException {
		
		printmenu(currPlayer);
		String temp = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Enter the type of card you want to play:"
				+ "nul");
		checkInput(temp,currPlayer);
		
	}

	private void checkInput(String temp,Player player) throws JSONException {
		GreenPlayerCardEnum gecDup = null;
		
		if(temp.matches("\\d+") && (Integer.parseInt(temp) == 1 || Integer.parseInt(temp) == 2)){
			if(Integer.parseInt(temp) == 1){
				
				PlayerCardUtility.displayPlayerCardDetails(player);
				// before every symbol ask which city area card you would like to play
				String res = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Enter the player card number you want to play:"
						+ "nul");
				//res = "RINCEWIND";
				 GreenPlayerCardEnum gec = PlayerCardUtility.getEnumInstance(res);
				 gecDup = gec;
				// before playing first symbol
				
					gec.performTasks(player);
			}
			else if(!dupCardStore.isEmpty()){
				playCityAreaCards(player, gecDup);
				askUsercardchoice(player);
			}
			else{
				System.out.println("No more city area cards to play...");
				askUsercardchoice(player);
			}
		}
		else{
		System.out.println("You  have eentered a wrong input.....Please choose again");	
		askUsercardchoice(player);
		}
	}

	

	public boolean wishToPlayCityCard(Player player) {
		
		Boolean dec = false;
		if(!player.getCityAreaCardsStore().isEmpty()){
			
			String result = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Do you wish to play city area card. Hit 'Y' or 'N':"
					+ "nul");
			if(result.equalsIgnoreCase("y") && !result.matches("\\d+")){
				dec= Boolean.TRUE;
			}
			else if(result.matches("\\d+")){
				System.out.println("Invalid input....");
				wishToPlayCityCard(player);
			}
			else if(result.equalsIgnoreCase("n")){
				dec= Boolean.FALSE;	
			}
			
		}else{
			System.out.println("No more city area cards to play...");
			dec = false;
		}
		return dec;
	}

	public void playCityAreaCards(Player player, GreenPlayerCardEnum gec) throws JSONException {
		
		if(!player.getCityAreaCardsStore().isEmpty()){
		do{
		displayCityAreaCards(player,dupCardStore);
		String result = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Which city area card. Enter number:"
				+ "nul");
		String areaCard = BoardGame.getPieceNumberList(result);
		CityAreaCardEnum temp = CityAreaCardEnum.getCityAreaCardInstance(areaCard);
		temp.performTasks(player);
		dupCardStore.remove(temp);
		if(wishToPlayCityCard(player) && !dupCardStore.isEmpty()){
			continue;
		}
		else{
			System.out.println("NO CITY AREA CARDS TO CHOOSE FROM..");
			return;
		}
		}while(!dupCardStore.isEmpty());
		//dupCardStore.clear();
		}
		else{
			System.out.println("NO CITY AREA CARDS TO CHOOSE FROM..");
			return;
		}
		
	}

	private void displayCityAreaCards(Player player, ArrayList<CityAreaCardEnum> dupStore) {
		System.out.printf("%15s%30s\n","CITY CARD NAME","ACTIONS TO PERFORM");
		int count = 1 ;
		for(CityAreaCardEnum gc : dupStore){
				System.out.printf("%3s%5s%10s%30s\n",count,gc.getareaName(),"   ",gc.getAction());
				BoardGame.pieceNumberAreaList.add(""+count+":"+gc.getareaName());
				count++;
			}
	}

	private void printmenu(Player currPlayer) {
		
		System.out.println("**************************** MENU ***************************************");
		System.out.printf("%5s%17s\n","1. Play Player Cards ","2. Benefit from City Area Card");
		System.out.println("*************************************************************************\n\n");
	}
	
	

}
