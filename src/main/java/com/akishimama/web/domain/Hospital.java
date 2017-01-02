package com.akishimama.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sat8bit on 2016/12/26.
 */
@Entity
public class Hospital {
    @GeneratedValue
    @Id
    private Integer hospitalId;

    private String name;

    private String address;
}