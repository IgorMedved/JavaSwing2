package javaswing2_gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel implements ActionListener {
	private JLabel mLblName;
	private JLabel mLblOccupation;

	private JTextField mTxtFldName;
	private JTextField mTxtFldOccupation;
	private JButton mBtnOk;
	private JList mAgeList;
	private JComboBox mEmpCombo;
	private JCheckBox mCitizenCheck;
	private JTextField mTaxField;
	private JLabel mTaxLabel;
	private JRadioButton mMaleRadio;
	private JRadioButton mFemaleRadio;
	private ButtonGroup mGenderGroup;

	private FormEventListener mFormEventListener;

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		mLblName = new JLabel("Name: ");
		mLblOccupation = new JLabel("Occupation: ");
		mTxtFldOccupation = new JTextField(10);
		mTxtFldName = new JTextField(10);
		mBtnOk = new JButton("OK");
		mAgeList = new JList();
		mEmpCombo = new JComboBox();
		mCitizenCheck = new JCheckBox();
		mTaxField = new JTextField(10);
		mTaxLabel = new JLabel("Tax ID: ");
		
	
		
		// Set up Mnemonics
		mBtnOk.setMnemonic(KeyEvent.VK_O);
		
		mLblName.setDisplayedMnemonic(KeyEvent.VK_N);
		mLblName.setLabelFor(mTxtFldName);
		
		mLblOccupation.setDisplayedMnemonic(KeyEvent.VK_C);
		mLblOccupation.setLabelFor(mTxtFldOccupation);
		
		

		mMaleRadio = new JRadioButton("male");
		mFemaleRadio = new JRadioButton("female");
		mGenderGroup = new ButtonGroup();
		
		mMaleRadio.setActionCommand("male");
		mFemaleRadio.setActionCommand("female");
		
		mMaleRadio.setSelected(true);

		// set up gender Radio
		mGenderGroup.add(mMaleRadio);
		mGenderGroup.add(mFemaleRadio);
		
		

		// set up tax ID

		mTaxLabel.setEnabled(false);
		mTaxField.setEnabled(false);

		mCitizenCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isTicked = mCitizenCheck.isSelected();
				mTaxLabel.setEnabled(isTicked);
				mTaxField.setEnabled(isTicked);

			}

		});

		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");

		mEmpCombo.setModel(empModel);
		mEmpCombo.setSelectedIndex(0);
		mEmpCombo.setEditable(true);

		DefaultListModel ageListModel = new DefaultListModel();
		ageListModel.addElement(new AgeCategory(0, "Under 18"));
		ageListModel.addElement(new AgeCategory(1, "18 to 65"));
		ageListModel.addElement(new AgeCategory(2, "65 and over"));

		mAgeList.setModel(ageListModel);

		mAgeList.setPreferredSize(new Dimension(110, 70));
		mAgeList.setBorder(BorderFactory.createEtchedBorder());
		mAgeList.setSelectedIndex(1);

		mBtnOk.addActionListener(this);

		componentLayout();

	}

	public void componentLayout() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;

		///////////////////// FIRST ROW //////////////////////

		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(mLblName, gc);

		gc.gridx = 1;

		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(mTxtFldName, gc);

		///////////////////// SECOND ROW //////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(mLblOccupation, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(mTxtFldOccupation, gc);

		///////////////////// THIRD ROW //////////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Age: "), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(mAgeList, gc);

		///////////////////// Fourth ROW //////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Employment: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(mEmpCombo, gc);

		///////////////////// Fifth ROW //////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("US Citizen: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(mCitizenCheck, gc);

		///////////////////// sixth ROW //////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(mTaxLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(mTaxField, gc);

		///////////////////// seventh ROW //////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Gender: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(mMaleRadio, gc);

		///////////////////// eighth ROW //////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .05;

		

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(mFemaleRadio, gc);

		///////////////////// LAST ROW //////////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(mBtnOk, gc);

		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (mFormEventListener != null) {
			String name = mTxtFldName.getText();
			String occupation = mTxtFldOccupation.getText();
			AgeCategory ageCat = (AgeCategory) mAgeList.getSelectedValue();
			String empCat = (String) mEmpCombo.getSelectedItem();

			String taxId = (mCitizenCheck.isSelected() ? mTaxField.getText() : "");

			boolean isUsCitizen = mCitizenCheck.isSelected();
			
			String gender = mGenderGroup.getSelection().getActionCommand();

			System.out.println(empCat);
			FormEvent ev = new FormEvent(e.getSource(), name, occupation, ageCat.getId(), empCat, taxId, isUsCitizen, gender);
			mFormEventListener.formEventOccurred(ev);
		}
	}

	public void setFormEventListener(FormEventListener listener) {
		mFormEventListener = listener;
	}

}

class EmploymentCategory {
	private int mId;
	private String mText;

	public EmploymentCategory(int id, String text) {
		mId = id;
		mText = text;
	}

	public int getId() {
		return mId;
	}

	public String toString() {
		return mText;
	}
}

class AgeCategory {
	private int mId;
	private String mText;

	public AgeCategory(int id, String text) {
		mId = id;
		mText = text;
	}

	public String toString() {
		return mText;
	}

	public int getId() {
		return mId;
	}
}
