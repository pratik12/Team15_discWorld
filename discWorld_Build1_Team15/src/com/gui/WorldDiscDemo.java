package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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

    public static void addComponentsToPane(final Container contentPane) throws IOException {
        final JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        fc.setCurrentDirectory(new File("C:\\tmp"));


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
        playerPanel.add(new JLabel("Player 1"));
        playerPanel.add(new JLabel("Player 2"));
        playerPanel.add(new JLabel("Player 3"));
        playerPanel.add(new JLabel("Player 4"));
        contentPane.add(playerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        JButton startButton = new JButton("Start");
        //Add action listener to button
        startButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //Execute when button is pressed
                System.out.println("You clicked the start button");
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
                    File save = new File(fullPath + ".txt");
                    try {
                        flag = save.createNewFile();
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
                    File[] selectedfiles = fc.getSelectedFiles();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < selectedfiles.length; i++) {
                        sb.append(selectedfiles[i].getName() + "\n");
                    }
                    JOptionPane.showMessageDialog(contentPane, sb.toString());
                }

            }
        });
        buttonPanel.add(loadButton);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //Execute when button is pressed
                System.out.println("You clicked the exit button");
            }
        });
        contentPane.add(buttonPanel, BorderLayout.CENTER);

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
