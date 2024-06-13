<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>OTP Verification</title>

<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="Css/Otp.css" />
<style type="text/css">
body {
	background-image: url('images/verify.jpg');
	margin: 0;
	font-family: "Poppins", sans-serif;
	background-size: cover;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	height: 100vh;
}
</style>
	</head>
	<body>
		<div class="container">
			<header>
				<i class="bx bxs-check-shield"></i>
			</header>
			<h4>Enter OTP Code</h4>
			<h:outputLabel value="A verification code has been sent to: #{EmployData.email}" />
   


			<h:form id="form">
				<div class="otp-field">
					<h:inputText id="digit1" maxlength="1"
						value="#{employeDao.enteredOtp1}" />
					<h:inputText id="digit2" maxlength="1"
						value="#{employeDao.enteredOtp2}" />
					<h:inputText id="digit3" maxlength="1"
						value="#{employeDao.enteredOtp3}" />
					<h:inputText id="digit4" maxlength="1"
						value="#{employeDao.enteredOtp4}" />
				</div>
				<div class="message">
					<h:message for="digit1" style="color: red; font: 12px" />
				</div> 
				<h:commandButton styleClass="btn1"
					action="#{employeDao.validateResetOtp()}" value="Verify OTP" />
			</h:form>
		</div>
		<script src="JavaScript\Verification.js"></script>
	</body>
</f:view>
