package vn.edu.hcmuaf.st.web.controller;

import vn.edu.hcmuaf.st.web.entity.Order;
import vn.edu.hcmuaf.st.web.service.OrderService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AutoCompleteOrderTask {

    private final OrderService orderService = new OrderService();

    public void run() {
        List<Order> pendingOrders = orderService.getAllOrders().stream()
                .filter(o -> "Đang giao".equals(o.getStatus()))
                .filter(o -> ChronoUnit.DAYS.between(o.getCreatedAt(), LocalDateTime.now()) >= 1) // X ngày (ví dụ: 7)
                .toList();

        for (Order o : pendingOrders) {
            orderService.updateOrderStatus(o.getIdOrder(), "Hoàn thành");
            System.out.println("✔ Đã tự động hoàn thành đơn #" + o.getIdOrder());
        }
    }

}
