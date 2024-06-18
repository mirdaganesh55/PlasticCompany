<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html>
<f:view>
	<html>
<head>
<meta charset="UTF-8">
<title>Employee Login</title>
<link rel="stylesheet" href="Css/EmpLogin.css">
<style>
body {
	background-image: url('images/LoginPage.jpg');
	background-size: cover;
	background-position: center;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	font-family: Arial, sans-serif;
	color: #fff; /* Text color */
}
.container {
	text-align: center;
}
.input-group {
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Login Here</h1>
		<h:form id="form">
			<div class="input-group">
				<h:outputLabel for="username">Username:</h:outputLabel>
				<h:inputText id="username" value="#{emplogin.username}" />
				<span class="message"><h:message for="username" /></span>
			</div>

			<div class="input-group">
				<h:outputLabel for="password">Password:</h:outputLabel>
				<h:inputSecret id="password" value="#{emplogin.password}" />
				<span class="message"><h:message for="password" /></span>
			</div>
			
			<h:commandButton action="#{employeController.empLogin(emplogin)}"
				value="Login" />
		</h:form>
	</div>
</body>
	</html>
</f:view>