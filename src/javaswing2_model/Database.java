package javaswing2_model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		
		String connectionUrl = "jdbc:mysql://localhost:3306/swingtest?autoReconnect=true&useSSL=false";
		
		mCon = DriverManager.getConnection(connectionUrl, "igor", "STUDENT");
		
		
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
	
	public void save() throws SQLException
	{
		String checkSql = "select count(*) as count from people where id=?";
		String insertSql = "insert into people (id, name, age, employment_status, tax_id, us_citizen, gender, occupation) values (?,?,?,?,?,?,?,?)";
		String updateSql = "update people set name=?, age=?, employment_status=?, tax_id=?, us_citizen=?, gender=?, occupation=? where id=?";
		
		PreparedStatement checkStmt = mCon.prepareStatement(checkSql);
		PreparedStatement insertStatement = mCon.prepareStatement(insertSql);
		PreparedStatement updateStatement = mCon.prepareStatement(updateSql);
		
		for (Person person: people)
		{
			int id = person.getId();
			String name = person.getName();
			String occupation = person.getOccupation();
			AgeCategory age = person.getAgeCategory();
			EmploymentCategory empCat = person.getEmploymentCategory();
			String taxId = person.getTaxId();
			boolean isCitizen = person.isUSCitizen();
			Gender gender = person.getGender();
			
			
			checkStmt.setInt(1,id); // parameterIndex, value 
			
			ResultSet checkResult = checkStmt.executeQuery();
			
			checkResult.next();
			
			int count = checkResult.getInt(1);
			
			if (count == 0)
			{
				System.out.println("Inserting person with id :" + id);
				int col = 1;
				
				insertStatement.setInt(col++, id);
				insertStatement.setString(col++, name);
				insertStatement.setString(col++, age.name());
				insertStatement.setString(col++, empCat.name());
				insertStatement.setString(col++, taxId);
				insertStatement.setBoolean(col++, isCitizen);
				insertStatement.setString(col++, gender.name());
				insertStatement.setString(col++, occupation);
				
				insertStatement.executeUpdate();
			}
			else
			{
				System.out.println("Updating person with id: " + id );
				
				int col =1;
				
				updateStatement.setString(col++, name);
				updateStatement.setString(col++, age.name());
				updateStatement.setString(col++, empCat.name());
				updateStatement.setString(col++, taxId);
				updateStatement.setBoolean(col++, isCitizen);
				updateStatement.setString(col++, gender.name());
				updateStatement.setString(col++, occupation);
				
				updateStatement.setInt(col++, id);
				
				updateStatement.executeUpdate();
			}
			
			System.out.println("Count for person with ID " + id + " is " + count);
			
		}
		
		updateStatement.close();
		insertStatement.close();
		checkStmt.close();
	}

}
