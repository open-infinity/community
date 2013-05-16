<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>


    <script>
        function createButton_onClick() {
         $('#shoppingListModel').submit();
        }

        function deleteButton_onClick() {
         $('#command').submit();
        }
    </script>


    <div class="container">
        <br />
        <h2>
            Create new ShoppingList
        </h2>
        <br />
        <div id="statusbox"></div>
        <br/>
        <div id="shoppinglistForm" class="span-12 last">
            <form:form commandName="shoppingListModel" action="shoppinglist" method="post">
                <fieldset>

                            <legend>Create new shopping list</legend>
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

<h2>List of all shopping lists</h2>
<table class="table table-striped">

    <tr>
    <td>List ID</td><td></td>
    </tr>


    <c:forEach var="pp" items="${shoppinglists}">
        <tr>
            <form:form method="DELETE" action="shoppinglist/${pp.id}">
                <td><a href="shoppinglist/${pp.id}">${pp.id}${pp.name}</a></td><td><a class="btn btn-danger btn-small" href="#" onclick="deleteButton_onClick()">Delete</a></td>
            </form:form>
        </tr>
    </c:forEach>
</table>

<c:set var="model" value="shoppingListModel" />


<%@ include file="/WEB-INF/views/common/validation_scripts.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>