<%--suppress XmlDuplicatedId --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="omb_login">
        <h3 class="omb_authTitle">Sign up or <a href="<c:url value="/user/login"/>">Login</a></h3>

        <div class="row omb_row-sm-offset-3">
            <div class="col-xs-12 col-sm-6">
                <sf:form modelAttribute="authtoken" method="POST" enctype="multipart/form-data" cssClass="omb_loginForm" autocomplete="false">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-user"></i></span>
                        <sf:input path="name" maxlength="15" size="15" cssClass="form-control"/>
                    </div>
                    <div class="error" id="usernameError">This username is already taken</div>
                    <sf:errors path="name" cssClass="error"/>
                    <span class="help-block"></span>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-lock"></i></span>
                        <sf:password path="password" size="30" cssClass="form-control"/>
                    </div>
                    <sf:errors path="password" cssClass="error"/>
                    <span class="help-block"></span>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-fw fa-envelope"></i></span>
                        <sf:input path="email" size="30" cssClass="form-control"/>
                    </div>
                    <sf:errors path="email" cssClass="error"/>
                    <span class="help-block"></span>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
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