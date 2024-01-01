package com.ducvt.subleasing.chat.service.impl;

import com.ducvt.subleasing.account.models.User;
import com.ducvt.subleasing.account.repository.UserRepository;
import com.ducvt.subleasing.chat.entities.Message;
import com.ducvt.subleasing.chat.entities.Topic;
import com.ducvt.subleasing.chat.payload.request.GetMessageRequest;
import com.ducvt.subleasing.chat.payload.request.SendMessageRequest;
import com.ducvt.subleasing.chat.payload.response.GetMessageResponse;
import com.ducvt.subleasing.chat.payload.response.SendMessageResponse;
import com.ducvt.subleasing.chat.repositories.MessageRepository;
import com.ducvt.subleasing.chat.repositories.TopicRepository;
import com.ducvt.subleasing.chat.service.ChatService;
import com.ducvt.subleasing.fw.constant.MessageEnum;
import com.ducvt.subleasing.fw.exceptions.BusinessLogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public List<String> getUsersContacted(Long userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        List<Long> contactIds = new ArrayList<>();
        if(userOptional.isPresent()) {
            List<String> contacts = new ArrayList<>();
            String username = userOptional.get().getUsername();
            Optional<List<Topic>> topics = topicRepository.findByNameContains(username);
            if(topics.isPresent()) {
                for(Topic topic : topics.get()) {
                    List<String> names = Arrays.asList(topic.getName().split("-"));
                    for(String name: names) {
                        if(!name.equals(username)) {
                            contacts.add(name);
                        }
                    }
                }
            }
//            for(String name: contacts) {
//                Optional<User> contactOptional = userRepository.findByUsername(name);
//                if(contactOptional.isPresent()) {
//                    contactIds.add(contactOptional.get().getUserId());
//                }
//            }
//            return contactIds;
            return contacts;
        } else {
            throw new BusinessLogicException(MessageEnum.NOT_FOUND_USER_BY_ID.getMessage());
        }
    }

    @Override
    public GetMessageResponse getMessages(String sender, String receiver) {
        GetMessageResponse getMessageResponse = new GetMessageResponse();
        List<SendMessageResponse> messages = new ArrayList<>();
        String topicName = makeTopicName(sender, receiver);
        Optional<Topic> topicOptional = topicRepository.findByName(topicName);
        if(topicOptional.isPresent()) {
            Long topicId = topicOptional.get().getTopicId();
            Optional<List<Message>> optionalMessages = messageRepository.findByTopic(topicId);
            if(optionalMessages.isPresent()) {
                for(Message message : optionalMessages.get()) {
                    SendMessageResponse sendMessageResponse = new SendMessageResponse();
                    sendMessageResponse.setContent(message.getContent());
                    Optional<User> userOptional = userRepository.findByUserId(message.getSenderId());
                    if(userOptional.isPresent()) {
                        sendMessageResponse.setSenderUsername(userOptional.get().getUsername());
                    } else {
                        throw new BusinessLogicException(MessageEnum.NOT_FOUND_USER_BY_ID.getMessage());
                    }
                    sendMessageResponse.setSentTime(message.getCreatedTime());
                    messages.add(sendMessageResponse);
                }
            }
        }
        getMessageResponse.setMessages(messages);
        getMessageResponse.setTopicName("/topic/"+ topicName);
        return getMessageResponse;
    }

    private String makeTopicName(String firstName, String secondName) {
        String[] sortedStrings = {firstName, secondName};
        Arrays.sort(sortedStrings);
        String topicName = sortedStrings[0] + "-" + sortedStrings[1];
        return topicName;
    }

    @Override
    public SendMessageResponse send(SendMessageRequest sendMessageRequest) {
        Optional<User> sender = userRepository.findByUsername(sendMessageRequest.getSender());
        Optional<User> receiver = userRepository.findByUsername(sendMessageRequest.getReceiver());
        if(!sender.isPresent() || !receiver.isPresent()) {
            throw new BusinessLogicException(MessageEnum.NOT_FOUND_USER_BY_ID.getMessage());
        } else {
            // save to db
//            String[] sortedStrings = {sender.get().getUsername(), receiver.get().getUsername()};
//            Arrays.sort(sortedStrings);
//            String topicName = sortedStrings[0] + "-" + sortedStrings[1];
            String topicName = makeTopicName(sender.get().getUsername(), receiver.get().getUsername());
            Optional<Topic> topic = topicRepository.findByName(topicName);
            Long topicId = null;
            if(!topic.isPresent()) {
                Topic newTopic = new Topic();
                newTopic.setName(topicName);
//                newTopic.setOfferId();
                newTopic.setCreatedTime(new Date());
                newTopic.setUpdatedTime(new Date());
                topicRepository.save(newTopic);
                topicId = newTopic.getTopicId();
            } else {
                topicId = topic.get().getTopicId();
            }
            Message message = new Message();
            message.setSenderId(sender.get().getUserId());
            message.setContent(sendMessageRequest.getContent());
            message.setCreatedTime(new Date());
            message.setTopic(topicId);
            messageRepository.save(message);

            SendMessageResponse sendMessageResponse = new SendMessageResponse();
            sendMessageResponse.setContent(message.getContent());
            sendMessageResponse.setSenderUsername(sender.get().getUsername());
            sendMessageResponse.setSentTime(message.getCreatedTime());

            // send by ws
            simpMessagingTemplate.convertAndSend("/topic/" + topicName, sendMessageResponse);

            return sendMessageResponse;
        }
    }
}
