<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

    <script>
    function createButton_onClick() {
     $('#catalogueModel').submit();
    }

    function deleteButton_onClick() {
     $('#command').submit();
    }

    </script>

    <div class="container">
        <br />
        <div id="statusbox"></div>
        <br/>
        <div id="catalogueForm" class="span-12 last">
            <form:form modelAttribute="catalogueModel" action="catalogue" method="post">
                <fieldset>

                            <legend>Create new catalogueId</legend>
                            <form:label	for="name" path="name" cssErrorClass="error">Name</form:label>
                            <form:input path="name" />
                            <form:errors path="name" />

                            <form:hidden path="id" id="id" /><br />
                            <a class="btn btn-primary" href="#" onclick="createButton_onClick()">Create</a>



                </fieldset>
            </form:form>
        </div>
    </div>

    <br/>

<h2>List of all catalogues</h2>
<table class="table table-striped">

    <tr>
    <td>Catalog ID</td><td>Name</td><td></td>
    </tr>


    <c:forEach var="o" items="${catalogs}">
        <tr>
            <form:form method="DELETE" action="catalogue/${o.id}">
                <td width="40%"><a href="catalogue/${o.id}">${o.id}<td width="40%"> ${o.name}</td> </a></td><td width="20%"><a class="btn btn-danger btn-small" href="#" onclick="deleteButton_onClick()">Delete</a></td>
            </form:form>
        </tr>
    </c:forEach>
</table>




<%@ include file="/WEB-INF/views/common/validation_scripts.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>