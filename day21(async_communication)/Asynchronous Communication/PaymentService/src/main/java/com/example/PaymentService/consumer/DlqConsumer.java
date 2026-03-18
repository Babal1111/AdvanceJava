package com.example.PaymentService.consumer;

import com.example.PaymentService.dto.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DlqConsumer {

    @RabbitListener(queues = "payment.dlq")
    public void handelDeadLetter(OrderCreatedEvent event) {
        System.out.println("-------------------");
        System.out.println("[DLQ] Failed message received");
        System.out.println("[DLQ] Order ID : " + event.getOrderId());
        System.out.println("[DLQ] Amount : " + event.getAmount());
        System.out.println("[DLQ] All retries exhausted - needs manual intervention");
        System.out.println("-------------------");
    }
}