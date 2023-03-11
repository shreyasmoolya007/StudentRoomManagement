<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.xadmin.studentroommanagement.dao.RoomDAO" %>
<html>
<head>
<title>Student Room Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
table {
  border: 0px solid black;
  border-radius: 20px;
}
</style>
</head>
<body>
	<%
    RoomDAO dao = new RoomDAO();
    int countVar = dao.getCount();
%>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark justify-content-between"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Student
					Room Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Registered</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Database</h3>
			<hr>
			<div class="container text-center">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Student</a>
				<p><%= countVar %></p>
			</div>
			<br>
			<table class="table table-hover table-dark">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Usn</th>
						<th>Room (From A-Z)</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="student" items="${listRoom}">

						<tr>
							<td><c:out value="${student.id}" /></td>
							<td><c:out value="${student.name}" /></td>
							<td><c:out value="${student.usn}" /></td>
							<td><c:out value="${student.room}" /></td>
							<td><a href="edit?id=<c:out value='${student.id}' />" class="btn btn-primary">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${student.id}' />" class="btn btn-danger">Delete</a></td>
						</tr>
					</c:forEach>

				</tbody>

			</table>
		</div>
	</div>
</body>
</html>