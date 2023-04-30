package com.simplilearn.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
		
		//Building connection between servlet(controller) and JSP(view)
		
		//List the students in MVC fashion
		
		try {
			
			listStudents(request,response);   //Provided below
			
		} catch (Exception e) {
						
			e.printStackTrace();
		}  
	
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//Get students from db util class
		
		List<Student> liststudents = studentdbutil.getStudents();
				
		//Add students to the Attribute of request object
		
		request.setAttribute("STUDENTS_LIST", liststudents);
		
		// Send to JSP page (view) using Request dispatcher
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-students.jsp"); //where to forward
		dispatcher.forward(request, response); //what to forward
		
	}

	
}
