package com.akishimama.web.domain;

import com.akishimama.web.utilities.DateTimeUtils;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by sat8bit on 2017/01/02.
 */
@Entity
@Data
public class WhatsNew {
    @GeneratedValue
    @Id
    private Integer whatsNewId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postedAt;

    @Column(length = 32)
    private String title;

    @Column(length = 4096)
    private String content;

    public String getFormattedPostedAt() {
        return DateTimeUtils.DateTimeJapaneseFormatter.format(postedAt);
    }
}
