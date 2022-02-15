package tankrotationexample.tankgame;


import java.awt.image.BufferedImage;

public abstract class PowerUp extends GameObjects {

    PowerUp(int x, int y, BufferedImage img) {
        super(x, y, img);
    }
}
