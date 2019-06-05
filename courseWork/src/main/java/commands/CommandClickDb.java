package commands;

import org.springframework.ui.Model;

import java.sql.SQLException;
import java.util.Map;

public class CommandClickDb extends Command {
    @Override
    public String execute(Map<String, String> allRequestParams, Model model, Map<String, String> session) throws SQLException {
        chooseTheTurnstile(allRequestParams, model, station);
        setCurrentTurnstileById(model, station.getCurrentTurnstileId());
        station.postToDb();

        model.addAttribute("indicatorImage", "/img/stop.png");
        model.addAttribute("sensor1Color", "myClass");
        model.addAttribute("sensor2Color", "myClass");
        model.addAttribute("sensor3Color", "myClass");

        model.addAttribute("onClickSensor1", "myFunction()");
        model.addAttribute("onClickSensor2", "myFunction()");
        model.addAttribute("onClickSensor3", "myFunction()");
        return "/index";
    }
}
