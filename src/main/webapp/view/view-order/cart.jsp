
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<style>
    .form-control:focus {
        box-shadow: none;
        border-color: #ced4da; /* hoặc bất kỳ màu border mặc định nào */
    }
</style>
<body>
<%@ include file="/view/view-index/header.jsp" %>
<section class="section-content padding-y bg">
    <div class="container">

        <div id="error-notification" style="
                display: none;
                background-color: #f8d7da;
                color: #842029;
                padding: 15px;
                border-radius: 5px;
                margin-bottom: 15px;
                border: 1px solid #f5c2c7;
                font-weight: bold;
            ">
        </div>


        <!-- ============================ COMPONENT 1 ================================= -->

        <div class="row">
            <aside class="col-lg-9">
                <div class="card">
                    <table class="table table-borderless table-shopping-cart">
                        <thead class="text-muted">
                        <tr class="small text-uppercase">
                            <th scope="col">Sản phẩm</th>
                            <th scope="col">Màu</th>
                            <th scope="col">Kích thước</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Giá</th>
                            <th scope="col" class="text-right"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${sessionScope.cart.cartItems.values()}">
                            <tr>
                                <td>
                                    <figure class="itemside align-items-center">
                                        <div class="aside">
                                            <img src="${item.imageUrl}" class="img-sm">
                                        </div>
                                        <figcaption class="info">
                                            <a href="#" class="title text-dark">${item.productTitle}</a>
                                            <p>${item.idVariant}</p>
                                                <%--                                            <p class="text-muted small">Matrix: 25 Mpx <br> Brand: Canon</p>--%>
                                        </figcaption>
                                    </figure>
                                </td>
                                <!-- Dropdown chọn màu -->
                                <td>
                                    <select name="color" class="form-control form-select" style="width: fit-content">
                                        <c:set var="uniqueColors" value="" />
                                        <c:forEach var="color" items="${item.availableColors}">
                                            <c:if test="${not fn:contains(uniqueColors, color.color)}">
                                                <option value="${color.idColor}" ${color.idColor == item.color.idColor ? 'selected' : ''}>
                                                        ${color.color}
                                                </option>
                                                <c:set var="uniqueColors" value="${uniqueColors},${color.color}" />
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>

                                <!-- Dropdown chọn size -->
                                <td>
                                    <select name="size" class="form-control form-select w-0" style="width: fit-content">
                                        <c:set var="uniqueSizes" value="" />
                                        <c:forEach var="size" items="${item.availableSizes}">
                                            <c:if test="${not fn:contains(uniqueSizes, size.size)}">
                                                <option value="${size.idSize}" ${size.idSize == item.size.idSize ? 'selected' : ''}>
                                                        ${size.size}
                                                </option>
                                                <c:set var="uniqueSizes" value="${uniqueSizes},${size.size}" />
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>

                                <td>
                                    <!-- col.// -->
                                    <div class="col">
                                        <div class="input-group input-spinner">
                                            <input class="idVariant-qty" type="hidden" name="idVariant" value="${item.idVariant}"/>
                                            <input class="qty-qty" type="hidden" name="quantity" value="${item.quantity}"/>

                                            <div class="input-group-prepend">
                                                <button class="btn btn-light btn-update-quantity" type="button" data-action="minus"><i class="fa fa-minus"></i></button>
                                            </div>
                                            <input type="text" class="form-control input-qty" data-id-variant="${item.idVariant}" value="${item.quantity}" name="quantity"/>
                                            <div class="input-group-append">
                                                <button class="btn btn-light btn-update-quantity" type="button" data-action="plus"><i class="fa fa-plus"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="price-wrap">
                                        <var class="price">
                                            <fmt:formatNumber value="${item.price}" pattern="#,##0 đ"/>
                                        </var>
                                    </div>
                                </td>
                                <td class="text-right">
                                    <form action="${pageContext.request.contextPath}/cart" method="post">
                                        <input type="hidden" name="action" value="remove"/>
                                        <input type="hidden" name="idVariant" value="${item.idVariant}"/>
                                        <button type="submit" class="btn btn-danger">Xóa</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div> <!-- card.// -->

            </aside> <!-- col.// -->
            <aside class="col-lg-3">

                <!-- Form áp dụng mã giảm giá -->
                <div class="card mb-3">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/cart?action=coupon" method="post">
                            <div class="form-group">
                                <label for="discountCode">Mã giảm giá</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="discountCode" name="code" placeholder="Nhập mã...">
                                    <div class="input-group-append">
                                        <button id="applyCoupon" class="btn btn-outline-primary" type="submit">Áp dụng</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="card">
                    <div class="card-body">
                        <dl class="dlist-align">
                            <dt>Tổng tiền:</dt>
                            <dd class="text-right" id="total-price">
                                <span>
                                    <fmt:formatNumber value="${cart.totalPrice}" pattern="#,##0 đ"/>
                                </span>
                            </dd>
                        </dl>
                        <dl class="dlist-align">
                            <dt>Giảm:</dt>
                            <dd class="text-right" id="discount-amount">
                                <span>
                                    <fmt:formatNumber value="${cart.discountAmount}" pattern="#,##0 đ"/>
                                </span>
                            </dd>
                        </dl>
                        <dl class="dlist-align">
                            <dt>Tổng thanh toán:</dt>
                            <dd class="text-right text-dark b" id="final-total">
                                <strong>
                                    <fmt:formatNumber value="${cart.finalTotal}" pattern="#,##0 đ"/>
                                </strong>
                            </dd>
                        </dl>
                        <hr>
                        <p class="text-center mb-3">
                            <img src="./images/misc/payments.png" height="26">
                        </p>

                        <form action="${pageContext.request.contextPath}/place-order" method="get">
                            <button type="submit" class="btn btn-primary btn-block"> Thanh toán </button>
                        </form>

                        <form action="${pageContext.request.contextPath}/cart" method="post">
                            <input type="hidden" name="action" value="continue">
                            <button type="submit" class="btn btn-light btn-block mt-4">Tiếp tục mua hàng</button>
                        </form>

                    </div>
                </div>

            </aside>


        </div>
        <!-- ============================ COMPONENT 1 END .// ================================= -->

    </div>
