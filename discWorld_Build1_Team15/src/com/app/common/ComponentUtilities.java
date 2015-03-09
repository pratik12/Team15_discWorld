package com.app.common;

import java.util.ArrayList;

import com.app.Area;
import com.app.Player;

public class ComponentUtilities {
	
	
	public void displayMinionsOfotherPlayer(Player selectedPlayer){
		
		for(ArrayList<String> a : selectedPlayer.getMinions().values()){
			for(String str : a){
				
					System.out.print("Minions Locations: "+str+" ,");
			}
		}
	}
	
	public int getMinionsOnBoardForPlayer(Player player){
		
		return (12-player.getMinionQuantity());
		
	}
	
	public int getBuildingsOnBoardForPlayer(Player player){
		
		return (6-player.getNumberOfBuildings());
		
	}

}
