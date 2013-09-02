package com.thang.view.main;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.thang.tools.model.ImagePath;
import com.thang.tools.util.ImageUtils;

/**
 * 显示用户的联系人的面板
 * @author Gandilong
 *
 */
public class BodyPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private RosterPanel roster=null;
	private GroupPanel group=null;
	private HistoryPanel history=null;
	
	 private JTabbedPane allPanel=null;
	 
	 public BodyPanel(){
		 init();
	 }
	 
	 private void init(){
		 allPanel=new JTabbedPane(JTabbedPane.TOP);
	        
	     roster=new RosterPanel();
	     group=new GroupPanel();
	       
	        
	     allPanel.addTab("好  友",ImageUtils.getImageIcon(ImagePath.TAB_Person),roster,"好友列表");
	     allPanel.addTab("群  落",ImageUtils.getImageIcon(ImagePath.TAB_Group),group,"好友群列表");
	     allPanel.addTab("记  录",ImageUtils.getImageIcon(ImagePath.TAB_History),history,"聊天记录");
	      
	        
	     this.setLayout(new BorderLayout());
	     add(allPanel,BorderLayout.CENTER);
	 }
  
	 
}
