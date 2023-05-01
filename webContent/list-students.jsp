<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.simplilearn.web.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student tracking</title>
<link  type="text/css" rel="stylesheet" href="css/style.css">
</head>

<%

//Get the students from the request object (sent by the servlet)

List<Student> theStudents = (List<Student>)request.getAttribute("STUDENTS_LIST");
%>
<body>

  <div id="wrapper">

     <div id="header">
          <h2>Learners Academy</h2>
     </div>
  </div>
  
  
  <div id="container">
  
      <div id="content">
      
         <!-- Adding Add Students Button that will take to add-student-form -->
         
         <input type='button' 
         value='Add Student' 
         onclick="window.location.href='add-student-form.jsp';return false;" 
         class='add-student-button'
         />
         
         <table width=100%>
         
           <tr>
           
              <th>Student ID</th>
              <th>Student Name</th>
              <th>Student E-Mail</th>
              <th>Student City</th>
              <th>Action</th>
              
           </tr>
           
           <% for(Student temp : theStudents){%>
           
           
            
           <tr>
             <td><%= temp.getId()%></td>
             <td><%= temp.getFullName()%></td>
             <td><%= temp.geteMail()%></td>
             <td><%= temp.getCity()%></td>
             <td><input type='button' 
                        value='Update' 
                        onclick="window.location.href='students?command=LOAD&studentId=<%=temp.getId()%>';return false;" 
                        class='add-student-button'
                 />
             </td>
           </tr>
           
           <% }%>
         </table>
         
         
      </div>
  
  </div>

</body>
</html>