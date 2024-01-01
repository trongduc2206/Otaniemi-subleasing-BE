package com.ducvt.subleasing.chat.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "topic")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    private Long offerId;

    private String name;

    private Date createdTime;

    private Date updatedTime;
}
