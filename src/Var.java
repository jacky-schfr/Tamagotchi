public class Var {

    public static String path = "src/save";

    public static int animationPetFileInt = 0;

    public static long currentTime;
    public static long animationTimer;
    public static long healthTimer, loveTimer, happinessTimer, hygeneTimer;

    public static long init(){
        return System.currentTimeMillis();
    }
}
