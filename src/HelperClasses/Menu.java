package HelperClasses;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Michael
 */
public class Menu {

    private static int selection;

    private static ArrayList<Integer> list = new ArrayList(); 
    private Menu() {
}
    
    
    public static int displayMenu(String message, int min, int max) {
        Scanner scan = new Scanner(System.in);

        System.out.println(message);
        while (!scan.hasNextInt()) {
            scan.nextLine();
            System.out.println("Please enter a number between " + min + " and " + max);
        }
        int userInput = scan.nextInt();
        while (userInput > max || userInput < min) {
            
            System.out.println("Please select a number between " +min + " and " +max); 
             userInput = scan.nextInt();
             while (!scan.hasNextInt()) {
            scan.nextLine();
            System.out.println("Please enter a number between " + min + " and " + max);
        }
        }
        
        if (userInput == 1) {
            selection = userInput;
            list.add(userInput);
            return selection;
        }
        if (userInput == 2) {
            selection = userInput;
            list.add(userInput); 
            return selection;

        }
        if (userInput == 3) {
            selection = userInput;
            list.add(userInput); 
            return selection;
        }
        if (userInput == 4) {
            selection = userInput; 
            list.add(userInput); 
            return selection;
        }    
        if (userInput == 5) {
            selection = userInput;
            list.add(userInput);
            return selection;
        }
        if (userInput == 6) {
            selection = userInput; 
            list.add(userInput);
            return selection; 
        }

       
        return max;

    }
    public static String getInput(String prompt) {
        String output = prompt +selection; 
        System.out.println(output);
        return output; 
    }
    public static void getData(int option) {
        list.get(option); 
        
         
    }


}