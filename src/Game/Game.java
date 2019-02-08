/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import GameEntities.*;
import HelperClasses.Description;
import HelperClasses.Menu;
import java.util.Random;

/**
 *
 * @author Michael
 */
public class Game {

    private static boolean canTravel = true;
    private static boolean riderDead = false;
    private static boolean gameWon = false;
    private static boolean caught = false;
    private static String location = null;
    private static String newWeather = null;
    private static String timeOfDay = null;
    private static String distanceDescription = null;
    private static int pursuersDistance = 0;
    private static int daysPassed = 1;
    private static int milesTraveled = 0;
    private static int randomMilesPerTurn = 0;
    private static int selection;
    private static String turnInformation = null;
    private static Random rand = new Random();

    public static void start() {
        gameSetup.gameSetup();
        processsDifficulty();
        while (milesTraveled <= 200) {
            Camel camel = gameSetup.getCamel();
            Rider rider = gameSetup.getRider();

            if (checkCamelCanTravel() == false) {
                System.out.println("\033[31m Your Camel Cannot Travel Currently");

            }
            if (camel.getEnergy() <= 0 || camel.getThirst() <= 0 || camel.getHunger() <= 0) {
                System.out.println("Your camel has died. Game Over");
                break;
            } else if (isRiderDead() == true) {
                System.out.println("Your Rider is Dead. Game Over.");
                break;
            } else if (caught == true) {
                System.out.println("The natives have finally caught up to you, they kill you, and take their camel \n\033[31mGame Over");
                break;
            }
            checkSelection();
            getNewLocation();
            getNewWeather();
            getNewTimeOfDay();
            outputTurnInformation();
            processTurn();
            getPursuerDistanceDescription();
            processPursurers();
            randomCaught();
            endTurnLine();
            processGameWon();

        }
        endGameStats();
    }

    private static double processsDifficulty() {
        int dif = gameSetup.getDifficulty();
        if (dif == 1) {
            return 1;
        }
        if (dif == 2) {
            return (1.25);
        }
        if (dif == 3) {
            return 1.5;
        } else {
            return dif;
        }
    }

    private static boolean checkCamelCanTravel() {
        Camel camel = gameSetup.getCamel();
        if (camel.getEnergy() <= 2 || camel.getThirst() <= 2 || camel.getHunger() <= 2) {
            canTravel = false;
        } else {
            canTravel = true;
        }

        return canTravel;
    }

    //checks to see if the rider is dead based on their stats being 0
    private static boolean isRiderDead() {
        Rider rider = gameSetup.getRider();
        if (rider.getEnergy() == 0 || rider.getThirst() == 0 || rider.getHunger() == 0) {
            riderDead = true;
        } else {
            riderDead = false;
        }

        return riderDead;
    }

    //generates a new location per turn, if camel can't travel, location stays the same.
    private static void getNewLocation() {
        int randomSelect = rand.nextInt(15);
        if (checkCamelCanTravel() == false) {
            location = location;
        } else if (randomSelect == 0 || randomSelect == 1) {
            location = "Oasis";
        } else if (randomSelect == 2 || randomSelect == 3) {
            location = "Ruins";
        } else if (randomSelect == 4 || randomSelect == 5) {
            location = "River Bank";
        } else if (randomSelect >= 6 || randomSelect <= 11) {
            location = "Desert Plains";
        } else if (randomSelect >= 12 || randomSelect <= 14) {
            location = "Desert Hills";
        }
    }

    //randomly generates a new weather condition.
    private static void getNewWeather() {
        int randomSelect = rand.nextInt(10);
        if (randomSelect == 0) {
            newWeather = "Sandstorm";
        } else if (randomSelect == 1) {
            newWeather = "Raining";
        } else if (randomSelect == 2) {
            newWeather = "Cloudy";
        } else if (randomSelect >= 3 || randomSelect <= 5) {
            newWeather = "Warm";
        } else if (randomSelect >= 6 || randomSelect <= 9) {
            newWeather = "Hot";
        }
    }

