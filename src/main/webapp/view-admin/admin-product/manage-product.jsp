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
    <link rel="stylesheet" href="../../css/manage.css">
    <link rel="stylesheet" href="../../css/both-nav.css">
    <title>Quản lý sản phẩm</title>
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
            <h2>Quản lý sản phẩm</h2>
            <div class="action-buttons">
                <a href="./add-product.jsp" class="btn btn-add"><i class="fa-solid fa-plus"></i>Thêm sản phẩm</a>
                <button class="btn btn-upload">Tải từ file</button>
                <button class="btn btn-print">In dữ liệu</button>
                <button class="btn btn-copy">Sao chép</button>
                <button class="btn btn-excel">Xuất Excel</button>
                <button class="btn btn-pdf">Xuất PDF</button>
                <button class="btn btn-delete">Xóa tất cả</button>
            </div>
            <table class="table" id="productTable">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Sản Phẩm</th>
                    <th>Danh Mục</th>
                    <th>Màu Sắc</th>
                    <th>Kích Thước</th>
                    <th>Số Lượng Tồn</th>
                    <th>Giá</th>
                    <th>Hình Ảnh</th>
                    <th>Trạng Thái</th>
                    <th>Tính Năng</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>#P12837</td>
                    <td>Áo Thun Nam</td>
                    <td>Thời Trang Nam</td>
                    <td>Đỏ, Xanh, Đen</td>
                    <td>M, L, XL</td>
                    <td>120</td>
                    <td>350,000 VND</td>
                    <td><img src="" alt="Áo Thun Nam" width="170"></td>
                    <td><span class="status active">Còn hàng</span></td>
                    <td>
                        <button class="btn btn-trash"><i class="fas fa-trash-alt"></i></button>
                        <a href="./update-product.jsp" class="btn btn-edit"><i class="fas fa-edit"></i></a>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
    </section>
</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/admin.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#productTable').DataTable({
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
