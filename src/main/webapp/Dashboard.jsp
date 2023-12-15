<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="data.DataItem"%>
<%@ page import="data.DataItemHour"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Dashboard</title>
<style>
body {
	background-color: #cac3c3;
}

p {
	font-color: green;
	text-align: center;
	font-size: 30px;
}

.tablehour {
	position: absolute;
	top: 52px;
	left: 600px;
}

#table {
	position: absolute;
	top: 134px;
	left: 600px;
}
</style>
</head>
<body>
	<form action="AccessDatabase" method="post">
		<label for="date">Select a Date:</label> <input type="date" id="date"
			name="selectedDate">
		<button type="Submit">Submit Date</button>
	</form>
	<form action="Login.jsp" method="post">
		<button type="Submit">Log Out</button>
	</form>
	<h1>Stats Data</h1>
	<table border="1">
		<tr>

			<th>Date</th>
			<th>Hour</th>
			<th>Min</th>
			<th>Min_5</th>
			<th>Min_15</th>
			<th>Del_count</th>
			<th>Fail_count</th>
		</tr>


		<c:forEach var="data" items="${dataList}">
			<tr>

				<td>${data.getDate()}</td>
				<td>${data.getHour()}</td>
				<td>${data.getMin()}</td>
				<td>${data.getMin_5()}</td>
				<td>${data.getMin_15()}</td>
				<td>${data.getdcount()}</td>
				<td>${data.getfcount()}</td>
			</tr>
		</c:forEach>
	</table>
	

	<h1 class="tablehour">Hour Stats Data</h1>
	<table border="1" id="table">
		<tr>
			<th>Hour</th>
			<c:forEach var="data" items="${dataListHour}">
				<td>${data.getHour()}</td>
			</c:forEach>
		</tr>
		<tr>
			<th>Del_count</th>
			<c:forEach var="data" items="${dataListHour}">
				<td>${data.getdcount()}</td>
			</c:forEach>
		</tr>
		<tr>
			<th>Fail_count</th>
			<c:forEach var="data" items="${dataListHour}">
				<td>${data.getfcount()}</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>