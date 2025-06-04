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
    <link rel="stylesheet" href="../../css/both-nav.css">
    <link rel="stylesheet" href="../../css/view.css">
    <title>Chi tiết khách hàng</title>
</head>
<body>

<!-- Side Nav -->
<%@ include file="../../assert/sidenav.jsp" %>

<div class="container">
    <!-- Navbar -->
    <%@ include file="../../assert/topnav.jsp" %>

    <!-- Content -->
    <div class="container-content">
        <h2 class="title">Chi Tiết Khách Hàng</h2>

        <!-- Thông tin khách hàng -->
        <div class="card">
            <div class="card-header">
                <h4>Thông Tin Cá Nhân</h4>
            </div>
            <div class="card-body">
                <p><strong>ID:</strong> #CD12837</p>
                <p><strong>Tên Tài Khoản:</strong> nguyenA</p>
                <p><strong>Họ và Tên:</strong> Nguyễn Văn A</p>
                <p><strong>Email:</strong> nguyenvana@example.com</p>
                <p><strong>Số Điện Thoại:</strong> 0901234567</p>
                <p><strong>Địa Chỉ:</strong> 123 Đường ABC, Quận 1, TP.HCM</p>
            </div>
        </div>

        <!-- Thông tin tài khoản -->
        <div class="card">
            <div class="card-header">
                <h4>Thông Tin Tài Khoản</h4>
            </div>
            <div class="card-body">
                <p><strong>Hình Thức Đăng Nhập:</strong> Google</p>
                <p><strong>Trạng Thái:</strong>Hoạt động</p>
                <p><strong>Ngày Đăng Ký:</strong> 2024-01-15</p>
            </div>
        </div>

        <!-- Thông tin mua hàng -->
        <div class="card">
            <div class="card-header">
                <h4>Thống Kê Mua Hàng</h4>
            </div>
            <div class="card-body">
                <p><strong>Tổng Tiền Đã Mua:</strong> 5,000,000 VND</p>
                <p><strong>Số Đơn Hàng:</strong> 12</p>
            </div>
        </div>

        <!-- Danh sách đơn hàng -->
        <div class="card">
            <div class="card-header">
                <h4>Danh Sách Đơn Hàng</h4>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th>ID Đơn Hàng</th>
                        <th>Ngày Đặt</th>
                        <th>Tổng Tiền</th>
                        <th>Trạng Thái</th>
                        <th>Chi Tiết</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>#DH1023</td>
                        <td>2024-02-15</td>
                        <td>1,500,000 VND</td>
                        <td>Đang Giao</td>
                        <td><a href="../admin-order/view-order.jsp" class="btn btn-primary btn-sm"><i class="fas fa-eye"></i>
                            Xem</a></td>
                    </tr>
                    <tr>
                        <td>#DH0987</td>
                        <td>2024-01-20</td>
                        <td>3,500,000 VND</td>
                        <td>Hoàn Thành</td>
                        <td><a href="../admin-order/view-order.jsp" class="btn btn-primary btn-sm"><i class="fas fa-eye"></i>
                            Xem</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Nút quay lại -->
        <div class="back-btn">
            <a href="manage-user.jsp" class="btn btn-secondary"><i class="fas fa-arrow-left"></i></a>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/admin.js"></script>
</body>
</html>
