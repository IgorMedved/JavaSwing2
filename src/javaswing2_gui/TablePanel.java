package javaswing2_gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javaswing2_model.Person;

public class TablePanel extends JPanel {
	
	private JTable mTable;
	private PersonTableModel mTableModel;
	private JPopupMenu mPopup;
	
	private PersonTableListener mPersonTableListener;
	
	
	public TablePanel()
	{
		mTableModel = new PersonTableModel();
		mTable  = new JTable(mTableModel);
		mPopup = new JPopupMenu();
		
		JMenuItem removeItem = new JMenuItem("Delete row");
		mPopup.add(removeItem);
		
		mTable.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) 
			{
				int row  = mTable.rowAtPoint(e.getPoint());
				
				mTable.getSelectionModel().setSelectionInterval(row, row);
				
				if(e.getButton() == MouseEvent.BUTTON3)
					mPopup.show(mTable, e.getX(), e.getY());
			}
			
		});
		
		removeItem.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = mTable.getSelectedRow();
				
				if (mPersonTableListener!=null)
				{
					mPersonTableListener.rowDeleted(row);
					mTableModel.fireTableRowsDeleted(row, row);
				}
				
			}
			
		});
		
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

	public void setPersonTableListener (PersonTableListener listener)
	{
		mPersonTableListener = listener;
	}

}
