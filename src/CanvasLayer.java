import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CanvasLayer {
    public Pet name;
    JFrame mainFrame = new JFrame();
    Canvas canvas = new Canvas();
    ArrayList<Food> foodCanvas;
    Graphics g;
    int width = 400;
    int height = 400;
    BufferStrategy bufferStrategy;
    BufferedImage cupcake, pizza, broccoli;

    public CanvasLayer(ArrayList<Food> foodCanvas, Pet name) {
        this.foodCanvas = foodCanvas;
        this.name = name;

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(width, height);
        mainFrame.setVisible(true);
//        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        canvas.setSize(width, height);
        canvas.setBackground(new Color(126, 146, 203, 255));
//        canvas.setVisible(true);
//        canvas.setFocusable(false);

        mainFrame.add(canvas);


        canvas.createBufferStrategy(3);
        bufferStrategy = canvas.getBufferStrategy();


        try {
            cupcake = ImageIO.read(new File("src/images/cupcake.png"));
            pizza = ImageIO.read(new File("src/images/pizza.png"));
            broccoli = ImageIO.read(new File("src/images/broccoli.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void basicStats() {

        g = bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        g.setColor(new Color(255, 255, 255));
        g.drawString("What do you want to feed?", width / 2 - 70, height / 2 - 100);
        g.setColor(new Color(146, 255, 175, 255));
        g.drawString("Health", 180, 20);
        for (int i = 1; i <= name.healthLvl / 10; i++) {
            int rectPos = 240 + i * 11;
            g.drawRect(rectPos, 10, 8, 10);
        }
        g.setColor(new Color(255, 221, 52));
        g.drawString("Happiness", 180, 35);
        for (int i = 1; i <= name.happinessLvl / 10; i++) {
            int rectPos = 240 + i * 11;
            g.drawRect(rectPos, 25, 8, 10);
        }
        try {
            if(name.loveLvl < 100){
                g.drawImage(ImageIO.read(new File(Heart.risingHeart((name.loveLvl+5)/5))), 2, 2, null);
            }
            else {
                g.drawImage(ImageIO.read(new File(Heart.risingHeart(20))), 2, 2, null);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void feeding() {

        g.setColor(new Color(255, 255, 255));
        for (Food i : foodCanvas) {
            if(i == foodCanvas.get(0)) {
                g.drawString("Cupcake", i.x+30, i.y+100);
                g.drawImage(cupcake, i.x, i.y, null);
            }
            if(i == foodCanvas.get(1)) {
                g.drawString("Pizza", i.x+40, i.y+100);
                g.drawImage(pizza, i.x, i.y, null);
            }
            if(i == foodCanvas.get(2)) {
                g.drawString("Broccoli", i.x+30, i.y+100);
                g.drawImage(broccoli, i.x, i.y, null);
            }
        }
        bufferStrategy.show();
        g.dispose();
    }
}
