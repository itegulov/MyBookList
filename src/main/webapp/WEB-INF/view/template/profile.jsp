<%@ include file="/WEB-INF/view/template/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><s:message code="appname"/></title>
    <tiles:insertAttribute name="styles"/>
    <!-- Bootstrap core CSS -->
    <link href="<s:url value="/res/frameworks/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <%--General CSS--%>
    <link href="<s:url value="/res/css/general.css"/>" rel="stylesheet">
    <script src="<c:url value="/res/frameworks/jquery/jquery.js"/>"></script>
    <script src="<s:url value="/res/frameworks/bootstrap/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div id="banner">
    <tiles:insertAttribute name="header"/>
</div>
<div class="container">
    <div class="row profile">
        <div class="col-md-3">
            <div id="sidebar">
                <tiles:insertAttribute name="sidebar"/>
            </div>
        </div>
        <div class="col-md-9">
            <div id="page">
                <tiles:insertAttribute name="content"/>
            </div>
        </div>
    </div>
</div>
<div id="footer_wrapper">
    <tiles:insertAttribute name="footer"/>
</div>
<tiles:insertAttribute name="scripts"/>
</body>
</html>