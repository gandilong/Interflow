package com.thang.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;

import com.thang.core.DefaultClient;
import com.thang.tools.model.ClientModel;
import com.thang.tools.model.ImagePath;
import com.thang.tools.model.LoginModel;
import com.thang.tools.util.ImageUtils;
import com.thang.tools.util.StrUtils;
import com.thang.view.login.ConfigDialog;
import com.thang.view.login.LoginPanel;
import com.thang.view.main.MainPanel;

public class Interflow extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    
    private LoginPanel loginPanel=null;//登陆面板
    private MainPanel mainPanel=null;//主面板
	
    private TMenuBar menuBar=null;//菜单栏
    private ConfigDialog configDialog=null;//配置对话框
    
    private static DefaultClient client=null;//客户终端
    private static ClientModel clientModel=null;
    private ConnectionConfiguration config=null;
    
    private LoginModel loginModel=null;//登陆时的一些配置信息
    private CardLayout card=null;
    
    private boolean isLogin=false;
    private static Logger logger=Logger.getLogger(Interflow.class);
    
    
    private static FileTransferManager fileManager=null;
    
	public Interflow(){
		super("Interflow");
		init();
        initComp();
        prepareData();
	}
	
	private void init(){
		card=new CardLayout();
		this.setIconImage(ImageUtils.getLogo());
		this.setLayout(card);
		this.setSize(300,600);
		this.getContentPane().setLayout(card);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setMaximumSize(new Dimension(550,750));
		this.setMinimumSize(new Dimension(300,500));
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void initComp(){
		menuBar=new TMenuBar(this);
		loginPanel=new LoginPanel(this);
		//mainPanel=new MainPanel(this);
		configDialog=new ConfigDialog(this);
		
		this.setJMenuBar(menuBar);
		this.add(loginPanel,"loginPanel");
		//this.add(mainPanel,"mainPanel");
		
	}
	
	/**
	 * 从注册表获历史数据。
	 */
	private void prepareData(){
		loginModel=new LoginModel();
		loginPanel.initByLoginModel(loginModel);
		configDialog.initByLoginModel(loginModel);
		if(loginModel.isAuto()){
			loginPanel.login();
		}
	}
	
	private void loginSuccess(){
		clientModel=new ClientModel(client.getConnection());
		mainPanel=new MainPanel(this);
		this.add(mainPanel,"mainPanel");
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		card.show(this.getContentPane(), "mainPanel");
		fileManager=new FileTransferManager(client.getConnection());
		menuBar.initMenu();
		loginModel.save();
		
	}
	
	private void failLogin(){
		//card.show(this, "loginPanel");
		menuBar.loginInit();
	}

	/**
	 * 执行登陆或服务器配置
	 * 每次点登陆都获得一次登陆配置
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {

		Object source=ae.getSource();

		if(source instanceof JMenuItem){
			
			configDialog.setVisible(true);
			
		}else if(source instanceof JButton){
			final JButton clickMe=((JButton)ae.getSource());
			clickMe.setEnabled(false);
			
			String uname=loginPanel.getUname();
			String upass=loginPanel.getUpass();
			String serviceName=loginPanel.getServiceName();
			
			loginModel.setUname(uname);
			loginModel.setUpass(upass);
			loginModel.setServiceName(serviceName);
			loginModel.setAuto(loginPanel.isAuto());
			loginModel.setRem(loginPanel.isRem());
			
			if(!StrUtils.validStr(uname)||!StrUtils.validStr(upass)){
				JOptionPane.showMessageDialog(this, "账号或密码不能空！","提示", JOptionPane.WARNING_MESSAGE,ImageUtils.getImageIcon(ImagePath.Alert));
				clickMe.setEnabled(true);
				return;
			}
			
			config=configDialog.getConfig();
			if(null!=config){
				if(StrUtils.validStr(serviceName)){
					config.setServiceName(serviceName);
				}
			}else{
				if(StrUtils.validStr(serviceName)){
					config=new ConnectionConfiguration(serviceName);
				}else{
					JOptionPane.showMessageDialog(this, "请配置一下服务器！","提示", JOptionPane.WARNING_MESSAGE,ImageUtils.getImageIcon(ImagePath.Alert));
					clickMe.setEnabled(true);
					return;
				}
				
			}
			
			
			client=new DefaultClient(config,null);
			try{
			    client.login(uname,upass);
			    
			}catch(Exception e){
				
				//client.close();
				failLogin();
				clickMe.setEnabled(true);
				logger.error("code:client.start Here is a error:"+e);
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "用户名或密码错误！","提示", JOptionPane.WARNING_MESSAGE,ImageUtils.getImageIcon(ImagePath.Alert));
			}
			loginSuccess();
		    clickMe.setEnabled(true);
		    client.start();
		    logger.info("Start Successful !");
		}
		
	}
	
	public static ClientModel getClient(){
		if(null!=client&&null==clientModel){
			clientModel=new ClientModel(client.getConnection());
			return clientModel;
		}
		return clientModel;
	}
	
	public void destory(){
		if(null!=client){
			client.close();	
		}
	}
	
	public static FileTransferManager getFileManger(){
		return fileManager;
	}
}
