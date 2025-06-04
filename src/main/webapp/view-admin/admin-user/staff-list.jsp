<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/both-nav.css">
    <title>Quản lý đơn hàng</title>
</head>
<body>

<!-- Side Nav -->
<%@ include file="../../assert/sidenav.jsp" %>

<div class="container">
    <!-- Navbar -->
    <%@ include file="../../assert/topnav.jsp" %>

    <!-- Table -->
    <section class="tables">
        <div class="container-table">
            <h2>Quản lý nhân viên</h2>
            <div class="action-buttons">
                <a href="${pageContext.request.contextPath}/admin/add-staff" class="btn btn-upload">Thêm Nhân Viên</a>
            </div>
            <table id="orderTable" class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Họ Và Tên</th>
                    <th>Email</th>
                    <th>Số Điện Thoại</th>
                    <th>Vai Trò</th>
                    <th>Hành Động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${staffList}">
                    <tr>
                        <td>${user.idUser}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.role}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/delete-staff?id=${user.idUser}"
                               onclick="return confirm('Bạn có chắc chắn muốn xóa nhân viên này?');"
                               class="btn btn-trash">
                                <i class="fas fa-trash-alt"></i>
                            </a>

                            <a href="${pageContext.request.contextPath}/admin/edit-staff?id=${user.idUser}"
                               class="btn btn-edit">
                                <i class="fas fa-edit"></i>
                            </a>
                        </td>


                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

</div>


</body>
</html>
