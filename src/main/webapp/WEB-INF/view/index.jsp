<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 class="text-center"><s:message code="mainPage.form.welcome.label"/></h1>

    <div class="jcarousel-wrapper">
        <div class="jcarousel">
            <ul>
                <c:forEach var="book" items="${books}" varStatus="loop">
                    <li><a href="books?id=${book.id}"><img class="img-responsive"
                                                           src="/res/images/books/${book.imagePath}"></a></li>
                </c:forEach>
            </ul>
        </div>

        <a href="#" class="jcarousel-control-prev">&lsaquo;</a>
        <a href="#" class="jcarousel-control-next">&rsaquo;</a>

        <p class="jcarousel-pagination"></p>
    </div>
</div>