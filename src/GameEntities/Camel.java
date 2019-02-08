package GameEntities;

/**
 *
 * @author Michael
 */
public class Camel {

    private int thirst;
    private int energy;
    private int hunger;
    private String camelName;

    //Constuctors 
    public Camel(String name, int thirst, int hunger, int energy) {
        this.camelName = name; 
        this.thirst = thirst;
        this.hunger = hunger;
        this.energy = energy;
    }
    public Camel(){
        thirst = 4; 
        energy = 6; 
        hunger = 10;
        camelName = "Test";
    }

    public Camel(String name) {
        name = camelName;
    }

    //getMethods
    public String getName() {
        return camelName; 
    }
    public int getThirst() {
        return thirst;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHunger() {
        return hunger;
    }

    //adjust methods
    public void adjustThirst(int value) {
        thirst = value; 
    }

    public void adjustEnergy(int value) {
        energy = value; 
    }

    public void adjustHunger(int value) {
        hunger = value; 
    }
    public String toString() {
        return camelName + " " +thirst +" " +hunger +" " +energy; 
        
}
}
