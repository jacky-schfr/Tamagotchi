import java.sql.Time;
import java.util.Timer;

public class Pet {
    int happinessLvl, healthLvl, happinessMax, healthMax;
    String name;

    public Pet(String name){
        this.name = name;
        this.happinessLvl = 50;
        this.healthLvl = 50;
        this.happinessMax = 100;
        this.healthMax = 100;
    }

    public void petHealth() {
        if(healthLvl!=0){
            healthLvl -= 1;
        }
        System.out.println(healthLvl);
    }


/*        if(healthLvl>30) {
            System.out.println("Your pet" + name + "is healthy.");
        }
        else if(healthLvl>10){
            System.out.println("Please take better care of "+name+"!");
        }
        else {
            System.out.println(name+" is ill. Please give "+name+" some medicine and take better care or "+name);
        }*/

    public void moreHappiness(){
        if(happinessLvl > 100){
            happinessLvl = happinessMax;
        }
        if (happinessLvl < 0){
            happinessLvl = 0;
        }
    }
    public void moreHealth() {
        if (healthLvl > 100) {
            healthLvl = happinessMax;
        }
        if (healthLvl < 0) {
            healthLvl = 0;
        }
    }
}
