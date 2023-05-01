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

	public Student loadstudent(int id)throws Exception {
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		int Studentid = id;
		
		Student tempStudent= null;;
		
	try {
		
		//Step 1: Get a connection to the database

			myConn = dataSource.getConnection();
			
		//Step 2: Create a SQL statement
		
			String q = "select*from students where id =?";
			                                                
			myStmt = myConn.prepareStatement(q);
			
		//Step 3: Set values	
			
			myStmt.setInt(1, Studentid);
			
		//Step 4: Execute SQL Query
		
			myRs = myStmt.executeQuery();
			
		//Step 5: Process the result set
			

			if(myRs.next()) {
				
			             //Retrieve data from ResultSet row
				
				String name_full = myRs.getString("name_full");
				String e_mail    = myRs.getString("e_mail");
				String city      = myRs.getString("city");
				
			             //Create new student object
			
				tempStudent = new Student(Studentid,name_full,e_mail,city);
				
				
			}else{
				 
				throw new Exception("Couldn't find studentid: "+ Studentid);
			}
			return tempStudent;
			
			
		}finally {
			
			// Step 6: close JDBC objects
			
			close(myConn,myStmt,myRs);  // close method defined below
		}
	

		
	}

	public void updatestudent(Student theStudent) throws Exception{
		
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		
		
		
	try {
		
		Student studentupdation = theStudent;
		
		int id = studentupdation.getId();
		String name = studentupdation.getFullName();
		String e_mail = studentupdation.geteMail();
		String city = studentupdation.getCity();
		
		//Step 1: Get a connection to the database

			myConn = dataSource.getConnection();
			
		//Step 2: Create a SQL statement
		
			String q = "update students set name_full=?, e_mail=?, city=? where id = ?";	
			
			myStmt = myConn.prepareStatement(q);
			
		//Step 3: Set the values  
			
			
			myStmt.setString(1, name );
			myStmt.setString(2, e_mail );
			myStmt.setString(3, city);
			myStmt.setInt(4, id);
			
		//Step 4: Execute SQL Query			
			
			myStmt.execute(); // Insert some product into students table.
						
		}finally {
			
		// Step 4:  Close JDBC objects
			
			close(myConn,myStmt,null);  // close method defined below
		}
		
	}

        
}
