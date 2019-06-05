package commands;

import factories.DAOFactory;
import model.dbEntity.Admin;
import org.springframework.ui.Model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CommandClickLogin extends Command {
    @Override
    public String execute(Map<String, String> allRequestParams, Model model, Map<String, String> session) throws SQLException {
        chooseTheTurnstile(allRequestParams, model, station);
        setCurrentTurnstileById(model, station.getCurrentTurnstileId());
        String login = allRequestParams.get("login");
        String password = allRequestParams.get("password");

        session.put("UserID", "superuser");

        Admin admin = (Admin) DAOFactory.getInstance().getDAO("AdminsDAO").getEntity(login, password);

        if(admin != null) {
            List list = DAOFactory.getInstance().getDAO("PassesThroughTurnstilesDAO").getAll();

            model.addAttribute("pass", list);
            return "/db";
        }
        else {
            model.addAttribute("errorMessage", "ERROR: Invalid login or password!");
            model.addAttribute("indicatorImage", "/img/stop.png");
            model.addAttribute("onClickSensor1", "myFunction()");
            model.addAttribute("onClickSensor2", "myFunction()");
            model.addAttribute("onClickSensor3", "myFunction()");

            return "/index";
        }
    }
}
