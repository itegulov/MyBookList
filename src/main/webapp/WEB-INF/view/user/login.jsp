<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container">
    <div class="omb_login">
        <h3 class="omb_authTitle"><s:message code="user.add_user.login"/> <s:message code="user.add_user.or"/>
            <a href="<c:url value="/user/sign_up"/>"><s:message code="user.add_user.sign_up"/></a></h3>
        <c:url value="/login_user" var="loginUrl"/>
        <div class="row omb_row-sm-offset-3">
            <div class="col-xs-12 col-sm-6">
                <c:if test='${requestScope.get("error") != null}'>
                    <p class='error'>
                        <s:message code="loginPage.form.errorLogin.label"/>
                    </p>
                </c:if>
                <form action="${loginUrl}" method="post" class="omb_loginForm" autocomplete="off">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-user"></i></span>
                        <input id="username" name="username" type="text" maxlength="15" size="15" class="form-control" title="username"/>
                    </div>
                    <span class="help-block"></span>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-lock"></i></span>
                        <input id="password" name="password" type="password" maxlength="30" class="form-control" title="username"/>
                    </div>
                    <span class="help-block"></span>

                    <button class="btn btn-lg btn-primary btn-block" type="submit"><s:message code="user.add_user.login_action"/></button>
                    <sec:csrfMetaTags/>
                    <sec:csrfInput />
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $('#username').watermark('Username');
        $('#password').watermark('Password');
    });
</script>