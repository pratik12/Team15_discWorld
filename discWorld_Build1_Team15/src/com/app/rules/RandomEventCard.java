package com.app.rules;

import com.app.Actions;
import com.app.Area;
import com.app.BoardGame;
import com.app.Player;
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
public enum RandomEventCard implements Actions {

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

    public Boolean doTheTasks() {
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
                int firstAreaNumber = utility.rollDie();
                int secondAreaNumber = utility.rollDie();
                Area firstArea = utility.getAreaByNumber(firstAreaNumber);
                Area secondArea = utility.getAreaByNumber(secondAreaNumber);

                if ((firstAreaNumber != 3) && (firstAreaNumber != 6) && (firstAreaNumber != 9)) {
                    //todo: It can not move to secondArea
                    moveMinion();
                }
                if ((secondAreaNumber != 3) && (secondAreaNumber != 6) && (secondAreaNumber != 9)) {
                    //todo: It can not move to firstArea
                    moveMinion();
                }
                return Boolean.TRUE;
            }

            case Fire: {
                int areaNumber = utility.rollDie();
                Area area = utility.getAreaByNumber(areaNumber);
                JSONObject jsonObject = BoardGame.getAreaDetails();
                String adjacentAreaStr = null;
                int secondTime = 0;
                do {
                    area.setBuildngs(Boolean.FALSE);
                    secondTime = utility.rollDie();
                    try {
                        adjacentAreaStr = BoardGame.getAdjacentAreaIDs(jsonObject, area.getAreaName());
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                } while (adjacentAreaStr.contains(Integer.toString(secondTime)) && area.isBuildngs());
                if (!area.isBuildngs()) {
                    return Boolean.TRUE;
                }
            }
            case Fog: {
                //todo:remove five playercards
                return Boolean.TRUE;
            }
            case Riots: {
                int totalNumTroubleMarkers = utility.calculateNumberOfTroubleMarkers();
                if (totalNumTroubleMarkers >= 8)
                    utility.isWinningConditionChecked();
            }

            case Explosion: {
                int areaNumber = utility.rollDie();
                Area area = utility.getAreaByNumber(areaNumber);
                area.setBuildngs(Boolean.FALSE);
                return Boolean.TRUE;
            }
            case MysteriousMurders: {
                int counter = 0;
                do {
                    int areaNumber = utility.rollDie();
                    Area area = utility.getAreaByNumber(areaNumber);
                    int playerCounter = 0;
                    Player[] players = new Player[12];

                    for (Player p : area.getPlayersInThisAreas()) {
                        //todo: count other playes in the area
                        players[playerCounter] = p;
                        playerCounter++;
                    }
                    int numberOfPlayer = utility.getRandNum(playerCounter);
                    int numberOfMinions = players[numberOfPlayer].getMinionQuantity();
                    numberOfMinions--;
                    players[numberOfPlayer].setMinionQuantity(numberOfMinions);
                    utility.giveTurnToleft();
                    counter++;
                } while (counter <= 4);

            }
            case DemonsFromTheDungeonDimensions: {
                int firstAreaNumber = utility.rollDie();
                int secondAreaNumber = utility.rollDie();
                int thirdAreaNumber = utility.rollDie();
                int fourthAreaNumber = utility.rollDie();
                Area firstArea = utility.getAreaByNumber(firstAreaNumber);
                Area secondArea = utility.getAreaByNumber(secondAreaNumber);
                Area thirdArea = utility.getAreaByNumber(thirdAreaNumber);
                Area fourthArea = utility.getAreaByNumber(thirdAreaNumber);
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
                //todo: any building there, has a value of Zero
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
                //todo: be sure area is the one belongs to player
                for (Player player : BoardGame.playersInGame) {
                    for (Area tempArea : player.getPlayerAreas()) {
                        if (tempArea.equals(area)) {
                            int minionNumber = player.getMinionQuantity();
                            minionNumber--;
                            player.setMinionQuantity(minionNumber);
                            //todo: control below line it should be wiped out
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

    public static void main(String[] args) {
        for (RandomEventCard p : RandomEventCard.values()) {
            System.out.println("******" + p);
            p.doTheTasks();
        }
    }


    @Override
    public boolean addMoney(int amount) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean moveMinion() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean assasinate(String pieceToRemove) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean scroll() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean playAnotherCard() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean interrupt() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
