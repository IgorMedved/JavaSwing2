package javaswing2_gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
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
  private PrefsDialog mPrefsDialog;
  private Preferences mPrefs;
  private JSplitPane mSplitPane;
  
 
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
    mPrefsDialog = new PrefsDialog(this);
    mPrefs = Preferences.userRoot().node("db");
    mSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mFormPanel, mTablePanel);
    
    
    mSplitPane.setOneTouchExpandable(true);
    mTablePanel.setData(mController.getPeople());
    
    
    mTablePanel.setPersonTableListener(new PersonTableListener(){
    	public void rowDeleted (int row)
    	{
    		mController.deletePerson(row);
    	}
    });
    
    setJMenuBar(createMenuBar());
    
    // pass a callback to mToolbar which would be called when buttons are pressed
    mToolbar.setToolbarListener(new ToolbarListener(){

		@Override
		public void saveEventOccurred()
		{
			connect();
			
			
			try {
				mController.save();
			} catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(MainFrame.this, "Unable to save to database", "Database connection problem", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		@Override
		public void refreshEventOccurred()
		{
			connect();
			
			try {
				mController.load();
			} catch (SQLException e)
			{
				JOptionPane.showMessageDialog(MainFrame.this, "Unable to load from database", "Database connection problem", JOptionPane.ERROR_MESSAGE);
			}
			
			mTablePanel.refresh();
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
                               
   
    //add (mTablePanel, BorderLayout.CENTER);

    add (mToolbar, BorderLayout.PAGE_START);
    
    //add (mFormPanel, BorderLayout.WEST);
    
    add (mSplitPane, BorderLayout.CENTER);
   
    
   
    addWindowListener(new WindowAdapter (){

		@Override
		public void windowClosing(WindowEvent e) {
			mController.disconnect();
			
			dispose();
			System.gc();
		}

		
    	
    });
    
    setSize(600,400);
    setMinimumSize(new Dimension(500,400));
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
	  JMenuItem prefsItem = new JMenuItem("Preferences...");
	  JCheckBoxMenuItem showFormCheckBoxItem = new JCheckBoxMenuItem("Person Form");
	  showFormCheckBoxItem.setSelected(true);
	  showMenu.add(showFormCheckBoxItem);
	  windowMenu.add(showMenu);
	  windowMenu.add(prefsItem);
	  
	  prefsItem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			mPrefsDialog.setVisible(true);
			
		}
		  
	  });
	  
	  mPrefsDialog.setListener(new PrefsListener()
	  {

		@Override
		public void preferencesSet(PrefsDialogEvent e) 
		{
			mPrefs.put("user", e.getUserName());
			mPrefs.put("password", e.getPassword());
			mPrefs.putInt("port", e.getConnectionPort());
		}
		  
	  });
	  
	  
	  String user = mPrefs.get("user", "");
	  String password = mPrefs.get("password", "");
	  int port = mPrefs.getInt("port", 3306);
	  
	  mPrefsDialog.setDefaults(user, password, port);
	  
	  menuBar.add(fileMenu);
	  menuBar.add(windowMenu);
	  //menuBar.add(showMenu);
	  
	  showFormCheckBoxItem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)e.getSource();
			
			if (menuItem.isSelected())
				mSplitPane.setDividerLocation((int)mFormPanel.getMinimumSize().getWidth());
			mFormPanel.setVisible(menuItem.isSelected());
			
		}
		  
	  });
	  
	  fileMenu.setMnemonic(KeyEvent.VK_F);
	  exitItem.setMnemonic(KeyEvent.VK_X);
	  windowMenu.setMnemonic(KeyEvent.VK_W);
	  
	  exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
	  
	  importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
	  
	  prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
	  
	  importDataItem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (mFileChooser.showOpenDialog(MainFrame.this)== JFileChooser.APPROVE_OPTION)
			{
				try {
					mController.loadFromFile(mFileChooser.getSelectedFile());
					mTablePanel.refresh();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		  
	  });
	  
	  exportDataItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (mFileChooser.showOpenDialog(MainFrame.this)== JFileChooser.APPROVE_OPTION)
				{
					try {
						mController.saveToFile(mFileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
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
			{
				WindowListener[] listeners = getWindowListeners();
				
				for (WindowListener listener: listeners)
				{
					listener.windowClosing(new WindowEvent(MainFrame.this, 0));
				}
			}
			
		}
		  
			  
	  });
	  
	  return menuBar;
	  
  }
  
  private void connect()
  {
	  try {
			mController.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to database", "Database connection problem", JOptionPane.ERROR_MESSAGE);
		}
  }
 
 
}