import javax.sound.sampled.*;
import java.io.File;

public class Correct_bgm extends Thread {
    @Override
    public void run() {
        File correct;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;

        correct = new File("music/correct.wav");

        Clip clip;

        try {
            stream = AudioSystem.getAudioInputStream(correct);
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