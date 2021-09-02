<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<title><spring:message code="computerDatabase" text="Computer Database"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="UTF-8">
	<!-- Bootstrap -->
	<link href="/training-java-webapp/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/training-java-webapp/static/css/font-awesome.css" rel="stylesheet" media="screen">
	<link href="/training-java-webapp/static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.html"> <spring:message code="appName" text="Application - Computers Database"/> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				<c:out value="${message}"/>
				<br/>
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<script src="/training-java/static/js/jquery.min.js"></script>
	<script src="/training-java/static/js/bootstrap.min.js"></script>
	<script src="/training-java/static/js/dashboard.js"></script>

</body>
</html>