package com.simplilearn.web.jdbc;

public class Student {
	
	private int id;
	private String fullName;
	private String eMail;
	private String City;
	
	
	
	public Student(String fullName, String eMail, String city) {
		
		this.fullName = fullName;
		this.eMail = eMail;
		City = city;
	}



	public Student(int id, String fullName, String eMail, String city) {
		
		this.id = id;
		this.fullName = fullName;
		this.eMail = eMail;
		City = city;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String geteMail() {
		return eMail;
	}



	public void seteMail(String eMail) {
		this.eMail = eMail;
	}



	public String getCity() {
		return City;
	}



	public void setCity(String city) {
		City = city;
	}



	//ToString method is useful in debugging and logging
	
	public String toString() {
		return "Student [id=" + this.id + ", fullName=" + this.fullName + ", eMail=" + this.eMail + ", City=" + this.City + "]";
	}
	
	
	
	

}
