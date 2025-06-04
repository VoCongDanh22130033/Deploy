<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
<%@ include file="/view/view-index/header.jsp" %>
<section class="section-pagetop bg">
    <div class="container">
        <h2 class="title-page">Tất Cả Sản Phẩm</h2>

    </div>
    <!-- container //  -->
</section>
<!-- ========================= SECTION INTRO END// ========================= -->
<!-- ========================= SECTION CONTENT ========================= -->
<section class="section-content padding-y">
    <div class="container">
        <div class="row">
            <aside class="col-md-3">
                <div class="card">
                    <article class="filter-group">
                        <header class="card-header">
                            <a href="#" data-toggle="collapse" data-target="#collapse_1"
                               aria-expanded="true" class=""> <i
                                    class="icon-control fa fa-chevron-down"></i>
                                <h6 class="title">Thể Loại</h6>
                            </a>
                        </header>
                        <div class="filter-content collapse show" id="collapse_1"
                             style="">
                            <div class="card-body">
                                <ul class="list-menu">
                                    <li><a
                                            href="${pageContext.request.contextPath}/all-boy-or-girl?boy_or_girl=1&page=1&pageSize=9">Bé
                                        Trai</a></li>
                                    <li><a
                                            href="${pageContext.request.contextPath}/fashion?idCategory=1">--Áo</a></li>
                                    <li><a
                                            href="${pageContext.request.contextPath}/fashion?idCategory=2&boy_or_girl=1">--Quần</a>
                                    </li>
                                    <li><a
                                            href="${pageContext.request.contextPath}/fashion?idCategory=3&boy_or_girl=1">--Giày,Dép</a>
                                    </li>
                                    <li><a
                                            href="${pageContext.request.contextPath}/fashion?idCategory=4&boy_or_girl=1">--Đồ
                                        Bộ</a></li>
                                    <li><a
                                            href="${pageContext.request.contextPath}/all-boy-or-girl?boy_or_girl=2&page=1&pageSize=9">Bé
                                        Gái</a></li>
                                    <li><a
                                            href="${pageContext.request.contextPath}/fashion?idCategory=5&boy_or_girl=2">--Áo
                                    </a></li>
                                    <li><a
                                            href="${pageContext.request.contextPath}/fashion?idCategory=6&boy_or_girl=2">--Quần
                                    </a></li>
                                    <li><a
                                            href="${pageContext.request.contextPath}/fashion?idCategory=7&boy_or_girl=2">--Váy
                                    </a></li>
                                    <li><a
                                            href="${pageContext.request.contextPath}/fashion?idCategory=8&boy_or_girl=2">--Đồ
                                        Bộ </a></li>
                                </ul>
                            </div>
                            <!-- card-body.// -->
                        </div>
                    </article>
                </div>
                <!-- card.// -->
            </aside>
            <!-- col.// -->
            <main class="col-md-9">
                <header class="border-bottom mb-4 pb-3">
                    <div class="form-inline">
                        <span class="mr-md-auto">32 Sản Phẩm </span>
                    </div>
                </header>
                <div class="row">
                    <c:forEach var="product" items="${products}" varStatus="status">
                        <c:if test="${status.index < 151}">
                            <div class="col-md-4 mb-4">
                                <figure class="card card-product-grid">
                                    <div class="img-wrap">
                                        <c:set var="imageUrl"
                                               value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>
                                        <img src="${imageUrl}" alt="${product.title}">
                                    </div>
                                    <figcaption class="info-wrap">
                                        <div class="fix-height">
                                            <a href="#" style="font-size: 13px" class="title">${product.title}</a>
                                            <div
                                                    class="d-flex align-items-center justify-content-between mb-3">
                                                <!-- Giá sản phẩm -->
                                                <span class="price text-danger fw-bold"> <fmt:formatNumber
                                                        value="${product.price - (product.price * product.discount.discountAmount / 100)}" pattern="#,##0 đ"/>
                													</span>
                                                <!-- Giá gốc -->
                                                <small style="text-decoration: line-through; color: gray;">
                                                    <fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/>
                                                </small>

                                                <!-- Nút xem chi tiết -->
                                                <button
                                                        class="btn btn-outline-secondary p-1 d-flex align-items-center"
                                                        data-bs-toggle="tooltip" title="Xem chi tiết">
                                                    <i class="fa-solid fa-eye fs-6"></i>
                                                </button>
                                                <!-- Form thêm vào giỏ hàng -->
                                                <form action="${pageContext.request.contextPath}/cart"
                                                      method="post" class="mb-0">
                                                    <input type="hidden" name="action" value="add"/> <input
                                                        type="hidden" name="idProduct"
                                                        value="${product.idProduct}"/>
                                                    <button
                                                            class="btn btn-primary p-1 d-flex align-items-center"
                                                            type="submit" data-bs-toggle="tooltip"
                                                            title="Thêm vào giỏ hàng">
                                                        <i class="fa fa-shopping-cart fs-6"></i>
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </figcaption>
                                </figure>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>

                <nav class="mt-4" aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item"><a class="page-link"
                                                     href="?boy_or_girl=${boy_or_girl}&page=${currentPage - 1}">Lùi</a>
                            </li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link"
                                   href="?boy_or_girl=${boy_or_girl}&page=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item"><a class="page-link"
                                                     href="?boy_or_girl=${boy_or_girl}&page=${currentPage + 1}">Tiến</a>
                            </li>
                        </c:if>
                    </ul>

                </nav>
            </main>
            <!-- col.// -->
        </div>
    </div>
    <!-- container .//  -->
</section>
<%@ include file="/view/view-index/footer.jsp" %>
</body>
</html>