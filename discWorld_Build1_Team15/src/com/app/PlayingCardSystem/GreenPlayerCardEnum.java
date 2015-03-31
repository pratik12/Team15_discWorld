	package com.app.PlayingCardSystem;
	
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONException;

import userInputUtility.UserInputUtility;

import com.app.Area;
import com.app.BoardGame;
import com.app.InterruptCard;
import com.app.Player;
import com.app.PlayerCardUtility;
import com.app.CityAreaCardSystem.CityAreaCardEnum;
import com.app.common.ComponentUtilities;
import com.app.rules.WinningCircumstancesFactory;
import com.app.rules.RandomEventCard;
import com.app.common.Utility;

	
	/**
	 * The Class PlayerCard.
	 *
	 * @author Pratik
	 * Class of player cards that will be given out to players when the game starts
	 * Enum implementation of playercards . these enums will be given out to every player at the start
	 * of game and every enum has actions mapped onto them that can be carried out when you 
	 * play a particular card. 
	 */
	public enum GreenPlayerCardEnum implements PlayingCardRulesInterface {
		
		GLOBALOBJ("self","green","deckpile",new String[]{}){},
		
		BOGGIS("Mr.Boggis","green","DeckPile", new String[]{"Read Scroll-> Take 2$ from every player","Place minion"} ){
			
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
				
			String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
				(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
				
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0]) && 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ){
					
					PlayingCardRuleEngine.TEST.takeMoney(2, currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
				}
				else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer, true);
					return;
	
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])
						&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					placeMinionActionPlayerCard(currentPlayingPlayer);
					res = lastaction(currentPlayingPlayer,res);
				}
			
			}
			}
		},//take 2$ from all players
		
	BEGGARSGUILD("THE BEGGARS GUILD","green","DeckPile", new String[]{"Read Scroll-> Take 2 playing cards","Place minion"} ) {
			
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
				
			String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
			while( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) || 
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0]) && 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
				takePlayingCards(currentPlayingPlayer, 2 , true);
				res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
				}
				else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer, true);
					return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])
						&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					placeMinionActionPlayerCard(currentPlayingPlayer);
					res = lastaction(currentPlayingPlayer,res);
				}
				
		
			}
			addToDiscardPile(1, this, currentPlayingPlayer, true);
		}
				
			
		},// sleect 1 player. take 2 cards
		
		AMBANK("The Bank of AnkhMorpork","green","DeckPile",new String[]{"Read Scroll->  Take 10$ loan from bank"
				+ " you must pay back 12$ or you loose 15 points", "Play another card"}) {
			
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
				String res = askSymbolsInOrder(this, "0",currentPlayingPlayer);
	
	            while ( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
	                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {
	
	                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
	    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
	                    this.takeLoanFromBank(10, currentPlayingPlayer);
	                    res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
	                }
	                else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
	    				addToDiscardPile(1, this, currentPlayingPlayer, true);
	    				return;
	    			}
	                else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])
	                		&& 
	    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
	                    try {
	                        playAnotherCard(currentPlayingPlayer, this);
	                    } catch (JSONException e) {
	                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	                    }
	                    res = lastaction(currentPlayingPlayer,res);
	                }
	                else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer, true);
						return;
					}
	            }
	            addToDiscardPile(1, this, currentPlayingPlayer, true);
				
			}
		}, //loan of 10$ from bank. at end payback 12$ or loose 15 points
		
		AMSUNSHINEDRAGONSANCTUARY("THE ANKH MORPORK SUNSHINE DRAGON SANCTUARY","green","DeckPile", new String[]{"Read Scroll->  Each player must give you 1$ or one of their"
				+ " cards","Play Another Card"}) {
	
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
			String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
	
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					System.out.println("Take 1$ from every other player or take 1 playing card");
					for(Player p : BoardGame.playersInGame){
					
						if(!(p.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor()))&& 
								!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						//Player fromPlayer = null;
						String res1 = questionsToAsk("Enter Your piece color to Give him a playing card :"+
						"or Enter 'P' to Pay him 1$");
						Player fromPlayer = null;
						if(res1.equalsIgnoreCase("r") || res1.equalsIgnoreCase("g")
								|| res1.equalsIgnoreCase("b") || res1.equalsIgnoreCase("y")){
							for(Player pc : BoardGame.playersInGame){
								if(pc.getPlayerColor().equalsIgnoreCase(res1))
									fromPlayer = pc;
								break;
							}
							takePlayingCards(currentPlayingPlayer, 1, true);
						}else if(res1.equalsIgnoreCase("P")){
							takeMoneyFromAllPlayer(1, currentPlayingPlayer);
						}
					}
				}
				res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
				}
				else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer, true);
					return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					// call the function of playing another card
					playAnotherCard(currentPlayingPlayer, this);
					res = lastaction(currentPlayingPlayer,res);
				}
				else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer, true);
					return;
				}
			}
			addToDiscardPile(1, this, currentPlayingPlayer, true);
			}
		}, // take 1$ from every player or one card
		
		ANGUA("Sergeant Angua","green","DeckPile",new String[]{"Remove Trouble Marker","Play Another Card"}) {
			
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) || 
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
	
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						// show to user where trouble markers are
						
						removeTroubleMarker();
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer, true);
						return;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						playAnotherCard(currentPlayingPlayer, this);
						res = lastaction(currentPlayingPlayer,res);
					}
					else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer, true);
						return;
					}
				}
				addToDiscardPile(1, this, currentPlayingPlayer, true);	
			}
		},//
		
	AGONYAUNTS("THE AGONY AUNTS","green","DeckPile", new String[]{"Assasinate","Take 2$ from Bank","Place Minion"}) {
			
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						assasinate(currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						takeMoneyFromBank(2, currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[2])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						placeMinionActionPlayerCard(currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
					}
					else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer, true);
						return;
					}
					
				}
				addToDiscardPile(1, this, currentPlayingPlayer, true);
			}
		},
		
	DYSK("THE DYSK","green","DeckPile",new String[]{"Add building","Read Scroll-> Earn 1$ for each minion in Isle of Gods"}) {
			
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
				
			String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					addBuildingAction(currentPlayingPlayer);
					
					res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;	
				}
				
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				
					//(currentPlayingPlayer);
					//HashMap<String, ArrayList<String>> minionsList = currentPlayingPlayer.getMinions();
				//	ArrayList<String> availableMinions = minionsList.get(currentPlayingPlayer.getPlayerColor());
					for(Player iteratePlayers : BoardGame.playersInGame){
						for(Area a : BoardGame.board_areas){
							if((a.getAreaName().equalsIgnoreCase("isle of gods")) && 
									currentPlayingPlayer.doYouHaveMinionInThisArea(iteratePlayers, a.getAreaName())){
								currentPlayingPlayer.setPlayerAmount(currentPlayingPlayer.getPlayerAmount() + 3);
							}
							System.out.println("Player amout updated: "+currentPlayingPlayer.getPlayerAmount());
							
						}
					}
					res = lastaction(currentPlayingPlayer,res);
				}
				else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer, true);
					return;
				}
			}	
				
			addToDiscardPile(1, this, currentPlayingPlayer, true);
			
				
			}
		},
		
		DUCKMAN("THE DUCKMAN","green","DeckPile",new String[]{"Read Scroll->  Move minion of opponent to another area that is adjacent"}) {
			
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						String result = questionsToAsk("Enter a players piece color to move : nul");
						Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
						mu = new ComponentUtilities();
						mu.displayMinionsOfotherPlayer(selectedPlayer);
						String fromLocation = questionsToAsk("Choose from where the Minion has to be moved:nul");
						String area = BoardGame.getPieceNumberList(fromLocation);
						moveMinionToOtherArea(currentPlayingPlayer, selectedPlayer, area, "");
						
						res = lastaction(currentPlayingPlayer,res);					
						}
					else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer, true);
						return;
					}
				}
				addToDiscardPile(1, this, currentPlayingPlayer, true);
			}
		},
		
		//a few bugs need to be removed
		DRUMKNOTT("DRUMKNOTT","green","DeckPile",new String[]{"Read Scroll->  Play 2 other cards from your hand"}) {
			
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						try {
	                        playAnotherCard(currentPlayingPlayer, this);
	                        playAnotherCard(currentPlayingPlayer, this);
	                        res = lastaction(currentPlayingPlayer,res);
	                    } catch (JSONException e) {
	                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	                    }
	
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer, false);
						return;
					}
				}
			addToDiscardPile(1, this, currentPlayingPlayer, true);	
			}
		},
		CMOTDIBBLER("CMOT DIBBLER","green","DeckPile",new String[]{"Read Scroll->  Roll die to determine action","Play another card"}) {
			// roll die. if 7 take 4 from bank. if 1 pay 2$ to bank or remove 1 YOUR minion.
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						int num = rollDie();
						if(num>=7){
							takeMoneyFromBank(4, currentPlayingPlayer);
						}
						else if(num==1){
							String result = questionsToAsk("Hit 'p' to pay money to bank : Hit 'd' to remove your minion");
							if(result.trim().equalsIgnoreCase("p")){
								payMoneyToBank(2, currentPlayingPlayer);
							}
							else if(result.trim().equalsIgnoreCase("d")){
								removeMinionOFYourOwn(1,currentPlayingPlayer);
							}
						}
						else{
							System.out.println("Number rolled has no action associated..");
						}
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer, true);
						return;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						playAnotherCard(currentPlayingPlayer, this);
						res = lastaction(currentPlayingPlayer,res);
					}
			}
				addToDiscardPile(1, this, currentPlayingPlayer, true);
			}
		},
		DRCRUCES("DR.CRUCES","green","DeckPile",new String[]{"Assasinate","take 3$"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						assasinate(currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						takeMoneyFromBank(3, currentPlayingPlayer);
						 res = lastaction(currentPlayingPlayer,res);
						}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer, true);
						return;
					}
					}
				addToDiscardPile(1, this, currentPlayingPlayer, true);
			}
		},
		CAPTAINCARROT("CAPTAIN CARROT","green","DeckPile",new String[]{"Place minion","Remove Trouble Marker","take 1$"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						
						placeMinionActionPlayerCard(currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						removeTroubleMarker();
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer, true);
						return;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[2])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						takeMoneyFromBank(1, currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
					}
				
				}
				addToDiscardPile(1, this, currentPlayingPlayer, true);
			}
		},
		MRSCAKE("MRS.CAKE","green","DeckPile",new String[]{"Read Scroll->","take 2$","Add building"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						for (WinningCircumstancesFactory.PersonalityCards personalityCard : WinningCircumstancesFactory.PersonalityCards.values()) {
	                        for (Player p : BoardGame.playersInGame) {
	                            //todo: check the exact types in running conditions
	                            if (personalityCard.toString().equals(p.getWinningCondition()))
	                                break;
	                            else
	                                System.out.println("" + p.getWinningCondition());
	                        }
	                    }
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
					}
					
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						takeMoneyFromBank(2, currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer, true);
						return;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[2])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						addBuildingAction(currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
					}
					}
				
			}
		},
		MRBENT("MR.BENT","green","DeckPile",new String[]{"Read Scroll->  Take 10$ loan from bank","Play another card"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
				
				String res = askSymbolsInOrder(this, "0",currentPlayingPlayer);
	
	            while ( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
	                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {
	
	                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
	    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
	                    this.takeLoanFromBank(10, currentPlayingPlayer);
	                    res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
	                }else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
	    				addToDiscardPile(1, this, currentPlayingPlayer, true);
	    				return;
	    			} else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
	    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
	                    try {
	                        playAnotherCard(currentPlayingPlayer, this);
	                        System.out.println("You have played the last action.This card will be discarded");
	                    } catch (JSONException e) {
	                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	                    }
	                    res = lastaction(currentPlayingPlayer,res);
	                }
	            }
				
				
			}
		},
		GROAT("GROAT","green","DeckPile",new String[]{"Place Minion"}) {
			
			@Override
	        public void performTasks(Player currentPlayingPlayer) throws JSONException {
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						placeMinionActionPlayerCard(currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer, true);
						return;
					}
				}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
	        }
		},
		GASPODE("GASPODE","green","DeckPile",new String[]{"Interrupt"}) {
			
			@Override
	        public void performTasks(Player currentPlayingPlayer) throws JSONException {
				InterruptCard ic = new InterruptCard();
				ic.setInterruptMessage("SM");
	            addToDiscardPile(1, this, currentPlayingPlayer, true);
			}
		},
		FRESHSTARTCLUB("FRESH START CLUB","green","DeckPile",new String[]{"Interrupt"}) {
			@Override
	        public void performTasks(Player currentPlayingPlayer) throws JSONException {
				String res = questionsToAsk("Player "+currentPlayingPlayer.getPlayerColor().toUpperCase()+
						" Do you want to play "+getName()+ " card. Hit Y or N:nul");
				if(res.equalsIgnoreCase("y")){
					placeMinionActionPlayerCard(currentPlayingPlayer);
					addToDiscardPile(1, this, currentPlayingPlayer,true);
				}
				else if(res.equalsIgnoreCase("n")){
					System.out.println("You choose not to play this card");
				}
			}
	
		},
		FOULOLERON("Foul Ole ron","green","DeckPile",new String[]{"Read Scroll->  Move opponent minion in adjacent area","Play another card"}) {
			// moveminion of OTHER player from an area to adjacent area
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
			String res = askSymbolsInOrder(this, "0",currentPlayingPlayer);
	
			while( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1]))){
			
				if(res.split(":")[0].trim().equalsIgnoreCase("Read Scroll->  Move opponent minion in adjacent area")&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
	        	
	        	String result = questionsToAsk("Enter a players piece color to move (R/G/Y/B) : nul");
				Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
				ComponentUtilities mun = new ComponentUtilities();
				mun.displayMinionsOfotherPlayer(selectedPlayer);
			
				String fromLocation = questionsToAsk("Enter area name from where the Minion has to be moved:nul");
			
				moveMinionToOtherArea(currentPlayingPlayer, selectedPlayer, fromLocation, "");
	        	
			      res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
			}
	        else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
	        	playAnotherCard(currentPlayingPlayer, this);
	        	res = lastaction(currentPlayingPlayer,res);
	        }else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer,true);
				return;
			}
	        }
	        addToDiscardPile(1, this, currentPlayingPlayer,true);
	        }
		},
		
		THEFOOLSGUILD("The Fools Guild","green","DeckPile",new String[]{"Read Scroll->  Select Player your choice. "
				+ "If they cannot give you 5$ place this card in front of them.This card goes in their hand and cannot be removed","Place Minion"}) {
			
			@Override
	        public void performTasks(Player currentPlayingPlayer) throws JSONException {
	
	            String res = askSymbolsInOrder(this, "0",currentPlayingPlayer);
	
	            while( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
	                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {
	
	                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
	    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
	
	                    String result = questionsToAsk("Enter the color of player you want to select (R/G/Y/B) in this format : nul");
	                    Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
	                    String answer = questionsToAsk("Hit '1' to pay 5$ or hit '2' to receive card");
	                    if (answer.trim().equalsIgnoreCase("1")) {
	                        takeMoneyFromPlayer(5, currentPlayingPlayer, selectedPlayer);
	                        res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
	                    } else if (answer.trim().equalsIgnoreCase("2")) {
	                        selectedPlayer.getPlayersPlayingCard().add(this);
	                        currentPlayingPlayer.getPlayersPlayingCard().remove(this);
	                        res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
	                    }
	
	                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
	    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
	                    // call appropriate function
	                	placeMinionActionPlayerCard(currentPlayingPlayer);
	                	res = lastaction(currentPlayingPlayer,res);
	                }else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
	    				addToDiscardPile(1, this, currentPlayingPlayer,true);
	    				return;
	    			}
	            }
	            addToDiscardPile(1, this, currentPlayingPlayer,true);
	        }
		},
		
		//a few bugs need to be removed
		THEFIREBRIGADE("The fire Brigade","green","DeckPile",new String[]{"Read Scroll->  Select player. Take 5$ from him. "
				+ "Else you can remove a building of his","Play another card"}) {
			// select plaeyr if they dont give you 5$ remove 1 of his building from board
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this, "0",currentPlayingPlayer);
	
	            while ( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
	                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {
	
	                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
	    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
				String result = questionsToAsk("Enter the color of player color you want to select: nul");
				Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
				if(!(takeMoneyFromPlayer(5, currentPlayingPlayer, selectedPlayer))){
					// you want to remove his building
					System.out.println("Cannot give you money. You can remove his building now...");
					removeBuilding(currentPlayingPlayer, selectedPlayer);
					
				}
				res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
			}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer,true);
				return;
			}
	                else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
	    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
	                	playAnotherCard(currentPlayingPlayer, this);
	                	res = lastaction(currentPlayingPlayer,res);
	                	}
	                }
	            addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		INIGOSKIMMER("Inigo Skimmer","green","DeckPile",new String[]{"Assasinate","Take 2$"}) {
			
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						assasinate(currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
					}
					
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						takeMoneyFromBank(2, currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
						}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
							addToDiscardPile(1, this, currentPlayingPlayer,true);
							return;
						}
					}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		HISTORYMONKS("History Monks","green","DeckPile",new String[]{"Read Scroll->  Draw 4 cards from discard pile","Place minion"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						drawCardsFromDiscardPile(4, currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
					
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						placeMinionActionPlayerCard(currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
					}
					
					}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		HEX("Hex","green","DeckPile", new String[]{"Read Scroll->  Draw 3 cards from deck","Add building"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						drawCardsFromDeck(3, currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
					
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){

						addBuildingAction(currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
					}
					}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		HERENOW("Here N Now","green","DeckPile",new String[]{"Read Scroll->  Roll Die. If 7 take 3$ from player of ur choice."
				+ "IF 1 remove a minion of your own","Play Another card"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						int num = rollDie();
						if(num >= 7){
							String playerToSelect = questionsToAsk("Enter player piece color to select:nul");
							Player player = selectPlayer(currentPlayingPlayer, playerToSelect);
							takeMoneyFromPlayer(3, currentPlayingPlayer, player);
							res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
						}
						else if(num==1){
								//removeMinionOFYourOwn(1,currentPlayingPlayer);
								String minionlocation = questionsToAsk("Enter area name to remove your minion from : nul");
								removeMinionFromLocation(1, currentPlayingPlayer, minionlocation.trim());
								res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
						}
						else{
							System.out.println("This" +num+ "from roll die has no effect");
							res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
						}
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
					
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						playAnotherCard(currentPlayingPlayer, this);
						res = lastaction(currentPlayingPlayer,res);
					}
					}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		
		HARRYKING("Harry King","green","DeckPile",new String[]{"Place minion","Read Scroll->  Discard cards of your choice"}) {
			// discrad as many cards as uw ish, get 2 for each
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					placeMinionActionPlayerCard(currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer,true);
					return;
				}
				
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					discardCardsPerYourWish(currentPlayingPlayer, this, 2);
					res = lastaction(currentPlayingPlayer,res);
				}
				}
			addToDiscardPile(1, this, currentPlayingPlayer,true);
		}
		},
		GRYLE("MR.GRYLE","green","DeckPile",new String[]{"Assasinate","Take 1$"}) {
			
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						assasinate(currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
						
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
					
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						takeMoneyFromBank(1, currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
					}
			}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		HARGAHOUSEOFRIBS("HARGA HOUSE OF RIBS","green","DeckPile",new String[]{"Take 3$","Place Minion"}) {
			
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						takeMoneyFromBank(3, currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
					
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
							placeMinionActionPlayerCard(currentPlayingPlayer);
							res = lastaction(currentPlayingPlayer,res);
						}
					}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		THEPEELEDNUTS("THE PEELED NUTS","green","DeckPile",new String[]{"No actions to perform on this card.."}) {
			
			
		},
		THEOPERAHOUSE("THE OPERA HOUSE","green","DeckPile",new String[]{"Add building","Read Scroll->  Take 1$ for minions in Isle of GOds"}) {
			
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						addBuildingAction(currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
						
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
					
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						//getMoneyForMinionsinArea(1, currentPlayingPlayer, "Isle of Gods");
						for(Player iteratePlayers : BoardGame.playersInGame){
							for(Area a : BoardGame.board_areas){
								if((a.getAreaName().equalsIgnoreCase("isle of gods")) && 
									currentPlayingPlayer.doYouHaveMinionInThisArea(iteratePlayers, a.getAreaName())){
									currentPlayingPlayer.setPlayerAmount(currentPlayingPlayer.getPlayerAmount() + 1);
									
							
								}
								System.out.println("Player amout updated: "+currentPlayingPlayer.getPlayerAmount());
								
							}
						}
						
						res = lastaction(currentPlayingPlayer,res);
						}
					}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
			
		},
		NOBBYNOBBSS("NOBBY NOBBSS","green","DeckPile",new String[]{"Read Scroll->  Take 3$ from player of your choice","Play another card"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						String result = questionsToAsk("Enter a players piece color you want to take money from : nul");
						Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
						takeMoneyFromPlayer(3, currentPlayingPlayer, selectedPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						playAnotherCard(currentPlayingPlayer, this);
						res = lastaction(currentPlayingPlayer,res);
					}
					}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		MODO("MODO","green","DeckPile",new String[]{"Read Scroll->  Discard one card of your choice","Place Minion"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					//	discardCardsPerYourWish(currentPlayer, this, 0);
						discardACard(currentPlayingPlayer, this);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						placeMinionActionPlayerCard(currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
						}
					}
				addToDiscardPile(1, this,currentPlayingPlayer,true);
				}
				
			
		},
		THEMENDEDDRUM("THE MENDEDDRUM","green","DeckPile",new String[]{"Add building","Take 2$"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
						if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
								!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						addBuildingAction(currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						takeMoneyFromBank(2, currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
						}
						
					}
				addToDiscardPile(1, this,currentPlayingPlayer,true);
				}
				
		},
		LIBRARIAN("LIBRARIAN","green","DeckPile",new String[]{"Read Scroll->  Draw 4 cards from deck"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
						if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
								!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
								System.out.println("4 cards will be drawn from deck...");
								drawCardsFromDeck(4, currentPlayingPlayer);
								res = lastaction(currentPlayingPlayer,res);
						}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
							addToDiscardPile(1, this, currentPlayingPlayer,true);
							return;
						}
				}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		LEONARDOFQUIRM("LEONARD OF QUIRM","green","DeckPile",new String[]{"Read Scroll->  Draw 4 cards from deck"}) {
			// draw 4 cards from the deck
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
						if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
								!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
							System.out.println("4 cards will be drawn from deck...");
								drawCardsFromDeck(4, currentPlayingPlayer);
								res = lastaction(currentPlayingPlayer,res);
						}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
							addToDiscardPile(1, this, currentPlayingPlayer,true);
							return;
						}
				}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		SHONKYSHOP("SHONKY SHOP","green","DeckPile",new String[]{"Read Scroll->  Discard cards as per yur wish","Add building"}) {
			
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
						if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0]) && 
								!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
							discardCardsPerYourWish(currentPlayingPlayer, this, 1);
							res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
						}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
							addToDiscardPile(1, this, currentPlayingPlayer,true);return;
						}
						else if(res.split(":")[0].trim().equalsIgnoreCase("Add building") && 
								!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
							
							addBuildingAction(currentPlayingPlayer);
							res = lastaction(currentPlayingPlayer,res);
						}
				}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		SACHARISSACRIPSLOCK("SACHARISSA CRIPSLOCK","green","DeckPile",new String[]{"Read Scroll->  Take 1$ for every trouble marker on board","Place Minion"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0]) && 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						for(Area a: BoardGame.board_areas)
							if(a.isTroubleMarkers())
								takeMoneyFromBank(1, currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);return;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase("Place Minion") && 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						placeMinionActionPlayerCard(currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
					}
				}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		ROSIEPALM("ROSIE PALM","green","DeckPile",new String[]{"Place Minion","Read Scroll->  Take money in exchange for cards from another player"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0]) && 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						placeMinionActionPlayerCard(currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1]) && 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				
						takeMoneyExchangeCardsFromAnotherPlayer(currentPlayingPlayer,2);
						res = lastaction(currentPlayingPlayer,res);
					}
				}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		
		//a few bugs need to be removed
		RINCEWIND("RINCEWIND","green","DeckPile",new String[]{"Read Scroll->Move minion from area with trouble marker to an adjacent area","Play another Card"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				System.out.println("Random Event Card will occur ");
				RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayingPlayer, 
						RandomEventCard.Flood, this);
				//RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayingPlayer, RandomEventCard.getShuffledRandomEventCard(), this); //Changed from fire to getshuffledrandomeventcard
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
	//				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])){
	//					//
	//				}
					 if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0]) && 
								!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						System.out.println("You can move your minions from this area: ");
					//	BoardGame.displayMinionsForPlayerOnBoard(currentPlayingPlayer,0);
						System.out.printf("Area Names with Trouble Markers\n");
						int ocunt =1;
						ArrayList<String> tempar = new ArrayList<String>();
						for(ArrayList<String> st : currentPlayingPlayer.getMinions().values()){
							for(String s : st){
								if(!s.equalsIgnoreCase("") ){
									if( currentPlayingPlayer.getAreaInstanceFromAreaName(s).isTroubleMarkers()){
										
										System.out.printf("%2s%15s",ocunt,s);
									tempar.add(s);
									BoardGame.pieceNumberAreaList.add(""+ocunt+":"+s);
									ocunt++;
								}
							}
						}
						
						}
							
						
						String output = questionsToAsk("Which area you want to move minion from:nul");
						String areaname = BoardGame.getPieceNumberList(output);
						System.out.println();
						removeMinionFromLocation(1, currentPlayingPlayer, areaname);
						System.out.println("Minion removed from "+areaname);
						System.out.println("You can move minions to one of these areas.....");
						for(Player p : BoardGame.playersInGame){
							if(p.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor())){
								
								for(String temp1 : tempar){
									
									if(!temp1.trim().equalsIgnoreCase("")){
										for(Area a : BoardGame.board_areas){
											if(temp1.trim().equalsIgnoreCase(a.getAreaName())){
												System.out.println("Adjacent areas for "+temp1);
												String result = BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.areaDetails, temp1);
												BoardGame.displayAdjacentAreas(result,0);
												System.out.println();
											}
										}
									}
								}
									}
								}
						String out = questionsToAsk("Which adjacent area you want to move minion to:nul");
						String areanam = BoardGame.getPieceNumberList(out);
						
						currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), areanam);
						Area thisarea = currentPlayingPlayer.getAreaInstanceFromAreaName(areanam);
						thisarea.setMinionColor(areanam); // Sanchit Fix, Minion Count not getting updated
						if(!(thisarea.isTroubleMarkers()) && currentPlayingPlayer.totalMinionsInAreaForAllPlayers(areanam)>1) //Sanchit Fix
							thisarea.setTroubleMarkers(true);
						
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
				}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						playAnotherCard(currentPlayingPlayer, this);
						res = lastaction(currentPlayingPlayer,res);
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
				}
			addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
			
		},
		THEROYALMINT("THE ROYAL MINT","green","DeckPile",new String[]{"Add building","Take 5$"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0]) && 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addBuildingAction(currentPlayingPlayer);
						
					 res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						takeMoneyFromBank(5, currentPlayingPlayer);
						res = lastaction(currentPlayingPlayer,res);
						}
					else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
							addToDiscardPile(1, this, currentPlayingPlayer,true);
							return;
						}
				}
			addToDiscardPile(1, this, currentPlayingPlayer,true);	
			}
			
		},
		QUEENMOLLY("QUEEN MOLLY","green","DeckPile",new String[]{"Place minion","Read Scroll->  Take 2 cards from player of your choice"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						placeMinionActionPlayerCard(currentPlayingPlayer);
						res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer,true);
						return;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						System.out.println("Take 2 laying cards frm player of your choice");
						String result = questionsToAsk("Enter a players piece color you want to sleect : nul");
						Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
						
						System.out.println("Your cards as of now Player " +selectedPlayer.getPlayerColor());
						for(GreenPlayerCardEnum gc : selectedPlayer.getPlayersPlayingCard())
							System.out.print(gc.getName()+", ");
						int num = 2;
						while(num!=0){
							System.out.println();
							String ress = questionsToAsk("Enter card you want to give : nul");
							for(GreenPlayerCardEnum gc : selectedPlayer.getPlayersPlayingCard()){
								if(ress.equalsIgnoreCase(gc.getName())){
									selectedPlayer.getPlayersPlayingCard().remove(gc);
									currentPlayingPlayer.getPlayersPlayingCard().add(gc);
									num--;
									break;
								}
							}
						}
						res = lastaction(currentPlayingPlayer,res);	
					}
				}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		PINKPUSSYCATCLUB("PINK PUSSY CAT CLUB","green","DeckPile",new String[]{"Take 3$","Play another card"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
						(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
				
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					takeMoneyFromBank(5, currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer,true);
					return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					playAnotherCard(currentPlayingPlayer, this);
					res = lastaction(currentPlayingPlayer,res);
				}
				}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		ZORGOTHERETROPHRENOLOGIST("ZORGO THE RETROPHRENOLOGIST","green","DeckPile",new String[]{"Read Scroll->  Exchange perosnality card","Add building"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
	
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
			(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					currentPlayingPlayer.exchangePersonalityCard();
					res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer,true);return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addBuildingAction(currentPlayingPlayer);
					res = lastaction(currentPlayingPlayer,res);
				}
				}
				addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
			
			
		},
		DRWHITEFACE("DR.WHITEFACE","green","DeckPile",new String[]{"Read Scroll->  Take 5$ from player of choice or give them this card"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				
				PlayerCardUtility.getEnumInstance("The Fools Guild").performTasks(currentPlayingPlayer);
			}
		},
		WALLACESONKY("WALLACE SONKY","green","DeckPile") {
			@Override
			public void performTasks(Player currentPlayingPlayer){
				
				
			}
		},
		THESEAMSTRESSESGUILD("THE SEAMSTRESSES GUILD","green","DeckPile",new String[]{"Read Scroll->  Take money in exchange of cards from Another player ","Place minion"}) {
			@Override                                                              
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
			(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
					
					
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0].trim())&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					takeMoneyExchangeCardsFromAnotherPlayer(currentPlayingPlayer,2);
					res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);;
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer,true);return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit")))
					placeMinionActionPlayerCard(currentPlayingPlayer);
				res = lastaction(currentPlayingPlayer,res);
				}
			addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		},
		MRPINANDMRTULIP("MR.PIN & MR.TULIP","green","DeckPile",new String[]{"Assasinate","Take 1$->From Bank"}) {
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				String res = askSymbolsInOrder(this ,"0",currentPlayingPlayer);
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
			(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					assasinate(currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim(), currentPlayingPlayer);
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer,true);
					return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit")))
					takeMoneyFromBank(1, currentPlayingPlayer);
				res = lastaction(currentPlayingPlayer,res);
				}
			addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
			
		},
		THETHIEVESGUILD("THE THIEVES GUILD","green","DeckPile",new String[]{"Read Scroll->  2$ will be taken from every Player","Place Minion"}) {
			// take 2$ from every other player
			@Override
			  public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
				String res = askSymbolsInOrder(this ,"0", currentPlayingPlayer);
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
			(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					for(Player p : BoardGame.playersInGame){
						if(!(p.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor())))
							takeMoneyFromPlayer(2, currentPlayingPlayer, p);
					}
					res = askSymbolsInOrder(this, res.split(":")[1].trim(), currentPlayingPlayer);
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer,true);return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					placeMinionActionPlayerCard(currentPlayingPlayer);
				res = lastaction(currentPlayingPlayer,res);
				}
				}
			addToDiscardPile(1, this, currentPlayingPlayer,true);
			}  
		}, 
		
		GIMLETDWARFDELICATESSEN("GIMLET DWARF DELICATESSEN","green","DeckPile",new String[]{"Take 3$","Place minion"}){
			@Override
			public void performTasks(Player currentPlayingPlayer) throws JSONException{
				String res = askSymbolsInOrder(this ,"0", currentPlayingPlayer);
				while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
			(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
				
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					takeMoneyFromBank(3, currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim(), currentPlayingPlayer);
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer,true);return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					placeMinionActionPlayerCard(currentPlayingPlayer);
					res = lastaction(currentPlayingPlayer,res);
				}
				}
			addToDiscardPile(1, this, currentPlayingPlayer,true);
			}
		};
		
		/** The location. */
		private String location ;
		
		/** The color. */
		private String color;
	
		/** The name. */
		private String name;
	
		private String[] symbols;
		
		private GreenPlayerCardEnum(){}
		
		/** The id of playing card **/
		/**
		 * Instantiates a new player card.
		 *
		 * @param color the color
		 * @param name the name
		 * @param location the location
		 */
		ComponentUtilities mu ;
		static ArrayList<String> pieceList = new ArrayList<String>();
		private GreenPlayerCardEnum( String name, String color, String location, String... symbols){
	
			setColor(color);
			setLocation(location); 
			setName(name);
			setSymbols(symbols);
		}
		
		
		/**
		 * Gets the color.
		 *
		 * @return the color
		 */
		public String getColor() {
			return color;
		}
	
		/**
		 * Sets the color.
		 *
		 * @param color the color to set
		 */
		public void setColor(String color) {
			this.color = color;
		}
	
		/**
		 * Gets the location.
		 *
		 * @return the location
		 */
		public String getLocation() {
			return location;
		}
	
		/**
		 * Sets the location.
		 *
		 * @param location the location of the player card to set
		 * would be set to either players hand, discard pile or draw pile
		 */
		public void setLocation(String location) {
			this.location = location;
		}
	
		/**
		 * Gets the name.
		 *
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	
		/**
		 * Sets the name.
		 *
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		
	
		/**
		 * @return the symbols
		 */
		public String[] getSymbols() {
			return symbols;
		}
		
		/**
		 * @param symbols the symbols to set
		 */
		public void setSymbols(String[] symbols) {
			this.symbols = symbols;
		}
		
		public void moveMinion() {
		}
		    
		public void assasinate(String pieceToRemove) {
		}
		
		/**
		 * this method will allow player to play another player card from his hand
		 * first dispalying to him the player cards available in his hand
		 */
		 @Override   
		public void playAnotherCard(Player currentPlayingPlayer, GreenPlayerCardEnum enumType) throws JSONException {
			 
			 System.out.println("You need to play another card of yours..");
			 
			 System.out.printf("%1s%15s","   ","Cards to choose from.");
			 int c =1;
			 for(GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard()){
				if(!(gc.getName().equalsIgnoreCase(enumType.getName()))){
					System.out.printf("%1s%2s%15s",c,"  ",gc.getName());
					BoardGame.pieceNumberAreaList.add(""+c+":"+gc.getName());
					c++;
				}
			 }
			 String res1 = questionsToAsk("Choose which card to play:nul");
			 String res = BoardGame.getPieceNumberList(res1);
			 for(GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard())
					if((gc.getName().equalsIgnoreCase(res.trim()))){
						gc.performTasks(currentPlayingPlayer);
						break;
					}
			 System.out.println("Action completed..");		
		}
		@Override    
		public void interrupt() {
		}
	
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {}
		
		/**
		 * this method will ask a player all his actions on player card in order
		 * if the player enters the same action as chosen first or the action has been chosen not in order then will throw an error message
		 * and ask the user to reenter the choice
		 * @throws JSONException 
		 */
		@Override
		public String askSymbolsInOrder(GreenPlayerCardEnum tempEnum, String result, Player currPlayer) throws JSONException {
			String res = "enter";
			res = UserInputUtility.USERINPUTUTILITYENUM.getUserInput(tempEnum,result,currPlayer);
			return res;
		}
		
		/**
		 * this method will allow a current player to take specified amount from another player
		 */
		@Override
		public boolean takeMoneyFromPlayer(int amt, Player currentPlayingPlayer, Player fromPlayer) {
			
		if(fromPlayer != null){
			System.out.println("Taking "+amt+"$'s from "+fromPlayer.getPlayerColor()+ "player...");
			String res = questionsToAsk("Would you Give me $'s .Hit Y for Yes or elsewise : nul");
			if(res.equalsIgnoreCase("y")){
				currentPlayingPlayer.setPlayerAmount(currentPlayingPlayer.getPlayerAmount() + amt);
				fromPlayer.setPlayerAmount(fromPlayer.getPlayerAmount() - amt);
				System.out.println("Money taken " +amt+" $'s from "+fromPlayer);
			}			
			else{
				System.out.println("--");
				return false;
			}
			
			System.out.println("Action completed..");
			return true;
			}else
				return false;
		}
		
		public boolean takeMoneyFromAllPlayer(int amt, Player currentPlayingPlayer) {
			
			for(Player iteratePlayer: BoardGame.playersInGame){
				if(!(iteratePlayer.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor().trim()))){
					System.out.println("Taking "+amt+"$'s from "+iteratePlayer.getPlayerColor()+ "player...");
					String res = questionsToAsk("Would you Give me $'s .Hit Y for Yes or elsewise : nul");
					if(res.equalsIgnoreCase("y")){
						currentPlayingPlayer.setPlayerAmount(currentPlayingPlayer.getPlayerAmount() + amt);
						iteratePlayer.setPlayerAmount(iteratePlayer.getPlayerAmount() - amt);
						System.out.println("Money taken " +amt+" $'s from "+iteratePlayer);
					}		
				}
					
			}
				
			System.out.println("Action completed..");
			return true;
		}
		
		/**
		 * this method will perform the action of giving specified number of 
		 * player cards to another player
		 * 
		 */
		@Override
		public void givePlayingCards(Player currrentPlayer , int number) {
			
			String result = questionsToAsk("Enter a players piece color to give playing card to : nul");
			Player selectedPlayer = selectPlayer(currrentPlayer, result);
			
			while(number!=0){
				
				System.out.println("Your cards as of now player "+currrentPlayer.getPlayerColor());
				for(GreenPlayerCardEnum gc : currrentPlayer.getPlayersPlayingCard())
					System.out.print(gc.getName()+", ");
				
				playingCardsAction(selectedPlayer,currrentPlayer,number);
				number--;
			}
			System.out.println("Action completed..");
		}
		
		/**
		 * this method will perform the action of taking the specified number of 
		 * player cards to another player
		 */
		@Override
		public void takePlayingCards(Player currrentPlayer , int number, boolean b) {
			
			System.out.println("Taking " +number+ " player cards..");
			String result = questionsToAsk("Enter a players piece color to take playing card from : nul");
			Player selectedPlayer = selectPlayer(currrentPlayer, result);
			
			while(number!=0){
			System.out.println("Your cards as of now  "+selectedPlayer.getPlayerColor()+" player");
			int c = 1;
			System.out.printf("%1s%15s","       "," PLAYER CARDS");
			for(GreenPlayerCardEnum gc : selectedPlayer.getPlayersPlayingCard()){
				System.out.printf("%1s%1s%15s",c,"  ",gc.getName());
				BoardGame.pieceNumberAreaList.add(""+c+":"+gc.getName());
				c++;
			}
			c=0;
			
			playingCardsAction(currrentPlayer,selectedPlayer,number);
			number--;
			}
			System.out.println("Taking Card action completed..");
		}
		/**
		 * returns a player from his piece color
		 */
		@Override
		public Player selectPlayer(Player currentPlayer, String playerToSelect) {
			
			Player temp = null;
			if(playerToSelect instanceof String && currentPlayer instanceof Player){
			if((playerToSelect.equalsIgnoreCase("r") || playerToSelect.equalsIgnoreCase("g") ||
					playerToSelect.equalsIgnoreCase("b") || playerToSelect.equalsIgnoreCase("y"))){
				for(Player pc : BoardGame.playersInGame){
					if(pc.getPlayerColor().equalsIgnoreCase(playerToSelect))
					temp = pc;
				}
			}
			else{
				System.out.println("Select color of existing player");
				return null;
			}
			System.out.println("Player Selected...");
			return temp;
			}
			else
				return null;
		}
	
		/**
		 * common method which will ask the user any number of question as you provide
		 * questions should be given in as "what is your name:nul"
		 */
	
		@Override
		public String questionsToAsk(String qns) {
			System.out.println();
			String result = null;
			Scanner in = new Scanner(System.in);
			String[] temp = qns.split(":"); 
	
			for(int i = 0 ; i < temp.length ; i++){
				if(!temp[i].trim().equalsIgnoreCase("nul"))
				System.out.printf("%-40s",temp[i]);
			}
			result = in.nextLine();
			if(!result.isEmpty())
				return result;
			else{
				System.out.println("Enter a valid input");
				questionsToAsk(qns);
			}
			return result;
		}
		
		/**
		 * takes an amount as loan from bank
		 */
		@Override
		public void takeLoanFromBank( int amt, Player currentPlayer) {
			
			System.out.println("Taking "+amt+" $'s from the bank...");
			if(amt!=0){
				currentPlayer.setPlayerAmount(currentPlayer.getPlayerAmount()+amt);
				BoardGame.setBank(BoardGame.getBank()-amt);
				
				//added this to make sure we display the loans taken by the player just for the Chrysoprase - Sanchit 
				currentPlayer.setPlayerLoan(currentPlayer.getPlayerLoan()+amt);
				System.out.println("Loan taken "+amt + " $'s from bank..");
				System.out.println("Player Amount Updated..");
			}
			else{
				System.out.println("Entered amount cannot be 0");
			}
			System.out.println("Action completed..");
		}
		
		/**
		 * roll die between 1 and 12
		 */
		@Override
		public int rollDie() {
	
			Random num = new Random();
			int i = num.nextInt(12);
			
			if(i!=0)
				return i; 
			else
				rollDie();
			return 0;
		}
		/**
		 * withdraw money from bank
		 */
		@Override
		public void takeMoneyFromBank(int amt, Player currentPlayer) {

			currentPlayer.setPlayerAmount(currentPlayer.getPlayerAmount()+amt);
			BoardGame.setBank(BoardGame.getBank()-amt);
			System.out.println("Players amount updated...");
		}
		/**
		 * deposit money to the bank
		 */
		@Override
		public void payMoneyToBank(int amt, Player currentPlayer) {
			
			
			System.out.println("\nPaying "+amt+"$'s to Bank");
			if(amt!=0 && !(currentPlayer.equals(null))){
				currentPlayer.setPlayerAmount(currentPlayer.getPlayerAmount() - amt);
				BoardGame.setBank(BoardGame.getBank() + amt);
				System.out.println(amt+"$'s Paid to Bank");
			}
			else{
				System.out.println("\nAmt cannot be zero or ");
			}
			
		}
		/**
		 * removes specified number of  minion for a player
		 * @throws JSONException 
		 */
		@Override
		public void removeMinion(int num, Player currentPlayer) throws JSONException {
			
			BoardGame.displayMinionsForPlayerOnBoard(currentPlayer,0);
				
				while(num!=0){
					String minionlocation = questionsToAsk("Enter area name to remove your minion from : nul");
					String area = BoardGame.getPieceNumberList(minionlocation);
					for(ArrayList<String> str : currentPlayer.getMinions().values()){
						for(String temp : str){
							if(area.equalsIgnoreCase(temp)){
								currentPlayer.getMinions().remove(temp);
								currentPlayer.setMinionQuantity(currentPlayer.getMinionQuantity()-1);
								currentPlayer.getAreaInstanceFromAreaName(temp).setTroubleMarkers(false);
								
							}
							break;
						}
					}
					num--;
				}
				
		}
		/**
		 * Remove a Minion provided location
		 */
		
		public void removeMinionFromLocation(int num, Player currentPlayer, String fromLocation) {
			
			while(num!=0){
			for(ArrayList<String> str : currentPlayer.getMinions().values()){
				for(String temp : str){
					if(fromLocation.equalsIgnoreCase(temp.trim()) && !(temp.equalsIgnoreCase(""))){
						currentPlayer.removeMinionInList(fromLocation);
						currentPlayer.setMinionQuantity(currentPlayer.getMinionQuantity()-1);
						currentPlayer.getAreaInstanceFromAreaName(temp).setTroubleMarkers(false);
						break;
					}
					
				}
				num--;
			}			
		}
	}
		
	
		/**
		 * This method moves minion of a selected player from one area to adjacent area.
		 * Takes in input the player who wants to move minion, the player who's minion he wants to move
		 * and the minion location from where it has to be moved.
		 * The method will list you all the adjacent areas for this minion and then you can 
		 * choose one area to place this minion into.
		 */
		@Override
		public void moveMinionToOtherArea(Player currentPlayer, Player fromPlayer,
				String fromLocation, String toLocation) throws JSONException {
	
			if(!(fromPlayer.equals(null))){
				
				if(!(InterruptCard.wantToPlayInterruptCard(fromPlayer, currentPlayer).equalsIgnoreCase("success"))){
				String loc = BoardGame.getPieceNumberList(fromLocation);	
				/*HashMap<String, ArrayList<String>> minionsList = fromPlayer.getMinions();
				ArrayList<String> availableMinions = minionsList.get(fromPlayer.getPlayerColor());
				//currentPlayer.placeMinion(toLocation);
				for (String temp: availableMinions){
					if(temp.trim() != ""){
						if(!temp.equals(loc)){
							System.out.println();
							System.out.println("Player has a Minion in the below location ");
							System.out.println(temp);
						}
						//System.out.println("Below are the possible Adjacent Areas ");
					}
				}*/
				String result = BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.areaDetails, loc);
				BoardGame.displayAdjacentAreasInMoving(result, loc);
				
				String res = questionsToAsk("Choose area to whose adjacent area you need to move minion to : nul");
				String area = BoardGame.getPieceNumberList(res);
				// record the result and place the opposite players minion there
				if(!area.equals(null)){
					
					removeMinionFromLocation(1, fromPlayer, loc);
						
					fromPlayer.setMinions(fromPlayer.getPlayerColor(), area);
					
					for(Area tempAreas : BoardGame.board_areas){
						if(tempAreas.getAreaName().equalsIgnoreCase(area.trim())){
							tempAreas.setMinionColor(area.trim());
							if(!(tempAreas.isTroubleMarkers()) && currentPlayer.totalMinionsInAreaForAllPlayers(area.trim())>1)
							tempAreas.setTroubleMarkers(true);
						}
					}
				}
				}
				else{
					System.out.println("Other player has played an interrupt card");
				}
		}
		
		}
		/** 
		 * this method would first display a list of areas from where the building can be removed
		 * This method removes the building of the opposite player from the area chosen
		 */
		@Override
		public void removeBuilding(Player currentPlayer, Player fromPlayer) {
			
			System.out.println("Opposite Players Areas where he has Building,You can select from below ");
			if(!(fromPlayer.getPlayerAreas().isEmpty())){
				for(Area str : fromPlayer.getPlayerAreas()){
					System.out.print(str.getAreaName() + ", ");
				}
			}
			String res = questionsToAsk("Enter the area name you want to remove building from : nul");
			int areaIndex = 0;
			for(Area area : fromPlayer.getPlayerAreas()){				
				if(res.trim().equalsIgnoreCase(area.getAreaName())){
					//fromPlayer.getPlayerAreas().remove(area);
					fromPlayer.removeBuildingForPlayer(areaIndex);
					area.setBuildngs(false);
					area.removePlayersInThisAreas(fromPlayer);
					BoardGame.setCityAreaCardRepo(fromPlayer.getCityReaCardFromAreaName(area.getAreaName()));
					fromPlayer.getCityAreaCardsStore().remove(fromPlayer.getCityReaCardFromAreaName(area.getAreaName()));
					//area.removeBuilding(area.getAreaName());
					break;
				}
				++areaIndex;
				
			}
			
		}
		
		/**
		 * general method which will allow the player to take cards from the 
		 * deck of player cards aside board game
		 */
		@Override
		public void drawCardsFromDeck(int num , Player currentPlayer) {
			
			while(num!=0){
				Random rand = new Random();
				int n = rand.nextInt(BoardGame.player_cards.size()-1);
				if(BoardGame.player_cards.get(n) instanceof GreenPlayerCardEnum){
					currentPlayer.getPlayersPlayingCard().add(BoardGame.player_cards.get(n));
					BoardGame.getDiscardPilePlayerCards().add(BoardGame.player_cards.get(n));
					BoardGame.player_cards.remove(BoardGame.player_cards.get(n));
					num--;
					System.out.println("Cards drawn from the pile..");
					//fillYourHandWIthPlayerCard(1,currentPlayer); // trial
				}
				else
					System.out.println("Not sufficient player cards are remaining in the deck");
			}
			
		}
		
		/**
		 * @throws JSONException 
		 * this method removes the minions of another player.
		 * takes input as number of minions, the current player and the player from whom the minion
		 * has to be taken from
		 */
		@Override
		public boolean removeMinionOFAnotherPlayer(int num, Player currentPlayer,
				Player fromPlayer) throws JSONException {
			
			String r = InterruptCard.wantToPlayInterruptCard(fromPlayer, currentPlayer);
			
			if(!(r.equalsIgnoreCase("gasp")) || r.equalsIgnoreCase("fsc")){
				System.out.printf("%25s%15s\n","Area Name","Player Piece Color");
				displayPieceList(fromPlayer);
				String minionAreaToRemove = questionsToAsk("Choose from where Minion has to be Removed:nul");
				String area = BoardGame.getPieceNumberList(minionAreaToRemove);
				if(fromPlayer.getAreaInstanceFromAreaName(area).isTroubleMarkers()){
					removeMinionFromLocation(1, fromPlayer, area);
				System.out.println("Minion assasinated..");
				
				if(r.equalsIgnoreCase("fsc")){
					PlayerCardUtility.getEnumInstance("Fresh Start Club").performTasks(fromPlayer);
				}
				return false;
				}
			}
			if(r.equalsIgnoreCase("gasp")){
				System.out.println("Other player has played GASPODE interrupt card");
			}
			return true;
		}
	
		private String getFromPieceList(String minionAreaToRemove) {
			String res = null;
			for(String s : pieceList){
				if(Integer.parseInt(s.split(":")[0].trim()) == Integer.parseInt(minionAreaToRemove.trim())){
					res = s.split(":")[1].trim();
				}
			}
			pieceList.clear();
			return res;
		}


		@Override
		public void removeMinionOFYourOwn(int num, Player currentPlayer) throws JSONException {
			
			removeMinion(num, currentPlayer);
		}
	
		/**
		 * Adds the specified players playing cards to the discard pile list on the board
		 */
		@Override
		public void addToDiscardPile(int num, GreenPlayerCardEnum gc,Player ps, boolean addCard) {
			
			if(ps.getPlayersPlayingCard().size() <= 5){
				while(num!=0){
					if(gc instanceof GreenPlayerCardEnum){
						System.out.println(gc.getName() + " Card being added to discard Pile...");
						ps.getPlayersPlayingCard().remove(gc);
						BoardGame.setDiscardPilePlayerCards(gc);
						num--;
						if(addCard){
							fillYourHandWIthPlayerCard(1,ps);
						}
					}
					else{
						System.out.println("Unsupported playercard. Verify the type of cardbeing passed");
					}
				}
			}
			else{
				while(num!=0){
					System.out.println(gc.getName() +" Card being added to discard Pile....");
					ps.getPlayersPlayingCard().remove(gc);
					BoardGame.setDiscardPilePlayerCards(gc);
					--num;
					if(ps.getPlayersPlayingCard().size()<5){
						if(addCard){
							fillYourHandWIthPlayerCard(1,ps);
						}
					}
				}
				System.out.println("There are 5 or more cards .. No need to draw a card ");
			}
		}
		/**
		 * this method works as resetting the players hand with the players card
		 * takes input as number of cards and the current player
		 */
		@Override
		public void fillYourHandWIthPlayerCard(int i, Player ps) {
			
			System.out.println("Resetting your player cards in hand... ");
			if(BoardGame.player_cards.size()!=0 &&  !(ps.getPlayersPlayingCard().size()>5)){
				while(ps.getPlayersPlayingCard().size()<5){
					ps.getPlayersPlayingCard().add(BoardGame.player_cards.get(0));
					BoardGame.player_cards.remove(0);
				}
			}
			
		}
	
		/**
		 * This function takes in amount , current player who's turn is going on
		 * and the area from where the minions has to be counted.
		 * Adds the specified amount to the player based on the number of minions in his specified area 
		 */
		@Override
		public void getMoneyForMinionsinArea(int amt,Player currentplayer, String areaName) {
			System.out.println("You will receive money for minion pieces in "+areaName);
			if(amt!=0 && !(areaName.isEmpty())){
				
				for(Player p : BoardGame.playersInGame){
					for(ArrayList<String> str : p.getMinions().values())
					for(String s :str){
						if(areaName.equalsIgnoreCase(s)){
							currentplayer.setPlayerAmount(currentplayer.getPlayerAmount() + amt);
							
						}
					}
					System.out.println("Check your inventory for money earned.No money added means there are no minions placed yet in "+areaName);
				}
				
				
			}
			System.out.println("Action Completed...");
		}
		/**
		 * the method accepts the current player and the selected player from which the cards have to be taken.
		 * This method also takes in the count of how many cards needs to be transferred.
		 */
		@Override
		public void playingCardsAction(Player currentPlayer, Player fromPlayer,
				int count) {
	
			String res1 = questionsToAsk("Choose a card ... : nul");
			String  res = BoardGame.getPieceNumberList(res1);
			for(GreenPlayerCardEnum gc : fromPlayer.getPlayersPlayingCard()){
				if(res.equalsIgnoreCase(gc.getName())){
					currentPlayer.getPlayersPlayingCard().add(gc);
					fromPlayer.getPlayersPlayingCard().remove(gc);
					break;
				}
				
			}
		}
		
		/**
		 * method takes as input the area name from which the trouble marker needs to be removed
		 */
		@Override
		public void removeTroubleMarker() {
			
			System.out.println("Trouble marker wil be removed..");
			System.out.println("Areas having Trouble Markers");
			int c =1;
			for(Area a : BoardGame.board_areas){
					if(a.isTroubleMarkers()){
						System.out.printf("%1s%2s%15s\n",c,"  ",a.getAreaName());
						BoardGame.pieceNumberAreaList.add(""+c+":"+a.getAreaName());
						c++;
					}
			}
			c=0;
			String areaName = questionsToAsk("Choose area name to remove trouble marker from:nul");
			String area = BoardGame.getPieceNumberList(areaName);
			BoardGame.playersInGame.get(0).getAreaInstanceFromAreaName(area).setTroubleMarkers(false);
			System.out.println("Troublemarker removed from "+area);
			System.out.println("Action Completed...");
		}
	
		/**
		 * method removes a players minion
		 */
		
		//adding a check for verifying if you are trying to assasinate opponent's minion or not -Sanchit
		@Override
		public void assasinate(Player ps) throws JSONException {
			
			
			String option = questionsToAsk("What would you like to assasinate 1.Demons 2.Trolls 3.Minions :nul");
			if(Integer.parseInt(option)==1){
				assasinateDemons(ps);
				String res = questionsToAsk("Choose an area to remove demon from:nul");
				String res1 = BoardGame.getPieceNumberList(res);
				Area a = ps.getAreaInstanceFromAreaName(res1);
				a.setDemons(a.getDemons()-1);
				BoardGame.setDemons(BoardGame.getDemons()+1);
			}
				
			if(Integer.parseInt(option)==2){
				assasinateTrolls(ps);
				String res = questionsToAsk("Choose an area to remove troll from:nul");
				String res1 = BoardGame.getPieceNumberList(res);
				Area a = ps.getAreaInstanceFromAreaName(res1);
				a.setTrolls(a.getTrolls()-1);
				BoardGame.setTrolls(BoardGame.getTrolls()+1);
			}
			if(Integer.parseInt(option)==3){
			
			String playerToSelect = questionsToAsk("Select players piece color (R/Y/G/B):nul");
			
			//System.out.println("Enter Player's Color ");
			for(Player assassinatePlayer : BoardGame.playersInGame){
				if(playerToSelect.equalsIgnoreCase(ps.getPlayerColor())){
					System.out.println("You cannot assasinate your own minion");
				}
				else{
					if(playerToSelect.equalsIgnoreCase(assassinatePlayer.getPlayerColor())){
						removeMinionOFAnotherPlayer(1, ps, assassinatePlayer);
					}
				}
			}
			}
		}
		
		private void assasinateTrolls(Player ps) {
			System.out.println("Trolls Area");
			int count =1;
			for(Area a : BoardGame.board_areas){
				if(a.getTrolls()>0){
					int t = a.getTrolls();
					while(t!=0){
						System.out.printf("%3s%15s\n",count,a.getAreaName());
						BoardGame.pieceNumberAreaList.add(""+count+":"+a.getAreaName());
						count++;
						t--;
					}
				}
			}
		}


		private void assasinateDemons(Player ps) {
			System.out.println("Demons Area");
			int count =1;
			for(Area a : BoardGame.board_areas){
				if(a.getDemons()>0){
					int t = a.getDemons();
					while(t!=0){
						System.out.printf("%3s%15s\n",count,a.getAreaName());
						BoardGame.pieceNumberAreaList.add(""+count+":"+a.getAreaName());
						count++;
						t--;
					}
				}
			}
			
		}


		private void displayPieceList(Player affectingPlayer) {
			int count = 1;
			for(Area a : BoardGame.board_areas){
				if(a.isTroubleMarkers() && checkValue(affectingPlayer,a) 
						){
					
					// get minions in this area					
					System.out.printf("%3s%15s%15s\n",count,a.getAreaName(),a.getMinionsForEveryPlayer(a.getAreaName() 
							,affectingPlayer));
					BoardGame.pieceNumberAreaList.add(""+count+":"+a.getAreaName());
					count++;
				}	
			}			
		}


		private boolean checkValue(Player affectingPlayer,Area a ) {
			// TODO Auto-generated method stub
			boolean r = false;
			for(ArrayList<String> s : affectingPlayer.getMinions().values()){
				for(String st : s){
					if(!st.trim().equalsIgnoreCase("") && st.trim().equalsIgnoreCase(a.getAreaName())){
						r = true;
					}
				}
			}
			return r;
		}


		@Override
		public void placeTroubleMarker(String areaLocation)
				throws JSONException {
			   
			
		}
		@Override
		public void ignoreRandomEvent(Player currentPlayer) {}
		@Override
		public void removeoneTroubleMarker(Player currentPlayer) {}
		@Override
		public void discardCard(Player currentPlayer) {}
		@Override
		public void placeoneMinion(Player currentPlayer, String areaLocation)
				throws JSONException {}
		/**
		 * draw specified number of cards from discard pile 
		 */
		@Override
		public void drawCardsFromDiscardPile(int num, Player player) {
	
			System.out.println("Cards will be drawn from discarded pile");
			if((BoardGame.getDiscardPilePlayerCards().size() <= 0)){
				System.out.println("Discard Pile is Empty !!! , Cannot draw card");
				return;
			}
			while((num!=0) && (BoardGame.getDiscardPilePlayerCards().size() > 0)) {
					Random ran = new Random();
					int i = ran.nextInt(BoardGame.getDiscardPilePlayerCards().size()-1);
					System.out.println("Adding cards from Discard Pile");
					player.getPlayersPlayingCard().add(BoardGame.getDiscardPilePlayerCards().get(i));
					num--;
				}
			
			System.out.println("Action Completed...");
		}
		
		/**
		 * place a minion with all the rules of placing a minion being implemented
		 */
		@Override
		public void placeMinionActionPlayerCard(Player currentPlayingPlayer) throws JSONException {
			
			if(currentPlayingPlayer.getMinionQuantity()==12){
				
				removeMinionOFYourOwn(1, currentPlayingPlayer);
				placeMinionActionPlayerCard(currentPlayingPlayer);
			}
			System.out.println("Area Names");
			int j=1;
			for(Area a : BoardGame.board_areas){
				System.out.printf("%2s%1s%25s\n",j,"  ",a.getAreaName());
				BoardGame.pieceNumberAreaList.add(""+j+":"+a.getAreaName());
				j++;
			}
			String areas = questionsToAsk("Where u want to place a minion:nul");
			String a = BoardGame.getPieceNumberList(areas);
			currentPlayingPlayer.placeMinion(a);
			/*
			System.out.println("Place a minion in area form these only..");
			
			if(currentPlayingPlayer.getMinionQuantity()!=0 && currentPlayingPlayer.getMinionQuantity()<12){
				BoardGame.displayMinionsForPlayerOnBoard(currentPlayingPlayer,
						0);
				String minionLocation = questionsToAsk("Enter area name where you want to place a minion? :nul");
				String area = BoardGame.getPieceNumberList(minionLocation);
				// should display the current state of board
				// print out the board state on a frame gui
				for(Area a : BoardGame.board_areas){
					if(a.getAreaName().equalsIgnoreCase(area)){
						currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), area);
						a.setMinionColor(area); // Sanchit Fix, Minion Count not getting updated
						if(!(a.isTroubleMarkers()) && currentPlayingPlayer.totalMinionsInAreaForAllPlayers(area)>1) //Sanchit Fix
							a.setTroubleMarkers(true);
						System.out.println("Your Minion is placed in:"+area);
						break;
					}
				}
			}
			else if(currentPlayingPlayer.getMinionQuantity() == 12){
				System.out.println("Choose an area to remove minion from....");
				BoardGame.displayMinionsForPlayerOnBoard(currentPlayingPlayer,0);
				removeMinionOFYourOwn(1, currentPlayingPlayer);
				placeMinionActionPlayerCard(currentPlayingPlayer);
			
			String minionLocation = questionsToAsk("Enter area name where you want to place a minion? :nul");
			// should display the current state of board
			// print out the board state on a frame gui
			for(Area a : BoardGame.board_areas){
				if(a.getAreaName().equalsIgnoreCase(minionLocation)){
					currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
					System.out.println("Your Minion is placed in:"+minionLocation);
					break;
				}
			}
			}
			else if(currentPlayingPlayer.getMinionQuantity() == 0){
				String minionLocation = questionsToAsk("Enter area name where you want to place a minion? :nul");
				// should display the current state of board
				// print out the board state on a frame gui
				for(Area a : BoardGame.board_areas){
					if(a.getAreaName().equalsIgnoreCase(minionLocation)){
						currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
						System.out.println("Your Minion is placed in:"+minionLocation);
						break;
					}
				}
			}*/
			System.out.println("Action completed...");
		}
		/**
		 * This method will allow the user to discard as many cards as he wants from his hand
		 */
		@Override
		public String discardCardsPerYourWish(Player currentPlayingPlayer, GreenPlayerCardEnum tempname, int amt){
			
			try{
			System.out.println("Cards will be discarded as per your wish");
			int numOfCards = Integer.parseInt(questionsToAsk("Enter the no of cards you want to discard"));
			while(currentPlayingPlayer.getPlayersPlayingCard().size()!=0 && numOfCards>0){
				int count = 1;
				System.out.printf("%-10s\n","Your player cards ");
				ArrayList<GreenPlayerCardEnum> listOfCurrPlayerCards = currentPlayingPlayer.getPlayersPlayingCard();
				for(GreenPlayerCardEnum gc : listOfCurrPlayerCards){
					if(!gc.getName().equalsIgnoreCase(tempname.getName()))
					{
						System.out.printf("%2s%1s%15s\n",count,"  ", gc.getName());
						BoardGame.pieceNumberAreaList.add(""+count+":"+gc.getName());
						count++;
					}
				}
				
				String r = questionsToAsk("Choose the card to discard..: nul");
				String result = BoardGame.getPieceNumberList(r);
				if(!(result.equalsIgnoreCase("exit"))){
					GreenPlayerCardEnum gec = PlayerCardUtility.getEnumInstance(result);
					if(listOfCurrPlayerCards.contains(gec)){
								addToDiscardPile(1,gec, currentPlayingPlayer, false);
								takeMoneyFromBank(amt, currentPlayingPlayer);
								System.out.println("Card Discarded");
								numOfCards--;
						}
					
				}
				else if(Integer.parseInt(result)==0)
				break;
				}
			}catch(Exception e){
				throw e ;
			}
			System.out.println("Action completed..");
			return "";
		}

		
