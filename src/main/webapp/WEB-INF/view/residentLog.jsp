<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Crazy Resident Login</title>
        <link rel="stylesheet" href="../../static/styles.css">
    </head>
<body>
    <header>
    <h1>Welcome, ${name}!</h1>
        <h2><a href="/log-out">Log out</a></h2>
    <nav>
        <ul class="main-menu">
            <li><a href="/resident-info">Update Information</a></li>
            <li><a href="/resident-maintenance">Report Maintenance</a></li>
            <li><a href="/resident-new-message">New Message</a></li>
            <li><a href="/resident-history-message">History Message</a></li>
        </ul>
        <p>${residentReplyInfo}</p>
    </nav>
    </header>
    <main>
        <div>
            <p>${updateRisidentInfo}</p>
            <p>${saveMaintenanceInfo}</p>
            <p>${saveMessageInfo}</p>
            <c:choose>
                <c:when test="${mode eq 'RESIDENT-INFO'}">
                    <form class="app-form" method="post" action="/edit-resident-info">
                        <label>Id</label>
                            <input name="id" value="${user.id}" readonly>
                        <label>Name</label>
                            <input name="name" value="${user.name}">
                        <label>Email</label>
                            <input name="email" value="${user.email}">
                        <label>Password</label>
                            <input name="password" value="${user.password}">
                        <input type="submit" value="submit">
                    </form>
                </c:when>

                <c:when test="${mode eq 'RESIDENT-MAINTENANCE'}">
                    <form class="app-form" method="post" action="/maintenance-submit">
                        <label>Name</label>
                            <input name="name" type="text">
                        <label>Room</label>
                            <input name="room" type="text">
                        <label>Description</label>
                            <input name="description" type="text">
                        <input type="submit" value="submit">
                    </form>
                </c:when>

                <c:when test="${mode eq 'RESIDENT-MESSAGE'}">
                    <form class="app-form" method="post" action="/resident-message-submit">
                        <label>Name</label>
                            <input name="name" type="text">
                        <label>Subject</label>
                            <input name="subject" type="text">
                        <label>Detail </label>
                            <input name="detail" value="From ${name}:" type="text">
                        <input type="submit" value="submit">
                    </form>
                </c:when>

                <c:when test="${mode eq 'RESIDENT-HISTORY-MESSAGE'}">
                    <div>
                        <table class="app-table">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Date</th>
                                <th>Subject</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="message" items="${messages}">
                                <tr>
                                    <td>${message.id}</td>
                                    <td>${message.date}</td>
                                    <td>${message.subject}</td>
                                    <td><a href="/resident-choose-message-detail?id=${message.id}"><span>Choose</span></a></td>
                                    <td><a href="/resident-delete-message?deleteId=${message.id}"><span>Complete</span></a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
            </c:when>


<%--                    <c:choose>--%>
                        <c:when test="${pick eq 'RESIDENT-CHOOSE-MESSAGE-DETAIL'}">
                            <form class="app-form" method="post" action="/resident-choose-message-submit">
                                <label>Id</label>
                                    <input name="id" value="${message.id}" readonly>
                                <label>Name</label>
                                    <input name="name" value="${message.name}" readonly>
                                <label>Subject</label>
                                    <input name="subject" value="${message.subject}" readonly>
                                <c:forEach var="detail" items="${details}">
                                    <label>History message</label>
                                    <input class="resident-message" name="detail" value="${detail}" readonly>
                                </c:forEach>
                                <label>New reply</label>
                                <input name="newDetail" value="From ${name}:">
                                <input type="submit" value="submit">
                            </form>
                        </c:when>

                    </c:choose>


<%--                </c:when>--%>


<%--            </c:choose>--%>
        </div>
    </main>
</body>
</html>