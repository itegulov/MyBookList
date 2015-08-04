<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <div id="error">
        <spring:message code="message.logoutError"/>
    </div>
</c:if>
<c:if test="${param.logSucc == true}">
    <div id="success">
        <spring:message code="message.logoutSucc"/>
    </div>
</c:if>
<body>
<a href="<c:url value="/user/login"/>">Login</a>
</body>