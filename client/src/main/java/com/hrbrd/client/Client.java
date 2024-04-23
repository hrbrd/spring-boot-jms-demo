package com.hrbrd.client;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Client {
    private final RabbitTemplate rabbitTemplate;

    public Client(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/get")
    public String getMessage() {
        Object message = rabbitTemplate.receiveAndConvert("queuetest");
        return message.toString();
    }

    @RabbitListener(queues = "queuetest")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }

}