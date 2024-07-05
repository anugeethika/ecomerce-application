<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form action="savecustomer" modelAttribute="customerobj">
enter name: <form:input path="name"/><br>
enter address: <form:input path="address"/><br>
enter moblienumber: <form:input path="moblienumber"/><br>
enter email: <form:input path="email"/><br>
enter password: <form:input path="password"/><br>
<input type="submit" value="register">
</form:form>
</body>
</html>