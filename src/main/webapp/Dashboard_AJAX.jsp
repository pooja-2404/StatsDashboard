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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
body {
	background-color: #cac3c3;
}

.tablehour {
	position: absolute;
	top: 52px;
	left: 600px;
}

#data-hour-table {
	position: absolute;
	top: 134px;
	left: 600px;
}
</style>
<script>
	$(document).ready(function() {
		function updateTableData() {
			$.ajax({
				url : "AccessDatabase",
				method : "POST",
				data : $("#date").serialize(),
				success : function(response) {
					var data = response.dataList; // ArrayList<DataItem>
					var dataHour = response.dataListHour; // ArrayList<DataItemHour
				 $("#data-table tbody").empty();
                     $("#data-hour-table tbody").empty();
                     
                     
                     // Populate the table with the new data
                     $.each(data, function (index, item) {
                         $("#data-table tbody").append(
                             "<tr>" +
                             "<td>" + item.ProcessedDate + "</td>" +
                             "<td>" + item.Hour + "</td>" +
                             "<td>" + item.Min + "</td>" +
                             "<td>" + item.Min_5 + "</td>" +
                             "<td>" + item.Min_15 + "</td>" +
                             "<td>" + item.Del_count + "</td>" +
                             "<td>" + item.Fail_count + "</td>" +
                             "</tr>"
                         );
                     });

                     $.each(dataHour, function (index, item) {
                         $("#data-hour-table tbody").append(
                             "<tr>" +
                             "<td>" + item.Hour + "</td>" +
                             "<td>" + item.Del_count+ "</td>" +
                             "<td>" + item.Fail_count + "</td>"+
                             "</tr>"
                         );
                     });
				}
			});
		}
		updateTableData();
		setInterval(updateTableData, 3000);
	});
</script>

</head>
<body>
	<form  action="AccessDatabase" method="post" id="date">
		<label for="date">Select a Date:</label> <input type="date" id="date"
			name="selectedDate"
			value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date())%>">
	</form>


	
	<form action="index.jsp" method="post">
		<button type="Submit">Back</button>
	</form>
	<h1>Stats Data</h1>
	<table border="1" id="data-table">
		 <thead>
			<tr>
				<th>Date</th>
				<th>Hour</th>
				<th>Min</th>
				<th>Min_5</th>
				<th>Min_15</th>
				<th>Del_count</th>
				<th>Fail_count</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<h1 class="tablehour">Hour Stats Data</h1>
	<table border="1" id="data-hour-table">
		<thead>
			<tr>
				<th>Hour</th>
				<th>Del_count</th>
				<th>Fail_count</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</body>
</html>