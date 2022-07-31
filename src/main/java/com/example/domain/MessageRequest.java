package com.example.testfive.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageRequest implements Serializable {

    private String sentFrom;
    private String title;
    private String text;
    private String sentTo;
}
