package com.ducvt.subleasing.chat.payload.request;

import lombok.Data;

@Data
public class GetMessageRequest {
    private String sender;
    private String receiver;
}
