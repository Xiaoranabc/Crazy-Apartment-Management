<%--
  Created by IntelliJ IDEA.
  User: zhaowenbo
  Date: 4/11/23
  Time: 11:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="../../static/styles.css">
</head>
<body>
<div>
  <form class="app-form" method="post" action="/save-user">
    <label>choose your role</label>
    <select name="role">
      <option value="staff">Staff</option>
      <option value="resident">Resident</option>
      <option value="applicant">Applicant</option>
    </select>
    <label>Name</label>
    <input name="name" type="text" value="${user.name}">
    <label>Email</label>
    <input name="email" type="email" value="${user.email}">
    <label>Password</label>
    <input name="password" type="password" value="${user.password}">
    <input type="submit" value="register">
  </form>
</div>
</body>
</html>
