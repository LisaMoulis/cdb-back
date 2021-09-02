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
            <a class="navbar-brand" href="computers"> <spring:message code="appName" text="Application - Computers Database"/> </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="add" text="Add Computer"/></h1>
                    <form action="add" method="POST" id="computer-form">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="name" text="Computer name"/></label>
                                <input type="text" class="form-control" id="computerName" name="name" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="introduced" text="Introduced date"/></label>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="discontinued" text="Discontinued date"/></label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="company" text="Company"/></label>
                                <select class="form-control" id="companyId" name="companyId" >
                                    <option value="0">--</option>
                                    <c:forEach items="${companies}" var="company">
                                    	<option value="${company.id}"><c:out value="${company.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <button type="submit" value="Add" class="btn btn-primary"><spring:message code="addShort" text="Add"/></button>
                            <spring:message code="or" text="or"/>
                            <a href="computers" class="btn btn-default"><spring:message code="cancel" text="Cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script>
    function validate(e) {
    			
		if (document.getElementById('computerName').value.length == 0)
        {
			alert("<spring:message code='alertName' text='The name is empty!'/>");
			if (e.preventDefault) e.preventDefault();
			return false;
        }
		
		if (document.getElementById('introduced').value.length != 0 && !document.getElementById('introduced').value.match("([0-9]{4}-[0-1][0-9]-[0-3][0-9])"))
		{
			alert("<spring:message code='alertIntroduced' text='Wrong introduced date format! The format must be yyyy-MM-dd.'/>");
			if (e.preventDefault) e.preventDefault();
			return false;
		}
		
		if (document.getElementById('discontinued').value.length != 0)
		{
			if(document.getElementById('introduced').value.length == 0)
			{
				alert("<spring:message code='alertEmptyIntroduced' text='The introduced date can\'t be empty if the discontinued date isn\'t!'/>");
				if (e.preventDefault) e.preventDefault();
				return false;
			}
			else if (!document.getElementById('discontinued').value.match("([0-9]{4}-[0-1][0-9]-[0-3][0-9])"))
			{
				alert("<spring:message code='alertDiscontinued' text='Wrong discontinued date format! The format must be yyyy-MM-dd.'/>");
				if (e.preventDefault) e.preventDefault();
				return false;
			}
		}
        return true;
    }

    var form = document.getElementById('computer-form');
    if (form.attachEvent) {
        form.attachEvent("submit", validate);
    } else {
        form.addEventListener("submit", validate);
    }
    </script>
</body>
</html>