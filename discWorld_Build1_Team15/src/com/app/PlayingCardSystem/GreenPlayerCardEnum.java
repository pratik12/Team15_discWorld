package com.app.PlayingCardSystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONException;

import userInputUtility.UserInputUtility;

import com.app.Area;
import com.app.BoardGame;
import com.app.InterruptCard;
import com.app.Player;
import com.app.PlayerCardUtility;
import com.app.common.ComponentUtilities;
import com.app.rules.WinningCircumstancesFactory;
import com.app.rules.RandomEventCard;

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
	
	BOGGIS("Mr.BOGGIS","green","DeckPile", new String[]{"Read Scroll->Take 2$ from every player","Place minion"} ){
		
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
		String res = askSymbolsInOrder(this ,"0");
		
		while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
			(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
			
			if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0]) && 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ){
				PlayingCardRuleEngine.TEST.takeMoney(2, currentPlayingPlayer);
				res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}
			else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer);
				return;

			}
			else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])
					&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				placeMinionActionPlayerCard(currentPlayingPlayer);
				break;
			}
		
		}
		
		addToDiscardPile(1, this, currentPlayingPlayer);
		}
		
		
		// place this card on the discard pile
	},//take 2$ from all players
	
BEGGARSGUILD("The BEGGARS GUILD","green","DeckPile", new String[]{"Read scroll->Take 2 playing cards","Place minion"} ) {
		
	@Override
	public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
		String res = askSymbolsInOrder(this ,"0");
			
		while( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) || 
				(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0]) && 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				
			takePlayingCards(currentPlayingPlayer, 2);
			res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}
			else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer);
				return;
			}
			else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])
					&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				
				placeMinionActionPlayerCard(currentPlayingPlayer);
				break;
			}
	
		}
		addToDiscardPile(1, this, currentPlayingPlayer);
	}
			
		
	},// sleect 1 player. take 2 cards
	
	AMBANK("The Bank of AnkhMorpork","green","DeckPile",new String[]{"Read scroll->Take 10$ loan from bank.At the end"
			+ " you must pay back 12$ or you loose 15 points", "Play another card"}) {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) {
		
			String res = askSymbolsInOrder(this, "0");

            while ( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
                    this.takeLoanFromBank(10, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                }
                else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
    				addToDiscardPile(1, this, currentPlayingPlayer);
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
                    break;
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
			
		}
	}, //loan of 10$ from bank. at end payback 12$ or loose 15 points
	
	AMSUNSHINEDRAGONSANCTUARY("The Ankh Morpork Sunshine Dragon Sanctuary","green","DeckPile", new String[]{"Read scroll->Each playe rmust give you 1$ or one of their"
			+ " cards","Play Another Card"}) {

	@Override
	public void performTasks(Player currentPlayingPlayer) throws JSONException {
		
		String res = askSymbolsInOrder(this ,"0");
		
		while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
				(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){

			if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				System.out.println("Take 1$ from every other player or take 1 playing card");
				for(Player p : BoardGame.playersInGame){
				
					if(!(p.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor()))&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					Player fromPlayer = null;
					String res1 = questionsToAsk("Enter Your piece color to Give him a playing card :"+
					"Enter 'curr' to Pay him 1$");
					if(res1.equalsIgnoreCase("r") || res1.equalsIgnoreCase("g")
							|| res1.equalsIgnoreCase("b") || res1.equalsIgnoreCase("y")){
						for(Player pc : BoardGame.playersInGame){
							if(pc.getPlayerColor().equalsIgnoreCase(res1))
								fromPlayer = pc;
							break;
						}
						takePlayingCards(currentPlayingPlayer, 1);
					}else if(res1.equalsIgnoreCase("curr")){
						takeMoneyFromPlayer(1, currentPlayingPlayer, fromPlayer);
					}
				}
			}
			res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}
			else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer);
				return;
			}
			else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				// call the function of playing another card
				playAnotherCard(currentPlayingPlayer, this);
				break;
			}
		}
		addToDiscardPile(1, this, currentPlayingPlayer);
		}
	}, // take 1$ from every player or one card
	
	ANGUA("Sergeant Angua","green","DeckPile",new String[]{"Remove Trouble Marker","Play Another Card"}) {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
			String res = askSymbolsInOrder(this ,"0");
			
			while( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) || 
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){

				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					// show to user where trouble markers are
					String areaName = questionsToAsk("Enter area name to remove trouble marker from:nul");
					removeTroubleMarker(areaName);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);
					return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					playAnotherCard(currentPlayingPlayer, this);
					break;
				}
			}
			addToDiscardPile(1, this, currentPlayingPlayer);	
		}
	},//
	
AGONYAUNTS("The Agony Aunts","green","DeckPile", new String[]{"Assasinate","Take 2$","Place Minion"}) {
		
	@Override
	public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					assasinate(currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					takeMoneyFromBank(2, currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[2])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					placeMinionActionPlayerCard(currentPlayingPlayer);
					System.out.println("You have played the last action.This card will be discarded");
					break;
				}
				else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);
					return;
				}
			}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	
