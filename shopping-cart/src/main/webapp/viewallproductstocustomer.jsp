
<%@page import="com.jsp.shoppingcart.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  List<Product> products =(List<Product>)request.getAttribute("productlist");
%>
<h1>
<a href="fetchitemsfromcart"> view cart</a>
</h1>
<table>
<th>id</th>
<th>brand</th>
<th>category</th>
<th>model</th>
<th>price</th>
<th>add to cart</th>
<% for(Product p:products) {%>
<tr>

<td> <%= p.getBrand() %></td>
<td> <%= p.getCategory() %></td>
<td> <%= p.getModel() %></td>
<td> <%= p.getPrice() %></td>
<td> <a href="additem?id=<%= p.getId()%>">add</a></td>
</tr>
<%
} 
%>
</table>
</body>
</html>