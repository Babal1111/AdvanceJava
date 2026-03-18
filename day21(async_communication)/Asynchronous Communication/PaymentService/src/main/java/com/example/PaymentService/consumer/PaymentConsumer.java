package com.example.PaymentService.consumer;

import com.example.PaymentService.dto.OrderCreatedEvent;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    @RabbitListener(queues = "order.india.queue")
    public void handleIndiaOrder(OrderCreatedEvent event) {
        System.out.println("[TOPIC - INDIA] Received Order: " + event.getOrderId());
        System.out.println("[TOPIC - INDIA] Amount: " + event.getAmount());
        System.out.println("[TOPIC - INDIA] Applying India regional processing...");
    }

    @RabbitListener(queues = "order.usa.queue")  // Corrected to listen to USA queue
    public void handleUsaOrder(OrderCreatedEvent event) {
        System.out.println("[TOPIC - USA] Received Order: " + event.getOrderId());
        System.out.println("[TOPIC - USA] Amount: " + event.getAmount());
        System.out.println("[TOPIC - USA] Applying USA regional processing...");
    }

    @RabbitListener(queues = "payment.queue")
    @Retryable(
            retryFor = Exception.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public void processPayment(OrderCreatedEvent event) {
        System.out.println("[DIRECT] Processing payment for order: " + event.getOrderId());
        System.out.println("[DIRECT] Amount: " + event.getAmount());

        if (event.getAmount() == -1) {
            System.out.println("[Retry] Payment failed for order: " + event.getOrderId() + " - retrying...");
            throw new RuntimeException("Payment failed - invalid amount for order: " + event.getOrderId());
        }
    }

    @Recover
    public void recover(RuntimeException ex, OrderCreatedEvent event) {
        System.out.println("-------------------");
        System.out.println("[RECOVER] All retry attempts failed for order: " + event.getOrderId());
        System.out.println("[RECOVER] Reason: " + ex.getMessage());
        System.out.println("[RECOVER] Forwarding to DLQ...");
        System.out.println("-------------------");

        // Send the message to the DLQ after retry failure
        throw new AmqpRejectAndDontRequeueException(ex);  // This will move the message to the DLQ
    }
}