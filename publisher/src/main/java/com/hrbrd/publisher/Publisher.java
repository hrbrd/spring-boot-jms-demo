package com.hrbrd.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Publisher {
    private final RabbitTemplate rabbitTemplate;

    public Publisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/add")
    public String addMessage(@RequestParam String message){
        rabbitTemplate.convertAndSend("queuetest", message);
        return "ok";
    }

}