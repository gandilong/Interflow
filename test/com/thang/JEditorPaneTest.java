package com.thang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;

import org.jdesktop.swingx.JXEditorPane;

public class JEditorPaneTest extends JFrame {

	private JXEditorPane show=null;
	private HTMLEditorKit kit=null;
	
	
	private StringBuilder sber=null;
	public JEditorPaneTest(){
		setSize(350, 360);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sber=new StringBuilder();
		init();
	}
	
	private void init(){
		try{
			kit=new HTMLEditorKit();
			kit.setDefaultCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		    show=new JXEditorPane("text/html","");
		    show.setBackground(Color.WHITE);
		    show.setForeground(Color.WHITE);
		    show.setPage("http://www.baidu.com");
		    
		    
		    System.out.println("1:"+show.getText());
		    
		    //kit=(HTMLEditorKit)show.getEditorKit();
		    show.setEditorKit(kit);
		    
		    System.out.println(kit);
		    Document doc=kit.createDefaultDocument();
		    show.setDocument(doc);
		    
		    System.out.println("2:"+show.getText());
		    
		    
		    show.replaceSelection("<h1><font color='red'>aaaaaaaaa<font></h1>");
		    sber.append("<h1><font color='red'>aaaaaaaaa<font></h1>");
		    show.setText(sber.toString());
		    sber.append("<h1><font color='red'>bbbb<font></h1><br/>");
		    show.setText(sber.toString());
		    System.out.println("3:"+show.getText());
		    
		    
		    
		    
		    
		    
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		add(new JScrollPane(show),BorderLayout.CENTER);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JEditorPaneTest test=new JEditorPaneTest();
		test.setVisible(true);
	}

}
