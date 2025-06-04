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
    <title>Thêm mã sản phẩm</title>
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
            <a href="./manage-coupon.jsp">Quản lý mã giảm giá</a> / <a href="./add-coupon.jsp">Thêm</a>
        </div>
        <div class="container-form">
            <div class="container-layout">
                <h2>Thêm mã giảm giá</h2>
                <form class="add-form">
                    <div class="form-group">
                        <label for="id-coupon">ID</label>
                        <input type="text" id="id-coupon" name="id-coupon" placeholder="Nhập ID mã giảm giá"/>
                    </div>

                    <div class="form-group">
                        <label for="code">Mã Giảm Giá</label>
                        <input type="text" id="code" name="code" placeholder="Nhập mã giảm giá"/>
                    </div>

                    <div class="form-group">
                        <label for="discount">Giá Trị Giảm</label>
                        <input type="number" id="discount" name="discount" placeholder="Nhập số tiền hoặc % giảm"/>
                    </div>

                    <div class="form-group">
                        <label for="type">Loại Giảm Giá</label>
                        <select id="type" name="type">
                            <option value="percentage">Giảm theo %</option>
                            <option value="fixed">Giảm theo số tiền</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="minOrder">Giá Trị Đơn Hàng Tối Thiểu</label>
                        <input type="number" id="minOrder" name="minOrder" placeholder="Nhập giá trị tối thiểu"/>
                    </div>

                    <div class="form-group">
                        <label for="startDate">Ngày Bắt Đầu</label>
                        <input type="date" id="startDate" name="startDate"/>
                    </div>

                    <div class="form-group">
                        <label for="endDate">Ngày Hết Hạn</label>
                        <input type="date" id="endDate" name="endDate"/>
                    </div>

                    <div class="form-group">
                        <label for="usageLimit">Số Lần Sử Dụng</label>
                        <input type="number" id="usageLimit" name="usageLimit" placeholder="Nhập số lần sử dụng"/>
                    </div>

                    <div class="form-group">
                        <label for="usedCount">Số Lần Đã Sử Dụng</label>
                        <input type="number" id="usedCount" name="usedCount" value="0" readonly/>
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
