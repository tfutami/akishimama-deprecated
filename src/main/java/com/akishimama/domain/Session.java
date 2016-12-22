package com.akishimama.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Session {
    @Id
    private String sessionId;

    @OneToOne
    private User user;
}
