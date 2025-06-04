package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Order;
import vn.edu.hcmuaf.st.web.entity.OrderDetail;
import vn.edu.hcmuaf.st.web.entity.Product;
import vn.edu.hcmuaf.st.web.entity.ProductVariant;

import java.util.List;

public class OrderDetailDao {

    private Jdbi jdbi;

    public OrderDetailDao() {
        this.jdbi = JDBIConnect.get();
    }

    public void insert(OrderDetail orderDetail) {
        String sql = "INSERT INTO orderDetail (idOrder, idVariant, quantity, price, discountPrice) " +
                "VALUES (:idOrder, :idVariant, :quantity, :price, :discountPrice)";

        jdbi.useHandle(handle -> {
            handle.createUpdate(sql)
                    .bind("idOrder", orderDetail.getIdOrder())
                    .bind("idVariant", orderDetail.getIdVariant())
                    .bind("quantity", orderDetail.getQuantity())
                    .bind("price", orderDetail.getPrice())
                    .bind("discountPrice", orderDetail.getDiscountPrice())
                    .execute();
        });
    }

    public List<OrderDetail> getDetailsByOrderId(int orderId) {
        String sql = """
        SELECT od.*, pv.idProduct, p.title
        FROM orderDetail od
        JOIN product_variants pv ON od.idVariant = pv.idVariant
        JOIN products p ON pv.idProduct = p.idProduct
        WHERE od.idOrder = :orderId
    """;

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("orderId", orderId)
                        .map((rs, ctx) -> {
                            OrderDetail detail = new OrderDetail();
                            detail.setIdOrder(rs.getInt("idOrder"));
                            detail.setIdVariant(rs.getInt("idVariant"));
                            detail.setQuantity(rs.getInt("quantity"));
                            detail.setPrice(rs.getDouble("price"));
                            detail.setDiscountPrice(rs.getDouble("discountPrice"));

                            detail.setNameProduct(rs.getString("title"));

                            return detail;
                        }).list()
        );
    }

}
