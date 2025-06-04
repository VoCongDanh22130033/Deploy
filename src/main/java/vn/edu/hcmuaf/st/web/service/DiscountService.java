package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.DiscountDao;
import vn.edu.hcmuaf.st.web.entity.Discount;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DiscountService {
    private final DiscountDao discountDao = new DiscountDao();

    // Lấy tất cả discount
    public List<Discount> getAllDiscounts() {
        return discountDao.getAll();
    }

    // Lấy discount theo ID
    public Optional<Discount> getDiscountById(int id) {
        return discountDao.getById(id);
    }

    // Thêm discount mới
    public boolean addDiscount(Discount discount) {
        return discountDao.add(discount);
    }

    // Cập nhật discount
    public boolean updateDiscount(Discount discount) {
        return discountDao.update(discount);
    }

    // Xóa discount
    public boolean deleteDiscount(int id) {
        return discountDao.delete(id);
    }

    // Kiểm tra discount còn hiệu lực không (theo ngày)
    public boolean isDiscountValid(Discount discount) {
        LocalDateTime now = LocalDateTime.now();
        return !now.isBefore(discount.getStartDate()) && !now.isAfter(discount.getEndDate());
    }

    // Lấy danh sách discount còn hiệu lực tại thời điểm hiện tại
    public List<Discount> getActiveDiscounts() {
        return getAllDiscounts().stream()
                .filter(this::isDiscountValid)
                .toList();
    }
}
