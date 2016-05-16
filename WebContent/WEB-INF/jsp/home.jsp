<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
	<title>Home</title>

	<script src="<c:url value="resources/js/jquery-ui-1.11.4/external/jquery/jquery.js"/>"></script>
	<script src="<c:url value="resources/js/jquery-ui-1.11.4/jquery-ui.js"/>"></script>
	<script src="<c:url value="resources/js/jquery-ui-1.11.4/jquery.dataTables.js"/>"></script>
	<script src="<c:url value="resources/js/jquery-ui-1.11.4/jquery.ui.effect.js"/>"></script>
	<script src="<c:url value="resources/js/jquery-ui-1.11.4/jquery.ui.dialog.js"/>"></script>
	<script src="<c:url value="resources/js/jquery-ui-1.11.4/jquery.ui.core.js"/>"></script>
	<script src="<c:url value="resources/js/home.js"/>"></script>
	<script src="<c:url value="resources/js/search.js"/>"></script>
	
	<link href="<c:url value="resources/css/style.css"/>" rel="stylesheet">
	<link href="<c:url value="resources/css/demo_page.css"/>" rel="stylesheet">
	<link href="<c:url value="resources/css/demo_table.css"/>" rel="stylesheet">
	<link href="<c:url value="resources/css/demo_table_jui.css"/>" rel="stylesheet">
	<link href="<c:url value="resources/js/jquery-ui-themes-1.11.4/themes/smoothness/jquery-ui.css"/>" rel="stylesheet">
	
	<%String rollNo = (String) request.getAttribute("rollNo"); %>
</head>

<body>		
	<input id="rollNoHidden" type="hidden" value="<%=rollNo%>"/>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			<a href="login?logout" var="logoutUrl">Logout</a>
		</h2>
	</c:if>
	
	<div id="tabs">
  		<ul>
		    <li><a href="#tabs-myProfile">My Profile</a></li>
		    <li><a href="#tabs-searchProfile">Search Profile</a></li>
		</ul>

  		<div id="tabs-myProfile">
	    	<form action="update/profile" method="post" id="profileForm">	    		
	  			<table>
	  				<tr>
	  					<td><label name="firstName">First Name</label></td>
	  					<td><input id="firstName" disabled="disabled"/></td>
	  					<td><label name="lastName">Last Name</label></td>
	  					<td><input id="lastName" disabled="disabled"/></td>
	  					<td><label name="rollNo">Enrollment Number</label></td>
	  					<td><input id="rollNo" disabled="disabled"/></td>
	  				</tr>
	  				<tr>
	  					<td colspan="2"><label name="firstNameError"></label></td>
	  					<td colspan="2"><label name="lastNameError"></label></td>
	  					<td colspan="2"></td>
	  				</tr>
	  				<tr>
	  					<td><label name="email">E-mail</label></td>
	  					<td><input id="email" disabled="disabled"/></td>
	  					<td><label name="phone">Phone Number</label></td>
	  					<td><input id="phone" maxlength="10" disabled="disabled"/></td>
	  				</tr>
	  				<tr>
	  					<td colspan="2"><label id="emailError"></label></td>
	  					<td colspan="2"><label id="phoneError"></label></td>
	  					<td></td>
	  				</tr>
	  				<tr>
	  					<td><label name="voteCount">Vote Count</label></td>
	  					<td><input id="voteCount" disabled="disabled"/></td>
	  					<td><label name="branch">Branch Name</label></td>
	  					<td><input id="branch" disabled="disabled"/></td>
	  				</tr>
	  			</table>
			</form>
			<input id="changeProfileBtn" type="button" value="Submit" disabled="disabled"/>
			<input id="editProfileBtn" type="button" value="Edit" />
		</div>
		
		<div id="tabs-searchProfile">
	    	<form id="profileSearchForm" action="" method="post">
				<table>
					<tr>
						<td><label name="fNameSearch">First Name</label></td>
	  					<td><input id="fNameSearch"/></td>
	  					<td><label name="lNameSearch">Last Name</label></td>
	  					<td><input id="lNameSearch"/></td>
	  					<td><label name="rollNoSearch">Enrollment Number</label></td>
	  					<td><input id="rollNoSearch"/></td>
					</tr>
					<tr>
						<td><input id="searchBtn" type="button" value="Search"/></td>
					</tr>
				</table>
			</form>
	
			<div id="searchResultsDiv">
				<table id="searchResultsTable">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Enrollment Number</th>
						<th>Branch Name</th>
						<th>Email</th>
						<th>Phone</th>						
						<th>Vote Count</th>
					</tr>
				</thead>
				</table>	
			</div>
			
			<div id="dialog-voting" title="Profile Vote">
				<form action="" method="post">
					<input type="radio" name="vote" value="like" checked>Like<br>
					<input type="radio" name="vote" value="dislike">Dislike<br>
				</form>
			</div>
			
		</div>
 	</div>
</body>
</html>