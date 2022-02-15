package tankrotationexample.tankgame;


import java.awt.*;
import java.awt.image.BufferedImage;


public class FirePowerUp extends PowerUp {

    private Rectangle bound;

    FirePowerUp(BufferedImage img, int x, int y){
        super(x,y,img);
        this.bound = new Rectangle(x,y,this.img.getWidth(), this.img.getHeight());
        gameObjects.add(this);
    }
}
