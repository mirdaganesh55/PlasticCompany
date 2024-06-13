<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html>
<f:view>
	<html>
<head>
<meta charset="UTF-8">
<title>Employee Profile</title>
</head>
<body>
	<div class="container">
		<h1>Employee Profile</h1>
		<h:form id="form" enctype="multipart/form-data">


			<div class="input-group">
				<h:outputLabel for="firstName">First Name:</h:outputLabel>
				<h:outputText value="#{EmployData.firstName}" styleClass="output-text" />
				<span class="message"><h:message for="firstName" /></span>
			</div>

			<div class="input-group">
				<h:outputLabel for="lastName">Last Name:</h:outputLabel>
				<h:outputText value="#{EmployData.lastName}" styleClass="output-text" />
				<span class="message"><h:message for="lastName" /></span>
			</div>
			
			<div class="input-group">
				<h:outputLabel for="Image">Profile Picture:</h:outputLabel>
				<h:graphicImage value="#{EmployData.imgUrl}" styleClass="output-text" />
				<span class="message"><h:message for="Image" /></span>
			</div>

		</h:form>
	</div>
</body>
	</html>
</f:view>
