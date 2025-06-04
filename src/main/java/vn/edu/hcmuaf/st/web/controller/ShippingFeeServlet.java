package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.st.web.service.GhnService;

import java.io.IOException;

@WebServlet("/api/shipping-fee")
public class ShippingFeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int districtId = Integer.parseInt(req.getParameter("districtId"));
        String wardCode = req.getParameter("wardCode");
        int weight = Integer.parseInt(req.getParameter("weight"));
        int serviceId = Integer.parseInt(req.getParameter("serviceId")); // 👈 nhận thêm serviceId

        try {
            int fee = GhnService.getShippingFee(districtId, wardCode, weight, serviceId); // 👈 truyền thêm serviceId

            resp.setContentType("application/json");
            resp.getWriter().write("{\"fee\": " + fee + "}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Không tính được phí giao hàng\"}");
        }
    }
}
