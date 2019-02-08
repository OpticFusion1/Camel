/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import HelperClasses.*;
import GameEntities.*;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Michael
 */
public class gameSetup {

    private static int difficultySelection;
    private static int camelSelection;
    private static int riderSelection;
    //---User Selected Camel Data---
    private static String camelName;
    private static int camelThirst;
    private static int camelEnergy;
    private static int camelHunger;
    public static Camel camel;

    //---User Selected Rider Data---
    private static String riderName;
    private static int riderThirst;
    private static int riderEnergy;
    private static int riderHunger;
    public static Rider rider;

    private static Scanner scan = new Scanner(System.in);

    public static void setupGame() {
        //Difficulty Selection Menu
        System.out.println("---Welcome To Camel---\nYou have stolen a camel to make your way across the great Mobi desert.\n"
                + "The natives want their camel back and are chasing you down! Survive your\n"
                + "desert trek and outrun the natives."
                + "\n"
                + "\n"
                + "\n---Instructions---"
                + "\n1: Please select your difficutly, this will detirmine how stats are affected while you continue your journey."
                + "\n2: Select if you would like to use a pre-made camel, a randomly generated camel, or you'd like to create your own."
                + "\n\tPlease note selecting lower values makes the game more difficult, regardless of difficulty."
                + "\n3: Select if you would like to use a pre-made rider, a randomly generated rider, or you'd like to create your own."
                + "\n\tPlease note selecting lower values makes the game more difficult, regardless of difficulty."
                + "\n"
                + "\nNote: It is possible for you to be caught if you are resting, searching for water, or searching for food.");
        difficultySelection = Menu.displayMenu(
                "\n Please select a difficulty"
                + "\n[1] EASY"
                + "\n[2] MEDIUM"
                + "\n[3] HARD ", 1, 3);
        Menu.getInput("Difficulty: ");
        //Camel Selection Menu
        camelSelection = Menu.displayMenu("---Please Select A Camel---"
                + "\n[1] Average Camel"
                + "\n[2] Random Camel"
                + "\n[3] Create Your Own", 1, 3);
        Menu.getInput("The camel you've selected is ");
        if (camelSelection == 3) //Creation of Custom Camel 
        {
            System.out.println("Please enter a name for your Camel"); //first question
            {
                camelName = scan.nextLine();
                System.out.println("Please select a thirst level [1-10].");
                while (!scan.hasNextInt()) {
                    System.out.println("Enter a valid selection for thirst [1-10].");
                    scan.nextLine();
                }
                camelThirst = scan.nextInt();
                while (camelThirst > 10 || camelThirst < 1) {
                    System.out.println("Enter a valid selection for thirst [1-10].");
                    camelThirst = scan.nextInt();
                } //end of user input for thirst
                System.out.println("Please select an energy level [1-10].");
                while (!scan.hasNextInt()) {
                    System.out.println("Enter a valid selection for energy [1-10]");
                    scan.nextLine();
                }
                camelEnergy = scan.nextInt();
                while (camelEnergy > 10 || camelEnergy < 1) {
                    System.out.println("Please enter a valid selection for energy [1-10]");
                    camelEnergy = scan.nextInt();
                } //end of user input for energy

                System.out.println("Please select a hunger level [1-10].");
                while (!scan.hasNextInt()) {
                    System.out.println("Enter a valid selection for hunger [1-10].");
                    scan.nextLine();
                }
                camelHunger = scan.nextInt();
                while (camelHunger > 10 || camelHunger < 1) {
                    System.out.println("Enter a valid selection for hunger [1-10].");
                    camelHunger = scan.nextInt();
                }
            } //End of user input for hunger
        }

        riderSelection = Menu.displayMenu("---Please Select A Rider---"
                + "\n[1] Average Rider"
                + "\n[2] Random Rider"
                + "\n[3] Create Your Own", 1, 3);
        Menu.getInput("The rider you've selected is ");
        if (riderSelection == 3) //Creation of Custom Camel 
        {
            System.out.println("Please enter a name for your Rider\nFor some reason the code is the same as camel"
                    + "but\nskips the rider input I'm not sure why."); //first question
            {
                riderName = scan.nextLine();
                System.out.println("Please select a thirst level [1-10].");
                while (!scan.hasNextInt()) {
                    System.out.println("Enter a valid selection for thirst [1-10].");
                    scan.nextLine();
                }
                riderThirst = scan.nextInt();
                while (riderThirst > 10 || riderThirst < 1) {
                    System.out.println("Enter a valid selection for thirst [1-10].");
                    riderThirst = scan.nextInt();
                } //end of user input for thirst
                System.out.println("Please select an energy level [1-10].");
                while (!scan.hasNextInt()) {
                    System.out.println("Enter a valid selection for energy [1-10]");
                    scan.nextLine();
                }
                riderEnergy = scan.nextInt();
                while (riderEnergy > 10 || riderEnergy < 1) {
                    System.out.println("Please enter a valid selection for energy [1-10]");
                    riderEnergy = scan.nextInt();
                } //end of user input for energy

                System.out.println("Please select a hunger level [1-10].");
                while (!scan.hasNextInt()) {
                    System.out.println("Enter a valid selection for hunger [1-10].");
                    scan.nextLine();
                }
                riderHunger = scan.nextInt();
                while (riderHunger > 10 || riderHunger < 1) {
                    System.out.println("Enter a valid selection for hunger [1-10].");
                    riderHunger = scan.nextInt();
                }
            } //End of user input for hunger
        }
        

    }

