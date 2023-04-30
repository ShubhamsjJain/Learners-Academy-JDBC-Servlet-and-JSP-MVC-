package com.simplilearn.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/StudentControllerServlet")

public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDbUtil studentdbutil;
	
	//Java EE Resource Injection
	
	@Resource(name="jdbc/learnersacademy2")
	private DataSource dataSource;
	
	
       
    @Override
	public void init() throws ServletException {
		super.init();
		
		// Building connection between servlet(controller) and dbutil(model)
		
        //Create our studentdbutil....and pass connection pool/dataSource into it
        
        try {
        	studentdbutil = new StudentDbUtil(dataSource);
        	
        }catch(Exception e){
        	throw new ServletException(e);
        }     	
        
   }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String theCommand = request.getParameter("command");   // taking command parameter having value ADD or UPDATE or DELETE
			
			// if theCommand is missing,default it to liststudents
			
			if(theCommand == null) {
				theCommand = "LIST";
			}
			
			switch(theCommand){
				
			
		        case "LIST":
					//List the students in MVC fashion
					
					listStudents(request,response);   //Provided below
					break;
					
			    case "ADD":
			
			        //Add the students in MVC fashion 
			    
			        addStudents(request,response);    //Provided below
			        break;
			        
			     default:
			    	 
			    	 listStudents(request,response);   //Provided below
					 
			
			}
			
			
			
		} catch (Exception e) {
						
			e.printStackTrace();
		}  
	
	}


	private void addStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//Read student info from form data
		
		
		String name = request.getParameter("name");
		String e_mail = request.getParameter("e_mail");
		String place = request.getParameter("place");
		
		//Create a new student object
		
		Student theStudent = new Student(name,e_mail,place);
		
		//Send this student object to studentDbUtil in order to insert it into database
		
		studentdbutil.addStudent(theStudent);
		
		//Send back to list-student page
		
		listStudents(request,response);
		
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//Get students from db util class
		
		List<Student> liststudents = studentdbutil.getStudents();
				
		//Add students to the Attribute of request object
		
		request.setAttribute("STUDENTS_LIST", liststudents);
		
		// Send to JSP page (view) using Request dispatcher (Building connection between servlet(controller) and JSP(view))

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-students.jsp"); //where to forward
		dispatcher.forward(request, response); //what to forward
		
	}

	
}
