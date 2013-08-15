package com.thang.core;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class DefaultClient {

	private XMPPConnection connection=null;
	
	private static Logger logger=Logger.getLogger(DefaultClient.class);
	
	/**
	 * 通过服务器名和回调方法构造客户端
	 * @param serverName
	 * @param callbackHandler
	 */
	public DefaultClient(String serverName,javax.security.auth.callback.CallbackHandler callbackHandler){
		if(null!=callbackHandler){
			connection=new XMPPConnection(serverName,callbackHandler);	
		}else{
			connection=new XMPPConnection(serverName);
		}
	}
	
	/**
	 * 通过配置对象和回调方法构造客户端
	 * @param config
	 * @param callbackHandler
	 */
	public DefaultClient(ConnectionConfiguration config,javax.security.auth.callback.CallbackHandler callbackHandler){
		if(null!=callbackHandler){
			connection=new XMPPConnection(config,callbackHandler);
		}else{
			connection=new XMPPConnection(config);	
		}
	}
	
	
	
	/**
	 * 开启服务
	 */
	public void start(){
		try {
			connection.connect();
			
			logger.info("Start Successful !");
		} catch (XMPPException e) {
			e.printStackTrace();
			logger.error("Here is a error:"+e.getMessage());
		}
	}
	
	/**
	 * 关闭服务
	 */
	public void close(){
		connection.disconnect();
	}
	
	
}
