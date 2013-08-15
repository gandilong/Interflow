package com.thang.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.swing.JFrame;

import org.jivesoftware.smack.ConnectionConfiguration;

import com.thang.core.DefaultClient;
import com.thang.view.login.LoginPanel;
import com.thang.view.main.MainPanel;

public class Interflow extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    
    private LoginPanel loginPanel=null;//登陆面板
    private MainPanel mainPanel=null;//主面板
	
    private TMenuBar menuBar=null;//菜单栏
    
    private DefaultClient client=null;//客户终端
    private ConnectionConfiguration config=null;
    
    
    private CardLayout card=null;
    
	public Interflow(){
		init();
        initComp();		
	}
	
	private void init(){
		card=new CardLayout();
		this.setSize(300,580);
		this.setLayout(card);
		this.setResizable(false);
		this.setMaximumSize(new Dimension(320,600));
		this.setMinimumSize(new Dimension(280,550));
		this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
	}
	
	private void initComp(){
		loginPanel=new LoginPanel(this);
		mainPanel=new MainPanel();
		menuBar=new TMenuBar();
		
		
		this.setJMenuBar(menuBar);
		this.add(loginPanel,"loginPanel");
		this.add(mainPanel,"mainPanel");
		
	}
	
	/**
	 * 从注册表获历史数据。
	 */
	private void prepareData(){
		
	}

	/**
	 * 执行登陆
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String uname=loginPanel.getUname();
		String upass=loginPanel.getUpass();
		
		
		client=new DefaultClient(config,new CallbackHandler(){

			@Override
			public void handle(Callback[] arg) throws IOException,UnsupportedCallbackException {
				
			}
			
		});
		
	}
	
}
