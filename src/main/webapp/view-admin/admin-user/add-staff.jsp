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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/both-nav.css">
  <title>Quản lý đơn hàng</title>
</head>
<style>
  .form-container {
    background-color: #fff;
    border-radius: 8px;
    padding: 30px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    margin-top: 40px;
    margin-right: 40px;
    margin-left: 50px; /* ✅ Thêm dòng này */
  }

  .form-container h5 {
    font-size: 20px;
    font-weight: 600;
    margin-bottom: 25px;
    color: #2c3e50;
  }

  .form-group {
    display: flex;
    flex-direction: column;
    margin-bottom: 20px;
  }

  .form-group label {
    font-weight: 500;
    margin-bottom: 8px;
    color: #333;
  }

  .form-group input,
  .form-group select {
    padding: 10px 12px;
    font-size: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    outline: none;
    transition: border-color 0.3s;
  }

  .form-group input:focus,
  .form-group select:focus {
    border-color: #007bff;
  }

  .form-actions {
    display: flex;
    justify-content: flex-end;
    gap: 10px;
    margin-top: 25px;
  }

  .btn-custom {
    padding: 10px 20px;
    font-size: 14px;
    font-weight: 500;
    border-radius: 6px;
    cursor: pointer;
    border: none;
    transition: background-color 0.3s;
  }

  .btn-success {
    background-color: #28a745;
    color: #fff;
  }

  .btn-success:hover {
    background-color: #218838;
  }

  .btn-secondary {
    background-color: #6c757d;
    color: #fff;
  }

  .btn-secondary:hover {
    background-color: #5a6268;
  }

</style>
<body>

<!-- Side Nav -->
<%@ include file="../../assert/sidenav.jsp" %>

<div class="container">
  <!-- Navbar -->
  <%@ include file="../../assert/topnav.jsp" %>

  <!-- Table -->
  <section class="container form-container">
    <h5>Thêm Nhân Viên</h5>
    <form action="${pageContext.request.contextPath}/admin/add-staff" method="post">
      <div class="form-group">
        <label for="username">Tên Nhân Viên</label>
        <input type="text" id="username" name="username" required>
      </div>

      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>
      </div>

      <div class="form-group">
        <label for="phoneNumber">Số Điện Thoại</label>
        <input type="text" id="phoneNumber" name="phoneNumber" required>
      </div>

      <div class="form-group">
        <label for="password">Mật Khẩu</label>
        <input type="password" id="password" name="password" required>
      </div>

      <div class="form-group">
        <label for="idRole">Chức Vụ</label>
        <select id="idRole" name="idRole" required>
          <c:forEach var="role" items="${roles}">
            <option value="${role.idRole}"
                    <c:if test="${role.idRole == user.idRole}">selected</c:if>>
                ${role.role.name()}
            </option>
          </c:forEach>
        </select>
      </div>

      <div class="form-actions">
        <button type="submit" class="btn-custom btn-success">Thêm Nhân Viên</button>
        <a href="${pageContext.request.contextPath}/admin/staff-list" class="btn-custom btn-secondary">Quay Lại</a>
      </div>
    </form>
  </section>

</div>


</body>
</html>





