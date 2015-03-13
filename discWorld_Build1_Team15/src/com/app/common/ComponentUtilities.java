package com.app.common;

import java.util.ArrayList;

import com.app.Area;
import com.app.Player;
/**
 * Thsi class behaves as a provider class to provide global funntionalities like 
 * getting total number of minion pieces on board
 * @author p_bidkar
 *
 */
public class ComponentUtilities {
	
	/**
	 * dispalys the Area names of other player where he has plced his minions 
	 * @param selectedPlayer
	 */
	public void displayMinionsOfotherPlayer(Player selectedPlayer){
		
		for(ArrayList<String> a : selectedPlayer.getMinions().values()){
			for(String str : a){
				
					System.out.print("Minions Locations: "+str+" ,");
			}
		}
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

}