    //creates a new day/night cylce changing each value off the previous
    //after it hits morning it adds +1 to the daysPassed value.
    private static void getNewTimeOfDay() {
        if (timeOfDay == null) {
            timeOfDay = "Morning";
        } else if (timeOfDay == "Night") {
            timeOfDay = "Morning";
            daysPassed += 1;
        } else if (timeOfDay == "Morning") {
            timeOfDay = "Afternoon";
        } else if (timeOfDay == "Afternoon") {
            timeOfDay = "Evening";
        } else if (timeOfDay == "Evening") {
            timeOfDay = "Night";
        }
    }

    //outputs the turn information with stats. 
    private static void outputTurnInformation() {
        if (selection == 1) {
            randomMilesPerTurn = 0;
        } else {

        }

        Camel camel = gameSetup.getCamel();
        Rider rider = gameSetup.getRider();
        System.out.println("You have traveled " + randomMilesPerTurn + " miles this turn."
                + "\n"
                + "\n---Statistics---"
                + "\nTime of Day: " + timeOfDay
                + "\nDays Passed: " + daysPassed
                + "\nTotal Miles Traveled: " + milesTraveled + " miles"
                + "\nCurrent Location: " + location
                + "\nWeather: " + newWeather
                + "\nCamel: " + Description.getStatus(camel)
                + "\nRider: " + Description.getStatus(rider));

    }

    //processes the turn, taking the selection number 1-6 and proccesses a method based on the selection. 
    private static void processTurn() {
        selection = Menu.displayMenu("---It's Your Turn!---"
                + "\n[1] Rest"
                + "\n[2] Travel Carefully"
                + "\n[3] Travel Regularly"
                + "\n[4] Ride All Out"
                + "\n[5] Search For Food"
                + "\n[6] Search For Water", 1, 6);
        if (selection == 1) {
            processRest();

        }
        if (selection == 2) {
            processTravelCarefully();

        }
        if (selection == 3) {
            processTravelRegularly();

        }
        if (selection == 4) {
            processRideAllOut();
        }
        if (selection == 5) {
            processFoodSearch();
        }
        if (selection == 6) {
            processWaterSearch();
        }
    }

    //randomizes a distance between the pursurer and the camel.
    private static void processPursurers() {
        pursuersDistance = rand.nextInt(4) + 4;

    }

    //outputs a description of the pursuerDistance to the user. 
    private static String getPursuerDistanceDescription() {

        System.out.println("The natives are only " + pursuersDistance + " units away!");
        return distanceDescription;
    }

    //checks if the the rider is caught
    //checks to see if you are resting, searching for food, or water. Since your camel is the fastest thing ever
    //the natives wont be able to catch you any other way.
    //then checks your game difficulty, it is harder to get caught if you're on easy.
    private static boolean randomCaught() {
        if (selection == 1 || selection == 5 || selection == 6) {
            if (gameSetup.getDifficulty() == 1) {
                int random = rand.nextInt(15);
                if (random == 0) {
                    caught = true;
                } else {
                    caught = false;
                }
            } else if (gameSetup.getDifficulty() == 2) {
                int random = rand.nextInt(10);
                if (random == 0) {
                    caught = true;
                } else {
                    caught = false;
                }
            } else if (gameSetup.getDifficulty() == 3) {
                int random = rand.nextInt(5);
                if (random == 0) {
                    caught = true;
                } else {
                    caught = false;
                }
            }
        }
        return caught;
    }

    //procceses the user selection to rest, it takes hunger and thirst away, but adding energy.
    private static void processRest() {
        System.out.println("You are resting");
        Camel camel = gameSetup.getCamel();
        Rider rider = gameSetup.getRider();
        //adjusts values on a +1 or +2 basis for rest
        camel.adjustEnergy(camel.getEnergy() + 2);
        camel.adjustThirst(camel.getThirst() - 1);
        camel.adjustHunger(camel.getHunger() - 1);
        rider.adjustEnergy(rider.getEnergy() + 1);
        rider.adjustThirst(rider.getThirst() - 1);
        rider.adjustHunger(rider.getHunger() - 1);
    }

