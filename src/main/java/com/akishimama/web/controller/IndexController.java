package com.akishimama.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping(path = "")
@Controller
public class IndexController {

    @RequestMapping(
            method = RequestMethod.GET
    )
    public String doGet(
            HttpServletRequest request,
            Model model
    ) {
        return "index";
    }
}
