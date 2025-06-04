<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/both-nav.css">
    <title>Admin</title>
</head>
<body>

<!-- Side Nav -->
<nav class="navbar-left" id="navbar-left">
    <div class="sidebar-header">
        <div class="user-profile">
            <div class="sidebar-title">
                <a href="/admin" class="brand-logo-mini"><img src="<%= request.getContextPath() %>/images/logo.png"
                                                                   alt="logo" width="125"></a>
                <h2>Litte Whale</h2>
            </div>
            <img class="user-avatar" src="<%= request.getContextPath() %>/images/avatar-admin.png" alt="User Image"
                 width="100">
            <div class="user-info">
                <p class="user-name">AdminName</p>
                <p class="user-greeting">Chào mừng bạn trở lại</p>
            </div>
        </div>
    </div>
    <hr>
    <ul class="sidebar-menu">
        <li class="menu-item ">
            <a class="menu-link" href="${pageContext.request.contextPath}/view-admin/admin.jsp">
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
            <a class="menu-link" href="${pageContext.request.contextPath}/admin/manage-order">
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
            <a class="menu-link"
               href="${pageContext.request.contextPath}/view-admin/admin-category/manage-category.jsp">
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
    </ul>
</nav>

<div class="container">
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
                    <a href="${pageContext.request.contextPath}/view-admin/admin-product/add-product.jsp"
                       class="nav-link"><i class="fa-solid fa-plus"></i>Thêm
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

    <!-- Overview -->
    <section class="overview">
        <h1 class="header-overview">Bảng điều khiển</h1>
        <!-- Stats -->
        <div class="stats">
            <div class="stat-card">
                <div class="stat-card-icon"><i class="fa-solid fa-user"></i></div>
                Tổng khách hàng: <span>56 khách hàng</span>
            </div>
            <div class="stat-card">
                <div class="stat-card-icon"><i class="fa-solid fa-kaaba"></i></div>
                Tổng sản phẩm: <span>1850 sản phẩm</span>
            </div>
            <div class="stat-card">
                <div class="stat-card-icon"><i class="fa-solid fa-cart-shopping"></i></div>
                Tổng đơn hàng: <span>247 đơn hàng</span>
            </div>
            <div class="stat-card">
                <div class="stat-card-icon"><i class="fa-brands fa-sellsy"></i></div>
                Tổng sản phẩm bán được (6 tháng) <span>1000 sản phẩm</span>
            </div>
        </div>
    </section>

    <!-- Table -->
    <section class="tables">
        <!-- Order Table -->
        <div class="container-table">
            <!-- table 1 -->
            <h2>Tình trạng đơn hàng</h2>
            <table class="table" id="orderTable">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên khách hàng</th>
                    <th>Tổng tiền</th>
                    <th>Hình thức thanh toán</th>
                    <th>Ngày bắt đầu</th>
                    <th>Trạng thái</th>
                    <th>Tính năng</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>AL3947</td>
                    <td>Phạm Thị Ngọc</td>
                    <td>19.770.000 đ</td>
                    <td>Thẻ tín dụng</td>
                    <td>2024-11-01</td>
                    <td><span class="status-label pending">Chờ xử lý</span></td>
                    <td>
                        <button class="btn btn-trash"><i class="fas fa-trash-alt"></i></button>
                        <button class="btn btn-edit"><i class="fas fa-edit"></i></button>
                    </td>
                </tr>
                <tr>
                    <td>ER3835</td>
                    <td>Nguyễn Thị Mỹ Yến</td>
                    <td>16.770.000 đ</td>
                    <td>Thanh toán khi nhận hàng</td>
                    <td>2024-11-02</td>
                    <td><span class="status-label in-transit">Đang vận chuyển</span></td>
                    <td>
                        <button class="btn btn-trash"><i class="fas fa-trash-alt"></i></button>
                        <button class="btn btn-edit"><i class="fas fa-edit"></i></button>
                    </td>
                </tr>
                <tr>
                    <td>MD0837</td>
                    <td>Triệu Thanh Phú</td>
                    <td>9.400.000 đ</td>
                    <td>Ví điện tử</td>
                    <td>2024-11-03</td>
                    <td><span class="status-label completed">Đã hoàn thành</span></td>
                    <td>

                    </td>   <button class="btn btn-trash"><i class="fas fa-trash-alt"></i></button>
                    <button class="btn btn-edit"><i class="fas fa-edit"></i></button>
                </tr>
                <tr>
                    <td>MT9835</td>
                    <td>Đặng Hoàng Phúc</td>
                    <td>40.650.000 đ</td>
                    <td>Chuyển khoản ngân hàng</td>
                    <td>2024-11-04</td>
                    <td><span class="status-label cancelled">Đã hủy</span></td>
                    <td>
                        <button class="btn btn-trash"><i class="fas fa-trash-alt"></i></button>
                        <button class="btn btn-edit"><i class="fas fa-edit"></i></button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="container-table">
            <!-- table2 2 -->
            <h2>Khách hàng mới</h2>
            <table class="table" id="userTable">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Tài Khoản</th>
                    <th>Họ và Tên</th>
                    <th>Email</th>
                    <th>Số Điện Thoại</th>
                    <th>Địa Chỉ</th>
                    <th>Tổng Tiền Mua</th>
                    <th>Tính Năng</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>#CD12837</td>
                    <td>nguyenA</td>
                    <td>Nguyễn Văn A</td>
                    <td>nguyenvana@example.com</td>
                    <td>0901234567</td>
                    <td>123 Đường ABC, Quận 1, TP.HCM</td>
                    <td>5,000,000 VND</td>

                    <td>
                        <button class="btn btn-trash"><i class="fas fa-trash-alt"></i></button>
                        <button class="btn btn-edit"><i class="fas fa-edit"></i></button>
                    </td>
                </tr>
                <tr>
                    <td>#CD12837</td>
                    <td>nguyenA</td>
                    <td>Nguyễn Văn A</td>
                    <td>nguyenvana@example.com</td>
                    <td>0901234567</td>
                    <td>123 Đường ABC, Quận 1, TP.HCM</td>
                    <td>5,000,000 VND</td>

                    <td>
                        <button class="btn btn-trash"><i class="fas fa-trash-alt"></i></button>
                        <button class="btn btn-edit"><i class="fas fa-edit"></i></button>
                    </td>
                </tr>
                <tr>
                    <td>#CD12837</td>
                    <td>nguyenA</td>
                    <td>Nguyễn Văn A</td>
                    <td>nguyenvana@example.com</td>
                    <td>0901234567</td>
                    <td>123 Đường ABC, Quận 1, TP.HCM</td>
                    <td>5,000,000 VND</td>

                    <td>
                        <button class="btn btn-trash"><i class="fas fa-trash-alt"></i></button>
                        <button class="btn btn-edit"><i class="fas fa-edit"></i></button>
                    </td>
                </tr>
                <tr>
                    <td>#CD12837</td>
                    <td>nguyenA</td>
                    <td>Nguyễn Văn A</td>
                    <td>nguyenvana@example.com</td>
                    <td>0901234567</td>
                    <td>123 Đường ABC, Quận 1, TP.HCM</td>
                    <td>5,000,000 VND</td>

                    <td>
                        <button class="btn btn-trash"><i class="fas fa-trash-alt"></i></button>
                        <button class="btn btn-edit"><i class="fas fa-edit"></i></button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </section>

    <!-- Chart -->
    <section>
        <div class="container-chart">
            <h2>Biểu đồ thống kê</h2>
            <div class="charts">
                <div class="chart" id="barChartDemo">
                    <h3>Thống kê 6 tháng doanh thu</h3>
                    <canvas id="barChart"></canvas>
                </div>
                <div class="chart" id="pieChartDemo">
                    <h3>Thống kê 6 sản phẩm bán chạy</h3>
                    <canvas id="pieChart"></canvas>
                </div>
            </div>
        </div>
    </section>


