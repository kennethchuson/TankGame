package tankrotationexample.tankgame;


import java.awt.image.BufferedImage;

public abstract class Wall extends Stationary {

    Wall(int x, int y, BufferedImage img) {
        super(x, y, img);
    }

}
