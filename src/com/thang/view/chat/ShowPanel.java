package com.thang.view.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXEditorPane;

import com.thang.tools.model.ChatInfo;
import com.thang.tools.util.DateUtils;

public class ShowPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private JXEditorPane show=null;
	private HTMLEditorKit htmlKit=null;
	
	private JToolBar tools=null;
	private HTMLDocument doc=null;
	
	private StringBuilder sber=null;
	
	private ChatPanel parent=null;
	private ChatInfo chatInfo=null;
	
	public ShowPanel(ChatPanel cp){
		sber=new StringBuilder();
		this.parent=cp;
		chatInfo=cp.getChatInfo();
		initTools();
		initEditor();
		init();
	}
	
	private void initTools(){
		 tools=new JToolBar();
		 tools.add(new JXButton(chatInfo.getChatUser().split("@")[0]));
	}
	
	private void initEditor(){
        htmlKit=new HTMLEditorKit();
        htmlKit.setDefaultCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        htmlKit.setLinkCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    doc=(HTMLDocument)htmlKit.createDefaultDocument();
	    
	    show=new JXEditorPane("text/html","");
	    show.setPreferredSize(new Dimension(400,290));
	    show.setBackground(Color.WHITE);
	    show.setEditable(false);
	    show.setBorder(BorderFactory.createLoweredBevelBorder());
	    show.setEditorKit(htmlKit);
	    show.setDocument(doc);
	    
	}
	
	private void init(){
	    setLayout(new BorderLayout());
	    setSize(400, 290);
    	setBackground(Color.WHITE);
    	setForeground(Color.WHITE);
	    add(tools,BorderLayout.NORTH);
	    add(new JScrollPane(show),BorderLayout.CENTER);
	}
	
	public void addText(String txt){
			sber.append("<span style='margin-left:12px;cursor:pointer' title='"+DateUtils.getSystime()+"'>"+txt+"</span>");
			show.setText(sber.toString());
	}

}
