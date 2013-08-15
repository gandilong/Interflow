package com.thang.view.login;

import javax.swing.JDialog;

import org.jivesoftware.smack.ConnectionConfiguration;

public class ConfigDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private ConnectionConfiguration config=null;

	
	
	public ConnectionConfiguration getConfig(){
		
		
		config.setCompressionEnabled(true);
		config.setSASLAuthenticationEnabled(true);
		return config;
	}
}
