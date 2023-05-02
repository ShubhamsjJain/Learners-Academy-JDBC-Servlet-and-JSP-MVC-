<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.simplilearn.web.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Classes</title>
<link  type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<% 

//Get the classes from the request object (sent by the servlet)

List<Class_Name> listclasses = (List<Class_Name>)request.getAttribute("CLASS_LIST");
%>

<body>

<div id="wrapper">

     <div id="header">
          <h2>Learners Academy</h2>
     </div>
  </div>
  
  
  <div id="container">
  
      <div id="content">
      
         <!-- Adding Add Class Button that will take to add-class-form -->
         
         <input type='button' 
         value='Add Class' 
         onclick="window.location.href='add-class-form.jsp';return false;" 
         class='add-student-button'
         />
         
         
         <table width=100%>
         
           <tr>
           
              <th>Class ID</th>
              <th>Class Name</th>
              <th>Action</th>
              
           </tr>
           
           <% for(Class_Name classes : listclasses) {%>
	 
            
           <tr>
             <td><%= classes.getId()%></td>
             <td><%= classes.getclass_name()%></td>
            
              <td><input type='button' 
                        value='Update' 
                        onclick="window.location.href='classes?command=LOAD&classId=<%= classes.getId()%>';return false;" 
                        class='add-student-button'
                 />
             
                 
                 <!-- Delete button with javascript to prompt user to confirm whether they want to delete -->
                 
                 <a type="button"
                    class="add-student-button"
                    href="classes?command=DELETE&classId=<%= classes.getId()%>"
                    onclick="if(!(confirm('Are you sure you want to delete?')))return false"
                    >Delete</a>
                 
             
           </tr>
           
           <% }%>
         </table>
         
         
      </div>
  
  </div>

</body>
</html>