 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

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
                <td>${o.id}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>