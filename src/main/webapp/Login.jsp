<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>HOME</title>
<style type="text/css">
body {
	background-color: #cac3c3;
}
label {
	font-family: sans-serif;
	font-size: 1.4vw;
	line-height: 20px;
	padding-bottom: 10px;
}

.div2, .div4 {
	width: 20vw;
	height: 7vh;
	border-radius: 10px;
}

.div5 {
	width: 10vw;
	height: 6vh;
	font-weight: bold;
	font-size: inherit;
	margin-top: 17px;
	margin-left: 5vw;
	border-radius: 10px;
}

fieldset {
	width: 30vw;
	border: 2px solid;
	padding: 30px 30px;
	margin-top: 20vh;
	margin-left: 30vw;
}
</style>
</head>
<body>
	<form action="VerifyLogin" method="post">
		<fieldset>
			<legend>Login Form</legend>
			<div>
				<div class="div1">
					<label>User Name</label>
				</div>
				<br>
				<div class="div2">
					<input type="text" name="user" id="username"
						placeholder="Enter User Name..." required class="div2">
				</div>
				<br>
				<div class="div3">
					<label>Password</label>
				</div>
				<br>
				<div class="div4">
					<input type="Password" name="pass" id="password"
						placeholder="*********" required class="div4">
				</div>
				<br>

				<div>
					<button type="Submit" class="div5">Login</button>
				</div>
			</div>
		</fieldset>
	</form>

</body>
</html>
