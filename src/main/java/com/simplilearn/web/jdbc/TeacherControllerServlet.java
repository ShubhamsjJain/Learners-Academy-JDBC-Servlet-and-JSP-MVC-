package com.simplilearn.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



@WebServlet("/TeacherControllerServlet")
public class TeacherControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TeacherDbUtil teacherdbutil;
	
	//Java EE Resource Injection
	
		@Resource(name="jdbc/learnersacademy2")
		private DataSource dataSource;

	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
        // Building connection between servlet(controller) and dbutil(model)
		
        //Create our teacherdbutil....and pass connection pool/dataSource into it
        
        try {
        	teacherdbutil = new TeacherDbUtil(dataSource);
        	
        }catch(Exception e){
        	throw new ServletException(e);
        }   
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String theCommand = request.getParameter("command");  // taking command parameter having value ADD or UPDATE or DELETE
			
			// if theCommand is missing,default it to liststudents
			
			if(theCommand == null) {
				
				theCommand = "LIST";
			}
				
			switch(theCommand) {
			
			case "LIST":
				
				//List the teachers in MVC fashion
				
				listteachers(request,response);
				break;
				
			case "ADD":
				
				//Add the teachers in MVC fashion
				
				addteacher(request,response);
				break;
				
            case "LOAD":
				
            	//Load all teacher info from database based on teacher id got from list-teacher.jsp
				
				loadteacher(request,response);
				break;
				
            case "UPDATE":
				
            	//Update the teacher in MVC fashion
				
				updateteacher(request,response);
				break;
				
            case "DELETE":
				
            	//Delete the teacher in MVC fashion
				
				deleteteacher(request,response);
				break;
				
			default:
				listteachers(request,response);
				break;
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteteacher(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		        //Get teacher Id from url linked to delete button
		
				int id = Integer.parseInt(request.getParameter("teacherId"));
				
				//Send this id to teacherdbutil class which will further send it to database to delete teacher
				
				teacherdbutil.deleteteacher(id);
				
				//Go back to list page
				
				listteachers(request,response);
		
	}

	private void updateteacher(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		       // Get the values from update form
		
				int teacherId = Integer.parseInt(request.getParameter("teacherId"));
				String name = request.getParameter("name");
				String e_mail = request.getParameter("e_mail");
				String place = request.getParameter("place");
				
				//Put these values in teacher object
				
				Teacher updatetea = new Teacher(teacherId,name,e_mail,place);
				
				//Send this teacher object to teacherdbutil class from where it will be updated to database
				
				teacherdbutil.updateteacher(updatetea);
				
				//Return to list page
				
				listteachers(request,response);
		
	}

	private void loadteacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		        //Take teacher id from url linked to update button
		
				int id = Integer.parseInt(request.getParameter("teacherId"));  //converting string into int
				
				//Send it to studentDbUtil in order to get student info from database
				
				Teacher theTeacher = teacherdbutil.loadteacher(id);
				
				//Place student in the request attribute
				
				request.setAttribute("TEACHER_LOAD", theTeacher);
				
				//send it to update-student-form.jsp
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/update-teacher-form.jsp");
				dispatcher.forward(request, response);
		
	}

	private void addteacher(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		       //Read teacher info from form data
		
		
				String name = request.getParameter("name");
				String e_mail = request.getParameter("e_mail");
				String place = request.getParameter("place");
				
				//Create a new teacher object
				
				Teacher theTeacher = new Teacher(name,e_mail,place);
				
				//Send this teacher object to teacherDbUtil in order to insert it into database
				
				teacherdbutil.addTeacher(theTeacher);
				
				//Send back to list-teacher page
				
				listteachers(request,response);
		
	}

	private void listteachers(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//Get teachers class objects in list from db util class
		
		List<Teacher> teachers = teacherdbutil.getTeachers();
		
		//Add students to the Attribute of request object
		
		request.setAttribute("TEACHER_LIST", teachers);
		
		// Send to JSP page (view) using Request dispatcher (Building connection between servlet(controller) and JSP(view))
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-teachers.jsp");//where to forward
		dispatcher.forward(request, response);//what to forward
		
		
	}

}
