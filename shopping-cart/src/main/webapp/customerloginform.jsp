<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> ${msg}</h1>
<form action="customerloginvalidations" method="post">
enter name:<input type="email" name="email">
enter password:<input type="password" name="password">
<input type="submit">
</form>
</body>
</html>