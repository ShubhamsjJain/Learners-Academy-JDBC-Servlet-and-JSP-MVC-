package com.simplilearn.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ClassDbUtil {

private DataSource dataSource;
	
	public ClassDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Class_Name> getClasses()throws Exception {
		
		
		       //Create an ArrayList of classes
		
				List<Class_Name> classes = new ArrayList<>();
				
						Connection myConn =null;
						Statement myStmt = null;
						ResultSet myRs = null;
						
						
					try {
						
						//Step 1: Get a connection to the database

							myConn = dataSource.getConnection();
							
						//Step 2: Create a SQL statement
						
							String q = "select*from classes";
							                                                
							myStmt = myConn.createStatement();
							
						//Step 3: Execute SQL Query
						
							myRs = myStmt.executeQuery(q);
							
						//Step 4: Process the result set
							

							while(myRs.next()) {
								
							
							             //Retrieve data from ResultSet row
								
								int id = myRs.getInt("id");
								String class_name = myRs.getString("class_name");
								
								
							             //Create new class object
							
								Class_Name tempClass = new Class_Name(id,class_name);
								
							             //add it to the list of classes
							
								classes.add(tempClass);  //putting object of Class class in ArrayList
							}
							
							return classes;
							
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

			public void addClass(Class_Name theClass)throws Exception {
				
				Class_Name class_name = theClass;
				
				String name = class_name.getclass_name();
				
				
				
				Connection myConn =null;
				PreparedStatement myStmt = null;
				
				
				
			try {
				
				//Step 1: Get a connection to the database

					myConn = dataSource.getConnection();
					
				//Step 2: Create a SQL statement
				
					String q = "insert into classes (class_name) VALUES(?)";	
					
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

			public Class_Name loadteacher(int id)throws Exception {
				
				Connection myConn =null;
				PreparedStatement myStmt = null;
				ResultSet myRs = null;
				
				int Classid = id;
				
				Class_Name tempClass= null;;
				
			try {
				
				//Step 1: Get a connection to the database

					myConn = dataSource.getConnection();
					
				//Step 2: Create a SQL statement
				
					String q = "select*from classes where id =?";
					                                                
					myStmt = myConn.prepareStatement(q);
					
				//Step 3: Set values	
					
					myStmt.setInt(1, Classid);
					
				//Step 4: Execute SQL Query
				
					myRs = myStmt.executeQuery();
					
				//Step 5: Process the result set
					

					if(myRs.next()) {
						
					             //Retrieve data from ResultSet row
						
						String class_name = myRs.getString("class_name");
						
						
					             //Create new class object
					
						tempClass = new Class_Name(Classid,class_name);
						
						
					}else{
						 
						throw new Exception("Couldn't find Classid: "+ Classid);
					}
					return tempClass;
					
					
				}finally {
					
					// Step 6: close JDBC objects
					
					close(myConn,myStmt,myRs);  // close method defined below
				}
				
			}

			public void updateclass(Class_Name updatecla)throws Exception {
				
				Connection myConn =null;
				PreparedStatement myStmt = null;
				
				
				
			try {
				
				Class_Name classupdation = updatecla;
				
				int id = classupdation.getId();
				String name = classupdation.getclass_name();
				
				
				//Step 1: Get a connection to the database

					myConn = dataSource.getConnection();
					
				//Step 2: Create a SQL statement
				
					String q = "update classes set class_name=? where id = ?";	
					
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

			public void deleteclass(int id) throws Exception{
				
				int claId = id;
				
				Connection myConn =null;
				PreparedStatement myStmt = null;
				
				
				
			try {
				
				
				//Step 1: Get a connection to the database

					myConn = dataSource.getConnection();
					
				//Step 2: Create a SQL statement
				
					String q = "delete from classes where id = ?";	
					
					myStmt = myConn.prepareStatement(q);
					
				//Step 3: Set the values  
					
					myStmt.setInt(1, claId);
					
				//Step 4: Execute SQL Query			
					
					myStmt.execute(); // Insert some product into students table.
								
				}finally {
					
				// Step 4:  Close JDBC objects
					
					close(myConn,myStmt,null);  // close method defined below
				}
				
			}
			
}

