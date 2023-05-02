<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.simplilearn.web.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Class Form</title>
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
  
      <h3>Update Class</h3>      
         
         
         
         <% 
         
       //Get the Class_Name class object having class info from the request object (sent by the servlet)
         Class_Name theClass= (Class_Name)request.getAttribute("CLASS_LOAD");
         
         %>
         
         <form action="classes" method="get">
         
              <input type="hidden" name="command" value="UPDATE" /> <!-- So that servletControllerStudent will understand what method(list,add,load,update,delete) does it need to call -->
              <input type="hidden" name="classId" value="<%= theClass.getId()%>" />
         
             <table>
        
                <tbody>
                  <tr>
                      <td><label>Class Name:</label></td>
                      <td><input type="text" name="name" value="<%= theClass.getclass_name()%>" required></td>
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
            <a href="classes">Back to List</a> 
         </p>
             
           
           
        
  </div>
</body>
</html>