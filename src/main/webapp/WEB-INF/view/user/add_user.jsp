<%--suppress XmlDuplicatedId --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container">
    <div class="omb_login">
        <h3 class="omb_authTitle"><s:message code="user.add_user.sign_up"/> <s:message code="user.add_user.or"/>
            <a href="<c:url value="/user/login"/>"><s:message code="user.add_user.login"/></a></h3>

        <div class="row omb_row-sm-offset-3">
            <div class="col-xs-12 col-sm-6">
                <sf:form modelAttribute="userDTO" method="POST" cssClass="omb_loginForm"
                         autocomplete="false" enctype="utf8">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-user"></i></span>
                        <sf:input path="username" maxlength="15" size="15" cssClass="form-control"/>
                    </div>
                    <div class="error" id="usernameError"><s:message code="user.add_user.username_is_taken"/></div>
                    <sf:errors path="username" cssClass="error"/>
                    <span class="help-block"></span>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-lock"></i></span>
                        <sf:password path="password" size="30" cssClass="form-control"/>
                    </div>
                    <sf:errors path="password" cssClass="error"/>
                    <span class="help-block"></span>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-lock"></i></span>
                        <sf:password path="passwordConfirm" size="30" cssClass="form-control"/>
                    </div>
                    <sf:errors cssClass="error" />
                    <span class="help-block"></span>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-envelope"></i></span>
                        <sf:input path="email" size="30" cssClass="form-control"/>
                    </div>
                    <sf:errors path="email" cssClass="error"/>
                    <span class="help-block"></span>

                    <button class="btn btn-lg btn-primary btn-block" type="submit"><s:message code="user.add_user.sign_up_action"/></button>
                    <sec:csrfMetaTags/>
                </sf:form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('#usernameError').hide();

        // check name availability on focus lost
        $('#name').blur(function() {
            checkAvailability(function(result) {
                if (result) {
                    $('#usernameError').hide();
                } else {
                    $('#usernameError').show();
                }
            });
        });

        $('#authtoken').submit(function(e) {
            checkAvailability(function(result) {
                if (result) {
                    $('#usernameError').hide();
                } else {
                    $('#usernameError').show();
                    e.preventDefault();
                }
            });
        });

        $('#username').watermark('Username');
        $('#password').watermark('Password');
        $('#passwordConfirm').watermark('Confirm password');
        $('#email').watermark('E-mail')
    });

    function checkAvailability(callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/user/is_user_exists?name=" + $('#name').val(),
            type: 'get',
            dataType: 'html',
            async: true,
            success: function(data) {
                callback(data === 'true');
            }
        });
    }
</script>