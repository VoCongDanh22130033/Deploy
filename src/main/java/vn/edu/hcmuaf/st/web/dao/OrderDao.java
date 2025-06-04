package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Update;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Address;
import vn.edu.hcmuaf.st.web.entity.Order;
import vn.edu.hcmuaf.st.web.entity.User;

import java.time.ZoneId;
import java.util.List;

public class OrderDao {

    private final Jdbi jdbi;

    public OrderDao() {
        this.jdbi = JDBIConnect.get();
    }

    public int insertOrder(Order order) {
        String sql = "INSERT INTO orders (idUser, idAddress, idCoupon, totalPrice, status) " +
                "VALUES (:idUser, :idAddress, :idCoupon, :totalPrice, :status)";

        return jdbi.withHandle(handle -> {
            Update update = handle.createUpdate(sql)
                    .bind("idUser", order.getUser().getIdUser())
                    .bind("idAddress", order.getAddress().getIdAddress())
                    .bind("idCoupon", order.getIdCoupon())
                    .bind("totalPrice", order.getTotalPrice())
                    .bind("status", order.getStatus());

            int orderId = update.executeAndReturnGeneratedKeys("idOrder")
                    .mapTo(Integer.class)
                    .findOne()
                    .orElseThrow(() -> new RuntimeException("Failed to insert order"));

            return orderId;
        });
    }

    public Order getOrderById(int idOrder) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT 
                                        o.idOrder, o.totalPrice, o.status, o.createAt,
                                        u.idUser, u.fullName, u.email,
                                        a.idAddress, a.address, a.ward, a.district, a.province
                                    FROM orders o
                                    JOIN users u ON o.idUser = u.idUser
                                    JOIN address a ON o.idAddress = a.idAddress
                                    WHERE o.idOrder = :idOrder
                                """)
                        .bind("idOrder", idOrder)
                        .map((rs, ctx) -> {
                            Order order = new Order();
                            order.setIdOrder(rs.getInt("idOrder"));
                            order.setTotalPrice(rs.getDouble("totalPrice"));
                            order.setStatus(rs.getString("status"));
                            order.setCreatedAt(rs.getTimestamp("createAt").toLocalDateTime());

                            // User
                            User user = new User();
                            user.setIdUser(rs.getInt("idUser"));
                            user.setFullName(rs.getString("fullName"));
                            user.setEmail(rs.getString("email"));
                            order.setUser(user);

                            // Address
                            Address address = new Address();
                            address.setIdAddress(rs.getInt("idAddress"));
                            address.setAddress(rs.getString("address"));
                            address.setWard(rs.getString("ward"));
                            address.setDistrict(rs.getString("district"));
                            address.setProvince(rs.getString("province"));
                            order.setAddress(address);

                            return order;
                        })
                        .findOne()
                        .orElse(null)
        );
    }

    public List<Order> getAllOrders() {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                SELECT 
                    o.idOrder, o.totalPrice, o.status, o.createAt,
                    u.idUser, u.username, u.fullName, u.email, u.phoneNumber,
                    a.idAddress, a.address, a.ward, a.district, a.province
                FROM orders o
                JOIN users u ON o.idUser = u.idUser
                JOIN address a ON o.idAddress = a.idAddress
                ORDER BY o.createAt DESC
            """)
                        .map((rs, ctx) -> {
                            Order order = new Order();
                            order.setIdOrder(rs.getInt("idOrder"));
                            order.setTotalPrice(rs.getDouble("totalPrice"));
                            order.setStatus(rs.getString("status"));
                            //order.setCreatedAt(rs.getTimestamp("createAt").toLocalDateTime());
                            order.setCreatedAt(rs.getTimestamp("createAt").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

                            User user = new User();
                            user.setIdUser(rs.getInt("idUser"));
                            user.setUsername(rs.getString("username"));
                            user.setFullName(rs.getString("fullName"));
                            user.setEmail(rs.getString("email"));
                            user.setPhoneNumber(rs.getString("phoneNumber"));
                            order.setUser(user);

                            Address address = new Address();
                            address.setIdAddress(rs.getInt("idAddress"));
                            address.setAddress(rs.getString("address"));
                            address.setWard(rs.getString("ward"));
                            address.setDistrict(rs.getString("district"));
                            address.setProvince(rs.getString("province"));
                            order.setAddress(address);

                            return order;
                        })
                        .list()
        );
    }

    public void updateStatus(int idOrder, String status) {
        String sql = "UPDATE orders SET status = :status WHERE idOrder = :idOrder";
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("status", status)
                        .bind("idOrder", idOrder)
                        .execute()
        );
    }

    public void updateGhnOrderCode(int idOrder, String ghnOrderCode) {
        String sql = "UPDATE orders SET ghnOrderCode = :ghnOrderCode WHERE idOrder = :idOrder";
        jdbi.useHandle(handle -> handle.createUpdate(sql)
                .bind("ghnOrderCode", ghnOrderCode)
                .bind("idOrder", idOrder)
                .execute()
        );
    }

    public void updateGhnStatus(int idOrder, String ghnStatus) {
        String sql = "UPDATE orders SET ghnStatus = :ghnStatus, lastUpdateStatus = NOW() WHERE idOrder = :idOrder";
        jdbi.useHandle(handle -> handle.createUpdate(sql)
                .bind("ghnStatus", ghnStatus)
                .bind("idOrder", idOrder)
                .execute()
        );
    }

    public static void main(String[] args) {
        OrderDao dao = new OrderDao();
        List<Order> orders = dao.getAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

}
