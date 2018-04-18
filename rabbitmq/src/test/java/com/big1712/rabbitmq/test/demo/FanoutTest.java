package com.big1712.rabbitmq.test.demo;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class FanoutTest {
	//交换机,有类型,发布订阅:fanout
	//路由模式:direct
	//主题模式:topic
	@Test
	public void send() throws Exception {
		
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		//声明交换机
		//参数意义,1 交换机名称,2 类型:fanout,direct,topic
		chan.exchangeDeclare("fanoutEx", "fanout");
		//发送消息
		for(int i=0;i<100;i++){
			String msg="1712 hello:"+i+"msg";
			chan.basicPublish("fanoutEx", "", null, msg.getBytes());
			System.out.println("第"+i+"条信息已经发送");
		}
	}
	
	@Test
	public void receiv01() throws Exception{
		//获取连接
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		//生命队列
		chan.queueDeclare("fanout01", false, false, false, null);
		//声明交换机
		chan.exchangeDeclare("fanoutEx", "fanout");
		//绑定队列到交换机
		//参数 1 队列名称,2 交换机名称 3 路由key
		chan.queueBind("fanout01", "fanoutEx", "");
		chan.basicQos(1);
		//定义消费者
		QueueingConsumer consumer=new QueueingConsumer(chan);
		//消费者与队列绑定
		chan.basicConsume("fanout01",false, consumer);
		while(true){
			Delivery delivery= consumer.nextDelivery();
			System.out.println("一号消费者接收到"+
			new String(delivery.getBody()));
			chan.basicAck(delivery.getEnvelope().
					getDeliveryTag(), false);
		}
	}
	@Test
	public void receiv02() throws Exception{
		//获取连接
		Connection conn = ConnectionUtil.getConn();
		Channel chan = conn.createChannel();
		//生命队列
		chan.queueDeclare("fanout02", false, false, false, null);
		//声明交换机
		chan.exchangeDeclare("fanoutEx", "fanout");
		//绑定队列到交换机
		//参数 1 队列名称,2 交换机名称 3 路由key
		chan.queueBind("fanout02", "fanoutEx", "");
		chan.basicQos(1);
		//定义消费者
		QueueingConsumer consumer=new QueueingConsumer(chan);
		//消费者与队列绑定
		chan.basicConsume("fanout02",false, consumer);
		while(true){
			Delivery delivery= consumer.nextDelivery();
			System.out.println("二号消费者接收到"+new String(delivery.getBody()));
			chan.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
}








































