package com.thang.tools.util;

import java.util.HashMap;
import java.util.Map;

import com.thang.view.chat.ChatWindow;

public class WindowManager{
	
	private static final long serialVersionUID = 1L;
	
	private static Map<String,ChatWindow> chatWins=new HashMap<String,ChatWindow>();
	
	
	public static ChatWindow getChatWindow(String fromOrTo){
		return chatWins.get(fromOrTo);
	}
	
	public static void addChatWindow(String fromOrTo,ChatWindow chat){
		chatWins.put(fromOrTo,chat);
	}
	
	public static void removeChatWindow(String fromOrTo){
		chatWins.remove(fromOrTo);
	}

}
