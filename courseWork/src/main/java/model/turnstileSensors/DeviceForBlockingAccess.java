package model.turnstileSensors;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public class DeviceForBlockingAccess extends TurnstileSensor {
    int idOfTurnstile;

    public DeviceForBlockingAccess(int idOfTurnstile) {
        this.idOfTurnstile = idOfTurnstile;
    }

    public boolean blockAccess(Model model) {
        model.addAttribute("indicatorImage", "img/stop.png");
        model.addAttribute("onClickSensor1", "myFunction()");
        model.addAttribute("onClickSensor2", "myFunction()");
        model.addAttribute("onClickSensor3", "myFunction()");
        return false;
    }
}
