package com.example.testfive.controller;

import com.example.testfive.domain.Message;
import com.example.testfive.domain.MessageRequest;
import com.example.testfive.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    public MessageController(SimpMessagingTemplate messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/send")
//    @SendTo("/topic/to/")
    public void create(MessageRequest messageRequest) {
        Message messageWS = messageService.createMessageWS(messageRequest);
        messagingTemplate.convertAndSend("/topic/to/".concat(messageRequest.getSentTo()), messageWS);
    }
}
