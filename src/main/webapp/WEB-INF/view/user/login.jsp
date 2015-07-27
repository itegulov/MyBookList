<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="omb_login">
        <h3 class="omb_authTitle">Login or <a href="<c:url value="/user?new"/>">Sign up</a></h3>
        <c:url value="/login_user" var="loginUrl" />
        <div class="row omb_row-sm-offset-3">
            <div class="col-xs-12 col-sm-6">
                <form action="${loginUrl}" method="post" class="omb_loginForm" autocomplete="off">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-user"></i></span>
                        <input name="username" type="text" maxlength="15" size="15" class="form-control"/>
                    </div>
                    <span class="help-block"></span>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-lock"></i></span>
                        <input name="password" type="password" maxlength="30" class="form-control"/>
                    </div>
                    <span class="help-block"></span>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>