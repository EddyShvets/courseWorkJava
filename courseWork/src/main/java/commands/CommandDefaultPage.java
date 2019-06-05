package commands;

import org.springframework.ui.Model;

import java.util.Map;

public class CommandDefaultPage extends Command {
    @Override
    public String execute(Map<String, String> allRequestParams, Model model, Map<String, String> session) {
        chooseTheTurnstile(allRequestParams, model, station);
        setCurrentTurnstileById(model, station.getCurrentTurnstileId());
        model.addAttribute("indicatorImage", "/img/stop.png");
        model.addAttribute("errorSensor", "");

        model.addAttribute("sensor1Color", "myClass");
        model.addAttribute("sensor2Color", "myClass");
        model.addAttribute("sensor3Color", "myClass");

        return "/index";
    }
}
