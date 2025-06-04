package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.st.web.service.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateOrderStatusServlet", urlPatterns = "/admin/update-order-status")
public class UpdateOrderStatusServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("idOrder");
        String newStatus = req.getParameter("status");

        if (idStr == null || newStatus == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu dữ liệu");
            return;
        }

        int idOrder = Integer.parseInt(idStr);

        // Kiểm tra trạng thái hợp lệ
        List<String> validStatuses = List.of("Chờ xác nhận", "Đang giao", "Hoàn thành", "Đã hủy");
        if (!validStatuses.contains(newStatus)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Trạng thái không hợp lệ");
            return;
        }

        // Cập nhật DB
        orderService.updateOrderStatus(idOrder, newStatus);

        // Chuyển hướng về lại trang chi tiết
        resp.sendRedirect(req.getContextPath() + "/admin/view-order?id=" + idOrder);
    }

}
