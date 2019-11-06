<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL=STYLESHEET
      HREF="JSP-Styles.css"
      TYPE="text/css">
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Add new Student</title>
</head>
<body>
    
    <form method="POST" action='StudentController' name="frmAddStudent">
    <input type="hidden" name="action" value="insert" />
        Registration Number : <input type="text"   name="RegistrationNumber"
            value="<c:out value="${Student.id}" />" /> <br /> 
        Name : <input
            type="text" name="Name"
            value="<c:out value="${Student.name}" />" /> <br /> 
        SurName : <input
            type="text" name="SurName"
            value="<c:out value="${Student.surname}" />" /> <br /> 
        Department : <input type="text" name="Department"
            value="<c:out value="${Student.department}" />" /> <br /> <input
            type="submit" value="Submit" />
    </form>
</body>
</html>