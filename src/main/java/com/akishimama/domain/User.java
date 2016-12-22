package com.akishimama.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sat8bit on 2016/12/18.
 */
@Data
@Entity
public class User {
    @GeneratedValue
    @Id
    private String id;

    private String nickname;
}
