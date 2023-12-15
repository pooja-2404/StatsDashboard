<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	background-color: #cac3c3;
}

.back {
	position: absolute;
	top: 10px;
}

.div {
	position: relative;
	top: 20vh;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert-Update-Delete form</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {
		$("#insert-button").click(function() {
			$("#action").val("insert");
			$.ajax({
				url : "InsertServlet",
				method : "POST",
				data : $("#insert-form").serialize(),
				success : function(response) {
					window.location.href = 'Dashboard_AJAX.jsp';
				},
				error : function(error) {
					window.location.href = 'index.jsp';
				}
			});
		});
	});
	$(document).ready(function() {
		$("#delete-button").click(function() {
			$("#action").val("delete");
			$.ajax({
				url : "InsertServlet", //replace with DeleteServlet
				method : "POST",
				data : $("#insert-form").serialize(),
				success : function(response) {
					window.location.href = 'Dashboard_AJAX.jsp';
				},
				error : function(error) {
					window.location.href = 'index.jsp';
				}
			});
		});
	});
	$(document).ready(function() {
		$("#update-button").click(function() {
			$("#action").val("update");
			$.ajax({
				url : "InsertServlet", //replace with DeleteServlet
				method : "POST",
				data : $("#insert-form").serialize(),
				success : function(response) {
					window.location.href = 'Dashboard_AJAX.jsp';
				},
				error : function(error) {
					window.location.href = 'index.jsp';
				}
			});
		});
	});
</script>
</head>
<body>
	<div class="div">
		<form id="insert-form">
			<input type="text" name="ProcessedDate" placeholder="Date" 
			value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date())%>">
			<input type="number" name="Hour" id="Hour" placeholder="Enter Hour...." min="0" max="23"> 
			<input type="number" name="Min" id="Min" placeholder="Enter Minute..." min="0" max="59"> 
			<input type="number" name="Min_5" id="Min_5" readonly> <input type="number" name="Min_15" id="Min_15" readonly> 
			<input type="number" name="Del_count" id="Del_count" placeholder="Delivered Count..."> 
			<input type="number" name="Fail_count" id="Fail_count" placeholder="Failed count...">
			<br>
			<br>
			<br> 
			<input type="hidden" name="action" id="action" value="">
			<button type="button" id="insert-button">Insert Data</button>
			<button type="button" id="delete-button">Delete Data</button>
			<button type="button" id="update-button">Update Data</button>
		</form>
	</div>
	<br>
	<form action="index.jsp" method="post">
		<button type="Submit" class="back">Back</button>
	</form>
	<script>
		// Get references to the input fields
		const minInput = document.getElementById("Min");
		const resultMin5 = document.getElementById("Min_5");
		const resultMin15 = document.getElementById("Min_15");
		minInput.addEventListener("input", function() {
			const minValue = parseInt(minInput.value);
			const resultMin_5 = Math.floor(minValue / 5);
			const resultMin_15 = Math.floor(minValue / 15);
			resultMin5.value = resultMin_5;
			resultMin15.value = resultMin_15;
		});
	</script>
</body>
</html>