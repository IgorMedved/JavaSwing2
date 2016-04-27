package javaswing2_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class PrefsDialog extends JDialog
{

	private JButton mOkButton;
	private JButton mCancelButton;
	private JSpinner mPortSpinner;
	private SpinnerNumberModel mSpinnerModel;
	private JTextField mUserField;
	private JPasswordField mPasswordField;
	
	private PrefsListener mListener;
	
	public PrefsDialog(JFrame parent)
	{
		super(parent, "Preferences", false);
		
		mOkButton = new JButton("OK");
		mCancelButton = new JButton("Cancel");
		
		mSpinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		mPortSpinner = new JSpinner(mSpinnerModel);
		mUserField = new JTextField(10);
		mPasswordField = new JPasswordField(10);
		
		mPasswordField.setEchoChar('*');
		
		layoutControls();
		
		mOkButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (mListener!= null)
				{
				
					Integer port = (Integer)mPortSpinner.getValue();
				
				
					String user =  mUserField.getText();
					char[] password = mPasswordField.getPassword();
					
					mListener.preferencesSet(new PrefsDialogEvent (this, user, new String(password), port));
				
					
				}
				PrefsDialog.this.setVisible(false);
			}
			
		});
		
		mCancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				
				PrefsDialog.this.setVisible(false);
			}
			
		});
		
		setSize(340, 250);
		setLocationRelativeTo(parent);
	}
	
	
	public void setListener(PrefsListener listener)
	{
		mListener = listener;
	}
	
	
	
	public void setDefaults(String user, String password, int port)
	{
		mUserField.setText(user);
		mPasswordField.setText(password);
		mSpinnerModel.setValue(port);
	}
	
	
	
	private void layoutControls()
	{
		
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		int space = 15;
		
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		Border titleBorder = BorderFactory.createTitledBorder("Database Preferences");
		
		controlsPanel.setLayout(new GridBagLayout());
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		//buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		/////////////////////////// Controls Panel ////////////////
		
		gc.gridx = 0;
		gc.insets = new Insets(10,0,5,15);
		gc.anchor = GridBagConstraints.EAST;
		controlsPanel.add (new JLabel("User Name: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		controlsPanel.add (mUserField, gc);
		
		/////////////////////////// Next row ////////////////
		gc.gridy++;
		gc.gridx = 0;
		//gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.EAST;
		controlsPanel.add (new JLabel("Password: "), gc);
		gc.anchor = GridBagConstraints.WEST;
		gc.gridx = 1;
		controlsPanel.add (mPasswordField, gc);
		
		/////////////////////////// Next row ////////////////
		gc.gridy++;
		gc.gridx = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST;
		controlsPanel.add (new JLabel("Port: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		controlsPanel.add (mPortSpinner, gc);
		
		
		/////////////////////////// Buttons Panel ////////////////
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		buttonsPanel.add (mOkButton);
		buttonsPanel.add (mCancelButton);
		
		Dimension btnSize = mCancelButton.getPreferredSize();
		
		mOkButton.setPreferredSize(btnSize);
		
		
		setLayout (new BorderLayout());
		
		add (controlsPanel, BorderLayout.CENTER);
		gc.gridy++;
		add (buttonsPanel, BorderLayout.SOUTH);
	}
}
