<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>403 - Không có quyền truy cập</title>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .error-container {
            text-align: center;
            background: #fff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
        }

        .error-code {
            font-size: 72px;
            font-weight: 800;
            color: #dc3545;
        }

        .error-message {
            font-size: 20px;
            color: #333;
            margin-top: 10px;
        }

        .btn-home {
            display: inline-block;
            margin-top: 25px;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border-radius: 6px;
            text-decoration: none;
            transition: background-color 0.3s;
        }

        .btn-home:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="error-container">
    <div class="error-code">403</div>
    <div class="error-message">Bạn không có quyền truy cập vào trang này.Chỉ có Quản Trị Viên mới có quyền truy cập</div>
    <a href="${pageContext.request.contextPath}/admin/staff-list" class="btn-home">Quay về </a>
</div>
</body>
</html>
