package javaswing2_model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database
{
	private List<Person> people;
	private Connection mCon;
	
	public Database()
	{
		people = new LinkedList<Person>();
	}
	
	public void addPerson (Person person)
	{
		people.add(person);
		
		
	}
	
	public void deletePerson (int row)
	{
		people.remove(row);
	}
	
	public List<Person> getPeople()
	{
		return Collections.unmodifiableList(people);
	}
	
	public void saveToFile (File file) throws IOException
	{
		FileOutputStream fos = new FileOutputStream (file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Person[] persons = people.toArray(new Person[people.size()]);
		oos.writeObject(persons);
		
		oos.close();
	}
	
	public void loadFromFile (File file) throws IOException
	{
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Person[] persons = (Person[])ois.readObject();
			
			people.clear();
			people.addAll(Arrays.asList(persons));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ois.close();
	}

	public void connect() throws Exception 
	{
		if (mCon != null) return;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			throw new Exception ("Driver not found");
		}
		
		String connectionUrl = "jdbc:mysql://localhost:3306/swingtest";
		
		mCon = DriverManager.getConnection(connectionUrl, "root", "q+64vethU8%hfg");
		
		System.out.println("Connected " + mCon);
	}
	
	public void disconnect()
	{
		if (mCon!=null)
		{
			try
			{
				mCon.close();
			} catch (SQLException e)
			{
				System.out.println("Can't close connection");
			}
		}
	}

}
