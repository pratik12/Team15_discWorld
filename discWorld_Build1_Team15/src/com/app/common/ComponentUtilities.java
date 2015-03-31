package com.app.common;

import java.util.ArrayList;

import org.json.JSONException;

import com.app.Area;
import com.app.BoardGame;
import com.app.Player;
import com.app.CityAreaCardSystem.CityAreaCardEnum;
/**
 * Thsi class behaves as a provider class to provide global funntionalities like 
 * getting total number of minion pieces on board
 * @author p_bidkar
 *
 */

public class ComponentUtilities {
	
	private static ArrayList<String> displayMinion = new ArrayList<String>();
	static int count = 1;
	/**
	 * dispalys the Area names of other player where he has plced his minions 
	 * @param selectedPlayer
	 */
	public void displayMinionsOfotherPlayer(Player selectedPlayer){
		
		System.out.printf("%10s","Area Name");
		int count = 1;
		for(ArrayList<String> a : selectedPlayer.getMinions().values()){
			for(String str : a){
				if(!str.equals("")){
					System.out.printf("%2s%10s\n",count,str);
					BoardGame.pieceNumberAreaList.add(""+count+":"+str);
					count++;
				}
			}
		}
	}
	public static ArrayList<String> getDisplayMinion() {
		return displayMinion;
	}
	public static void setDisplayMinion(String displayMinion) {
		ComponentUtilities.getDisplayMinion().add(displayMinion);
	}
	/**
	 * Returns you the number of minions on board for a player
	 * @param player
	 * @return
	 */
	public int getMinionsOnBoardForPlayer(Player player){
		
		return (12-player.getMinionQuantity());
		
	}
	/**
	 * Returns you the number of buildings on board for a player
	 * @param player
	 * @return
	 */
	public int getBuildingsOnBoardForPlayer(Player player){
		
		return (6-player.getNumberOfBuildings());
		
	}
	
	public void displayBuildingsForPlayeronBoard(Player player) throws JSONException{
		
		if(player.getCityAreaCardsStore().size()!=0){
		System.out.printf("%2s%15s\n","   ","Area Names");
		int count = 1;
		for(CityAreaCardEnum e : player.getCityAreaCardsStore()){
			System.out.printf("%3s%15s\n",count,e.getareaName());
			BoardGame.pieceNumberAreaList.add(""+count+":"+e.getareaName());
			count++;
		}
		}
		else{
			System.out.printf("%20s","no buildings on Board");
		}
		
		
	}
	public void showMinionsAllPlayers(String areaName,Player player) {
		
		for(ArrayList<String> a : player.getMinions().values()){
			
			for(String str : a){
				if(str.equalsIgnoreCase(areaName)){
					System.out.printf("%-2s%8s%2s%10s\n",count,player.getPlayerColor(),"  ",areaName);
					BoardGame.setNumberPieceList((""+
					count+":"+areaName+":"+player.getPlayerColor()));
					count++;
				}
			}
		}
	}
	
	public String getAreaNameDisplayMinion(String num){
		
		String res = null;
		for(String s : BoardGame.getPieceNumberAreaList()){
			if(Integer.parseInt(s.split(":")[0].trim()) == Integer.parseInt(num.trim())){
				res = s.split(":")[1].trim()+":"+s.split(":")[2].trim();
			}
		}
		BoardGame.getPieceNumberAreaList().clear();
		return res;
	}
	
	public Player getPlayerFromPieceColor(String color){
		Player fromPlayer = null;
		for(Player po : BoardGame.playersInGame){
			if(color.equalsIgnoreCase(po.getPlayerColor()))
			fromPlayer = po;
		}
		return fromPlayer;
	}
}
