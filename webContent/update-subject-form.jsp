<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.simplilearn.web.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Subject Form</title>
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
  
      <h3>Update Subject</h3>      
         
         
         
         <% 
         
       //Get the Subject class object having subject info from the request object (sent by the servlet)
         Subject theSubject= (Subject)request.getAttribute("SUBJECT_LOAD");
         
         %>
         
         <form action="subjects" method="get">
         
              <input type="hidden" name="command" value="UPDATE" /> <!-- So that servletControllerStudent will understand what method(list,add,load,update,delete) does it need to call -->
              <input type="hidden" name="subjectId" value="<%= theSubject.getId()%>" />
         
             <table>
        
                <tbody>
                  <tr>
                      <td><label>Subject Name:</label></td>
                      <td><input type="text" name="name" value="<%= theSubject.getsubject_name()%>" required></td>
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
            <a href="subjects">Back to List</a> 
         </p>
             
           
           
        
  </div>
</body>
</html>