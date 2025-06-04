
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/card-product.css">
</head>
<body>

<!-- ========================= SECTION MAIN ========================= -->
<section class="section-intro padding-y-sm">
    <div class="container">
        <div class="intro-banner-wrap">
            <img src="https://file.hstatic.net/1000290074/file/artboard_1.jpg" class="img-fluid rounded">
        </div>
    </div>
</section>
<!-- ========================= SECTION MAIN END// ========================= -->

<!-- ========================= SAN PHAM HOT MUA NHIEU  ========================= -->
<section class="section-name padding-y-sm my-4">
    <div class="container">
        <header class="section-heading">
            <h3 class="section-title">Các Sản Phẩm Hot</h3>
        </header>

        <div class="best-seller" id="best-seller">
            <div class="row">
                <c:forEach var="product" items="${products}" begin="1" end="8">
                    <div class="col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="${pageContext.request.contextPath}/detail?pid=${product.idProduct}">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price" style="text-decoration: line-through;">
                                            <fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/>
                                        </small>
                                        <p class="dis-price">
                                            <fmt:formatNumber value="${product.finalPrice}" pattern="#,##0 đ"/>
                                        </p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa-solid fa-eye"></i></a>
                                        <form action="${pageContext.request.contextPath}/cart" method="post">
                                            <input type="hidden" name="action" value="add"/>
                                            <input type="hidden" name="idProduct" value="${product.idProduct}"/>
                                            <button class="btn-cart" type="submit">
                                                <i class="fa fa-shopping-cart"></i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="load-more">
            <button>Xem thêm</button>
        </div>
    </div>
</section>

<!-- ========================= SAN PHAM GIAM GIA  ========================= -->
<section class="section-name padding-y-sm my-4">
    <div class="container">
        <header class="section-heading">
<%--            <a href="${pageContext.request.contextPath}/view/view-product/store.jsp"--%>
<%--               class="btn btn-outline-primary float-right">Xem tất cả</a>--%>
            <h3 class="section-title">Các Sản Phẩm Giảm Giá</h3>
        </header>

        <div class="disount-products" id="disount-products">
            <div class="row" id="discount">
                <c:forEach var="product" items="${productsTop8HasDisount}">
                    <div class="product col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="${pageContext.request.contextPath}/detail?pid=${product.idProduct}">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price" style="text-decoration: line-through;">
                                            <fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/>
                                        </small>
                                        <p class="dis-price">
                                            <fmt:formatNumber value="${product.finalPrice}" pattern="#,##0 đ"/>
                                        </p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa-solid fa-eye"></i></a>
                                        <form action="${pageContext.request.contextPath}/cart" method="post">
                                            <input type="hidden" name="action" value="add"/>
                                            <input type="hidden" name="idProduct" value="${product.idProduct}"/>
                                            <button class="btn-cart" type="submit">
                                                <i class="fa fa-shopping-cart"></i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="load-more">
            <button class="btn-load" data-type="discount">Xem thêm</button>
        </div>
    </div>
</section>

