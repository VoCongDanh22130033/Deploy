<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/view/view-index/header.jsp" %>
<html>
<head>
    <title>Thanh toán</title>
    <style>
        .shipping-option {
            border: 2px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 10px;
            cursor: pointer;
            transition: border-color 0.3s ease;
        }

        .shipping-option input[type="radio"] {
            display: none;
        }

        .shipping-option.selected {
            border-color: #28a745; /* xanh lá */
            background-color: #f4fffa;
        }

        .shipping-option:hover {
            border-color: #28a745;
        }

        .shipping-option .price {
            font-weight: bold;
            color: #28a745;
        }

        .shipping-option del {
            color: #999;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<section class="section-content padding-y bg">
    <div class="container">

        <div class="border-top pt-3 mt-4">
            <div class="bg-light py-3 px-3">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="toggleCoupon" data-toggle="collapse"
                           data-target="#couponForm">
                    <label class="form-check-label font-weight-bold" for="toggleCoupon">
                        Bạn có mã giảm giá vận chuyển? <span
                            class="text-primary">Nhấn vào đây để điền mã giảm giá</span>
                    </label>
                </div>
            </div>

            <div id="couponForm" class="collapse px-3 mt-3">
                <p>Hãy nhập mã giảm giá vận chuyển</p>
                <div class="form-inline">
                    <input type="text" class="form-control mr-2 mb-2" placeholder="Enter coupon code"
                           style="min-width: 250px;">
                    <button class="btn btn-danger mb-2 font-weight-bold text-uppercase">Áp dụng mã giảm giá</button>
                </div>
            </div>
        </div>

        <!-- ============================ COMPONENT 2 ================================= -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">
                    ${errorMessage}
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/place-order" method="post" id="submitForm">
            <div class="row">
                <main class="col-md-8">

                    <article class="card mb-4">
                        <div class="card-body">
                            <h4 class="card-title mb-4">Giỏ hàng</h4>
                            <div class="row">

                                <c:forEach var="item" items="${sessionScope.cart.cartItems.values()}">
                                    <div class="col-md-6">
                                        <figure class="itemside mb-4">
                                            <div class="aside"><img src="${item.imageUrl}" class="border img-sm"></div>
                                            <figcaption class="info">
                                                <p> ${item.productTitle} </p>
                                                <span class="text-muted d-block"> Số lượng: ${item.quantity} </span>
                                                <span class="text-muted d-block"> Giá: <fmt:formatNumber
                                                        value="${item.price}" pattern="#,##0 đ"/> </span>
                                            </figcaption>
                                        </figure>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                    </article>


                    <article class="card mb-4">
                        <div class="card-body">
                            <h4 class="card-title mb-4">Thông tin liên lạc</h4>

                            <div class="row">
                                <div class="form-group col-sm-12">
                                    <label> Họ và Tên </label>
                                    <input type="text" name="fullName" placeholder="Nhập thông tin"
                                           class="form-control"
                                           value="${not empty fullName ? fullName : null}" />
                                </div>

                                <div class="form-group col-sm-6">
                                    <label>Số điện thoại</label>
                                    <input type="text" name="phone" placeholder="+98"
                                           class="form-control"
                                           value="${not empty phone ? phone : null}" />
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>Email</label>
                                    <input type="email" name="email" placeholder="example@gmail.com"
                                           class="form-control"
                                           value="${not empty email ? email : null}" />
                                </div>
                            </div> <!-- row.// -->

                        </div> <!-- card-body.// -->
                    </article> <!-- card.// -->


                    <article class="card mb-4">
                        <div class="card-body">
                            <h4 class="card-title mb-4">Thông tin giao hàng</h4>



                            <div class="row">
                                <div class="form-group col-sm-12">
                                    <label> Địa chỉ* </label>
                                    <input type="text" name="address" placeholder="Nhập địa chỉ" class="form-control">
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>Phường/Xã*</label>
                                    <select name="ward" id="ward" class="form-control" required></select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>Quận/Huyện*</label>
                                    <select name="district" id="district" class="form-control" onchange="onDistrictChange()" required></select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>Tỉnh/Thành phố*</label>
                                    <select name="province" id="province" class="form-control" onchange="onProvinceChange()" required></select>
                                </div>
                            </div> <!-- row.// -->

                        </div> <!-- card-body.// -->
                    </article> <!-- card.// -->


                    <article class="accordion" id="accordion_pay">
                        <div class="card">
                            <header class="card-header">
                                <i class="fas fa-wallet float-right mt-1 text-primary"></i>
                                <label class="form-check collapsed" data-toggle="collapse" data-target="#pay_ewallet">
                                    <input class="form-check-input" name="payment-option" checked type="radio"
                                           value="ewallet">
                                    <h6 class="form-check-label mb-0">
                                        Thanh toán qua ví điện tử
                                    </h6>
                                </label>
                            </header>
                            <div id="pay_ewallet" class="collapse show" data-parent="#accordion_pay">
                                <div class="card-body text-center">
                                    <p class="text-muted">Sử dụng ví điện tử như Momo, ZaloPay để thanh toán. Bạn sẽ
                                        được chuyển hướng để xác nhận giao dịch.</p>
                                    <a href="#"><img src="./images/misc/btn-momo.webp" height="32" class="mr-2"></a>
                                    <a href="#"><img src="./images/misc/btn-zalopay.png" height="32"></a>
                                    <a href="#"><img src="./images/misc/btn-vnpay.png" height="32"></a>
                                </div>
                            </div>
                        </div>

                        <div class="card">
                            <header class="card-header">
                                <i class="fas fa-truck float-right mt-1 text-success"></i>
                                <label class="form-check" data-toggle="collapse" data-target="#pay_cod">
                                    <input class="form-check-input" name="payment-option" type="radio" value="cod">
                                    <h6 class="form-check-label mb-0">
                                        Thanh toán khi nhận hàng (COD)
                                    </h6>
                                </label>
                            </header>
                            <div id="pay_cod" class="collapse" data-parent="#accordion_pay">
                                <div class="card-body">
                                    <p class="text-success">Bạn sẽ thanh toán khi nhận được hàng từ nhân viên giao
                                        hàng.</p>

                                    <div class="mt-3">
                                        <h6 class="font-weight-bold">Phương thức vận chuyển</h6>

                                        <div class="shipping-option selected" onclick="selectOption(this)">
                                            <input type="radio" name="shipping" value="tietkiem" checked>
                                            <div class="d-flex justify-content-between">
                                                <div>
                                                    <strong>Tiết Kiệm</strong>
                                                    <div class="text-muted small">Giao từ 7-12 ngày</div>
                                                    <div class="text-muted small">coupon dùng để miễn phí vận chuyển
                                                        #FreeShip
                                                    </div>
                                                </div>
                                                <div class="text-right">
                                                    <div>
                                                        <del>18.000₫</del>
                                                    </div>
                                                    <div class="price">Miễn phí</div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="shipping-option" onclick="selectOption(this)">
                                            <input type="radio" name="shipping" value="nhanh">
                                            <div class="d-flex justify-content-between">
                                                <div>
                                                    <strong>Nhanh</strong>
                                                    <div class="text-muted small">Giao từ 3-5 ngày</div>
                                                </div>
                                                <div class="text-right">
                                                    <div>22.200₫</div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="shipping-option" onclick="selectOption(this)">
                                            <input type="radio" name="shipping" value="hoatoc">
                                            <div class="d-flex justify-content-between">
                                                <div>
                                                    <strong>Hỏa Tốc</strong>
                                                    <div class="text-info small">🚚 Đảm bảo nhận hàng vào ngày mai</div>
                                                    <div class="text-muted small">Không hỗ trợ chương trình Đồng Kiểm
                                                    </div>
                                                </div>
                                                <div class="text-right">
                                                    <div class="price text-dark">65.100₫</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </article>

                    <!-- accordion end.// -->

                </main> <!-- col.// -->
                <aside class="col-md-4">
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
                                <dt>Tiền vận chuyển:</dt>
                                <dd class="text-right">
                                    <span id="shipping-fee">
                                        <fmt:formatNumber value="0" pattern="#,##0 đ"/>
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
                                <dt>Tổng thanh toán</dt>
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

                            <button type="submit" class="btn btn-primary btn-block">Thanh toán</button>


                        </div> <!-- card-body.// -->
                    </div> <!-- card.// -->
                </aside>
                <!-- col.// -->
            </div>

            <!-- Các input ẩn -->
            <input type="hidden" name="amountDelivery" id="amountDelivery">
            <input type="hidden" name="finalPrice" id="finalPrice">

        </form><!-- row.// -->
        <!-- ============================ COMPONENT 2 END//  ================================= -->
    </div> <!-- container .//  -->
</section>
<!-- ========================= SECTION CONTENT END// ========================= -->

<%@ include file="/view/view-index/footer.jsp" %>


<script>
    function selectOption(el) {
        const options = document.querySelectorAll('.shipping-option');
        options.forEach(opt => opt.classList.remove('selected'));
        el.classList.add('selected');
        el.querySelector('input[type="radio"]').checked = true;
    }
</script>

<script>
    const TOKEN_GHN = '16a9a441-3f8e-11f0-9b81-222185cb68c8'; // Token GHN của bạn

    // 1. Load danh sách tỉnh/thành khi trang load
    window.addEventListener("DOMContentLoaded", () => {
        fetch('https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province', {
            method: 'GET',
            headers: { 'Token': TOKEN_GHN }
        })
            .then(res => res.json())
            .then(data => {
                const provinceSelect = document.getElementById("province");
                provinceSelect.innerHTML = ''; // Clear nếu có dữ liệu cũ
                data.data.forEach(province => {
                    const opt = new Option(province.ProvinceName, province.ProvinceID);
                    provinceSelect.appendChild(opt);
                });

                if(provinceSelect.options.length > 0){
                    provinceSelect.selectedIndex = 0;
                    onProvinceChange();
                }
            })
            .catch(err => console.error('Lỗi tải tỉnh:', err));
    });

    // 2. Khi chọn tỉnh → gọi API GHN lấy danh sách quận/huyện
    function onProvinceChange() {
        const provinceId = document.getElementById("province").value;
        fetch('https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Token': TOKEN_GHN
            },
            body: JSON.stringify({ province_id: parseInt(provinceId) })
        })
            .then(res => res.json())
            .then(data => {
                const districtSelect = document.getElementById("district");
                districtSelect.innerHTML = '';
                data.data.forEach(district => {
                    const opt = new Option(district.DistrictName, district.DistrictID);
                    districtSelect.appendChild(opt);
                });

                if(districtSelect.options.length > 0){
                    districtSelect.selectedIndex = 0;
                    onDistrictChange();
                }
            })
            .catch(err => console.error('Lỗi tải huyện:', err));
    }

    // 3. Khi chọn quận → gọi API GHN lấy danh sách phường/xã
    function onDistrictChange() {
        const districtId = document.getElementById("district").value;

        const url = "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=" + districtId;

        fetch(url, {
            method: 'GET',
            headers: { 'Token': TOKEN_GHN }
        })
            .then(res => res.json())
            .then(data => {
                const wardSelect = document.getElementById("ward");
                wardSelect.innerHTML = '';
                data.data.forEach(ward => {
                    const opt = new Option(ward.WardName, ward.WardCode);
                    wardSelect.appendChild(opt);
                });
            })
            .catch(err => console.error('Lỗi tải xã:', err));
    }