</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="js/admin.js"></script>
<script>
    $(document).ready(function () {
        $('#orderTable').DataTable({
            "paging": true,         // Bật phân trang
            "searching": true,      // Bật tìm kiếm
            "ordering": true,       // Bật sắp xếp cột
            "info": true,           // Hiển thị thông tin tổng số dòng
            "language": {           // Tùy chỉnh ngôn ngữ
                "search": "Tìm kiếm:",
                "lengthMenu": "Hiển thị _MENU_ dòng",
                "info": "Hiển thị _START_ đến _END_ của _TOTAL_",
                "paginate": {
                    "first": "Đầu",
                    "last": "Cuối",
                    "next": "Tiếp",
                    "previous": "Trước"
                }
            }
        });
    });

    $(document).ready(function () {
        $('#userTable').DataTable({
            "paging": true,         // Bật phân trang
            "searching": true,      // Bật tìm kiếm
            "ordering": true,       // Bật sắp xếp cột
            "info": true,           // Hiển thị thông tin tổng số dòng
            "language": {           // Tùy chỉnh ngôn ngữ
                "search": "Tìm kiếm:",
                "lengthMenu": "Hiển thị _MENU_ dòng",
                "info": "Hiển thị _START_ đến _END_ của _TOTAL_",
                "paginate": {
                    "first": "Đầu",
                    "last": "Cuối",
                    "next": "Tiếp",
                    "previous": "Trước"
                }
            }
        });
    });
</script>
<script>
    $(document).ready(function () {
        // Dữ liệu cho Bar Chart - Doanh thu trong 6 tháng
        var barData = {
            labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6"],
            datasets: [{
                label: 'Doanh thu',
                data: [150, 200, 250, 300, 220, 280],
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        };
        var barOptions = {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        };
        if ($("#barChart").length) {
            var barChartCanvas = $("#barChart").get(0).getContext("2d");
            var barChart = new Chart(barChartCanvas, {
                type: 'bar',
                data: barData,
                options: barOptions
            });
        }

        // Dữ liệu cho Pie Chart - Sản phẩm bán chạy
        var pieData = {
            labels: ["Sản phẩm A", "Sản phẩm B", "Sản phẩm C", "Sản phẩm D", "Sản phẩm E"],
            datasets: [{
                data: [20, 30, 15, 25, 10], // Chỉ giữ lại 5 giá trị
                backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                    'rgba(255, 206, 86, 0.5)',
                    'rgba(75, 192, 192, 0.5)',
                    'rgba(153, 102, 255, 0.5)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)'
                ],
                borderWidth: 1
            }]
        };
        const pieOptions = {
            responsive: true,
            animation: {
                animateScale: true,
                animateRotate: true
            }
        };
        if ($("#pieChart").length) {
            const pieChartCanvas = $("#pieChart").get(0).getContext("2d");
            const pieChart = new Chart(pieChartCanvas, {
                type: 'pie',
                data: pieData,
                options: pieOptions
            });
        }
    });
</script>
</body>
</html>
