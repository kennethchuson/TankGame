package tankrotationexample.tankgame;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Tank extends Moveable {

    private int vx;
    private int vy;
    private int health;
    private int angle;
    private int lives;
    private int R = 1;
    private int damage = 5;
    private final int ROTATIONSPEED = 1;
    private float cooldown;
    private float cooldownTimer = 0;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShotPressed;
    private Bullet bullet;
    private Rectangle bound = new Rectangle(this.x, this.y, img.getWidth(), img.getHeight());
    public ArrayList<Bullet> bulletsList = new ArrayList<>();
    private SoundEffects sound;
    private boolean checkFireUp = false;
    private boolean checkFireBounce = false;





    Tank(int x, int y, int vx, int vy, int angle, BufferedImage img) {

        super(x,y, img);
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.health = 30;
        this.lives = 3;
        this.cooldown = 200;
        Map.objects.add(this);

    }

    void toggleUpPressed() { this.UpPressed = true; }

    void toggleDownPressed() { this.DownPressed = true; }

    void toggleRightPressed() { this.RightPressed = true; }

    void toggleLeftPressed() { this.LeftPressed = true; }

    void toggleShotPressed(){ this.ShotPressed = true; }

    void unToggleUpPressed() { this.UpPressed = false; }

    void unToggleDownPressed() { this.DownPressed = false; }

    void unToggleRightPressed() { this.RightPressed = false; }

    void unToggleLeftPressed() { this.LeftPressed = false; }

    void unToggleShotPressed(){ this.ShotPressed = false; }

    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
            updateBounds();
        }
        if (this.DownPressed) {
            this.moveBackwards();
            updateBounds();
        }
        if (this.RightPressed) {
            this.rotateRight();
            updateBounds();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
            updateBounds();

        }


        if (cooldownTimer < this.cooldown) {
            cooldownTimer += 1;
        }

        if (this.ShotPressed){
            this.shoot(bullet);
            updateBounds();
        }

        for (int x = 0; x < bulletsList.size(); x++) {
            if (bulletsList.get(x).isAlive()) {
                bulletsList.get(x).update();
            }
        }

        checkCollision(this);

    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
        updateBounds();
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
        updateBounds();
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
        updateBounds();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
        updateBounds();
    }

    private void shoot(Bullet bullet){

        if (cooldownTimer >= this.cooldown) {
            if (checkFireUp) {
                bulletsList.add(new Bullet(this,x + 15,y + 15,angle));
                sound.clipSound5();
            }
            else {
                bulletsList.add(new Bullet(this,x + 15,y + 15,angle));
                sound.clipSound1();
            }


            if (checkFireBounce) {
                bulletsList.add(new Bullet(this,x + 15,y + 15,angle + 10));
                bulletsList.add(new Bullet(this,x + 15,y + 15,angle));
                bulletsList.add(new Bullet(this,x + 15,y + 15,angle - 10));
            }


            cooldownTimer = 0;
        }
    }

    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= TRE.GAME_WIDTH - 88) {
            x = TRE.GAME_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= TRE.GAME_HEIGHT - 80) {
            y = TRE.GAME_HEIGHT - 80;
        }
    }
    public int getHealth(){
        return health;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + "angle=" + angle + Arrays.toString(bulletsList.toArray());
    }

    public void takeDamage() {
        health -= damage;
        if(health <= 0){
            health = 30;
            lives -=1;
        }
    }

    //get and return lives
    public int getLives(){
        return lives;
    }

    //Winning
    public boolean isWon(){
        if(lives == 0){
            sound.clipSound2();
           // System.exit(1);
            return true;
        }
        return false;
    }


    public Rectangle getBounds(){
        return this.bound;
    }

    public void updateBounds(){
        this.bound = new Rectangle(this.x, this.y, img.getWidth(),img.getHeight());
    }

    public void checkCollision(Tank tank){
        GameObjects obj;
        Rectangle tbound = tank.getBounds();
        for (int i =0; i< Map.objects.size();i++){
            obj = Map.objects.get(i);
            if (tbound.intersects(obj.getBounds())){
                handle(obj);
            }
        }
    }

    public void handle(GameObjects obj){
        if (obj instanceof UnbreakableWall){
            if (this.UpPressed){
                this.x -= vx;
                this.y -= vy;
            }
            if (this.DownPressed){
                this.x += vx;
                this.y += vy;
            }
        }
        if (obj instanceof BreakableWall){
            if (this.UpPressed){
                this.x -= vx;
                this.y -= vy;
            }
            if (this.DownPressed){
                this.x += vx;
                this.y += vy;
            }
        }
        if (obj instanceof HealthPowerUp){
            if(health < 30) {
                sound.clipSound4();
                this.health = 30;
                Map.objects.remove(obj);
            }
        }
        if( obj instanceof SpeedUpPowerUp){
            sound.clipSound4();
            checkFireBounce = true;
            Map.objects.remove(obj);
        }
        if (obj instanceof FirePowerUp){
            sound.clipSound4();
            checkFireUp = true;
            this.cooldown = 30;
            Map.objects.remove(obj);
        }
    }

    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int x = 0; x < bulletsList.size(); x++) {
            if (bulletsList.get(x).isAlive()) {
                bulletsList.get(x).drawImage(g2d);
            }
        }
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        g2d.drawImage(this.img, rotation, null);
    }
}
