
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quên Mật Khẩu</title>
</head>
<body>
<%@ include file="/view/view-index/header.jsp" %>
<section class="section-content padding-y">
    <div class="card mx-auto" style="max-width:520px; margin-top:40px;">
        <article class="card-body">
            <%
                String loggedInEmail = (String) session.getAttribute("email"); // Lấy email từ session
            %>
            <!-- Nếu đã đăng nhập, đổi tiêu đề thành "Đổi Mật Khẩu" -->
            <header class="mb-4">
                <h4 class="card-title">
                    <%= (loggedInEmail != null) ? "Đổi Mật Khẩu" : "Quên Mật Khẩu" %>
                </h4>
            </header>
            <form action="${pageContext.request.contextPath}/forgot-password" method="post">
                <div class="form-group">
                    <label>Email của bạn</label>
                    <input type="email" name="email" class="form-control"
                           value="<%= (loggedInEmail != null) ? loggedInEmail : "" %>"
                           placeholder="Nhập email"
                        <%= (loggedInEmail != null) ? "readonly" : "" %> required>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Gửi Yêu Cầu</button>
                </div>
                <!-- Hiển thị thông báo lỗi hoặc thành công -->
                <c:if test="${not empty error}">
                    <div class="error-message" style="color: red;">${error}</div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="success-message" style="color: green;">${success}</div>
                </c:if>
            </form>
        </article>
    </div>
    <% if (loggedInEmail == null) { %>
    <p class="text-center mt-4">Bạn nhớ mật khẩu? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
    <% } %>
    <br><br>
</section>
<%@ include file="/view/view-index/footer.jsp" %>
</body>
</html>
