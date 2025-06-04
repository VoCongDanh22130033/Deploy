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
            <!-- table2 2 -->
            <h2>Quản lý đơn hàng</h2>
            <div class="action-buttons">
                <!-- <a href="#" class="btn btn-add"><i class="fa-solid fa-plus"></i>Thêm đơn hàng</a> -->
                <button class="btn btn-upload">Tải từ file</button>
                <button class="btn btn-print">In dữ liệu</button>
                <button class="btn btn-copy">Sao chép</button>
                <button class="btn btn-excel">Xuất Excel</button>
                <button class="btn btn-pdf">Xuất PDF</button>
                <button class="btn btn-delete">Xóa tất cả</button>
            </div>
            <table class="table" id="orderTable">
                <thead>
                <tr>
                    <th>ID Đơn Hàng</th>
                    <th>Tên Tài Khoản</th>
                    <th>Họ và Tên</th>
                    <th>Email</th>
                    <th>Số Điện Thoại</th>
                    <th>Địa Chỉ Giao Hàng</th>
                    <th>Tổng Tiền</th>
                    <th>Trạng Thái</th>
                    <th>Ngày Đặt Hàng</th>
                    <th>Thanh Toán</th>
                    <th>Tính Năng</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>#DH${order.idOrder}</td>
                        <td>${order.user.username}</td>
                        <td>${order.user.fullName}</td>
                        <td>${order.user.email}</td>
                        <td>${order.user.phoneNumber}</td>
                        <td>${order.address.address}, ${order.address.ward}, ${order.address.district}, ${order.address.province}</td>
                        <td><fmt:formatNumber value="${order.totalPrice}" type="currency"/></td>
                        <td>${order.status}</td>
                        <td><fmt:formatDate value="${order.createdAtAsDate}" pattern="yyyy-MM-dd"/></td>
                        <td>COD</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/view-order?id=${order.idOrder}" class="btn btn-view"><i class="fas fa-eye"></i></a>
                            <form action="./delete-order" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${order.idOrder}" />
                                <button class="btn btn-trash" onclick="return confirm('Xoá đơn hàng?')"><i class="fas fa-trash-alt"></i></button>
                            </form>
                            <a href="./update-order.jsp?id=${order.idOrder}" class="btn btn-edit"><i class="fas fa-edit"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </section>
</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#orderTable').DataTable({
            "paging": true,         // Bật phân trang
            "searching": true,      // Bật tìm kiếm
            "ordering": true,       // Bật sắp xếp cột
            "info": true,           // Hiển thị thông tin tổng số dòng
            "order": [[9, "desc"]],  // 👉 Thêm dòng này để mặc định sắp xếp theo Ngày Đặt Hàng giảm dần
            "language": {           // Tùy chỉnh ngôn ngữ
                "search": "Tìm kiếm:",
                "lengthMenu": "Hiển thị _MENU_ dòng",
                "info": "Hiển thị _START_ đến _END_ của _TOTAL_",
                "paginate": {
                    "first": "Đầu",
                    "last": "Cuối",
                    "next": "Tiếp",
                    "previous": "Trước"
                }
            }
        });
    });
</script>
</body>
</html>
