package testint;

import java.sql.SQLException;

import javaswing2_model.AgeCategory;
import javaswing2_model.Database;
import javaswing2_model.EmploymentCategory;
import javaswing2_model.Gender;
import javaswing2_model.Person;

public class TestDatabase {

	public static void main(String[] args)
	{
		System.out.println("Testing Database connection");
		Database db = new Database();
		try
		{
			db.connect();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.addPerson (new Person("Joe", "builder", AgeCategory.ADULT, EmploymentCategory.EMPLOYED, 
						"777", true, Gender.MALE));
		db.addPerson (new Person("Sue", "artist", AgeCategory.SENIOR, EmploymentCategory.SELF_EMPLOYED, 
				null, false, Gender.FEMALE));
		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnect();
	}

}
