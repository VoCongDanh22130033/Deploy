package vn.edu.hcmuaf.st.web.controller;

import vn.edu.hcmuaf.st.web.service.CouponService;
import vn.edu.hcmuaf.st.web.service.CouponService.CouponResult;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CouponController", urlPatterns = {"/apply-coupon"})
public class CouponController extends HttpServlet {

    private final CouponService couponService = new CouponService();

    // GET dùng để kiểm tra mã và lấy thông tin đơn giản (không tính giảm giá)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (code == null || code.trim().isEmpty()) {
            resp.getWriter().write("{\"success\":false,\"message\":\"Chưa nhập mã giảm giá.\"}");
            return;
        }

        CouponResult result = couponService.applyCoupon(code, 0, 0, false);
        // Lưu ý: Ở đây truyền 0 tiền vì chỉ để kiểm tra tồn tại mã và thời hạn thôi

        if (!result.isSuccess()) {
            resp.getWriter().write("{\"success\":false,\"message\":\"" + result.getMessage() + "\"}");
        } else {
            // Trả về thành công, không cần trả discountAmount vì chưa có tổng tiền, phí vận chuyển
            resp.getWriter().write("{\"success\":true,\"message\":\"Mã hợp lệ.\"}");
        }
    }

    // POST dùng để thực sự áp dụng mã, cần tổng tiền và phí ship từ client gửi lên
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String productTotalStr = req.getParameter("productTotal");
        String shippingFeeStr = req.getParameter("shippingFee");
        String applyToShippingStr = req.getParameter("applyToShipping");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Kiểm tra dữ liệu đầu vào
        if (code == null || code.trim().isEmpty() ||
                productTotalStr == null || shippingFeeStr == null || applyToShippingStr == null) {
            resp.getWriter().write("{\"success\":false,\"message\":\"Thiếu tham số.\"}");
            return;
        }

        try {
            double productTotal = Double.parseDouble(productTotalStr);
            double shippingFee = Double.parseDouble(shippingFeeStr);
            boolean applyToShipping = Boolean.parseBoolean(applyToShippingStr);

            CouponResult result = couponService.applyCoupon(code, productTotal, shippingFee, applyToShipping);

            if (result.isSuccess()) {
                resp.getWriter().write("{\"success\":true,\"message\":\"" + result.getMessage() +
                        "\",\"discountAmount\":" + result.getDiscountAmount() + "}");
            } else {
                resp.getWriter().write("{\"success\":false,\"message\":\"" + result.getMessage() + "\"}");
            }
        } catch (NumberFormatException e) {
            resp.getWriter().write("{\"success\":false,\"message\":\"Giá trị tiền không hợp lệ.\"}");
        }
    }
}
