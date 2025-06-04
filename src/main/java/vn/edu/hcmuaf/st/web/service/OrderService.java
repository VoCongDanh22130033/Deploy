package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.OrderDao;
import vn.edu.hcmuaf.st.web.entity.Order;

import java.util.List;

public class OrderService {

    private final OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public int createOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    public Order getOrderById(int id) {
        return orderDao.getOrderById(id);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public void updateOrderStatus(int idOrder, String status) {
        orderDao.updateStatus(idOrder, status);
    }

    public void refreshOrderStatusFromGhn(int idOrder) {
        Order order = orderDao.getOrderById(idOrder);
        if(order.getGhnOrderCode() == null) {
            System.out.println("Đơn chưa có mã GHN, không thể lấy trạng thái");
            return;
        }
        try {
            String ghnStatus = GhnTrackingService.getGhnOrderStatus(order.getGhnOrderCode());
            orderDao.updateGhnStatus(idOrder, ghnStatus);

            // Map trạng thái GHN sang trạng thái đơn hàng của bạn
            if ("Đã giao".equalsIgnoreCase(ghnStatus) || "Giao thành công".equalsIgnoreCase(ghnStatus)) {
                orderDao.updateStatus(idOrder, "Hoàn thành");
            } else if ("Đang giao".equalsIgnoreCase(ghnStatus)) {
                orderDao.updateStatus(idOrder, "Đang giao");
            }
            // Thêm các trạng thái khác nếu muốn
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGhnOrderCode(int idOrder, String ghnOrderCode) {
        orderDao.updateGhnOrderCode(idOrder, ghnOrderCode);
    }


    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        Order order = orderService.getOrderById(5);
        System.out.println(order);
    }
}
