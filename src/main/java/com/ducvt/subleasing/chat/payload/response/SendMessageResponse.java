package com.ducvt.subleasing.chat.payload.response;

import lombok.Data;

import java.util.Date;

@Data
public class SendMessageResponse {
    private String senderUsername;
    private String content;
    private Date sentTime;
}
