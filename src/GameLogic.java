import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;


public class GameLogic {

    public static void main(String[] args) {

        Food cupcake = new Food();
        cupcake.cupcake();
        cupcake.cupcakeParameter();
        Food pizza = new Food();
        pizza.pizza();
        pizza.pizzaParameter();
        Food broccoli = new Food();
        broccoli.broccoli();
        broccoli.broccoliParameter();


        ArrayList<Food> foodCanvas = new ArrayList<>();
        foodCanvas.add(cupcake);
        foodCanvas.add(pizza);
        foodCanvas.add(broccoli);

        Pet pet = new Pet("Cutie");

        boolean run = true;

        CanvasLayer c = new CanvasLayer(foodCanvas, pet);

        c.canvas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (foodCollide(cupcake, e)) {
                    pet.moreHappiness(cupcake);
                    pet.moreHealth(cupcake);
                }
                if (foodCollide(pizza, e)) {
                    pet.moreHappiness(pizza);
                    pet.moreHealth(pizza);
                }
                if (foodCollide(broccoli, e)) {
                    pet.moreHappiness(broccoli);
                    pet.moreHealth(broccoli);
                }
            }
        });

        Var.healthTimer = Var.init();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Var.currentTime = System.currentTimeMillis();
                c.basicStats();
                c.feeding();
                c.petAnimation();
                c.buffer();
                pet.updatePet();
                if (pet.moreLove && pet.lveTimer == null) {
                    pet.petLove();
                }
                if ((Var.currentTime - Var.healthTimer) >= 500) {
                    pet.petHealth();
                    Var.healthTimer = System.currentTimeMillis();
                }
                save(pet);
            }
        }, 0, 60);
    }
    public static boolean foodCollide(Food food, MouseEvent mouse) {
        return mouse.getX() > food.x && mouse.getX() < (food.x + food.ovalWidth) &&
                mouse.getY() > food.y && mouse.getY() < (food.y + food.ovalHeight);
    }
    public static void save(Pet p){
        File file = new File(Var.path +"//tamagotchi.json");
        try{
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())), "UTF-8");
            JSONObject json = new JSONObject(content);
            System.out.println(json);

            json.put("name", p.name);
            json.put("loveLvl", p.loveLvl);
            json.put("health", p.healthLvl);
            json.put("happiness", p.happinessLvl);

            FileWriter fw = new FileWriter("src/save/tamagotchi.json");
            fw.write(json.toString());
            fw.flush();
            fw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}


