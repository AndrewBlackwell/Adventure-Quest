/** 
    ___       __                 __                
   /   | ____/ /   _____  ____  / /___  __________ 
  / /| |/ __  / | / / _ \/ __ \/ __/ / / / ___/ _ \
 / ___ / /_/ /| |/ /  __/ / / / /_/ /_/ / /  /  __/
/_/__|_\__,_/ |___/\___/_/_/_/\__/\__,_/_/   \___/ 
  / __ \__  _____  _____/ /_                       
 / / / / / / / _ \/ ___/ __/                       
/ /_/ / /_/ /  __(__  ) /_                         
\___\_\__,_/\___/____/\__/   

Adventure Quest is a text-based adventure game that I created in Java.
It is a simple game that allows the user to make choices that will affect the outcome of the game.
While the mechanisms are simple and the game is short, it was a great learning experience for me, 
and I beleive it demonstrates my ability to write clean, responsive code.
Andrew Blackwell, 9/30/2020
*/

//Manage imports
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Game {
    // Swing components
    JFrame frame;
    JPanel titlePanel, startPanel, mainPanel, optionsPanel, heroPanel;
    JLabel titleLabel, healthPointsLabel, healthPointsValue, weaponLabel, weaponName;
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    // AWT components & ActionListeners
    TitleListener tl = new TitleListener();
    ChoiceListener cl = new ChoiceListener();
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 60);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    Container container;
    // Game variables
    int HeroHP, dragonHP, rustedKey;
    String weapon, room;

    public static void main(String[] args) {
        new Game();
    }

    // Constructor
    public Game() {
        frame = new JFrame("Adventure Quest");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        container = frame.getContentPane();

        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 150);
        titlePanel.setBackground(Color.black);
        titleLabel = new JLabel("ADVENTURE QUEST");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titleFont);

        startPanel = new JPanel();
        startPanel.setBounds(300, 400, 200, 100);
        startPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(tl);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);

        titlePanel.add(titleLabel);
        startPanel.add(startButton);

        container.add(titlePanel);
        container.add(startPanel);
        frame.setVisible(true);
    }

    public void initialize() {
        titlePanel.setVisible(false);
        startPanel.setVisible(false);

        mainPanel = new JPanel();
        mainPanel.setBounds(100, 100, 600, 250);
        mainPanel.setBackground(Color.black);
        container.add(mainPanel);

        mainTextArea = new JTextArea("This is the main text area.");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainPanel.add(mainTextArea);

        optionsPanel = new JPanel();
        optionsPanel.setBounds(250, 350, 300, 150);
        optionsPanel.setBackground(Color.black);
        optionsPanel.setLayout(new GridLayout(4, 1));
        container.add(optionsPanel);

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(cl);
        choice1.setActionCommand("c1");
        choice1.setBorderPainted(false);
        optionsPanel.add(choice1);

        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(cl);
        choice2.setActionCommand("c2");
        choice2.setBorderPainted(false);
        optionsPanel.add(choice2);

        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(cl);
        choice3.setActionCommand("c3");
        choice3.setBorderPainted(false);
        optionsPanel.add(choice3);

        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(cl);
        choice4.setActionCommand("c4");
        choice4.setBorderPainted(false);
        optionsPanel.add(choice4);

        heroPanel = new JPanel();
        heroPanel.setBounds(100, 15, 600, 50);
        heroPanel.setBackground(Color.black);
        heroPanel.setLayout(new GridLayout(1, 4));
        container.add(heroPanel);
        container.revalidate();
        container.repaint();
        healthPointsLabel = new JLabel("HP: ");
        healthPointsLabel.setFont(normalFont);
        healthPointsLabel.setForeground(Color.white);
        heroPanel.add(healthPointsLabel);
        healthPointsValue = new JLabel();
        healthPointsValue.setFont(normalFont);
        healthPointsValue.setForeground(Color.white);
        heroPanel.add(healthPointsValue);
        weaponLabel = new JLabel("Weapon: ");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.white);
        heroPanel.add(weaponLabel);
        weaponName = new JLabel();
        weaponName.setFont(normalFont);
        weaponName.setForeground(Color.white);
        heroPanel.add(weaponName);

        playerSetup();
    }

    public void playerSetup() {
        HeroHP = 35;
        dragonHP = 100;
        weapon = "Knife";
        healthPointsValue.setText("" + HeroHP);
        weaponName.setText(weapon);

        dungeonEntrance();
    }

    public void dungeonEntrance() {
        room = "dungeonEntrance";
        mainTextArea
                .setText(
                        "You awaken in a dark corridor.\nThe exit door is locked.\nYou hold a small rusted knife.\n\nWhat do you do?");
        choice1.setText("Examine the door");
        choice2.setText("Pry the door open");
        choice3.setText("Explore the corridor");
        choice4.setText("");
        // choice4.setVisible(false);
    }

    public void examineDoor() {
        room = "examineDoor";
        mainTextArea.setText(
                "The door is old, but sturdy.\nIt unlocks with a key,\nbut you don't see one around.");
        choice1.setText("Go back");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void pryDoor() {
        room = "pryDoor";
        mainTextArea.setText(
                "You force your knife in the lock\nYou struggle and lose energy\n(hp - 9)");
        HeroHP -= 9;
        healthPointsValue.setText("" + HeroHP);
        choice1.setText("Go back");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void crossRoad() {
        room = "crossRoad";
        mainTextArea.setText("You reach a room with four doors.\nThe southern door leads back to the entrance.");
        choice1.setText("Go north");
        choice2.setText("Go east");
        choice3.setText("Go south");
        choice4.setText("Go west");
    }

    public void north() {
        room = "north";
        mainTextArea.setText(
                "You encounter a healing spring. \nYou drink the water and rest. \n\n(Your HP is recovered by 10)");
        HeroHP += 10;
        healthPointsValue.setText("" + HeroHP);
        choice1.setText("Go back");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void east() {
        room = "east";
        mainTextArea.setText(
                "You stumble apon the burnt remains\nof a warrior. You pay your respects,\nand recieve the BroadSword.");
        weapon = "BroadSword";
        weaponName.setText(weapon);
        choice1.setText("Go back");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void west() {
        room = "west";
        mainTextArea.setText("You encounter a sleeping dragon!\nDo you dare to fight it?");
        choice1.setText("Fight");
        choice2.setText("Flee");
        choice3.setText("");
        choice4.setText("");

    }

    public void fight() {
        room = "fight";
        mainTextArea.setText("Dragon HP: " + dragonHP + "\n\nWhat do you do?");
        choice1.setText("Attack");
        choice2.setText("Run");
        choice3.setText("");
        choice4.setText("");
    }

    public void playerAttack() {
        room = "playerAttack";

        int playerDamage = 0;

        if (weapon.equals("Knife")) {
            playerDamage = new java.util.Random().nextInt(5) + 10;
        } else if (weapon.equals("BroadSword")) {
            playerDamage = new java.util.Random().nextInt(15) + 20;
        }

        mainTextArea.setText("You attacked the Dragon and dealt " + playerDamage + " damage!");

        dragonHP -= playerDamage;

        choice1.setText("continue");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void monsterAttack() {
        room = "monsterAttack";

        int monsterDamage = 0;

        monsterDamage = new java.util.Random().nextInt(10) + 10;

        mainTextArea.setText("The Dragon attacked you and dealt " + monsterDamage + " damage!");

        HeroHP -= monsterDamage;
        healthPointsValue.setText("" + HeroHP);

        choice1.setText("continue");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void win() {
        room = "win";

        mainTextArea.setText("You defeated the Dragon!\nIt dropped treasure!\n\n(You obtained a Rusted Key)");

        rustedKey = 1;

        choice1.setText("Go back");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void lose() {
        room = "lose";

        mainTextArea.setText("You meet a fiery demise...\n\n<GAME OVER>");

        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    public void ending() {
        room = "ending";

        mainTextArea.setText(
                "You're back where you started,\nmuch stronger than before!\nYou unlock the door and escape!\n\n<THE END>");

        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    public class TitleListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            initialize();
        }
    }

    public class ChoiceListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            String yourChoice = event.getActionCommand();

            switch (room) {
                case "dungeonEntrance":
                    switch (yourChoice) {
                        case "c1":
                            if (rustedKey == 1) {
                                ending();
                            } else {
                                examineDoor();
                            }
                            break;
                        case "c2":
                            pryDoor();
                            break;
                        case "c3":
                            crossRoad();
                            break;
                    }
                    break;
                case "examineDoor":
                    switch (yourChoice) {
                        case "c1":
                            dungeonEntrance();
                            break;
                    }
                    break;
                case "pryDoor":
                    switch (yourChoice) {
                        case "c1":
                            dungeonEntrance();
                            break;
                    }
                    break;
                case "crossRoad":
                    switch (yourChoice) {
                        case "c1":
                            north();
                            break;
                        case "c2":
                            east();
                            break;
                        case "c3":
                            dungeonEntrance();
                            break;
                        case "c4":
                            west();
                            break;
                    }
                    break;
                case "north":
                    switch (yourChoice) {
                        case "c1":
                            crossRoad();
                            break;
                    }
                    break;
                case "east":
                    switch (yourChoice) {
                        case "c1":
                            crossRoad();
                            break;
                    }
                    break;
                case "west":
                    switch (yourChoice) {
                        case "c1":
                            fight();
                            break;
                        case "c2":
                            crossRoad();
                            break;
                    }
                    break;
                case "fight":
                    switch (yourChoice) {
                        case "c1":
                            playerAttack();
                            break;
                        case "c2":
                            crossRoad();
                            break;
                    }
                    break;
                case "playerAttack":
                    switch (yourChoice) {
                        case "c1":
                            if (dragonHP < 1) {
                                win();
                            } else {
                                monsterAttack();
                            }
                            break;
                    }
                    break;
                case "monsterAttack":
                    switch (yourChoice) {
                        case "c1":
                            if (HeroHP < 1) {
                                lose();
                            } else {
                                fight();
                            }
                            break;
                    }
                    break;
                case "win":
                    switch (yourChoice) {
                        case "c1":
                            crossRoad();
                    }
                    break;

            }
        }
    }

}
