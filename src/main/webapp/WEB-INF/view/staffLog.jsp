<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Crazy Staff Login</title>
    <link rel="stylesheet" href="../../static/styles.css">
</head>
<body>
    <header>
        <h1>Welcome, ${name}!</h1>
        <h2><a href="/log-out">Log out</a></h2>
        <nav>
            <ul>
                <li><a href="/user-management">User Management</a></li>
                <li><a href="/room-management">Room Management</a></li>
                <li><a href="/staff-message">Message</a></li>
            </ul>
        </nav>
    </header>
    <main>
<%--        mode-choose   --%>
        <c:choose>
<%--    when mode=user-management  --%>
            <c:when test="${mode eq 'MODE-USER-MANAGEMENT'}">
                <div>
                    <ul>
                        <li><a href="/show-user?pick=SHOW-USERS">Show Users</a></li>
                        <li><a href="/search-user?pick=SEARCH-USERS">Search User</a></li>
                    </ul>
<%--                    user-management-when--%>
                    <c:choose>
<%--                    user-management when pick=showusers--%>
                        <c:when test="${pick eq 'SHOW-USERS'}">
                            <div>
                                <table>
                                    <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>
                                            <form method="post" action="/show-roles">
                                                <select name="role" id="roleSelect">
                                                    <option value="">Role</option>
                                                    <option value="staff">staff</option>
                                                    <option value="resident">resident</option>
                                                    <option value="applicant">applicant</option>
                                                </select>
                                            </form>
                                        </th>
                                        <th>name</th>
                                        <th>Email</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${users}">
                                            <tr>
                                                <td>${user.id}</td>
                                                <td>${user.role}</td>
                                                <td>${user.name}</td>
                                                <td>${user.email}</td>
                                                <td><a href="/delete-user?id=${user.id}"><span>delete</span></a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:when>
<%--                    user-management when pick=searchuser    --%>
                        <c:when test="${pick eq 'SEARCH-USERS'}">
                            <div>
                                <form class="search-form" method="get" action="/search-user-result">
                                    <label>Search by name:</label>
                                        <input name="name" type="text">
                                    <label>Search by Id:</label>
                                        <input name="id" type="text">
                                    <input type="submit" value="submit">
                                </form>
                            </div>
                        <p>${saveInfo}</p>
<%--                    c choose :search-user--%>
                    <c:choose>
<%--                    user-management  search-user  when: result--%>
                        <c:when test="${search eq 'RESULT'}">
                        <div>
                            <table>
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Role</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${users}">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.role}</td>
                                        <td>${user.name}</td>
                                        <td>${user.email}</td>
                                        <td><a href="/delete-user?id=${user.id}"><span>delete</span></a></td>
                                        <td><a href="/edit-user?id=${user.id}"><span>edit</span></a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        </c:when>
<%--                    user-management  search-user  when: error--%>
                        <c:when test="${search eq 'NULL-ERROR'}">
                        <div>
                            <p>No result available</p>
                        </div>
                        </c:when>
<%--                    user-management  search-user  when: search-edit--%>
                        <c:when test="${search eq 'SEARCH-EDIT'}">
                            <form class="app-form" id="edit-form" method="post" action="/edit-user-result">
                                <label>Id</label>
                                    <input name="id" value="${user.id}" readonly>
                                <label>Role</label>
                                    <input name="role" value="${user.role}" readonly>
                                <label>Name</label>
                                    <input name="changeName" value="${user.name}">
                                <label>Email</label>
                                    <input name="changeEmail" value="${user.email}">
                                <input name="submit" type="submit" value="submit">
                            </form>
                        </c:when>
<%--                    c : choose search-user end--%>
                    </c:choose>
<%--                    c: choose when pick=search-user end--%>
                    </c:when>
<%--                    c: choose user-management end--%>
                    </c:choose>
                <div>
<%--                    c: when mode=user-management end--%>
            </c:when>
<%--    c when mode=room-management--%>
            <c:when test="${mode eq 'MODE-ROOM-MANAGEMENT'}">
                <div>
                    <ul>
                        <li><a href="/show-room?pick=SHOW-ROOM">Show Room</a></li>
                        <li><a href="/edit-room?pick=EDIT-ROOM">Edit Room</a></li>
                    </ul>
<%--                    c: choose room-manage choose  --%>
                    <c:choose>
<%--                        room-manage choose when pick=show-room--%>
                        <c:when test="${pick eq 'SHOW-ROOM'}">
                            <table class="app-table-big">
                                <thead>
                                    <tr>
                                        <th>Room number</th>
                                        <th>Room price</th>
                                        <th>Users</th>
                                        <th>parking</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="room" items="${rooms}">
                                        <tr>
                                            <td>${room.roomNumber}</td>
                                            <td>${room.price}</td>
                                            <td>
                                                <c:if test="${!empty room.usersInRoom}">
                                                    <c:forEach var="roomUser" items="${room.usersInRoom}">
                                                        <li class="room-user">user Id:${roomUser.id}    user name:${roomUser.name}</li>
                                                    </c:forEach>
                                                </c:if>
                                            </td>
                                            <td>${room.parking}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
