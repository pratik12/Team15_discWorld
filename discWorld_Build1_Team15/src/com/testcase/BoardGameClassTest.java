package com.testcase;

import com.app.BoardGame;
import org.junit.BeforeClass;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/1/15
 * Time: 10:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class BoardGameClassTest {

    @BeforeClass
    public static void initializeResources() {
        BoardGame.startGame();
        BoardGame.initiateNumberOfPlayers(Integer.parseInt("4"));
    }
}
