package javaswing2_gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class ServerTreeCellRenderer implements TreeCellRenderer
{

	private JCheckBox mLeafRenderer;
	private DefaultTreeCellRenderer mNonLeafRenderer;
	
	private Color mTextForeground;
	private Color mTextBackground;
	private Color mSelectedForeground;
	private Color mSelectedBackground;
	
	
	public ServerTreeCellRenderer()
	{
		mLeafRenderer = new JCheckBox();
		mNonLeafRenderer = new DefaultTreeCellRenderer();
		
		mNonLeafRenderer.setLeafIcon(Utils.createIcon("/images/server16.gif"));
		mNonLeafRenderer.setOpenIcon(Utils.createIcon("/images/webcomponent16.gif"));
		mNonLeafRenderer.setClosedIcon(Utils.createIcon("/images/webcomponentadd16.gif"));
	
		mTextForeground = UIManager.getColor("Tree.textForeground");
		mTextBackground = UIManager.getColor("Tree.textBackground");
		mSelectedForeground = UIManager.getColor("Tree.selectionForeground");
		mSelectedBackground = UIManager.getColor("Tree.selectionBackground");
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus)
	{
		if (leaf)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
			ServerInfo nodeInfo = (ServerInfo)node.getUserObject();
			
			
			
			if (selected)
			{
				mLeafRenderer.setForeground(mSelectedForeground);
				mLeafRenderer.setBackground(mSelectedBackground);
			}
			else
			{
				mLeafRenderer.setForeground(mTextForeground);
				mLeafRenderer.setBackground(mTextBackground);
			}
			
			mLeafRenderer.setText(nodeInfo.getName());
			mLeafRenderer.setSelected(nodeInfo.isChecked());
			
			return mLeafRenderer;
		}
		else
			return mNonLeafRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
	}

}
