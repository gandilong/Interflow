package com.thang.tools.util;

import org.apache.commons.lang.StringUtils;

public class StrUtils extends StringUtils{

	
	public static boolean validStr(String value){
		if(null!=value&&!"".equals(value.trim())){
			return true;
		}
		return false;
	}
	
	
	public static boolean validIntStr(String value){
		if(validStr(value)&&value.matches("^[0-9]+$")){
			return true;
		}
		return false;
	}
	
	public static String replaceValue(String value,String newValue){
		if(validStr(value)){
			return value;
		}
		return newValue;
	}
	
		
}
