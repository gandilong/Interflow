package com.thang;

import org.jivesoftware.smack.ConnectionConfiguration;

public class ConnectionConfigurationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionConfiguration cc=new ConnectionConfiguration("good");
		System.out.println(cc.isCompressionEnabled());
	}

}
