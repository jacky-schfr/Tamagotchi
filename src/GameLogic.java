import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


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


        System.out.println(pet.happinessLvl);


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

                System.out.println(pet.healthLvl +"+"+ pet.happinessLvl);
            }
        });


        while (run) {
            c.feeding();


        }
    }
}


