package tankrotationexample.tankgame;


import java.awt.image.BufferedImage;

public abstract class Moveable extends GameObjects {

    Moveable(int x, int y, BufferedImage img) {
        super(x, y, img);
    }


    public abstract void update();
}
