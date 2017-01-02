package com.akishimama.web.controller;

import com.akishimama.web.domain.Hospital;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ri104 on 16/12/18.
 */
@RequestMapping(path = "/yobou/hospital")
@Controller
public class HospitalController {
    @RequestMapping(
            method = RequestMethod.GET
    )
    public String doGet(){

        Hospital hospital = new Hospital();

        return "hospital";
    }
}
