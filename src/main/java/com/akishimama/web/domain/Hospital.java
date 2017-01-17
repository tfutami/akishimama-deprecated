package com.akishimama.web.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sat8bit on 2016/12/26.
 */
@Data
@Entity
public class Hospital {
    @Id
    private Integer hospitalId;

    private String name;

    private String address;

    private Boolean reservation;

    @Column(length = 4096)
    private String info;

    @Column(length = 4096)
    private String vaccineInfo;

    @Column(length = 4096)
    private String doctorsInfo;

    @ManyToMany
    private List<Vaccine> vaccineList;
}