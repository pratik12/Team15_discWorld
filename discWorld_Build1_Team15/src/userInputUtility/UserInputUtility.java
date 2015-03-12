package userInputUtility;

import java.util.Scanner;

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
	Scanner str = new Scanner(System.in);;
	int choice = 0;
	String result = null;
	
	public String getUserInput(GreenPlayerCardEnum temp,String result){
		
		String retVal = null;
		if(temp!=null ){
			System.out.println("Select from menu:");
			
				for(int i = 1 ; i<= temp.getSymbols().length;i++){
					System.out.print( i + ". " + temp.getSymbols()[i-1] + " ");
				}
				System.out.println(temp.getSymbols().length+1 + ". Exit");
				choice = in.nextInt();
				if(choice!=temp.getSymbols().length+1 && choice>Integer.parseInt(result)){
					retVal = temp.getSymbols()[choice-1]+":"+Integer.toString(choice);
					 
				}
				else if(choice<Integer.parseInt(result)){
					System.out.println("You cannot enter a choice in reverse direction...");
					getUserInput(temp, result);
				}
				else if(choice==Integer.parseInt(result)){
					System.out.println("You cannot enter same choice again");
					getUserInput(temp, result);
				}
		}
		else{
			System.out.println("Player playing card is null");
		}
		return retVal;		
	}

	
	

}
