<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 class="text-center">Welcome to MyBookList! I hope you'll enjoy it.</h1>
    <div id="booksCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <c:forEach var="book" items="${books}" varStatus="loop">
                <li data-target="#booksCarousel" data-slide-to="${loop.index}"
                    <c:if test="${loop.index == 0}">class="active"</c:if>></li>
            </c:forEach>
        </ol>

        <div class="carousel-inner" role="listbox">
            <c:forEach var="book" items="${books}" varStatus="loop">
                <div class="item <c:if test="${loop.index == 0}">active</c:if>">
                    <a href="books?id=${book.id}"><img class="img-responsive center-block" src="/res/images/books/${book.imagePath}"></a>
                    <h3 class="text-center">${book.title}</h3>
                </div>
            </c:forEach>
        </div>

        <a class="left carousel-control" href="#booksCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#booksCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>