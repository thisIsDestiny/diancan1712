package com.big1712.rabbitmq.test.demo;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class WorkTest {
	@Test
	public void send() throws Exception{
		//获取连接
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		//声明队列
		//chan.queueDeclare("work1", false, false, false, null);
		//chan.queueDeclare("work2", false, false, false, null);
		for(int i=0;i<100;i++){
			String msg="1712,hello:"+i+"message";
			chan.basicPublish("", "work123", null, msg.getBytes());
			System.out.println("第"+i+"条信息已经发送");
		}
		chan.close();
		conn.close();
	}
	@Test
	public void receive1() throws Exception{
		//获取连接,获取信道
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		chan.queueDeclare("work", false, false, false, null);
		//同一时刻服务器只发送一条消息给同一消费者,消费者空闲,才发送一条
		chan.basicQos(1);
		//定义消费者
		QueueingConsumer consumer=new QueueingConsumer(chan);
		//绑定队列和消费者的关系
		//queue
		//autoAck:消息被消费后,是否自动确认回执,如果false,不自动需要手动在
		//完成消息消费后进行回执确认,channel.ack,channel.nack
		//callback
		//chan.basicConsume(queue, autoAck, callback)
		chan.basicConsume("work", false, consumer);
		//监听
		while(true){
			System.out.println("1");
			Delivery delivery=consumer.nextDelivery();
			
			System.out.println("2");
			byte[] result = delivery.getBody();
			
			System.out.println("3");
			String msg=new String(result);
			System.out.println("接受到:"+msg);
			
			Thread.sleep(50);
			//返回服务器,回执
			//chan.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			System.out.println("ack");
		}	
	}
	@Test
	public void receive2() throws Exception{
		//获取连接,获取信道
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		//chan.queueDeclare("work3", false, false, false, null);
		//同一时刻服务器只发送一条消息给同一消费者,消费者空闲,才发送一条
		chan.basicQos(1);
		//定义消费者
		QueueingConsumer consumer=new QueueingConsumer(chan);
		//绑定队列和消费者的关系
		//queue
		//autoAck:消息被消费后,是否自动确认回执,如果false,不自动需要手动在
		//完成消息消费后进行回执确认,channel.ack,channel.nack
		//callback
		//chan.basicConsume(queue, autoAck, callback)
		chan.basicConsume("work", false, consumer);
		//监听
		while(true){
			Delivery delivery=consumer.nextDelivery();
			byte[] result = delivery.getBody();
			String msg=new String(result);
			System.out.println("接受到:"+msg);
			Thread.sleep(150);
			//返回服务器,回执
			chan.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
	
}
