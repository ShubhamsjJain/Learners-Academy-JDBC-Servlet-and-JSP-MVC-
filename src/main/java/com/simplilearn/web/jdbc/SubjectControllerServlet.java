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


@WebServlet("/SubjectControllerServlet")
public class SubjectControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
private SubjectDbUtil subjectdbutil;
	
	//Java EE Resource Injection
	
		@Resource(name="jdbc/learnersacademy2")
		private DataSource dataSource;

	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
        // Building connection between servlet(controller) and dbutil(model)
		
        //Create our subjectdbutil....and pass connection pool/dataSource into it
        
        try {
        	subjectdbutil = new SubjectDbUtil(dataSource);
        	
        }catch(Exception e){
        	throw new ServletException(e);
        }   
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		try {
			
			String theCommand = request.getParameter("command");  // taking command parameter having value ADD or UPDATE or DELETE
			
			// if theCommand is missing,default it to listsubjects
			
			if(theCommand == null) {
				
				theCommand = "LIST";
			}
				
			switch(theCommand) {
			
			case "LIST":
				
				//List the subjects in MVC fashion
				
				listsubjects(request,response);
				break;
				
            case "ADD":
				
				//Add the subjects in MVC fashion
				
				addsubject(request,response);
				break;
				
             case "LOAD":
				
				//Load the subjects in MVC fashion
				
				loadsubject(request,response);
				break;
				
             case "UPDATE":
 				
 				//Update the subjects in MVC fashion
 				
 				updatesubject(request,response);
 				break;
 				
             case "DELETE":
  				
  				//Delete the subjects in MVC fashion
  				
  				deletesubject(request,response);
  				break;
				
			
				
			default:
				listsubjects(request,response);
				break;
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void deletesubject(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//Get subject Id from url linked to delete button
		
		int id = Integer.parseInt(request.getParameter("subjectId"));
		
		//Send this id to subjectdbutil class which will further send it to database to delete class
		
		subjectdbutil.deleteclass(id);
		
		//Go back to list page
		
		listsubjects(request,response);
		
	}


	private void updatesubject(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		// Get the values from update form
		
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		String name = request.getParameter("name");
		
		
		//Put these values in subject object
		
		Subject updatesub = new Subject(subjectId,name);
		
		//Send this subject object to subjectdbutil class from where it will be updated to database
		
		subjectdbutil.updatesubject(updatesub);
		
		//Return to list page
		
		listsubjects(request,response);
	}


	private void loadsubject(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//Take subject id from url linked to update button
		
		int id = Integer.parseInt(request.getParameter("subjectId"));  //converting string into int
		
		//Send it to studentDbUtil in order to get subject info from database
		
		Subject theSubject = subjectdbutil.loadteacher(id);
		
		//Place class in the request attribute
		
		request.setAttribute("SUBJECT_LOAD", theSubject);
		
		//send it to update-student-form.jsp
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-subject-form.jsp");
		dispatcher.forward(request, response);
		
	}


	private void addsubject(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//Read subject info from form data
		
		
		String subject = request.getParameter("name");
		
		
		//Create a new subject object
		
		Subject theSubject = new Subject(subject);
		
		//Send this subject object to subjectDbUtil in order to insert it into database
		
		subjectdbutil.addSubject(theSubject);
		
		//Send back to list-teacher page
		
		listsubjects(request,response);
		
	}


	private void listsubjects(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//Get Subject class objects in list from db util class
		
		List<Subject> subjects = subjectdbutil.getSubjects();
		
		//Add subjects to the Attribute of request object
		
		request.setAttribute("SUBJECT_LIST", subjects);
		
		// Send to JSP page (view) using Request dispatcher (Building connection between servlet(controller) and JSP(view))
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-subjects.jsp");//where to forward
		dispatcher.forward(request, response);//what to forward
		
	}

}
