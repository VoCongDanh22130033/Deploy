<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.0.0.min.js" type="text/javascript"></script>
    <!-- Bootstrap4 files-->
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    <link href="<%= request.getContextPath() %>/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <!-- Font awesome 5 -->
    <link href="${pageContext.request.contextPath}/fonts/fontawesome/css/all.min.css" type="text/css"
          rel="stylesheet">
    <!-- custom style -->
    <link href="${pageContext.request.contextPath}/css/ui.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet"
          media="only screen and (max-width: 1200px)"/>
    <!-- custom javascript -->
    <script src="${pageContext.request.contextPath}/js/script.js" type="text/javascript"></script>
    <script type="text/javascript">
        /// some script
        // jquery ready start
        $(document).ready(function () {
            // jQuery code
        });
        // jquery end
    </script>
</head>

<body>
<section class="header-main ">

    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg col-sm col-md col-6 flex-grow-0">
                <div class="category-wrap dropdown d-inline-block float-right">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bars"></i> Kiểm Tra Đơn Hàng
                    </button>
                </div>  <!-- category-wrap.// -->
            </div> <!-- col.// -->
            <a href="" class="btn btn-outline-primary">Liên Hệ</a>
            <div class="col-lg  col-md-6 col-sm-12 col">
                <form action="#" class="search">
                    <div class="input-group w-100">
                        <input type="text" class="form-control" style="width:60%;" placeholder="Search">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="submit">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </form> <!-- search-wrap .end// -->
            </div> <!-- col.// -->
            <div class="col-lg-3 col-sm-6 col-8 order-2 order-lg-3">
                <div class="d-flex justify-content-end mb-3 mb-lg-0">

                    <div class="widget-header">
                        <small class="title text-muted">
                            ${not empty facebookName ? facebookName
                                    : not empty sessionScope.user ? sessionScope.user.fullName
                                    : not empty sessionScope.fullname ? sessionScope.fullname
                                    : "Khách!"}
                        </small>
                        <div>
                            <c:choose>
                                <c:when test="${not empty facebookName or not empty sessionScope.user or not empty sessionScope.fullname}">
                                    <a href="${pageContext.request.contextPath}/profile">Xem Tài Khoản</a>
                                    <span class="dark-transp"> | </span>
                                    <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/sign">Đăng Nhập</a>
                                    <span class="dark-transp"> | </span>
                                    <a href="${pageContext.request.contextPath}/register">Đăng Ký</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>


                    <a href="${pageContext.request.contextPath}/view/view-order/cart.jsp"
                       class="widget-header pl-3 ml-3">
                        <div class="icon icon-sm rounded-circle border"><i class="fa fa-shopping-cart"></i></div>
                        <span class="badge badge-pill badge-danger notify">0</span>
                    </a>
                </div> <!-- widgets-wrap.// -->
            </div> <!-- col.// -->
        </div> <!-- row.// -->
    </div> <!-- container.// -->
</section> <!-- header-main .// -->