<!-- ========================= SAN PHAM CUA BE TRAI  ========================= -->
<section class="section-name padding-y-sm my-4">
    <div class="container">
        <header class="section-heading">
            <a href="${pageContext.request.contextPath}/all-boy-or-girl?boy_or_girl=1&page=1&pageSize=9" class="btn btn-outline-primary float-right">Xem tất cả</a>
            <h3 class="section-title">Thời Trang Bé Trai</h3>
        </header>

        <div class="nav-category" data-category="boy">
            <p class="tab" data-tab="ao-boy">Áo</p>
            <p class="tab" data-tab="quan-boy">Quần</p>
            <p class="tab" data-tab="giay-boy">Giày</p>
            <p class="tab" data-tab="do-bo-boy">Đồ bộ</p>
        </div>

        <c:forEach var="category" items="${productsByCategory1}">
            <div class="products-category" id="${category.key}" style="display: ${category.key == 'ao-boy' ? 'flex' : 'none'};">
                <div class="row" id="boy">
                    <c:forEach var="product" items="${category.value}">
                        <div class="product col-md-3 gap-3 mb-5">
                            <div class="product-card">
                                <div class="badge-custome">Hot</div>

                                <c:set var="imageUrl" value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                                <div class="product-tumb">
                                    <img src="${imageUrl}" alt="${product.title}">
                                </div>
                                <div class="product-details">
                                    <span class="product-catagory">${product.category.name}</span>
                                    <h4>
                                        <a href="${pageContext.request.contextPath}/detail?pid=${product.idProduct}">${product.title}</a>
                                    </h4>
                                    <div class="product-bottom-details">
                                        <div class="product-price">
                                            <c:choose>
                                                <c:when test="${product.discount != null && product.discount.discountAmount>0}">
                                                    <small class="ori-price" style="text-decoration: line-through;">
                                                        <fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/>
                                                    </small>
                                                    <p class="dis-price">
                                                        <fmt:formatNumber value="${product.finalPrice}" pattern="#,##0 đ"/>
                                                    </p>
                                                </c:when>
                                                <c:otherwise>
                                                    <small class="ori-price" style="color: #ff9800; font-size: 16px;">
                                                        <fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/>
                                                    </small>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>

                                        <div class="product-links">
                                            <a href="#"><i class="fa-solid fa-eye"></i></a>
                                            <form action="${pageContext.request.contextPath}/cart" method="post">
                                                <input type="hidden" name="action" value="add"/>
                                                <input type="hidden" name="idProduct" value="${product.idProduct}"/>
                                                <button class="btn-cart" type="submit">
                                                    <i class="fa fa-shopping-cart"></i>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>

        <div class="load-more">
            <button class="btn-load" data-type="boy">Xem thêm</button>
        </div>
    </div>
</section>

<!-- ========================= SAN PHAM CUA BE GAI  ========================= -->
<section class="section-name padding-y-sm my-4">
    <div class="container">
        <header class="section-heading">
            <a href="${pageContext.request.contextPath}/all-boy-or-girl?boy_or_girl=2&page=1&pageSize=9"
               class="btn btn-outline-primary float-right">Xem tất cả</a>
            <h3 class="section-title">Thời Trang Bé Gái</h3>
        </header>

        <div class="nav-category" data-category="girl">
            <p class="tab" data-tab="ao-girl">Áo</p>
            <p class="tab" data-tab="quan-girl">Quần</p>
            <p class="tab" data-tab="vay-girl">Váy</p>
            <p class="tab" data-tab="do-bo-girl">Đồ bộ</p>
        </div>

        <c:forEach var="category" items="${productsByCategory2}">
            <div class="products-category" id="${category.key}" style="display: ${category.key == 'ao-girl' ? 'flex' : 'none'};">
                <div class="row" id="girl">
                    <c:forEach var="product" items="${category.value}">
                        <div class="product col-md-3 gap-3 mb-5">
                            <div class="product-card">
                                <div class="badge-custome">Hot</div>

                                <c:set var="imageUrl" value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                                <div class="product-tumb">
                                    <img src="${imageUrl}" alt="${product.title}">
                                </div>
                                <div class="product-details">
                                    <span class="product-catagory">${product.category.name}</span>
                                    <h4>
                                        <a href="${pageContext.request.contextPath}/detail?pid=${product.idProduct}">${product.title}</a>
                                    </h4>
                                    <div class="product-bottom-details">
                                        <div class="product-price">
                                            <c:choose>
                                                <c:when test="${product.discount != null && product.discount.discountAmount>0}">
                                                    <small class="ori-price" style="text-decoration: line-through;">
                                                        <fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/>
                                                    </small>
                                                    <p class="dis-price">
                                                        <fmt:formatNumber value="${product.finalPrice}" pattern="#,##0 đ"/>
                                                    </p>
                                                </c:when>
                                                <c:otherwise>
                                                    <small class="ori-price">
                                                        <fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/>
                                                    </small>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="product-links">
                                            <a href="#"><i class="fa-solid fa-eye"></i></a>
                                            <form action="${pageContext.request.contextPath}/cart" method="post">
                                                <input type="hidden" name="action" value="add"/>
                                                <input type="hidden" name="idProduct" value="${product.idProduct}"/>
                                                <button class="btn-cart" type="submit">
                                                    <i class="fa fa-shopping-cart"></i>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>

        <div class="load-more">
            <button class="btn-load" data-type="girl">Xem thêm</button>
        </div>
    </div>
</section>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>


</body>
</html>