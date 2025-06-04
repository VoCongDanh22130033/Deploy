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
    <link rel="stylesheet" href="../../css/add.css">
    <title>Cập nhật khách hàng</title>
</head>
<body>

<!-- Side Nav -->
<%@ include file="../../assert/sidenav.jsp" %>

<div class="container">
    <!-- Navbar -->
    <%@ include file="../../assert/topnav.jsp" %>

    <!-- Form -->
    <section class="form-add">
        <div class="breadcrumb">
            <a href="./manage-user.jsp">Quản lý khách hàng</a> / <a href="./update-user.jsp">Cập
            nhật</a>
        </div>
        <div class="container-form">
            <div class="container-layout">
                <h2>Cập nhật khách hàng</h2>
                <form class="add-form">
                    <div class="form-group">
                        <label for="id-user">ID</label>
                        <input type="text" id="id-user" name="id-user" placeholder="Nhập ID khách hàng" readonly/>
                    </div>
                    <div class="form-group">
                        <label for="username">Tài khoản</label>
                        <input type="text" id="username" name="username" placeholder="Nhập tài khoản" required/>
                    </div>
                    <div class="form-group">
                        <label for="name">Họ và tên</label>
                        <input type="text" id="name" name="name" placeholder="Nhập họ và tên" required/>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" placeholder="Nhập Email" required/>
                    </div>
                    <div class="form-group">
                        <label for="phone">Số điện thoại</label>
                        <input type="text" id="phone" name="phone" placeholder="Nhập số điện thoại"
                               pattern="[0-9]{10,11}" required/>
                    </div>
                    <div class="form-group">
                        <label for="address">Địa chỉ</label>
                        <input type="text" id="address" name="address" placeholder="Nhập địa chỉ" required/>
                    </div>
                    <div class="form-group">
                        <label for="login-method">Phương thức đăng nhập</label>
                        <select id="login-method" name="login-method">
                            <option value="email">Local</option>
                            <option value="email">Email</option>
                            <option value="google">Google</option>
                            <option value="facebook">Facebook</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="status">Trạng thái tài khoản</label>
                        <select id="status" name="status">
                            <option value="active">Hoạt động</option>
                            <option value="blocked">Bị khóa</option>
                            <option value="pending">Chờ xác minh</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="created-date">Ngày tạo tài khoản</label>
                        <input type="date" id="created-date" name="created-date" required/>
                    </div>
                    <div class="form-group">
                        <label for="image">Ảnh đại diện</label>
                        <div class="file-input">
                            <button type="button">
                                <i class="fas fa-upload"></i> Chọn ảnh
                            </button>
                            <input type="file" id="image" name="image" accept="image/*"/>
                        </div>
                        <img id="image-preview" src="#" alt="Ảnh xem trước"
                             style="display:none; width:100px; height:100px;"/>
                    </div>
                    <div class="form-actions">
                        <button type="submit" class="save-button">Lưu lại</button>
                        <button type="button" class="cancel-button">Hủy bỏ</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/admin.js"></script>
</body>
</html>