</section>
<script>
    document.querySelectorAll('.btn-update-quantity').forEach(button => {
        button.addEventListener('click', function () {
            const spinner = this.closest('.input-spinner');
            const inputQty = spinner.querySelector('.input-qty');
            const hiddenIdVariant = spinner.querySelector('.idVariant-qty');
            const hiddenQuantity = spinner.querySelector('.qty-qty');

            let quantity = parseInt(inputQty.value);
            const action = this.dataset.action;
            const idVariant = hiddenIdVariant.value;

            if (action === 'plus') quantity++;
            if (action === 'minus' && quantity > 1) quantity--;

            inputQty.value = quantity;
            hiddenQuantity.value = quantity; // Đồng bộ luôn hidden quantity

            function formatCurrency(amount) {
                return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
            }

            fetch('/web/cart?action=updateQuantity', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: "idVariant="+idVariant+"&quantity="+quantity
            })
                //
                .then(res => res.json())
                .then(data => {
                    if (data.totalPrice) {
                        document.getElementById('total-price').textContent = formatCurrency(data.totalPrice);
                    }
                    if (data.discountAmount !== undefined) {
                        document.getElementById('discount-amount').textContent = formatCurrency(data.discountAmount);
                    }
                    if (data.finalTotal !== undefined) {
                        document.getElementById('final-total').textContent = formatCurrency(data.finalTotal);
                    }
                });

        });
    });
</script>

<script>
    const isLoggedIn = ${sessionScope.user != null ? "true" : "false"};
    const errorDiv = document.getElementById('error-notification');

    document.querySelector('form[action$="/place-order"]').addEventListener('submit', function(e) {
        if (!isLoggedIn) {
            e.preventDefault();
            errorDiv.textContent = 'Bạn cần đăng nhập để thanh toán!';
            errorDiv.style.display = 'block';
            setTimeout(() => errorDiv.style.display = 'none', 5000);
        }
    });
</script>
<script src="${pageContext.request.contextPath}/js/coupon.js"></script>
</body>
</html>