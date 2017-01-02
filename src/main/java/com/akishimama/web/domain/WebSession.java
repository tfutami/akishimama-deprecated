package com.akishimama.web.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class WebSession {
    @Id
    @Column(length = 16)
    private String sessionId;

    @OneToOne
    private User user;
}
