package GameEntities;
/**
 *
 * @author Michael
 */
public class Rider {
    private int thirst; 
    private int energy; 
    private int hunger; 
    private String riderName; 
    
    //Constructors
    public Rider() {
        this.thirst = thirst; 
        this.energy = energy; 
        this.hunger = hunger; 
        this.riderName = riderName; 
    }
    public Rider(String name){
        name = riderName; 
    }
    public Rider(String name, int thirst, int hunger, int energy){
        this.riderName = name;  
        this.thirst = thirst; 
        this.hunger = hunger; 
        this.energy = energy; 
    }
    //getMethods
    public String getName() {
        return riderName; 
    }
    public int getThirst(){
        return thirst; 
    }
    public int getEnergy() {
        return energy;
    }
    public int getHunger() {
        return hunger; 
    }
    //adjustMethods
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
        return riderName + " " + thirst + " " +energy + " " +hunger; 
    }
}
