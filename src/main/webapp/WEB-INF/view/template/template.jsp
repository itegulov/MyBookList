<%@ include file="/WEB-INF/view/template/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div id="banner">
    <tiles:insertAttribute name="header" />
</div>
<div></div>
<div></div>
<div id="page">
    <tiles:insertAttribute name="content" />
</div>
<div></div>
<div id="footer_wrapper">
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>