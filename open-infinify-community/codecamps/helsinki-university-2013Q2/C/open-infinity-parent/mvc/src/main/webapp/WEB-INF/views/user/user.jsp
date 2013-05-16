<%-- 
    Author     : Jarno Knihtilä
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
    </head>
    <body>
        <h1>Hello! your role is user and your username is ${username}!</h1>
        <p><a href="<c:url value="j_spring_security_logout" />" > Logout</a></p>
    </body>
</html>
