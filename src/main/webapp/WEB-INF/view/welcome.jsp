<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in</title>
    <link rel="stylesheet" href="../../static/styles.css">
</head>
<body>
    <h1>Welcome to Crazy Apartment</h1>
    <div>
        <form class="app-form" method="post" action="/login-user">
            <label>Email</label>
            <input name="email" type="email">
            <label>Password</label>
            <input name="password" type="password">
            <label></label>
            <input type="submit" value="login"/>
        </form>
    </div>

    <div>
        <form class="app-form" method="post" action="/register">
            <input type="submit" value="Register">
        </form>
    </div>

    <c:choose>
        <c:when test="${mode eq 'Invalid'}">
            <div>Invalid email or password, please try again</div>
        </c:when>
    </c:choose>

</body>
</html>