</script>


<%--<script>--%>
<%--    let data = [];--%>

<%--    fetch('${pageContext.request.contextPath}/json/vietnam-location.json')--%>
<%--        .then(res => res.json())--%>
<%--        .then(json => {--%>
<%--            data = json;--%>
<%--            const provinceSelect = document.getElementById('province');--%>
<%--            json.forEach(province => {--%>
<%--                const option = document.createElement('option');--%>
<%--                option.value = province.name;--%>
<%--                option.textContent = province.name;--%>
<%--                provinceSelect.appendChild(option);--%>
<%--            });--%>
<%--            onProvinceChange(); // Tự động load quận/huyện đầu tiên--%>
<%--        });--%>

<%--    function onProvinceChange() {--%>
<%--        const provinceName = document.getElementById('province').value;--%>
<%--        const province = data.find(p => p.name === provinceName);--%>
<%--        const districtSelect = document.getElementById('district');--%>
<%--        districtSelect.innerHTML = '';--%>
<%--        province?.districts.forEach(d => {--%>
<%--            const option = document.createElement('option');--%>
<%--            option.value = d.name;--%>
<%--            option.textContent = d.name;--%>
<%--            districtSelect.appendChild(option);--%>
<%--        });--%>
<%--        onDistrictChange();--%>
<%--    }--%>

