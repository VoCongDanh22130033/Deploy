package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.st.web.entity.Size;
import vn.edu.hcmuaf.st.web.service.SizeService;

import java.io.IOException;
import java.util.List;

@WebServlet("/sizes")
public class SizeController extends HttpServlet {

    private SizeService sizeService = new SizeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy danh sách tất cả kích thước từ service
        List<Size> sizes = sizeService.getAllSizes();

        // Đặt danh sách vào request để hiển thị trong JSP
        request.setAttribute("sizes", sizes);

        // Chuyển hướng đến JSP để hiển thị
        request.getRequestDispatcher("/view/view-product/sizes.jsp").forward(request, response);
    }
}