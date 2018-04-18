package com.big1712.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitExchangeConfig{
	
    //直连交换机
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(RabbitConstant.EXCHANGE_NAME); 
    }

    //队列
    @Bean
    public Queue queue() {
        return QueueBuilder.durable(RabbitConstant.QUEUE_NAME).build();
    }

    //绑定
    @Bean
    public Binding binding() {
    	
        return BindingBuilder.bind(this.queue()).to(this.defaultExchange()).with(RabbitConstant.ROUTING_KEY);
    }

}