package userInputUtility;

import java.util.Scanner;

import com.app.PlayingCardSystem.PlayerCard;
/**
 * This class will be used everytime to prompt user for his input during the game
 * @author Pratik Bidkar
 *
 */
public class UserInputUtility {
	
	Scanner in = new Scanner(System.in); 
	Scanner str = new Scanner(System.in);;
	int choice = 0;
	String result = null;
	
	public String getUserInput(PlayerCard temp,String result){
		
		if(temp!=null){
			System.out.println("Select from menu:");
			
				for(int i = 1 ; i<= temp.getSymbols().length;i++){
					System.out.print( i + ". " + temp.getSymbols()[i-1] + " ");
				}
				System.out.println(temp.getSymbols().length+1 + " Exit");
				choice = in.nextInt();
				if(choice!=temp.getSymbols().length+1 && choice>Integer.parseInt(result))
					
					return temp.getSymbols()[choice-1]+":"+Integer.toString(choice);
				else
					return "exit";
			
		}
		else{
			System.out.println("Please enter one of following choices");
			System.out.println("1. Selecting a player.");
			System.out.println("2. Select something else");
			choice = in.nextInt();
			switch(choice){
			
			case 1:
				System.out.println("Enter his playing piece color");
				result = str.next();
				break;
			}
		}
	
		return result;
		
	}
	

}
