package vn.edu.hcmuaf.st.web.controller;

import com.google.gson.Gson;
import vn.edu.hcmuaf.st.web.service.ProductService;
import vn.edu.hcmuaf.st.web.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet({"/detail"})

public class DetailController extends HttpServlet {

    private ProductService productService;

    public void init() throws ServletException {
        productService = new ProductService();  // Gọi Service để xử lý nghiệp vụ
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idRaw = request.getParameter("pid");
        try {
            int id = Integer.parseInt(idRaw);
            Product product = productService.getProductById(id);  // Lấy sản phẩm từ DB
            if (product == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }

            request.setAttribute("product", product);
            request.getRequestDispatcher("/view/view-product/product-detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


}
