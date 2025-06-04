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
    <title>Thêm danh mục</title>
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
            <a href="./manage-category.jsp">Quản lý danh mục</a> / <a href="./add-category.jsp">Thêm</a>
        </div>
        <div class="container-form">
            <div class="container-layout">
                <h2>Thêm danh mục</h2>
                <form class="add-form">
                    <div class="form-group">
                        <label for="id-category">ID Danh Mục</label>
                        <input type="text" id="id-category" name="id-category" placeholder="Nhập ID danh mục"/>
                    </div>
                    <div class="form-group">
                        <label for="categoryType">Loại Danh Mục</label>
                        <input type="text" id="categoryType" name="categoryType" placeholder="Nhập loại danh mục"/>
                    </div>
                    <div class="form-group">
                        <label for="name">Tên Danh Mục</label>
                        <input type="text" id="name" name="name" placeholder="Nhập tên danh mục"/>
                    </div>
                    <div class="form-group">
                        <label for="description">Mô Tả</label>
                        <textarea id="description" name="description" placeholder="Nhập mô tả"></textarea>
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
