package com.thang.tools.util;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.thang.tools.model.ImagePath;

public class ImageUtils {
	
	 public static ImageIcon icon=new ImageIcon(ImageUtils.class.getResource(ImagePath.Interflow_Logo));
	    
	 /**
	   * 得到标志性图标
	   * @return 
	   */
	 public static Image getLogo(){
	       return icon.getImage();
	 }
	    
	 public static Image getImage(String path){
	      return new ImageIcon(ImageUtils.class.getResource(path)).getImage();
	 }
	    
	 public static ImageIcon getImageIcon(String path){
	      return new ImageIcon(ImageUtils.class.getResource(path));
	 }
	    
}
