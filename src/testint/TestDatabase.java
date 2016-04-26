package testint;

import javaswing2_model.Database;

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
		db.disconnect();
	}

}
