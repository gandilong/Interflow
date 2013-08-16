package com.thang.view.login;

import javax.swing.JDialog;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.proxy.ProxyInfo;

import com.thang.tools.model.LoginModel;
import com.thang.tools.util.ImageUtils;
import com.thang.tools.util.StrUtils;
import com.thang.view.Interflow;

public class ConfigDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private ConnectionConfiguration config=null;

	private Interflow parent;
	private LoginModel loginModel=null;
	public ConfigDialog(Interflow p){
		super(p,true);
		parent=p;
		initComponents();
		init();
	}
	
	private void init(){
		this.setIconImage(ImageUtils.getLogo());
		this.setLocationRelativeTo(null);
	}
	
	public void initByLoginModel(LoginModel model){
		this.loginModel=model;
		server_host_input.setText(model.getServerHost());
		server_port_input.setText(String.valueOf(model.getServerPort()));
		proxy_done_input.setSelectedIndex(model.getProxyDone());
		proxy_host_input.setText(model.getProxyHost());
		proxy_port_input.setText(String.valueOf(model.getProxyPort()));
		proxy_uname_input.setText(model.getProxyUname());
		proxy_upass_input.setText(model.getProxyUpass());
		proxy.setSelected(model.isProxy());
	}
	
	public ConnectionConfiguration getConfig(){
		String serverHost=server_host_input.getText();
		String serverPort=server_port_input.getText();
		boolean needProxy=proxy.isSelected();
		
		//采用服务器
		if(StrUtils.validStr(serverHost)&&StrUtils.validIntStr(serverPort)&&!needProxy){
			config=new ConnectionConfiguration(serverHost,Integer.parseInt(serverPort));
		}
		
		//采用代理
		if(needProxy){
			
			String proxyHost=proxy_host_input.getText();
			String proxyPort=proxy_port_input.getText();
			String proxyUname=proxy_uname_input.getText();
			String proxyUpass=proxy_upass_input.getText();
			int item=proxy_done_input.getSelectedIndex();
			if(StrUtils.validStr(proxyHost)&&StrUtils.validIntStr(serverPort)&&StrUtils.validStr(proxyUname)&&StrUtils.validStr(proxyUpass)){
				ProxyInfo proxyInfo=null;
				switch(item){
				    case 0: proxyInfo=new ProxyInfo(ProxyInfo.ProxyType.SOCKS4,proxyHost,Integer.parseInt(proxyPort),proxyUname,proxyUpass);break;
				    case 1: proxyInfo=new ProxyInfo(ProxyInfo.ProxyType.SOCKS5,proxyHost,Integer.parseInt(proxyPort),proxyUname,proxyUpass);break;
				    case 2: proxyInfo=new ProxyInfo(ProxyInfo.ProxyType.HTTP,proxyHost,Integer.parseInt(proxyPort),proxyUname,proxyUpass);break;
				    case 3: proxyInfo=new ProxyInfo(ProxyInfo.ProxyType.NONE,proxyHost,Integer.parseInt(proxyPort),proxyUname,proxyUpass);break;
				}
				
				if(StrUtils.validStr(serverHost)&&StrUtils.validIntStr(serverPort)){
					config=new ConnectionConfiguration(serverHost,Integer.parseInt(serverPort),proxyInfo);
				}else{
					config=new ConnectionConfiguration("",proxyInfo);
				}
					
			}
			
		}
		
		if(null!=config){
			config.setCompressionEnabled(true);
			config.setSASLAuthenticationEnabled(true);
		}
		
		return config;
	}
	
	private void initComponents() {

		    serverLabel = new javax.swing.JLabel();
	        portLabel = new javax.swing.JLabel();
	        proxy = new javax.swing.JCheckBox();
	        deal = new javax.swing.JLabel();
	        host = new javax.swing.JLabel();
	        server_host_input = new javax.swing.JTextField();
	        server_port_input = new javax.swing.JTextField();
	        proxy_done_input = new javax.swing.JComboBox();
	        proxy_host_input = new javax.swing.JTextField();
	        host_port = new javax.swing.JLabel();
	        proxy_port_input = new javax.swing.JTextField();
	        username = new javax.swing.JLabel();
	        proxy_uname_input = new javax.swing.JTextField();
	        password = new javax.swing.JLabel();
	        proxy_upass_input = new javax.swing.JTextField();
	        ok_btn = new javax.swing.JButton();

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	        setTitle("配置");

	        serverLabel.setText("服务器：");

	        portLabel.setText("端口号：");

	        proxy.setText("是否代理");
	        proxy.addChangeListener(new javax.swing.event.ChangeListener() {
	            public void stateChanged(javax.swing.event.ChangeEvent evt) {
	                proxyStateChanged(evt);
	            }
	        });

	        deal.setText("协  议：");

	        host.setText("主  机：");

	        proxy_done_input.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Socket", "Http" }));

	        host_port.setText("端  口：");

	        username.setText("账  号：");

	        password.setText("密  码：");

	        ok_btn.setText(" 确  定 ");
	        ok_btn.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                ok_btnActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(41, 41, 41)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(password)
	                        .addGap(18, 18, 18)
	                        .addComponent(proxy_upass_input))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(username)
	                        .addGap(18, 18, 18)
	                        .addComponent(proxy_uname_input))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(host_port)
	                        .addGap(18, 18, 18)
	                        .addComponent(proxy_port_input))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(host)
	                        .addGap(18, 18, 18)
	                        .addComponent(proxy_host_input))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(deal)
	                        .addGap(18, 18, 18)
	                        .addComponent(proxy_done_input, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(proxy)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(portLabel)
	                        .addGap(18, 18, 18)
	                        .addComponent(server_port_input))
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(serverLabel)
	                        .addGap(18, 18, 18)
	                        .addComponent(server_host_input, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(62, Short.MAX_VALUE))
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(ok_btn)
	                .addGap(53, 53, 53))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(62, 62, 62)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(serverLabel)
	                    .addComponent(server_host_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(29, 29, 29)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(portLabel)
	                    .addComponent(server_port_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addComponent(proxy)
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(deal)
	                    .addComponent(proxy_done_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(21, 21, 21)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(host)
	                    .addComponent(proxy_host_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(26, 26, 26)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(host_port)
	                    .addComponent(proxy_port_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(28, 28, 28)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(username)
	                    .addComponent(proxy_uname_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(25, 25, 25)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(password)
	                    .addComponent(proxy_upass_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
	                .addComponent(ok_btn)
	                .addGap(21, 21, 21))
	        );

	        pack();
	    }
	
	

	private void ok_btnActionPerformed(java.awt.event.ActionEvent evt) {                                       
		loginModel.setServerHost(server_host_input.getText());
		loginModel.setServerPort(Integer.parseInt(StrUtils.replaceValue(server_port_input.getText(),"5222")));
		loginModel.setProxy(proxy.isSelected());
		
		if(proxy.isSelected()){
			loginModel.setProxyDone(proxy_done_input.getSelectedIndex());
			loginModel.setProxyHost(proxy_host_input.getText());
			loginModel.setProxyPort(Integer.parseInt(StrUtils.replaceValue(proxy_port_input.getText(),"0")));
			loginModel.setProxyUname(proxy_uname_input.getText());
			loginModel.setProxyUpass(proxy_upass_input.getText());
		}
		
	    this.setVisible(false);
	        
	}                                
	 
    private void proxyStateChanged(javax.swing.event.ChangeEvent evt) {                                   
	     if(proxy.isSelected()){
	           setProxyEnable(true);
	     }else{
	          setProxyEnable(false);
	      }
	}
	    
	 public void setProxyEnable(boolean status){
	      proxy_done_input.setEnabled(status);    
	      proxy_host_input.setEnabled(status);
	      proxy_port_input.setEnabled(status);
	      proxy_uname_input.setEnabled(status);
	      proxy_upass_input.setEnabled(status);
	           
	      server_host_input.setEnabled(!status);
	      server_port_input.setEnabled(!status);
	 }

	
    private javax.swing.JLabel deal;
    private javax.swing.JLabel host;
    private javax.swing.JLabel host_port;
    private javax.swing.JButton ok_btn;
    private javax.swing.JLabel password;
    private javax.swing.JLabel portLabel;
    private javax.swing.JCheckBox proxy;
    private javax.swing.JComboBox proxy_done_input;
    private javax.swing.JTextField proxy_host_input;
    private javax.swing.JTextField proxy_port_input;
    private javax.swing.JTextField proxy_uname_input;
    private javax.swing.JTextField proxy_upass_input;
    private javax.swing.JLabel serverLabel;
    private javax.swing.JTextField server_host_input;
    private javax.swing.JTextField server_port_input;
    private javax.swing.JLabel username;
}
