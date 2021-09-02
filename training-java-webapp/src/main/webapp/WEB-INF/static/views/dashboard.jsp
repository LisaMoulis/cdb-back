<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<title><spring:message code="computerDatabase" text="Computer Database"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="/training-java-webapp/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/training-java-webapp/static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="/training-java-webapp/static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<%@ page import="model.ComputerList" %>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="computers"> <spring:message code="appName" text="Application - Computers Database"/> </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                <c:out value="${page.nbComputers}"/> <spring:message code="computersFound" text="Computers Found" />
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">
						<input type="hidden" name="page" value="${page.page}">
						<input type="hidden" name="size" value="${page.size}">
                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" value="${page.search}" />
                        <br/>
                        <button type="submit" id="searchsubmit" name="searchsubmit" value="Filter by name" class="btn btn-primary" ><spring:message code="filterName" text="Filter by name"/></button>
                        <button type="submit" id="searchsubmit" name="searchsubmit" value="Filter by company" class="btn btn-primary"><spring:message code="filterCompany" text="Filter by company"/></button>
                        <button type="submit" id="searchsubmit" name="searchsubmit" value="Cancel" class="btn btn-primary"><spring:message code="cancel" text="Cancel"/></button>
                        <br/>
                        <button type="submit" id="searchorder" name="searchorder" value="Ascending" class="btn btn-primary" ><spring:message code="ascending" text="Ascending"/></button>
                        <button type="submit" id="searchorder" name="searchorder" value="Descending" class="btn btn-primary" ><spring:message code="descending" text="Descending"/></button>
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="add"><spring:message code="add" text="Add Computer"/></a> 
                    <a class="btn btn-default" id="editComputerEdit" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="edit" text="Edit"/></a>
                    <a style="display:none" class="btn btn-default" id="editComputerView" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="view" text="View"/></a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="delete" method="POST">
            <input type="hidden" name="selection" value="">
        </form> 
        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            <spring:message code="name" text="Computer name"/>
                        </th>
                        <th>
                            <spring:message code="introduced" text="Introduced date"/>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <spring:message code="discontinued" text="Discontinued date"/>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <spring:message code="company" text="Company"/>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                	<c:forEach items="${page.computers}" var="entry">
	                    <tr>
	                        <td class="editMode">
                            	<input type="checkbox" name="cb" class="cb" value="${entry.id}">
                        	</td>
	                        <td>
	                            <a href="edit?id=${entry.id}" onclick=""><c:out value="${entry.name}"/></a>
	                        </td>
	                        <td><c:if test="${not empty entry.introduced}">${entry.introduced}</c:if></td>
	                        <td><c:if test="${not empty entry.discontinued}">${entry.discontinued}</c:if></td>
	                        <td><c:if test="${not empty entry.company}">${entry.company.name}</c:if></td>
	
	                    </tr>
					</c:forEach>
                </tbody>
            </table>
        </div>
    </section>
    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <li>
                    <a href="computers?page=${page.page-1}&size=${page.size}&search=${page.search}" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              	<c:set var="beginning" value="1" scope="page" />
              	
              	<c:if test="${page.page>5}">
              		<c:set var="beginning" value="${page.page-5}" scope="page" />
              	</c:if>
              	<c:set var="end" value="${beginning+10}" scope="page" />
              	<c:if test="${end>page.nbPages}">
              		<c:set var="end" value="${page.nbPages}" scope="page" />
              		<c:set var="beginning" value="${end-10}" scope="page" />
              		<c:if test="${beginning<1}">
              			<c:set var="beginning" value="1" scope="page" />
              		</c:if>
              	</c:if>
              	
				<c:forEach begin="${beginning}" end="${end}" var="i" step="1">
					<li><a href="computers?page=${i}&size=${page.size}&search=${page.search}">${i}</a></li>
				</c:forEach>
				
              <li>
                <a href="computers?page=${page.page+1}&size=${page.size}&search=${page.search}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>


        <div class="btn-group btn-group-sm pull-right" role="group" >
            <button type="button" class="btn btn-default" onclick="size10()">10</button>
            <button type="button" class="btn btn-default" onclick="size50()">50</button>
            <button type="button" class="btn btn-default" onclick="size100()">100</button>
        </div></div>

    </footer>
<script src="/training-java-webapp/static/js/jquery.min.js"></script>
<script src="/training-java-webapp/static/js/bootstrap.min.js"></script>
<script src="/training-java-webapp/static/js/dashboard.js"></script>
<script> 
	function size10() {
		var searchParams = new URLSearchParams(window.location.search);
		if (searchParams.get('page') == null)
		{
			window.location.replace("computers?page=1&size=10&search=" + document.getElementById("searchbox").value);
		}
		else
		{
			window.location.replace("computers?page="+ searchParams.get('page') +"&size=10&search=" + document.getElementById("searchbox").value);
		}
	}
	function size50() {
		var searchParams = new URLSearchParams(window.location.search);
		if (searchParams.get('page') == null)
		{
			window.location.replace("computers?page=1&size=50&search=" + document.getElementById("searchbox").value);
		}
		else
		{
			window.location.replace("computers?page="+ searchParams.get('page') +"&size=50&search=" + document.getElementById("searchbox").value);
		}
	}
	function size100() {
		var searchParams = new URLSearchParams(window.location.search);
		if (searchParams.get('page') == null)
		{
			window.location.replace("computers?page=1&size=100&search=" + document.getElementById("searchbox").value);
		}
		else
		{
			window.location.replace("computers?page="+ searchParams.get('page') +"&size=100&search=" + document.getElementById("searchbox").value);
		}
	}
</script>
</body>
</html>