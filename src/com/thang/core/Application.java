package com.thang.core;



import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.thang.tools.util.SysteUtils;
import com.thang.view.Interflow;

public class Application {

	
	public static void main(String[] args) {
		try{
		   
			if(SysteUtils.isWindows()) {
                JFrame.setDefaultLookAndFeelDecorated(true);
	            JDialog.setDefaultLookAndFeelDecorated(true);
            }
			
		   UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
           Properties props = new Properties();
           props.put("logoString", "gandilong");
           com.jtattoo.plaf.smart.SmartLookAndFeel.setCurrentTheme(props);
		
		   java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	Interflow interflow=new Interflow();
	            	interflow.setVisible(true);
	            }
	        });
	    
		}catch(Exception e){
			e.printStackTrace();
		}
	      	
	}
	
	
}
