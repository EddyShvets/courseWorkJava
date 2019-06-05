package commands;

import org.springframework.ui.Model;

import java.util.Map;

public class CommandClickFirstButton extends Command {
    @Override
    public String execute(Map<String, String> allRequestParams, Model model, Map<String, String> session) {
        chooseTheTurnstile(allRequestParams, model, station);
        setCurrentTurnstileById(model, station.getCurrentTurnstileId());
        changeIndicatorState(model, station.proccessButton1());

        return "/index";
    }
}
