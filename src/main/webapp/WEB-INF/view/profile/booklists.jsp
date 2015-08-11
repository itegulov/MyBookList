<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach items="${booklists}" var="booklist">
    <h4>${booklist.name}</h4>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>#</th>
                <th>Author</th>
                <th>Title</th>
                <th>Score</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${booklist.books}" var="book" varStatus="loop">
                <tr>
                    <th scope="row" class="col-md-1">${loop.index + 1}</th>
                    <td class="col-md-3">${book.author.name} ${book.author.family}</td>
                    <td class="col-md-6"><a href="<c:url value="/books?id=${book.id}"/>">${book.title}</a></td>
                    <td class="col-md-2">8</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:forEach>