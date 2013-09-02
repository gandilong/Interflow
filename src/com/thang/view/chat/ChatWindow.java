package com.thang.view.chat;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

import com.thang.tools.model.ChatInfo;
import com.thang.tools.util.DateUtils;
import com.thang.tools.util.ImageUtils;
import com.thang.tools.util.WindowManager;
import com.thang.view.Interflow;

public class ChatWindow extends JFrame implements MessageListener,WindowListener{

	private static final long serialVersionUID = 1L;

	private ChatPanel chatPanel=null;
	private CommonPanel commonPanel=null;
	private Chat chat=null;
	private ChatInfo chatInfo;
	public ChatWindow(ChatInfo ci){
		super(ci.getChatUser());
		if(null!=ci.getThread()){
			this.chat=Interflow.getClient().creatChat(ci.getChatUser(),ci.getThread(),this);	
		}else{
			this.chat=Interflow.getClient().creatChat(ci.getChatUser(),this);
		}
		
		this.chatInfo=ci;
		setSize(550, 470);
		setResizable(false);
		setLayout(new BorderLayout());
		setIconImage(ImageUtils.getLogo());
		setLocationRelativeTo(null);
		init();
		pack();
	}
	
	
	private void init(){
		chatPanel=new ChatPanel(chatInfo);
		commonPanel=new CommonPanel();
		setChat(chat);
		chat.addMessageListener(this);
		add(chatPanel,BorderLayout.CENTER);
		add(commonPanel,BorderLayout.EAST);
	}
	
	public void show(String content){
		chatPanel.show(content);
	}


    public void setChat(Chat chat){
    	this.chat=chat;
    	chatPanel.setChat(chat);
    }
    
    public Chat getChat(){
    	return chat;
    }
    
	@Override
	public void processMessage(Chat chat, Message msg) {
		if(!this.isVisible()){
			this.setVisible(true);	
		}
		String user=msg.getFrom();
		user=user.substring(0, user.indexOf("@"));
		show(user+":"+"("+DateUtils.getHour()+":"+DateUtils.getMinute()+")"+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;"+msg.getBody()+"<br/>");
	}


	@Override
	public void windowActivated(WindowEvent e) {
				
	}


	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("rmove user chatwindow="+chatInfo.getChatUser().split("@")[0]);
		WindowManager.removeChatWindow(chatInfo.getChatUser().split("@")[0]);
	}


	@Override
	public void windowClosing(WindowEvent e) {
		
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		
	}


	@Override
	public void windowOpened(WindowEvent e) {
		
	}
	
}
