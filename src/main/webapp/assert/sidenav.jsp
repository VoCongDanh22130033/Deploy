<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!-- Side Nav -->
<nav class="navbar-left" id="navbar-left">
    <div class="sidebar-header">
        <div class="user-profile">
            <div class="sidebar-title">
                <a href="${pageContext.request.contextPath}/admin" class="brand-logo-mini"><img src="${pageContext.request.contextPath}/images/logo.png" alt="logo"
                                                                        width="125"></a>
                <h2>Litte Whale</h2>
            </div>
            <img class="user-avatar" src="${pageContext.request.contextPath}/images/avatar-admin.png" alt="User Image" width="100">
            <div class="user-info">
                <p class="user-name">AdminName</p>
                <p class="user-greeting">Chào mừng bạn trở lại</p>
            </div>
        </div>
    </div>
    <hr>
    <ul class="sidebar-menu">
        <li class="menu-item ">
            <a class="menu-link" href="${pageContext.request.contextPath}/admin">
                <span class="menu-icon"><i class="fa fa-tachometer-alt"></i></span>
                <span class="menu-title">Bảng điều khiển</span>
            </a>
        </li>
        <li class="menu-item">
            <a class="menu-link" href="${pageContext.request.contextPath}/view-admin/admin-user/manage-user.jsp">
                <span class="menu-icon"><i class="fa fa-users"></i></span>
                <span class="menu-title">Quản lý khách hàng</span>
            </a>
        </li>
        <li class="menu-item">
            <a class="menu-link" href="${pageContext.request.contextPath}/view-admin/admin-product/manage-product.jsp">
                <span class="menu-icon"><i class="fa fa-box"></i></span>
                <span class="menu-title">Quản lý sản phẩm</span>
            </a>
        </li>
        <li class="menu-item">
            <a class="menu-link" href="${pageContext.request.contextPath}/view-admin/admin-order/manage-order.jsp">
                <span class="menu-icon"><i class="fa fa-tasks"></i></span>
                <span class="menu-title">Quản lý đơn hàng</span>
            </a>
        </li>
        <li class="menu-item">
            <a class="menu-link" href="${pageContext.request.contextPath}/view-admin/admin-coupon/manage-coupon.jsp">
                <span class="menu-icon"><i class="fa-solid fa-ticket"></i></span>
                <span class="menu-title">Quản lý mã giảm giá</span>
            </a>
        </li>
        <li class="menu-item">
            <a class="menu-link" href="${pageContext.request.contextPath}/view-admin/admin-category/manage-category.jsp">
                <span class="menu-icon"><i class="fa fa-chart-bar"></i></span>
                <span class="menu-title">Quản lý danh mục</span>
            </a>
        </li>
        <li class="menu-item">
            <a href="${pageContext.request.contextPath}/admin/staff-list">
                <span class="menu-icon"><i class="fa fa-users"></i></span>
                <span class="menu-title">Quản Lý Nhân Viên</span>
            </a>
        </li>
        <!-- Nút đăng xuất -->
        <li class="menu-item logout-item">
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">
                <span class="menu-icon"><i class="fa fa-sign-out-alt"></i></span>
                <span class="menu-title">Đăng xuất</span>
            </a>
        </li>
    </ul>
</nav>
