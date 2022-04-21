public class Heart {

    public static String fileHeart = "src/images/hearts/heart0.png";
    public static String risingHeart(int i){
        return fileHeart = fileHeart.replace(String.valueOf(i-1), String.valueOf(i));
    }
}
