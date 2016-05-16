<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script src="<c:url value="resources/js/jquery-ui-1.11.4/jquery.dataTables.js"/>"></script>
<title>Search Profile</title>
</head>
<body>
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
				<td><input id="search" type="button" value="Search"/></td>
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
					<th>Votes</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Branch Name</th>
				</tr>
			</thead>
		</table>
	
	</div>
</body>
</html>