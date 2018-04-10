package com.diancan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("index")
	public String getIndex(){
		return getPage("index");
	}
	
	@GetMapping("{pageName}")
	public String getPage(@PathVariable String pageName){
		return pageName;
	}
	
}
