package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.ProductDao;
import vn.edu.hcmuaf.st.web.entity.Product;


import java.util.List;

public class ProductService {

    private final ProductDao productDao;

    public ProductService() {
        this.productDao = new ProductDao();
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public Product getProductById(int idProduct) {
        return productDao.getById(idProduct);
    }

    public Product getProductByIdVariant(int idVariant) {
        return productDao.getByIdVariant(idVariant);
    }

    public boolean addProduct(Product product) {
        return productDao.add(product);
    }

    public boolean updateProduct(Product product) {
        return productDao.update(product);
    }

    public boolean deleteProduct(int id) {
        return productDao.delete(id);
    }

    public List<Product> getTop8ProductsHasDiscount() {
        return productDao.getProductsHasDiscount(8, 0);
    }

    public List<Product> getNextTop8ProductsHasDiscount(int offset) {
        return productDao.getProductsHasDiscount(8, offset);
    }

    public List<Product> getTop8ProductsByCategory(int categoryId) {
        return productDao.getProductsByCategory(categoryId, 8, 0);
    }

    public List<Product> getNextTop8ProductsByCategory(int categoryId, int offset) {
        return productDao.getProductsByCategory(categoryId, 8, offset);
    }


    public List<Product> getProductsByPage(int page, int pageSize) {
        int totalProducts = getTotalProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        if (page > totalPages) {
            page = totalPages; // Đảm bảo rằng page không vượt quá tổng số trang
        }

        int offset = (page - 1) * pageSize;
        return productDao.getProducts(offset, pageSize);
    }


    public int getTotalProducts() {
        return productDao.getNumberOfRecords();
    }

    // Phương thức lấy sản phẩm theo idCategory từ 1 đến 4 (đồ bé trai)
    public List<Product> getProductsByCategoryRange(int idCategory, int boy_or_girl, int offset, int pageSize) {
        // Kiểm tra boy_or_girl hợp lệ (chỉ nhận 1 hoặc 2)
        if (boy_or_girl != 1 && boy_or_girl != 2) {
            throw new IllegalArgumentException("Invalid boy_or_girl value. Must be 1 or 2.");
        }

        // Kiểm tra idCategory hợp lệ (từ 1 đến 8 hoặc 0 để lấy tất cả danh mục)
        if (idCategory != 0 && (idCategory < 1 || idCategory > 8)) {
            throw new IllegalArgumentException("Invalid category id. Category must be between 1 and 8, or 0 for all categories.");
        }

        // Gọi DAO để lấy danh sách sản phẩm (chấp nhận idCategory = 0)
        return productDao.getProductsByCategoryRange(idCategory, boy_or_girl, offset, pageSize);
    }

    // Phương thức lấy tổng số sản phẩm theo idCategory từ 1 đến 4
    public int getTotalProductsByCategoryRange(int idCategory, int boy_or_girl) {
        // Kiểm tra giá trị hợp lệ của boy_or_girl
        if (boy_or_girl != 1 && boy_or_girl != 2) {
            throw new IllegalArgumentException("Invalid boy_or_girl value. Must be 1 or 2.");
        }

        // Nếu idCategory = 0, lấy tổng sản phẩm không phân biệt danh mục
        if (idCategory == 0) {
            return productDao.getTotalProductsByCategoryRange(0, boy_or_girl);
        }

        // Kiểm tra idCategory hợp lệ (1 đến 8)
        if (idCategory < 1 || idCategory > 8) {
            throw new IllegalArgumentException("Invalid category id. Category must be between 1 and 8, or 0 for all categories.");
        }

        return productDao.getTotalProductsByCategoryRange(idCategory, boy_or_girl);
    }

    public List<Product> getProductsByBoyOrGirl(int boy_or_girl, int offset, int pageSize) {
        // Kiểm tra boy_or_girl hợp lệ (chỉ nhận 1 hoặc 2)
        if (boy_or_girl != 1 && boy_or_girl != 2) {
            throw new IllegalArgumentException("Invalid boy_or_girl value. Must be 1 or 2.");
        }

        // Gọi DAO để lấy danh sách sản phẩm theo boy_or_girl (không cần idCategory)
        return productDao.getProductsByBoyOrGirl(boy_or_girl, offset, pageSize);
    }

    public int getTotalProductsByBoyOrGirl(int boy_or_girl) {
        // Kiểm tra giá trị hợp lệ của boy_or_girl
        if (boy_or_girl != 1 && boy_or_girl != 2) {
            throw new IllegalArgumentException("Invalid boy_or_girl value. Must be 1 or 2.");
        }

        // Gọi DAO để lấy tổng số sản phẩm (không cần category)
        return productDao.getTotalProductsByBoyOrGirl(boy_or_girl);
    }

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product product = productService.getProductByIdVariant(1);
        System.out.println(product);
    }
}
