<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

    <h2>${"catalogname"} contains:</h2>
    <table>
        <c:forEach var="o" items="${productlist}">

            <tr>
                <td{o}</td>
            </tr>
        </c:forEach>
    </table>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>