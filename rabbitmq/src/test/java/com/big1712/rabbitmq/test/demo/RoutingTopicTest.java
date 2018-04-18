package com.big1712.rabbitmq.test.demo;

import java.io.IOException;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class RoutingTopicTest {
	
	@Test
	public void routingSend() throws Exception{
		//获取连接
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		//声明交换机
		//参数意义,1 交换机名称,2 类型:fanout,direct,topic
		chan.exchangeDeclare("directEx", "direct");
		//发送消息
		String msg="路由模式的消息";
		chan.basicPublish("directEx", "jt1713", 
				null, msg.getBytes());
	}
	
	@Test
	public void routingRec01() throws Exception{
		System.out.println("一号消费者等待接收消息");
		//获取连接
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		//声明队列
		chan.queueDeclare("direct01", false, false, false, null);
		//声明交换机
		chan.exchangeDeclare("directEx", "direct");
		//绑定队列到交换机
		//参数 1 队列名称,2 交换机名称 3 路由key
		chan.queueBind("direct01", "directEx", "jt1712");
		chan.basicQos(1);
		//定义消费者
		QueueingConsumer consumer=new QueueingConsumer(chan);
		//消费者与队列绑定
		chan.basicConsume("direct01",false, consumer);
		while(true){
			Delivery delivery= consumer.nextDelivery();
			System.out.println("一号消费者接收到"+
			new String(delivery.getBody()));
			chan.basicAck(delivery.getEnvelope().
					getDeliveryTag(), false);
		}
	}
	@Test
	public void routingRec02() throws Exception{
		System.out.println("二号消费者等待接收消息");
		//获取连接
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		//声明队列
		chan.queueDeclare("direct02", false, false, false, null);
		//声明交换机
		chan.exchangeDeclare("directEx", "direct");
		//绑定队列到交换机
		//参数 1 队列名称,2 交换机名称 3 路由key
		chan.queueBind("direct02", "directEx", "jt1711");
		chan.basicQos(1);
		//定义消费者
		QueueingConsumer consumer=new QueueingConsumer(chan);
		//消费者与队列绑定
		chan.basicConsume("direct02",false, consumer);
		while(true){
			Delivery delivery= consumer.nextDelivery();
			System.out.println("二号消费者接收到"+
			new String(delivery.getBody()));
			chan.basicAck(delivery.getEnvelope().
					getDeliveryTag(), false);
		}
	}
	
	@Test
	public void topicSend() throws Exception{
		//获取连接
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		//声明交换机
		//参数意义,1 交换机名称,2 类型:fanout,direct,topic
		chan.exchangeDeclare("topicEx", "topic");
		//发送消息
		String msg="主题模式的消息";
		chan.basicPublish("topicEx", "jt1712.add.update", 
				null, msg.getBytes());
	}
	@Test
	public void topicRec01() throws Exception{
		System.out.println("一号消费者等待接收消息");
		//获取连接
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		//声明队列
		chan.queueDeclare("topic01", false, false, false, null);
		//声明交换机
		chan.exchangeDeclare("topicEx", "topic");
		//绑定队列到交换机
		//参数 1 队列名称,2 交换机名称 3 路由key
		chan.queueBind("topic01", "topicEx", "jt1712");
		chan.basicQos(1);
		//定义消费者
		QueueingConsumer consumer=new QueueingConsumer(chan);
		//消费者与队列绑定
		chan.basicConsume("topic01",false, consumer);
		while(true){
			Delivery delivery= consumer.nextDelivery();
			System.out.println("一号消费者接收到"+
			new String(delivery.getBody()));
			chan.basicAck(delivery.getEnvelope().
					getDeliveryTag(), false);
		}
	}
	@Test
	public void topicRec02() throws Exception{
		System.out.println("二号消费者等待接收消息");
		//获取连接
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		//声明队列
		chan.queueDeclare("topic02", false, false, false, null);
		//声明交换机
		chan.exchangeDeclare("topicEx", "topic");
		//绑定队列到交换机
		//参数 1 队列名称,2 交换机名称 3 路由key
		chan.queueBind("topic02", "topicEx", "jt1712.#");
		chan.basicQos(1);
		//定义消费者
		QueueingConsumer consumer=new QueueingConsumer(chan);
		//消费者与队列绑定
		chan.basicConsume("topic02",false, consumer);
		while(true){
			Delivery delivery= consumer.nextDelivery();
			System.out.println("二号消费者接收到"+
			new String(delivery.getBody()));
			chan.basicAck(delivery.getEnvelope().
					getDeliveryTag(), false);
		}
	}
}






















