package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.st.web.service.OrderService;

import java.io.IOException;

@WebServlet(name="UpdateGhnStatusServlet", urlPatterns = "/admin/update-ghn-status")
public class UpdateGhnStatusServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("idOrder");
        if(idStr == null || !idStr.matches("\\d+")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
            return;
        }
        int idOrder = Integer.parseInt(idStr);
        orderService.refreshOrderStatusFromGhn(idOrder);

        resp.sendRedirect(req.getContextPath() + "/admin/view-order?id=" + idOrder);
    }
}
