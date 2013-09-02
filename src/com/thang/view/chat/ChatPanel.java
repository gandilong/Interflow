package com.thang.view.chat;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jivesoftware.smack.Chat;

import com.thang.tools.model.ChatInfo;

/**
 * 显示聊天内容
 * @author gandilong
 *
 */
public class ChatPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ShowPanel show=null;
	private InputPanel input=null; 
	
	private ChatInfo chatInfo=null;
	
	public ChatPanel(ChatInfo info){
		this.chatInfo=info;
	    init();	
	}
	
	private void init(){
		try {
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createRaisedBevelBorder());
			setSize(400, 470);
			input=new InputPanel(this);
			show=new ShowPanel(this);
			add(input,BorderLayout.SOUTH);
			add(show,BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void show(String content){
		show.addText(content);
	}
	
	public void setChat(Chat chat){
		input.setChat(chat);
	}
	
	public ChatInfo getChatInfo(){
		return chatInfo;
	}

}
