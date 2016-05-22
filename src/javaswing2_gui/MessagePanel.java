package javaswing2_gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

class ServerInfo
{
	private String mName;
	private int mId;
	public ServerInfo(String pName, int pId)
	{
		super();
		mName = pName;
		mId = pId;
	}
	public String getName()
	{
		return mName;
	}
	public int getId()
	{
		return mId;
	}
	
	public String toString()
	{
		return mName;
	}
}

public class MessagePanel extends JPanel
{
	private JTree mServerTree;
	private DefaultTreeCellRenderer mTreeCellRenderer;
	
	public MessagePanel()
	{
		mTreeCellRenderer = new DefaultTreeCellRenderer();
		mServerTree = new JTree(createTree());
		
		mTreeCellRenderer.setLeafIcon(Utils.createIcon("/images/server16.gif"));
		mTreeCellRenderer.setOpenIcon(Utils.createIcon("/images/webcomponent16.gif"));
		mTreeCellRenderer.setClosedIcon(Utils.createIcon("/images/webcomponentadd16.gif"));
		
		mServerTree.setCellRenderer(mTreeCellRenderer);
		mServerTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		mServerTree.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent pArg0)
			{
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) mServerTree.getLastSelectedPathComponent();
				Object userObject = node.getUserObject();
				
				if (userObject instanceof ServerInfo)
				{
					int id = ((ServerInfo)userObject).getId();
					System.out.println("Got userObject with ID " + id);
				}
				
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
		DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 0));
		DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 1));
		DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 2));
		
		branch1.add(server1);
		branch1.add(server2);
		branch1.add(server3);
		
		DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
		DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London",3));
		DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Manchester",4));
		DefaultMutableTreeNode server6 = new DefaultMutableTreeNode(new ServerInfo("Liverpool",5));
		
		branch2.add(server4);
		branch2.add(server5);
		branch2.add(server6);
		
		top.add(branch1);
		top.add(branch2);
		
		return top;
	}
}

