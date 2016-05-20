package javaswing2_gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener
{
  private JButton mSaveButton;
  private JButton mRefreshButton;
  
  private ToolbarListener mToolbarListener; 
 
  public Toolbar()
  {
    setBorder(BorderFactory.createEtchedBorder());
	  
	mSaveButton = new JButton ("Save");
    mRefreshButton = new JButton ("Refresh");
    
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
