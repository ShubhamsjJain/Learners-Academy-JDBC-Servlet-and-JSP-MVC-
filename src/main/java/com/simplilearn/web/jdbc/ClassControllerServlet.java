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


@WebServlet("/ClassControllerServlet")

public class ClassControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
private ClassDbUtil classdbutil;
	
	//Java EE Resource Injection
	
		@Resource(name="jdbc/learnersacademy2")
		private DataSource dataSource;

	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
        // Building connection between servlet(controller) and dbutil(model)
		
        //Create our classdbutil....and pass connection pool/dataSource into it
        
        try {
        	classdbutil = new ClassDbUtil(dataSource);
        	
        }catch(Exception e){
        	throw new ServletException(e);
        }   
	}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String theCommand = request.getParameter("command");  // taking command parameter having value ADD or UPDATE or DELETE
			
			// if theCommand is missing,default it to listclasses
			
			if(theCommand == null) {
				
				theCommand = "LIST";
			}
				
			switch(theCommand) {
			
			case "LIST":
				
				//List the classes in MVC fashion
				
				listclasses(request,response);
				break;
				
			case "ADD":
				
				//Add the class in MVC fashion
				
				addclass(request,response);
				break;
				
            case "LOAD":
				
            	//Load all classes info from database based on classes id got from list-class.jsp
				
				loadclass(request,response);
				break;
				
            case "UPDATE":
				
            	//Update the class in MVC fashion
				
				updateclass(request,response);
				break;
				
            case "DELETE":
				
            	//Delete the class in MVC fashion
				
				deleteclass(request,response);
				break;
				
			default:
				listclasses(request,response);
				break;
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

private void deleteclass(HttpServletRequest request, HttpServletResponse response)throws Exception {
	
	//Get class Id from url linked to delete button
	
	int id = Integer.parseInt(request.getParameter("classId"));
	
	//Send this id to classdbutil class which will further send it to database to delete class
	
	classdbutil.deleteclass(id);
	
	//Go back to list page
	
	listclasses(request,response);
	
}

private void updateclass(HttpServletRequest request, HttpServletResponse response)throws Exception {
	
	// Get the values from update form
	
	int classId = Integer.parseInt(request.getParameter("classId"));
	String name = request.getParameter("name");
	
	
	//Put these values in class object
	
	Class_Name updatecla = new Class_Name(classId,name);
	
	//Send this class object to classdbutil class from where it will be updated to database
	
	classdbutil.updateclass(updatecla);
	
	//Return to list page
	
	listclasses(request,response);
	
}

private void loadclass(HttpServletRequest request, HttpServletResponse response)throws Exception {
	
	 //Take class id from url linked to update button
	
	int id = Integer.parseInt(request.getParameter("classId"));  //converting string into int
	
	//Send it to classDbUtil in order to get class info from database
	
	Class_Name theClass = classdbutil.loadteacher(id);
	
	//Place class in the request attribute
	
	request.setAttribute("CLASS_LOAD", theClass);
	
	//send it to update-student-form.jsp
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/update-class-form.jsp");
	dispatcher.forward(request, response);
	
}

private void addclass(HttpServletRequest request, HttpServletResponse response)throws Exception {
	
	//Read class info from form data
	
	
	String class_name = request.getParameter("name");
	
	
	//Create a new class object
	
	Class_Name theClass = new Class_Name(class_name);
	
	//Send this class object to classDbUtil in order to insert it into database
	
	classdbutil.addClass(theClass);
	
	//Send back to list-teacher page
	
	listclasses(request,response);
	
}

private void listclasses(HttpServletRequest request, HttpServletResponse response)throws Exception {
	
	        //Get classes class objects in list from db util class
	
			List<Class_Name> classes = classdbutil.getClasses();
			
			//Add classes to the Attribute of request object
			
			request.setAttribute("CLASS_LIST", classes);
			
			// Send to JSP page (view) using Request dispatcher (Building connection between servlet(controller) and JSP(view))
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("list-classes.jsp");//where to forward
			dispatcher.forward(request, response);//what to forward
	
}

}
