package com.app.PlayingCardSystem;

import com.app.Area;
import com.app.BoardGame;
import com.app.Player;
import com.app.common.ComponentUtilities;
import com.app.rules.RandomEventCard;
import com.app.rules.WinningCircumstancesFactory;
import org.json.JSONException;
import userInputUtility.UserInputUtility;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Class PlayerCard.
 *
 * @author Pratik
 *         Class of player cards that will be given out to players when the game starts
 */
public enum GreenPlayerCardEnum implements PlayingCardRulesInterface {

    GLOBALOBJ("self", "green", "deckpile", new String[]{}) {},

    BOGGIS("BOGGIS", "green", "DeckPile", new String[]{"Read Scroll", "Place minion"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    PlayingCardRuleEngine.TEST.takeMoney(2, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    // call appropriate function
                    String minionLocation = questionsToAsk("Enter area name where you want to place a minion? : nul");
                    // should display the current state of board
                    // print out the board state on a frmae gui
                    for (Area a : BoardGame.board_areas) {
                        if (a.getAreaName().equalsIgnoreCase(minionLocation)) {
                            currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
                            break;
                        }

                    }
                }
                break;
            }
            System.out.println("You hae finished playing this card. Place it on discard deck");
            addToDiscardPile(1, this, currentPlayingPlayer);
        }


