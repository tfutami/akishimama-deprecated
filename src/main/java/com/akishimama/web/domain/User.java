package com.akishimama.web.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Created by sat8bit on 2016/12/18.
 */
@Data
@Entity
public class User {
    @GeneratedValue
    @Id
    private Integer userId;

    @Column(length = 16)
    private String nickname;
}
