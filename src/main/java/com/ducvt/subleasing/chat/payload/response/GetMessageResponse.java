package com.ducvt.subleasing.chat.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class GetMessageResponse {
    private List<SendMessageResponse> messages;
    private String topicName;
}
