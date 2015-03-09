package com.app.common;

import java.util.ArrayList;

import com.app.Area;
import com.app.Player;

public class MinionUtilities {
	
	
	public void displayMinionsOfotherPlayer(Player selectedPlayer){
		
		for(ArrayList<String> a : selectedPlayer.getMinions().values()){
			for(String str : a){
				
					System.out.print("Minions Locations: "+str+" ,");
			}
		}
	}
	
	public ArrayList<Area> getMinionsForAnArea(){
		return null;
		
	}

}
