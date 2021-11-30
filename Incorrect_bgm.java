import javax.sound.sampled.*;
import java.io.File;

public class Incorrect_bgm extends Thread {
    @Override
    public void run() {
        File incorrect;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;

        incorrect = new File("music/incorrect.wav");

        Clip clip;

        try {
            stream = AudioSystem.getAudioInputStream(incorrect);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            System.out.println("err : " + e);
        }
    }
}