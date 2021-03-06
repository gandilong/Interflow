package com.thang.core;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import com.thang.tools.model.MyFilter;
import com.thang.tools.model.MyStatus;

public class DefaultClient {

	private XMPPConnection connection=null;
	
	private PacketGuard guard=null;
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
		init();
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
		init();
	}
	
	private void init(){
		guard=new PacketGuard(connection.getChatManager());
	}
	
	
	
	/**
	 * 开启服务
	 */
	public void login(String uname,String upass)throws XMPPException{
			connection.connect();
			connection.login(uname, upass);
	}
	
	public void start(){
		connection.addPacketListener(guard,MyFilter.getInstance());
		connection.addPacketInterceptor(guard,MyFilter.getInstance());
	}
	
	public XMPPConnection getConnection(){
		return connection;
	}
	
	/**
	 * 关闭服务
	 */
	public void close(){
		Presence presence = new Presence(Presence.Type.unavailable);
		presence.setStatus(MyStatus.offline);
		connection.sendPacket(presence);
		connection.disconnect();
	}
	
}
