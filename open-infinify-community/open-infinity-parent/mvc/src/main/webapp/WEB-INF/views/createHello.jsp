<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Give a name</title>
</head>
<body>

		<div class="container">
			<br />
			<h2>
				Who to greet
			</h2>
			
			<br />
			<div class="sub_menu"><b>Please Define name</b></div>
			<br />	
			
			<div id="statusbox"></div>
			<br/>
			<div id="helloForm" class="span-12 last">	

				<form:form modelAttribute="helloName" action="/hello-application/hello/addName" method="post">
					<table id="helloTable">
						<tr>
							<td><form:label	id="nameLabel" for="name" path="name" cssErrorClass="error">Name</form:label></td>
							<td><form:input path="name" /></td>
							<td><form:errors path="name" /></td>
						</tr>
						<tr>
							<td>
								<p>	
							<input id="save" type="submit" value="Save" />
						</p>
							</td>
							<td></td>
							<td></td>
						</tr>
						</table>
				</form:form>
			</div>	
		</div>
