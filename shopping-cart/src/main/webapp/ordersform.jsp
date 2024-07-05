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
<form:form modelAttribute="ordersobj">
Enter name: <form:input path="name"/>
Enter address:<form:input path="address"/>
Enter moblienumber:<form:input path="mobilenumber"/>
<input type="submit">
</form:form>

</body>
</html>