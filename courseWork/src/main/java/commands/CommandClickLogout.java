package commands;

import org.springframework.ui.Model;

import java.util.Map;

public class CommandClickLogout extends Command {
    @Override
    public String execute(Map<String, String> allRequestParams, Model model, Map<String, String> session) {
        chooseTheTurnstile(allRequestParams, model, station);
        setCurrentTurnstileById(model, station.getCurrentTurnstileId());
        //chooseTheTurnstile(request, station);
        //request.getSession().invalidate();
        session.remove("UserID");

        model.addAttribute("indicatorImage", "/img/stop.png");
        model.addAttribute("onClickSensor1", "myFunction()");
        model.addAttribute("onClickSensor2", "myFunction()");
        model.addAttribute("onClickSensor3", "myFunction()");

        return "redirect:/myUrl";
    }
}
