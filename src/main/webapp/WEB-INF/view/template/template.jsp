<%@ include file="/WEB-INF/view/template/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <tiles:insertAttribute name="styles" />
    <!-- Bootstrap core CSS -->
    <link href="<s:url value="/res/frameworks/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<div id="banner">
    <tiles:insertAttribute name="header" />
</div>
<div id="page">
    <tiles:insertAttribute name="content" />
</div>
<div id="footer_wrapper">
    <tiles:insertAttribute name="footer" />
</div>
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="<s:url value="/res/frameworks/bootstrap/js/bootstrap.min.js"/>"></script>
<tiles:insertAttribute name="scripts" />
</body>
</html>