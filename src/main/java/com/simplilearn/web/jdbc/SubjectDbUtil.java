package com.simplilearn.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class SubjectDbUtil {

private DataSource dataSource;
	
	public SubjectDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Subject> getSubjects()throws Exception {
		
		//Create an ArrayList of subjects
		
		List<Subject> subjects = new ArrayList<>();
		
				Connection myConn =null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				
			try {
				
				//Step 1: Get a connection to the database

					myConn = dataSource.getConnection();
					
				//Step 2: Create a SQL statement
				
					String q = "select*from subjects";
					                                                
					myStmt = myConn.createStatement();
					
				//Step 3: Execute SQL Query
				
					myRs = myStmt.executeQuery(q);
					
				//Step 4: Process the result set
					

					while(myRs.next()) {
						
					
					             //Retrieve data from ResultSet row
						
						int id = myRs.getInt("id");
						String subject_name = myRs.getString("subject_name");
						
						
					             //Create new subject object
					
						Subject tempSubject = new Subject(id,subject_name);
						
					             //add it to the list of subjects
					
						subjects.add(tempSubject);  //putting object of Subject class in ArrayList
					}
					
					return subjects;
					
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

	public void addSubject(Subject theSubject)throws Exception {
		
		Subject subject_name = theSubject;
		
		String name = subject_name.getsubject_name();
		
		
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		
		
		
	try {
		
		//Step 1: Get a connection to the database

			myConn = dataSource.getConnection();
			
		//Step 2: Create a SQL statement
		
			String q = "insert into subjects (subject_name) VALUES(?)";	
			
			myStmt = myConn.prepareStatement(q);
			
		//Step 3: Set the values
			
			
			myStmt.setString(1, name );
			
			
		//Step 4: Execute SQL Query			
			
			myStmt.executeUpdate(); // Insert some product into students table.
						
		}finally {
			
		// Step 4:  Close JDBC objects
			
			close(myConn,myStmt,null);  // close method defined below
		}
		
	}

	public Subject loadteacher(int id)throws Exception {
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		int Subjectid = id;
		
		Subject tempSubject= null;;
		
	try {
		
		//Step 1: Get a connection to the database

			myConn = dataSource.getConnection();
			
		//Step 2: Create a SQL statement
		
			String q = "select*from subjects where id =?";
			                                                
			myStmt = myConn.prepareStatement(q);
			
		//Step 3: Set values	
			
			myStmt.setInt(1, Subjectid);
			
		//Step 4: Execute SQL Query
		
			myRs = myStmt.executeQuery();
			
		//Step 5: Process the result set
			

			if(myRs.next()) {
				
			             //Retrieve data from ResultSet row
				
				String subject_name = myRs.getString("subject_name");
				
				
			             //Create new subject object
			
				tempSubject = new Subject(Subjectid,subject_name);
				
				
			}else{
				 
				throw new Exception("Couldn't find Subjectid: "+ Subjectid);
			}
			return tempSubject;
			
			
		}finally {
			
			// Step 6: close JDBC objects
			
			close(myConn,myStmt,myRs);  // close method defined below
		}
	}

	public void updatesubject(Subject updatesub)throws Exception {
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		
		
		
	try {
		
		Subject subjectupdation = updatesub;
		
		int id = subjectupdation.getId();
		String name = subjectupdation.getsubject_name();
		
		
		//Step 1: Get a connection to the database

			myConn = dataSource.getConnection();
			
		//Step 2: Create a SQL statement
		
			String q = "update subjects set subject_name=? where id = ?";	
			
			myStmt = myConn.prepareStatement(q);
			
		//Step 3: Set the values  
			
			
			myStmt.setString(1, name );
			myStmt.setInt(2, id);
			
		//Step 4: Execute SQL Query			
			
			myStmt.execute(); // Insert some product into students table.
						
		}finally {
			
		// Step 4:  Close JDBC objects
			
			close(myConn,myStmt,null);  // close method defined below
		}
		
	}

	public void deleteclass(int id) throws Exception {
		
		int subId = id;
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		
		
		
	try {
		
		
		//Step 1: Get a connection to the database

			myConn = dataSource.getConnection();
			
		//Step 2: Create a SQL statement
		
			String q = "delete from subjects where id = ?";	
			
			myStmt = myConn.prepareStatement(q);
			
		//Step 3: Set the values  
			
			myStmt.setInt(1, subId);
			
		//Step 4: Execute SQL Query			
			
			myStmt.execute(); // Insert some product into students table.
						
		}finally {
			
		// Step 4:  Close JDBC objects
			
			close(myConn,myStmt,null);  // close method defined below
		}
	}

	
	
}
