package com.example.PaymentService.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // ===================== DIRECT EXCHANGE =====================

    public static final String DIRECT_EXCHANGE = "order.direct.exchange";
    public static final String PAYMENT_QUEUE = "payment.queue";
    public static final String DIRECT_ROUTING_KEY = "order.created";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Queue paymentQueue() {
        return QueueBuilder.durable(PAYMENT_QUEUE)
                .withArgument("x-dead-letter-exchange", DLQ_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DLQ_ROUTING)
                .build();
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder
                .bind(paymentQueue())
                .to(directExchange())
                .with(DIRECT_ROUTING_KEY);
    }

    // ===================== TOPIC EXCHANGE =====================

    public static final String TOPIC_EXCHANGE = "order.topic.exchange";
    public static final String ORDER_INDIA_QUEUE = "order.india.queue";
    public static final String ORDER_USA_QUEUE = "order.usa.queue";
    public static final String TOPIC_ROUTING_INDIA = "order.india";
    public static final String TOPIC_ROUTING_USA = "order.usa";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Queue orderIndiaQueue() {
        return new Queue(ORDER_INDIA_QUEUE);
    }

    @Bean
    public Queue orderUsaQueue() {
        return new Queue(ORDER_USA_QUEUE);
    }

    @Bean
    public Binding topicIndiaBinding() {
        return BindingBuilder
                .bind(orderIndiaQueue())
                .to(topicExchange())
                .with(TOPIC_ROUTING_INDIA);
    }

    @Bean
    public Binding topicUsaBinding() {
        return BindingBuilder
                .bind(orderUsaQueue())
                .to(topicExchange())
                .with(TOPIC_ROUTING_USA);
    }

    // ===================== DEAD LETTER EXCHANGE (DLX) =====================

    public static final String DLQ_EXCHANGE = "dlx.exchange";
    public static final String DLQ_QUEUE = "payment.dlq";
    public static final String DLQ_ROUTING = "dlq.routing";

    @Bean
    public DirectExchange dlqExchange() {
        return new DirectExchange(DLQ_EXCHANGE);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DLQ_QUEUE);
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder
                .bind(deadLetterQueue())
                .to(dlqExchange())
                .with(DLQ_ROUTING);
    }

    // ===================== COMMON =====================

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}