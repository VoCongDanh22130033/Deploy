<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/view/view-index/header.jsp" %>

<body>

<div class="container"
     style="margin: 0 auto; width: 60%; padding: 50px; background: #f1f1f1; margin-top: 50px; margin-bottom: 50px;">
    <h2 class="text-center text-success">
        <i class="fas fa-check-circle"></i> Thanh toán thành công
    </h2>
    <div class="text-center my-3">
        <a href="${pageContext.request.contextPath}/home" class="btn btn-success">Tiếp tục mua sắm</a>
    </div>

    <div class="row invoice row-printable">
        <div class="col-md-12">
            <div class="panel panel-default plain">
                <div class="panel-body p30">
                    <div class="row">
                        <!-- Logo và thông tin người nhận -->
                        <div class="col-lg-6">
                            <div class="invoice-logo">
                                <img src="${pageContext.request.contextPath}/images/logo.png" style="max-height: 40px;">
                            </div>
                        </div>
                        <div class="col-lg-6 text-right">
                            <ul class="list-unstyled">
                                <li><strong>Hóa đơn cho</strong></li>
                                <li>${order.user.fullName}</li>
                                <li>${order.address.address}</li>
                                <li>${order.address.ward}, ${order.address.district}, ${order.address.province}</li>
                            </ul>
                        </div>

                        <!-- Chi tiết hóa đơn -->
                        <div class="col-lg-12 mt-4">
                            <div class="well">
                                <ul class="list-unstyled mb-0">
                                    <li><strong>Hóa đơn:</strong> #${order.idOrder}</li>
                                    <li><strong>Giao dịch:</strong> COD</li>
                                    <li><strong>Ngày:</strong>
                                        <fmt:formatDate value="${orderDate}" pattern="dd/MM/yyyy"/>
                                    </li>
                                </ul>
                            </div>

                            <!-- Danh sách sản phẩm -->
                            <div class="table-responsive mt-4">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th class="text-center">Sản phẩm</th>
                                        <th class="text-center">Số lượng</th>
                                        <th class="text-center">Thành tiền</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${orderDetails}">
                                        <tr>
                                            <td>${item.nameProduct}</td>
                                            <td class="text-center">${item.quantity}</td>
                                            <td class="text-center">
                                                <fmt:formatNumber value="${item.price * item.quantity}" type="currency"
                                                                  currencySymbol="" groupingUsed="true"/> đ
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <th colspan="2" class="text-right">Tổng tiền:</th>
                                        <th class="text-center">
                                            <fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="" groupingUsed="true"/> đ
                                        </th>
                                    </tr>
                                    <tr>
                                        <th colspan="2" class="text-right">Tiền vận chyển:</th>
                                        <th class="text-center">
                                            <fmt:formatNumber value="${amountDelivery}" pattern="#,##0 đ"/>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th colspan="2" class="text-right">Tổng thanh toán:</th>
                                        <th class="text-center">
                                            <fmt:formatNumber value="${finalPrice}" pattern="#,##0 đ"/>
                                        </th>
                                    </tr>
                                    </tfoot>
                                </table>
                            </div>

                            <div class="invoice-footer text-center mt-4">
                                <p>Cảm ơn bạn đã mua sắm tại GreatKart!</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<%@ include file="/view/view-index/footer.jsp" %>
</body>
</html>
