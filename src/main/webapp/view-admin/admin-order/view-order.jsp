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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/view.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/both-nav.css">
    <title>Chi tiết đơn hàng</title>
    <style>
        #buy-table td {
            color: #000 !important;
        }
        #buy-table th {
            background-color: #333;
            color: #fff;
        }
        #buy-table tbody tr:hover td {
            color: #000 !important;
        }
    </style>
</head>
<body>

<!-- Side Nav -->
<%@ include file="../../assert/sidenav.jsp" %>

<div class="container">
    <!-- Navbar -->
    <%@ include file="../../assert/topnav.jsp" %>

    <!-- Content -->
    <div class="container-content">
        <h2 class="title">Chi Tiết Đơn Hàng</h2>

        <!-- Thông tin khách hàng -->
        <div class="card">
            <div class="card-header">
                <h4>Thông Tin Cá Nhân</h4>
            </div>
            <div class="card-body">
                <p><strong>ID:</strong> #DH${order.idOrder}</p>
                <p><strong>Tên Tài Khoản:</strong> ${order.user.username}</p>
                <p><strong>Họ và Tên:</strong> ${order.user.fullName}</p>
                <p><strong>Email:</strong> ${order.user.email}</p>
                <p><strong>Số Điện Thoại:</strong> ${order.user.phoneNumber}</p>
                <p><strong>Địa Chỉ:</strong> ${order.address.address}, ${order.address.ward}, ${order.address.district}, ${order.address.province}</p>
            </div>
        </div>

        <!-- Thông tin tài khoản -->
        <div class="card">
            <div class="card-header">
                <h4>Thông Tin Tài Khoản</h4>
            </div>
            <div class="card-body">
                <p><strong>Hình Thức Đăng Nhập:</strong> Google</p>
                <p><strong>Trạng Thái:</strong> Hoạt động</p>
                <p><strong>Ngày Đăng Ký:</strong> 2024-01-15</p>
            </div>
        </div>

        <!-- Danh sách hàng hóa đã mua -->
        <div class="card">
            <div class="card-header">
                <h4>Danh Sách Hàng Hóa Đã Mua</h4>
            </div>
            <div class="card-body">
                <table class="table" id="buy-table">
                    <thead>
                    <tr>
                        <th>Sản Phẩm</th>
                        <th>Số Lượng</th>
                        <th>Giá</th>
                        <th>Thành Tiền</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="d" items="${details}">
                        <tr>
                            <td>${d.nameProduct}</td>
                            <td>${d.quantity}</td>
                            <td><fmt:formatNumber value="${d.discountPrice > 0 ? d.discountPrice : d.price}" type="currency"/></td>
                            <td>
                                <fmt:formatNumber value="${(d.discountPrice > 0 ? d.discountPrice : d.price) * d.quantity}" type="currency"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>


        <!-- Ghi chú đơn hàng -->
        <div class="card">
            <div class="card-header">
                <h4>Ghi Chú Đơn Hàng</h4>
            </div>
            <div class="card-body">
                <p>Khách hàng yêu cầu giao hàng trước 18:00.</p>
                <p>Ghi chú thêm: Vui lòng gọi trước khi giao.</p>
            </div>
        </div>

        <div class="card mt-4">
            <div class="card-header">
                <h4>Cập nhật trạng thái đơn hàng</h4>
            </div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin/update-order-status" method="post">
                    <input type="hidden" name="idOrder" value="${order.idOrder}" />
                    <div class="form-group">
                        <label>Trạng thái đơn hàng:</label>
                        <select class="form-control" name="status">
                            <c:forEach var="s" items="${['Chờ xác nhận', 'Đang giao', 'Hoàn thành', 'Đã hủy']}">
                                <option value="${s}" ${order.status == s ? 'selected' : ''}>${s}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Cập nhật</button>
                </form>
            </div>
        </div>


        <!-- Nút quay lại -->
        <div class="back-btn">
            <a href="${pageContext.request.contextPath}/admin/manage-order" class="btn btn-secondary"><i class="fas fa-arrow-left"></i></a>
        </div>
    </div>

</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/admin.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#buy-table').DataTable({
            "paging": true,         // Bật phân trang
            "searching": true,      // Bật tìm kiếm
            "ordering": true,       // Bật sắp xếp cột
            "info": true,           // Hiển thị thông tin tổng số dòng
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
