/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thang.view;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Gandilong
 */
public class TMenuBar extends JMenuBar{
    

	private static final long serialVersionUID = 1L;
	//登陆设置
	private JMenu loginSet=null;
	private JMenuItem serverSet=null;
	
	//一级菜单
     private JMenu file=null;//文件
     private JMenu people=null;//联系人
     private JMenu operation=null;//操作
     private JMenu help=null;//帮助
     
     //二级菜单    
     private JMenuItem changeStatus=null;//更改状态
     private JMenu myStatus=null;//我的状态
     private JMenuItem set=null;//个人设置
     private JMenuItem sysSet=null;//系统设置
     private JMenuItem logout=null;//退出
     
     //三级菜单
     private JMenuItem online=null;//在线
     private JMenuItem outline=null;//离线
     private JMenuItem busy=null;//忙碌
     private JMenuItem dnd=null;//请勿打扰
     private JMenu away=null;//离开
     
     //四级菜单
     private JCheckBoxMenuItem backNow=null;//马上回来
     private JCheckBoxMenuItem busyNow=null;//正在忙
     private JCheckBoxMenuItem meeting=null;//正在开会
     private JCheckBoxMenuItem outeat=null;//外出用餐
     private JCheckBoxMenuItem sleep=null;//休息中...
     
     private JCheckBoxMenuItem nothing=null;//无任何回复
     
     private JMenuItem defined=null;//自定义回复
     
    
     
    public TMenuBar(){
       super();
       loginInit();
       //initMenu();
    }
    
    private void loginInit(){
    	loginSet=new JMenu("设置");
    	serverSet=new JMenuItem("服务器设置");
    	loginSet.add(serverSet);
    	add(loginSet);
    }
    
     public void initMenu(){
                         
           
           file=new JMenu("文件");
           people=new JMenu("联系人");
           operation=new JMenu("操作");
           help=new JMenu("帮助");
           
           changeStatus=new JMenuItem("更改状态");
           myStatus();  
           set=new JMenuItem("个人设置");
           sysSet=new JMenuItem("系统设置");
           logout=new JMenuItem("退出");
           
           file.add(changeStatus);
           file.addSeparator();
           file.add(myStatus);
           file.addSeparator();
           file.add(set);
           file.add(sysSet);
           file.addSeparator();
           file.add(logout);
           
           
           add(file);
           add(people);
           add(operation);
           add(help);
           
           //this.setJMenuBar(jmb);
    }
    
    //构建myStatus菜单
    public void myStatus(){
         
           myStatus=new JMenu("我的状态");
        
           online=new JMenuItem("在线");
           outline=new JMenuItem("离线");
           busy=new JMenuItem("忙碌");
           dnd=new JMenuItem("请勿打扰");
                  
           away=new JMenu("离开");
        
        
           backNow=new JCheckBoxMenuItem("马上回来");
           busyNow=new JCheckBoxMenuItem("正在忙...",true);
           meeting=new JCheckBoxMenuItem("正在开会...");
           outeat=new JCheckBoxMenuItem("外出用餐");
           sleep=new JCheckBoxMenuItem("休息中...");
     
           nothing=new JCheckBoxMenuItem("无任何回复");
     
           defined=new JMenuItem("自定义回复");
                         
           away.add(backNow);
           away.add(busyNow);
           away.add(meeting);
           away.add(outeat);
           away.add(sleep);
           away.add(nothing);
           away.addSeparator();
           away.add(defined);
           
           
                  
           myStatus.add(online);
           myStatus.add(busy);
           myStatus.add(dnd);
           myStatus.add(outline);
           myStatus.addSeparator();
           myStatus.add(away);
           
    }
    
}
