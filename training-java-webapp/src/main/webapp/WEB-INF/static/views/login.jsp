<!DOCTYPE html>
<html>
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
	<link href="/training-java-webapp/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/training-java-webapp/static/css/font-awesome.css" rel="stylesheet" media="screen">
	<link href="/training-java-webapp/static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">	
			<h1>Login</h1>
		   <form name='login' action="login" method='POST'>
		      <table>
		         <tr>
		            <td>User:</td>
		            <td><input type='text' name='username' value=''></td>
		         </tr>
		         <tr>
		            <td>Password:</td>
		            <td><input type='password' name='password' /></td>
		         </tr>
		         <tr>
		            <td><input name="submit" type="submit" value="submit" /></td>
		         </tr>
		      </table>
		  </form>
		</div>
	</section>

	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/dashboard.js"></script>

</body>
</html>