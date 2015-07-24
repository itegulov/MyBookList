<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add new user</title>

    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>
<sf:form modelAttribute="user" method="POST" enctype="multipart/form-data">
    <fieldset>
        <table cellspacing="0">
            <tr>
                <th><sf:label path="name">Username:</sf:label></th>
                <td><sf:input path="name" maxlength="15" size="15"/></td>
                <td><sf:errors path="name" cssClass="error"/></td>
            </tr>
            <tr>
                <th><sf:label path="password">Password:</sf:label></th>
                <td><sf:password path="password" size="30"/></td>
                <td><sf:errors path="password" cssClass="error"/></td>
            </tr>
            <tr>
                <th><sf:label path="email">Email:</sf:label></th>
                <td><sf:input path="email" size="30"/></td>
                <td><sf:errors path="email" cssClass="error"/></td>
            </tr>
            <tr>
                    <%--<th><sf:label path="avatar">Avatar: </sf:label></th>--%>
                    <%--<td><input type="file" name="avatar"/></td>--%>
            </tr>
            <tr>
                <th></th>
                <td><input name="commit" type="submit" value="Sign Up"></td>
            </tr>
        </table>
    </fieldset>
</sf:form>
</body>
</html>