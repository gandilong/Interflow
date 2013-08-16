package com.thang.view.login;

import javax.swing.JPanel;

import com.thang.tools.model.ImagePath;
import com.thang.tools.model.LoginModel;
import com.thang.tools.util.ImageUtils;
import com.thang.tools.util.StrUtils;
import com.thang.view.Interflow;

public class LoginPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	 private javax.swing.JCheckBox autoCheck;
	 private javax.swing.JButton loginBtn;
	 private javax.swing.JLabel loginLabel;
	 private javax.swing.JButton otherBtn;
	 private javax.swing.JCheckBox remCheck;
	 private javax.swing.JTextField serviceNameInput;
	 private javax.swing.JLabel serviceNameLabel;
	 private javax.swing.JTextField unameInput;
	 private javax.swing.JLabel unameLabel;
	 private javax.swing.JPasswordField upassInput;
	 private javax.swing.JLabel upassLabel;
	 
	 private Interflow parent;
	 public LoginPanel(Interflow p){
		 initComponents();
		 this.parent=p;
		 init();
	 }
	 
	 private void init(){
		 loginBtn.addActionListener(this.parent);
	 }
	 
	 
	 public String getUname(){
		 return unameInput.getText();
	 }
	 
	 public String getUpass(){
		 return new String(upassInput.getPassword());
	 }
	 
	 public String getServiceName(){
		 return serviceNameInput.getText();
	 }
	 
	 /**
	  * 是否记住密码
	  * @return
	  */
	 public boolean isRem(){
		 return remCheck.isSelected();
	 }
	 
	 /**
	  * 是否自动登陆
	  * @return
	  */
	 public boolean isAuto(){
		 return autoCheck.isSelected();
	 }
	 
	 /**
	  * 代码方式执行登陆
	  */
	 public void login(){
		 loginBtn.doClick();
	 }
	 
	 public void initByLoginModel(LoginModel model){
			if(StrUtils.validStr(model.getServiceName())){
				serviceNameInput.setText(model.getServiceName());
			}
			if(StrUtils.validStr(model.getUname())){
				unameInput.setText(model.getUname());
			}
			if(model.isRem()){
				upassInput.setText(model.getUpass());
				remCheck.setSelected(true);
			}
			if(model.isAuto()){
				autoCheck.setSelected(true);
			}
	 }
	 
	 private void initComponents() {

		    loginLabel = new javax.swing.JLabel();
	        unameLabel = new javax.swing.JLabel();
	        upassLabel = new javax.swing.JLabel();
	        serviceNameLabel = new javax.swing.JLabel();
	        unameInput = new javax.swing.JTextField();
	        upassInput = new javax.swing.JPasswordField();
	        serviceNameInput = new javax.swing.JTextField();
	        remCheck = new javax.swing.JCheckBox();
	        autoCheck = new javax.swing.JCheckBox();
	        loginBtn = new javax.swing.JButton();
	        otherBtn = new javax.swing.JButton();

	        setPreferredSize(new java.awt.Dimension(300, 560));

	        loginLabel.setIcon(ImageUtils.getImageIcon(ImagePath.Login));
	        unameLabel.setText("账号：");

	        upassLabel.setText("密码：");

	        serviceNameLabel.setText("服务器：");

	        remCheck.setText("记住密码");

	        autoCheck.setText("自动登陆");

	        loginBtn.setText("登 陆");

	        otherBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource(ImagePath.Arrow_Right))); // NOI18N

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(loginLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(38, 38, 38)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(upassLabel)
	                    .addComponent(serviceNameLabel)
	                    .addComponent(unameLabel))
	                .addGap(5, 5, 5)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(remCheck)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(autoCheck))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(unameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(upassInput, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(serviceNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(otherBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(39, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(45, 45, 45)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(unameLabel)
	                        .addComponent(unameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(otherBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(upassLabel)
	                    .addComponent(upassInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(25, 25, 25)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(serviceNameLabel)
	                    .addComponent(serviceNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(31, 31, 31)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(remCheck)
	                    .addComponent(autoCheck))
	                .addGap(18, 18, 18)
	                .addComponent(loginBtn)
	                .addGap(0, 69, Short.MAX_VALUE))
	        );
	 }
	 
	 
	
}
