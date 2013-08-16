/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thang.view;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.thang.tools.util.ImageUtils;

/**
 *
 * @author Administrator
 */
public class MyTray {
    
    public MyTray(final Interflow t){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              
                try{
                                       
                      SystemTray tray = SystemTray.getSystemTray(); // 创建系统托盘
                      Image image =ImageUtils.getLogo();
                      
                      PopupMenu popup = new PopupMenu();
                
                      MenuItem defaultItem = new MenuItem("主界面");
                      MenuItem exitItem = new MenuItem("退出");
                 
                     defaultItem.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            t.setVisible(true);
                        }
                 
                     });
                 
                    exitItem.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                             t.destory();
                             System.exit(0);
                        }
                 
                   });
                 
                
                 popup.add(defaultItem);
                 popup.add(exitItem);
                
                TrayIcon trayIcon = new TrayIcon(image, "Interflow", popup);
                                
                trayIcon.addMouseListener(new MouseAdapter(){

                        @Override
                        public void mouseClicked(MouseEvent me) {
                            int k=me.getClickCount();
                            if(2==k){
                                 t.setVisible(true);
                            }
                        }
                });
                
                tray.add(trayIcon);
                
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        });
    }
    
}
