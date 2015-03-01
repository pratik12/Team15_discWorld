package com.app.PlayingCardSystem;

import java.util.Scanner;

import userInputUtility.UserInputUtility;

import com.app.Player;

public enum ScrollUtility  {

	TESTR;
	UserInputUtility uiuObj;
	
	public void performAction(String enumName, Player currentPlayingPlayer){
		
		PlayerCard temp = null;
		
		for(PlayerCard p : currentPlayingPlayer.getPlayersPlayingCard()){
			if(p.getName().equalsIgnoreCase(enumName))
				temp = p;
		}
		
		switch(temp){
		
		case AGONYAUNTS: case ANGUA: case CAPTAINCARROT: case DRCRUCES:  case GROAT: case GIMLETDWARFDELICATESSEN:
		case INIGOSKIMMER: case GRYLE: 	case HARGAHOUSEOFRIBS: case THEMENDEDDRUM: 	case THEPEELEDNUTS:
		case MRPINANDMRTULIP:
		
			break;
		case AMBANK:
			// palce this card. take loan of 10 from bank. at end pay 12 to bank or lose 15 points
			break;
		case AMSANCTUARY:
			// each player gives you 1$ or card
			break;
		
		case BEGGARSGUILD:
			// select one player. take 2 cards from them
			String str = uiuObj.getUserInput(null,"0");
			PlayingCardRuleEngine.TEST.selectPlayer(currentPlayingPlayer, str);
			break;
		case BOGGIS:
			// take 2$ from evry other player
			String res = askSymbolsInOrder(temp ,"0");
			while(!(res.split(":")[0].equalsIgnoreCase("exit")) || 
				(!res.split(":")[0].equalsIgnoreCase(temp.getSymbols()[temp.getSymbols().length-1])) ){
				if(res.split(":")[0].equalsIgnoreCase("scroll")){
					PlayingCardRuleEngine.TEST.takeMoney(2, currentPlayingPlayer);
					res = askSymbolsInOrder(temp, res.split(":")[1]);
				}
				else if(res.split(":")[0].equalsIgnoreCase("minion")){
					// call appropriate function
					currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), "Unreal Estate");
				}
			}
			System.out.println("You hae finished playing this card. Place it on discard deck");
			break;
		
		case CMOTDIBBLER:
			// rolll die. if 7 take 4 from bank. if 1 pay 2$ to bank or remove 1 YOUR minion. 
			break;
		
		case DRUMKNOTT:
			// play other 2 cards from your hand
			break;
		case DUCKMAN: case FOULOLERON:
			// moveminion of OTHER player from an area to adjacent area
			break;
		case DYSK: case THEOPERAHOUSE:
			// earn 1$ for each minion on ISLE OF GODS
			break;
		case FRESHSTARTCLUB:
			//if minion removed place it in diff area
			break;
		case GASPODE:
			// stop player from moving or removing one of your minions
			break;
		case HARRYKING: // discard as many cards as u wish and take 2$ for eac discarded
			break;
		case HERENOW: // roll die. if 7 take 3$ from player of your choice. if 1 rem 1 YOUR minion
			break;
		case HEX: // take 3 cards from draw deck
			break;
		case HISTORYMONKS: // shuffle discard pile and draw 4 cards
			break;
		
		case LEONARDOFQUIRM: case LIBRARIAN: // take 4 cards frm draw deck
			break;
		case MODO: // discard 1 card
			break;
		case MRSCAKE:// confused
			break;
		case NOBBYNOBBSS: // take 3$ from player of ur choice
			break;
		case QUEENMOLLY: //select player. give you 2 card of heric hoice
			break;
		case RINCEWIND: // move minion from area hasving TM to adjacent area
			break;
		case ROSIEPALM: case THESEAMSTRESSESGUILD: // chose player. give them card from your hand. take 2 $
			break;
		case SACHARISSACRIPSLOCK:// earn 1$ each for each trouble marker
			break;
		case SHONKYSHOP:// discard as many cards as u wish and take 2$ for eac discarded
			break;
		case THEFIREBRIGADE:
			// select plaeyr if they dont give you 5$ remove 1 of his building from board
			break;
		case THEFOOLSGUILD: case DRWHITEFACE:
			// slect player if they dont give 5$ place this card front of them...confused
			break;
	
		case THEROYALMINT: case PINKPUSSYCATCLUB: 
			break;
			
		case THETHIEVESGUILD: // take 2$ from every other player
			break;
		case WALLACESONKY: // unaffected to other card played by player
			break;
		case ZORGOTHERETROPHRENOLOGIST: // exchange PERSONALITY CARD with one from not used pile
			break;
		default:
			break;
		
		
		}
		
	}
	
	public static String askSymbolsInOrder(PlayerCard tempEnum, String result){
		
		UserInputUtility u = new UserInputUtility();
		String res = "enter";
		
			res = u.getUserInput(tempEnum,result);
			return res;
	}

	public static void main(String[] args){
		String res = askSymbolsInOrder(PlayerCard.BOGGIS,"");	
	}

}
