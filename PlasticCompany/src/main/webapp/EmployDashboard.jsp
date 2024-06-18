<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html>
<f:view>
	<html>
<head>
<meta charset="UTF-8">
<title>Employee Dashboard</title>
<link rel="stylesheet" href="Css/Employdashboard.css">
</head>
<body style="background-color: #C8BEF3">
	<form enctype="multipart/form-data">
		<div class="container">
			<main class="main-content">
				<aside class="sidebar">
					<h2 id="menuHeader">Menu</h2>
					<ul id="menuLinks" style="display: none;">
						<li><a href="#">Dashboard</a></li>
						<li><a href="#">Profile</a></li>
						<li><a href="#">Tasks</a></li>
						<li><a href="#">Notifications</a></li>
						<li><a href="EmpLogin.jsf">Logout</a> </li>
						
					</ul>
				</aside>

				<div>
					<h:panelGroup layout="block" style="float: left;">
						<h2>
							Welcome
							<h:outputText value="#{EmployList.firstName}" />
						</h2>
					</h:panelGroup>
					<div class="profile-image" style="float: right;">
						<h3>Profile Picture</h3>
						<h:graphicImage value="#{EmployList.imgUrl}" />
						<div style="clear: both;"></div>
						<h2>Employee Details</h2>
						<div class="profile-info"
							style="float: left; background-image: url('Css/images/verify.jpg');">
							<p>
								<strong>Name:</strong>
								<h:outputText
									value="#{EmployList.firstName} #{EmployList.lastName}" />
							</p>
							<p>
								<strong>Email:</strong>
								<h:outputText value="#{EmployList.email}" />
							</p>
							<p>
								<strong>Phone:</strong>
								<h:outputText value="#{EmployList.phoneNumber}" />
							</p>
							<!-- Add more details as needed -->
						</div>
					</div>

				</div>
				<br />

				<div class="profile"></div>

				<div class="tasks">
					<h2>Tasks</h2>
					<ul>
						<li>Task 1</li>
						<li>Task 2</li>
						<li>Task 3</li>
						<!-- Add dynamic tasks here using JSF tags -->
					</ul>
				</div>
				<div class="notifications">
					<h2>Notifications</h2>
					<ul>
						<li>New message from HR</li>
						<li>Upcoming meeting at 2:00 PM</li>
						<!-- Add dynamic notifications here using JSF tags -->
					</ul>
				</div>

				<div class="events">
					<h2>Upcoming Events</h2>
					<ul>
						<li>Team building event on Friday</li>
						<li>Training session next week</li>
						<!-- Add dynamic events here using JSF tags -->
					</ul>
				</div>
			</main>
		</div>
	</form>
	<script src="JavaScript\DashBoard.js"></script>
</body>
	</html>
</f:view>
