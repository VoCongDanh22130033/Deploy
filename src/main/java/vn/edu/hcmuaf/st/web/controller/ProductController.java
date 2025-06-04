package vn.edu.hcmuaf.st.web.controller;
import vn.edu.hcmuaf.st.web.service.ProductService;
import vn.edu.hcmuaf.st.web.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@WebServlet({"/all-product", "/product", "/fashion", "/all-boy-or-girl"})
public class ProductController extends HttpServlet {
    private ProductService productService;
    public void init() {
        productService = new ProductService();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        commonSettings(request, response);

        String path = request.getRequestURI(); // Lấy đường dẫn hiện tại

        if (path.endsWith("/all-product")) {
            // Nếu là đường dẫn /all-product, lấy tất cả sản phẩm
            handleAllProducts(request, response);
        } else if (path.endsWith("/product")) {
            // Nếu là đường dẫn /product, xử lý phân trang
            handlePagedProducts(request, response);
        } else if (path.endsWith("/fashion")) {
            handlePagedProductsRange(request, response);
        } else if (path.endsWith("/all-boy-or-girl")) {
            handleAllBoyOrGirl(request, response);
        }
    }

    // Phân trang cho đồ của bé trai ,bé gái
    private void handleAllBoyOrGirl(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy tham số từ request
            String boyOrGirlParam = request.getParameter("boy_or_girl");
            int boy_or_girl = 1; // Mặc định là Nam

            if (boyOrGirlParam != null) {
                try {
                    int parsedValue = Integer.parseInt(boyOrGirlParam);
                    if (parsedValue == 1 || parsedValue == 2) {
                        boy_or_girl = parsedValue; // Chỉ gán nếu hợp lệ
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi chuyển đổi boy_or_girl: " + e.getMessage());
                }
            }

            int page = Optional.ofNullable(request.getParameter("page"))
                    .map(Integer::parseInt)
                    .filter(p -> p > 0)
                    .orElse(1); // Mặc định trang 1

            int pageSize = 9;
            int offset = (page - 1) * pageSize;

            // Gọi service lấy danh sách sản phẩm theo giới tính
            List<Product> productList = productService.getProductsByBoyOrGirl(boy_or_girl, offset, pageSize);

            // Lấy tổng số sản phẩm để tính tổng trang
            int totalProducts = productService.getTotalProductsByBoyOrGirl(boy_or_girl);
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

            // Nếu page > totalPages, đặt về trang cuối
            if (page > totalPages) {
                page = totalPages;
            }

            // Gửi dữ liệu lên JSP
            request.setAttribute("products", productList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("boy_or_girl", boy_or_girl);

        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }

        // Forward đến trang hiển thị sản phẩm (JSP)
        request.getRequestDispatcher("/view/view-product/store.jsp").forward(request, response);
    }

    private void handleAllProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Product> productList = productService.getAllProducts();
            System.out.println("Số sản phẩm lấy được: " + productList.size()); // Debug
            for (Product p : productList) {
                System.out.println(p); // Debug chi tiết
            }

            if (productList == null || productList.isEmpty()) {
                request.setAttribute("message", "Không có sản phẩm nào.");
            } else {
                request.setAttribute("products", productList);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/view/view-product/store.jsp").forward(request, response);
    }

    // phân trang tất cả sản phẩm
    private void handlePagedProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Kiểm tra tham số "page", nếu không có thì mặc định là 1
            String pageParam = request.getParameter("page");
            int page = 1;
            int pageSize = 9;

            if (request.getParameter("page") != null) {
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                    if (page < 1) page = 1; // Đảm bảo không có giá trị âm hoặc 0
                } catch (NumberFormatException e) {
                    page = 1; // Nếu có lỗi, mặc định về trang 1
                }
            }
            // Debug: Kiểm tra các tham số page và pageSize
            System.out.println("Trang: " + page + ", Kích thước trang: " + pageSize);
            List<Product> productList = productService.getProductsByPage(page, pageSize);
            int totalProducts = productService.getTotalProducts(); // Lấy tổng số sản phẩm

            // Debug: Kiểm tra số sản phẩm và tổng số sản phẩm
            System.out.println("Số sản phẩm: " + productList.size());
            System.out.println("Tổng số sản phẩm: " + totalProducts);

            // Tính số trang (totalPages)
            // Tính toán tổng số trang
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

            // Giới hạn giá trị của `page` để không vượt quá tổng số trang
            if (page > totalPages) {
                page = totalPages; // Nếu `page` lớn hơn số trang, chỉnh lại `page` là số trang cuối
            }

            // Gửi dữ liệu đến JSP
            request.setAttribute("products", productList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);


        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("/view/view-product/store.jsp").forward(request, response);
    }

    // utf - 8 để tránh lỗi hiển thị chữ
    private void commonSettings(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    }

    private void handlePagedProductsRange(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy tham số "page" và kiểm tra giá trị hợp lệ
            int page = Optional.ofNullable(request.getParameter("page"))
                    .map(Integer::parseInt)
                    .filter(p -> p > 0)
                    .orElse(1); // Default là trang 1 nếu không có

            int pageSize = 9;

            // Lấy "idCategory", cho phép "0" để lấy tất cả danh mục
            Integer idCategory = Optional.ofNullable(request.getParameter("idCategory"))
                    .map(Integer::parseInt)
                    .filter(id -> id == 0 || (id >= 1 && id <= 8)) // Cho phép idCategory = 0 để lấy tất cả sản phẩm
                    .orElse(0); // Default là 0 để lấy tất cả danh mục

            // Lấy "boy_or_girl" (1: Nam, 2: Nữ)
            int boy_or_girl = Optional.ofNullable(request.getParameter("boy_or_girl"))
                    .map(Integer::parseInt)
                    .filter(bg -> bg == 1 || bg == 2)
                    .orElse(1); // Default là 1 (Nam)

            // Tính toán offset cho phân trang
            int offset = (page - 1) * pageSize;

            // Lấy danh sách sản phẩm (cho phép idCategory = 0 để lấy tất cả)
            List<Product> productList = productService.getProductsByCategoryRange(idCategory, boy_or_girl, offset, pageSize);

            // Lấy tổng số sản phẩm
            int totalProducts = productService.getTotalProductsByCategoryRange(idCategory, boy_or_girl);

            // Tính số trang
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

            // Nếu `page` lớn hơn tổng số trang, đặt về trang cuối
            if (page > totalPages) {
                page = totalPages;
            }

            // Đưa dữ liệu lên JSP
            request.setAttribute("products", productList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("idCategory", idCategory);
            request.setAttribute("boy_or_girl", boy_or_girl);

        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }

        // Forward đến JSP
        request.getRequestDispatcher("/view/view-product/store.jsp").forward(request, response);
    }

}