<%--    function onDistrictChange() {--%>
<%--        const provinceName = document.getElementById('province').value;--%>
<%--        const districtName = document.getElementById('district').value;--%>
<%--        const province = data.find(p => p.name === provinceName);--%>
<%--        const district = province?.districts.find(d => d.name === districtName);--%>
<%--        const wardSelect = document.getElementById('ward');--%>
<%--        wardSelect.innerHTML = '';--%>
<%--        district?.wards.forEach(w => {--%>
<%--            const option = document.createElement('option');--%>
<%--            option.value = w;--%>
<%--            option.textContent = w;--%>
<%--            wardSelect.appendChild(option);--%>
<%--        });--%>
<%--    }--%>
<%--</script>--%>

<script>
    const shippingOptions = document.querySelectorAll('.shipping-option');
    const totalPriceText = "${cart.totalPrice}"; // từ server render
    const discountAmountText = "${cart.discountAmount}"; // từ server render
    const totalPrice = parseFloat(totalPriceText);
    const discountAmount = parseFloat(discountAmountText);

    function selectOption(el) {
        // UI: Highlight selected
        shippingOptions.forEach(opt => opt.classList.remove('selected'));
        el.classList.add('selected');
        el.querySelector('input[type="radio"]').checked = true;

        // Logic: Xác định phí vận chuyển theo value
        const value = el.querySelector('input[type="radio"]').value;
        let deliveryFee = 0;

        if (value === 'nhanh') deliveryFee = 22200;
        else if (value === 'hoatoc') deliveryFee = 65100;
        else deliveryFee = 0;

        // Cập nhật vào DOM
        document.querySelector('dd span[id="shipping-fee"]')?.remove(); // clean nếu có
        const shippingDisplay = document.createElement('span');
        shippingDisplay.id = "shipping-fee";
        shippingDisplay.textContent = new Intl.NumberFormat('vi-VN').format(deliveryFee) + ' đ';
        el.closest('form').querySelector('dl:nth-of-type(2) dd').appendChild(shippingDisplay);

        const finalTotal = totalPrice + deliveryFee - discountAmount;
        document.getElementById("final-total").innerHTML = "<strong>" + new Intl.NumberFormat('vi-VN').format(finalTotal) + " đ</strong>";

        // Gán vào input ẩn
        document.getElementById("amountDelivery").value = deliveryFee;
        document.getElementById("finalPrice").value = finalTotal;
    }
</script>

</body>
</html>
