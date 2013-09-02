package com.thang.view.main;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;

import com.thang.tools.model.ChatInfo;
import com.thang.tools.model.ClientModel;
import com.thang.tools.model.ImagePath;
import com.thang.tools.model.MyEntry;
import com.thang.tools.util.ImageUtils;
import com.thang.tools.util.StrUtils;
import com.thang.tools.util.WindowManager;
import com.thang.view.Interflow;
import com.thang.view.chat.ChatWindow;
import com.thang.view.other.EntryInfoWindow;

public class RosterPanel extends JPanel implements RosterListener{

	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeTableNode rootNode=null;
	private DefaultTreeTableModel model=null;
	private ClientModel client=null;   
	
	
	private JXTreeTable tree=null;
	private EntryInfoWindow infoWin=null;
	private javax.swing.JScrollPane jScrollPaned;
	 /**
	   * Creates new form RosterPanel
	   */
	public RosterPanel() {
		setLayout(new BorderLayout());
		 client=Interflow.getClient();
	     initTree();
	     initComponents();
	}
	    
	public void initTree(){
		
		rootNode=new DefaultMutableTreeTableNode();
		
		 Roster roster=client.getRoster();
		 Collection<RosterGroup> groups=roster.getGroups();
		 Collection<RosterEntry> entries=null;
		 for(RosterGroup group:groups){
			 DefaultMutableTreeTableNode groupNode= new DefaultMutableTreeTableNode(group.getName());
			 entries=group.getEntries();
			 for(RosterEntry entry:entries){
				 MyEntry ent=new MyEntry(entry);
				 DefaultMutableTreeTableNode node=new DefaultMutableTreeTableNode(ent);
				 groupNode.add(node);
			 }
			 rootNode.add(groupNode);
		 }
	        
	        model=new DefaultTreeTableModel(rootNode);
	        
	   }

	    
	  @SuppressWarnings("unchecked")
	  private void initComponents() {

	        jScrollPaned = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        tree = new JXTreeTable(model);
	        
	        
	        tree.setEditable(false);
            tree.setClosedIcon(ImageUtils.getImageIcon(ImagePath.Tree_Folder_Close));
            tree.setOpenIcon(ImageUtils.getImageIcon(ImagePath.Tree_Folder_Open));
            tree.setLeafIcon(ImageUtils.getImageIcon(ImagePath.Head_Woman));
	        tree.addMouseListener(new TreeMouseListener(this));
	        
	        jScrollPaned.setViewportView(tree);

	        add(jScrollPaned,BorderLayout.CENTER);
	        
	    }                       
	                    
	    
		@Override
		public void entriesAdded(Collection<String> arg0) {
			
		}

		@Override
		public void entriesDeleted(Collection<String> arg0) {
			
		}

		@Override
		public void entriesUpdated(Collection<String> arg0) {
			
		}

		@Override
		public void presenceChanged(Presence p) {
			String from=p.getFrom();
			if(StrUtils.validStr(from)){
				if(p.isAvailable()){
					System.out.println(from.split("@")[0]+" 上线！");
				}else{
					System.out.println(from.split("@")[0]+" 离线！");
				}
			}
			
			
		}
		
	public JXTreeTable getTreeTable(){
		return tree;
	}
	
	public ClientModel getClientModel(){
		return client;
	}
}


class TreeMouseListener extends MouseAdapter{
	
	private JXTreeTable tree;
	private ClientModel client=null;
	public TreeMouseListener(RosterPanel rp){
		this.tree=rp.getTreeTable();
		this.client=rp.getClientModel();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(2==e.getClickCount()){
			TreePath path = tree.getPathForLocation(e.getX(), e.getY());
			
			if(null!=path){
				DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode) path.getLastPathComponent();
				if(!node.isLeaf()){
					return;
				}
				
				final MyEntry entry=(MyEntry)node.getUserObject();
				SwingUtilities.invokeLater(new Runnable(){

					@Override
					public void run() {
						ChatInfo chatInfo=new ChatInfo(client.getUser(),entry.getUser(),null);
						//Chat chat=client.creatChat(entry.getUser());
						ChatWindow chatWin=new ChatWindow(chatInfo);
						//chatWin.getChat().addMessageListener(chatWin);
						//chatWin.setChat();
						WindowManager.addChatWindow(entry.getUser().split("@")[0],chatWin);
						chatWin.setVisible(true);
					}
					
				});
			}
		}
		
		
	}
	
	
}
