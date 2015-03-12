package com.testcase;

import com.app.Area;
import com.app.PlayingCardSystem.GreenPlayerCardEnum;
import com.app.common.Utility;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 3/11/15
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class GreenPlayerClassTest {

    @Test
    public void removeTroubleMarker() {
        Utility utility = new Utility();
        int areaNumber = utility.rollDie();
        Area areaName = utility.getAreaByNumber(areaNumber);
        GreenPlayerCardEnum.GLOBALOBJ.removeTroubleMarker(areaName.getAreaName());
    }


}
