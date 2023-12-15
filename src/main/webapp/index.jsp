<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Selection Dashboard</title>
<style>
body {
	background-color: #cac3c3;
}

h1 {
position:relative;
	font-style:italic;
	top:15px;
}
.out{
position: absolute;
  top: 10px;}
</style>
</head>
<body>
<form action="Login.jsp" method="post">
		<button type="Submit" class="out" >Log Out</button>
	</form>
	<h1>Select the Action Want to Perform:</h1>
	<form action="insert.jsp" method="post">
		<label for="InsertData">Insert Data</label>
		
	
	<br>
	
		<label for="DeleteData">Delete Data</label>
		
	<br>
	<label for="UpdateData">Update Data</label> <br><br><br>
	<button type="submit">Submit</button>
		</form>
	<br>
	<form action="Dashboard_AJAX.jsp" method="get">
		<label for="SelectData">Select Data</label>
		<button type="submit">Submit</button>
	</form>
</body>
</html>