<!-- section-header.// -->
<header class="section-header">
    <section class="header-main border-bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-2 col-md-3 col-6">
                    <a href="${pageContext.request.contextPath}/home" class="brand-wrap"> <img class="logo"
                                                                                               src="${pageContext.request.contextPath}/images/logo.png">
                    </a>
                    <!-- brand-wrap.// -->
                </div>
                <div class="col-lg col-sm col-md col-6 flex-grow-0"></div>
                <!-- col.// -->

                <div class="col-lg  col-md-6 col-sm-12 col">
                    <nav class="navbar navbar-expand-lg navbar-light bg-while">
                        <div class="container">
                            <!-- Nút Toggle cho Mobile -->
                            <button class="navbar-toggler" type="button"
                                    data-toggle="collapse" data-target="#navbarNav"
                                    aria-controls="navbarNav" aria-expanded="false"
                                    aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>

                            <!-- Nội dung Navbar -->
                            <div class=" " id="navbarNav">
                                <ul class="navbar-nav mx-auto">
                                    <li class="nav-item dropdown">
                                        <a
                                                class="nav-link text-dark"
                                                href="${pageContext.request.contextPath}/home"
                                                role="button" data-toggle="dropdown" aria-haspopup="true"
                                                aria-expanded="false" style="font-size: 20px;"> Trang Chủ
                                        </a>
                                    </li>
                                    <!-- Bé Trai -->
                                    <li class="nav-item dropdown"><a
                                            class="nav-link  dropdown-toggle text-dark" href="#"
                                            id="" role="button" data-toggle="dropdown"
                                            aria-haspopup="true" aria-expanded="false" style="font-size: 20px;"> Bé
                                        Trai </a>
                                        <div class="dropdown-menu" aria-labelledby="boyDropdown">
                                            <a class="dropdown-item"
                                               href="${pageContext.request.contextPath}/fashion?idCategory=3">Giày
                                                dép</a>
                                            <a class="dropdown-item"
                                               href="${pageContext.request.contextPath}/fashion?idCategory=1">Áo</a>
                                            <a class="dropdown-item"
                                               href="${pageContext.request.contextPath}/fashion?idCategory=2">Quần</a>
                                            <a class="dropdown-item"
                                               href="${pageContext.request.contextPath}/fashion?idCategory=4">Đồ Bộ</a>
                                        </div>
                                    </li>
                                    <!-- Bé Gái -->
                                    <li class="nav-item dropdown"><a
                                            class="nav-link dropdown-toggle text-dark" href="#"
                                            id="girlDropdown" role="button" data-toggle="dropdown"
                                            aria-haspopup="true" aria-expanded="false" style="font-size: 20px;"> Bé
                                        Gái </a>
                                        <div class="dropdown-menu" aria-labelledby="girlDropdown">
                                            <a class="dropdown-item"
                                               href="${pageContext.request.contextPath}/fashion?idCategory=5&boy_or_girl=2">Áo</a>
                                            <a
                                                    class="dropdown-item"
                                                    href="${pageContext.request.contextPath}/fashion?idCategory=6&boy_or_girl=2">Quần</a>
                                            <a
                                                    class="dropdown-item"
                                                    href="${pageContext.request.contextPath}/fashion?idCategory=7&boy_or_girl=2">Chân
                                                Váy</a>
                                            <a class="dropdown-item"
                                               href="${pageContext.request.contextPath}/fashion?idCategory=8&boy_or_girl=2">Đồ
                                                Bộ</a>
                                        </div>
                                    </li>
                                    <%--                                    <li class="nav-item dropdown"><a--%>
                                    <%--                                            class="nav-link dropdown-toggle text-dark" href="#"--%>
                                    <%--                                            id="boyDropdown" role="button" data-toggle="dropdown"--%>
                                    <%--                                            aria-haspopup="true" aria-expanded="false" style="font-size: 20px;">--%>
                                    <%--                                        Giày </a>--%>
                                    <%--                                        <div class="dropdown-menu" aria-labelledby="boyDropdown">--%>
                                    <%--                                            <a class="dropdown-item" href="#">Giày dép</a> <a--%>
                                    <%--                                                class="dropdown-item" href="#">Áo</a> <a--%>
                                    <%--                                                class="dropdown-item" href="#">Quần</a>--%>
                                    <%--                                        </div>--%>
                                    <%--                                    </li>--%>
                                    <%--                                    <li class="nav-item dropdown"><a--%>
                                    <%--                                            class="nav-link dropdown-toggle text-dark" href="#"--%>
                                    <%--                                            role="button" data-toggle="dropdown"--%>
                                    <%--                                            aria-haspopup="true" aria-expanded="false" style="font-size: 20px;">--%>
                                    <%--                                        Dép </a>--%>
                                    <%--                                        <div class="dropdown-menu" aria-labelledby="boyDropdown">--%>
                                    <%--                                            <a class="dropdown-item" href="#">Giày dép</a> <a--%>
                                    <%--                                                class="dropdown-item" href="#">Áo</a> <a--%>
                                    <%--                                                class="dropdown-item" href="#">Quần</a>--%>
                                    <%--                                        </div>--%>
                                    <%--                                    </li>--%>
                                </ul>
                            </div>

                        </div>
                        <!-- col.// -->
                    </nav>
                </div>
                <!-- col.// -->

                <!-- col.// -->
            </div>
            <!-- row.// -->
        </div>
        <!-- container.// -->
    </section>
    <!-- header-main .// -->
</header>
<!-- section-header.// -->

<body>