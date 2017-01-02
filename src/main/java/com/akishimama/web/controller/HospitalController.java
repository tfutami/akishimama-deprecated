package com.akishimama.web.controller;

import com.akishimama.web.constraint.ModelAttributeName;
import com.akishimama.web.domain.Hospital;
import com.akishimama.web.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ri104 on 16/12/18.
 */
@RequestMapping(path = "/yobou/hospital")
@Controller
@Slf4j
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepository;

    @RequestMapping(
            value = "/{hospitalId}",
            method = RequestMethod.GET
    )
    public String doGet(
            @PathVariable("hospitalId") String hospitalId,
            Model model
    ){

        log.info("hospitalId is {}", hospitalId);

        Hospital hospital = hospitalRepository.findOne(Integer.valueOf(hospitalId));
        log.info("hospital is {}", hospital.getName());

        model.addAttribute(ModelAttributeName.HOSPITAL, hospital);

        return "hospital";
    }
}
