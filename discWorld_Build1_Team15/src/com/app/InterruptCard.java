package com.app;

import org.json.JSONException;

import com.app.PlayingCardSystem.GreenPlayerCardEnum;

/**
 * This class implements the interrupt card functionalities. Will ask other player to 
 * play his interrupt card even is its not his turn
 * @author p_bidkar
 *
 */
public class InterruptCard {
	
	
	private static String interruptMessage;
	
	
	public static String wantToPlayInterruptCard(Player affectedPlayer, Player currentPlayer) throws JSONException{
		InterruptCard icard = new InterruptCard();
		String result = null;
		for(GreenPlayerCardEnum gec : affectedPlayer.getPlayersPlayingCard()){
			if(gec.getName().equalsIgnoreCase("gaspode")){
				
				String res = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("PLAYER "+affectedPlayer.getPlayerColor()+
						" Do you want to play your "+gec.getName()+ " card. Hit 'Y' or 'N':nul");
				if(res.equalsIgnoreCase("y") && !(res.matches("\\d+"))){
					gec.performTasks(affectedPlayer);
					result = icard.getInterruptMessage();
					if(result.equalsIgnoreCase("Sm")){
						System.out.println("Player "+currentPlayer.getPlayerColor()+" cannot carry out the action... ");
						result = "gasp";
						break;
					}
				}
			}
			if(gec.getName().equalsIgnoreCase("Fresh start club")){
						result = "fsc";
						break;
				}
			else{
				result = "none";
				//break;
			}
		}
		return result;
	}
	/**
	 * @return the interruptMessage
	 */
	public String getInterruptMessage() {
		return interruptMessage;
	}
	/**
	 * @param interruptMessage the interruptMessage to set
	 */
	public void setInterruptMessage(String interruptMessage) {
		InterruptCard.interruptMessage = interruptMessage;
	}

}
