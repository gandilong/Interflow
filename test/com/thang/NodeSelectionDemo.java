package com.thang;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jdesktop.swingx.JXTree;

public class NodeSelectionDemo extends JFrame{
	 private static final long serialVersionUID = 7912488705640918487L;
	    private JPanel contentPane;
	    private JXTree tree;
	    private JTextArea textArea;
	    
	    /**
	     * Launch the application.
	     */
	    public static void main(String[] args) {
	        try {
	            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	        } catch (Throwable e) {
	            e.printStackTrace();
	        }
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    NodeSelectionDemo frame = new NodeSelectionDemo();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	    
	    /**
	     * Create the frame.
	     */
	    public NodeSelectionDemo() {
	        addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowActivated(WindowEvent e) {
	                do_this_windowActivated(e);
	            }
	        });
	        setTitle("侦听节点事件");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 450, 200);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        contentPane.setLayout(new BorderLayout(0, 0));
	        setContentPane(contentPane);
	        
	        JPanel panel = new JPanel();
	        contentPane.add(panel, BorderLayout.CENTER);
	        panel.setLayout(new GridLayout(1, 2, 5, 5));
	        
	        JScrollPane scrollPane1 = new JScrollPane();
	        panel.add(scrollPane1);
	        
	        tree = new JXTree();
	        tree.addTreeSelectionListener(new TreeSelectionListener() {
	            public void valueChanged(TreeSelectionEvent e) {
	                do_tree_valueChanged(e);
	            }
	        });
	        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);//设置只能选择一行
	        scrollPane1.setViewportView(tree);
	        
	        JScrollPane scrollPane2 = new JScrollPane();
	        panel.add(scrollPane2);
	        
	        textArea = new JTextArea();
	        textArea.setLineWrap(true);
	        scrollPane2.setViewportView(textArea);
	    }
	    
	    protected void do_this_windowActivated(WindowEvent e) {
	        DefaultMutableTreeNode root=new DefaultMutableTreeNode("中华人民共和国");//创建根节点
	        DefaultMutableTreeNode parent1=new DefaultMutableTreeNode("山东省");
	        root.add(parent1);//增加子节点
	        DefaultMutableTreeNode parent2=new DefaultMutableTreeNode("东营市");
	        root.add(parent2);//增加子节点
	        DefaultTreeModel model=new DefaultTreeModel(root);
	        tree.setModel(model);//更新树模型
	        }
	    
	    protected void do_tree_valueChanged(TreeSelectionEvent e) {
	        TreePath path=tree.getSelectionPath();//获得用户选择的节点路径
	        if(path==null){//如果没有节点则直接返回
	            return;
	        }
	        DefaultMutableTreeNode node =(DefaultMutableTreeNode) path.getLastPathComponent();
	        String text1="你选择的是..."+path.getLastPathComponent();//路径
	        String  text2="你选择的是.."+path.getLastPathComponent();
	        if(node.toString().equals("山东省")){//选择的节点匹配
	            textArea.setText(text1);
	        }else{
	            textArea.setText(text2);
	        }
	    }
	}
	
