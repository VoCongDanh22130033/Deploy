package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.st.web.entity.*;
import vn.edu.hcmuaf.st.web.service.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "orderController", urlPatterns = "/place-order")
public class OrderController extends HttpServlet {

    private final OrderDetailService orderDetailService = new OrderDetailService();
    private final AddressService addressService = new AddressService();
    private final PaymentService paymentService = new PaymentService();
    private final AccountService accountService = new AccountService();
    private final OrderService orderService = new OrderService();
    private final ProductVariantService productVariantService = new ProductVariantService();
    private final CouponService couponService = new CouponService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        //kiem tra cart
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (cart == null || cart.getCartItems().isEmpty()) {
            resp.sendRedirect(req.getContextPath()+"/cart");
            return;
        }

        // Gán thông tin người dùng để hiển thị sẵn trong form
        req.setAttribute("fullName", user.getFullName());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("phone", user.getPhoneNumber());

        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/view/view-order/place-order.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (user == null || cart == null || cart.getCartItems().isEmpty()) {
            resp.sendRedirect(req.getContextPath()+"/cart");
            return;
        }

        // ⬇️ LẤY GIÁ TRỊ GỬI TỪ FORM (phí ship và tổng tiền sau khi cộng phí ship, trừ giảm giá)
        String shippingFeeStr = req.getParameter("amountDelivery");
        String finalPriceStr = req.getParameter("finalPrice");

        double amountDelivery = shippingFeeStr != null ? Double.parseDouble(shippingFeeStr) : 0;
        double finalPrice = finalPriceStr != null ? Double.parseDouble(finalPriceStr) : cart.getTotalPrice();

        //1. Lay thong tin tu form

        //1.1 Lay thong tin nguoi dung
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        //1.2. Form thong tin giao hangg
        String addressText = req.getParameter("address");
        String ward = req.getParameter("ward");
        String district = req.getParameter("district");
        String province = req.getParameter("province");

        //2.Luu address
        Address address = new Address();
        address.setUser(user);
        address.setAddress(addressText);
        address.setWard(ward);
        address.setDistrict(district);
        address.setProvince(province);
        int addressId = addressService.addAddress(address);
        address.setIdAddress(addressId);

        //3.tao don hang
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus("Chờ xác nhận");
        order.setAmountDelivery(amountDelivery); // không lưu DB nhưng giữ trong object
        order.setFinalPrice(finalPrice);
        int orderId = orderService.createOrder(order);
        order.setIdOrder(orderId);

        String couponCode = req.getParameter("couponCode");
        if (couponCode != null && !couponCode.isEmpty()) {
            Optional<Coupon> couponOptional = couponService.getCouponByCode(couponCode);
            if (couponOptional.isPresent()) {
                Coupon coupon = couponOptional.get();

                // ✅ Tăng số lượt sử dụng (không cần đánh dấu người dùng)
                couponService.incrementUsedCount(coupon.getIdCoupon());

                // ✅ Nếu giảm theo phần trăm
                double relevantTotal = cart.getTotalPrice(); // bạn có thể thay đổi nếu chỉ áp dụng cho sản phẩm nào đó
                double discount;
                if (coupon.isPercentage()) {
                    discount = relevantTotal * coupon.getDiscountAmount() / 100.0;
                } else {
                    discount = coupon.getDiscountAmount();
                }

                // Không cho vượt quá tổng tiền
                discount = Math.min(discount, relevantTotal);

                // ✅ Set giảm giá vào đơn hàng
                order.setDiscountAmount(discount);
            }
        }

        //4.tao chi tiet don hang
        for(CartItem cartItem : cart.getCartItems().values()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setIdOrder(orderId);
            orderDetail.setIdVariant(cartItem.getIdVariant());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getPrice());

            orderDetailService.createOrderDetail(orderDetail);

            // ⬇️ TRỪ SỐ LƯỢNG SAU KHI TẠO CHI TIẾT ĐƠN HÀNG
            productVariantService.reduceQuantity(cartItem.getIdVariant(), cartItem.getQuantity());
        }

        // 5. Tạo đơn vận chuyển GHN cho toàn bộ đơn hàng
        try {
            String ghnOrderCode = GhnShippingService.createShippingOrder(order);
            orderService.updateGhnOrderCode(orderId, ghnOrderCode);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi (có thể log, thông báo admin hoặc người dùng)
        }

        //5.tao thanh toan
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod("COD");
        payment.setAmount(cart.getTotalPrice());
        payment.setStatus("Chờ xác nhận");
        paymentService.createPayment(payment);

        //6.xoa gio hang
        req.getSession().removeAttribute("cart");

        // Truy vấn lại đầy đủ order sau khi tạo (kèm theo address, user, orderDetails)
        Order fullOrder = orderService.getOrderById(orderId);
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
        //Payment paymentInfo = paymentService.getPaymentByOrderId(orderId);

        req.setAttribute("order", fullOrder);
        req.setAttribute("orderDetails", orderDetails);
        //req.setAttribute("payment", paymentInfo);

        req.setAttribute("orderDate", java.sql.Timestamp.valueOf(fullOrder.getCreatedAt()));

        req.setAttribute("amountDelivery", amountDelivery);
        req.setAttribute("finalPrice", finalPrice);

        req.getRequestDispatcher("/view/view-order/order-complete.jsp").forward(req, resp);


    }
}