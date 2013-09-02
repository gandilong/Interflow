/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thang.view.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 *
 * @author Administrator
 */
public class InputBottom extends javax.swing.JPanel implements ActionListener{

  
	private static final long serialVersionUID = 1L;
    private InputPanel parent=null;
   
    public InputBottom(InputPanel p) {
    	this.parent=p;
        initComponents();
    }

  
    @SuppressWarnings("unchecked")
    private void initComponents() {

        send = new javax.swing.JButton();
        close = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(400, 30));

        send.setText("  发  送  ");
        send.setActionCommand("send");
        
        close.setText("  关  闭  ");
        close.setActionCommand("close");
        
        send.addActionListener(this);
        close.addActionListener(this);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 248, Short.MAX_VALUE)
                .addComponent(close)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(send))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(close)
                .addComponent(send))
        );
    }
    private javax.swing.JButton send;
    private javax.swing.JButton close;

	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if("close".equals(command)){
			parent.close();
		}
		if("send".equals(command)){
			parent.send();
		}
		
	}
    
}
