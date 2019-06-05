package model.turnstileSensors;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.TargetDataLine;

public class DeviceForSoundSignaling extends TurnstileSensor {
    TargetDataLine warningSound;

    public DeviceForSoundSignaling(int idOfTurnstile, TargetDataLine targetDataLine) {
    }

    public void playWarningSound(Model model) {
        System.out.println("sound");
        model.addAttribute("bell", "img/bellOn.png");
        //Toolkit.getDefaultToolkit().beep();
    }
}
