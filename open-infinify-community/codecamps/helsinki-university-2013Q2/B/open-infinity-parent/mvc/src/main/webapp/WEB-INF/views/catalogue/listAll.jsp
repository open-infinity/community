<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
	
    <table>
        <c:forEach var="o" items="${catalogs}">
            
            <tr>
            	<form:form method="DELETE" action ="catalogue/delete/${o.id}">
                <td>${o.id}<input type="submit" value="Delete"></input></td>
            	</form:form>
            </tr>
        </c:forEach>
    </table>

<%@ include file="/WEB-INF/views/common/validation_scripts.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>