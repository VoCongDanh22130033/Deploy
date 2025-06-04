package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.st.web.entity.Product;
import vn.edu.hcmuaf.st.web.service.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoadMoreController", urlPatterns = "/loadmore")
public class LoadMoreController extends HttpServlet {

    private final ProductService productService = new ProductService(); // Khởi tạo một lần

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        int amount = Integer.parseInt(req.getParameter("total"));
        String type = req.getParameter("type");
        List<Product> loadMore = new ArrayList<>(); // Khởi tạo danh sách rỗng

        String selectedCategory = req.getParameter("category"); // Lấy danh mục
        System.out.println("Loading more products: type=" + type + ", category=" + selectedCategory + ", offset=" + amount); // Debug


        if(type.equals("discount")) {
            loadMore = productService.getNextTop8ProductsHasDiscount(amount);
        } else if (selectedCategory != null) { // Chỉ kiểm tra category nếu không phải discount
            switch (selectedCategory) {
                case "ao-boy":
                    loadMore = productService.getNextTop8ProductsByCategory(1, amount);
                    break;
                case "quan-boy":
                    loadMore = productService.getNextTop8ProductsByCategory(2, amount);
                    break;
                case "giay-boy":
                    loadMore = productService.getNextTop8ProductsByCategory(3, amount);
                    break;
                case "do-bo-boy":
                    loadMore = productService.getNextTop8ProductsByCategory(4, amount);
                    break;
                case "ao-girl":
                    loadMore = productService.getNextTop8ProductsByCategory(5, amount);
                    break;
                case "quan-girl":
                    loadMore = productService.getNextTop8ProductsByCategory(6, amount);
                    break;
                case "vay-girl":
                    loadMore = productService.getNextTop8ProductsByCategory(7, amount);
                    break;
                case "do-bo-girl":
                    loadMore = productService.getNextTop8ProductsByCategory(8, amount);
                    break;
                default:
                    loadMore = new ArrayList<>();
            }
        }


        PrintWriter out = resp.getWriter();

        for(Product product : loadMore){
            out.println("<div class=\"product col-md-3 gap-3 mb-5\">\n" +
                    "    <div class=\"product-card\">\n" +
                    "        <div class=\"badge-custome\">Hot</div>\n" +
                    "        <div class=\"product-tumb\">\n" +
                    "            <img src=\"" + product.getProductImages().get(0).getImageUrl() + "\" alt=\"" + product.getTitle() + "\">\n" +
                    "        </div>\n" +
                    "        <div class=\"product-details\">\n" +
                    "            <span class=\"product-catagory\">" + product.getCategory().getName() + "</span>\n" +
                    "            <h4><a href=\"#\">" + product.getTitle() + "</a></h4>\n" +
                    "            <div class=\"product-bottom-details\">\n" +
                    "                <div class=\"product-price\">\n" +
                    "                    <small class=\"ori-price\" style=\"text-decoration: line-through;\">\n" +
                    "                        " + String.format("%,.0f đ", product.getPrice()) + "\n" +
                    "                    </small>\n" +
                    "                    <p class=\"dis-price\">\n" +
                    "                        " + String.format("%,.0f đ", product.getFinalPrice()) + "\n" +
                    "                    </p>\n" +
                    "                </div>\n" +
                    "                <div class=\"product-links\">\n" +
                    "                    <a href=\"#\"><i class=\"fa-solid fa-eye\"></i></a>\n" +
                    "                    <a href=\"#\"><i class=\"fa fa-shopping-cart\"></i></a>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>");
        }

    }

}
