package com.akishimama.web.controller;

import com.akishimama.web.constraint.ModelAttributeName;
import com.akishimama.web.repository.WhatsNewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping(path = "")
@Controller
public class IndexController {

    @Autowired
    private WhatsNewRepository whatsNewRepository;

    @RequestMapping(
            method = RequestMethod.GET
    )
    public String doGet(
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute(
                ModelAttributeName.WHATS_NEW_LIST,
                whatsNewRepository.findTop10ByOrderByPostedAtDesc());
        return "index";
    }
}
