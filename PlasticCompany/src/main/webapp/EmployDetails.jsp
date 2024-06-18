<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employ Details</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(function() {
	$(".dateOfBirth").datepicker({
		dateFormat : 'mm/dd/yy',
		changeMonth : true,
		changeYear : true,
		yearRange : "-100:+0" // Allow selection of the last 100 years
	});
});
</script>
<link rel="stylesheet" href="Css\EmployDetails.css" />
<style type="text/css">
body {
	background-image: url('images/employeback.jpg');
	background-size: auto;
}
</style>
</head>
<body>
	<f:view>
		<div id="empfrom">
			<h1 style="color: #E65715" align="center">Employ Registration!!!</h1>
			<h:form enctype="multipart/form-data" id="form">
				<h:outputLabel for="firstName">First Name:</h:outputLabel>
				<h:inputText id="firstName" value="#{employe.firstName}" />
				<span class="message"> <h:message for="firstName"
						style="display: block; color: red; margin-top: 0rem;" />
				</span>
				<br />

				<h:outputLabel for="lastName">Last Name:</h:outputLabel>
				<h:inputText id="lastName" value="#{employe.lastName}" />
				<span class="message"> <h:message for="lastName"
						style="display: block; color: red; margin-top: 0rem;" />
				</span>
				<br />

				<h:outputLabel for="gender">Gender:</h:outputLabel>
				<h:selectOneMenu id="gender" value="#{employe.gender}">
					<f:selectItem itemLabel="Select Gender" itemValue="" />
					<f:selectItem itemValue="Male" itemLabel="Male" />
					<f:selectItem itemValue="Female" itemLabel="Female" />
					<f:selectItem itemValue="Other" itemLabel="Other" />
				</h:selectOneMenu>
				<span class="message"> <h:message for="gender"
						style="display: block; color: red;margin-top: 0rem;" />
				</span>
				<br />

				<label for="dateOfBirth">Date of Birth</label>
				<h:inputText id="dateOfBirth" value="#{employe.dateOfBirth}"
					styleClass="dateOfBirth " autocomplete="off">
					<f:convertDateTime pattern="MM/dd/yyyy" />
				</h:inputText>
				<span class="message"> <h:message for="dateOfBirth"
						style="display: block; color: red;margin-top:0rem;" />
				</span>
				<br />

				<h:outputLabel for="username">UserName</h:outputLabel>
				<h:inputText id="username" value="#{emplogin.username}" />
				<span class="message"> <h:message for="username"
						style="display: block; color: red; margin-top: 0rem;" />
				</span>
				<br />

				<h:outputLabel for="passCode">Password</h:outputLabel>
				<h:inputSecret id="passCode" value="#{emplogin.password}"
					onkeyup="checkPasswordStrength(this.value, 'password-strength-message')" />
				<span class="message"> <h:message for="passCode" />
				</span>
				<br />
				<span id="password-strength-message" style="color: black;"></span>
				<br />

				<span id="cfm-match-message" style="color: red;"></span>
				<span id="cfm-password-strength-message" style="color: black;"></span>
				<br />

				<h:outputLabel for="email">Email</h:outputLabel>
				<h:inputText id="email" value="#{employe.email}" />
				<span class="message"> <h:message for="email"
						style="display: block; color: red; margin-top: 0rem;" />
				</span>
				<br />

				<h:outputLabel for="phoneNumber">Phone Number</h:outputLabel>
				<h:inputText id="phoneNumber" value="#{employe.phoneNumber}" />
				<span class="message"> <h:message for="phoneNumber"
						style="display: block; color: red; margin-top: 0rem;" />
				</span>
				<br />

				<h:outputLabel for="address">Address</h:outputLabel>
				<h:inputTextarea id="address" value="#{employe.address}" />
				<span class="message"> <h:message for="address"
						style="display: block; color: red; margin-top: 0rem;" />
				</span>
				<br />

				<h:outputLabel for="image">Upload your image:</h:outputLabel>
				<h:inputFile id="image" value="#{employe.file}" />
				<span class="message"> <h:message for="image"
						style="display: block; color: red; margin-top: 0rem;" />
				</span>
				<br />

				<h:commandButton value="Submit"
					action="#{employeController.addValidConditions(employe,emplogin)}"
					styleClass="ui-input-btn ui-btn ui-mini ui-btn-inline 
            ui-corner-all ui-btn-b" />
			</h:form>
		</div>
	</f:view>
	<script src="JavaScript\EmployDetails.js"></script>
</body>
</html>