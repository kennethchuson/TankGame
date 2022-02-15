package tankrotationexample.tankgame;


import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthPowerUp extends PowerUp {

    private Rectangle bound;

    HealthPowerUp(BufferedImage img, int x, int y){
        super(x,y,img);
        this.bound = new Rectangle(x,y,this.img.getWidth(), this.img.getHeight());
        gameObjects.add(this);
    }
}
