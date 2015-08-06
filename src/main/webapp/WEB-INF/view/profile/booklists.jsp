<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
    <c:forEach items="${booklists}" var="booklist">
        <tr>
            <td>${booklist.name}</td>
            <td>${booklist.books}</td>
        </tr>
    </c:forEach>
</table>