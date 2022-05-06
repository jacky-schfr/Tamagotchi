import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Pet {
    int happinessLvl, healthLvl, happinessMax, healthMax, loveLvl;
    String name;
    Timer lveTimer = null;
    Boolean moreLove;

    public String petA(){
        String filePet = "src/images/pet/p0.png";
        for(int i = 1; i <=3; i++) {
            filePet = filePet.replace(String.valueOf(0), String.valueOf(i));
//            if(i == 3){
//                i =  0;
//            }
//            System.out.println(filePet);
        }
        return filePet;
    }


    public Pet(String name) {
        this.name = name;
        this.happinessMax = 100;
        this.healthMax = 100;

//        TODO: Initialisieren der Werte in einer eigenen Methode
//              Abfragen ob es "Cutie" schon gibt in der GameLogic.java
//              Also... entweder Aufrufen der init Methode ODER laden von Daten (Alles in der GameLogic.java)
        if(!name.equals("Cutie")){
            this.happinessLvl = 50;
            this.healthLvl = 50;
            this.loveLvl = 0;
        }
        else {
//            TODO: Laden von Daten aus dem json File in der GameLogic.java mit einer eigenen Methode
            File file = new File(Var.path +"//tamagotchi.json");

            try{
                String content = new String(Files.readAllBytes(Paths.get(file.toURI())), "UTF-8");
                JSONObject json = new JSONObject(content);

                name = (String)json.get("name");
                loveLvl = json.getInt("loveLvl");
                healthLvl = json.getInt("health");
                happinessLvl = json.getInt("happiness");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
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

    public void moreHappiness(Food f) {
        happinessLvl = happinessLvl + f.happinessLvl;

        if (happinessLvl > 100) {
            happinessLvl = happinessMax;
        }
        if (happinessLvl < 0) {
            happinessLvl = 0;
        }
    }

    public void moreHealth(Food f) {
        healthLvl = healthLvl + f.foodValue;

        if (healthLvl > 100) {
            healthLvl = happinessMax;
        }
        if (healthLvl < 0) {
            healthLvl = 0;
        }
    }
}
