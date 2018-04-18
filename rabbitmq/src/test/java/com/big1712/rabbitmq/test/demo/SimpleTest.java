package com.big1712.rabbitmq.test.demo;


import java.io.IOException;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class SimpleTest {
	//模拟生产者将消息放入队列
	@Test
	public void send() throws Exception{
		/*1 创建连接工厂
		 * 2 配置共创config
		 * 3 获取连接
		 * 4获取信道
		 * 5 从信道声明queue
		 * 6 发送消息
		 * 7 释放资源
		 */
		Connection conn = getConn();
		
		Channel chan = getChannel(conn);
		
		//发送消息
		String msg="helloworld,nihaoa";
		
		chan.basicPublish("", "simple", null, msg.getBytes());
		//exchange String类型,交换机名称,简单模式使用默认交换""
		//routingkey String类型,当前的消息绑定的routingkey,简单模式下,与队列同名即可
		//props BasicProperties类型,消息的属性字段对象,例如BasicProperties
		//可以设置一个deliveryMode的值0 持久化,1 表示不持久化,durable配合使用
		//body byte[] :消息字符串的byte数组
	}
	private Channel getChannel(Connection conn) throws IOException {
		
		//从连接获取信道
		Channel chan=conn.createChannel();
		
		//利用channel声明第一个队列
		chan.queueDeclare("simple", false, false, false, null);
	    //queue String类型,表示声明的queue对列的名字
		//durable Boolean类型,表示是否持久化
		//exclusive Boolean类型:当前声明的queue是否专注;true当前连接创建的
		//任何channle都可以连接这个queue,false,新的channel不可使用
		//autoDelete Boolean类型:在最后连接使用完成后,是否删除队列,false
		//arguments Map类型,其他声明参数
		return chan;
	}
	private Connection getConn() throws IOException {
		ConnectionFactory factory=new ConnectionFactory();
		factory.setHost("106.75.85.179");
		factory.setPort(5672);
		factory.setVirtualHost("/jt");
		factory.setUsername("jtadmin");
		factory.setPassword("123456");
		//从工厂获取连接
		Connection conn=factory.newConnection();
		return conn;
	}
	//模拟消费端
	@Test
	public void receive() throws Exception{
		
		Connection conn = getConn();
		
		Channel chan = getChannel(conn);
		
		//创建一个消费者
		QueueingConsumer consumer= new QueueingConsumer(chan);
		chan.basicConsume("simple", consumer);
		
		//监听队列
		while(true){
			//获取下一个delivery,delivery从队列获取消息
			Delivery delivery = consumer.nextDelivery();
			String msg=new String(delivery.getBody());
			System.out.println(msg);
		}
	}
}



