    //Setup Camel based on your selection in the menu
    public static void setupCamel(int option) {
        if (option == 1) {
            camel = new Camel("Smokey", 8, 7, 9);
            System.out.println("--Average Camel--"
                    + "\nName: " + camel.getName()
                    + "\nThrst: " + camel.getThirst()
                    + "\nHunger:" + camel.getHunger()
                    + "\nEnergy:" + camel.getEnergy());
        }
        if (option == 2) {
            Random rand = new Random();
            String[] name = {"Smokey", "Charlie", "Bella", "Lucy", "Simba", "Rosie", "Gidget"};
            int randomNameSelect = rand.nextInt(name.length);
            String randomName = "" + name[randomNameSelect];
            camel = new Camel(randomName, rand.nextInt(6) + 4, rand.nextInt(6) + 4, rand.nextInt(6) + 4);
            System.out.println("--Random Camel--"
                    + "\nName: " + camel.getName()
                    + "\nThirst: " + camel.getThirst()
                    + "\nHunger: " + camel.getHunger()
                    + "\nEnergy: " + camel.getEnergy());
        }
        if (option == 3) {
            camel = new Camel(camelName, camelThirst, camelHunger, camelEnergy);
            System.out.println("--Custom Camel--"
                    + "\nName: " + camel.getName()
                    + "\nThirst: " + camel.getThirst()
                    + "\nHunger: " + camel.getHunger()
                    + "\nEnergy: " + camel.getEnergy());

        }
    }

    //Setup Rider based on your selection in the menu
    private static void setupRider(int option) {
        if (option == 1) {
            rider = new Rider("Joe", 5, 7, 10);
            System.out.println("--Average Rider--"
                    + "\nName: " + rider.getName()
                    + "\nThirst: " + rider.getThirst()
                    + "\nHunger: " + rider.getHunger()
                    + "\nEnergy: " + rider.getEnergy());
        }
        if (option == 2) {
            Random rand = new Random();
            String[] name = {"James", "Michael", "Thomas", "Jeff", "Kevin", "Mary", "Lisa", "Karen", "Sarah", "Susan"};
            int randomNameSelect = rand.nextInt(name.length);
            String randomName = "" + name[randomNameSelect];
            rider = new Rider(randomName, rand.nextInt(6) + 4, rand.nextInt(6) + 4, rand.nextInt(6) + 4);
            System.out.println("--Random Rider--"
                    + "\nName: " + rider.getName()
                    + "\nThirst: " + rider.getThirst()
                    + "\nHunger: " + rider.getHunger()
                    + "\nEnergy: " + rider.getEnergy());
        }
        if (option == 3) {
            rider = new Rider(riderName, riderThirst, riderHunger, riderEnergy);
            System.out.println("--Custom Rider--"
                    + "\nName: " + rider.getName()
                    + "\nThirst: " + rider.getThirst()
                    + "\nHunger: " + rider.getHunger()
                    + "\nEnergy: " + rider.getEnergy());
        }
    }

    //public constructor that sets up game. 
    public static void gameSetup() {
        setupGame();
        setupCamel(camelSelection);
        setupRider(riderSelection);
    }

    public static Camel getCamel() {
        return camel;
    }

    public static Rider getRider() {
        return rider;
    }

    public static int getDifficulty() {
        return difficultySelection;
    }

}
