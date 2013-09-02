package com.thang.tools.util;

public class SysUtils {

	public static boolean isWindows(){
		return System.getProperty("os.name").toLowerCase().startsWith("windows");
	}
	
	public static boolean isVista() {
        return System.getProperty("os.name").toLowerCase().contains("vista");
    }

    public static boolean isMac() {
        return System.getProperty("os.name").toLowerCase().indexOf("mac") != -1;
    }
	
}
