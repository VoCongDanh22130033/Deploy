<%--
  Created by IntelliJ IDEA.
  User: hdanh
  Date: 24/02/2025
  Time: 8:49 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@ include file="/view/view-index/header.jsp" %>
<section class="section-content padding-y">
    <!-- ============================ COMPONENT REGISTER   ================================= -->
    <!-- ============================ COMPONENT ĐĂNG KÝ ================================= -->
    <div class="card mx-auto" style="max-width:520px; margin-top:40px;">
        <article class="card-body">
            <header class="mb-4"><h4 class="card-title">Đăng ký</h4></header>
            <form action="${pageContext.request.contextPath}/register" method="post">
                <div class="form-row">
                    <div style="display: none;">
                        <label>Role</label>
                        <input type="text" name="idRole" class="form-control" value="2">
                    </div>
                    <div style="display: none;">
                        <label>Active</label>
                        <input type="text" name="active" class="form-control" value="1">
                    </div>
                    <!-- Họ và Tên -->
                    <div class="col form-group">
                        <label>Họ Và Tên</label>
                        <input type="text" name="fullname" class="form-control" placeholder="Nhập Họ Và Tên"
                               value="${param.fullname}">
                    </div>
                    <!-- Email -->
                    <div class="col form-group">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control" placeholder="Email"
                               value="${param.email}">
                        <c:if test="${not empty emailError}">
                            <div class="error-message text-danger">${emailError}</div>
                        </c:if>
                    </div>
                </div> <!-- form-row end.// -->
                <!-- Tên đăng nhập -->
                <div class="form-group">
                    <label>Tên Đăng Nhập</label>
                    <input type="text" name="username" class="form-control"
                           placeholder="Tên Đăng Nhập" required
                           pattern="^[a-zA-Z0-9_]{4,}$"
                           title="Chỉ được dùng chữ cái không dấu, số, dấu gạch dưới, ít nhất 4 ký tự"
                           value="${param.username}">
                    <c:if test="${not empty usernameError}">
                        <div class="error-message text-danger">${usernameError}</div>
                    </c:if>
                </div>
                <!-- Số điện thoại -->
                <div class="form-group">
                    <label>Số Điện Thoại</label>
                    <input type="text" name="phoneNumber" class="form-control"
                           placeholder="Số Điện Thoại"
                           value="${param.phoneNumber}"
                           required
                           pattern="^0\d{9}$"
                           title="Số điện thoại phải có đúng 10 chữ số và bắt đầu bằng số 0">
                    <c:if test="${not empty phoneError}">
                        <div class="error-message text-danger">${phoneError}</div>
                    </c:if>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Tạo mật khẩu</label>
                        <input name="password" class="form-control" type="password"
                               required
                               pattern="^(?=.*[A-Z])(?=.*\W).{8,}$"
                               title="Mật khẩu phải có ít nhất 8 ký tự, chứa ít nhất 1 chữ in hoa và 1 ký tự đặc biệt">
                    </div>

                    <div class="form-group col-md-6">
                        <label>Nhập lại mật khẩu</label>
                        <input name="confirmPassword" class="form-control" type="password"
                               required>
                    </div>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Đăng ký</button>
                </div> <!-- form-group// -->
                <%-- hiện thông báo lỗi--%>
                <c:if test="${not empty error}">
                    <div class="error-message">${error}</div>
                </c:if>

            </form>
        </article><!-- card-body.// -->
    </div> <!-- card .// -->
    <p class="text-center mt-4">Bạn đã có tài khoản? <a href="">Đăng nhập</a></p>
    <br><br>

    <!-- ============================ COMPONENT REGISTER  END.// ================================= -->
</section>
<%@ include file="/view/view-index/footer.jsp" %>
<script>
    function sanitizeUsername(input) {
        // Loại bỏ dấu tiếng Việt, khoảng trắng và ký tự đặc biệt
        input.value = input.value
            .normalize("NFD")                     // Tách dấu ra khỏi chữ
            .replace(/[\u0300-\u036f]/g, "")     // Xóa dấu
            .replace(/\s+/g, "")                 // Xóa khoảng trắng
            .replace(/[^a-zA-Z0-9_]/g, "");      // Xóa ký tự không hợp lệ
    }
</script>

<input type="text" name="username" class="form-control"
       placeholder="Tên Đăng Nhập" required
       oninput="sanitizeUsername(this)">

</body>
</html>


