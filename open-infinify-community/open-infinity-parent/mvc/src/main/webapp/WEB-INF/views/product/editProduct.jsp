<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

		<div class="container">
<%-- 			<%@ include file="/WEB-INF/views/error/generic_error.jsp"%> --%>
			<br />
			<h2>
				Edit Product
			</h2>
			
			<br />
			<div class="sub_menu"><b>Please Define The Product Information</b></div>
			<br />	
			
			<div id="statusbox"></div>
			<br/>
			<div id="productForm" class="span-12 last">	
				<form:form modelAttribute="productModel" action="product" method="POST">
					<table id="productTable">
						<tr>
							<td><form:label	id="nameLabel" for="name" path="name" cssErrorClass="error">Name</form:label></td>
							<td><form:input path="name" /></td>
							<td><form:errors path="name" /></td>
						</tr>
						<tr>	
							<td><form:label for="description" path="description" cssErrorClass="error">Description</form:label></td>
							<td><form:input path="description" /></td>
							<td><form:errors path="description" /></td>
						</tr>
						<tr>	
							<td><form:label for="company" path="company" cssErrorClass="error">Company</form:label></td>
							<td><form:input path="company" /></td>
							<td><form:errors path="company" /></td>
						</tr>
						<tr>	
							<td><form:label for="price" path="price" cssErrorClass="error">Price</form:label></td>
							<td><form:input path="price" /></td>
							<td><form:errors path="price" /></td>
						</tr>
						<tr>
							<td>
								<p>	
									<form:hidden path="id" id="id" />
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
	<script type="text/javascript">	
		$(document).ready(function() {
			$("#productModel").submit(function() {
				var request = $(this).serializeObject();
				$.postJSON("productModel", request, 
				function(request) {
					setStatusField("Product registered successfully!");
					$.each($('#productModel').serializeArray(), function(i, field) {
					    fieldValidated(field.name, { valid : true });
					});
					}, 
				function(data){
					// Set default view
					$.each($('#productModel').serializeArray(), function(i, field) {
					    fieldValidated(field.name, { valid : true });
					});
					// Set error view
					var obj = jQuery.parseJSON(data.responseText);
					var errorCounter = 0;
					$.each(obj, function(key, val) {
						fieldValidated(key, { valid : false, message : val});
						errorCounter++;
					});
					setStatusField("Product under editing contains " + errorCounter + " warning messages.");
				});
				return false;				
			});
		});
		
		function checkErrorFieldStatusForObject(field) {
			if (field==true) {
				fieldValidated(field, { valid : true });
			} else {
				fieldValidated(field, { valid : false, message : $('#'+field).val() + " is not valid."});
			}
		}
		
		function setStatusField(status) {
			document.getElementById('statusbox').innerHTML=status;
		}
		
		function validateField(validationUrl, field) {
			$.getJSON(validationUrl, { name: $("#" + field).val() }, function(fieldStatus) {
				if (fieldStatus.stringFieldValid) {
					fieldValidated(field, { valid : true });
				} else {
					fieldValidated(field, { valid : false, message : $("#" + field).val() + " field contains errros: "+ fieldStatus.stringFieldInvalid});
				}
			});
		}

		function fieldValidated(field, result) {
			if (result.valid) {
				$("#" + field).css({backgroundColor: 'white', border: '1px solid black', color: 'black', border: '3px inset green', foreGround: 'green'});
				$("#" + field + "Label").removeClass("error");
				$("#" + field + "\\.errors").remove();
				$('#create').attr("disabled", false);
			} else {
				$("#" + field + "Label").addClass("error");
				if ($("#" + field + "\\.errors").length == 0) {
					$("#" + field).css({backgroundColor: '#ffe', border: '3px inset red'});
					$("#" + field).after("<span id='" + field + ".errors'> " + result.message + "</span>").css({color: 'red'});		
				} else {
					$("#" + field + "\\.errors").html("<span id='" + field + ".errors'> " + result.message + "</span>");		
				}				
			}			
		}

		function resetForm() {
			$('#productModel')[0].reset();
		}
		
	</script>

	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>