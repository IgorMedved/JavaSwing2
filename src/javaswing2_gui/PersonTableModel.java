package javaswing2_gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import javaswing2_model.Person;

public class PersonTableModel extends AbstractTableModel {

	private List<Person> mDb;
	private static final String[] COL_NAMES = {"ID", "Name", "Occupation", "Age Category", "Employment Category", "US Citizen", "Tax Id", "Gender" };
	
	public PersonTableModel()
	{
		
	}
	
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return COL_NAMES[column];
	}



	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public int getRowCount() {
		return mDb== null? 0: mDb.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (mDb != null)
		{
		
			Person person = mDb.get(row);
			
			switch(col)
			{
			case 0:
				return person.getId();
			case 1:
				return person.getName();
			case 2:
				return person.getOccupation();
			case 3:
				return person.getAgeCategory();
			case 4:
				return person.getEmploymentCategory();
			case 5:
				return person.isUSCitizen();
			case 6:
				return person.getTaxId();
			case 7:
				return person.getGender();
			
			}
		}
		
		
		return null;
	}
	
	public void setData(List<Person>db)
	{
		mDb = db;
	}

}
