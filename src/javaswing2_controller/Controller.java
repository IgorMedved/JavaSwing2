package javaswing2_controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javaswing2_gui.FormEvent;
import javaswing2_model.AgeCategory;
import javaswing2_model.Database;
import javaswing2_model.EmploymentCategory;
import javaswing2_model.Gender;
import javaswing2_model.Person;

public class Controller
{
	private Database mDb = new Database();
	
	public List<Person> getPeople()
	{
		return mDb.getPeople();
	}
	
	public void addPerson (FormEvent e)
	{
		String name = e.getName();
        String occupation = e.getOccupation();
        
        int ageCategoryIndex = e.getAgeCategory();
        String empCat = e.getEmploymentCategory();
        String taxId = e.getTaxId();
        boolean isUs = e.isUSCitizen();
        String gender = e.getGender();
        
        
        AgeCategory ageCategory = null;
        
        switch(ageCategoryIndex)
        {
        case 0:
        	ageCategory = AgeCategory.CHILD;
        	break;
        case 1:
        	ageCategory = AgeCategory.ADULT;
        	break;
        case 2:
        	ageCategory = AgeCategory.SENIOR;
        	break;
        }
        
        EmploymentCategory empCategory;
        
        if (empCat.equals("employed"))
        	empCategory = EmploymentCategory.EMPLOYED;
        else if (empCat.equals("unemployed"))
        	empCategory = EmploymentCategory.UNEMPLOYED;
        else if (empCat.equals("self-employed"))
        	empCategory = EmploymentCategory.SELF_EMPLOYED;
        
        else
        {
        	empCategory = EmploymentCategory.OTHER;
        	System.err.println(empCat);
        }
        	
        Gender genderCat;
        
        if (gender.equals("male"))
        	genderCat = Gender.MALE;
        else
        	genderCat = Gender.FEMALE;
        		
        
        Person person = new Person(name, occupation, ageCategory, empCategory,taxId, isUs, genderCat);
        
        mDb.addPerson(person);
        
        
	}
	
	public void saveToFile (File file) throws IOException
	{
		mDb.saveToFile(file);
		
	}
	
	public void loadFromFile(File file) throws IOException
	{
		mDb.loadFromFile(file);
	}
	
	public void deletePerson(int row)
	{
		if (mDb!= null)
			mDb.deletePerson(row);
	}
	
	public void save() throws SQLException
	{
		mDb.save();
	}
	
	public void load() throws SQLException
	{
		mDb.load();
	}
	
	public void connect() throws Exception
	{
		mDb.connect();
	}
	
	public void disconnect()
	{
		mDb.disconnect();
	}
}



