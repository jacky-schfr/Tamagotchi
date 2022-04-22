import java.util.Timer;
import java.util.TimerTask;

public class Pet {
    int happinessLvl, healthLvl, happinessMax, healthMax, loveLvl;
    String name;
    Timer lveTimer = null;
    Boolean moreLove;


    public Pet(String name) {
        this.name = name;
        this.happinessLvl = 50;
        this.healthLvl = 50;
        this.happinessMax = 100;
        this.healthMax = 100;
        this.loveLvl = 0;
    }

    public void petHealth() {
        if (healthLvl != 0) {
            healthLvl -= 1;
        }
    }

    public void petLove() {
        lveTimer = new Timer();
        Var.loveTimer = Var.init();
        lveTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (loveLvl < 100) {
                    if(!moreLove){
                        lveTimer.cancel();
                        lveTimer = null;
                    }
                    if ((Var.currentTime - Var.loveTimer) >= 3500) {
                        loveLvl += 5;
                        Var.loveTimer = System.currentTimeMillis();
                    }
                }
            }
        }, 0, 60);
    }

    public void updatePet(){
        moreLove = healthLvl >= 50 && happinessLvl >= 30 || healthLvl >= 30 && happinessLvl >= 50;

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

    public void moreHappiness() {
        if (happinessLvl > 100) {
            happinessLvl = happinessMax;
        }
        if (happinessLvl < 0) {
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
