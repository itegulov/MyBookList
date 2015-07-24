<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="text-left">${book.title}</h1>

<div class="row">
    <div class="col-md-3">
        <h2>by ${book.author.name} ${book.author.family}</h2>
        <img class="img-responsive" src="/res/images/books/${book.imagePath}">

        <p><b>ISBN-13:</b> ${book.ISBN}</p>
    </div>
    <div class="col-md-9">
        <h2>Description</h2>

        <p>${book.description}</p>
    </div>
</div>