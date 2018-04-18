package com.big1712.rabbitmq.test.demo;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
	
	
	
	public static Connection getConn(){
		try{
			ConnectionFactory factory = getConFac();
		
			//从工厂获取连接
			Connection conn=factory.newConnection();
			return conn;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	/**
	 * 获取工厂
	 * 
	 * @return
	 */
	private static ConnectionFactory getConFac() {
		ConnectionFactory factory=new ConnectionFactory();
		factory.setHost("106.75.85.179");
		factory.setPort(5672);
		factory.setVirtualHost("/jt");
		factory.setUsername("jtadmin");
		factory.setPassword("123456");
		return factory;
	}
}
