public class Food {
    int foodValue, happinessLvl, x, y, ovalHeight, ovalWidth;
    String foodName;

    public void cupcake() {
        this.foodName = "Cupcake";
        this.foodValue = -10;
        this.happinessLvl = 30;
//        return foodName + " Health: " + foodValue + " Happiness: " + happinessLvl;
    }
    public void pizza(){
        this.foodName = "Pizza";
        this.foodValue = 10;
        this.happinessLvl = 30;
    }
    public void broccoli(){
        this.foodName = "Broccoli";
        this.foodValue = 40;
        this.happinessLvl = -10;
    }

    public void cupcakeParameter() {
        this.x = 60;
        this.y = 200;
        this.ovalHeight = 100;
        this.ovalWidth = 70;
    }
    public void pizzaParameter(){
        this.x = 160;
        this.y = 200;
        this.ovalHeight = 100;
        this.ovalWidth = 70;
    }
    public void broccoliParameter(){
        this.x = 260;
        this.y = 200;
        this.ovalHeight = 100;
        this.ovalWidth = 70;
    }

    public int getFoodValue() {
        return foodValue;
    }

/*    public int getHappinessLvl() {
        return happinessLvl;
    }*/

    public String getFoodName() {
        return foodName;
    }
}
