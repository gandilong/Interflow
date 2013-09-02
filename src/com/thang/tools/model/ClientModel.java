package com.thang.tools.model;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Presence;

public class ClientModel {

	private XMPPConnection client;
	
	public ClientModel(XMPPConnection conn){
	      this.client=conn;	
	}
	
	public String getUser(){
		return client.getUser().split("@")[0];
	}
	
	
	public Roster getRoster(){
		return client.getRoster();
	}
	
	public void logout(){
		try{
			Presence presence = new Presence(Presence.Type.unavailable);
			presence.setStatus(MyStatus.offline);
			client.sendPacket(presence);
		    client.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Chat creatChat(String user,MessageListener msgListener){
		return client.getChatManager().createChat(user,msgListener);
	}
	
	public Chat creatChat(String user,String thread,MessageListener msgListener){
		return client.getChatManager().createChat(user,thread,msgListener);
	}
	
	
}
