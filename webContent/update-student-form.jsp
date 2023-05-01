<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.simplilearn.web.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Student Form</title>
<link  type="text/css" rel="stylesheet" href="css/style.css">
<link  type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>

  <div id="wrapper">

     <div id="header">
          <h2>Learners Academy</h2>
     </div>
     
  </div>
  
  
  <div id="container">
  
      <h3>Update Student</h3>      
         
         
         
         <% 
         
       //Get the Student class object having student info from the request object (sent by the servlet)
         Student theStudent= (Student)request.getAttribute("STUDENT_LOAD");
         
         %>
         
         <form action="students" method="get">
         
              <input type="hidden" name="command" value="UPDATE" /> <!-- So that servletControllerStudent will understand what method(list,add,load,update,delete) does it need to call -->
              <input type="hidden" name="studentId" value="<%= theStudent.getId()%>" />
         
             <table>
        
                <tbody>
                  <tr>
                      <td><label>Name:</label></td>
                      <td><input type="text" name="name" value="<%= theStudent.getFullName()%>" required></td>
                 </tr>
                 
                 <tr>
                      <td><label>E-mail:</label></td>
                      <td><input type="email" name="e_mail" value="<%= theStudent.geteMail()%>" required></td>
                </tr>
                
                <tr>
                      <td><label>City:</label></td>
                      <td><input type="text"  name="place" value="<%= theStudent.getCity()%>" required></td>
                </tr>
                
                <tr>
                      <td><label></label></td>
                      <td><input type="submit" value="Save" class="save"></td>
                </tr>
            
            </tbody>
        </table>

    </form>
         
         <div style="clear: both;"></div>
         
         <p>
            <a href="students">Back to List</a> 
         </p>
             
           
           
        
  </div>
</body>
</html>