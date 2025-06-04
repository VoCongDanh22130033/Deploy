package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.OrderDetailDao;
import vn.edu.hcmuaf.st.web.entity.OrderDetail;

import java.util.List;

public class OrderDetailService {

    private final OrderDetailDao orderDetailDao;

    public OrderDetailService() {
        this.orderDetailDao = new OrderDetailDao();
    }

    public void createOrderDetail(OrderDetail orderDetail) {
        orderDetailDao.insert(orderDetail);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        return orderDetailDao.getDetailsByOrderId(orderId);
    }

}
