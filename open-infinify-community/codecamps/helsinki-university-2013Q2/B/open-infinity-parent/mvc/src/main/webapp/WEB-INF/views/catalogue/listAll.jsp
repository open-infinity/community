<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

    <div class="container">
        <br />
        <h2>
            Create new catalogue
        </h2>
        <br />
        <div id="statusbox"></div>
        <br/>
        <div id="catalogueForm" class="span-12 last">
            <form:form commandName="catalogueModel" action="catalogue" method="post">
                <table id="catalogueTable">
                    <tr>
                        <td><form:label	for="name" path="name" cssErrorClass="error">Name</form:label></td>
                        <td><form:input path="name" /></td>
                        <td><form:errors path="name" /></td>
                    </tr>
                    <tr>
                        <td>
                            <p>
                                <form:hidden path="id" id="id" />
                                <input id="save" type="submit" value="Create" />
                                <input id="save" type="submit" value="Back" />
                            </p>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>

    <br/>

<table>
    <c:forEach var="o" items="${catalogs}">
        <tr>
            <form:form method="DELETE" action="catalogue/${o.id}">
                <td><a href="catalogue/${o.id}">${o.id} ${o.name}</a></td><td><input type="submit" value="Delete"></td>
            </form:form>
        </tr>
    </c:forEach>
</table>

<c:set var="model" value="catalogModel" />


<%@ include file="/WEB-INF/views/common/validation_scripts.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>