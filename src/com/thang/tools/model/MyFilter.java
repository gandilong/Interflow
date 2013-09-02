package com.thang.tools.model;

import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Packet;

public class MyFilter implements PacketFilter {

	private static MyFilter filter=new MyFilter();
	
	private MyFilter(){
		
	}
	
	public static MyFilter getInstance(){
		return filter;
	}
	
	@Override
	public boolean accept(Packet pkt) {
		if(null==pkt){
			return false;
		}
		return true;
	}

}
