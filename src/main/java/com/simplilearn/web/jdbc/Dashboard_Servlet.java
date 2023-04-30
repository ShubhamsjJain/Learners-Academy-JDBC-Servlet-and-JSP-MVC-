package com.simplilearn.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Dashboard_Servlet
 */
@WebServlet("/Dashboard_Servlet")
public class Dashboard_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get getWriter object
		
		PrintWriter out = response.getWriter();
		
		// setting format of response content
		
		response.setContentType("text/html");
		
		
		//requesting parameters and printing on browser
		
		String e_mail_Id = request.getParameter("e-mail Id");
		String Password = request.getParameter("Password");
		
		if(e_mail_Id.equals("abc.123@gmail.com") && Password.equals("dashboard")) {
			
			out.print("<html><body>");
			out.print("<link rel=\"stylesheet\" href=\"css/styles.css\">");
			out.print("<h1>Welcome to Admin Dashboard<h1>");
			
			out.print("<form action= \"index.html\" method= \"post\" align= \"right\">");
			out.print("<button>Logout</button>");
			out.print("</form>");		
			
			out.print("<hr width= \"100%\" size= \"7\" color= \"grey\">");
			out.print("<h3>");  
			out.print("E-Mail: " + e_mail_Id);
			out.print("<br>");
			out.print("Password: " + Password);
			out.print("</h3>");
			
			//STUDENTS
			
			out.print("<a href=students>View Students</a><br>"); 
			
			
			
			
			out.print("</body></html>");
		}else {
			out.print("Wrong ID or Password.Please enter correct values");
			response.sendRedirect("index.html");
		}
	}

}
