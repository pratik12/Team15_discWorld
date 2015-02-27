package com.app.common;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/26/15
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utility {


    public int rollDie() {
        Random rand = new Random();
        return rand.nextInt(12) + 1;
    }



}
