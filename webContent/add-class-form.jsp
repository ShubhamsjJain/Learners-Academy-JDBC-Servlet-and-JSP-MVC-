<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Class Form</title>
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
  
      <h3>Add Class</h3>      
         
         
         <form action="classes" method="get">
         
         <input type="hidden" name="command" value="ADD" /> <!-- So that servletControllerStudent will understand what method(list,add,update,delete) does it need to call -->
         
        <table>
        
        <tbody>
            <tr>
                <td><label>Class Name:</label></td>
                <td><input type="text" name="name" required></td>
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