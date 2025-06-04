package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.CouponDAO;
import vn.edu.hcmuaf.st.web.entity.Coupon;

import java.time.LocalDateTime;
import java.util.Optional;

public class CouponService {
    private final CouponDAO couponDAO = new CouponDAO();

    public void incrementUsedCount(int idCoupon) {
        couponDAO.incrementUsedCount(idCoupon);
    }

    public static class CouponResult {
        private final boolean success;
        private final String message;
        private final double discountAmount;
        private final double finalTotal;
        private final String couponCode;

        public CouponResult(boolean success, String message, double discountAmount, double finalTotal, String couponCode) {
            this.success = success;
            this.message = message;
            this.discountAmount = discountAmount;
            this.finalTotal = finalTotal;
            this.couponCode = couponCode;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public double getDiscountAmount() {
            return discountAmount;
        }

        public double getFinalTotal() {
            return finalTotal;
        }

        public String getCouponCode() {
            return couponCode;
        }
    }

    /**
     * Áp dụng mã giảm giá cho sản phẩm hoặc vận chuyển.
     *
     * @param code             Mã giảm giá người dùng nhập
     * @param productTotal     Tổng tiền sản phẩm
     * @param shippingFee      Phí vận chuyển
     * @param applyToShipping  True nếu áp dụng cho phí ship, false nếu cho sản phẩm
     * @return CouponResult
     */
    public CouponResult applyCoupon(String code, double productTotal, double shippingFee, boolean applyToShipping) {
        Optional<Coupon> optionalCoupon = couponDAO.getByCode(code);
        System.out.println("[DEBUG] Tìm mã: " + code);

        if (optionalCoupon.isEmpty()) {
            System.out.println("[DEBUG] Không tìm thấy mã trong DB");
            return new CouponResult(false, "Mã giảm giá không tồn tại.", 0, productTotal + shippingFee, null);
        }

        Coupon coupon = optionalCoupon.get();
        System.out.println("[DEBUG] Coupon tìm được: " + coupon);

        // Kiểm tra loại mã
        if ((applyToShipping && coupon.getDiscountType() != Coupon.DiscountType.SHIPPING) ||
                (!applyToShipping && coupon.getDiscountType() != Coupon.DiscountType.PRODUCT)) {
            return new CouponResult(false, "Mã không áp dụng cho phần này.", 0, productTotal + shippingFee, code);
        }

        // Kiểm tra thời gian hiệu lực
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(coupon.getStartDate()) || now.isAfter(coupon.getEndDate())) {
            return new CouponResult(false, "Mã đã hết hạn hoặc chưa bắt đầu.", 0, productTotal + shippingFee, code);
        }

        // Kiểm tra số lượt dùng
        if (coupon.getUsedCount() >= coupon.getUsageLimit()) {
            return new CouponResult(false, "Mã đã hết lượt sử dụng.", 0, productTotal + shippingFee, code);
        }

        // Kiểm tra giá trị đơn hàng tối thiểu
        double baseAmount = applyToShipping ? shippingFee : productTotal;
        if (baseAmount < coupon.getMinOrderValue()) {
            return new CouponResult(false, "Đơn hàng chưa đủ điều kiện áp dụng mã.", 0, productTotal + shippingFee, code);
        }

        // Tính giảm giá
        double discount = coupon.isPercentage()
                ? baseAmount * coupon.getDiscountAmount() / 100.0
                : coupon.getDiscountAmount();
        discount = Math.min(discount, baseAmount); // Không vượt quá tổng phần áp dụng

        // Cập nhật lượt dùng
        couponDAO.incrementUsedCount(coupon.getIdCoupon());

        // Tổng sau giảm
        double finalTotal = applyToShipping
                ? productTotal + (shippingFee - discount)
                : (productTotal - discount) + shippingFee;

        return new CouponResult(true, "Áp dụng mã thành công.", discount, finalTotal, code);
    }

    /**
     * Trả về coupon nếu tồn tại trong DB
     */
    public Optional<Coupon> getCouponByCode(String code) {
        return couponDAO.getByCode(code);
    }
}
