package userInputUtility;

import java.util.Scanner;

import org.json.JSONException;

import com.app.Player;
import com.app.UserCardChoice;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.PlayingCardSystem.PlayingCardRulesInterface;
/**
 * This class will be used everytime to prompt user for his input during the game
 * @author Pratik Bidkar
 *
 */
public enum UserInputUtility{
	
	USERINPUTUTILITYENUM;
	Scanner in = new Scanner(System.in); 
	Scanner str = new Scanner(System.in);
	int choice = 0;
	String result = null;
	UserCardChoice uc = new UserCardChoice();
	
	public String getUserInput(GreenPlayerCardEnum temp,String result,Player player) throws JSONException{
		
		String retVal = null;
		if(temp!=null ){
			// before playing first symbol
			if(!uc.wishToPlayCityCard(player)){
				retVal = selectFromMenu(temp,result,player);	
			}
			else{
				uc.playCityAreaCards(player, temp);
				retVal = selectFromMenu(temp,result,player);	
			}
			
		}
		else{
			System.out.println("Player playing card is null");
		}
		return retVal;		
	}
	
	private String selectFromMenu(GreenPlayerCardEnum temp, String result, Player player) throws JSONException {
		
		String ans = "";
		System.out.println("Select from menu:");
		
		for(int i = 1 ; i<= temp.getSymbols().length;i++){
			System.out.print( i + ". " + temp.getSymbols()[i-1] + " ");
		}
		System.out.println(temp.getSymbols().length+1 + ". Exit");
		choice = in.nextInt();
		if(choice!=temp.getSymbols().length+1 && choice>Integer.parseInt(result)){
			ans = temp.getSymbols()[choice-1]+":"+Integer.toString(choice);
			 
		}
		else if(choice<Integer.parseInt(result)){
			System.out.println("You cannot enter a choice in reverse direction...");
			getUserInput(temp, result, player);
		}
		else if(choice==Integer.parseInt(result)){
			System.out.println("You cannot enter same choice again");
			getUserInput(temp, result,player);
		}
		else if(choice==temp.getSymbols().length+1){
			//getUserInput(temp, result,player);
			ans = "exit";
		}
	return ans.trim();
	}

	
	

}
