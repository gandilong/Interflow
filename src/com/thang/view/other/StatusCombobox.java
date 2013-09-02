package com.thang.view.other;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboPopup;

import com.thang.tools.model.ImagePath;
import com.thang.tools.util.ImageUtils;

public class StatusCombobox extends BasicComboPopup{

	private static final long serialVersionUID = 1L;

	private static JButton stBtn=null;//状态按钮
	private static JComboBox jcb=null;
	private static List<Status> sts=null;
	
	static{
		jcb=new JComboBox();
		//初始化数据
		sts=new ArrayList<Status>();
		sts.add(new Status("  聊天 ",ImageUtils.getImageIcon(ImagePath.Status_Call)));
	    sts.add(new Status("  在线 ",ImageUtils.getImageIcon(ImagePath.Status_Online)));
	    sts.add(new Status("  忙碌 ",ImageUtils.getImageIcon(ImagePath.Status_Busy))); 
	    
	    jcb.setBackground(Color.WHITE);
	    jcb.setBorder(BorderFactory.createEmptyBorder());
	    jcb.setModel(new DefaultComboBoxModel(sts.toArray()));
	    jcb.setEnabled(false);
        jcb.setVisible(false);
        
	    
	    jcb.setRenderer(new DefaultListCellRenderer(){
			private static final long serialVersionUID = 1L;

			@Override
           public Component getListCellRendererComponent(JList jlist, Object value, int index, boolean isSelected, boolean hasFocus) {
               jlist.setVisibleRowCount(3);
               setOpaque(true);
               
               Status s=(Status)value;
               setText(s.getText());
               setIcon(s.getIcon());
        
               setBackground(isSelected?jlist.getSelectionBackground():jlist.getBackground());
               setForeground(isSelected?jlist.getSelectionForeground():jlist.getForeground());
            
                return this;
           }
           
           
       });
	    
	    jcb.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent ie) {
                Object obj=jcb.getSelectedItem();
                if(obj instanceof Status){
                     Status s=(Status)obj;
                     itemChanged(s);
                }
                
            }
        
        });
	    
	    
	}
	
	
	private static void itemChanged(Status s){
		stBtn.setIcon(s.getIcon());
	}
	
	
	public StatusCombobox(JButton btn){
		super(jcb);
		stBtn=btn;
		jcb.setBackground(stBtn.getBackground());
		jcb.setForeground(stBtn.getForeground());
		jcb.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
            	StatusCombobox.this.setVisible(false);
            }
             
       });
	}
	
}

class Status{

    private String text;
    private ImageIcon icon;

    public Status(){}
    
    public Status(String txt,ImageIcon icon){
         this.text=txt;
         this.icon=icon;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

  

}