public void discardACard(Player currentPlayingPlayer, GreenPlayerCardEnum tempname){
		
		
		
		try{
			if(currentPlayingPlayer.getPlayersPlayingCard().size()!=0){
				System.out.println("Card will be discarded as per your wish");
				
				System.out.println("Your cards as of now: ");
				ArrayList<GreenPlayerCardEnum> listOfCurrPlayerCards = currentPlayingPlayer.getPlayersPlayingCard();
				int count =1;
				for(GreenPlayerCardEnum gc : listOfCurrPlayerCards){
					if(!gc.getName().equalsIgnoreCase(tempname.getName())){
						System.out.printf("%-2s-15s",count,gc.getName());
						BoardGame.pieceNumberAreaList.add(""+count+":"+gc.getName());
						count++;
					}
				}
						
				String result = questionsToAsk("Choose the card : nul");
					
				if(!(result.equalsIgnoreCase("exit"))){
					GreenPlayerCardEnum gec = PlayerCardUtility.getEnumInstance(result);
				//	for(GreenPlayerCardEnum temp : listOfCurrPlayerCards){
					//	if(temp.getName().equalsIgnoreCase(result.trim())){
					if(listOfCurrPlayerCards.contains(gec)){
							//if(currentPlayingPlayer.getPlayersPlayingCard().contains(result.trim())){
									
						addToDiscardPile(1,gec, currentPlayingPlayer, false);
						System.out.println("Card Discarded");
						//}
					}						
				}
			}
			System.out.println("Action completed..");
		}
		catch(Exception e){
			throw e ;
		}
	}

		
		/**
		 * 
		 * 
		 */
		@Override
		public void takeMoneyExchangeCardsFromAnotherPlayer(Player currentPlayingPlayer,int amt){
		String result = questionsToAsk("Enter a players piece color you want to give card to : nul");
		Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
		
		System.out.println("Your cards as of now: ");
		for(GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard())
			System.out.print(gc.getName()+", ");
	
		String res = questionsToAsk("Enter card you want to give : nul");
		for(GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard()){
			if(res.equalsIgnoreCase(gc.getName())){
				selectedPlayer.getPlayersPlayingCard().add(gc);
				currentPlayingPlayer.getPlayersPlayingCard().remove(gc);
				takeMoneyFromPlayer(amt, currentPlayingPlayer, selectedPlayer);
				break;
			}
		}
		System.out.println("Action Completed...");
		}
		
		@Override
		public void placeMinionActionPlayerCard(Player currentPlayingPlayer,String s) throws JSONException{
				
		};
		
		@Override
		public void addBuildingAction(Player player){
			System.out.println("You can place a building in either of these areas: ");
			player.displayAreasWithPlayersMinion(player);
			String area_name = questionsToAsk("Enter area name where you want to place building:nul");
			String area = BoardGame.getPieceNumberList(area_name);
			player.addBuilding(area);
			for(Area a : BoardGame.board_areas){
				if(area.equalsIgnoreCase(a.getAreaName().trim())){
					player.addBuilding(area);
				}
			}
			
		}
		
		public String lastaction(Player currentPlayingPlayer, String res) throws JSONException{
			
			System.out.println("You have finished playing this card. It will be discarded");
			res = askSymbolsInOrder(this, res.split(":")[1].trim(),currentPlayingPlayer);
			return res;
		}
	}
