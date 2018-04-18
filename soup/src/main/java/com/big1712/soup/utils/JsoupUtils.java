package com.big1712.soup.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsoupUtils {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	private JsoupUtils(){}
	
	/**
	 * 获取节点对象
	 * @param url	请求地址
	 * @return 		文档节点对象
	 */
	public static JsonNode getJsonNode(String url){
		
		JsonNode node = null;
		
		try {
			String body = getBody(url);
			
			node = mapper.readTree(body);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return node;
	}
	
	/**
	 * 获取节点对象
	 * @param url	请求地址(返回jsonp)
	 * @return 		文档节点对象
	 */
	public static JsonNode getJsonpNode(String url){
		
		JsonNode node = null;
		
		try {
			String body = getBody(url);
			
			body = RegexUtils.getSub(body, "^\\w*\\((.*?)\\)$");
			
			node = mapper.readTree(body);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return node;
	}

	private static String getBody(String url) throws IOException {
		String body = Jsoup.connect(url)
						.ignoreContentType(true)
						.execute()
						.body();
		return body;
	}
	
	/**
	 * 获取指定节点元素集合
	 * @param url		请求地址
	 * @param cssQuery  css请求样式
	 * @return			元素集合
	 */
	public static Elements getElements(String url,String cssQuery) {
	
		Elements eles = null;
		
		try {
			eles = Jsoup.connect(url).get().select(cssQuery);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return eles;
	}
}
