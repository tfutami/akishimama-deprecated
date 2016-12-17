package com.akishimama.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sat8bit on 2016/12/17.
 */
@RequestMapping(path = "")
@Controller
public class IndexController {
    @RequestMapping(
            method = RequestMethod.GET
    )
    public String doGet() {
        return "index";
    }
}
