package javaswing2_gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel
{
  private JTextArea mTextArea;
 
  public TextPanel()
  {
    mTextArea = new JTextArea();
    setLayout(new BorderLayout());
   
    add(new JScrollPane(mTextArea), BorderLayout.CENTER);
   
   
  }
 
  public void appendText (String text)
  {
    if (mTextArea!= null)
      mTextArea.append(text);
  }
   
}
