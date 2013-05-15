<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
    <table>
        <c:forEach var="o" items="${catalogs}">
            
            <tr>
            	<form form:form method = "DELETE" action ="catalogue/delete/${o.id}">
                <td>${o.id}<input type="submit" id="${o.id}" value="Delete"></input></td>
            	</form>
            </tr>
        </c:forEach>
    </table>
    

</body>
</html>
<%@ include file="/WEB-INF/views/common/validation_scripts.jsp" %>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>