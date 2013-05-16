<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/common/includes.jsp"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="jumbotron">
        <h2>Open Infinity - Team B</h2>
        <br />
        <div class="sub_menu"><b>The Shopping Application</b></div>
        <br />
        <p><spring:message code="server.time"/> ${serverTime}.</p>
            <br/><br/>
            <a class="btn btn-large btn-primary" href="#">New shoppinglist</a>
            <br/><br/>

	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>