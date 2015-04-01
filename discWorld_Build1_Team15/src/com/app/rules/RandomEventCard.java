package com.app.rules;

import com.app.Area;
import com.app.BoardGame;
import com.app.InterruptCard;
import com.app.Player;
import com.app.PlayerCardUtility;
import com.app.CityAreaCardSystem.CityAreaCardEnum;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.common.ComponentUtilities;
import com.app.common.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/26/15
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public enum RandomEventCard {
    GLOBALOBJ(0),
    Flood(1),
    TheDragon(2),
    MysteriousMurders(3),
    Fog(4),
    Riots(5),
    DemonsFromTheDungeonDimensions(6),
    Subsidence(7),
    BloodyStupidJohnson(8),
    Trolls(9),
    Explosion(10),
    Earthquake(11),
    Fire(12);

    public static RandomEventCard getShuffledRandomEventCard() { // made static for verification
    	
    	
        ArrayList<RandomEventCard>  rc = new ArrayList<RandomEventCard>();
        
        RandomEventCard retVal = null;
        for(RandomEventCard rec : RandomEventCard.values()){
        	if(!(rec.name().equals(GLOBALOBJ.name())) && !BoardGame.getDiscardedRandomEventCards().contains(rec))
        		rc.add(rec);
        }
        
        int i = BoardGame.shuffle(13);
        	
        if(!(rc.size()<=i)){
        	retVal = rc.get(i);
        }
        else{
        	getShuffledRandomEventCard();
        }
        return retVal;
    }

    private static final Map<Integer, String> lookup
            = new HashMap<Integer, String>();

    static {
        for (RandomEventCard s : EnumSet.allOf(RandomEventCard.class))
            lookup.put(s.getCode(), s.name());
    }

    private int code;

    private RandomEventCard(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static String get(int code) {
        return lookup.get(code);
    }

    Utility utility = new Utility();
    ComponentUtilities cu = new ComponentUtilities();
    
    public Boolean doTheTasks(Player currentPlayer, RandomEventCard rc, GreenPlayerCardEnum tempGreenPlayerCard) throws JSONException {
        switch (rc) {
        
            case TheDragon: {
            	System.out.println(rc.name() + " occured..");
                int areaNumber = utility.rollDie();
                Area area = utility.getAreaByNumber(areaNumber);
                /*area.setBuildngs(Boolean.FALSE);*/
                System.out.printf("%2s%10s%6s\n","  ","Roll Die",areaNumber);
                System.out.println(area.getAreaName()+" has been struck by Dragon..Wohhoo");
                currentPlayer.getAreaInstanceFromAreaName(area.getAreaName()).setTroubleMarkers(false);
                System.out.printf("%2s%10s\n","  ","Removed ");
               
               // boolean cardUsed = false;
                for(Player p : BoardGame.playersInGame){
                	
                	System.out.println("Carrying out removing of pieces for Player "+p.getPlayerColor());
                	ArrayList<String> minArea = new ArrayList<String>();
                	for(ArrayList<String> s : p.getMinions().values())
                		for(String str : s)
                			minArea.add(str);
                	
                	
                	
                	int count = 0 ;
                	//boolean remPiece = false;
                	//boolean askForSmallGod = true;
                	//for(ArrayList<String> s : p.getMinions().values()){
                		for(String st : minArea){
                			if(!(st.equalsIgnoreCase("")) && st!=null){
                				count++;
                				//if(p.getMinions().values().size()!=0 && p.getMinions().values()!=null ){
                					/*if(askForSmallGod){
                					if(isSmallGods(p)){
                						String qns = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Would you like to use your "
                								+ " Small Gods City Area Card, Hit Y or N:nul");
                						if(qns.equalsIgnoreCase("y")){
                							remPiece = true;
                							askForSmallGod = false;
                							GreenPlayerCardEnum.GLOBALOBJ.payMoneyToBank(3, p);
                							cardUsed = true;
                						}
                						else{
                							remPiece = false;
                						}
                					}
                					}					*/
                				//	if(!remPiece){
                					GreenPlayerCardEnum.GLOBALOBJ.removeMinionFromLocation(p.getMinions().values().size(), p, area.getAreaName());
                					
                				//	}
                				//}
                			}
                		}
                	//}
                	System.out.println(count+" "+p.getPlayerColor()+" been removed..");
                }
                //if(!cardUsed)
                	area.removeBuilding(area.getAreaName());
                if(area.getTrolls()>0){
                	System.out.println(area.getTrolls()+" Trolls been removed..");
                	BoardGame.setTrolls(BoardGame.getTrolls()+area.getTrolls());
                	area.setTrolls(0);
                }
                if(area.getDemons()>0){
                	System.out.println(area.getDemons()+" Demons been removed..");
                	BoardGame.setDemons(BoardGame.getDemons()+area.getDemons());
                	area.setDemons(0);
                }
                return Boolean.TRUE;
            }

            case Flood: {
                //todo: check it later to be exact and correct
            	System.out.println(rc.name() + " occured..");
            	for(int i =0;i<2;i++){
            		int areaNum = utility.rollDie();
            		
            		Area area = utility.getAreaByNumber(areaNum);
            		System.out.println("Area Rolled " +area.getAreaName());
            		doFloodAction(currentPlayer, areaNum, area);
            	}
                return Boolean.TRUE;
            }

            case Fire: {
            	System.out.println(rc.name() + " occured..");
            	 int areaNumber = utility.rollDie();
                 Area initiateAreaOnFire = utility.getAreaByNumber(areaNumber);
                try {
                    performFireAction(initiateAreaOnFire);
                    return Boolean.TRUE;
                } catch (JSONException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
            case Fog: {
            	System.out.println(rc.name() + " occured..");
                int c = 0 ; 
            	do{
            		GreenPlayerCardEnum temp = BoardGame.player_cards.get(0);
            		System.out.println("Cards Drawn "+temp);
            		BoardGame.getDiscardPilePlayerCards().add(temp);
            		BoardGame.player_cards.remove(temp);
            		System.out.println("Cards discarded... "+temp);
            		c++;
            	}while(c!=5);
                return Boolean.TRUE;
            }
            case Riots: {
            	System.out.println(rc.name() + " occured..");
                int totalNumTroubleMarkers = utility.calculateNumberOfTroubleMarkers();
                if (totalNumTroubleMarkers >= 8){
                    utility.announceWinner();
                    System.exit(0);
                }
                else
                {
                	System.out.println("Less troublemarkers on board , the random event cant't happen");
                }
                return Boolean.TRUE;
            }

            case Explosion: {
            	System.out.println(rc.name() + " occured..");
                int areaNumber = utility.rollDie();
                Area area = utility.getAreaByNumber(areaNumber);
                area.removeBuilding(area.getAreaName());
                return Boolean.TRUE;
            }
            case MysteriousMurders: {
            	System.out.println(rc.name() + " occured..");
            	doMuder(currentPlayer,3);
                return Boolean.TRUE;
            }
            case DemonsFromTheDungeonDimensions: {
            	System.out.println(rc.name() + " occured..");
                int firstAreaNumber = utility.rollDie();
                int secondAreaNumber = utility.rollDie();
                int thirdAreaNumber = utility.rollDie();
                int fourthAreaNumber = utility.rollDie();
                Area firstArea = utility.getAreaByNumber(firstAreaNumber);
                Area secondArea = utility.getAreaByNumber(secondAreaNumber);
                Area thirdArea = utility.getAreaByNumber(thirdAreaNumber);
                Area fourthArea = utility.getAreaByNumber(fourthAreaNumber);
                int demonNum = firstArea.getDemons();
                demonNum++;
                firstArea.setDemons(demonNum);
                firstArea.setTroubleMarkers(Boolean.TRUE);
                firstArea.setAreaCityCards(Boolean.FALSE);
                demonNum = secondArea.getTrolls();
                demonNum++;
                secondArea.setDemons(demonNum);
                secondArea.setTroubleMarkers(Boolean.TRUE);
                secondArea.setAreaCityCards(Boolean.FALSE);
                demonNum = thirdArea.getDemons();
                demonNum++;
                thirdArea.setDemons(demonNum);
                thirdArea.setAreaCityCards(Boolean.FALSE);
                demonNum = fourthArea.getDemons();
                demonNum++;
                fourthArea.setDemons(demonNum);
                fourthArea.setTroubleMarkers(Boolean.TRUE);
                return Boolean.TRUE;


            }
            case Subsidence: {
            	System.out.println(rc.name() + " occured..");
            	for(Player p : BoardGame.playersInGame){
            		performSubsidenceAction(p,cu);
            	}
                return Boolean.TRUE;
            }
            case BloodyStupidJohnson: {
            	System.out.println(rc.name() + " occured..");
            	
                int areaNumber = utility.rollDie();
                Area area = utility.getAreaByNumber(areaNumber);
                
                CityAreaCardEnum en = CityAreaCardEnum.getCityAreaCardInstance(area.getAreaName());
                for (Player player : BoardGame.playersInGame) {
                	if(player.getCityAreaCardsStore().contains(en)){
                		System.out.println(en.getareaName()+ " Card set to one side");
                		en.setActiveValue(false);
                		GreenPlayerCardEnum.GLOBALOBJ.removeMinionFromLocation(1, player,area.getAreaName());
                		System.out.println("Minion of Player "+player.getPlayerColor()+ " removed from "
                				+en.getareaName() );
                	}
                	else{
                		System.out.println("No CityAreaCard for Player "+player.getPlayerColor()+" in "+en.getareaName());
                	}
                }
                    
                return Boolean.TRUE;

            }
            case Trolls: {
            	System.out.println(rc.name() + " occured..");
                int firstAreaNumber = utility.rollDie();
                int secondAreaNumber = utility.rollDie();
                int thirdAreaNumber = utility.rollDie();
                Area firstArea = utility.getAreaByNumber(firstAreaNumber);
                Area secondArea = utility.getAreaByNumber(secondAreaNumber);
                Area thirdArea = utility.getAreaByNumber(thirdAreaNumber);
                int trollNum = firstArea.getTrolls();
                trollNum++;
                firstArea.setTrolls(trollNum);
                trollNum = secondArea.getTrolls();
                trollNum++;
                secondArea.setTrolls(trollNum);
                trollNum = thirdArea.getTrolls();
                trollNum++;
                thirdArea.setTrolls(trollNum);
                return Boolean.TRUE;
            }
            case Earthquake: {
            	System.out.println(rc.name() + " occured..");
                int firstAreaNumber = utility.rollDie();
                int secondAreaNumber = utility.rollDie();
                Area firstArea = utility.getAreaByNumber(firstAreaNumber);
                Area secondArea = utility.getAreaByNumber(secondAreaNumber);
                firstArea.removeBuilding(firstArea.getAreaName());
                secondArea.removeBuilding(secondArea.getAreaName());
                return Boolean.TRUE;
            }

            default:
                return Boolean.FALSE;
        }

    }
    
    private void doMuder(Player currentPlayer, int count) throws JSONException {
    	
    	int i = utility.rollDie();
    	Area a = utility.getAreaByNumber(i);
    	if(a.getMinionsForEveryPlayer(a.getAreaName(),currentPlayer)!="none"){
    		System.out.printf("%15s%10s\n","Player Color","Area Name");
    		for(Player p : BoardGame.playersInGame){
    			
    			cu.showMinionsAllPlayers(a.getAreaName(), p);
    		}
    		String num = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Choose an area:nul");
    		// this function returns selected area name and player color
    		String s = cu.getAreaNameDisplayMinion(num);
    		Player fromPlayer = cu.getPlayerFromPieceColor(s.split(":")[1]);
    		remMinionRECFucntion(fromPlayer,currentPlayer,s.split(":")[0]);
    	}else{
    		System.out.println("No minions to choose from"+a.getAreaName());
    	}
    	
    		count--;
    		if(count>=0){
    			String str = utility.giveTurnToleft();
    			Player nextPlayer = cu.getPlayerFromPieceColor(str.trim());
    		
    			doMuder(nextPlayer,count);
    		}
	}

	private void remMinionRECFucntion(Player fromPlayer, Player currentPlayer, String areaName) throws JSONException {
		
		String r = InterruptCard.wantToPlayInterruptCard(fromPlayer, currentPlayer);
		
		if(!(r.equalsIgnoreCase("gasp")) || r.equalsIgnoreCase("fsc")){
			//if(fromPlayer.getAreaInstanceFromAreaName(areaName).isTroubleMarkers()){
				GreenPlayerCardEnum.GLOBALOBJ.removeMinionFromLocation(1, fromPlayer, areaName);
			System.out.println("Minion assasinated..");
			
			if(r.equalsIgnoreCase("fsc")){
				PlayerCardUtility.getEnumInstance("Fresh Start Club").performTasks(fromPlayer,true);
			}
			}
			else{
				System.out.println("No trouble Markers. Cannot remove the minion....");
				return;
			}
		//}
		if(r.equalsIgnoreCase("gasp")){
			System.out.println("Other player has played GASPODE interrupt card");
		}
	}

	private void performSubsidenceAction(Player p,ComponentUtilities cu) throws JSONException {
    	
	    	cu.displayBuildingsForPlayeronBoard(p);
	    	System.out.println();
	    	
	    	ArrayList<CityAreaCardEnum> temporary = new ArrayList<CityAreaCardEnum>();
	    	for(CityAreaCardEnum cae : p.getCityAreaCardsStore()){
	    		temporary.add(cae);
	    	}
			for(CityAreaCardEnum cae : temporary){
				if(!(p.getPlayerAmount()<2)){
					GreenPlayerCardEnum.GLOBALOBJ.payMoneyToBank(2, p);
					System.out.println("2$ paid to bank for building in area "+cae.getareaName()+ 
							" Foo Player " + p.getPlayerColor());
				}
				else{
					System.out.println("Insufficient funds...");
					System.out.println("Building from this area "+cae.getareaName()+" will be removed "+
							" Foo Player " + p.getPlayerColor());
					p.getAreaInstanceFromAreaName(cae.getareaName()).removeBuilding(cae.getareaName());
				}
			}
	}

	private void performFireAction(Area initialArea) throws JSONException {

		Utility utility = new Utility();
		Area secondAreaObj = null;
		if(initialArea.isBuildngs()){
			initialArea.removeBuilding(initialArea.getAreaName());
			
			int i = utility.rollDie();
			if(i>12)
				utility.rollDie();
			secondAreaObj = utility.getAreaByNumber(i);
			for(Area a : BoardGame.getAdjacentAreasForAnArea(initialArea.getAreaName())){
				if(secondAreaObj.getAreaName().equalsIgnoreCase(a.getAreaName())){
					performFireAction(secondAreaObj);
				}
			}
			System.out.println("No more affected adjacent areas found..Fire Spreading stopped");
		}
		else{
			System.out.println("No building in "+initialArea.getAreaName());
			System.out.println("Fire spreading has stopped..");
		}
	}
	
private void doFloodAction(Player currentPlayer,int num,Area area) {
    
	System.out.println("It is turn of player "+currentPlayer.getPlayerColor());
	ArrayList<String> minionAreas = new ArrayList<String>();
	for(ArrayList<String> minionArea : currentPlayer.getMinions().values()) {
        for (String temp : minionArea) {
        	
        		minionAreas.add(temp);
        }
        }
    	Utility util = new Utility();
        if ((num != 3) && (num != 6) && (num != 9)) {
            try {
                BoardGame.displayAdjacentAreas(BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.areaDetails, area.getAreaName()),0);
                String selectedAdjacentArea1 = GreenPlayerCardEnum.GLOBALOBJ.questionsToAsk("Choose an area:nul");
                String selectedAdjacentArea = BoardGame.getPieceNumberList(selectedAdjacentArea1);
                if (!selectedAdjacentArea.equals(area.getAreaName())) {
                	for(int i =0 ; i < minionAreas.size(); i++)
                        	if(!(minionAreas.get(i).equalsIgnoreCase(""))){	
                           if (minionAreas.get(i).trim().equalsIgnoreCase(area.getAreaName())) {
                                currentPlayer.getMinions().remove(area.getAreaName());
                                currentPlayer.setMinionQuantity(currentPlayer.getMinionQuantity() - 1);
                                currentPlayer.getAreaInstanceFromAreaName(area.getAreaName()).setTroubleMarkers(false);
                                currentPlayer.placeMinion(selectedAdjacentArea);
                                //break;
                            }
                        }
                } else {
                    System.out.println("Selected Area Is An Affected Area and not acceptable");
                }
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        else{
        	System.out.println("Flood cannot occur in "+area.getAreaName());
        	return;
        }

            for (Player player : BoardGame.playersInGame) {
            	
            	String nextPlayer = util.giveTurnToleft();
            	
            	ArrayList<String> minionAreass = new ArrayList<String>();
            	for(ArrayList<String> s : player.getMinions().values())
            		for(String sgh : s)
            			if(!sgh.equalsIgnoreCase(""))
            				minionAreass.add(sgh);
            	
                if (player.getPlayerColor().equalsIgnoreCase(nextPlayer)){// && !nextPlayer.equalsIgnoreCase(currentPlayer.getPlayerColor())){
                		for(String thisSting : minionAreass)
                			if(thisSting.trim().equalsIgnoreCase(area.getAreaName()))
                				doFloodAction(player,num,area);
                }
                
                minionAreass.clear();
                //break;
            }
    }

	public boolean isSmallGods(Player ps){
		
		if(ps.getCityAreaCardsStore().contains(CityAreaCardEnum.SMALLGODS))
			return true;
		else
			return false;
	}
}