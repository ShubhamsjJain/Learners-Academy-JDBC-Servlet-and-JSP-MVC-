<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.simplilearn.web.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Teachers</title>
<link  type="text/css" rel="stylesheet" href="css/style.css">
</head>

<% 

//Get the students from the request object (sent by the servlet)

List<Teacher> listteachers = (List<Teacher>)request.getAttribute("TEACHER_LIST");
%>

<body>

<div id="wrapper">

     <div id="header">
          <h2>Learners Academy</h2>
     </div>
  </div>
  
  
  <div id="container">
  
      <div id="content">
      
         <!-- Adding Add Teacher Button that will take to add-teacher-form -->
         
         <input type='button' 
         value='Add Teacher' 
         onclick="window.location.href='add-teacher-form.jsp';return false;" 
         class='add-student-button'
         />
         
         
         <table width=100%>
         
           <tr>
           
              <th>Teacher ID</th>
              <th>Teacher Name</th>
              <th>Teacher E-Mail</th>
              <th>Teacher City</th>
              <th>Action</th>
              
           </tr>
           
           <% for(Teacher teacher : listteachers) {%>
	 
            
           <tr>
             <td><%= teacher.getId()%></td>
             <td><%= teacher.getFullName()%></td>
             <td><%= teacher.geteMail()%></td>
             <td><%= teacher.getCity()%></td>
              <td><input type='button' 
                        value='Update' 
                        onclick="window.location.href='teachers?command=LOAD&teacherId=<%= teacher.getId()%>';return false;" 
                        class='add-student-button'
                 />
             
                 
                 <!-- Delete button with javascript to prompt user to confirm whether they want to delete -->
                 
                 <a type="button"
                    class="add-student-button"
                    href="teachers?command=DELETE&teacherId=<%= teacher.getId()%>"
                    onclick="if(!(confirm('Are you sure you want to delete?')))return false"
                    >Delete</a>
                 
             
           </tr>
           
           <% }%>
         </table>
         
         
      </div>
  
  </div>


</body>
</html>