<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.simplilearn.web.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Subjects</title>
<link  type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<% 

//Get the subjects from the request object (sent by the servlet)

List<Subject> listsubjects = (List<Subject>)request.getAttribute("SUBJECT_LIST");
%>

<body>

<div id="wrapper">

     <div id="header">
          <h2>Learners Academy</h2>
     </div>
  </div>
  
  
  <div id="container">
  
      <div id="content">
      
         <!-- Adding Add Subject Button that will take to add-subject-form -->
         
         <input type='button' 
         value='Add Subject' 
         onclick="window.location.href='add-subject-form.jsp';return false;" 
         class='add-student-button'
         />
         
         
         <table width=100%>
         
           <tr>
           
              <th>Subject ID</th>
              <th>Subject Name</th>
              <th>Action</th>
              
           </tr>
           
           <% for(Subject subjects : listsubjects) {%>
	 
            
           <tr>
             <td><%= subjects.getId()%></td>
             <td><%= subjects.getsubject_name()%></td>
            
              <td><input type='button' 
                        value='Update' 
                        onclick="window.location.href='subjects?command=LOAD&subjectId=<%= subjects.getId()%>';return false;" 
                        class='add-student-button'
                 />
             
                 
                 <!-- Delete button with javascript to prompt user to confirm whether they want to delete -->
                 
                 <a type="button"
                    class="add-student-button"
                    href="subjects?command=DELETE&subjectId=<%= subjects.getId()%>"
                    onclick="if(!(confirm('Are you sure you want to delete?')))return false"
                    >Delete</a>
                 
             
           </tr>
           
           <% }%>
         </table>
         
         
      </div>
  
  </div>

</body>
</html>