DYSK("The Dysk","green","DeckPile",new String[]{"Add building","Read Scroll"}) {
		
	@Override
	public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
		String res = askSymbolsInOrder(this ,"0");
		
		while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
				(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
		
			if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				
				currentPlayingPlayer.displayAreasWithPlayersMinion(currentPlayingPlayer);
				String area_name = questionsToAsk("Enter area name to place building:nul");
				currentPlayingPlayer.addBuilding(area_name);
				res = askSymbolsInOrder(this ,"0");
			}
			else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
			
				placeMinionActionPlayerCard(currentPlayingPlayer);
				System.out.println("You have played the last action.This card will be discarded");
				break;
			}
			else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer);
				return;
			}
		}	
			
		addToDiscardPile(1, this, currentPlayingPlayer);
			
		}
	},
	
	DUCKMAN("The Duckman","green","DeckPile",new String[]{"Read scroll"}) {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					String result = questionsToAsk("Enter a players piece color to give playing card to : nul");
					Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
					ComponentUtilities mu = new ComponentUtilities();
					mu.displayMinionsOfotherPlayer(selectedPlayer);
					String toLocation = questionsToAsk("Enter area name:nul");
					moveMinionToOtherArea(currentPlayingPlayer, selectedPlayer, toLocation);
					System.out.println("You have played the last action.This card will be discarded");
					break;
				}
				else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);
					return;
				}
			}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	
	DRUMKNOTT("DRUMKNOTT","green","DeckPile",new String[]{"Read scroll->Play 2 other cards from your hand"}) {
		
		@Override
		public void performTasks(Player currentPlayingPlayer){
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					try {
                        playAnotherCard(currentPlayingPlayer, this);
                        playAnotherCard(currentPlayingPlayer, this);
                        System.out.println("You have played the last action.This card will be discarded");
                        break;
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);
					return;
				}
			}
		addToDiscardPile(1, this, currentPlayingPlayer);	
		}
	},
	CMOTDIBBLER("CMOT DIBBLER","green","DeckPile",new String[]{"Read scroll","Play another card"}) {
		// roll die. if 7 take 4 from bank. if 1 pay 2$ to bank or remove 1 YOUR minion.
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					int num = rollDie();
					if(num>=7){
						takeMoneyFromBank(4, currentPlayingPlayer);
					}
					else if(num==1){
						String result = questionsToAsk("Hit 'p' to pay money to bank : Hit 'rem' to remove your minion");
						if(result.trim().equalsIgnoreCase("p")){
							payMoneyToBank(2, currentPlayingPlayer);
						}
						else if(result.trim().equalsIgnoreCase("rem")){
							removeMinionOFYourOwn(1,currentPlayingPlayer);
						}
					}
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);
					return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					playAnotherCard(currentPlayingPlayer, this);
					System.out.println("You have played the last action.This card will be discarded");
					break;
				}
		}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	DRCRUCES("DR.CRUCES","green","DeckPile",new String[]{"Assasinate","take 3$"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					assasinate(currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					takeLoanFromBank(3, currentPlayingPlayer);
					System.out.println("You have played the last action.This card will be discarded");
					break;
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);
					return;
				}
				}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	CAPTAINCARROT("CAPTAIN CARROT","green","DeckPile",new String[]{"Place minion","Remove Trouble Marker","take 1$"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					
					placeMinionActionPlayerCard(currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					String result = questionsToAsk("Enter area anme to remove marker form : nul");
					removeTroubleMarker(result);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);
					return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[2])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					takeMoneyFromBank(1, currentPlayingPlayer);
					System.out.println("You have played the last action.This card will be discarded");
					break;
				}
			
			}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	MRSCAKE("MRS.CAKE","green","DeckPile",new String[]{"Read scroll","take 2$","add building"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) {
		
			String res = askSymbolsInOrder(this ,"0");
			
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
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}
				
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					takeMoneyFromBank(2, currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);
					return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[2])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					currentPlayingPlayer.displayAreasWithPlayersMinion(currentPlayingPlayer);
					String area_name = questionsToAsk("Enter area name to add building into:nul");
					currentPlayingPlayer.addBuilding(area_name);
					System.out.println("You have played the last action.This card will be discarded");
					break;
				}
				}
			
		}
	},
	MRBENT("MR.BENT","green","DeckPile",new String[]{"Read Scroll->Take 10$ loan from bank.","Play another card"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) {
			
			String res = askSymbolsInOrder(this, "0");

            while ( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
                    this.takeLoanFromBank(10, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                }else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
    				addToDiscardPile(1, this, currentPlayingPlayer);
    				return;
    			} else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
                    try {
                        playAnotherCard(currentPlayingPlayer, this);
                        System.out.println("You have played the last action.This card will be discarded");
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    break;
                }
            }
			
			
		}
	},
	GROAT("GROAT","green","DeckPile",new String[]{"Place Minion"}) {
		
		@Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					placeMinionActionPlayerCard(currentPlayingPlayer);
					System.out.println("You have played the last action.This card will be discarded");
					break;
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);
					return;
				}
			}
			addToDiscardPile(1, this, currentPlayingPlayer);
        }
	},
	GASPODE("GASPODE","green","DeckPile",new String[]{"Interrupt"}) {
		
		@Override
        public void performTasks(Player currentPlayingPlayer) {
			InterruptCard ic = new InterruptCard();
            String res = askSymbolsInOrder(this, "0");

            while ( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
                	ic.setInterruptMessage("STOP MINION");
                	break;
                }else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
    				addToDiscardPile(1, this, currentPlayingPlayer);
    				return;
    			}
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	FRESHSTARTCLUB("FRESH START CLUB","green","DeckPile",new String[]{"Interrupt"}) {
		@Override
        public void performTasks(Player currentPlayingPlayer) {
			InterruptCard ic = new InterruptCard();
            String res = askSymbolsInOrder(this, "0");

            while ( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
                	ic.setInterruptMessage("STOP MINION");
                	return;
                }else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
    				addToDiscardPile(1, this, currentPlayingPlayer);break;
    			}
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
		}

	},
	FOULOLERON("Foul Ole ron","green","DeckPile",new String[]{"Read Scroll->","Play another card"}) {
		// moveminion of OTHER player from an area to adjacent area
		@Override
	public void performTasks(Player currentPlayingPlayer) throws JSONException {
		
		String res = askSymbolsInOrder(this, "0");

        while ( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {
			
        if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
				!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
			String result = questionsToAsk("Enter the color of player you want to select (R/G/Y/B) in this format : nul");
			Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
			removeMinionOFAnotherPlayer(2, currentPlayingPlayer, selectedPlayer);
			System.out.println("Areas where your selected player has minions");
			for(ArrayList<String> t : selectedPlayer.getMinions().values())
				for(String str : t)
					System.out.printf("%-10s", str);
			
			String areaToMoveMinionFrom = questionsToAsk("Enter area name to move minion from : nul");
			moveMinionToOtherArea(currentPlayingPlayer, selectedPlayer, areaToMoveMinionFrom);
		      res = askSymbolsInOrder(this, res.split(":")[1].trim());
		}
        else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
				!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
        	playAnotherCard(currentPlayingPlayer, this);
        	System.out.println("You have played the last action.This card will be discarded");
        	break;
        }else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
			addToDiscardPile(1, this, currentPlayingPlayer);
			return;
		}
        }
        addToDiscardPile(1, this, currentPlayingPlayer);
        }
	},
	THEFOOLSGUILD("The Fools Guild","green","DeckPile",new String[]{"Read Scroll->Slect Player your choice. "
			+ "If they cannot give you 5$ place this card in front of them.This card goes in their hand and cannot be removed","Place Minion"}) {
		
		@Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while ( !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {

                    String result = questionsToAsk("Enter the color of player you want to select (R/G/Y/B) in this format : nul");
                    Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
                    String answer = questionsToAsk("Hit '1' to pay 5$ or hit '2' to receive card");
                    if (answer.trim().equalsIgnoreCase("1")) {
                        takeMoneyFromPlayer(5, currentPlayingPlayer, selectedPlayer);
                        res = askSymbolsInOrder(this, res.split(":")[1].trim());
                    } else if (answer.trim().equalsIgnoreCase("2")) {
                        selectedPlayer.getPlayersPlayingCard().add(this);
                        currentPlayingPlayer.getPlayersPlayingCard().remove(this);
                        res = askSymbolsInOrder(this, res.split(":")[1].trim());
                    }

                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))) {
                    // call appropriate function
                	placeMinionActionPlayerCard(currentPlayingPlayer);
                	System.out.println("You have played the last action.This card will be discarded");
                	break;
                }else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
    				addToDiscardPile(1, this, currentPlayingPlayer);
    				return;
    			}
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
	},
	THEFIREBRIGADE("The fire Brigade","green","DeckPile",new String[]{"Read Scroll->Select player. Take 5$ from him. "
			+ "Else you can remove a building of his","Play another card"}) {
		// select plaeyr if they dont give you 5$ remove 1 of his building from board
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this, "0");

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
			res = askSymbolsInOrder(this, res.split(":")[1].trim());
		}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
			addToDiscardPile(1, this, currentPlayingPlayer);
			return;
		}
                else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
    					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
                	playAnotherCard(currentPlayingPlayer, this);
                	System.out.println("You have finished playing this card. It will be discarded");
                	break;
                	}
                }
            addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	INIGOSKIMMER("Inigo Skimmer","green","DeckPile",new String[]{"Assasinate","Take 2$"}) {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
		
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					assasinate(currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}
				
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					takeMoneyFromBank(2, currentPlayingPlayer);
					System.out.println("You have finished playing this card. It will be discarded");
					break;
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer);
						return;
					}
				}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	HISTORYMONKS("History Monks","green","DeckPile",new String[]{"Read Scroll->Draw 4 cards from discard pile","Place minion"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
		
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					drawCardsFromDiscardPile(4, currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);return;
				}
				
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					placeMinionActionPlayerCard(currentPlayingPlayer);
					System.out.println("You have finished playing this card. It will be discarded");
					break;
				}
				
				}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	HEX("Hex","green","DeckPile", new String[]{"Read Scroll->Draw 3 cards from deck","Add building"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) {
		
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					drawCardsFromDeck(3, currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);return;
				}
				
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					currentPlayingPlayer.displayAreasWithPlayersMinion(currentPlayingPlayer);
					String area_name = questionsToAsk("Enter area name where you want to place building:nul");
					currentPlayingPlayer.addBuilding(area_name);
					System.out.println("You have finished playing this card. It will be discarded");
					break;
				}
				}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	HERENOW("Here N Now","green","DeckPile",new String[]{"Read Scroll->Roll Die. If 7 take 3$ from player of ur choice."
			+ "IF 1 remove a minion of your own","Play Another card"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
		
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					int num = rollDie();
					if(num >= 7){
						String playerToSelect = questionsToAsk("Enter player piece color to select:nul");
						Player player = selectPlayer(currentPlayingPlayer, playerToSelect);
						takeMoneyFromPlayer(3, currentPlayingPlayer, player);
						res = askSymbolsInOrder(this, res.split(":")[1].trim());
					}
					else if(num==1){
							removeMinionOFYourOwn(1,currentPlayingPlayer);
							res = askSymbolsInOrder(this, res.split(":")[1].trim());
					}
					else{
						System.out.println("This" +num+ "from roll die has no effect");
						res = askSymbolsInOrder(this, res.split(":")[1].trim());
					}
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);return;
				}
				
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					playAnotherCard(currentPlayingPlayer, this);
					System.out.println("You have finished playing this card. It will be discarded");
					break;
				}
				}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	
	HARRYKING("Harry King","green","DeckPile",new String[]{"Place minion","Read Scroll->Discard 2 cards of your choice"}) {
		// discrad as many cards as uw ish, get 2 for each
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
		
		String res = askSymbolsInOrder(this ,"0");
		
		while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
				(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
		
			if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				
				placeMinionActionPlayerCard(currentPlayingPlayer);
				res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer);return;
			}
			
			else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				
				discardCardsPerYourWish(currentPlayingPlayer, this, 2);
				System.out.println("You have finished playing this card. It will be discarded");
				break;
			}
			}
		addToDiscardPile(1, this, currentPlayingPlayer);
	}
	},
	GRYLE("MR.GRYLE","green","DeckPile",new String[]{"Assasinate","Take 1$"}) {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
		
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					assasinate(currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);return;
				}
				
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					takeMoneyFromBank(1, currentPlayingPlayer);
					System.out.println("You have finished playing this card. It will be discarded");
					break;
				}
		}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	HARGAHOUSEOFRIBS("HARGA HOUSE OF RIBS","green","DeckPile",new String[]{"Take 3$","Place Minion"}) {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException {
		
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					takeMoneyFromBank(3, currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);return;
				}
				
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						placeMinionActionPlayerCard(currentPlayingPlayer);
						System.out.println("You have finished playing this card. It will be discarded");
						break;
					}
				}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	THEPEELEDNUTS("THE PEELED NUTS","green","DeckPile",new String[]{"..No actions to perform.."}) {
		
		
	},
	THEOPERAHOUSE("THE OPERA HOUSE","green","DeckPile",new String[]{"Add building","Read Scroll->Take 1$ for minions in Isle of GOds"}) {
		
		@Override
		public void performTasks(Player currentPlayingPlayer) {
		
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					currentPlayingPlayer.displayAreasWithPlayersMinion(currentPlayingPlayer);
					String area_name = questionsToAsk("Enter area name to add building to:nul");
					currentPlayingPlayer.addBuilding(area_name);
					
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);return;
				}
				
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					getMoneyForMinionsinArea(1, currentPlayingPlayer, "Isle of Gods");
					System.out.println("You have finished playing this card. It will be discarded");
					break;
					}
				}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
		
	},
	NOBBYNOBBSS("NOBBY NOBBSS","green","DeckPile",new String[]{"Read Scroll->Take 3$ from player of your choice","Play another card"}) {
		@Override
		public void performTasks(Player currentPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					String result = questionsToAsk("Enter a players piece color you want to take money from : nul");
					Player selectedPlayer = selectPlayer(currentPlayer, result);
					takeMoneyFromPlayer(3, currentPlayer, selectedPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayer);return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					playAnotherCard(currentPlayer, this);
					System.out.println("You have finished playing this card. It will be discarded");
					break;
				}
				}
			addToDiscardPile(1, this, currentPlayer);
		}
	},
	MODO("MODO","green","DeckPile",new String[]{"Read Scroll->Discard a card of your choice","Place Minion"}) {
		@Override
		public void performTasks(Player currentPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					discardCardsPerYourWish(currentPlayer, this, 1);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayer);return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					placeMinionActionPlayerCard(currentPlayer);
					break;
					}
				}
			addToDiscardPile(1, this,currentPlayer);
			}
			
		
	},
	THEMENDEDDRUM("THE MENDEDDRUM","green","DeckPile",new String[]{"Add building","Take 2$"}) {
		@Override
		public void performTasks(Player currentPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
					if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					
					currentPlayer.displayAreasWithPlayersMinion(currentPlayer);
					String area_name = questionsToAsk("Enter area name to add building to:nul");
					currentPlayer.addBuilding(area_name);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayer);return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					takeMoneyFromBank(2, currentPlayer);
					break;
					}
					
				}
			addToDiscardPile(1, this,currentPlayer);
			}
			
	},
	LIBRARIAN("LIBRARIAN","green","DeckPile",new String[]{"Read Scroll->Draw 4 cards from deck"}) {
		@Override
		public void performTasks(Player currentPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
					if(res.split(":")[0].trim().equalsIgnoreCase("Read Scroll")&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
							System.out.println("4 cards will be drawn from deck...");
							drawCardsFromDeck(4, currentPlayer);
							break;
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayer);return;
					}
			}
			addToDiscardPile(1, this, currentPlayer);
		}
	},
	LEONARDOFQUIRM("LEONARD OF QUIRM","green","DeckPile",new String[]{"Read Scroll->Draw 4 cards from deck"}) {
		// draw 4 cards from the deck
		@Override
		public void performTasks(Player currentPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
					if(res.split(":")[0].trim().equalsIgnoreCase("Read Scroll")&& 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						System.out.println("Cards will be drawn from deck.....");
							drawCardsFromDeck(4, currentPlayer);
							break;
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayer);return;
					}
			}
			addToDiscardPile(1, this, currentPlayer);
		}
	},
	SHONKYSHOP("SHONKY SHOP","green","DeckPile",new String[]{"Read Scroll->Discard cards as per yur wish","Add building"}) {
		
		@Override
		public void performTasks(Player currentPlayingPlayer){
		
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
					if(res.split(":")[0].trim().equalsIgnoreCase("Read Scroll") && 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						discardCardsPerYourWish(currentPlayingPlayer, this, 1);
						res = askSymbolsInOrder(this, res.split(":")[1].trim());
					}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer);return;
					}
					else if(res.split(":")[0].trim().equalsIgnoreCase("Add building") && 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						
						currentPlayingPlayer.displayAreasWithPlayersMinion(currentPlayingPlayer);
						String area_name = questionsToAsk("Enter area name to add building to:nul");
						currentPlayingPlayer.addBuilding(area_name);
						break;
					}
			}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	SACHARISSACRIPSLOCK("SACHARISSA CRIPSLOCK","green","DeckPile",new String[]{"Read Scroll->Take 1$ for every trouble marker on board","Place Minion"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase("Read Scroll") && 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					for(Area a: BoardGame.board_areas)
						if(a.isTroubleMarkers())
							takeMoneyFromBank(1, currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase("Place Minion") && 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					placeMinionActionPlayerCard(currentPlayingPlayer);
					break;
				}
			}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	ROSIEPALM("ROSIE PALM","green","DeckPile",new String[]{"Place Minion","Read Scroll->Take money in exchange for cards from another player"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase("Place Minion") && 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					placeMinionActionPlayerCard(currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);return;
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase("Read Scroll") && 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
			
					takeMoneyExchangeCardsFromAnotherPlayer(currentPlayingPlayer,2);
					break;
				}
			}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	RINCEWIND("RINCEWIND","green","DeckPile",new String[]{"Play Random Event card","Read Scroll->Move miion from area with trouble marker to an adjacent area","Play another Card"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
			System.out.println("Compulsory action will be performed..");
			RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayingPlayer, RandomEventCard.Fire, this);
			String res = askSymbolsInOrder(this ,"0");
			
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
//				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])){
//					//
//				}
				 if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1]) && 
							!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					ArrayList<Area> temp = new ArrayList<Area>();
					for(Area a : BoardGame.board_areas){
						if(a.isTroubleMarkers())
							temp.add(a);
					}
					System.out.println("You can move minions from this area: ");
					ArrayList<String> str = new ArrayList<String>();
					for(Player p : BoardGame.playersInGame){
						if(p.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor())){
							
							for(Area a : temp)
								if(p.getMinions().values().contains(a.getAreaName())){
									str.add(a.getAreaName());
									System.out.println(a.getAreaName());
								}
							break;
						}
						break;
			}
					for(String s : str){
						String result = BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.areaDetails, s);
						BoardGame.displayAdjacentAreas(result);
						
					}
					String output = questionsToAsk("Which adjacent area you want to move minion to:nul");
					currentPlayingPlayer.getMinions().values().remove(output);
					currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), output);
					
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[2])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					playAnotherCard(currentPlayingPlayer, this);
					break;
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);return;
				}
			}
		addToDiscardPile(1, this, currentPlayingPlayer);
		}
		
	},
	THEROYALMINT("THE ROYAL MINT","green","DeckPile",new String[]{"Add building","Take 5$"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer){
			
			String res = askSymbolsInOrder(this ,"0");
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0]) && 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					currentPlayingPlayer.displayAreasWithPlayersMinion(currentPlayingPlayer);
				 String area_name = questionsToAsk("Enter area name to place building:nul");
				 currentPlayingPlayer.addBuilding(area_name);
				 res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}
				else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					takeMoneyFromBank(5, currentPlayingPlayer);
					break;}
				else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
						addToDiscardPile(1, this, currentPlayingPlayer);return;
					}
			}
		addToDiscardPile(1, this, currentPlayingPlayer);	
		}
		
	},
	QUEENMOLLY("QUEEN MOLLY","green","DeckPile",new String[]{"Place minion","Read Scroll->Take 2 cards from player of your choice"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this ,"0");
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
				if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
						!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					placeMinionActionPlayerCard(currentPlayingPlayer);
					res = askSymbolsInOrder(this, res.split(":")[1].trim());
				}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
					addToDiscardPile(1, this, currentPlayingPlayer);return;
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
				break;	
				}
			}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	PINKPUSSYCATCLUB("PINK PUSSY CAT CLUB","green","DeckPile",new String[]{"Take 3$","Play another card"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
			
			String res = askSymbolsInOrder(this ,"0");
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
					(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
			
			if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				takeMoneyFromBank(5, currentPlayingPlayer);
				res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer);return;
			}
			else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				playAnotherCard(currentPlayingPlayer, this);
				break;
			}
			}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	ZORGOTHERETROPHRENOLOGIST("ZORGO THE RETROPHRENOLOGIST","green","DeckPile",new String[]{"Read Scroll->Exchange perosnality card","Add building"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer){
			

			String res = askSymbolsInOrder(this ,"0");
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
		(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
			if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				currentPlayingPlayer.exchangePersonalityCard();
				res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer);return;
			}
			else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				currentPlayingPlayer.displayAreasWithPlayersMinion(currentPlayingPlayer);
				 String area_name = questionsToAsk("Enter area name to place building:nul");
				 currentPlayingPlayer.addBuilding(area_name);
				break;
			}
			}
			addToDiscardPile(1, this, currentPlayingPlayer);
		}
		
		
	},
	DRWHITEFACE("DR.WHITEFACE","green","DeckPile",new String[]{"Read Scroll->"}) {
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
	THESEAMSTRESSESGUILD("THE SEAMSTRESSES GUILD","green","DeckPile",new String[]{"Read Scroll->Take money in exchange of cards from Another player ","Place minion"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
		
			String res = askSymbolsInOrder(this ,"0");
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
		(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
			if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				takeMoneyExchangeCardsFromAnotherPlayer(currentPlayingPlayer,2);
				res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer);return;
			}
			else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit")))
				placeMinionActionPlayerCard(currentPlayingPlayer);
				break;
			}
		addToDiscardPile(1, this, currentPlayingPlayer);
		}
	},
	MRPINANDMRTULIP("MR.PIN & MR.TULIP","green","DeckPile",new String[]{"Assasinate","Take 1$->From Bank"}) {
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
			String res = askSymbolsInOrder(this ,"0");
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
		(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
			if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				assasinate(currentPlayingPlayer);
				res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer);
				return;
			}
			else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit")))
				takeMoneyFromBank(1, currentPlayingPlayer);
				break;
			}
		addToDiscardPile(1, this, currentPlayingPlayer);
		}
		
	},
	THETHIEVESGUILD("THE THIEVES GUILD","green","DeckPile",new String[]{"Read Scroll->Money will betaken from Player","Place Minion"}) {
		// take 2$ from every other player
		@Override
		  public void performTasks(Player currentPlayingPlayer) throws JSONException{
		
			String res = askSymbolsInOrder(this ,"0");
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
		(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
			if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				for(Player p : BoardGame.playersInGame){
					if(!(p.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor())))
						takeMoneyFromPlayer(2, currentPlayingPlayer, p);
				}
				res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer);return;
			}
			else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit")))
				placeMinionActionPlayerCard(currentPlayingPlayer);
				break;
			}
		addToDiscardPile(1, this, currentPlayingPlayer);
			
			
			
			
		}  
		
	}, GIMLETDWARFDELICATESSEN("GIMLET DWARF DELICATESSEN","green","DeckPile",new String[]{"Take 3$","Place minion"}){
		@Override
		public void performTasks(Player currentPlayingPlayer) throws JSONException{
			String res = askSymbolsInOrder(this ,"0");
			while(  !((res.split(":")[0].trim().equalsIgnoreCase("exit"))) ||
		(!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length-1])) ){
			
			if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				takeMoneyFromBank(3, currentPlayingPlayer);
				res = askSymbolsInOrder(this, res.split(":")[1].trim());
			}else if((res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				addToDiscardPile(1, this, currentPlayingPlayer);return;
			}
			else if(res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])&& 
					!(res.split(":")[0].trim().equalsIgnoreCase("exit"))){
				placeMinionActionPlayerCard(currentPlayingPlayer);
				break;
			}
		
			}
		addToDiscardPile(1, this, currentPlayingPlayer);
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
	
	@Override
	public void takeMoneyFromBank(Player currPlayer,int amount) {
		takeMoneyFromBank(amount, currPlayer);
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
		 for(GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard())
			if(!(gc.getName().equalsIgnoreCase(enumType.getName())))
				System.out.println(gc.getName() +", ");
		 String res = questionsToAsk("Choose which card to play:nul");
		 for(GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard())
				if((gc.getName().equalsIgnoreCase(enumType.getName()))){
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
	 */
	@Override
	public String askSymbolsInOrder(GreenPlayerCardEnum tempEnum, String result) {
		String res = "enter";
		res = UserInputUtility.USERINPUTUTILITYENUM.getUserInput(tempEnum,result);
		return res;
	}
	
	/**
	 * this method will allow a current player to take specified amount from another player
	 */
	@Override
	public boolean takeMoneyFromPlayer(int amt, Player currentPlayingPlayer, Player fromPlayer) {
		
		if(!(fromPlayer.equals(null))){
		System.out.println("Taking "+amt+"$'s from "+fromPlayer.getPlayerColor()+ "player...");
		String res = questionsToAsk("Would you Give me $'s .Hit Y for Yes or elsewise : nul");
		if(res.equalsIgnoreCase("y")){
			currentPlayingPlayer.setPlayerAmount(currentPlayingPlayer.getPlayerAmount() + amt);
			fromPlayer.setPlayerAmount(fromPlayer.getPlayerAmount() - amt);
		}
		else{
			return false;
		}
		
		System.out.println("Action completed..");
		return true;
		}else
			return false;
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
	public void takePlayingCards(Player currrentPlayer , int number) {
		
		System.out.println("Taking " +number+ "player cards..");
		String result = questionsToAsk("Enter a players piece color to take playing card from : nul");
		Player selectedPlayer = selectPlayer(currrentPlayer, result);
		
		while(number!=0){
		System.out.println("Your cards as of now  "+selectedPlayer.getPlayerColor()+" player");
		for(GreenPlayerCardEnum gc : selectedPlayer.getPlayersPlayingCard())
			System.out.print(gc.getName()+", ");

		playingCardsAction(currrentPlayer,selectedPlayer,number);
		number--;
		}
		System.out.println("Action completed..");
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
		
		System.out.println(amt+"$'s will be taken from bank...");
		if(amt!=0){
			currentPlayer.setPlayerAmount(currentPlayer.getPlayerAmount()+amt);
			BoardGame.setBank(BoardGame.getBank()-amt);
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
		
		takeLoanFromBank(amt, currentPlayer);
	}
	/**
	 * deposit money to the bank
	 */
	@Override
	public void payMoneyToBank(int amt, Player currentPlayer) {
		
		if(amt!=0 && !(currentPlayer.equals(null))){
			currentPlayer.setPlayerAmount(currentPlayer.getPlayerAmount() - amt);
			BoardGame.setBank(BoardGame.getBank() + amt);
		}
		else{
			System.out.println("Amt cannot be zero or ");
		}
		
	}
	/**
	 * removes specified number of  minion for a player
	 */
	@Override
	public void removeMinion(int num, Player currentPlayer) {
		
			while(num!=0){
				String minionlocation = questionsToAsk("Enter area name to remove your minion from : nul");
				for(ArrayList<String> str : currentPlayer.getMinions().values()){
					for(String temp : str){
						if(minionlocation.equalsIgnoreCase(temp)){
							currentPlayer.getMinions().remove(temp);
							currentPlayer.setMinionQuantity(currentPlayer.getMinionQuantity()-1);
							currentPlayer.getAreaInstanceFromAreaName(temp).setTroubleMarkers(false);
							break;
						}
					}
				}
				num--;
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
			String toLocation) throws JSONException {

		if(!(fromPlayer.equals(null))){
			String result = BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.getInstance().areaDetails, toLocation);
			BoardGame.displayAdjacentAreas(result);
			String res = questionsToAsk("Enter area name to whose adjacent area you need to move minion to : nul");
			// record the result and place the opposite players minion there
			if(!res.equals(null)){
				fromPlayer.setMinions(fromPlayer.getPlayerColor(), res);
			}
		}
		else{
			System.out.println("From Player is empty");
		}
	}
	
	/** 
	 * this method would first display a list of areas from where the building can be removed
	 * This method removes the building of the opposite player from the area chosen
	 */
	@Override
	public void removeBuilding(Player currentPlayer, Player fromPlayer) {
		
		System.out.println("Opposite Players Areas where he has Building ");
		if(!(fromPlayer.getPlayerAreas().isEmpty())){
			for(Area str : fromPlayer.getPlayerAreas()){
				System.out.print(str.getAreaName() + ", ");
			}
		}
		String res = questionsToAsk("Enter the area name you want to remove building from : nul");
		for(Area area : fromPlayer.getPlayerAreas()){
			if(res.trim().equalsIgnoreCase(area.getAreaName())){
				area.removeBuilding(area.getAreaName());
				break;
			}
			
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
		
		
		if(!(InterruptCard.wantToPlayInterruptCard(fromPlayer, currentPlayer).equalsIgnoreCase("success"))){
			removeMinion(num, fromPlayer);
			return false;
		}
		else{
			System.out.println("Other player has played an interrupt card");
		}
		return true;
	}

	@Override
	public void removeMinionOFYourOwn(int num, Player currentPlayer) {
		
		removeMinion(num, currentPlayer);
	}

	/**
	 * Adds the specified players playing cards to the discard pile list on the board
	 */
	@Override
	public void addToDiscardPile(int num, GreenPlayerCardEnum gc,Player ps) {
		
		if(!(ps.getPlayersPlayingCard().size()>5)){
		while(num!=0){
			if(gc instanceof GreenPlayerCardEnum){
				System.out.println("Card being added to discard Pile");
				ps.getPlayersPlayingCard().remove(gc);
				BoardGame.setDiscardPilePlayerCards(gc);
				num--;
				}
			else{
				System.out.println("Unsupported playercard. Verify the type of cardbeing passed");
			}
		}
		
		fillYourHandWIthPlayerCard(1,ps);
		}
		else{
			System.out.println("No need to discard ");
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

		String res = questionsToAsk("Enter card name : nul");
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
	public void removeTroubleMarker(String areaName) {
		
		System.out.println("Trouble marker wil be removed..");
		for(Area a : BoardGame.board_areas){
			if(areaName.equalsIgnoreCase(a.getAreaName())){
				if(a.isTroubleMarkers()){
					a.setTroubleMarkers(false);
					break;
				}
			}
		//break;
		}
		System.out.println("Action Completed...");
	}

	/**
	 * method removes a players minion
	 */
	@Override
	public void assasinate(Player ps) throws JSONException {
		
		System.out.println("Minion pieces will be assasinated");
		for(Area a : BoardGame.board_areas){
			if(a.isTroubleMarkers()){
				// get minions in this area
				System.out.println("Minions for each player in this area:");
				System.out.printf("%-15s%-15s",a.getMinionsForEveryPlayer(a.getAreaName()),a.getAreaName() );
			}	
				
		String playerToSelect = questionsToAsk("Enter players Piece Color:nul");
		Player player = selectPlayer(ps, playerToSelect);
		removeMinionOFAnotherPlayer(1, ps, player);
			break;	
			}
		
		System.out.println("Action Completed...");
	}
	@Override
	public void placeTroubleMarker(Player currentPlayer, String areaLocation)
			throws JSONException {
		   
		
	}
	@Override
	public void ignoreRandomEvent(Player currentPlayer) {
		   
		
	}
	@Override
	public void removeoneTroubleMarker(Player currentPlayer) {
		   
		
	}
	@Override
	public void discardCard(Player currentPlayer) {
		   
		
	}
	@Override
	public void placeoneMinion(Player currentPlayer, String areaLocation)
			throws JSONException {
		   
		
	}
	/**
	 * draw specified number of cards from discard pile 
	 */
	@Override
	public void drawCardsFromDiscardPile(int num, Player player) {

		System.out.println("Cards will be drawn from discarded pile");
		while(num!=0){
				Random ran = new Random();
				int i = ran.nextInt(BoardGame.getDiscardPilePlayerCards().size()-1);
				System.out.println("Adding cards from Discard Pile");
				player.getPlayersPlayingCard().add(BoardGame.getDiscardPilePlayerCards().get(i));
			}
		System.out.println("Action Completed...");
	}
	
	/**
	 * place a minion with all the rules of placing a minion being implemented
	 */
	@Override
	public void placeMinionActionPlayerCard(Player currentPlayingPlayer) throws JSONException {
		System.out.println("Place a minion in area form these only..");
		
		if(currentPlayingPlayer.getMinionQuantity()!=0 && currentPlayingPlayer.getMinionQuantity()<12){
			BoardGame.displayMinionsForPlayerOnBoard(currentPlayingPlayer);
			System.out.println();
			String minionLocation = questionsToAsk("Enter area name where you want to place a minion? :nul");
			// should display the current state of board
			// print out the board state on a frame gui
			for(Area a : BoardGame.board_areas){
				if(a.getAreaName().equalsIgnoreCase(minionLocation)){
					currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
					if(!(a.isTroubleMarkers()) && currentPlayingPlayer.totalMinionsInAreaForAllPlayers(minionLocation)>1)
						a.setTroubleMarkers(true);
					break;
				}
			}
		}
		else if(currentPlayingPlayer.getMinionQuantity() == 12){
			System.out.println("Choose an area to remove minion from....");
			BoardGame.displayMinionsForPlayerOnBoard(currentPlayingPlayer);
			removeMinionOFYourOwn(1, currentPlayingPlayer);
			placeMinionActionPlayerCard(currentPlayingPlayer);
		
		String minionLocation = questionsToAsk("Enter area name where you want to place a minion? :nul");
		// should display the current state of board
		// print out the board state on a frame gui
		for(Area a : BoardGame.board_areas){
			if(a.getAreaName().equalsIgnoreCase(minionLocation)){
				currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
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
					break;
				}
			}
		}
		System.out.println("Action completed..");
	}
	/**
	 * This method will allow the user to discard as many cards as he wants from his hand
	 */
	@Override
	public String discardCardsPerYourWish(Player currentPlayingPlayer, GreenPlayerCardEnum tempname, int amt){
		
		try{
		System.out.println("Cards will be discarded as per your wish");
		while(currentPlayingPlayer.getPlayersPlayingCard().size()!=0){
			System.out.println("Your cards as of now: ");
			for(GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard())
				System.out.print(gc.getName()+" ,");
			
			String result = questionsToAsk("Enter the card name : Hit '0' to exit");
			if(!(result.equalsIgnoreCase("exit"))){
				for(GreenPlayerCardEnum temp : currentPlayingPlayer.getPlayersPlayingCard()){
					if(currentPlayingPlayer.getPlayersPlayingCard().contains(result)){
						addToDiscardPile(1,temp, currentPlayingPlayer);
						takeMoneyFromBank(amt, currentPlayingPlayer);
						System.out.println("Card Discarded");
					}
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
	public void placeMinionActionPlayerCard(Player currentPlayingPlayer,String s){};
}
