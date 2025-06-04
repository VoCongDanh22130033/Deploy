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
<section class="section-content padding-y bg">
    <div class="container">

        <!-- ============================ COMPONENT 1 ================================= -->
        <div class="card">
            <div class="row no-gutters">
                <aside class="col-md-6">
                    <article class="gallery-wrap">
                        <div class="img-big-wrap">
                            <a href="${product.productImages[0].imageUrl}">
                                <img src="${product.productImages[0].imageUrl}" alt="${product.title}">
                            </a>
                        </div> <!-- img-big-wrap.// -->

                    </article> <!-- gallery-wrap .end// -->
                </aside>
                <main class="col-md-6 border-left">
                    <article class="content-body">

                        <h2 class="title">${product.title}</h2>

                        <div class="mb-3">
                            <var class="price h4">
                                <c:choose>
                                    <c:when test="${not empty product.discount}">
                                        <span style="text-decoration: line-through; color: gray;">${product.price}₫</span>
                                        <span class="text-danger ml-2">${product.price - (product.price * product.discount.discountAmount / 100)}₫</span>
                                    </c:when>
                                    <c:otherwise>
                                        ${product.price}₫
                                    </c:otherwise>
                                </c:choose>
                            </var>
                        </div>

                        <p>${product.description}</p>


                        <hr>
                        <div class="row">
                            <div class="item-option-select">
                                <h6>Choose Color</h6>
                                <div class="btn-group btn-group-sm btn-group-toggle" data-toggle="buttons">
                                    <label class="btn btn-light">
                                        <input type="radio" name="radio_color"> Silver
                                    </label>
                                    <label class="btn btn-light">
                                        <input type="radio" name="radio_color"> Gray
                                    </label>
                                    <label class="btn btn-light active">
                                        <input type="radio" name="radio_color checked"> Gold
                                    </label>
                                    <label class="btn btn-light">
                                        <input type="radio" name="radio_color"> Black
                                    </label>
                                </div>
                            </div>
                        </div> <!-- row.// -->
                        <div class="row">
                            <div class="item-option-select">
                                <h6>Select Size</h6>
                                <div class="btn-group btn-group-sm btn-group-toggle" data-toggle="buttons">
                                    <label class="btn btn-light">
                                        <input type="radio" name="radio_color"> S
                                    </label>
                                    <label class="btn btn-light active">
                                        <input type="radio" name="radio_color" checked> M
                                    </label>
                                    <label class="btn btn-light">
                                        <input type="radio" name="radio_color"> L
                                    </label>
                                    <label class="btn btn-light">
                                        <input type="radio" name="radio_color"> XL
                                    </label>
                                </div>
                            </div>
                        </div> <!-- row.// -->
                        <hr>

                        <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="add"/>
                            <input type="hidden" name="idProduct" value="${product.idProduct}"/>
                            <button type="submit" class="btn btn-primary">
                                <i class="fa fa-shopping-cart"></i> Thêm vào giỏ
                            </button>
                        </form>
                        <a href="${pageContext.request.contextPath}/view/view-order/place-order.jsp" class="btn  btn-primary"> <span class="text">Mua sản phẩm</span> </a>
                    </article> <!-- product-info-aside .// -->
                </main> <!-- col.// -->
            </div> <!-- row.// -->
        </div> <!-- card.// -->
        <!-- ============================ COMPONENT 1 END .// ================================= -->

        <br>
        <div class="row">
            <div class="col-md-9">
                <header class="section-heading">
                    <h3>Đánh Giá Của Khách Hàng</h3>
                </header>

                <!-- Bình luận 1 -->
                <article class="box mb-3">
                    <div class="icontext w-100">
                        <img src="./images/avatars/avatar1.jpg" class="img-xs icon rounded-circle">
                        <div class="text">
                            <span class="date text-muted float-md-right">24.04.2020</span>
                            <h6 class="mb-1">Công Danh</h6>
                        </div>
                    </div>
                    <div class="mt-3">
                        <p>Sản phẩm này rất tốt, tôi rất hài lòng.</p>
                    </div>
                </article>

                <!-- Bình luận 2 -->
                <article class="box mb-3">
                    <div class="icontext w-100">
                        <img src="./images/avatars/avatar2.jpg" class="img-xs icon rounded-circle">
                        <div class="text">
                            <span class="date text-muted float-md-right">10.05.2020</span>
                            <h6 class="mb-1">Ngọc Hân</h6>
                        </div>
                    </div>
                    <div class="mt-3">
                        <p>Dịch vụ chăm sóc khách hàng rất chuyên nghiệp, sản phẩm chất lượng.</p>
                    </div>
                </article>

                <!-- Bình luận 3 -->
                <article class="box mb-3">
                    <div class="icontext w-100">
                        <img src="./images/avatars/avatar3.jpg" class="img-xs icon rounded-circle">
                        <div class="text">
                            <span class="date text-muted float-md-right">15.06.2020</span>
                            <h6 class="mb-1">Minh Tuấn</h6>
                        </div>
                    </div>
                    <div class="mt-3">
                        <p>Giao hàng nhanh, đóng gói cẩn thận, rất đáng tiền.</p>
                    </div>
                </article>

                <!-- Form nhập bình luận mới -->
                <div class="box mb-3">
                    <h5>Để lại đánh giá của bạn</h5>
                    <form>
                        <div class="mb-3">
                            <label class="form-label">Tên của bạn</label>
                            <input type="text" class="form-control" placeholder="Nhập tên của bạn">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Bình luận</label>
                            <textarea class="form-control" rows="3" placeholder="Nhập đánh giá của bạn"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Gửi đánh giá</button>
                    </form>
                </div>
            </div> <!-- col.// -->
        </div> <!-- row.// -->

        <!-- Thêm Bootstrap nếu chưa có -->


    </div> <!-- container .//  -->
</section>
<%@ include file="/view/view-index/footer.jsp" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>
</html>