package javaswing2_gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

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
		
		setLayout (new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		/////////////////////////// First row ////////////////
		
		gc.gridx = 0;
		gc.insets = new Insets(10,0,0,0);
		add (new JLabel("User Name: "), gc);
		
		gc.gridx = 1;
		add (mUserField, gc);
		
		/////////////////////////// Next row ////////////////
		gc.gridy++;
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,0);
	
		add (new JLabel("Password: "), gc);

		gc.gridx = 1;
		add (mPasswordField, gc);
		
		/////////////////////////// Next row ////////////////
		gc.gridy++;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		add (new JLabel("Port: "), gc);
		gc.gridx++;
		add (mPortSpinner, gc);
		
		
		/////////////////////////// Next row ////////////////
		
		gc.gridy++;
		gc.weighty =4;
		gc.gridx = 0;
		gc.insets = new Insets(0,0,10,0);
		gc.anchor = GridBagConstraints.SOUTH;
		add (mOkButton, gc);
		
		gc.gridx++;
		add (mCancelButton, gc);
		
		mOkButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (mListener!= null)
				{
				
					Integer port = (Integer)mPortSpinner.getValue();
				
				
					String user =  mUserField.getText();
					char[] password = mPasswordField.getPassword();
					//System.out.println(user + ": " + new String(password));
					
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
		
		setSize(400, 300);
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
	
}
