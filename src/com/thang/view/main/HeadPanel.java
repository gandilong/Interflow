package com.thang.view.main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.thang.tools.model.ClientModel;
import com.thang.tools.model.ImagePath;
import com.thang.tools.util.ImageUtils;
import com.thang.view.Interflow;
import com.thang.view.other.ScaleIcon;
import com.thang.view.other.StatusCombobox;


/**
 * 显示用户个人信息的头面板
 * @author Gandilong
 *
 */
public class HeadPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private javax.swing.JLabel headLabel;
	private javax.swing.JLabel nameLabel;
	private javax.swing.JButton stBtn;
	
	private ClientModel client=null;
	
	public HeadPanel(){
		init();
		initComponents();
		
	}
	
	private void init(){
		client=Interflow.getClient();
	}
	
	
	private void initComponents(){
		    headLabel = new javax.swing.JLabel();
	        stBtn = new javax.swing.JButton(){
				private static final long serialVersionUID = 1L;
				@Override
	        	public void paint(Graphics g) {
	        		super.paint(g);
	        		g.drawImage(ImageUtils.getImage(ImagePath.Arrow_Down),this.getWidth()/2+10,this.getHeight()/2,null);
	        	}
	        };
	        stBtn.setBorderPainted(false);
	        stBtn.setBackground(this.getBackground());
	        stBtn.setForeground(this.getForeground());
	        final StatusCombobox s=new StatusCombobox(stBtn);
	        stBtn.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent evt) {
					s.show((JButton)evt.getSource(), 26,22);
				}
				
			});
	        
	        
	        nameLabel = new javax.swing.JLabel(client.getUser());
            
	        headLabel.setIcon(new ScaleIcon(ImageUtils.getImageIcon(ImagePath.Head_01))); // NOI18N
             
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(headLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(stBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
	                .addContainerGap(189, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(headLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(stBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap())
	        );
	}
	
}
