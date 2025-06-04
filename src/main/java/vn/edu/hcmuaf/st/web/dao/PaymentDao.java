package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Payment;

import java.time.LocalDate;

public class PaymentDao {

    private Jdbi jdbi;

    public PaymentDao() {
        this.jdbi = JDBIConnect.get();
    }

    public void insert(Payment payment) {
        String sql = "INSERT INTO payments (idOrder, paymentMethod, amount, status) " +
                "VALUES (:idOrder, :paymentMethod, :amount, :status)";

        jdbi.useHandle(handle -> {
            handle.createUpdate(sql)
                    .bind("idOrder", payment.getOrder().getIdOrder())
                    .bind("paymentMethod", payment.getPaymentMethod())
                    .bind("amount", payment.getAmount())
                    .bind("status", payment.getStatus())
                    .execute();
        });
    }

    public Payment getPaymentByOrderId(int orderId) {
        String sql = """
        SELECT * FROM payments
        WHERE idOrder = :orderId
    """;

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("orderId", orderId)
                        .map((rs, ctx) -> {
                            Payment payment = new Payment();
                            payment.setPaymentMethod(rs.getString("paymentMethod"));
                            payment.setStatus(rs.getString("status"));
                            payment.setAmount(rs.getDouble("amount"));
                            payment.setUpdateAt(LocalDate.from(rs.getTimestamp("updateAt").toLocalDateTime()));

                            return payment;
                        }).findOne().orElse(null)
        );
    }


}
