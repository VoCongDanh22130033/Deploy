package vn.edu.hcmuaf.st.web.dao;

import com.mysql.cj.BindValue;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Coupon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CouponDAO {
    private final Jdbi jdbi;

    Coupon coupon = new Coupon();

    public CouponDAO() {
        this.jdbi = JDBIConnect.get();
    }

    // Custom RowMapper để ánh xạ ResultSet → Coupon (vì có enum và boolean)
    private final RowMapper<Coupon> couponMapper = (rs, ctx) -> {
        Coupon coupon = new Coupon();
        coupon.setIdCoupon(rs.getInt("idCoupon"));
        coupon.setCode(rs.getString("code"));
        coupon.setDiscountAmount(rs.getDouble("discountAmount"));
        coupon.setPercentage(rs.getBoolean("isPercentage")); // tinyint(1) → boolean
        coupon.setMinOrderValue(rs.getDouble("minOrderValue"));

        // Xử lý timestamp → LocalDateTime
        if (rs.getTimestamp("startDate") != null) {
            coupon.setStartDate(rs.getTimestamp("startDate").toLocalDateTime());
        }
        if (rs.getTimestamp("endDate") != null) {
            coupon.setEndDate(rs.getTimestamp("endDate").toLocalDateTime());
        }

        coupon.setUsageLimit(rs.getInt("usageLimit"));
        coupon.setUsedCount(rs.getInt("usedCount"));

        // Lấy enum DiscountType (cột discountType lưu 'PRODUCT' hoặc 'SHIPPING')
        String typeStr = rs.getString("discountType");
        if (typeStr != null) {
            try {
                coupon.setDiscountType(Coupon.DiscountType.valueOf(typeStr.toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Nếu dữ liệu DB không đúng, bạn có thể log hoặc bỏ qua
                coupon.setDiscountType(null);
            }
        }

        return coupon;
    };

    /**
     * Lấy tất cả coupon trong DB
     */
    public List<Coupon> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM coupons")
                        .map(couponMapper)
                        .list()
        );
    }

    /**
     * Lấy coupon theo idCoupon (int)
     */
    public Optional<Coupon> getById(int idCoupon) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM coupons WHERE idCoupon = :idCoupon")
                        .bind("idCoupon", idCoupon)
                        .map(couponMapper)
                        .findOne()
        );
    }

    /**
     * Lấy coupon theo code (varchar)
     */
    public Optional<Coupon> getByCode(String code) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM coupons WHERE code = :code")
                        .bind("code", code)
                        .map(couponMapper)
                        .findOne()
        );
    }

    /**
     * Tăng usedCount lên 1 (khi coupon được áp dụng thành công)
     */
    public boolean incrementUsedCount(int idCoupon) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE coupons SET usedCount = usedCount + 1 WHERE idCoupon = :idCoupon")
                        .bind("idCoupon", idCoupon)
                        .execute() > 0
        );
    }
    public Coupon findByCode(String code) {
        return getByCode(code).orElse(null);
    }
    public static void main(String[] args) {
        CouponDAO dao = new CouponDAO();
        List<Coupon> allCoupons = dao.getAll();

        System.out.println("====== Danh sách mã giảm giá ======");
        for (Coupon c : allCoupons) {
            System.out.println(c); // Sẽ gọi toString() đã override sẵn
        }
    }



}
