

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student Room Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body style="background-color: #00bfff">

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
	<div class="container col-md-5" style="padding-top: 105px;">
		<div class="card">
			<div class="card-body">
				<c:if test="${student != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${student == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${student != null}">
            			Edit Student
            		</c:if>
						<c:if test="${student == null}">
            			Add New Student
            		</c:if>
					</h2>
				</caption>

				<c:if test="${student != null}">
					<input type="hidden" name="id"
						value="<c:out value='${student.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Student Name</label> <input type="text"
						value="<c:out value='${student.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Student Usn</label> <input type="text"
						value="<c:out value='${student.usn}' />" class="form-control"
						name="usn">
				</fieldset>

				<fieldset class="form-group">
					<label>Room (From A-Z)</label> <input type="text"
						value="<c:out value='${student.room}' />" class="form-control"
						name="room">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>