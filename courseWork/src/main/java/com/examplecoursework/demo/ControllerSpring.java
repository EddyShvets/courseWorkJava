package com.examplecoursework.demo;

import commands.Command;
import factories.CommandsFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.DefaultSessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("UserID")
public class ControllerSpring {
    @RequestMapping(value = "/myUrl", method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDeviceRequestsHandler(@RequestParam Map<String, String> allRequestParams, Model model) throws SQLException, InterruptedException {
        Command command;

        if (allRequestParams.get("command") != null) {
            command = CommandsFactory.getInstance().getCommand(allRequestParams.get("command"));
        } else {
            command = CommandsFactory.getInstance().getCommand("default");
        }

        Map<String, String> session = new HashMap<>();

        String execute = command.execute(allRequestParams, model, session);

        if (session.get("UserID") != null) {
            model.addAttribute("UserID", session.get("UserID"));
        }

        return execute;
    }

    @RequestMapping(value = "/adminUrl", method = {RequestMethod.GET, RequestMethod.POST})
    public String logSession(@RequestParam Map<String, String> allRequestParams,
                             Model model,
                             @SessionAttribute("UserID") String userID,
                             WebRequest request,
                             DefaultSessionAttributeStore store,
                             SessionStatus status) throws SQLException, InterruptedException {
        Command command;

        if (allRequestParams.get("command") != null) {
            command = CommandsFactory.getInstance().getCommand(allRequestParams.get("command"));
        } else {
            command = CommandsFactory.getInstance().getCommand("default");
        }

        Map<String, String> session = new HashMap<>();

        session.put("UserID", userID);

        String execute = command.execute(allRequestParams, model, session);

        if (session.get("UserID") == null) {
            status.setComplete();
            store.cleanupAttribute(request, "UserID");
        }

        return execute;
    }
}
