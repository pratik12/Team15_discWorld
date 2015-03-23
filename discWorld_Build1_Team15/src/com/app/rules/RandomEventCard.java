package com.app.rules;

import com.app.Area;
import com.app.BoardGame;
import com.app.Player;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.common.Utility;
import org.json.JSONException;

import java.util.ArrayList;
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

    public static RandomEventCard getShuffledRandomEventCard() {

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
        //discard the randomEventCard
        BoardGame.setDiscardedRandomEventCards(this);

        switch (rc) {
            case TheDragon: {
                int areaNumber = utility.rollDie();
                Area area = utility.getAreaByNumber(areaNumber);
                //remove building
                if (area.getPlayersInThisAreas() != null)
                    area.getPlayersInThisAreas().remove(currentPlayer);
                BoardGame.setCityAreaCardRepo(currentPlayer.getCityReaCardFromAreaName(area.getAreaName()));
                currentPlayer.getCityAreaCardsStore().remove(currentPlayer.getCityReaCardFromAreaName(area.getAreaName()));
                //remove minion
                area.setMinions(0);
                area.getMinionColor().remove(new String(currentPlayer.getPlayerColor()));
                //remove trouble markers
                area.setTroubleMarkerArea(null);
                area.setTroubleMarkers(false);
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
                    try {
                        BoardGame.displayAdjacentAreas(BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.areaDetails, firstArea.getAreaName()));
                        String selectedAdjacentArea = utility.checkInputAnswer();
                        if (!selectedAdjacentArea.equals(secondArea.getAreaName())) {
                            //remove minion from destination
                            for (ArrayList<String> minionArea : currentPlayer.getMinions().values()) {
                                for (String temp : minionArea) {
                                    if (firstArea.getAreaName().equalsIgnoreCase(temp)) {
                                        currentPlayer.getMinions().remove(temp);
                                        currentPlayer.setMinionQuantity(currentPlayer.getMinionQuantity() - 1);
                                        currentPlayer.getAreaInstanceFromAreaName(temp).setTroubleMarkers(false);
                                        break;
                                    }
                                }
                            }
                            currentPlayer.placeMinion(selectedAdjacentArea);
                        } else {
                            System.out.println("Selected Area Is An Affected Area and not acceptable");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }

                if ((secondAreaNumber != 3) && (secondAreaNumber != 6) && (secondAreaNumber != 9) && (secondAreaNumber != firstAreaNumber)) {
                    try {
                        BoardGame.displayAdjacentAreas(BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.areaDetails, secondArea.getAreaName()));
                        String selectedAdjacentArea = utility.checkInputAnswer();
                        if (!selectedAdjacentArea.equals(firstArea.getAreaName())) {
                            //remove minion from destination
                            for (ArrayList<String> minionArea : currentPlayer.getMinions().values()) {
                                for (String temp : minionArea) {
                                    if (secondArea.getAreaName().equalsIgnoreCase(temp)) {
                                        currentPlayer.getMinions().remove(temp);
                                        currentPlayer.setMinionQuantity(currentPlayer.getMinionQuantity() - 1);
                                        currentPlayer.getAreaInstanceFromAreaName(temp).setTroubleMarkers(false);
                                        break;
                                    }
                                }
                            }
                            currentPlayer.placeMinion(selectedAdjacentArea);
                        } else {
                            System.out.println("Selected Area Is An Affected Area and not acceptable");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                return Boolean.TRUE;
            }

            case Fire: {
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

                GreenPlayerCardEnum.GLOBALOBJ.discardCardsPerYourWish(currentPlayer, tempGreenPlayerCard, 5);
                return Boolean.TRUE;
            }
            case Riots: {
                int totalNumTroubleMarkers = utility.calculateNumberOfTroubleMarkers();
                if (totalNumTroubleMarkers >= 8)
                    utility.announceWinner();
                else
                    System.out.println("################################No More Than " + totalNumTroubleMarkers + " TroubleMarkers To Win!");
                return Boolean.TRUE;
            }

            case Explosion: {
                int areaNumber = utility.rollDie();
                Area area = utility.getAreaByNumber(areaNumber);
                //remove building
                if (area.getPlayersInThisAreas() != null)
                    area.getPlayersInThisAreas().remove(currentPlayer);
                BoardGame.setCityAreaCardRepo(currentPlayer.getCityReaCardFromAreaName(area.getAreaName()));
                currentPlayer.getCityAreaCardsStore().remove(currentPlayer.getCityReaCardFromAreaName(area.getAreaName()));
                return Boolean.TRUE;
            }
            case MysteriousMurders: {
                doMurder(currentPlayer);
                return Boolean.TRUE;
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
                thirdArea.setTroubleMarkers(Boolean.TRUE);
                thirdArea.setAreaCityCards(Boolean.FALSE);
                demonNum = fourthArea.getDemons();
                demonNum++;
                fourthArea.setDemons(demonNum);
                fourthArea.setTroubleMarkers(Boolean.TRUE);
                fourthArea.setAreaCityCards(Boolean.FALSE);
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
                            //remove building
                            if (amountOfEachPlayer < 0) {
                                numberOfBuilding = player.getNumberOfBuildings();
                                numberOfBuilding--;
                                player.setNumberOfBuildings(numberOfBuilding);
                                amountOfEachPlayer = amountOfEachPlayer + 2;
                                String areaNumber = null;
                                if (!player.getPlayerAreas().isEmpty()) {
                                    System.out.println("Select Which Area you want to remove building from?(please enter number)");
                                    for (Area area : player.getPlayerAreas()) {
                                        System.out.println("############## " + area.getAreaName() + " : " + area.getAreaNumber());
                                    }
                                    areaNumber = utility.checkInputAnswer();

                                    Area area = utility.getAreaByNumber(Integer.parseInt(areaNumber));
                                    if (area.getPlayersInThisAreas() != null)
                                        area.getPlayersInThisAreas().remove(player);
                                    BoardGame.setCityAreaCardRepo(player.getCityReaCardFromAreaName(area.getAreaName()));
                                    player.getCityAreaCardsStore().remove(player.getCityReaCardFromAreaName(area.getAreaName()));
                                }
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
                firstArea.setTroubleMarkers(Boolean.TRUE);
                trollNum = secondArea.getTrolls();
                trollNum++;
                secondArea.setTrolls(trollNum);
                secondArea.setTroubleMarkers(Boolean.TRUE);
                trollNum = thirdArea.getTrolls();
                trollNum++;
                thirdArea.setTrolls(trollNum);
                thirdArea.setTroubleMarkers(Boolean.TRUE);
                return Boolean.TRUE;
            }
            case Earthquake: {
                int firstAreaNumber = utility.rollDie();
                int secondAreaNumber = utility.rollDie();
                Area firstArea = utility.getAreaByNumber(firstAreaNumber);
                Area secondArea = utility.getAreaByNumber(secondAreaNumber);
                if (firstArea.getPlayersInThisAreas() != null)
                    firstArea.getPlayersInThisAreas().remove(currentPlayer);
                BoardGame.setCityAreaCardRepo(currentPlayer.getCityReaCardFromAreaName(firstArea.getAreaName()));
                currentPlayer.getCityAreaCardsStore().remove(currentPlayer.getCityReaCardFromAreaName(firstArea.getAreaName()));
                if (secondArea.getPlayersInThisAreas() != null)
                    secondArea.getPlayersInThisAreas().remove(currentPlayer);
                BoardGame.setCityAreaCardRepo(currentPlayer.getCityReaCardFromAreaName(secondArea.getAreaName()));
                currentPlayer.getCityAreaCardsStore().remove(currentPlayer.getCityReaCardFromAreaName(secondArea.getAreaName()));
                return Boolean.TRUE;
            }

            default:
                return Boolean.FALSE;
        }

    }

    private void doMurder(Player currentPlayer) {
        Utility utility = new Utility();
        int areaNumber = utility.rollDie();
        Area area = utility.getAreaByNumber(areaNumber);
        int numberOfMinions = utility.getNumberOfMinions(area.getAreaName());
        currentPlayer.setMinionQuantity(--numberOfMinions);
        area.getMinionColor().remove(new String(currentPlayer.getPlayerColor()));
        for (int i = 0; i < BoardGame.playersInGame.size() - 1; i++) {
            String nextPlayer = utility.giveTurnToleft();
            for (Player player : BoardGame.playersInGame) {
                if (player.getPlayerColor().equalsIgnoreCase(nextPlayer))
                    doMurder(player);
            }
        }
    }

    private void performFireAction(Area initialArea) throws JSONException {

        Utility utility = new Utility();
        if (initialArea.isBuildngs()) {
            initialArea.removeBuilding(initialArea.getAreaName());
            Area secondAreaObj = utility.getAreaByNumber(utility.rollDie());
            for (Area a : BoardGame.getAdjacentAreasForAnArea(initialArea.getAreaName())) {
                if (secondAreaObj.getAreaName().equalsIgnoreCase(a.getAreaName())) {
                    performFireAction(secondAreaObj);
                }
            }
        } else {
            System.out.println("Fire spreading has stopped..");
        }
    }
}