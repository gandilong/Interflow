package com.thang.tools.model;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;

import com.thang.tools.util.StrUtils;
import com.thang.tools.util.XmlUtils;

/**
 * 记录用户的登陆配置，通过该类转换成特定的字符串并保存到注册表中
 * @author Gandilong
 *
 */
public class LoginModel {

	private String uname;
	private String upass;
	private boolean rem;//记住密码
	private boolean auto;//自动登陆
	
	private String serviceName;//服务器名称
	
	private String serverHost;//服务器主机
	private int serverPort;//服务器端口
	
	private boolean proxy;//是否使用代理
	
	private String proxyHost;
	private int proxyPort;
	private int proxyDone;
	private String proxyUname;
	private String proxyUpass;
	
	private Document config=null; 
	public LoginModel(){
		config=XmlUtils.getXML(ConfigModel.getConfigFile());
		
		Element root=config.getRootElement();
		Element login=root.element("login");
		Iterator<Element> serverConfig=login.elementIterator();
		Element item=null;
		String name=null;
		while(serverConfig.hasNext()){
			item=serverConfig.next();
			name=item.getName();
			if("service_name".equals(name)){
				setServiceName(item.getText());
			}else if("server_host".equals(name)){
				setServerHost(item.getText());
			}else if("server_port".equals(name)){
				setServerPort(Integer.parseInt(StrUtils.replaceValue(item.getText(),"5222")));
			}else if("uname".equals(name)){
				setUname(item.getText());
			}else if("upass".equals(name)){
				setUpass(item.getText());
			}else if("proxy".equals(name)){
				setProxy(Boolean.parseBoolean(StrUtils.replaceValue(item.getText(),"false")));
			}else if("proxy_host".equals(name)){
				setProxyHost(item.getText());
			}else if("proxy_port".equals(name)){
				setProxyPort(Integer.parseInt(StrUtils.replaceValue(item.getText(),"0")));
			}else if("proxy_done".equals(name)){
				setProxyDone(Integer.parseInt(StrUtils.replaceValue(item.getText(),"0")));
			}else if("proxy_uname".equals(name)){
				setProxyUname(item.getText());
			}else if("proxy_upass".equals(name)){
				setProxyUpass(item.getText());
			}else if("rem".equals(name)){
				setRem(Boolean.parseBoolean(StrUtils.replaceValue(item.getText(),"false")));
			}else if("auto".equals(name)){
				setAuto(Boolean.parseBoolean(StrUtils.replaceValue(item.getText(),"false")));
			}
			
		}
		
	}
	

	public void save(){
		XmlUtils.setXML(config, ConfigModel.getConfigFile());
	}
	
	public boolean isRem() {
		return rem;
	}

	public void setRem(boolean rem) {
		this.rem = rem;
		config.getRootElement().element("login").element("rem").setText(String.valueOf(rem));
	}

	public boolean isAuto() {
		return auto;
	}


	public void setAuto(boolean auto) {
		this.auto = auto;
		config.getRootElement().element("login").element("auto").setText(String.valueOf(auto));
	}


	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
		config.getRootElement().element("login").element("uname").setText(uname);
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
		config.getRootElement().element("login").element("upass").setText(upass);
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
		config.getRootElement().element("login").element("service_name").setText(serviceName);
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
		config.getRootElement().element("login").element("server_host").setText(serverHost);
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
		config.getRootElement().element("login").element("server_port").setText(String.valueOf(serverPort));
	}

	public boolean isProxy() {
		return proxy;
	}

	public void setProxy(boolean proxy) {
		this.proxy = proxy;
		config.getRootElement().element("login").element("proxy").setText(String.valueOf(proxy));
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
		config.getRootElement().element("login").element("proxy_host").setText(String.valueOf(proxyHost));
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
		config.getRootElement().element("login").element("proxy_port").setText(String.valueOf(proxyPort));
	}

	public int getProxyDone() {
		return proxyDone;
	}

	public void setProxyDone(int proxyDone) {
		this.proxyDone = proxyDone;
		config.getRootElement().element("login").element("proxy_done").setText(String.valueOf(proxyDone));
	}

	public String getProxyUname() {
		return proxyUname;
	}

	public void setProxyUname(String proxyUname) {
		this.proxyUname = proxyUname;
		config.getRootElement().element("login").element("proxy_uname").setText(String.valueOf(proxyUname));
	}

	public String getProxyUpass() {
		return proxyUpass;
	}

	public void setProxyUpass(String proxyUpass) {
		this.proxyUpass = proxyUpass;
		config.getRootElement().element("login").element("proxy_upass").setText(String.valueOf(proxyUpass));
	}
	
	@Override
	public String toString() {
		return this.uname+" and "+this.upass;
	}
}
