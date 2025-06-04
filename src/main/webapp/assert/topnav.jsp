<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!-- Navbar -->
<nav class="navbar">
    <div class="navbar-menu">
        <button class="navbar-toggler" id="navbar-toggle">
            <span class="navbar-icon">&#9776;</span>
        </button>
        <div class="search">
            <input type="text" placeholder="Search products">
        </div>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="../view-admin/admin-product/add-product.jsp" class="nav-link"><i class="fa-solid fa-plus"></i>Thêm
                    sản phẩm mới</a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link"><i class="fa-solid fa-message"></i> Tin Nhắn <span
                        class="badge">0</span></a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link"><i class="fa-solid fa-bell"></i> Thông Báo <span
                        class="badge">0</span></a>
            </li>
        </ul>
        <div class="profile-container">
            <a href="#" class="profile-link">
                <img src="https://anhcute.net/wp-content/uploads/2024/09/Hinh-anh-chibi-Spiderman-sieu-dang-yeu.jpg"
                     alt="Profile" class="profile-pic">
                <span class="profile-name">AdminName</span>
            </a>
        </div>
    </div>
</nav>