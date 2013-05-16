<%-- 
    Document   : catalogue
    Created on : 15.5.2013, 19:10:23
    Author     : Jarno Knihtilä
--%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<table>
    <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Company</th>
            <th>Description</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="p" items="${products}">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.company}</td>
                <td>${p.description}</td>
                <td>${p.price}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
