package com.thang.core;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.ping.packet.Ping;

import com.thang.tools.model.ChatInfo;
import com.thang.tools.util.StrUtils;
import com.thang.tools.util.WindowManager;
import com.thang.view.Interflow;
import com.thang.view.chat.ChatWindow;

/**
 * 验证收发的数据包
 * @author Gandilong
 *
 */
public class PacketGuard implements PacketListener,PacketInterceptor {

	private ChatManager chatManager=null;
	
	public PacketGuard(ChatManager chatM){
		chatManager=chatM;
	}
	
	/**
	 * 处理发给自己的数据包
	 */
	@Override
	public void processPacket(Packet pkt) {
		
		if(pkt instanceof Ping){
			Ping ping=(Ping)pkt;
		}
		
        if(pkt instanceof Presence){//好友上线信息
			Presence pre=(Presence)pkt;
		}
		
		if(pkt instanceof Message){//好友聊天信息
			Message msg=(Message)pkt;
			ChatWindow chatWin=WindowManager.getChatWindow(msg.getFrom().split("@")[0]);
			if(null==chatWin){
				ChatInfo chatInfo=new ChatInfo(msg.getTo(),msg.getFrom(),msg.getThread());
				ChatWindow newChatWin=new ChatWindow(chatInfo);
				WindowManager.addChatWindow(msg.getFrom().split("@")[0], newChatWin);
				System.out.println("body="+msg.getBody());
				newChatWin.setVisible(true);
			}
		}
		
		
		
	}

	/**
	 * 处理发送到服务器的数据包
	 */
	@Override
	public void interceptPacket(Packet pkt) {
		if(pkt instanceof Ping){
			Ping ping=(Ping)pkt;
		}
		
        if(pkt instanceof Presence){
			Presence pre=(Presence)pkt;
		}
        
        if(pkt instanceof Message){
        	
        }
        
	}

}
