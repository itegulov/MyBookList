<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    Here is list of all books yay!
    <table>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.ISBN}</td>
                <td>${book.title}</td>
                <td>${book.description}</td>
                <td>${book.author}</td>
            </tr>
        </c:forEach>
    </table>
</div>