package com.app.rules;

import com.app.Area;
import com.app.BoardGame;
import com.app.Player;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.common.Utility;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

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

    public RandomEventCard getShuffledRandomEventCard() {

        RandomEventCard rc = null;
        for (RandomEventCard rec : RandomEventCard.values()) {
            for (RandomEventCard temp : BoardGame.getDiscardedRandomEventCards()) {
                if (!(temp.name().equals(rec.name())))
                    rc = rec;

            }
        }
        return rc;
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

    public Boolean doTheTasks(Player currentPlayer, RandomEventCard rc, GreenPlayerCardEnum tempGreenPlayerCard) {
        Utility utility = new Utility();

        switch (this) {
            case TheDragon: {
                int areaNumber = utility.rollDie();
                Area area = utility.getAreaByNumber(areaNumber);
                area.setBuildngs(Boolean.FALSE);
                area.setMinions(0);
                area.setTroubleMarkers(Boolean.FALSE);
                area.setTrolls(0);
                area.setDemons(0);
                return Boolean.TRUE;
            }

            case Flood: {
                //todo: check it later to be exact and correct
                int firstAreaNumber = utility.rollDie();
                int secondAreaNumber = utility.rollDie();
                Area firstArea = utility.getAreaByNumber(firstAreaNumber);
                Area secondArea = utility.getAreaByNumber(secondAreaNumber);
                JSONObject areaDetails = BoardGame.getInstance().getAreaDetails();

                if ((firstAreaNumber != 3) && (firstAreaNumber != 6) && (firstAreaNumber != 9)) {
                    try {
                        BoardGame.displayAdjacentAreas(BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.areaDetails, firstArea.getAreaName()));
                        GreenPlayerCardEnum.GLOBALOBJ.removeMinionOFYourOwn(1, currentPlayer);
                        GreenPlayerCardEnum.GLOBALOBJ.placeMinionActionPlayerCard(currentPlayer, secondArea.getAreaName());
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }

                if ((secondAreaNumber != 3) && (secondAreaNumber != 6) && (secondAreaNumber != 9)) {
                    try {
                        BoardGame.displayAdjacentAreas(BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.areaDetails, secondArea.getAreaName()));
                        GreenPlayerCardEnum.GLOBALOBJ.removeMinionOFYourOwn(1, currentPlayer);
                        GreenPlayerCardEnum.GLOBALOBJ.placeMinionActionPlayerCard(currentPlayer, firstArea.getAreaName());
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                return Boolean.TRUE;
            }

            case Fire: {
            	 int areaNumber = utility.rollDie();
                 Area initiateAreaOnFire = utility.getAreaByNumber(areaNumber);
                 performFireAction(initiateAreaOnFire);
                 
            }
            case Fog: {

                GreenPlayerCardEnum.GLOBALOBJ.discardCardsPerYourWish(currentPlayer, tempGreenPlayerCard, 5);
                return Boolean.TRUE;
            }
            case Riots: {
                int totalNumTroubleMarkers = utility.calculateNumberOfTroubleMarkers();
                if (totalNumTroubleMarkers >= 8)
                    utility.announceWinner();
            }

            case Explosion: {
                int areaNumber = utility.rollDie();
                Area area = utility.getAreaByNumber(areaNumber);
                if (area.isAreaCityCards()) {
                    area.setBuildngs(Boolean.FALSE);
                }
                return Boolean.TRUE;
            }
            case MysteriousMurders: {
                int counter = 0;

                do {
                    int areaNumber = utility.rollDie();
                    Area area = utility.getAreaByNumber(areaNumber);
                    int numberOfMinions = utility.getNumberOfMinions(area.getAreaName());
                    currentPlayer.setMinionQuantity(--numberOfMinions);
                    utility.giveTurnToleft();
                    counter++;
                } while (counter <= BoardGame.playersInGame.size());

            }
            case DemonsFromTheDungeonDimensions: {
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
                int numberOfBuilding = 0;
                int amountOfEachPlayer = 0;
                for (Player player : BoardGame.playersInGame) {
                    numberOfBuilding = player.getNumberOfBuildings();
                    if (numberOfBuilding > 0)
                        for (int i = 0; i < numberOfBuilding; i++) {
                            amountOfEachPlayer = player.getPlayerAmount();
                            amountOfEachPlayer = amountOfEachPlayer - 2;
                            if (amountOfEachPlayer < 0) {
                                numberOfBuilding = player.getNumberOfBuildings();
                                numberOfBuilding--;
                                player.setNumberOfBuildings(numberOfBuilding);
                                amountOfEachPlayer = amountOfEachPlayer + 2;
                            }
                            player.setPlayerAmount(amountOfEachPlayer);
                        }
                }
                return Boolean.TRUE;
            }
            case BloodyStupidJohnson: {
                int areaNumber = utility.rollDie();
                Area area = utility.getAreaByNumber(areaNumber);
                for (Player player : BoardGame.playersInGame) {
                    for (Area tempArea : player.getPlayerAreas()) {
                        if (tempArea.equals(area)) {
                            int minionNumber = player.getMinionQuantity();
                            minionNumber--;
                            player.setMinionQuantity(minionNumber);
                            area.setAreaCityCards(Boolean.FALSE);
                        }
                    }

                }
                return Boolean.TRUE;

            }
            case Trolls: {
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
                int firstAreaNumber = utility.rollDie();
                int secondAreaNumber = utility.rollDie();
                Area firstArea = utility.getAreaByNumber(firstAreaNumber);
                Area secondArea = utility.getAreaByNumber(secondAreaNumber);
                firstArea.setBuildngs(Boolean.FALSE);
                secondArea.setBuildngs(Boolean.FALSE);
                return Boolean.TRUE;
            }

            default:
                return Boolean.FALSE;
        }

    }
    
    private void performFireAction(Area initialArea) throws JSONException {

		Utility utility = new Utility();
		if(initialArea.isBuildngs()){
			initialArea.removeBuilding(initialArea.getAreaName());
			Area secondAreaObj = utility.getAreaByNumber(utility.rollDie());
			for(Area a : BoardGame.getAdjacentAreasForAnArea(initialArea.getAreaName())){
				if(secondAreaObj.getAreaName().equalsIgnoreCase(a.getAreaName())){
					performFireAction(secondAreaObj);
				}
			}
		}
		else{
			System.out.println("Fire spreading has stopped..");
		}
	}
}