package com.ducvt.subleasing.chat.service;

import com.ducvt.subleasing.chat.entities.Message;
import com.ducvt.subleasing.chat.payload.request.GetMessageRequest;
import com.ducvt.subleasing.chat.payload.request.SendMessageRequest;
import com.ducvt.subleasing.chat.payload.response.GetMessageResponse;
import com.ducvt.subleasing.chat.payload.response.SendMessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {
    List<String> getUsersContacted(Long userId);
    GetMessageResponse getMessages(String sender, String receiver);
//    List<Message> getMessages(GetMessageRequest getMessageRequest);
    SendMessageResponse send(SendMessageRequest sendMessageRequest);
}
