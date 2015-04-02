package com.app.rules;

import com.app.Area;
import com.app.BoardGame;
import com.app.Player;
import com.app.PlayerCardUtility;
import com.app.CityAreaCardSystem.CityAreaCardEnum;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.rules.WinningCircumstancesFactory.PersonalityCards;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/24/15
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class LordSelRusWor implements WinningCircumstances {

    @Override
    public Boolean isWinner() {
    	
    	boolean ans = false;
        int numberOfPlayers = BoardGame.playersInGame.size();
        Player currentPlayer = null;
        for (Player player : BoardGame.playersInGame) {
        	if(player.getWinningCondition().equals(PersonalityCards.LordSelachii.getName()) || 
        			player.getWinningCondition().equals(PersonalityCards.LordRust.getName()) || 
        			player.getWinningCondition().equals(PersonalityCards.LordDeWorde.getName()))
                currentPlayer = player;
            }
        
        if (currentPlayer != null && currentPlayer.getPlayerAreas() != null && !currentPlayer.getPlayerAreas().isEmpty()) {
            if (numberOfPlayers == 2) {
                ans = checkControlledAreas(currentPlayer, 7);
            } else if (numberOfPlayers == 3) {
                ans = checkControlledAreas(currentPlayer, 5);
            } else if (numberOfPlayers == 4) {
                ans = checkControlledAreas(currentPlayer, 4);
            }
        }
        return ans;
    }
    
    
    private Boolean checkControlledAreas(Player currentPlayer, int numberOfAreaTocheck) {
    	int currPlayerCount = 0;
    	int odrPlayerCount = 0;
    	int controlledAreasPlayer = 0;
    	int ctrlCount = 0;
    	ArrayList<Player> temp = new ArrayList<Player>(); 
        for(Player p : BoardGame.playersInGame){
        	if(!(p.getPlayerColor().equalsIgnoreCase(currentPlayer.getPlayerColor())))
        		temp.add(p);
        }
    	for(Area a : BoardGame.board_areas){
    		
    		if(a.getDemons()==0){
    			//if(a.getAreaName().equalsIgnoreCase("Longwell"))
    				//System.out.println("bdjfsdfdsf");
    			currPlayerCount = calculateMinion(a, currentPlayer) + calculateBldg(a, currentPlayer);
    		
    			for(Player p : temp){
    				odrPlayerCount = calculateMinion(a, p) + calculateBldg(a, p);
    			
    				if(currPlayerCount > odrPlayerCount && currPlayerCount > a.getTrolls()){
    					ctrlCount++;
    				}
    				else
    					ctrlCount = 0;
    				
    			}
    			if(ctrlCount == 3){
    				System.out.println("Area Controlled : " +a.getAreaName());
    				controlledAreasPlayer++;
    				ctrlCount = 0;
    			}
    		}
    		
    	}
    	if(controlledAreasPlayer==numberOfAreaTocheck)
    		return Boolean.TRUE;
    	else
    		return Boolean.FALSE;
    }

	private int calculateMinion(Area a, Player p){
		
		ArrayList<String> temporary = new ArrayList<String>();
		for(ArrayList<String> at : p.getMinions().values()){
			for(String str : at){
				temporary.add(str);
			}
		}
		int mincount = 0 ;
		//for(ArrayList<String> s : p.getMinions().values()){
			for(String str : temporary){
				if(!str.equalsIgnoreCase("")){
					if(str.trim().equalsIgnoreCase(a.getAreaName().trim())){
						mincount++;
					}
				}
			//}
		}
	//	System.out.println("Player Color :" +p.getPlayerColor() + " Minions Area "+a.getAreaName());
		return mincount;
	}
		
	private int calculateBldg(Area a , Player p){	
		int bldgcount = 0 ;
		ArrayList<CityAreaCardEnum> temporary = new ArrayList<CityAreaCardEnum>();
		for(CityAreaCardEnum at : p.getCityAreaCardsStore()){
				temporary.add(at);
		}
		for(CityAreaCardEnum cec : temporary){
			if(cec.getareaName().trim().equalsIgnoreCase(a.getAreaName().trim())){
				bldgcount++;
			}
		}
	//	System.out.println("Player Color :" +p.getPlayerColor() + " Building Area "+a.getAreaName());
		return bldgcount;
	}
}
