package com.simplilearn.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TeacherDbUtil {
	
private DataSource dataSource;
	
	public TeacherDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Teacher> getTeachers()throws Exception{
		
		//Create an ArrayList of teachers
		
		List<Teacher> teachers = new ArrayList<>();
		
				Connection myConn =null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				
			try {
				
				//Step 1: Get a connection to the database

					myConn = dataSource.getConnection();
					
				//Step 2: Create a SQL statement
				
					String q = "select*from teachers";
					                                                
					myStmt = myConn.createStatement();
					
				//Step 3: Execute SQL Query
				
					myRs = myStmt.executeQuery(q);
					
				//Step 4: Process the result set
					

					while(myRs.next()) {
						
					
					             //Retrieve data from ResultSet row
						
						int id = myRs.getInt("id");
						String name = myRs.getString("name");
						String e_mail = myRs.getString("e_mail");
						String city = myRs.getString("city");
						
					             //Create new student object
					
						Teacher tempTeacher = new Teacher(id,name,e_mail,city);
						
					             //add it to the list of students
					
						teachers.add(tempTeacher);  //putting object of Student class in ArrayList
					}
					
					return teachers;
					
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

	public void addTeacher(Teacher theTeacher)throws Exception {
		
        Teacher teacher = theTeacher;
		
		String name = teacher.getFullName();
		String e_mail = teacher.geteMail();
		String city = teacher.getCity();
		
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		
		
		
	try {
		
		//Step 1: Get a connection to the database

			myConn = dataSource.getConnection();
			
		//Step 2: Create a SQL statement
		
			String q = "insert into teachers (name,e_mail,city) VALUES(?,?,?)";	
			
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

	public Teacher loadteacher(int id) throws Exception {
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		int Teacherid = id;
		
		Teacher tempTeacher= null;;
		
	try {
		
		//Step 1: Get a connection to the database

			myConn = dataSource.getConnection();
			
		//Step 2: Create a SQL statement
		
			String q = "select*from teachers where id =?";
			                                                
			myStmt = myConn.prepareStatement(q);
			
		//Step 3: Set values	
			
			myStmt.setInt(1, Teacherid);
			
		//Step 4: Execute SQL Query
		
			myRs = myStmt.executeQuery();
			
		//Step 5: Process the result set
			

			if(myRs.next()) {
				
			             //Retrieve data from ResultSet row
				
				String name_full = myRs.getString("name");
				String e_mail    = myRs.getString("e_mail");
				String city      = myRs.getString("city");
				
			             //Create new student object
			
				tempTeacher = new Teacher(Teacherid,name_full,e_mail,city);
				
				
			}else{
				 
				throw new Exception("Couldn't find teacherid: "+ Teacherid);
			}
			return tempTeacher;
			
			
		}finally {
			
			// Step 6: close JDBC objects
			
			close(myConn,myStmt,myRs);  // close method defined below
		}
		
	}

	public void updateteacher(Teacher updatetea)throws Exception {
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		
		
		
	try {
		
		Teacher teacherupdation = updatetea;
		
		int id = teacherupdation.getId();
		String name = teacherupdation.getFullName();
		String e_mail = teacherupdation.geteMail();
		String city = teacherupdation.getCity();
		
		//Step 1: Get a connection to the database

			myConn = dataSource.getConnection();
			
		//Step 2: Create a SQL statement
		
			String q = "update teachers set name=?, e_mail=?, city=? where id = ?";	
			
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

	public void deleteteacher(int id)throws Exception {
		
        int teaId = id;
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		
		
		
	try {
		
		
		//Step 1: Get a connection to the database

			myConn = dataSource.getConnection();
			
		//Step 2: Create a SQL statement
		
			String q = "delete from teachers where id = ?";	
			
			myStmt = myConn.prepareStatement(q);
			
		//Step 3: Set the values  
			
			myStmt.setInt(1, teaId);
			
		//Step 4: Execute SQL Query			
			
			myStmt.execute(); // Insert some product into students table.
						
		}finally {
			
		// Step 4:  Close JDBC objects
			
			close(myConn,myStmt,null);  // close method defined below
		}
		
	}

}
