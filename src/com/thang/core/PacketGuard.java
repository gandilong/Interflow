package com.thang.core;

import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

/**
 * 验证收发的数据包
 * @author Gandilong
 *
 */
public class PacketGuard implements PacketListener,PacketInterceptor {

	/**
	 * 处理发给自己的数据包
	 */
	@Override
	public void processPacket(Packet pkt) {
        System.out.println("I am process Packet:"+pkt.toXML());
	}

	/**
	 * 处理发送到服务器的数据包
	 */
	@Override
	public void interceptPacket(Packet pkt) {
		System.out.println("I am intercept packet:"+pkt.toXML());
	}

}