        // place this card on the discard pile
    },//take 2$ from all players

    BEGGARSGUILD("BEGGARS GUILD", "green", "DeckPile", new String[]{"Read scroll", "Place minion"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    takePlayingCards(currentPlayingPlayer, 2);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {

                    String minionLocation = questionsToAsk("Enter area name where you want to place a minion? : nul");
                    // should display the current state of board
                    // print out the board state on a frmae gui
                    for (Area a : BoardGame.board_areas) {
                        if (a.getAreaName().equalsIgnoreCase(minionLocation)) {
                            currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
                            break;
                        }

                    }
                    break;
                }
                break;
            }
            System.out.println("You have finished playing this card");
            addToDiscardPile(1, this, currentPlayingPlayer);
        }


    },// sleect 1 player. take 2 cards

    AMBANK("The Bank of AnkhMorpork", "green", "DeckPile", new String[]{"Read scroll", "Play another card"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    this.takeLoanFromBank(10, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    try {
                        playAnotherCard(currentPlayingPlayer, this);
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    break;
                }
            }
            System.out.println("You hae finished playing this card. Place it on discard deck");
            addToDiscardPile(1, this, currentPlayingPlayer);

        }
    }, //loan of 10$ from bank. at end payback 12$ or loose 15 points

    AMSUNSHINEDRAGONSANCTUARY("AM SUNSHINE DRAGON SANCTUARY", "green", "DeckPile", new String[]{"Read scroll", "Play Another Card"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    for (Player p : BoardGame.playersInGame) {
                        if (!(p.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor()))) {

                            Player fromPlayer = null;
                            String res1 = questionsToAsk("Enter Your piece color to Give him a playing card :" +
                                    "Enter 'curr' to Pay him 1$");
                            if (res1.equalsIgnoreCase("r") || res1.equalsIgnoreCase("g")
                                    || res1.equalsIgnoreCase("b") || res1.equalsIgnoreCase("y")) {
                                for (Player pc : BoardGame.playersInGame) {
                                    if (pc.getPlayerColor().equalsIgnoreCase(res1))
                                        fromPlayer = pc;
                                    break;
                                }
                                takePlayingCards(currentPlayingPlayer, 1);
                            } else if (res1.equalsIgnoreCase("curr")) {
                                takeMoneyFromPlayer(1, currentPlayingPlayer, fromPlayer);
                            }
                        }
                    }
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    // call the function of playing another card
                    playAnotherCard(currentPlayingPlayer, this);
                    break;
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    }, // take 1$ from every player or one card

    ANGUA("ANGUA", "green", "DeckPile", new String[]{"Remove Trouble Marker", "Play Another Card"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    // show to user where trouble markers are
                    String areaName = questionsToAsk("Enter area name to remove trouble marker from:nul");
                    removeTroubleMarker(areaName);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    playAnotherCard(currentPlayingPlayer, this);
                    break;
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },//

    AGONYAUNTS("AGONYAUNTS", "green", "DeckPile", new String[]{"Assasinate", "Take 2$", "Place Minion"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    assasinate(currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {

                    takeMoneyFromBank(2, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[2])) {

                    String minionLocation = questionsToAsk("Enter area name where you want to place a minion? :nul");
                    // should display the current state of board
                    // print out the board state on a frmae gui
                    for (Area a : BoardGame.board_areas) {
                        if (a.getAreaName().equalsIgnoreCase(minionLocation)) {
                            currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
                            break;
                        }
                    }

                }
                break;
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },

    DYSK("DYSK", "green", "DeckPile", new String[]{"Add building", "Read Scroll"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    String area_name = questionsToAsk("Enter area name to place building:nul");
                    currentPlayingPlayer.addBuilding(area_name);
                    res = askSymbolsInOrder(this, "0");
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {

                    String minionLocation = questionsToAsk("Enter area name where you want to place a minion? :nul");
                    // should display the current state of board
                    // print out the board state on a frmae gui
                    for (Area a : BoardGame.board_areas) {
                        if (a.getAreaName().equalsIgnoreCase(minionLocation)) {
                            currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
                            break;
                        }
                    }
                }
                break;
            }

            addToDiscardPile(1, this, currentPlayingPlayer);

        }
    },

    DUCKMAN("DUCKMAN", "green", "DeckPile", new String[]{"Read scroll"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    String result = questionsToAsk("Enter a players piece color to give playing card to : nul");
                    Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
                    ComponentUtilities mu = new ComponentUtilities();
                    mu.displayMinionsOfotherPlayer(selectedPlayer);
                    String toLocation = questionsToAsk("Enter area name:nul");
                    moveMinionToOtherArea(currentPlayingPlayer, selectedPlayer, toLocation);
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },

    DRUMKNOTT("DRUMKNOTT", "green", "DeckPile", new String[]{"Read scroll"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    try {
                        playAnotherCard(currentPlayingPlayer, this);
                        playAnotherCard(currentPlayingPlayer, this);
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    CMOTDIBBLER("CMOTDIBBLER", "green", "DeckPile", new String[]{"Read scroll", "Play another card"}) {
        // roll die. if 7 take 4 from bank. if 1 pay 2$ to bank or remove 1 YOUR minion.
        @Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    int num = rollDie();
                    if (num >= 7) {
                        takeMoneyFromBank(4, currentPlayingPlayer);
                    } else if (num == 1) {
                        String result = questionsToAsk("Hit 'p' to pay money to bank : Hit 'rem' to remove your minion");
                        if (result.trim().equalsIgnoreCase("p")) {
                            payMoneyToBank(2, currentPlayingPlayer);
                        } else if (result.trim().equalsIgnoreCase("rem")) {
                            removeMinionOFYourOwn(1, currentPlayingPlayer);
                        }
                    }
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    playAnotherCard(currentPlayingPlayer, this);
                    break;
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    DRCRUCES("DR.CRUCES", "green", "DeckPile", new String[]{"Assasinate", "take 3$"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    assasinate(currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    takeLoanFromBank(3, currentPlayingPlayer);
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    CAPTAINCARROT("CAPTAIN CARROT", "green", "DeckPile", new String[]{"Place minion", "Remove Trouble Marker", "take 1$"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    String minionLocation = questionsToAsk("Enter area name where you want to place a minion? :nul");
                    currentPlayingPlayer.placeMinion(minionLocation);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {

                    String result = questionsToAsk("Enter area anme to remove marker form : nul");
                    removeTroubleMarker(result);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[2])) {
                    takeMoneyFromBank(1, currentPlayingPlayer);
                }

            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    MRSCAKE("MRS.CAKE", "green", "DeckPile", new String[]{"Read scroll", "take 2$", "add building"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
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
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    takeMoneyFromBank(2, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[2])) {
                    String area_name = questionsToAsk("Enter area name to add building into:nul");
                    currentPlayingPlayer.addBuilding(area_name);
                    break;
                }
            }

        }
    },
    MRBENT("MR.BENT", "green", "DeckPile", new String[]{"Read Scroll", "Play another card"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    this.takeLoanFromBank(10, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    try {
                        playAnotherCard(currentPlayingPlayer, this);
                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    break;
                }
            }


        }
    },
    GROAT("GROAT", "green", "DeckPile", new String[]{"Place Minion"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {
            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    String minionLocation = questionsToAsk("Enter area name where you want to place a minion? :nul");
                    currentPlayingPlayer.placeMinion(minionLocation);
                    break;
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    GASPODE("GASPODE", "green", "DeckPile", new String[]{"Interrupt"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    break;
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    FRESHSTARTCLUB("FRESH START CLUB", "green", "DeckPile", new String[]{"Interrupt"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    break;
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }

    },
    FOULOLERON("GROAT", "green", "DeckPile", new String[]{"Read Scroll", "Play another card"}) {
        // moveminion of OTHER player from an area to adjacent area
        @Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    String result = questionsToAsk("Enter the color of player you want to select (R/G/Y/B) in this format : nul");
                    Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
                    removeMinionOFAnotherPlayer(2, currentPlayingPlayer, selectedPlayer);
                    System.out.println("Areas where your selected player has minions");
                    for (ArrayList<String> t : selectedPlayer.getMinions().values())
                        for (String str : t)
                            System.out.printf("%-10s", str);

                    String areaToMoveMinionFrom = questionsToAsk("Enter area name to move minion from : nul");
                    moveMinionToOtherArea(currentPlayingPlayer, selectedPlayer, areaToMoveMinionFrom);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    playAnotherCard(currentPlayingPlayer, this);
                    break;
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    THEFOOLSGUILD("The Fools Guild", "green", "DeckPile", new String[]{"Read Scroll", "Place Minion"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    String result = questionsToAsk("Enter the color of player you want to select (R/G/Y/B) in this format : nul");
                    Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
                    String answer = questionsToAsk("Hit '1' to pay 5$ or hit '2' to receive The Fools' Guild");
                    if (answer.trim().equalsIgnoreCase("1")) {
                        takeMoneyFromPlayer(5, currentPlayingPlayer, selectedPlayer);
                    } else if (answer.trim().equalsIgnoreCase("2")) {
                        selectedPlayer.getPlayersPlayingCard().add(this);
                        currentPlayingPlayer.getPlayersPlayingCard().remove(this);
                    }

                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    // call appropriate function
                    String minionLocation = questionsToAsk("Enter area name where you want to place a minion? : nul");
                    // should display the current state of board
                    // print out the board state on a frmae gui
                    for (Area a : BoardGame.board_areas) {
                        if (a.getAreaName().equalsIgnoreCase(minionLocation)) {
                            currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
                            break;
                        }

                    }
                }
                break;
            }
            System.out.println("You hae finished playing this card. Place it on discard deck");
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    THEFIREBRIGADE("GROAT", "green", "DeckPile", new String[]{"Read Scroll", "Play another card"}) {
        // select plaeyr if they dont give you 5$ remove 1 of his building from board
        @Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    String result = questionsToAsk("Enter the color of player you want to select (R/G/Y/B) in this format : nul");
                    Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);
                    String result1 = questionsToAsk("Enter the maount you wish to take from player : nul");
                    if (!(takeMoneyFromPlayer(Integer.parseInt(result1), currentPlayingPlayer, selectedPlayer))) {
                        // you want to remove his building
                        removeBuilding(currentPlayingPlayer, selectedPlayer);
                    }
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    playAnotherCard(currentPlayingPlayer, this);
                    break;
                }
            }
        }
    },
    INIGOSKIMMER("GROAT", "green", "DeckPile", new String[]{"Assasinate", "Take 2$"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    assasinate(currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    takeMoneyFromBank(2, currentPlayingPlayer);

                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    HISTORYMONKS("GROAT", "green", "DeckPile", new String[]{"Read Scroll", "Place minion"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    drawCardsFromDiscardPile(4, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {

                    placeMinionActionPlayerCard(currentPlayingPlayer);

                }

            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    HEX("GROAT", "green", "DeckPile", new String[]{"Read Scroll", "Add building"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    drawCardsFromDeck(3, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    String area_name = questionsToAsk("Enter area name where you want to place building:nul");
                    currentPlayingPlayer.addBuilding(area_name);
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    HERENOW("GROAT", "green", "DeckPile", new String[]{"Read Scroll", "Play Another card"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    int num = rollDie();
                    if (num >= 7) {
                        String playerToSelect = questionsToAsk("Enter player piece color to select:nul");
                        Player player = selectPlayer(currentPlayingPlayer, playerToSelect);
                        takeMoneyFromPlayer(3, currentPlayingPlayer, player);
                    } else if (num == 1) {
                        removeMinionOFYourOwn(1, currentPlayingPlayer);
                    } else {
                        System.out.println("This" + num + "from roll die has no effect");
                    }
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    playAnotherCard(currentPlayingPlayer, this);
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },

    HARRYKING("GROAT", "green", "DeckPile", new String[]{"Place minion", "Read Scroll"}) {
        // discrad as many cards as uw ish, get 2 for each
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    placeMinionActionPlayerCard(currentPlayingPlayer);
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {

                    discardCardsPerYourWish(currentPlayingPlayer, this, 2);
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    GRYLE("MR GRYLE", "green", "DeckPile", new String[]{"Assasinate", "Take 1$"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    assasinate(currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {

                    takeMoneyFromBank(1, currentPlayingPlayer);
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    HARGAHOUSEOFRIBS("HARGAHOUSEOFRIBS", "green", "DeckPile", new String[]{"Take 3$", "Place Minion"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    takeMoneyFromBank(3, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    placeMinionActionPlayerCard(currentPlayingPlayer);
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    THEPEELEDNUTS("THE PEELED NUTS", "green", "DeckPile") {


    },
    THEOPERAHOUSE("THE OPERA HOUSE", "green", "DeckPile", new String[]{"Add building", "Read Scroll"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    String area_name = questionsToAsk("Enter area name to add building to:nul");
                    currentPlayingPlayer.addBuilding(area_name);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    getMoneyForMinionsinArea(1, currentPlayingPlayer, "Isle of Gods");
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }

    },
    NOBBYNOBBSS("NOBBYNOBBSS", "green", "DeckPile", new String[]{"Read Scroll", "Play another card"}) {
        @Override
        public void performTasks(Player currentPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    String result = questionsToAsk("Enter a players piece color you want to take money from : nul");
                    Player selectedPlayer = selectPlayer(currentPlayer, result);
                    takeMoneyFromPlayer(3, currentPlayer, selectedPlayer);
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    playAnotherCard(currentPlayer, this);
                }
            }
            addToDiscardPile(1, this, currentPlayer);
        }
    },
    MODO("MODO", "green", "DeckPile", new String[]{"Read Scroll", "Place Minion"}) {
        @Override
        public void performTasks(Player currentPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    discardCard(currentPlayer);
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    placeMinionActionPlayerCard(currentPlayer);
                }
            }
            addToDiscardPile(1, this, currentPlayer);
        }


    },
    THEMENDEDDRUM("THE MENDEDDRUM", "green", "DeckPile", new String[]{"Add building", "Take 2$"}) {
        @Override
        public void performTasks(Player currentPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {

                    String area_name = questionsToAsk("Enter area name to add building to:nul");
                    currentPlayer.addBuilding(area_name);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    takeMoneyFromBank(2, currentPlayer);
                }
            }
            addToDiscardPile(1, this, currentPlayer);
        }

    },
    LIBRARIAN("LIBRARIAN", "green", "DeckPile", new String[]{"Read Scroll"}) {
        @Override
        public void performTasks(Player currentPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase("Read Scroll")) {
                    drawCardsFromDeck(4, currentPlayer);
                }
            }
            addToDiscardPile(1, this, currentPlayer);
        }
    },
    LEONARDOFQUIRM("LEONARD OF QUIRM", "green", "DeckPile", new String[]{"Read Scroll"}) {
        // draw 4 cards from the deck
        @Override
        public void performTasks(Player currentPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase("Read Scroll")) {
                    drawCardsFromDeck(4, currentPlayer);
                }
            }
            addToDiscardPile(1, this, currentPlayer);
        }
    },
    SHONKYSHOP("SHONKY SHOP", "green", "DeckPile", new String[]{"Read Scroll", "Add building"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase("Read Scroll")) {
                    discardCardsPerYourWish(currentPlayingPlayer, this, 1);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase("Add building")) {

                    String area_name = questionsToAsk("Enter area name to add building to:nul");
                    currentPlayingPlayer.addBuilding(area_name);
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    SACHARISSACRIPSLOCK("SACHARISSA CRIPSLOCK", "green", "DeckPile", new String[]{"Read Scroll", "Place Minion"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase("Read Scroll")) {
                    for (Area a : BoardGame.board_areas)
                        if (a.isTroubleMarkers())
                            takeMoneyFromBank(1, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase("Place Minion"))
                    placeMinionActionPlayerCard(currentPlayingPlayer);

            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    ROSIEPALM("ROSIE PALM", "green", "DeckPile", new String[]{"Place Minion", "Read Scroll"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase("Place Minion")) {
                    placeMinionActionPlayerCard(currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase("Read Scroll")) {

                    takeMoneyExchangeCardsFromAnotherPlayer(currentPlayingPlayer, 2);
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    RINCEWIND("RINCEWIND", "green", "DeckPile", new String[]{"Play Random Event card", "Read Scroll", "Play another Card"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {
            String res = askSymbolsInOrder(this, "0");

            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                System.out.println("Random Event Card is being played...");

                RandomEventCard.GLOBALOBJ.doTheTasks(currentPlayingPlayer, RandomEventCard.GLOBALOBJ.getShuffledRandomEventCard(), this);

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    //
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    ArrayList<Area> temp = new ArrayList<Area>();
                    for (Area a : BoardGame.board_areas) {
                        if (a.isTroubleMarkers())
                            temp.add(a);
                    }
                    System.out.println("You can move minions from this area: ");
                    ArrayList<String> str = new ArrayList<String>();
                    for (Player p : BoardGame.playersInGame) {
                        if (p.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor())) {

                            for (Area a : temp)
                                if (p.getMinions().values().contains(a.getAreaName())) {
                                    str.add(a.getAreaName());
                                    System.out.println(a.getAreaName());
                                }
                            break;
                        }
                        break;
                    }
                    for (String s : str) {
                        String result = BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.areaDetails, s);
                        BoardGame.displayAdjacentAreas(result);

                    }
                    String output = questionsToAsk("Which adjacent area you want to move minion to:nul");
                    currentPlayingPlayer.getMinions().values().remove(output);
                    currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), output);

                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[2])) {
                    playAnotherCard(currentPlayingPlayer, this);
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }

    },
    THEROYALMINT("THE ROYAL MINT", "green", "DeckPile", new String[]{"Add building", "Take 5$"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");
            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    String area_name = questionsToAsk("Enter area name to place building:nul");
                    currentPlayingPlayer.addBuilding(area_name);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1]))
                    takeMoneyFromBank(5, currentPlayingPlayer);
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }

    },
    QUEENMOLLY("QUEEN MOLLY", "green", "DeckPile", new String[]{"Place minion", "Read Scroll"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");
            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    placeMinionActionPlayerCard(currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {

                    String result = questionsToAsk("Enter a players piece color you want to sleect : nul");
                    Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);

                    System.out.println("Your cards as of now Player " + selectedPlayer.getPlayerColor());
                    for (GreenPlayerCardEnum gc : selectedPlayer.getPlayersPlayingCard())
                        System.out.print(gc.name() + ", ");
                    int num = 2;
                    while (num != 0) {
                        String ress = questionsToAsk("Enter card you want to give : nul");
                        for (GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard()) {
                            if (ress.equalsIgnoreCase(gc.name())) {
                                selectedPlayer.getPlayersPlayingCard().remove(gc);
                                currentPlayingPlayer.getPlayersPlayingCard().add(gc);
                            }
                        }
                    }

                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    PINKPUSSYCATCLUB("PINK PUSSY CAT CLUB", "green", "DeckPile", new String[]{"Take 3$", "Play another card"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) throws JSONException {

            String res = askSymbolsInOrder(this, "0");
            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {


                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    takeMoneyFromBank(5, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    playAnotherCard(currentPlayingPlayer, this);
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    ZORGOTHERETROPHRENOLOGIST("ZORGO THE RETRO-PHRENOLOGIST", "green", "DeckPile", new String[]{"Read Scroll", "Add building"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {


            String res = askSymbolsInOrder(this, "0");
            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    currentPlayingPlayer.exchangePersonalityCard();
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    String area_name = questionsToAsk("Enter area name to place building:nul");
                    currentPlayingPlayer.addBuilding(area_name);
                    break;
                }
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }


    },
    DRWHITEFACE("DRWHITEFACE", "green", "DeckPile") {
        @Override
        public void performTasks(Player currentPlayingPlayer) {
        }
    },
    WALLACESONKY("WALLACESONKY", "green", "DeckPile") {
        @Override
        public void performTasks(Player currentPlayingPlayer) {


        }
    },
    THESEAMSTRESSESGUILD("THESEAMSTRESSESGUILD", "green", "DeckPile", new String[]{"Read Scroll", "Place minion"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");
            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    takeMoneyExchangeCardsFromAnotherPlayer(currentPlayingPlayer, 2);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1]))
                    placeMinionActionPlayerCard(currentPlayingPlayer);
                break;
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    },
    MRPINANDMRTULIP("MR.PIN AND MR.TULIP", "green", "DeckPile", new String[]{"Assasinate", "Take 1$"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {
            String res = askSymbolsInOrder(this, "0");
            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    assasinate(currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1]))
                    takeMoneyFromBank(1, currentPlayingPlayer);
                break;
            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }

    },
    THETHIEVESGUILD("THETHIEVESGUILD", "green", "DeckPile", new String[]{"Read Scroll", "Place Minion"}) {
        // take 2$ from every other player
        @Override
        public void performTasks(Player currentPlayingPlayer) {

            String res = askSymbolsInOrder(this, "0");
            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    for (Player p : BoardGame.playersInGame) {
                        if (!(p.getPlayerColor().equalsIgnoreCase(currentPlayingPlayer.getPlayerColor())))
                            takeMoneyFromPlayer(2, currentPlayingPlayer, p);
                    }
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1]))
                    placeMinionActionPlayerCard(currentPlayingPlayer);
                break;
            }
            addToDiscardPile(1, this, currentPlayingPlayer);


        }

    }, GIMLETDWARFDELICATESSEN("GIMLETDWARFDELICATESSEN", "green", "DeckPile", new String[]{"Take 3$", "Place minion"}) {
        @Override
        public void performTasks(Player currentPlayingPlayer) {
            String res = askSymbolsInOrder(this, "0");
            while (!(res.split(":")[0].trim().equalsIgnoreCase("exit")) ||
                    (!res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[this.getSymbols().length - 1]))) {

                if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[0])) {
                    takeMoneyFromBank(3, currentPlayingPlayer);
                    res = askSymbolsInOrder(this, res.split(":")[1].trim());
                } else if (res.split(":")[0].trim().equalsIgnoreCase(this.getSymbols()[1])) {
                    placeMinionActionPlayerCard(currentPlayingPlayer);
                    break;
                }

            }
            addToDiscardPile(1, this, currentPlayingPlayer);
        }
    };

    /**
     * The location.
     */
    private String location;

    /**
     * The color.
     */
    private String color;

    /**
     * The name.
     */
    private String name;

    private String[] symbols;

    private GreenPlayerCardEnum() {
    }

    /** The id of playing card **/
    /**
     * Instantiates a new player card.
     *
     * @param color    the color
     * @param name     the name
     * @param location the location
     */
    private GreenPlayerCardEnum(String name, String color, String location, String... symbols) {

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
     *                 would be set to either players hand, discard pile or draw pile
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
    public void takeMoneyFromBank(Player currPlayer, int amount) {
        takeMoneyFromBank(amount, currPlayer);
    }

    public void moveMinion() {
    }

    public void assasinate(String pieceToRemove) {
    }

    @Override
    public void playAnotherCard(Player currentPlayingPlayer, GreenPlayerCardEnum enumType) throws JSONException {

        for (GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard())
            if (!(gc.name.equalsIgnoreCase(enumType.name)))
                System.out.println(gc.name + ", ");
        String res = questionsToAsk("Choose which card to play:nul");
        for (GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard())
            if ((gc.name.equalsIgnoreCase(enumType.name))) {
                gc.performTasks(currentPlayingPlayer);
                break;
            }

    }

    @Override
    public void interrupt() {
    }

    @Override
    public void performTasks(Player currentPlayingPlayer) throws JSONException {
    }

    @Override
    public String askSymbolsInOrder(GreenPlayerCardEnum tempEnum, String result) {
        String res = "enter";
        res = UserInputUtility.USERINPUTUTILITYENUM.getUserInput(tempEnum, result);
        return res;
    }

    @Override
    public boolean takeMoneyFromPlayer(int amt, Player currentPlayingPlayer, Player fromPlayer) {

        String res = questionsToAsk("Would you Give me $'s .Hit Y for Yes or elsewise : nul");
        if (res.equalsIgnoreCase("y")) {
            currentPlayingPlayer.setPlayerAmount(currentPlayingPlayer.getPlayerAmount() + amt);
            fromPlayer.setPlayerAmount(fromPlayer.getPlayerAmount() - amt);
        } else {
            return false;
        }


        return true;
    }

    @Override
    public void givePlayingCards(Player currrentPlayer, int number) {

        String result = questionsToAsk("Enter a players piece color to give playing card to : nul");
        Player selectedPlayer = selectPlayer(currrentPlayer, result);

        while (number != 0) {

            System.out.println("Your cards as of now player " + currrentPlayer.getPlayerColor());
            for (GreenPlayerCardEnum gc : currrentPlayer.getPlayersPlayingCard())
                System.out.print(gc.name() + ", ");

            playingCardsAction(selectedPlayer, currrentPlayer, number);
            number--;
        }

    }

    @Override
    public void takePlayingCards(Player currrentPlayer, int number) {

        String result = questionsToAsk("Enter a players piece color to take playing card from : nul");
        Player selectedPlayer = selectPlayer(currrentPlayer, result);

        while (number != 0) {
            System.out.println("Your cards as of now player " + selectedPlayer.getPlayerColor());
            for (GreenPlayerCardEnum gc : currrentPlayer.getPlayersPlayingCard())
                System.out.print(gc.name() + ", ");

            playingCardsAction(currrentPlayer, selectedPlayer, number);
            number--;
        }

    }

    @Override
    public Player selectPlayer(Player currentPlayer, String playerToSelect) {

        Player temp = null;
        if ((playerToSelect.equalsIgnoreCase("r") || playerToSelect.equalsIgnoreCase("g") ||
                playerToSelect.equalsIgnoreCase("b") || playerToSelect.equalsIgnoreCase("y"))) {
            for (Player pc : BoardGame.playersInGame) {
                if (pc.getPlayerColor().equalsIgnoreCase(playerToSelect))
                    temp = pc;
            }
        } else {
            System.out.println("Select color of existing player");
        }

        return temp;
    }


    @Override
    public String questionsToAsk(String qns) {
        String result = null;
        Scanner in = new Scanner(System.in);
        String[] temp = qns.split(":");

        for (int i = 0; i < temp.length; i++) {
            if (!temp[i].trim().equalsIgnoreCase("nul"))
                System.out.println(temp[i] + ", ");
        }
        result = in.nextLine();
        return result;
    }

    @Override
    public void takeLoanFromBank(int amt, Player currentPlayer) {

        if (amt != 0) {
            currentPlayer.setPlayerAmount(currentPlayer.getPlayerAmount() + amt);
            BoardGame.setBank(BoardGame.getBank() - amt);
            currentPlayer.setPlayerLoan(currentPlayer.getPlayerLoan() + amt);
        } else {
            System.out.println("Entered amount cannot be 0");
        }
    }

    @Override
    public int rollDie() {

        Random num = new Random();
        int i = num.nextInt(12);

        if (i != 0)
            return i;
        else
            rollDie();
        return 0;
    }

    @Override
    public void takeMoneyFromBank(int amt, Player currentPlayer) {

        takeLoanFromBank(amt, currentPlayer);
    }

    @Override
    public void payMoneyToBank(int amt, Player currentPlayer) {

        if (amt != 0 && !(currentPlayer.equals(null))) {
            currentPlayer.setPlayerAmount(currentPlayer.getPlayerAmount() - amt);
            BoardGame.setBank(BoardGame.getBank() + amt);
        } else {
            System.out.println("Amt cannot be zero or ");
        }

    }

    @Override
    public void removeMinion(int num, Player currentPlayer) {

        while (num != 0) {
            String minionlocation = questionsToAsk("Enter area name to remove your minion from : nul");
            for (ArrayList<String> str : currentPlayer.getMinions().values()) {
                for (String temp : str) {
                    if (minionlocation.equalsIgnoreCase(temp)) {
                        currentPlayer.getMinions().values().remove(temp);
                        currentPlayer.setMinionQuantity(currentPlayer.getMinionQuantity() - 1);
                        break;
                    }

                }
                break;
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

        if (!(fromPlayer.equals(null))) {
            String result = BoardGame.getInstance().getAdjacentAreaIDs(BoardGame.getInstance().areaDetails, toLocation);
            BoardGame.displayAdjacentAreas(result);
            String res = questionsToAsk("Enter area name to whose adjacent area you need to move minion to : nul");
            // record the result and place the opposite players minion there
            if (!res.equals(null)) {
                fromPlayer.setMinions(fromPlayer.getPlayerColor(), res);
            }
        } else {
            System.out.println("From Player is empty");
        }
    }

    @Override
    public void removeBuilding(Player currentPlayer, Player fromPlayer) {

        System.out.println("Opposite Players Areas where he has Building ");
        if (!(fromPlayer.getPlayerAreas().isEmpty())) {
            for (Area str : fromPlayer.getPlayerAreas()) {
                System.out.print(str.getAreaName() + ", ");
            }
        }
        String res = questionsToAsk("Enter the area name you want to remove building from : nul");
        for (Area area : fromPlayer.getPlayerAreas()) {
            if (res.trim().equalsIgnoreCase(area.getAreaName())) {
                fromPlayer.getPlayerAreas().remove(area);
                break;
            }
            break;
        }

    }

    @Override
    public void drawCardsFromDeck(int num, Player currentPlayer) {

        while (num != 0) {
            Random rand = new Random();
            int n = rand.nextInt(BoardGame.player_cards.size() - 1);
            if (BoardGame.player_cards.get(n) instanceof GreenPlayerCardEnum)
                currentPlayer.getPlayersPlayingCard().add(BoardGame.player_cards.get(n));
            else
                System.out.println("Not sufficient player cards are remaining in the deck");
        }

    }

    /**
     *
     */
    @Override
    public void removeMinionOFAnotherPlayer(int num, Player currentPlayer,
                                            Player fromPlayer) {

        removeMinion(num, fromPlayer);

    }

    @Override
    public void removeMinionOFYourOwn(int num, Player currentPlayer) {

        removeMinion(num, currentPlayer);
    }

    /**
     * Adds the specified players playing cards to the discard pile list on the board
     */
    @Override
    public void addToDiscardPile(int num, GreenPlayerCardEnum gc, Player ps) {
        while (num != 0) {
            if (gc instanceof GreenPlayerCardEnum) {
                System.out.println("Card being added to discard Pile");
                ps.getPlayersPlayingCard().remove(gc);
                BoardGame.setDiscardPilePlayerCards(gc);
                num--;
            } else
                System.out.println("Unsupported playercard. Verify the type of cardbeing passed");
        }
    }

    /**
     * This function takes in amount , current player who's turn is going on
     * and the area from where the minions has to be counted.
     * Adds the specified amount to the player based on the number of minions in his specified area
     */
    @Override
    public void getMoneyForMinionsinArea(int amt, Player currentplayer, String areaName) {

        if (amt != 0 && !(areaName.isEmpty())) {

            for (Player p : BoardGame.playersInGame) {
                for (ArrayList<String> str : p.getMinions().values())
                    for (String s : str) {
                        if (areaName.equalsIgnoreCase(s)) {
                            currentplayer.setPlayerAmount(currentplayer.getPlayerAmount() + amt);

                        }
                    }
            }


        }

    }

    @Override
    public void playingCardsAction(Player currentPlayer, Player fromPlayer,
                                   int count) {

        String res = questionsToAsk("Enter card name : nul");
        for (GreenPlayerCardEnum gc : currentPlayer.getPlayersPlayingCard()) {
            if (res.equalsIgnoreCase(gc.name())) {
                currentPlayer.getPlayersPlayingCard().add(gc);
                fromPlayer.getPlayersPlayingCard().remove(gc);
                break;
            }

        }
    }

    @Override
    public void removeTroubleMarker(String areaName) {

        for (Area a : BoardGame.board_areas) {
            if (areaName.equalsIgnoreCase(a.getAreaName())) {
                if (a.isTroubleMarkers()) {
                    a.setTroubleMarkers(false);
                }
            }
            break;
        }

    }


    @Override
    public void assasinate(Player ps) {

        for (Area a : BoardGame.board_areas) {
            if (a.isTroubleMarkers()) {
                for (Player p : a.getPlayersInThisAreas()) {
                    if (!(p.getPlayerColor().equalsIgnoreCase(ps.getPlayerColor()))) {
                        System.out.println("Minions for Playr " + p.getPlayerColor());
                        for (ArrayList<String> arr : p.getMinions().values()) {
                            for (String str : arr) {
                                System.out.print(" "
                                        + str);
                            }
                        }
                    }
                }
                String playerToSelect = questionsToAsk("Enter players Piece Color:nul");
                Player player = selectPlayer(ps, playerToSelect);
                removeMinionOFAnotherPlayer(1, ps, player);

            }
        }

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

    @Override
    public void drawCardsFromDiscardPile(int num, Player player) {

        while (num != 0) {
            Random ran = new Random();
            int i = ran.nextInt(BoardGame.getDiscardPilePlayerCards().size() - 1);
            System.out.println("Adding cards from Discard Pile");
            player.getPlayersPlayingCard().add(BoardGame.getDiscardPilePlayerCards().get(i));
        }

    }

    @Override
    public void placeMinionActionPlayerCard(Player currentPlayingPlayer) {

        String minionLocation = questionsToAsk("Enter area name where you want to place a minion? :nul");
        // should display the current state of board
        // print out the board state on a frmae gui
        for (Area a : BoardGame.board_areas) {
            if (a.getAreaName().equalsIgnoreCase(minionLocation)) {
                currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
                break;
            }
        }
    }

    @Override
    public void placeMinionActionPlayerCard(Player currentPlayingPlayer, String disallowedAreaName) {

        String minionLocation = questionsToAsk("Enter area name where you want to place a minion? :nul");
        // should display the current state of board
        // print out the board state on a frmae gui
        for (Area a : BoardGame.board_areas) {
            if (a.getAreaName().equalsIgnoreCase(minionLocation) && !a.getAreaName().equalsIgnoreCase(disallowedAreaName)) {
                currentPlayingPlayer.setMinions(currentPlayingPlayer.getPlayerColor(), minionLocation);
                break;
            }
        }
    }

    @Override
    public void discardCardsPerYourWish(Player currentPlayingPlayer, GreenPlayerCardEnum tempname, int amt) {

        while (currentPlayingPlayer.getPlayersPlayingCard().size() != 0) {
            System.out.println("Your cards as of now: ");
            for (GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard())
                System.out.print(gc.name() + ", ");

            String result = questionsToAsk("Enter the card name : Hit '0' to exit");
            if (Integer.parseInt(result) != 0) {
                for (GreenPlayerCardEnum temp : currentPlayingPlayer.getPlayersPlayingCard()) {
                    if (currentPlayingPlayer.getPlayersPlayingCard().contains(result)) {
                        addToDiscardPile(1, temp, currentPlayingPlayer);
                        takeMoneyFromBank(amt, currentPlayingPlayer);
                        System.out.println("Card Discarded");
                    }
                }
            } else if (Integer.parseInt(result) == 0)
                break;

        }
    }


    @Override
    public void takeMoneyExchangeCardsFromAnotherPlayer(Player currentPlayingPlayer, int amt) {
        String result = questionsToAsk("Enter a players piece color you want to give card to : nul");
        Player selectedPlayer = selectPlayer(currentPlayingPlayer, result);

        System.out.println("Your cards as of now: ");
        for (GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard())
            System.out.print(gc.name() + ", ");

        String res = questionsToAsk("Enter card you want to give : nul");
        for (GreenPlayerCardEnum gc : currentPlayingPlayer.getPlayersPlayingCard()) {
            if (res.equalsIgnoreCase(gc.name())) {
                selectedPlayer.getPlayersPlayingCard().add(gc);
                currentPlayingPlayer.getPlayersPlayingCard().remove(gc);
                takeMoneyFromPlayer(amt, currentPlayingPlayer, selectedPlayer);
                break;
            }

        }

    }

}
