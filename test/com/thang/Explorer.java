package com.thang;

import java.awt.Desktop;
import java.net.URI;

public class Explorer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		    //Runtime.getRuntime().exec("explorer.exe http://www.baidu.com");
			
			if(Desktop.isDesktopSupported()){
				URI uri=new URI("http://www.baidu.com");
				Desktop desktop=Desktop.getDesktop();
				if(desktop.isSupported(java.awt.Desktop.Action.BROWSE)){
					//desktop.browse(uri);
				}else{
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://www.baidu.com");
				}
			}else{
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://www.baidu.com");	
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
