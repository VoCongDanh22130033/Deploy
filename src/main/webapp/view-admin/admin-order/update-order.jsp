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
    <title>Cập nhật đơn hàng</title>
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
            <a href="./manage-order.jsp">Quản lý đơn hàng</a> / <a href="./update-order.jsp">Cập nhật</a>
        </div>
        <div class="container-form">
            <div class="container-layout">
                <h2>Cập nhật đơn hàng</h2>
                <form class="add-form">
                    <div class="form-group">
                        <label for="order-id">ID Đơn Hàng</label>
                        <input type="text" id="order-id" name="order-id" value="#DH12837" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="username">Tài khoản</label>
                        <input type="text" id="username" name="username" value="nguyenA" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="name">Họ và Tên</label>
                        <input type="text" id="name" name="name" value="Nguyễn Văn A" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" id="email" name="email" value="nguyenvana@example.com" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="phone">Số điện thoại</label>
                        <input type="text" id="phone" name="phone" value="0901234567"/>
                    </div>

                    <div class="form-group">
                        <label for="address">Địa chỉ giao hàng</label>
                        <input type="text" id="address" name="address" value="123 Đường ABC, Quận 1, TP.HCM"/>
                    </div>

                    <div class="form-group">
                        <label for="total-price">Tổng Tiền</label>
                        <input type="text" id="total-price" name="total-price" value="5,000,000 VND" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="order-status">Trạng Thái Đơn Hàng</label>
                        <select id="order-status" name="order-status">
                            <option value="pending">Chờ xác nhận</option>
                            <option value="processing">Đang giao</option>
                            <option value="completed">Hoàn thành</option>
                            <option value="cancelled">Đã hủy</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="order-date">Ngày Đặt Hàng</label>
                        <input type="date" id="order-date" name="order-date" value="2025-02-23" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="delivery-date">Ngày Giao Dự Kiến</label>
                        <input type="date" id="delivery-date" name="delivery-date"/>
                    </div>

                    <div class="form-group">
                        <label for="payment-method">Phương Thức Thanh Toán</label>
                        <select id="payment-method" name="payment-method">
                            <option value="cod">Thanh toán khi nhận hàng (COD)</option>
                            <option value="bank-transfer">Chuyển khoản</option>
                            <option value="e-wallet">Ví điện tử</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="order-notes">Ghi Chú Đơn Hàng</label>
                        <textarea id="order-notes" name="order-notes" placeholder="Nhập ghi chú nếu có"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="order-table">Danh Sách Sản Phẩm</label>
                        <table class="table-order-items" id="order-table">
                            <thead>
                            <tr>
                                <th>Tên Sản Phẩm</th>
                                <th>Số Lượng</th>
                                <th>Giá</th>
                                <th>Tổng</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Áo Thun Nam</td>
                                <td>2</td>
                                <td>250,000 VND</td>
                                <td>500,000 VND</td>
                            </tr>
                            <tr>
                                <td>Quần Jeans</td>
                                <td>1</td>
                                <td>700,000 VND</td>
                                <td>700,000 VND</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="save-button">Cập Nhật</button>
                        <button type="button" class="cancel-button">Hủy bỏ</button>
                    </div>
                </form>

            </div>
        </div>
    </section>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/admin.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#order-table').DataTable({
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
<script src="https://cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
<script>
    CKEDITOR.replace('order-notes');
</script>
</body>
</html>
