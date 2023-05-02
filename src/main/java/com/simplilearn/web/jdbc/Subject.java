package com.simplilearn.web.jdbc;

public class Subject {

	private int id;
	private String Subject_name;
	
	
	
	
	public Subject(String Subject_name) {
		
		this.Subject_name = Subject_name;
		
	}



	public Subject(int id, String Subject_name) {
		
		this.id = id;
		this.Subject_name = Subject_name;
		
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getsubject_name() {
		return Subject_name;
	}



	public void setsubject_name(String Subject_name) {
		this.Subject_name = Subject_name;
	}



	//ToString method is useful in debugging and logging
	
	public String toString() {
		return "Subject [id=" + this.id + ", Subject name=" + this.Subject_name+ "]";
	}

}
