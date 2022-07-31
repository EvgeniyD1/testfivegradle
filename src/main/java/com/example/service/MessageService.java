package com.example.testfive.service;

import com.example.testfive.dao.MessageRepository;
import com.example.testfive.domain.Message;
import com.example.testfive.domain.MessageRequest;
import com.example.testfive.domain.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    public MessageService(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    public Message createMessageWS(MessageRequest messageRequest){
        User addressee = userService.findByUsername(messageRequest.getSentTo());

        Message message = new Message();
        message.setUser(addressee);
        message.setSentFrom(messageRequest.getSentFrom());
        message.setDate(new Date());
        message.setTitle(messageRequest.getTitle());
        message.setText(messageRequest.getText());

        save(message);
        return message;
    }

    public void save(Message message){
        messageRepository.save(message);
    }
}
