package com.thang.tools.model;

import org.jivesoftware.smack.proxy.ProxyInfo;

public class ServerModel {

	private String host;
	private int port;
	private String serviceName;
	private ProxyInfo proxy;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public ProxyInfo getProxy() {
		return proxy;
	}
	public void setProxy(ProxyInfo proxy) {
		this.proxy = proxy;
	}
	
	
}
