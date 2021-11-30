import javax.sound.sampled.*;
import java.io.File;


public class BGM extends Thread {
    @Override
    public void run() {
        File backmusic;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;

        backmusic = new File("music/backmusic.wav");
        Clip clip;

            try {
                    stream = AudioSystem.getAudioInputStream(backmusic);
                    format = stream.getFormat();
                    info = new DataLine.Info(Clip.class, format);
                    clip = (Clip) AudioSystem.getLine(info);
                    clip.open(stream);
                    clip.start();
                    clip.loop(10);
            } catch (Exception e) {
                System.out.println("err : " + e);
            }
        }
    }