    //processes the food search per area, changing the values of the rider and camel based on the location. Difficulty does not apply to this method. 
    private static void processFoodSearch() {
        Camel camel = gameSetup.getCamel();
        Rider rider = gameSetup.getRider();

        if (location == "Oasis") {
            int foodCamel = rand.nextInt(3) + 1;
            int foodRider = rand.nextInt(2) + 1;
            camel.adjustHunger((camel.getHunger() + foodCamel) * ((int) processsDifficulty()));
            rider.adjustHunger((rider.getHunger() + foodRider) * ((int) processsDifficulty()));

        } else if (location == "Ruins") {
            int foodCamel = rand.nextInt(1);
            int foodRider = rand.nextInt(1);
            camel.adjustHunger(camel.getHunger() + foodCamel * ((int) processsDifficulty()));
            rider.adjustHunger(camel.getHunger() + foodRider * ((int) processsDifficulty()));
        } else if (location == "River Bank") {
            int foodCamel = rand.nextInt(2) + 1;
            int foodRider = rand.nextInt(1);
            camel.adjustHunger(camel.getHunger() + foodCamel * ((int) processsDifficulty()));
            rider.adjustHunger(rider.getHunger() + +foodRider * ((int) processsDifficulty()));
        } else if (location == "Desert Plains") {
            camel.adjustHunger(camel.getHunger() + 0 * ((int) processsDifficulty()));
            rider.adjustHunger(rider.getHunger() + 1 * ((int) processsDifficulty()));
        } else if (location == "Desert Hills") {
            camel.adjustHunger(camel.getHunger() + 2 * ((int) processsDifficulty()));
            rider.adjustHunger(rider.getHunger() + 0 * ((int) processsDifficulty()));
        }
    }

    //PROCESSES THE WATER SEARCH PER AREA, changing the values on the location. Difficulty does not apply to this method.
    //If weahter = raining, thirst level will go up regardless of the area.
    private static void processWaterSearch() {
        Camel camel = gameSetup.getCamel();
        Rider rider = gameSetup.getRider();

        if (location == "Oasis") {
            int camelWater = rand.nextInt(2) + 1;
            int riderWater = rand.nextInt(3) + 1;
            camel.adjustThirst(camel.getThirst() + camelWater * ((int) processsDifficulty()));
            rider.adjustThirst(rider.getThirst() + riderWater * ((int) processsDifficulty()));
        } else if (location == "Ruins") {
            camel.adjustThirst(camel.getThirst() + 1);
            rider.adjustThirst(rider.getThirst() + 0);
        } else if (location == "River Bank") {
            int camelWater = rand.nextInt(3) + 1;
            int riderWater = rand.nextInt(2) + 1;
            camel.adjustThirst(camel.getThirst() + camelWater * ((int) processsDifficulty()));
            rider.adjustThirst(rider.getThirst() + riderWater * ((int) processsDifficulty()));
        } else if (location == "Desert Plains") {
            camel.adjustThirst(camel.getThirst() + 0 * ((int) processsDifficulty()));
            rider.adjustThirst(rider.getThirst() + 1 * ((int) processsDifficulty()));
        } else if (location == "Desert Hills") {
            camel.adjustThirst(camel.getThirst() + 1 * ((int) processsDifficulty()));
            rider.adjustThirst(rider.getThirst() + 1 * ((int) processsDifficulty()));
        } else if (newWeather == "Raining") {
            camel.adjustThirst(camel.getThirst() + 2);
            rider.adjustThirst(rider.getThirst() + 2);
        } else if (newWeather == "Hot") {
            camel.adjustThirst(camel.getThirst() -2);
            rider.adjustThirst(rider.getThirst() -3);
        }
    }

    //processes the travel carefully option, taking away thirst, energy, and hunger. Difficutly applies.
    private static void processTravelCarefully() {
        if (checkCamelCanTravel() == true || isRiderDead() == false) {
            Camel camel = gameSetup.getCamel();
            Rider rider = gameSetup.getRider();
            System.out.println("You decide to take your time, knowing if the time came, your camel wouldn't dissapoint.");
            camel.adjustEnergy(camel.getEnergy() - 1 * ((int) processsDifficulty()));
            rider.adjustEnergy(rider.getEnergy() - 1 * ((int) processsDifficulty()));
            camel.adjustThirst(camel.getThirst() - 1 * ((int) processsDifficulty()));
            rider.adjustThirst(rider.getThirst() - 0 * ((int) processsDifficulty()));
            camel.adjustHunger(camel.getHunger() - 1 * ((int) processsDifficulty()));
            rider.adjustHunger(rider.getHunger() - 1 * ((int) processsDifficulty()));
            randomMilesPerTurn = rand.nextInt(10) + 4;
            milesTraveled += randomMilesPerTurn;
        } else {
            System.out.println("You cannot travel!");
        }
    }

