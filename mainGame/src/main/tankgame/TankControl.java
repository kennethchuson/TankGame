package tankrotationexample.tankgame;

import jdk.jshell.spi.ExecutionControlProvider;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class TankControl implements KeyListener {

    private Tank t1;
    private UnbreakableWall wall;
    private final int up;
    private final int down;
    private final int right;
    private final int shoot;
    private final int left;
    




    public TankControl(Tank t1, int up, int down, int left,int shoot, int right) {
        this.t1 = t1;

        this.up = up;

        this.down = down;

        this.right = right;

        this.shoot = shoot;

        this.left = left;


    }
    



    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up) {
            this.t1.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.t1.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.t1.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.t1.toggleRightPressed();
        }

        if (keyPressed == shoot) {
            this.t1.toggleShotPressed();

        }
    }
    @Override
    public void keyTyped(KeyEvent ke) { }


    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == up) {
            this.t1.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.t1.unToggleDownPressed();
        }
        if (keyReleased  == right) {
            this.t1.unToggleRightPressed();
        }
        if (keyReleased  == left) {
            this.t1.unToggleLeftPressed();
        }

        if (keyReleased == shoot){
            this.t1.unToggleShotPressed();
        }

    }
}
