package com.ducvt.subleasing.chat.controllers;

import com.ducvt.subleasing.chat.payload.request.GetMessageRequest;
import com.ducvt.subleasing.chat.payload.request.SendMessageRequest;
import com.ducvt.subleasing.chat.service.ChatService;
import com.ducvt.subleasing.fw.utils.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChatController {
//    @Autowired
//    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    ChatService chatService;

    @PostMapping(value = "/send")
    public ResponseEntity send(@RequestBody SendMessageRequest messageRequest){
//        simpMessagingTemplate.convertAndSend(messageRequest.getTopic(), messageRequest);
        return ResponseFactory.success(chatService.send(messageRequest));
    }

    @GetMapping(value = "/contact/{id}")
    public ResponseEntity getContact(@PathVariable Long id) {
        return ResponseFactory.success(chatService.getUsersContacted(id));
    }

    @GetMapping("/messages")
    public ResponseEntity getMessages(@RequestParam String sender, @RequestParam String receiver) {
        return ResponseFactory.success(chatService.getMessages(sender, receiver));
    }
}
