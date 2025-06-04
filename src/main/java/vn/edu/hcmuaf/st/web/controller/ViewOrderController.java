package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.st.web.entity.Order;
import vn.edu.hcmuaf.st.web.entity.OrderDetail;
import vn.edu.hcmuaf.st.web.service.OrderDetailService;
import vn.edu.hcmuaf.st.web.service.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "viewOrder", value = "/admin/view-order")
public class ViewOrderController extends HttpServlet {

    private final OrderService orderService = new OrderService();
    private final OrderDetailService orderDetailService = new OrderDetailService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null || !idStr.matches("\\d+")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
            return;
        }

        int id = Integer.parseInt(idStr);

        Order order = orderService.getOrderById(id);
        List<OrderDetail> details = orderDetailService.getOrderDetailsByOrderId(id);

        if (order == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy đơn hàng");
            return;
        }

        req.setAttribute("order", order);
        req.setAttribute("details", details);
        req.getRequestDispatcher("/view-admin/admin-order/view-order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
