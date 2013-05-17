<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<h2>List of products in catalogue <c:out value="${name}"/> </h2> 
<!--form:label for="catalogueId" path="catalogueId" cssErrorClass="error">Catalogue</form:label>
                            
                                <form:select path="catalogueId">
                                    <form:options items="${catalogs}" itemValue="id" itemLabel="name"/>
                                </form:select>
                            
                            <form:errors path="catalogueId" /-->
<table class="table table-striped">

    <tr>
        <td>Product name</td><td>Description</td><td>Company</td><td>Price</td><td></td>
        
    </tr>

    <c:forEach var="o" items="${productlist}">
        <tr>
         <form:form method="DELETE" action="catalogue/${o.id}">
            <td>${o.name}</td><td>${o.description}</td><td>${o.company}</td><td>${o.price} &euro;</td><td width="20%"><a class="btn btn-danger btn-small" href="#" onclick="deleteButton_onClick()">Delete</a></td>
         </form:form>   
        </tr>
    </c:forEach>
</table>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>