    //processes the travel regularly option, taking away thirst, hunger, and energy. Difficulty applies
    private static void processTravelRegularly() {
        if (checkCamelCanTravel() == true || isRiderDead() == false) {
            Camel camel = gameSetup.getCamel();
            Rider rider = gameSetup.getRider();
            System.out.println("You decide to leave the " + location + " taking a brisk pace with your camel.");
            camel.adjustEnergy(camel.getEnergy() - 2 * ((int) processsDifficulty()));
            rider.adjustEnergy(rider.getEnergy() - 1 * ((int) processsDifficulty()));
            camel.adjustThirst(camel.getThirst() - 2 * ((int) processsDifficulty()));
            rider.adjustThirst(rider.getThirst() - 1 * ((int) processsDifficulty()));
            camel.adjustHunger(camel.getHunger() - 2 * ((int) processsDifficulty()));
            rider.adjustHunger(rider.getHunger() - 2 * ((int) processsDifficulty()));
            randomMilesPerTurn = rand.nextInt(15) + 4;
            milesTraveled += randomMilesPerTurn;
        } else {
            System.out.println("You cannot travel!");
        }
    }

    //processes the rideAllOut option, taking away thirst, energy, and hunger. Difficulty applies
    private static void processRideAllOut() {
        if (checkCamelCanTravel() == true || isRiderDead() == false) {
            Camel camel = gameSetup.getCamel();
            Rider rider = gameSetup.getRider();
            System.out.println("You kick your camel into high gear, booking it out of the" + location);
            camel.adjustEnergy(camel.getEnergy() - 3 * ((int) processsDifficulty()));
            rider.adjustEnergy(rider.getEnergy() - 2 * ((int) processsDifficulty()));
            camel.adjustThirst(camel.getThirst() - 3 * ((int) processsDifficulty()));
            rider.adjustThirst(rider.getThirst() - 2 * ((int) processsDifficulty()));
            camel.adjustHunger(camel.getHunger() - 3 * ((int) processsDifficulty()));
            rider.adjustHunger(rider.getHunger() - 2 * ((int) processsDifficulty()));
            randomMilesPerTurn = rand.nextInt(20) + 4;
            milesTraveled += randomMilesPerTurn;
        } else {
            System.out.println("You cannot travel!");
        }
    }

    //processes if the milesTraveled is greater than 300, if so game is won.
    private static boolean processGameWon() {
        if (milesTraveled >= 300) {
            System.out.println("You run off the desert, your life intact, your camel still kicking. \nYou feel relief as you continue down your path with your new-found camel.");
            gameWon = true;

        } else {
            gameWon = false;
        }
        return gameWon;
    }

    //provides a line between turns to make the expierence easier to see.
    private static void endTurnLine() {
        System.out.println("---------------------------");
    }
    //checks if user selected rest, search for food or water and keeps location the same

    private static void checkSelection() {
        if (selection == 1) {
            location = location;
        } else if (selection == 5) {
            location = location;
        } else if (selection == 6) {
            location = location;
        }
        else {
            getNewLocation(); 
        }
    }

    //if the game is won or lost, it will output colorful stats!
    private static void endGameStats() {
        System.out.println("--End Game Stats---"
                + "\nTotal Miles Traveled: " + milesTraveled + "/200"
                + "\n--Camel Stats--"
                + "\nName: " + gameSetup.getCamel().getName()
                + "\nHunger: " + gameSetup.getCamel().getHunger()
                + "\nEnergy: " + gameSetup.getCamel().getEnergy()
                + "\nThirst: " + gameSetup.getCamel().getThirst()
                + "\n--Rider Stats--"
                + "\nName: " + gameSetup.getRider().getName()
                + "\nHunger: " + gameSetup.getRider().getHunger()
                + "\nEnergy: " + gameSetup.getRider().getEnergy()
                + "\nThirst: " + gameSetup.getRider().getThirst()
                + "");
    }

    private Game(int difficulty, Camel camel, Rider rider) {
        difficulty = gameSetup.getDifficulty();
        camel = gameSetup.getCamel();
        rider = gameSetup.getRider();
    }
}
