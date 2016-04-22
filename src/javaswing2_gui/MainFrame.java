package javaswing2_gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import javaswing2_controller.Controller;

public class MainFrame extends JFrame
{
  private TextPanel mTextPanel;
  
  private Toolbar mToolbar;
  private FormPanel mFormPanel;
  
  private JFileChooser mFileChooser;
  private Controller mController;
  private TablePanel mTablePanel;
 
  public MainFrame()
  {
    super ("Hello World");
   
    setLayout (new BorderLayout());
   
    mTextPanel= new TextPanel();
    mToolbar = new Toolbar();
    mFormPanel = new FormPanel();
    mFileChooser = new JFileChooser();
    mFileChooser.addChoosableFileFilter(new PersonFileFilter());
    mController = new Controller();
    mTablePanel = new TablePanel();
    
    mTablePanel.setData(mController.getPeople());
    
    setJMenuBar(createMenuBar());
    
    // pass a callback to mToolbar which would be called when buttons are pressed
    mToolbar.setStringListener(new StringListener(){

		@Override
		public void textEmitted(String text)
		{
			mTextPanel.appendText(text);
			
		}
    	
    });
    
    mFormPanel.setFormEventListener(new FormEventListener(){
      
      @Override
      public void formEventOccurred (FormEvent e)
      {
        mController.addPerson(e);
        mTablePanel.refresh();
      }
    });
                               
   
    add (mTablePanel, BorderLayout.CENTER);

    add (mToolbar, BorderLayout.NORTH);
    
    add (mFormPanel, BorderLayout.WEST);
   
    
   
    
    
    setSize(600,400);
    setMinimumSize(new Dimension(500,400));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  private JMenuBar createMenuBar()
  {
	  JMenuBar menuBar = new JMenuBar();
	  
	  JMenu fileMenu = new JMenu("File");
	  
	  
	  JMenuItem exportDataItem = new JMenuItem("Export Data...");
	  JMenuItem importDataItem = new JMenuItem("Import Data...");
	  JMenuItem exitItem = new JMenuItem("Exit");
	  
	  fileMenu.add(exportDataItem);
	  fileMenu.add(importDataItem);
	  fileMenu.addSeparator();
	  fileMenu.add(exitItem);
	  
	  JMenu windowMenu = new JMenu("Window");
	  
	  JMenu showMenu = new JMenu("Show");
	  JCheckBoxMenuItem showFormCheckBoxItem = new JCheckBoxMenuItem("Person Form");
	  showFormCheckBoxItem.setSelected(true);
	  showMenu.add(showFormCheckBoxItem);
	  windowMenu.add(showMenu);
	  
	  menuBar.add(fileMenu);
	  menuBar.add(windowMenu);
	  //menuBar.add(showMenu);
	  
	  showFormCheckBoxItem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)e.getSource();
			
			mFormPanel.setVisible(menuItem.isSelected());
			
		}
		  
	  });
	  
	  fileMenu.setMnemonic(KeyEvent.VK_F);
	  exitItem.setMnemonic(KeyEvent.VK_X);
	  windowMenu.setMnemonic(KeyEvent.VK_W);
	  
	  exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
	  
	  importDataItem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (mFileChooser.showOpenDialog(MainFrame.this)== JFileChooser.APPROVE_OPTION)
				System.out.println(mFileChooser.getSelectedFile());
			
		}
		  
	  });
	  
	  exportDataItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (mFileChooser.showSaveDialog(MainFrame.this)== JFileChooser.APPROVE_OPTION)
					System.out.println(mFileChooser.getSelectedFile());
				
			}
			  
		  });
	  exitItem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			/*String text = JOptionPane.showInputDialog(MainFrame.this, "Enter you user name", "Enter User Name", JOptionPane.OK_OPTION|JOptionPane.QUESTION_MESSAGE);
			
			System.out.println(text);*/
			
			
			int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit the application?", "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
			
			
			if (action == JOptionPane.OK_OPTION)
				System.exit(0);
			
		}
		  
			  
	  });
	  
	  return menuBar;
	  
  }
 
 
}