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
<title>Show All students</title>
</head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Registration Number</th>
                <th>Name</th>
                <th>SurName</th>
                <th>Department</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.Students}" var="s">
                <tr>
                    <td><c:out value="${s.id}" /></td>
                    <td><c:out value="${s.name}" /></td>
                    <td><c:out value="${s.surname}" /></td>
                    <td><c:out value="${s.department}" /></td>
                    <td><a href="StudentController?action=edit&RegistrationNumber=<c:out value="${s.id}"/>">Update</a></td>
                    <td><a href="StudentController?action=delete&RegistrationNumber=<c:out value="${s.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="StudentController?action=insert">Add student</a></p>
</body>
</html>