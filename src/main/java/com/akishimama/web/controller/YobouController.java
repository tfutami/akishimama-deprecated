package com.akishimama.web.controller;

import com.akishimama.web.constraint.ModelAttributeName;
import com.akishimama.web.domain.Hospital;
import com.akishimama.web.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Ri104 on 16/12/18.
 */
@RequestMapping(path = "/yobou")
@Controller
@Slf4j
public class YobouController {

    @Autowired
    private HospitalRepository hospitalRepository;

    @RequestMapping(
            method = RequestMethod.GET
    )
    public String doGet(Model model){

        List<Hospital> hospitalList = hospitalRepository.findAll();
        model.addAttribute(ModelAttributeName.HOSPITAL_LIST, hospitalList);

        return "yobou";
    }
}
