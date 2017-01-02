package com.akishimama.web.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class TwitterAuth {
    @Id
    private String twitterId;

    @OneToOne
    private User user;
}
