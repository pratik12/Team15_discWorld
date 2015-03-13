package com.app;

import java.io.ObjectInputStream.GetField;

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
			if(gec.getName().equalsIgnoreCase("gaspode") ||
					gec.getName().equalsIgnoreCase("wallace sonky") ||
					gec.getName().equalsIgnoreCase("fresh start club")){
				String res = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("PLAYER "+affectedPlayer.getPlayerColor()+
						" Do you want to play your interrupt card. Hit 'Y' or 'N':nul");
				if(res.equalsIgnoreCase("y")){
					gec.performTasks(affectedPlayer);
					result = icard.getInterruptMessage();
					if(result.equalsIgnoreCase("Stop minion")){
						System.out.println("Player "+currentPlayer.getPlayerColor()+" cannot carry out the action... ");
						System.out.println("");
						break;
					}
				}
			}
			else{
				result = "fail";
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
		this.interruptMessage = interruptMessage;
	}

}
