package com.thang.view.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.text.html.HTMLEditorKit;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXEditorPane;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;

import com.thang.tools.model.ChatInfo;
import com.thang.tools.util.DateUtils;
import com.thang.tools.util.WindowManager;
import com.thang.view.Interflow;

/**
 * 输入聊天内容
 * @author gandilong
 *
 */
public class InputPanel extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;

	private JToolBar tools=null;
	
	private Chat chat;
	
	private ChatInfo chatInfo=null;
	private ChatPanel parent=null;
	
	private JTextArea area=null;
	
	private InputBottom bottom=null;
	
	public InputPanel(ChatPanel p){
		parent=p;
		this.chatInfo=p.getChatInfo();
		initButtons();
		initTools();
		init();
	}
	
	
	private void initButtons(){
		bottom=new InputBottom(this);
		area=new JTextArea(6,36);
		area.addKeyListener(this);
	}
	
	private void initTools(){
		tools=new JToolBar();
		tools.add(new JXButton(chatInfo.getSelfUser()));
		tools.setBorder(getBorder());
		
	}
	
	
	private void init(){
		setLayout(new BorderLayout());
		setSize(400, 90);
		
		
		add(tools,BorderLayout.NORTH);
		add(new JScrollPane(area),BorderLayout.CENTER);
		add(bottom,BorderLayout.SOUTH);
	}
	
	public void send(){
		if(null!=chat){
			
			try {
				String content=area.getText();
				if(null==content||"".equals(content.trim())){
					return;
				}
				chat.sendMessage(content);
				parent.show(chatInfo.getSelfUser()+":("+DateUtils.getHour()+":"+DateUtils.getMinute()+")"+"<br/>&nbsp;&nbsp;&nbsp;&nbsp;"+content+"<br/>");
			} catch (XMPPException e) {
				e.printStackTrace();
			}
			area.setText("");
		}else{
			throw new RuntimeException("chat为空！");
		}
	}
	
	public void close(){
		ChatWindow chatWin=WindowManager.getChatWindow(chatInfo.getChatUser().split("@")[0]);
		if(null!=chatWin){
			chatWin.setVisible(false);
		}
	}
	
	public void setChat(Chat chat){
		this.chat=chat;
	}


	@Override
	public void keyPressed(KeyEvent e) {
         if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_ENTER){
        	 send();
         }
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
