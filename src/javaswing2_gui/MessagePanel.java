package javaswing2_gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class MessagePanel extends JPanel
{
	private JTree mServerTree;
	
	public MessagePanel()
	{
		mServerTree = new JTree(createTree());
		
		mServerTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		mServerTree.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent pArg0)
			{
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) mServerTree.getLastSelectedPathComponent();
				Object userObject = node.getUserObject();
				
				System.out.println(userObject);
			}
			
		});
		
		setLayout (new BorderLayout());
		
		add (new JScrollPane(mServerTree), BorderLayout.CENTER);
	}
	
	
	private DefaultMutableTreeNode createTree()
	{
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");
		
		DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
		DefaultMutableTreeNode server1 = new DefaultMutableTreeNode("New York");
		DefaultMutableTreeNode server2 = new DefaultMutableTreeNode("Boston");
		DefaultMutableTreeNode server3 = new DefaultMutableTreeNode("Los Angeles");
		
		branch1.add(server1);
		branch1.add(server2);
		branch1.add(server3);
		
		DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
		DefaultMutableTreeNode server4 = new DefaultMutableTreeNode("London");
		DefaultMutableTreeNode server5 = new DefaultMutableTreeNode("Manchester");
		DefaultMutableTreeNode server6 = new DefaultMutableTreeNode("Liverpool");
		
		branch2.add(server4);
		branch2.add(server5);
		branch2.add(server6);
		
		top.add(branch1);
		top.add(branch2);
		
		return top;
	}
}

