package com.thang.tools.model;

public class ChatInfo {

	private String selfUser;
	private String chatUser;
	private String thread;
	
	public ChatInfo(String self,String chat,String thread){
		selfUser=self;
		chatUser=chat;
		this.thread=thread;
	}
	
	public String getSelfUser() {
		return selfUser;
	}
	public void setSelfUser(String selfUser) {
		this.selfUser = selfUser;
	}
	public String getChatUser() {
		return chatUser;
	}
	public void setChatUser(String chatUser) {
		this.chatUser = chatUser;
	}

	public String getThread() {
		return thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}
	
}
