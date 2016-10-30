
package Tools;

import java.io.File;
import java.io.PrintStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLoader {
    public static Clip loadSound(String name) {
        Clip clip = null;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./res/sounds/" + name));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch (Exception ex) {
            System.out.println("Error with loading sound.");
            ex.printStackTrace();
        }
        return clip;
    }
}

