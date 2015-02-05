package com.gui;

import com.app.BoardGame;
import com.app.FileManager;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.JFileChooser.APPROVE_OPTION;

// TODO: Auto-generated Javadoc
/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 1/29/15
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorldDiscDemo {

	/** The right to left. */
	public static boolean RIGHT_TO_LEFT = false;

	/** The player data. */
	static ArrayList<String> playerData = new ArrayList<String>();

	/**
	 * Adds the components to pane.
	 *
	 * @param contentPane the content pane
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void addComponentsToPane(final Container contentPane) throws IOException {
		final JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);

		contentPane.setLayout(new BorderLayout(5, 5));
		if (!(contentPane.getLayout() instanceof BorderLayout)) {
			contentPane.add(new JLabel("Container doesn't use BorderLayout!"));
			return;
		}

		if (RIGHT_TO_LEFT) {
			contentPane.setComponentOrientation(
					java.awt.ComponentOrientation.RIGHT_TO_LEFT);
		}

		JButton jbnSampleButtons = new JButton("Button 2 (CENTER)");
		jbnSampleButtons.setPreferredSize(new Dimension(200, 100));
		contentPane.setLayout(new FlowLayout());
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(2, 2));

		showTextAreas(contentPane, playerPanel);

		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1));
		JButton startButton = new JButton("Start");
		//Add action listener to button
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String playersNumber = JOptionPane.showInputDialog(null,
						"How many players are going to be initiated?",
						"Let's start!",
						JOptionPane.QUESTION_MESSAGE);
				if (!inputNumberIsValid(playersNumber)) {
					JOptionPane.showMessageDialog(null, "Entered Number Is not Valid!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					BoardGame.startGame();
					BoardGame.initiateNumberOfPlayers(Integer.parseInt(playersNumber));
				}
			}
		});

		buttonPanel.add(startButton);
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser saveFile = new JFileChooser();
				saveFile.setDialogTitle("Save to");
				int userSelection = saveFile.showSaveDialog(null);
				File File_Path = saveFile.getSelectedFile();
				String fullPath = File_Path.getAbsolutePath();
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					try {
						FileManager.saveMap(fullPath + ".txt");
					} catch (IOException e1) {
						e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
					}
				}

			}

		});

		buttonPanel.add(saveButton);
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retVal = fc.showOpenDialog(contentPane);
				if (retVal == APPROVE_OPTION) {
					String selectedfilePath = fc.getSelectedFile().getAbsolutePath();
					String fileName = fc.getSelectedFile().getName();
					playerData = FileManager.loadFile(selectedfilePath,fileName);
					try {
						createAndShowGUI();
					} catch (IOException e1) {
						e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
					}

				}

			}
		});
		buttonPanel.add(loadButton);
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//Execute when button is pressed
				System.exit(0);
			}
		});
		buttonPanel.add(exitButton);
		contentPane.add(buttonPanel, BorderLayout.CENTER);
	}

	/**
	 * Show text areas.
	 *
	 * @param contentPane the content pane
	 * @param playersPanel the players panel
	 */
	private static void showTextAreas(Container contentPane, JPanel playersPanel) {
		int playerCount = 0;
		if (!playerData.isEmpty()) {
			playerCount = playerData.size() - 1;
			for (int i = 0; i < playerCount; i++) {
				JPanel player = new JPanel();
				player.setBackground(identifyColor(playerData.get(i)));
				StringBuffer playerContent = new StringBuffer();
				playerContent.append(playerData.get(i));
				JTextArea textArea = new JTextArea(playerContent.toString(), 8, 15);
				textArea.setLineWrap(true);
				player.add(new JScrollPane(textArea));
				playersPanel.add(player);

			}
		}
		contentPane.add(playersPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates the and show gui.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void createAndShowGUI() throws IOException {
		JFrame.setDefaultLookAndFeelDecorated(true);

		JFrame frame = new JFrame("DiscWorld Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set up the content pane and add swing components to it
		addComponentsToPane(frame.getContentPane());

		frame.pack();
		frame.setVisible(true);
	}


	/**
	 * Identify color.
	 *
	 * @param colour the colour
	 * @return the color
	 */
	public static Color identifyColor(String colour) {
		switch (colour.trim().charAt(0)) {
		case 'R':
			return Color.RED;
		case 'G':
			return Color.GREEN;
		case 'B':
			return Color.BLUE;
		case 'Y':
			return Color.YELLOW;
		default:
			return Color.CYAN;
		}
	}

	/**
	 * Input number is valid.
	 *
	 * @param userInput the user input
	 * @return true, if successful
	 */
	public static boolean inputNumberIsValid(String userInput) {
		boolean validationResult = true;
		if ((userInput == null) || (!userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4")))
			validationResult = false;
		return validationResult;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (IOException e) {
					e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
				}
			}
		});
	}
}
