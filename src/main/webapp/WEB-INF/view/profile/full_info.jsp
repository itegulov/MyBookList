<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-6">
        <h5>${user.name}'s details</h5>
        <table>
            <tr>
                <td class="col-md-3">Last online</td>
                <td class="col-md-9">${user.joinDate}</td>
            </tr>
            <tr>
                <td class="col-md-3">Joined</td>
                <td class="col-md-9">${user.joinDate}</td>
            </tr>
            <tr>
                <td class="col-md-3">Gender</td>
                <td class="col-md-9">Male</td>
            </tr>
        </table>
        <br>
        <h5>Stats</h5>
        <table>
            <tr>
                <td class="col-md-9">Total book lists count</td>
                <td class="col-md-3">${user.bookLists.size()}</td>
            </tr>
            <tr>
                <td class="col-md-9">Total books</td>
                <td class="col-md-3">${user.bookLists.size() * 2}</td>
            </tr>
        </table>
    </div>
    <div class="col-md-6">
        <h5>${user.name}'s random books</h5>
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Title</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${randomBooks}" var="randomBook" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td><a href="<c:url value="/books?id=${randomBook.id}"/>">${randomBook.title}</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>