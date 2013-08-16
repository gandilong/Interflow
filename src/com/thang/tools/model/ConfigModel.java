package com.thang.tools.model;

import com.thang.tools.util.RegUtils;

/**
 * 记录用户的配置信息，通过该类转换成特定的字符串并保存到注册表中
 * @author Gandilong
 *
 */
public class ConfigModel {

	private static String installDir=null;//安装目录
	private static String tempDir=null;//安装目录中的缓存文件目录
	
	private static String configFile="config.xml";//缓存文件名称
	
	static{
		//在安装的时候会把安装信息写入注册表，所以以下代码是从注册表中取值
		installDir=RegUtils.get("intallDir","F:/github/Interflow");
		tempDir=installDir+"/temp";
	}
	
	
	public static String getInstallDir() {
		return installDir;
	}
	public static void setInstallDir(String installDir) {
		ConfigModel.installDir = installDir;
	}
	public static String getTempDir() {
		return tempDir;
	}
	public static void setTempDir(String tempDir) {
		ConfigModel.tempDir = tempDir;
	}
	
	public static String getConfigFile(){
		return tempDir+"/"+configFile;
	}
	
	
	
}
