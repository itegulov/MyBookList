<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="omb_login">
        <h3 class="omb_authTitle">Login or <a href="<c:url value="/user?new"/>">Sign up</a></h3>

        <div class="row omb_row-sm-offset-3">
            <div class="col-xs-12 col-sm-6">
                <sf:form modelAttribute="user" method="POST" enctype="multipart/form-data" cssClass="omb_loginForm" autocomplete="false">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-user"></i></span>
                        <sf:input path="name" maxlength="15" size="15" cssClass="form-control"/>
                    </div>
                    <sf:errors path="name" cssClass="error"/>
                    <span class="help-block"></span>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-lock"></i></span>
                        <sf:password path="password" size="30" cssClass="form-control"/>
                    </div>
                    <sf:errors path="password" cssClass="error"/>
                    <span class="help-block"></span>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
                </sf:form>
            </div>
        </div>
    </div>
</div>