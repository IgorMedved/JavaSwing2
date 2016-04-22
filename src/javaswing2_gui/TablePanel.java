package javaswing2_gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javaswing2_model.Person;

public class TablePanel extends JPanel {
	
	private JTable mTable;
	private PersonTableModel mTableModel;
	
	
	public TablePanel()
	{
		mTableModel = new PersonTableModel();
		mTable  = new JTable();
		mTable.setModel(mTableModel);
		
		setLayout (new BorderLayout());
		
		add (new JScrollPane(mTable), BorderLayout.CENTER);
		
	}
	
	public void setData(List<Person> db)
	{
		mTableModel.setData(db);
	}
	
	public void refresh()
	{
		if (mTableModel != null)
			mTableModel.fireTableDataChanged();
	}


}
