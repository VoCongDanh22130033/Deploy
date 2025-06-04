package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Discount;

import java.util.List;
import java.util.Optional;

public class DiscountDao {
    private final Jdbi jdbi;

    public DiscountDao() {
        this.jdbi = JDBIConnect.get();
    }

    public List<Discount> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM discount")
                        .mapToBean(Discount.class)
                        .list()
        );
    }

    public Optional<Discount> getById(int idDiscount) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM discount WHERE idDiscount = :idDiscount")
                        .bind("idDiscount", idDiscount)
                        .mapToBean(Discount.class)
                        .findOne()
        );
    }

    public boolean add(Discount discount) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO discount (discountAmount, startDate, endDate) VALUES (:discountAmount, :startDate, :endDate)")
                        .bind("discountAmount", discount.getDiscountAmount())
                        .bind("startDate", discount.getStartDate())
                        .bind("endDate", discount.getEndDate())
                        .execute() > 0
        );
    }

    public boolean update(Discount discount) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE discount SET discountAmount = :discountAmount, startDate = :startDate, endDate = :endDate WHERE idDiscount = :idDiscount")
                        .bind("idDiscount", discount.getIdDiscount())
                        .bind("discountAmount", discount.getDiscountAmount())
                        .bind("startDate", discount.getStartDate())
                        .bind("endDate", discount.getEndDate())
                        .execute() > 0
        );
    }

    public boolean delete(int idDiscount) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM discount WHERE idDiscount = :idDiscount")
                        .bind("idDiscount", idDiscount)
                        .execute() > 0
        );
    }
}
