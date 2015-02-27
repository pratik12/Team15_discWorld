package com.app.rules;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 2/26/15
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public enum RandomEventCard {

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

    private RandomEventCard(int number) {
    }

    public void doTheTasks() {
        System.out.println("***************");
        /*
        switch(this) {
            case Flood:
            {

            }
            case TheDragon:
            case MysteriousMurders:
            case Fog:
            case Riots:
            case DemonsFromTheDungeonDimensions:
            case Subsidence:
            case BloodyStupidJohnson:
            case Trolls:
            case Explosion:
            case Earthquake:
            case Fire:
            default:
                return true;
        }
        */
    }

    public static void main(String[] args) {
        for (RandomEventCard p : RandomEventCard.values()) {
            System.out.println("******"+p);
            p.doTheTasks();
        }
    }


}
