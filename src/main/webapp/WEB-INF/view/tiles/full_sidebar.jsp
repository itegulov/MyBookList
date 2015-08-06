<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="profile-sidebar">
    <div class="profile-userpic">
        <img src="http://keenthemes.com/preview/metronic/theme/assets/admin/pages/media/profile/profile_user.jpg"
             class="img-responsive" alt="">
    </div>
</div>
<div class="profile-usertitle">
    <div class="profile-usertitle-name">
        ${user.name}
    </div>
    <div class="profile-usertitle-job">
        Book lover
    </div>
</div>
<div class="profile-userbuttons">
    <button type="button" class="btn btn-success btn-sm">Follow</button>
    <button type="button" class="btn btn-danger btn-sm">Message</button>
</div>
<div class="profile-usermenu">
    <ul class="nav">
        <li>
            <a href="<c:url value="/profile/${user.name}"/>">
                <i class="glyphicon glyphicon-home"></i>
                Overview </a>
        </li>
        <li>
            <a href="<c:url value="/profile/settings/${user.name}"/>">
                <i class="glyphicon glyphicon-user"></i>
                Account Settings </a>
        </li>
        <li>
            <a href="<c:url value="/profile/booklists/${user.name}"/>">
                <i class="glyphicon glyphicon-ok"></i>
                Book lists </a>
        </li>
    </ul>
</div>