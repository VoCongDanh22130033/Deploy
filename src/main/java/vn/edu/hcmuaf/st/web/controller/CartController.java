package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.st.web.entity.*;
import vn.edu.hcmuaf.st.web.service.ProductService;
import vn.edu.hcmuaf.st.web.service.ProductVariantService;
import vn.edu.hcmuaf.st.web.service.CouponService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "cartController", urlPatterns = "/cart")
public class CartController extends HttpServlet {

    private final ProductService productService = new ProductService();
    private final ProductVariantService productVariantService = new ProductVariantService();
    private final CouponService couponService = new CouponService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/view-order/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = req.getParameter("action");

        switch (action) {
            case "add":
                int productId = Integer.parseInt(req.getParameter("idProduct"));
                Product product = productService.getProductById(productId);
                List<ProductVariant> variants = productVariantService.getProductVariantsByIdProduct(productId);
                product.setProductVariants(variants);
                cart.addItem(product);
                resp.sendRedirect(req.getContextPath() + "/cart");
                break;

            case "continue":
                String prevUrl = (String) session.getAttribute("prevUrl");
                if (prevUrl == null) {
                    prevUrl = req.getContextPath() + "/home";
                }
                resp.sendRedirect(prevUrl);
                break;

            case "remove":
                int idVariant = Integer.parseInt(req.getParameter("idVariant"));
                cart.removeItem(idVariant);
                session.setAttribute("cart", cart);
                resp.sendRedirect(req.getContextPath() + "/cart");
                break;

            case "updateQuantity":
                try {
                    int idVariantUpdate = Integer.parseInt(req.getParameter("idVariant"));
                    int newQuantity = Integer.parseInt(req.getParameter("quantity"));

                    cart.addQuantity(idVariantUpdate, newQuantity);
                    session.setAttribute("cart", cart);

                    double totalPrice = cart.getTotalPrice();
                    double discountAmount = cart.getDiscountAmount();
                    double finalTotal = cart.getFinalTotal();

                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");

                    String json = String.format(
                            "{\"totalPrice\": %.0f, \"discountAmount\": %.0f, \"finalTotal\": %.0f}",
                            totalPrice, discountAmount, finalTotal
                    );

                    resp.getWriter().write(json);
                } catch (NumberFormatException e) {
                    resp.setContentType("text/plain");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write("ERROR: Invalid number format");
                }
                break;

            case "coupon":
                String code = req.getParameter("code");
                boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
                System.out.println("[DEBUG] isAjax: " + isAjax);

                if (code != null && !code.trim().isEmpty()) {
                    Optional<Coupon> optionalCoupon = couponService.getCouponByCode(code.trim());
                    if (optionalCoupon.isPresent()) {
                        Coupon coupon = optionalCoupon.get();

                        boolean isProductCoupon = coupon.getDiscountType() == Coupon.DiscountType.PRODUCT;
                        boolean isValidDate = LocalDateTime.now().isAfter(coupon.getStartDate()) && LocalDateTime.now().isBefore(coupon.getEndDate());
                        boolean underUsage = coupon.getUsedCount() < coupon.getUsageLimit();
                        boolean meetsMinOrder = cart.getTotalPrice() >= coupon.getMinOrderValue();

                        if (isProductCoupon && isValidDate && underUsage && meetsMinOrder) {
                            double discount;
                            if (coupon.isPercentage()) {
                                discount = cart.getTotalPrice() * coupon.getDiscountAmount() / 100.0;
                            } else {
                                discount = coupon.getDiscountAmount();
                            }

                            discount = Math.min(discount, cart.getTotalPrice());

                            cart.setCouponCode(code);
                            cart.setDiscountAmount(discount);
                            cart.setFinalTotal(cart.getTotalPrice() - discount);

                            couponService.incrementUsedCount(coupon.getIdCoupon());

                            if (isAjax) {
                                resp.setContentType("application/json");
                                resp.setCharacterEncoding("UTF-8");
                                String json = String.format(
                                        "{\"success\": true, \"message\": \"Áp dụng mã thành công.\", \"discountAmount\": %.0f, \"finalTotal\": %.0f}",
                                        discount, cart.getFinalTotal()
                                );
                                resp.getWriter().write(json);
                            } else {
                                req.setAttribute("couponMessage", "Áp dụng mã thành công.");
                                session.setAttribute("cart", cart);
                                req.getRequestDispatcher("view/view-order/cart.jsp").forward(req, resp);
                            }
                            return;

                        } else {
                            String reason = "";
                            if (!isProductCoupon) reason += "Mã không áp dụng cho sản phẩm. ";
                            if (!isValidDate) reason += "Mã đã hết hạn hoặc chưa bắt đầu. ";
                            if (!underUsage) reason += "Mã đã hết lượt sử dụng. ";
                            if (!meetsMinOrder) reason += "Đơn hàng chưa đủ điều kiện áp dụng mã. ";

                            if (isAjax) {
                                resp.setContentType("application/json");
                                resp.setCharacterEncoding("UTF-8");
                                resp.getWriter().write("{\"success\": false, \"message\": \"" + reason + "\", \"discountAmount\": 0, \"finalTotal\": " + cart.getTotalPrice() + "}");
                            } else {
                                req.setAttribute("couponMessage", reason);
                                cart.setDiscountAmount(0);
                                cart.setFinalTotal(cart.getTotalPrice());
                                session.setAttribute("cart", cart);
                                req.getRequestDispatcher("view/view-order/cart.jsp").forward(req, resp);
                            }
                            return;
                        }

                    } else {
                        if (isAjax) {
                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write("{\"success\": false, \"message\": \"Mã không tồn tại.\", \"discountAmount\": 0, \"finalTotal\": " + cart.getTotalPrice() + "}");
                        } else {
                            req.setAttribute("couponMessage", "Mã không tồn tại.");
                            cart.setDiscountAmount(0);
                            cart.setFinalTotal(cart.getTotalPrice());
                            session.setAttribute("cart", cart);
                            req.getRequestDispatcher("view/view-order/cart.jsp").forward(req, resp);
                        }
                    }
                }
                break;

            default:
                resp.sendRedirect(req.getContextPath() + "/cart");
                break;
        }
    }
}
