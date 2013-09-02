package com.thang.view.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jdesktop.swingx.JXLabel;

import com.thang.tools.model.ImagePath;
import com.thang.tools.util.ImageUtils;

/**
 * 公共面板
 * @author gandilong
 *
 */
public class CommonPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JXLabel she=null;
	private JXLabel me=null;
	
	public CommonPanel(){
		init();
	}
	
	private void init(){
		setLayout(new BorderLayout());
		setSize(150, 470);
		setPreferredSize(new Dimension(150,470));
		she=new JXLabel(ImageUtils.getImageIcon(ImagePath.Head_05));
		me=new JXLabel(ImageUtils.getImageIcon(ImagePath.Head_03));
		
		add(she,BorderLayout.CENTER);
		add(me,BorderLayout.SOUTH);
	}
	
}
