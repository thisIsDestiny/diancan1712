package com.big1712.soup.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	private RegexUtils(){}
	
	 /**  
     * 正则表达式匹配两个指定字符串中间的内容  
     * @param soap  
     * @return  
     */    
    public static  List<String> getSubs(String soap,String rgex){    
        List<String> list = new ArrayList<String>();    
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式    
        Matcher m = pattern.matcher(soap);    
        int i = 1;
        while (m.find()) {    
              
            list.add(m.group(i++));        
        }    
        return list;    
    }    
        
    /**  
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样  
     * @param soap  
     * @param rgex  
     * @return  
     */    
    public static String getSub(String soap,String rgex){    
    	
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式    
        
        Matcher m = pattern.matcher(soap);    
        
        while(m.find()){    
            return m.group(1);    
        }    
        return "";    
    }    
	
}
