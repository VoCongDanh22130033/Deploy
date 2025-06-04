<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Đặt Lại Mật Khẩu</title>
</head>
<body>
<%@ include file="/view/view-index/header.jsp" %>

<section class="section-content padding-y">
    <div class="card mx-auto" style="max-width:520px; margin-top:40px;">
        <article class="card-body">
            <header class="mb-4"><h4 class="card-title">Đặt Lại Mật Khẩu</h4></header>

            <form action="${pageContext.request.contextPath}/reset-password" method="post">
                <div class="form-group">
                    <label>Mật Khẩu Mới</label>
                    <input type="password" name="password" class="form-control" placeholder="Nhập mật khẩu mới" required>
                </div>

                <div class="form-group">
                    <label>Xác Nhận Mật Khẩu</label>
                    <input type="password" name="confPassword" class="form-control" placeholder="Xác nhận mật khẩu mới" required>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Xác nhận </button>
                </div>

                <%-- Hiện thông báo lỗi --%>
                <c:if test="${not empty error}">
                    <div class="error-message" style="color: red;">${error}</div>
                </c:if>

                <%-- Hiện thông báo thành công --%>
                <c:if test="${not empty success}">
                    <div class="success-message" style="color: green;">${success}</div>
                </c:if>
                <c:if test="${not empty status}">
                    <c:choose>
                        <c:when test="${status == 'invalidInput'}">
                            <div class="alert alert-danger">Mật khẩu không hợp lệ hoặc không trùng khớp.</div>
                        </c:when>
                        <c:when test="${status == 'resetSuccess'}">
                            <div class="alert alert-success">Mật khẩu đã được thay đổi thành công!</div>
                        </c:when>
                        <c:when test="${status == 'resetFailed'}">
                            <div class="alert alert-danger">Đổi mật khẩu thất bại. Vui lòng thử lại!</div>
                        </c:when>
                    </c:choose>
                </c:if>

            </form>

        </article>
    </div>

    <p class="text-center mt-4">Quay lại <a href="${pageContext.request.contextPath}/sign">Đăng nhập</a></p>
</section>
<%@ include file="/view/view-index/footer.jsp" %>
</body>
</html>
