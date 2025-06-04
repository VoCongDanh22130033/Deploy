package vn.edu.hcmuaf.st.web.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AutoCompleteOrderServlet", urlPatterns = "/admin/auto-complete-orders")
public class AutoCompleteOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new AutoCompleteOrderTask().run();
        resp.setContentType("text/plain");
        resp.getWriter().write("Đã xử lý tự động hoàn thành đơn hàng.");
    }
}
