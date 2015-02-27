package com.app.rules;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/24/15
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class WinningCircumstancesFactory {

    public static WinningCircumstances getWinningCircumstance(String personalityCardType) {
        if (personalityCardType.equals(""))
            return new LordVetinari();
        else if (personalityCardType.equals(""))
            return new LordSelRusWor();
        else if (personalityCardType.equals(""))
            return new DragonKingOfArms();
        else if (personalityCardType.equals(""))
            return new Chrysoprase();
        else if (personalityCardType.equals(""))
            return new CommanderVimes();
        return null;
    }
}
