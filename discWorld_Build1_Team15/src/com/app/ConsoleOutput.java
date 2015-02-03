package com.app;

// TODO: Auto-generated Javadoc
/**
 * print the output to console
 * prints out the players inventory, players status, and board status.
 *
 * @author Pratik
 */
public class ConsoleOutput {

	/**
	 * Prints the player inventory.
	 *
	 * @param player the player
	 */
	public static void printOutInventory(Player player) {

		System.out.println(player.currentInventory());
		System.out.println();
	}

	/**
	 * output to the console the current status for every player.
	 *
	 * @param player the player
	 */
	public static void printOutPlayerState(Player player) {
		System.out.println(player.toString());
		System.out.println();
	}

	/**
	 * This method prints out the game board state.
	 */
	public static void printOutGameBoardState() {

		System.out.println("***** Game Board State *****");
		System.out.printf("%-22s%-18s%-18s%-18s%-18s%s\n", "Areas", "Minions", "Trouble?", "Buildings?", "Demons", "Trolls");
		System.out.println();

		// iterating over all areas of the board initially to setup 3 trouble markers in 3 specfic areas according ot the rule
		for (Area temp : BoardGame.board_areas) {
			// printout areas
			temp.to_String();
		}
		System.out.println("Amount with the Bank "+BoardGame.getBank());


	}


}
