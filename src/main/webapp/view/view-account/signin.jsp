
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% Boolean isLocked = (Boolean) request.getAttribute("isLocked"); %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
<%@ include file="/view/view-index/header.jsp" %>

<section class="section-conten padding-y" style="min-height:84vh">
    <div class="card mx-auto" style="max-width: 380px; margin-top:100px;">
        <div class="card-body">
            <h4 class="card-title mb-4 text-center">Đăng Nhập</h4>

            <form action="${pageContext.request.contextPath}/sign" method="post">
                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="nhập vào tài khoản"
                        <%= (isLocked != null && isLocked) ? "disabled" : "" %>>
                </div>

                <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="Mật Khẩu"
                        <%= (isLocked != null && isLocked) ? "disabled" : "" %>>
                </div>

                <div class="form-group">
                    <a href="<%= request.getContextPath() %>/view/view-account/forgot-password.jsp" class="float-right">Quên Mật Khẩu?</a>
                </div>

                <label>Mã xác nhận:</label><br>
                <div style="font-weight: bold; font-size: 20px; letter-spacing: 2px; color: blue;">
                    ${captchaText}
                </div>
                <input type="text" name="captchaInput" required
                    <%= (isLocked != null && isLocked) ? "disabled" : "" %>>
                <br><br>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"
                            <%= (isLocked != null && isLocked) ? "disabled" : "" %>>
                        Đăng Nhập
                    </button>
                </div>
            </form>

            <div class="d-flex justify-content-between">
                <!-- Google Login -->
                <div class="form-group" style="width: 48%; margin-right: 10px;">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/web/login&response_type=code&client_id=783371694466-v4tsjt4ch1ulqtbdinv02of0q3t4tpm8.apps.googleusercontent.com&approval_prompt=force"
                       class="btn btn-danger btn-block"
                       style="height: 33px; padding: 5px 10px;"
                            <%= (isLocked != null && isLocked) ? "onclick=\"return false;\" style='pointer-events: none; opacity: 0.5;'" : "" %>>
                        <i class="fa-brands fa-google"></i> Google
                    </a>
                </div>

                <!-- Facebook Login -->
                <div class="form-group" style="width: 48%;">
                    <a href="https://www.facebook.com/v17.0/dialog/oauth?client_id=646739511162892&redirect_uri=http://localhost:8080/web/login-fb&response_type=code&scope=email,public_profile"
                       class="btn btn-primary btn-block"
                       style="height: 33px; padding: 5px 10px;"
                            <%= (isLocked != null && isLocked) ? "onclick=\"return false;\" style='pointer-events: none; opacity: 0.5;'" : "" %>>
                        <i class="fa-brands fa-facebook"></i> Facebook
                    </a>
                </div>
            </div>

            <!-- Hiển thị lỗi nếu có -->
            <div class="form-group">
                <c:if test="${not empty error}">
                    <p style="color: red; font-weight: bold;">${error}</p>
                </c:if>

                <c:if test="${not empty lockMessage}">
                    <p style="color: red; font-weight: bold;">${lockMessage}</p>
                </c:if>
            </div>
        </div>
    </div>

    <p class="text-center mt-4">Không Có Tài Khoản ?
        <a href="<%= request.getContextPath() %>/view/view-account/register.jsp">Đăng Ký</a>
    </p>
    <br><br>
</section>

<%@ include file="/view/view-index/footer.jsp" %>
</body>
</html>
