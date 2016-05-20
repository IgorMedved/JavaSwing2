package javaswing2_model;

import java.io.Serializable;

public class Person implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3514330541820258129L;
	private static int count = 1;
	private int mId;
	
	private String mName;
	private String mOccupation;
	private AgeCategory mAgeCategory;
	private EmploymentCategory mEmploymentCategory;
	private String mTaxId;
	private boolean mUSCitizen;
	private Gender mGender;
	
	
	
					
	
	public Person(String name, String occupation, AgeCategory ageCategory,
			EmploymentCategory employmentCategory, String taxId,
			boolean uSCitizen, Gender gender)
	{
		
		this (count, name, occupation, ageCategory, employmentCategory, taxId, uSCitizen, gender);
		/*mName = name;
		mOccupation = occupation;
		mAgeCategory = ageCategory;
		mEmploymentCategory = employmentCategory;
		mTaxId = taxId;
		mUSCitizen = uSCitizen;
		mGender = gender;*/
		
		//mId = count;
		//count++;
	}
	
	
	public Person(int pId, String pName, String pOccupation, AgeCategory pAgeCategory,
			EmploymentCategory pEmploymentCategory, String pTaxId, boolean pUSCitizen, Gender pGender) {
		
		mId = pId;
		mName = pName;
		mOccupation = pOccupation;
		mAgeCategory = pAgeCategory;
		mEmploymentCategory = pEmploymentCategory;
		mTaxId = pTaxId;
		mUSCitizen = pUSCitizen;
		mGender = pGender;
		count ++;
	}


	public int getId()
	{
		return mId;
	}
	public String getName()
	{
		return mName;
	}
	public String getOccupation()
	{
		return mOccupation;
	}
	public AgeCategory getAgeCategory()
	{
		return mAgeCategory;
	}
	public EmploymentCategory getEmploymentCategory()
	{
		return mEmploymentCategory;
	}
	public String getTaxId()
	{
		return mTaxId;
	}
	public boolean isUSCitizen()
	{
		return mUSCitizen;
	}
	public Gender getGender()
	{
		return mGender;
	}
	public void setId(int id)
	{
		mId = id;
	}
	public void setName(String name)
	{
		mName = name;
	}
	public void setOccupation(String occupation)
	{
		mOccupation = occupation;
	}
	public void setAgeCategory(AgeCategory ageCategory)
	{
		mAgeCategory = ageCategory;
	}
	public void setEmploymentCategory(EmploymentCategory employmentCategory)
	{
		mEmploymentCategory = employmentCategory;
	}
	public void setTaxId(String taxId)
	{
		mTaxId = taxId;
	}
	public void setUSCitizen(boolean uSCitizen)
	{
		mUSCitizen = uSCitizen;
	}
	public void setGender(Gender gender)
	{
		mGender = gender;
	}
	
	public String toString()
	{
		return mId + " " + mName;
	}
	
	
	
	
	
}
