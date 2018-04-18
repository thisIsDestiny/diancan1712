package com.big1712.redis.test;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.big1712.redis.config.DefaultConfig;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import redis.clients.jedis.Jedis;

public class RedisTest {
	
	@Test
	public void demo1(){
		
		Jedis jedis = new Jedis("192.168.163.8",6379,5000);
	
		Set<String> keys = jedis.keys("*");
		
		for (String string : keys) {
			
			System.out.println(string);
		}
		
		
	}
	
}
