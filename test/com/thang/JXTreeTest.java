package com.thang;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

public class JXTreeTest extends JXFrame{

	public JXTreeTest(){
		super("good",true);
		setSize(300, 300);
		setLocationRelativeTo(null);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		JXTreeTest jxt=new JXTreeTest();
		
		/*String[] a={"aaa","bbb"};
		 JXTree tree = new JXTree(a);
		 tree.setRolloverEnabled(true);
		 tree.setCellRenderer(new DefaultTreeRenderer());
		 tree.addHighlighter(new ColorHighlighter(HighlightPredicate.ROLLOVER_ROW,null, Color.RED));
		 
		 jxt.add(tree);*/
		
		  
		  //System.out.println(treeTable.getModel());
		  //DefaultTreeTableModel treeModel=(DefaultTreeTableModel)treeTable.getModel();
		  DefaultTreeTableModel treeModel=new DefaultTreeTableModel();
		  treeModel.setRoot(new DefaultMutableTreeTableNode("root"));
		  DefaultMutableTreeTableNode root=(DefaultMutableTreeTableNode)treeModel.getRoot();
		  DefaultMutableTreeTableNode a=null;
		  DefaultMutableTreeTableNode b=null;
		  a=new DefaultMutableTreeTableNode("aaa");
		  b=new DefaultMutableTreeTableNode("bbb");
		  
		  a.add(new DefaultMutableTreeTableNode("good job"));
		  a.add(new DefaultMutableTreeTableNode("good jobddd"));
		  root.add(a);
		  
		  b.add(new DefaultMutableTreeTableNode("god job"));
		  b.add(new DefaultMutableTreeTableNode("god jd"));
		  root.add(b);
		  
		  
		  JXTreeTable tree=new JXTreeTable(treeModel);
		  
		  tree.addMouseListener(new MouseAdapter() {
			   @Override
			public void mouseEntered(MouseEvent e) {
				
				super.mouseEntered(e);
			}  
		   });
		  //tree.setRootVisible(true);
		  //tree.setRolloverEnabled(false);
		  tree.setEditable(false);
		  tree.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				System.out.println("================");
				
			}
			  
		  });
		  jxt.add(tree);
		 jxt.setVisible(true);
	}

}
