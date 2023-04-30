package com.simplilearn.web.jdbc;


import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class StudentDbUtil {
	
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Student> getStudents()throws Exception{
		
		//Create an ArrayList of students
		
		List<Student> students = new ArrayList<>();
		
				Connection myConn =null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				
			try {
				
				//Step 1: Get a connection to the database

					myConn = dataSource.getConnection();
					
				//Step 2: Create a SQL statement
				
					String q = "select*from students";
					                                                
					myStmt = myConn.createStatement();
					
				//Step 3: Execute SQL Query
				
					myRs = myStmt.executeQuery(q);
					
				//Step 4: Process the result set
					

					while(myRs.next()) {
						
					
					             //Retrieve data from ResultSet row
						
						int id = myRs.getInt("id");
						String name_full = myRs.getString("name_full");
						String e_mail = myRs.getString("e_mail");
						String city = myRs.getString("city");
						
					             //Create new student object
					
						Student tempStudent = new Student(id,name_full,e_mail,city);
						
					             //add it to the list of students
					
						students.add(tempStudent);  //putting object of Student class in ArrayList
					}
					
					return students;
					
				}finally {
					
					// Step 5:  Close JDBC objects
					
					close(myConn,myStmt,myRs);  // close method defined below
				}
			
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			
			if(myConn != null) {
			myConn.close();
		    }
		
			if(myStmt != null) {
			myStmt.close();
		    }
		
			if(myRs != null) {
			myRs.close();
		    }
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

	public void addStudent(Student theStudent)throws Exception {
		
		Student student = theStudent;
		
		String name = student.getFullName();
		String e_mail = student.geteMail();
		String city = student.getCity();
		
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		
		
		
	try {
		
		//Step 1: Get a connection to the database

			myConn = dataSource.getConnection();
			
		//Step 2: Create a SQL statement
		
			String q = "insert into students (name_full,e_mail,city) VALUES(?,?,?)";	
			
			myStmt = myConn.prepareStatement(q);
			
		//Step 3: Set the values
			
			
			myStmt.setString(1, name );
			myStmt.setString(2, e_mail );
			myStmt.setString(3, city);
			
		//Step 4: Execute SQL Query			
			
			myStmt.executeUpdate(); // Insert some product into students table.
						
		}finally {
			
		// Step 4:  Close JDBC objects
			
			close(myConn,myStmt,null);  // close method defined below
		}
	
}

        
}
