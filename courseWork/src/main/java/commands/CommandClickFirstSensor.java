package commands;

import org.springframework.ui.Model;

import java.util.Map;

public class CommandClickFirstSensor extends Command {
    @Override
    public String execute(Map<String, String> allRequestParams, Model model, Map<String, String> session) throws InterruptedException {
        chooseTheTurnstile(allRequestParams, model, station);
        setCurrentTurnstileById(model, station.getCurrentTurnstileId());
        if(station.getCurrentTurnstile().getIndicator().getIndicatorString().equals("PASS")) {
            model.addAttribute("onClickSensor1", "");
            model.addAttribute("sensor1Color", "myClassDisable");
            disableSensor(model, 2);
            disableSensor(model, 3);
            model.addAttribute("indicatorImage", "/img/pass.png");
            blockButtons(model);

            station.proccessSensor1(model);

            model.addAttribute("sensor1Color", "myClass");
            model.addAttribute("sensor2Color", "myClass");
            model.addAttribute("sensor3Color", "myClass");

            model.addAttribute("onClickSensor1", "");
            model.addAttribute("sensor1Color", "myClassDisable");
            disableSensor(model, 1);
            disableSensor(model, 2);
            disableSensor(model, 3);

            if(station.getCurrentTurnstile().getIndicator().getIndicatorString().equals("STOP")) {
                model.addAttribute("indicatorImage", "/img/stop.png");
            }
//
//            model.addAttribute("onClickSensor1", "myFunction()");
//            model.addAttribute("onClickSensor2", "myFunction()");
//            model.addAttribute("onClickSensor3", "myFunction()");

            station.getCurrentTurnstile().getTimer().countTime(5);
            model.addAttribute("refreshSeconds", 5);


            station.getCurrentTurnstile().getIndicator().setIndicatorString("STOP");

        } else {
            model.addAttribute("sensor1Color", "myClass");
            model.addAttribute("sensor2Color", "myClass");
            model.addAttribute("sensor3Color", "myClass");


            model.addAttribute("errorSensor", "ERROR: Access denied!");
            station.getCurrentTurnstile().getDeviceForBlockingAccess().blockAccess(model);
            station.getCurrentTurnstile().getDeviceForSoundSignaling().playWarningSound(model);
        }
        return "/index";
    }
}
