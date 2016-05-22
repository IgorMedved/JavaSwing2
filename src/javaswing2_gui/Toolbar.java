package javaswing2_gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar implements ActionListener
{
  private JButton mSaveButton;
  
  private JButton mRefreshButton;
  
  private ToolbarListener mToolbarListener; 
 
  public Toolbar()
  {
    
	setBorder(BorderFactory.createEtchedBorder());
	
	//setFloatable(true);
	  
	mSaveButton = new JButton ();
	mSaveButton.setIcon(Utils.createIcon ("/images/save16.gif"));
	mSaveButton.setToolTipText("Save");
	
    mRefreshButton = new JButton ();
    mRefreshButton.setIcon(Utils.createIcon("/images/refresh16.gif"));
    mRefreshButton.setToolTipText("Refresh");
    
    
    mSaveButton.setMnemonic(KeyEvent.VK_H);
    mRefreshButton.setMnemonic(KeyEvent.VK_G);
   
    setLayout (new FlowLayout(FlowLayout.LEFT));
   
    add (mSaveButton);
    add (mRefreshButton);
    
    mSaveButton.addActionListener(this);
    mRefreshButton.addActionListener(this);
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (mToolbarListener!=null)
    {
      JButton buttonClicked = (JButton)e.getSource();
      
      if (buttonClicked == mSaveButton)
      {
        mToolbarListener.saveEventOccurred(); 
      }
      else if (buttonClicked == mRefreshButton)
      {
    	  mToolbarListener.refreshEventOccurred();
      }
    }
    
  }
  
  
  // proper way to pass messages between components using interfaces
  public void setToolbarListener (ToolbarListener listener)
  {
    mToolbarListener = listener ;
  }
  
  
  
               
 
}
