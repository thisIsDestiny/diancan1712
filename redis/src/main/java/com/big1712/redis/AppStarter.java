package com.big1712.redis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.big1712.redis.config.DefaultConfig;


@SpringBootApplication
@RestController
public class AppStarter {

	public static void main(String[] args) {

		SpringApplication.run(AppStarter.class, args);
	}
	
	@Autowired
	private DefaultConfig config;

	@RequestMapping("/prop")
	public Map<String, Object> configurationProperties() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("host", config.getHost());
		map.put("port", config.getPort());
		map.put("timeout", config.getTimeout());
		return map;
	}



}
