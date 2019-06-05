package commands;

import model.Station;
import org.springframework.ui.Model;

import java.sql.SQLException;
import java.util.Map;

public abstract class Command {
    Station station;

    public Command() {
        station = Station.getInstance();
    }

    public abstract String execute(Map<String, String> allRequestParams, Model model, Map<String, String> session) throws SQLException, InterruptedException;

    Model blockButtons(Model model) {
        model.addAttribute("disabledButton", "disabled");
        return model;
    }

    void disableSensor(Model model, int id) {
        switch (id) {
            case 1: {
                model.addAttribute("disabledSensor1", "disabled");
                break;
            }
            case 2: {
                model.addAttribute("disabledSensor2", "disabled");
                break;
            }
            case 3: {
                model.addAttribute("disabledSensor3", "disabled");
                break;
            }
        }
    }

    void setCurrentTurnstileById(Model model, int idOfTurnstile) {
        //clearSelectForm(model);
        switch (idOfTurnstile) {
            case 0: {
                model.addAttribute("selectedTurnstile0", true);
                break;
            }
            case 1: {
                model.addAttribute("selectedTurnstile1", true);
                break;
            }
            case 2: {
                model.addAttribute("selectedTurnstile2", true);
                break;
            }
            case 3: {
                model.addAttribute("selectedTurnstile3", true);
                break;
            }
            case 4: {
                model.addAttribute("selectedTurnstile4", true);
                break;
            }
        }
    }

    Model changeIndicatorState(Model model, String state) {
        switch (state) {
            case "PASS": {
                blockButtons(model);

                model.addAttribute("sensor1Color", "myClass");
                model.addAttribute("sensor2Color", "myClass");
                model.addAttribute("sensor3Color", "myClass");

                model.addAttribute("indicatorImage", "/img/pass.png");
                break;
            }
            case "STOP": {
                model.addAttribute("sensor1Color", "myClass");
                model.addAttribute("sensor2Color", "myClass");
                model.addAttribute("sensor3Color", "myClass");

                model.addAttribute("errorSensor", "ERROR: Access denied!");
                model.addAttribute("strForWarning", "ERROR: Invalid card!");
                model.addAttribute("onClickIndicator", "myFunction()");
                model.addAttribute("indicatorImage", "/img/stop.png");

                break;
            }
        }
        return model;
    }

    void chooseTheTurnstile(Map<String, String> allRequestParams, Model model, Station station) {
        if (allRequestParams.get("selectTurnstile") != null) {
            switch (allRequestParams.get("selectTurnstile")) {
                case "turnstile0": {
                    model.addAttribute("selectedTurnstile0", true);
                    station.setCurrentTurnstileId(0);
                    break;
                }
                case "turnstile1": {
                    model.addAttribute("selectedTurnstile1", true);
                    station.setCurrentTurnstileId(1);
                    break;
                }
                case "turnstile2": {
                    model.addAttribute("selectedTurnstile2", true);
                    station.setCurrentTurnstileId(2);
                    break;
                }
                case "turnstile3": {
                    model.addAttribute("selectedTurnstile3", true);
                    station.setCurrentTurnstileId(3);
                    break;
                }
                case "turnstile4": {
                    model.addAttribute("selectedTurnstile4", true);
                    station.setCurrentTurnstileId(4);
                    break;
                }
            }
        }
    }
}
