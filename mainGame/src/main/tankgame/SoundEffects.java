package tankrotationexample.tankgame;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffects {


    private static Clip clip = null;
    private static Clip clip2 = null;
    private static Clip clip3 = null;
    private static Clip clip4 = null;



    static {
        File file = new File("resources/Explosion_small.wav");
        File file2 = new File("resources/Explosion_large.wav");
        File file3 = new File("resources/Music.wav");
        File file4 = new File("resources/pickup.wav");



        AudioInputStream audioStream = null;
        AudioInputStream audioStream2 = null;
        AudioInputStream audioStream3 = null;
        AudioInputStream audioStream4 = null;




        try {
            audioStream = AudioSystem.getAudioInputStream(file);
            audioStream2 = AudioSystem.getAudioInputStream(file2);
            audioStream3 = AudioSystem.getAudioInputStream(file3);
            audioStream4 = AudioSystem.getAudioInputStream(file4);




        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

        try {
            clip = AudioSystem.getClip();
            clip2 = AudioSystem.getClip();
            clip3 = AudioSystem.getClip();
            clip4 = AudioSystem.getClip();



            clip.open(audioStream);
            clip2.open(audioStream2);
            clip3.open(audioStream3);
            clip4.open(audioStream4);



        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void clipSound1() {
        clip.setFramePosition(0);
        clip.start();
    }

    public static void clipSound2() {
        clip2.setFramePosition(0);
        clip2.start();
    }

    public static void clipSound3() {

        clip3.start();
    }

    public static void clipSound3STOP() {

        clip3.stop();
    }

    public static void clipSound4() {
        clip4.setFramePosition(0);
        clip4.start();
    }

    public static void clipSound5() {
        FloatControl soundControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        soundControl.setValue(-10.0f);
        clip.setFramePosition(0);
        clip.start();
    }








}
