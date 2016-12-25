package com.akishimama.web.controller;

import com.akishimama.constraint.ModelAttributeName;
import com.akishimama.domain.Session;
import com.akishimama.web.SessionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sat8bit on 2016/12/17.
 */
@Slf4j
@RequestMapping(path = "")
@Controller
public class IndexController {

    @Autowired
    private SessionHandler sessionHandler;

    @RequestMapping(
            method = RequestMethod.GET
    )
    public String doGet(
            HttpServletRequest request,
            Model model
    ) {
        Session session = sessionHandler.getSession(request);
        model.addAttribute(ModelAttributeName.USER, session.getUser());

        log.info("session is {}", session);
        log.info("session user is {}", session.getUser());

        return "index";
    }
}
