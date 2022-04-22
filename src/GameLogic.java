import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GameLogic {


    public static boolean foodCollide(Food food, MouseEvent mouse) {
        return mouse.getX() > food.x && mouse.getX() < (food.x + food.ovalWidth) &&
                mouse.getY() > food.y && mouse.getY() < (food.y + food.ovalHeight);
    }

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
                    pet.happinessLvl = pet.happinessLvl + cupcake.happinessLvl;
                    pet.moreHappiness();

                    pet.healthLvl = pet.healthLvl + cupcake.foodValue;
                    pet.moreHealth();
                }
                if (foodCollide(pizza, e)) {
                    pet.happinessLvl = pet.happinessLvl + pizza.happinessLvl;
                    pet.moreHappiness();

                    pet.healthLvl = pet.healthLvl + pizza.foodValue;
                    pet.moreHealth();
                }
                if (foodCollide(broccoli, e)) {
                    pet.happinessLvl = pet.happinessLvl + broccoli.happinessLvl;
                    pet.moreHappiness();

                    pet.healthLvl = pet.healthLvl + broccoli.foodValue;
                    pet.moreHealth();
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
                pet.updatePet();
                if (pet.moreLove && pet.lveTimer == null) {
                    pet.petLove();
                }
                if ((Var.currentTime - Var.healthTimer) >= 500) {
                    pet.petHealth();
                    Var.healthTimer = System.currentTimeMillis();
                }
            }
        }, 0, 60);

    }
}


