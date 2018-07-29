package sample;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    public void playSound(boolean isLooped, String soundUrl, float volume) {
        try {
            File soundFile = new File(soundUrl);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            FloatControl setVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume.setValue(volume);

            if (!isLooped) {
                clip.start();
            } else {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
