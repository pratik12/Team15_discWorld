package com.gui;

import com.app.BoardGame;
import com.app.GameState;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.JFileChooser.APPROVE_OPTION;

/**
 * Created with IntelliJ IDEA.
 * User: Mahdiye
 * Date: 1/29/15
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorldDiscDemo {

    public static boolean RIGHT_TO_LEFT = false;
    static ArrayList<String> playerData = new ArrayList();

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
                if ((playersNumber == null) || (!playersNumber.equals("2") && !playersNumber.equals("3") && !playersNumber.equals("4"))) {
                    JOptionPane.showMessageDialog(null, "Entered Number Is not Valid!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    BoardGame.startGame();
                    BoardGame.initiate_number_of_players(Integer.parseInt(playersNumber));
                }
            }
        });

        buttonPanel.add(startButton);
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                JFileChooser saveFile = new JFileChooser();
                saveFile.setDialogTitle("Save to");
                int userSelection = saveFile.showSaveDialog(null);
                File File_Path = saveFile.getSelectedFile();
                String fullPath = File_Path.getAbsolutePath();
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    try {
                        GameState.saveMap(fullPath + ".txt");
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
                    playerData = GameState.loadFile(selectedfilePath);
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

    private static void showTextAreas(Container contentPane, JPanel playersPanel) {
        int playerCount = 0;
        if (!playerData.isEmpty()) {
            playerCount = playerData.get(0).charAt(playerData.get(0).length() - 1) - 48;
            for (int i = 1; i <= playerCount; i++) {
                JPanel player = new JPanel();
                player.setBackground(identifyColor(playerData.get(7 * (i - 1) + 1)));
                StringBuffer playerContent = new StringBuffer();
                for (int j = 7 * (i - 1) + 1; j < 7 * i; j++) {
                    playerContent.append(playerData.get(j));
                    playerContent.append(System.getProperty("line.separator"));
                }
                JTextArea textArea = new JTextArea(playerContent.toString(), 8, 15);
                textArea.setLineWrap(true);
                player.add(new JScrollPane(textArea));
                playersPanel.add(player);

            }
        }
        contentPane.add(playersPanel, BorderLayout.CENTER);
    }

    private static void createAndShowGUI() throws IOException {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("WorldDisc Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane and add swing components to it
        addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }


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
