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
	
	<div id="searchResultsDiv" width="100%">
		<table id="searchResultsTable" width="100%">
			<thead width="100%">
				<tr width="100%">
					<th width="15%">First Name</th>
					<th width="15%">Last Name</th>
					<th width="15%">Enrollment Number</th>
					<th width="10%">Votes</th>
					<th width="15%">Email</th>
					<th width="15%">Phone</th>
					<th width="15%">Branch Name</th>
				</tr>
			</thead>
		</table>
	
	</div>
</body>
</html>