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
    <title>Cập nhật sản phẩm</title>
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
            <a href="./manage-product.jsp">Quản lý sản phẩm</a> / <a
                href="./update-product.jsp">Cập nhật</a>
        </div>
        <div class="container-form">
            <div class="container-layout">
                <h2>Cập nhật sản phẩm</h2>
                <form class="add-form">
                    <div class="form-group">
                        <label for="product-id">ID Sản Phẩm</label>
                        <input type="text" id="product-id" name="product-id" placeholder="Nhập ID sản phẩm"/>
                    </div>

                    <div class="form-group">
                        <label for="product-name">Tên Sản Phẩm</label>
                        <input type="text" id="product-name" name="product-name" placeholder="Nhập tên sản phẩm"/>
                    </div>

                    <div class="form-group">
                        <label for="description">Mô Tả Sản Phẩm</label>
                        <textarea id="description" name="description" placeholder="Nhập mô tả sản phẩm"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="category">Danh Mục</label>
                        <select id="category" name="category">
                            <option value="">Chọn danh mục</option>
                            <option value="clothes">Quần áo</option>
                            <option value="shoes">Giày dép</option>
                            <option value="accessories">Phụ kiện</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="color">Màu Sắc</label>
                        <select id="color" name="color">
                            <option value="">Chọn màu sắc</option>
                            <option value="red">Đỏ</option>
                            <option value="blue">Xanh dương</option>
                            <option value="black">Đen</option>
                            <option value="white">Trắng</option>
                            <option value="yellow">Vàng</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="size">Kích Thước</label>
                        <select id="size" name="size">
                            <option value="">Chọn kích thước</option>
                            <option value="S">S</option>
                            <option value="M">M</option>
                            <option value="L">L</option>
                            <option value="XL">XL</option>
                            <option value="XXL">XXL</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="stock">Số Lượng Tồn Kho</label>
                        <input type="number" id="stock" name="stock" placeholder="Nhập số lượng" min="0"/>
                    </div>

                    <div class="form-group">
                        <label for="price">Giá Bán</label>
                        <input type="text" id="price" name="price" placeholder="Nhập giá sản phẩm"/>
                    </div>

                    <div class="form-group">
                        <label for="product-image">Hình Ảnh</label>
                        <div class="file-input">
                            <button type="button">
                                <i class="fas fa-upload"></i> Chọn ảnh
                            </button>
                            <input type="file" id="product-image" name="product-image"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="status">Trạng Thái</label>
                        <select id="status" name="status">
                            <option value="available">Còn hàng</option>
                            <option value="out-of-stock">Hết hàng</option>
                        </select>
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
<script src="https://cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
<script>
    CKEDITOR.replace('description');
</script>
</body>
</html>
