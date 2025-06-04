package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.st.web.entity.Product;
import vn.edu.hcmuaf.st.web.service.ProductService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "homeController", urlPatterns = "/home")
public class HomeController extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        // Lưu URL hiện tại vào session để tiêps tục mua hàng
        String prevUrl = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        request.getSession().setAttribute("prevUrl", prevUrl);

        // Lấy danh sách sản phẩm
        List<Product> products = productService.getAllProducts();

        // Lấy danh sách sản phẩm theo category
        Map<String, List<Product>> productsByCategory1 = getProductsByCategoryBoy(productService);
        Map<String, List<Product>> productsByCategory2 = getProductsByCategoryGirl(productService);

        //lấy danh sách sản phẩm có giảm giá
        List<Product> productsTop8HasDisount = getTop8ProductsHasDiscount(productService);
        request.setAttribute("products", products);
        request.setAttribute("productsByCategory1", productsByCategory1);
        request.setAttribute("productsByCategory2", productsByCategory2);
        request.setAttribute("productsTop8HasDisount", productsTop8HasDisount);
        request.getRequestDispatcher("/view/view-index/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("/view/view-index/index.jsp");
        rd.forward(request, response);
    }

    public Map<String, List<Product>> getProductsByCategoryBoy(ProductService productService) {
        Map<String, List<Product>> productsByCategory = new HashMap<>();

        productsByCategory.put("ao-boy", productService.getTop8ProductsByCategory(1));
        productsByCategory.put("quan-boy", productService.getTop8ProductsByCategory(2));
        productsByCategory.put("giay-boy", productService.getTop8ProductsByCategory(3));
        productsByCategory.put("do-bo-boy", productService.getTop8ProductsByCategory(4));

        return productsByCategory;
    }

    public Map<String, List<Product>> getProductsByCategoryGirl(ProductService productService) {
        Map<String, List<Product>> productsByCategory = new HashMap<>();

        productsByCategory.put("ao-girl", productService.getTop8ProductsByCategory(5));
        productsByCategory.put("quan-girl", productService.getTop8ProductsByCategory(6));
        productsByCategory.put("vay-girl", productService.getTop8ProductsByCategory(7));
        productsByCategory.put("do-bo-girl", productService.getTop8ProductsByCategory(8));

        return productsByCategory;
    }

    public List<Product> getTop8ProductsHasDiscount(ProductService productService) {
        return productService.getTop8ProductsHasDiscount();
    }

}
