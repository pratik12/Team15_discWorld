package com.app.rules;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/24/15
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class WinningCircumstancesFactory {

    public enum PersonalityCards {
        LordDeWorde("Lord De Worde",1),
        LordVetinari("Lord Vetinari",2),
        LordSelachii("Lord Selachi",3),
        DragonKingOfArms("Dragon King Of Arms",4),
        Chrysoprase("Chrysoprase",5),
        CommanderVimes("Commander Vimes",6),
        LordRust("Lord Rust",7);
        
        private String name ;
        private int num;
        private PersonalityCards(String name, int num){
        	this.setName(name);
        	this.setNum(num);
        }
        /*private static final Map<Integer, String> lookup
                = new HashMap<Integer, String>();

        static {
            for (PersonalityCards s : EnumSet.allOf(PersonalityCards.class))
                lookup.put(s.getCode(), s.name());
        }

        private int code;

        private PersonalityCards(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static String get(int code) {
            return lookup.get(code);
        }*/
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the num
		 */
		public int getNum() {
			return num;
		}
		/**
		 * @param num the num to set
		 */
		public void setNum(int num) {
			this.num = num;
		}
    }

    public static WinningCircumstances getWinningCircumstance(String personalityCardType) {
        
    	for(PersonalityCards pc : PersonalityCards.values()){
    	if (personalityCardType.trim().equalsIgnoreCase(PersonalityCards.LordVetinari.getName()))
            return new LordVetinari();
        else if (personalityCardType.equals(PersonalityCards.LordSelachii.getName()) || 
        		personalityCardType.equals(PersonalityCards.LordRust.getName()) || 
        		personalityCardType.equals(PersonalityCards.LordDeWorde.getName()))
            return new LordSelRusWor();
        else if (personalityCardType.equals(PersonalityCards.DragonKingOfArms.getName()))
            return new DragonKingOfArms();
        else if (personalityCardType.equals(PersonalityCards.Chrysoprase.getName()))
            return new Chrysoprase();
        else if (personalityCardType.equals(PersonalityCards.CommanderVimes.getName()))
            return new CommanderVimes();
    	}
        return null;
    }
}
