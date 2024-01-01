package com.ducvt.subleasing.chat.payload.request;

import lombok.Data;

@Data
public class SendMessageRequest {
    private String sender;
    private String receiver;
    private String content;
}
