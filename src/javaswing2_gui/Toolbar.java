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
  private JButton mHelloButton;
  private JButton mGoodbyeButton;
  
  private StringListener mStringListener; 
 
  public Toolbar()
  {
    setBorder(BorderFactory.createEtchedBorder());
	  
	mHelloButton = new JButton ("Hello");
    mGoodbyeButton = new JButton ("Goodbye!");
    
    mHelloButton.setMnemonic(KeyEvent.VK_H);
    mGoodbyeButton.setMnemonic(KeyEvent.VK_G);
   
    setLayout (new FlowLayout(FlowLayout.LEFT));
   
    add (mHelloButton);
    add (mGoodbyeButton);
    
    mHelloButton.addActionListener(this);
    mGoodbyeButton.addActionListener(this);
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (mStringListener!=null)
    {
      JButton buttonClicked = (JButton)e.getSource();
      
      if (buttonClicked == mHelloButton)
      {
        mStringListener.textEmitted("Hello\n"); 
      }
      else if (buttonClicked == (JButton)e.getSource())
      {
    	  mStringListener.textEmitted("Goodbye!\n");
      }
    }
    
  }
  
  
  // proper way to pass messages between components using interfaces
  public void setStringListener (StringListener listener)
  {
    mStringListener = listener ;
  }
  
               
 
}
