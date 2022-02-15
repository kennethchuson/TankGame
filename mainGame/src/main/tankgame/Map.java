package tankrotationexample.tankgame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class Map  {

    public static final int GAME_WIDTH = 1600;
    public static final int GAME_HEIGHT = 1000;
    public static ArrayList<GameObjects> objects = new ArrayList<>();
    private BufferedImage background, wall, wall2, healthPowerUp, increasedDamage, speedUp;

    Map(){

    }

    public void init() {

        try {
            background = read(new File("resources/Background.bmp"));
            wall = read(new File("resources/Wall1.gif"));
            wall2 = read(new File("resources/Wall2.gif"));
            healthPowerUp = read(new File("resources/pickUp3.png"));
            increasedDamage = read(new File("resources/pickUp4.png"));
            speedUp = read(new File("resources/pickUp1.png"));



        } catch (IOException e) {
        }


        InputStreamReader isr = new InputStreamReader(TRE.class.getClassLoader().getResourceAsStream("map1"));

        BufferedReader mapReader = new BufferedReader(isr);

        String row = null;
        try {
            row = mapReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (row == null) {
            System.err.println("error");
        }
        String[] mapInfo = row.split("\t");
        int numCols = Integer.parseInt(mapInfo[0]);
        int numRows = Integer.parseInt(mapInfo[1]);

        System.out.println("numCols: " + numCols);
        System.out.println("numRows: " + numRows);


        for (int curRow = 0; curRow < numRows; curRow++) {
            try {
                row = mapReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mapInfo = row.split("\t");
            for (int curCol = 0; curCol < numCols; curCol++) {
                switch(mapInfo[curCol]) {
                    case "2":
                        BreakableWall br = new BreakableWall(wall,curCol * 30, curRow * 30);
                        objects.add(br);
                        break;
                    case "3":
                        HealthPowerUp hp = new HealthPowerUp(healthPowerUp, curCol * 30, curRow * 30);
                        objects.add(hp);
                        break;
                    case "4":
                        FirePowerUp pp = new FirePowerUp(increasedDamage, curCol * 30, curRow * 30);
                        objects.add(pp);
                        break;
                    case "5":
                        SpeedUpPowerUp sp = new SpeedUpPowerUp(speedUp, curCol * 30, curRow * 30);
                        objects.add(sp);
                        break;
                    case "9":
                        UnbreakableWall ubr = new UnbreakableWall(wall2,curCol * 30, curRow * 30);
                        objects.add(ubr);
                        break;
                }
            }
        }
    }

    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < GAME_WIDTH/background.getWidth()+1; i++){
            for (int j = 0; j <GAME_HEIGHT/background.getHeight()+1; j++){
                g2d.drawImage(background, i*background.getWidth(), j*background.getHeight(),null);
            }
        }
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).drawImage(g2d);
        }
    }
}