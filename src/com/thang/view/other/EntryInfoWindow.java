package com.thang.view.other;

import java.awt.Graphics;

import javax.swing.JWindow;

import com.thang.tools.model.MyEntry;

public class EntryInfoWindow extends JWindow {

	private static final long serialVersionUID = 1L;
	
	private MyEntry entry=null;
	
	public EntryInfoWindow(MyEntry entry){
		this.entry=entry;
		init();
	}
	
	private void init(){
		this.setSize(120, 90);
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		g.drawString(entry.getName(), 20, 30);
	}
	

}
