<!--@author Samir Puuska-->
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<h2>List of products in catalogue <c:out value="${name}"/> </h2>

<table class="table table-striped">

    <tr>
    <td>Product name</td>
    </tr>


    <c:forEach var="o" items="${productlist}">
        <tr>
                <td width="40%">${o.name}</td>
        </tr>
    </c:forEach>
</table>




<%@ include file="/WEB-INF/views/common/footer.jsp"%>