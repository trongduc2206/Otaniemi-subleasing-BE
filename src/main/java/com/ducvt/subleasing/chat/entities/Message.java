package com.ducvt.subleasing.chat.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private Long senderId;

    private String content;

    private Long topic;

    private Date createdTime;
}
