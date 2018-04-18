package com.big1712.rabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.big1712.rabbitmq.config.RabbitConstant;

@Controller
public class TestController {

	@Autowired
	private RabbitTemplate rabbit;

	@RequestMapping("/test")
	public void test(){

		rabbit.convertAndSend(RabbitConstant.EXCHANGE_NAME,
				RabbitConstant.ROUTING_KEY,"test");

	}

	@RabbitListener(queues=RabbitConstant.QUEUE_NAME)
	public void process01(Object info) throws Exception{
		
		System.out.println("接收到数据:"+info);
		
	}

}
