<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Xác nhận OTP</title>
</head>
<body>
<%@ include file="/view/view-index/header.jsp" %>

<section class="section-content padding-y">
    <div class="card mx-auto" style="max-width:520px; margin-top:40px;">
        <article class="card-body">
            <header class="mb-4"><h4 class="card-title">Xác nhận OTP</h4></header>

            <form action="${pageContext.request.contextPath}/enter-otp" method="post">
                <div class="form-group">
                    <label>Nhập mã OTP</label>
                    <input type="text" name="otp" class="form-control" placeholder="Nhập mã OTP" required>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Xác nhận</button>
                </div>

                <%-- Hiện thông báo lỗi nếu có --%>
                <c:if test="${not empty error}">
                    <div class="error-message">${error}</div>
                </c:if>

                <%-- Hiện thông báo thành công nếu có --%>
                <c:if test="${not empty success}">
                    <div class="success-message">${success}</div>
                </c:if>
            </form>

            <p class="text-center mt-4">Chưa nhận được mã? <a href="${pageContext.request.contextPath}/resend-otp">Gửi lại OTP</a></p>
        </article>
    </div>
</section>

<%@ include file="/view/view-index/footer.jsp" %>
</body>
</html>
