<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="navbar-brand"><s:message code="appname"/></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/"/>"><s:message code="header.home"/></a></li>
                <li><a href="<c:url value="/about"/>"><s:message code="header.about"/></a></li>
                <li><a href="<c:url value="/contacts"/>"><s:message code="header.contacts"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <li><a href="<c:url value="/user/login"/>"><s:message code="user.add_user.login"/></a></li>
                    <li><a href="<c:url value="/user/sign_up"/>"><s:message code="user.add_user.sign_up"/></a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li><span class="navbar-text">Welcome, <sec:authentication property="principal.username"/></span></li>
                    <li><a href="<c:url value="/user/logout"/>"><s:message code="user.logout"/></a></li>
                </sec:authorize>
                <li><a href="?language=en"><img src="<c:url value="/res/images/flags/en.png"/>"/></a></li>
                <li><a href="?language=ru"><img src="<c:url value="/res/images/flags/ru.png"/>"/></a></li>
            </ul>
        </div>
    </div>
</nav>