<%--                            room-manage choose when pick=show-room end--%>
                        </c:when>
<%--                        room-manage choose when pick=edit-room--%>
                        <c:when test="${pick eq 'EDIT-ROOM'}">
                            <form class="search-form" method="get" action="/search-room-result">
                                <label>Search by room number:
                                    <input name="number" type="text">
                                </label>
                                <input type="submit" value="submit">
                            </form>
                            <p>${saveRoomInfo}</p>
                        </c:when>
<%--                        room-manage choose when pick=edit-room--%>
                        <c:when test="${search eq 'ROOM-RESULT'}">
                            <form class="app-form" method="post" action="/edit-room-submit">
                                <label>Room number</label>
                                    <input name="roomNumber" value="${room.roomNumber}">

                                <label>Price</label>
                                    <input name="price" value="${room.price}">

                                <label>User</label>
                                    <c:if test="${!empty usersInRoom}">
                                        <c:forEach var="roomUser" items="${usersInRoom}">
                                            <input name="user_id" value="${roomUser.id}">
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${empty usersInRoom}">
                                        <input name="user_id" value="type user id">
                                        <input id="addOne" value="add more user" onclick="addOneMore()">
                                        <span id="span-listener"></span>
                                    </c:if>
                                <label>Parking</label>
                                    <input name="parking" value="${room.parking}">

                                <input type="submit" value="submit">
                            </form>
                        </c:when>
<%--                        c: choose room-manage choose end--%>
                    </c:choose>
                </div>
<%--    when mode=room-manage end--%>
            </c:when>
<%--    when mode=message start--%>
            <c:when test="${mode eq 'MODE-MESSAGE'}">
                <div>
                    <ul>
                        <li><a href="/message-maintenance?pick=MESSAGE-MAINTENANCE">Maintenance</a></li>
                        <li><a href="/message-other?pick=MESSAGE-OTHER">Other Message</a></li>
                    </ul>
<%--                    c choose mode=message start--%>
                    <c:choose>
<%--                        c when mode=message pick-maintenance--%>
                        <c:when test="${pick eq 'MESSAGE-MAINTENANCE'}">
                            <div>
                                <table>
                                    <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Name</th>
                                        <th>Room number</th>
                                        <th>Date</th>
                                        <th>description</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="maintenance" items="${maintenances}">
                                        <tr>
                                            <td>${maintenance.id}</td>
                                            <td>${maintenance.name}</td>
                                            <td>${maintenance.room}</td>
                                            <td>${maintenance.date}</td>
                                            <td>${maintenance.description}</td>
                                            <td><a href="/delete-maintenance?id=${maintenance.id}"><span>Complete</span></a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
<%--                            c when mode =message pick=maintenance end--%>
                        </c:when>
<%--                        c when mode=message pick=other message start--%>
                        <c:when test="${pick eq 'MESSAGE-OTHER'}">
                            <div>
                                <table>
                                    <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Date</th>
                                        <th>Name</th>
                                        <th>subject</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="message" items="${messages}">
                                        <tr>
                                            <td>${message.id}</td>
                                            <td>${message.date}</td>
                                            <td>${message.name}</td>
                                            <td>${message.subject}</td>
                                            <td><a href="/show-message-detail?MessageId=${message.id}"><span>Choose</span></a></td>
                                            <td><a href="/delete-message?id=${message.id}"><span>Complete</span></a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
<%--                            c when mode=message pick=other-message end--%>
                        </c:when>
<%--                        c when mode=message pick=message-detail start--%>
                        <c:when test="${pick eq 'MESSAGE-DETAIL'}">
                            <form class="app-form" method="post" action="/message-detail-submit">
                                <label>Id</label>
                                    <input name="id" value="${message.id}" readonly>
                                <label>Date</label>
                                    <input name="date" value="${message.date}" readonly>
                                <label>Name</label>
                                    <input name="name" value="${message.name}"readonly>
                                <label>Subject</label>
                                    <input name="subject" value="${message.subject}"readonly>
                                <c:forEach var="detail" items="${details}">
                                    <label>History message</label>
                                    <input class="staff-reply" name="detail" value="${detail}" readonly>
                                </c:forEach>
                                <label>Reply:</label>
                                <input name="newDetail" value="From ${name}:">
                                <input name="submit" type="submit" value="submit">
                            </form>
                            <p>${saveMessageInfo}</p>
<%--                            c when mode=message pick=message-detail end--%>
                        </c:when>
<%--                        c choose mode=message end--%>
                    </c:choose>


                </div>


<%--c: when mode-message end--%>
            </c:when>
<%--    big mode choose end--%>
        </c:choose>
    </main>
        <script>
            document.getElementById('roleSelect').addEventListener('change', function() {
                var role = this.value;
                if (role !== '') {
                    window.location.href = '/show-roles?role=' + role;
                }
            });
        </script>
        <script>
            function addOneMore() {
                document.getElementById('span-listener').innerHTML = '<input name="user_id" value="type user id">';
            }
        </script>
</body>
</html>
