package com.thang.view.main;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.thang.view.Interflow;

/**
 * 主面板，其子面板由HeadPanel , BodyPanel组成
 * @author gandilong
 *
 */
public class MainPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private HeadPanel headPanel=null;
	private BodyPanel bodyPanel=null;
	
	private Interflow parent=null;
	
	public MainPanel(Interflow p){
		parent=p;
		init();
	}
	
	private void init(){
		setLayout(new BorderLayout());
		headPanel=new HeadPanel();
		bodyPanel=new BodyPanel();
		
		add(headPanel,BorderLayout.NORTH);
		add(bodyPanel,BorderLayout.CENTER);
	}
	
}
