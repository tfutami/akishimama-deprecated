package com.akishimama.web.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by Ri104 on 17/01/02.
 */
@Data
@Entity
public class Vaccine {
    @Id
    private Integer vaccineId;

    private String name;

    @ManyToMany(mappedBy = "vaccineList")
    private List<Hospital> hospitalList;

}
