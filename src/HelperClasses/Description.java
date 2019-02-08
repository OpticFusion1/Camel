package HelperClasses;

import GameEntities.Camel;
import GameEntities.Rider;

/**
 *
 * @author Michael
 */
public class Description {


    private Description(Camel camel, Rider rider) {
        String output; 
        output = "--Camel Stats--"
                + "\nThirst: " +getThirstDescription(camel)
                + "\nEnergy: " +getEnergyDescription(camel)
                + "\nHunger: " +getHungerDescription(camel);
    }

    
    public static String getThirstDescription(Camel camel) {
        String output; 
        if (camel.getThirst() <= 10 && camel.getThirst() >=7) {
            output = "Quenched: " + camel.getThirst();
        } else if (camel.getThirst() <=6 && camel.getThirst() >=3) {
            output = "Slightly Partched:" + camel.getThirst();
        } else {
            output = "Bone Dry: " + camel.getThirst();
        }
        return output; 
    }
    
    public static String getHungerDescription(Camel camel) {
        String output; 
        if (camel.getHunger() <= 10 && camel.getHunger() >=7) {
            output = "Full: " + camel.getHunger();
        } else if (camel.getHunger() <= 6 && camel.getHunger() >=3) {
            output = "Satisfied: " + camel.getHunger();
        } else {
           output = "Hungry: " + camel.getHunger();
        }
        return output; 
    }
    
    public static String getEnergyDescription(Camel camel) {
        String output; 
        if (camel.getEnergy() <= 10 && camel.getEnergy() >=7) {
           output = "Awake: " + camel.getEnergy();
        } else if (camel.getEnergy() <= 6 && camel.getEnergy() >=3) {
            output = "Tired: " + camel.getEnergy();
        } else {
           output = "Exhausted: " + camel.getEnergy();
        }
        return output; 
    }
    
    public static String getStatus(Camel camel) {
        return "\nThirst Level: " + camel.getThirst() + "\nEnergy Level: " + camel.getEnergy() + "\nHunger Level: " + camel.getHunger();
    }
    
    public static void getThirstDescription(Rider rider){
        if (rider.getThirst() <= 10 && rider.getThirst() >=7) {
            System.out.print("Quenched: " +rider.getThirst());
        }
        else if (rider.getThirst() <=6 && rider.getThirst() >=3) {
            System.out.print("Slightly Partched: " +rider.getThirst());
        }
        else
            System.out.print("Bone Dry: " +rider.getThirst());
    }
    
    public static void getHungerDescription(Rider rider) {
        if (rider.getHunger() <= 10 && rider.getHunger() >=7){
            System.out.print("Full: " +rider.getHunger());
        }
        else if (rider.getHunger() <=6 && rider.getHunger() >=3){
            System.out.print("Satisfied: " +rider.getHunger()); 
        }
        else 
            System.out.print("Hungry:" +rider.getHunger()); 
    }
    
    public static void getEnergyDescription(Rider rider) {
        if (rider.getEnergy() <= 10 && rider.getEnergy() >=7) {
            System.out.print("Awake: " +rider.getEnergy());
        }
        else if (rider.getEnergy() <=6 && rider.getEnergy() >=3) {
            System.out.print("Tired: " +rider.getEnergy()); 
        }
        else
            System.out.print("Exhausted: " +rider.getEnergy());
    }
    public static String getStatus(Rider rider){
        return "\nThirst Level: " +rider.getThirst() + "\nEnergy Level: " +rider.getEnergy() +"\nHunger Level: " +rider.getHunger();
    }
    
}
