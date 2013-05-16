<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>


    <script>
        function createButton_onClick() {
         $('#productModel').submit();
        }

        function deleteButton_onClick() {
         $('#command').submit();
        }
    </script>


       <div class ="container">
			
			<div id="productForm" class="span-12 last">	
				<form:form modelAttribute="productModel" action="productModel" method="POST">
				    <fieldset>
				            <legend>Create new product</legend>
							<form:label	for="name" path="name" cssErrorClass="error">Name</form:label>
							<form:input path="name" />
							<form:errors path="name" />
						
							
							<form:label for="description" path="description" cssErrorClass="error">Description</form:label>
							<form:input path="description" />
							<form:errors path="description" />
						
							
							<form:label for="company" path="company" cssErrorClass="error">Company</form:label>
							<form:input path="company" />
							<form:errors path="company" />
						
							
							<form:label for="price" path="price" cssErrorClass="error">Price</form:label>
							<form:input path="price" />
							<form:errors path="price" />
						
						
                            <form:label for="catalogue" path="catalogue" cssErrorClass="catalogue">Catalogue</form:label>
                            
                                <form:select path="catalogue">
                                    <form:options items="${catalogs}" itemValue="name" itemLabel="name"/>
                                </form:select>
                            
                            <form:errors path="catalogue" />
						
						
							

									<form:hidden path="id" id="id" /><br />
									<a class="btn btn-primary" href="#" onclick="createButton_onClick()">Create</a>



						
					</fieldset>
				</form:form>
			</div>
	   </div>
<h2>List of all Products</h2>

    <table class="table table-striped">
        <tr>
        <td>Product ID</td><td></td>
        </tr>

        <c:forEach var="o" items="${products}">
            <tr>
                <form:form method="DELETE" action="product/${o.id}">
                <td><a href="product/${o.id}">${o.name}</a></td><td><a class="btn btn-danger btn-small" href="#" onclick="deleteButton_onClick()">Delete</a></td>
                </form:form>
            </tr>
        </c:forEach>
    </table>



		<c:set var="model" value="productModel" />
		
<%@ include file="/WEB-INF/views/common/validation_scripts.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>