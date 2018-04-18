package com.big1712.soup.test;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.big1712.soup.utils.RegexUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsoupTest {

	private final ObjectMapper jsonMapper = new ObjectMapper();
	
	/**
	 * 1.抓取一整个网页
	 */
	public String getWholePage(){
		
		String url = "";
		
		String body = "";
		
		try {
			Response response = Jsoup.connect(url).execute();
			
			body = response.body();
			
			System.out.println(body);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return body;
	}
	
	/**
	 * 2.抓取整个网站中指定标签集合
	 */
	public void getTargetTags(){
		
		String url = "";
		
		Elements eles;
		
		try {
			Document document = Jsoup.connect(url).get();
			
			//符合标签
			eles = document.select("");
			print(eles);
			
			//单独标签
			eles = document.getElementsByTag("");
			print(eles);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 3.json转化为节点对象
	 * @throws IOException 
	 * 
	 */
	public void toJsonNode(String body) throws IOException{
		
		JsonNode rootJsonNode = jsonMapper.readTree(body);
		
		rootJsonNode.get(0).get("tag");
		
	}
	
	/**
	 * 4.jsonp转化为节点对象
	 * @throws IOException 
	 * 
	 */
	@Test
	public void toJsonpNode() throws IOException{
		
		String jsonpData = "callback({json:apple})";
		
		System.out.println(jsonpData);
		
		String jsonData = RegexUtils.getSub(jsonpData, "^\\w*\\((.*?)\\)$");
		
		System.out.println(jsonData);
		
		//toJsonNode(jsonData);
	}
	
	private void print(Elements eles){
		int index = 0;
		for (Element element : eles) {
			
			System.out.println(index++ + ":" + element.text());
		}
	}
	
}
