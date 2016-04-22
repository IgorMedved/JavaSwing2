package javaswing2_gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PrefsDialog extends JDialog
{

	private JButton mOkButton;
	private JButton mCancelButton;
	private JSpinner mPortSpinner;
	private SpinnerNumberModel mSpinnerModel;
	
	public PrefsDialog(JFrame parent)
	{
		super(parent, "Preferences", false);
		
		mOkButton = new JButton("OK");
		mCancelButton = new JButton("Cancel");
		
		mSpinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		mPortSpinner = new JSpinner(mSpinnerModel);
		
		setLayout (new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		add (new JLabel("Port: "), gc);
		gc.gridx++;
		add (mPortSpinner, gc);
		
		/////////////////////////// Next row ////////////////
		
		gc.gridy++;
		gc.gridx = 0;
		add (mOkButton, gc);
		
		gc.gridx++;
		add (mCancelButton, gc);
		
		mOkButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Integer value = (Integer)mPortSpinner.getValue();
				System.out.println(value);
				
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
	
}
