package com.example.demo.webSockets.controller;


import com.example.demo.webSockets.dtos.Greeting;
import com.example.demo.webSockets.dtos.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greet(HelloMessage message){
        return  new Greeting("Hello, "+
                HtmlUtils.htmlEscape(message.getName()));
    }
}
