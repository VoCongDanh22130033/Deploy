package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.*;


import java.time.LocalDateTime;
import java.util.*;

public class ProductDao {

    private final Jdbi jdbi;

    public ProductDao() {
        this.jdbi = JDBIConnect.get();
    }

    // lấy tât cả có trong bảng product
    public List<Product> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT 
                                        p.idProduct, p.title, p.price, p.description, p.status,
                                        c.idCategory, c.categoryType, c.name AS categoryName, c.description AS categoryDescription,
                                        d.idDiscount, d.discountAmount, d.startDate, d.endDate,
                                        pi.idImage, pi.imageUrl, pi.`order`
                                    FROM products p
                                    JOIN categories c ON p.idCategory = c.idCategory
                                    LEFT JOIN discount d ON p.idDiscount = d.idDiscount
                                    LEFT JOIN product_images pi ON p.idProduct = pi.idProduct
                                    ORDER BY p.idProduct, pi.`order`
                                """)
                        .reduceRows(new LinkedHashMap<Integer, Product>(), (map, row) -> {
                            int productId = row.getColumn("idProduct", Integer.class);
                            Product product = map.computeIfAbsent(productId, id -> new Product(
                                    id,
                                    new Category(
                                            row.getColumn("idCategory", Integer.class),
                                            row.getColumn("categoryType", String.class),
                                            row.getColumn("categoryName", String.class),
                                            row.getColumn("categoryDescription", String.class)
                                    ),
                                    row.getColumn("idDiscount", Integer.class) != null ? new Discount(
                                            row.getColumn("idDiscount", Integer.class),
                                            row.getColumn("discountAmount", Double.class),
                                            row.getColumn("startDate", LocalDateTime.class),
                                            row.getColumn("endDate", LocalDateTime.class)
                                    ) : null,
                                    row.getColumn("title", String.class),
                                    row.getColumn("price", Double.class),
                                    row.getColumn("description", String.class),
                                    row.getColumn("status", Boolean.class)
                            ));

                            if (row.getColumn("idImage", Integer.class) != null) {
                                // Kiểm tra xem danh sách productImages đã được khởi tạo chưa
                                if (product.getProductImages() == null) {
                                    product.setProductImages(new ArrayList<>());
                                }

                                // Sau khi đảm bảo danh sách không null, thêm sản phẩm vào
                                product.getProductImages().add(new ProductImage(
                                        row.getColumn("idImage", Integer.class),
                                        row.getColumn("imageUrl", String.class),
                                        row.getColumn("order", Integer.class)
                                ));
                            }
                            return map;
                        }).values().stream().toList()

        );
    }

    // lấy theo id
    public Product getById(int idProduct) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT
                                        p.idProduct, p.title, p.price, p.description, p.STATUS, p.createAt, p.updateAt,
                                        c.idCategory, c.categoryType, c.NAME AS categoryName, c.description AS categoryDescription,
                                        d.idDiscount, d.discountAmount, d.startDate, d.endDate,
                                        pi.idImage, pi.imageUrl, pi.`order`
                                    FROM products p
                                    JOIN categories c ON p.idCategory = c.idCategory
                                    LEFT JOIN discount d ON p.idDiscount = d.idDiscount
                                    LEFT JOIN product_images pi ON p.idProduct = pi.idProduct
                                    WHERE p.idProduct = :idProduct
                                    ORDER BY pi.`order`;
                                """)
                        .bind("idProduct", idProduct)
                        .reduceRows(new LinkedHashMap<Integer, Product>(), (map, row) -> {
                            int productId = row.getColumn("idProduct", Integer.class);

                            Product product = map.computeIfAbsent(productId, id -> {
                                // Tạo đối tượng Category
                                Category category = new Category(
                                        row.getColumn("idCategory", Integer.class),
                                        row.getColumn("categoryType", String.class),
                                        row.getColumn("categoryName", String.class),
                                        row.getColumn("categoryDescription", String.class)
                                );

                                // Tạo đối tượng Discount nếu có
                                Discount discount = (row.getColumn("idDiscount", Integer.class) != null) ?
                                        new Discount(
                                                row.getColumn("idDiscount", Integer.class),
                                                row.getColumn("discountAmount", Double.class),
                                                row.getColumn("startDate", LocalDateTime.class),
                                                row.getColumn("endDate", LocalDateTime.class)
                                        ) : null;

                                return new Product(
                                        id,
                                        category,
                                        discount,
                                        row.getColumn("title", String.class),
                                        row.getColumn("price", Double.class),
                                        row.getColumn("description", String.class),
                                        row.getColumn("status", Boolean.class),
                                        row.getColumn("createAt", LocalDateTime.class),
                                        row.getColumn("updateAt", LocalDateTime.class)
                                );
                            });

                            // Thêm ảnh sản phẩm nếu có
                            Integer imageId = row.getColumn("idImage", Integer.class);
                            if (imageId != null) {
                                product.getProductImages().add(new ProductImage(
                                        imageId,
                                        row.getColumn("imageUrl", String.class),
                                        row.getColumn("order", Integer.class)
                                ));
                            }

                            return map;
                        })
                        .values().stream().findFirst().orElse(null)
        );
    }

    public Product getByIdVariant(int idVariant) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT
                                        p.idProduct, p.title, p.price, p.description, p.STATUS, p.createAt, p.updateAt,
                                        c.idCategory, c.categoryType, c.NAME AS categoryName, c.description AS categoryDescription,
                                        d.idDiscount, d.discountAmount, d.startDate, d.endDate,
                                        pi.idImage, pi.imageUrl, pi.`order`
                                    FROM product_variants pv
                                    JOIN products p ON pv.idProduct = p.idProduct
                                    JOIN categories c ON p.idCategory = c.idCategory
                                    LEFT JOIN discount d ON p.idDiscount = d.idDiscount
                                    LEFT JOIN product_images pi ON p.idProduct = pi.idProduct
                                    WHERE pv.idVariant = :idVariant
                                    ORDER BY pi.`order`;
                                """)
                        .bind("idVariant", idVariant)
                        .reduceRows(new LinkedHashMap<Integer, Product>(), (map, row) -> {
                            int productId = row.getColumn("idProduct", Integer.class);

                            Product product = map.computeIfAbsent(productId, id -> {
                                // Tạo đối tượng Category
                                Category category = new Category(
                                        row.getColumn("idCategory", Integer.class),
                                        row.getColumn("categoryType", String.class),
                                        row.getColumn("categoryName", String.class),
                                        row.getColumn("categoryDescription", String.class)
                                );

                                // Tạo đối tượng Discount nếu có
                                Discount discount = (row.getColumn("idDiscount", Integer.class) != null) ?
                                        new Discount(
                                                row.getColumn("idDiscount", Integer.class),
                                                row.getColumn("discountAmount", Double.class),
                                                row.getColumn("startDate", LocalDateTime.class),
                                                row.getColumn("endDate", LocalDateTime.class)
                                        ) : null;

                                return new Product(
                                        id,
                                        category,
                                        discount,
                                        row.getColumn("title", String.class),
                                        row.getColumn("price", Double.class),
                                        row.getColumn("description", String.class),
                                        row.getColumn("status", Boolean.class),
                                        row.getColumn("createAt", LocalDateTime.class),
                                        row.getColumn("updateAt", LocalDateTime.class)
                                );
                            });

                            // Thêm ảnh sản phẩm nếu có
                            Integer imageId = row.getColumn("idImage", Integer.class);
                            if (imageId != null) {
                                product.getProductImages().add(new ProductImage(
                                        imageId,
                                        row.getColumn("imageUrl", String.class),
                                        row.getColumn("order", Integer.class)
                                ));
                            }

                            return map;
                        })
                        .values().stream().findFirst().orElse(null)
        );
    }

    public boolean add(Product product) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO products (idCategory, idDiscount, title, price, description, status, createAt) VALUES (:idCategory, :idDiscount, :title, :price, :description, :status, NOW())")
                        .bind("idCategory", product.getCategory().getIdCategory())
                        .bind("idDiscount", product.getDiscount().getIdDiscount())
                        .bind("title", product.getTitle())
                        .bind("price", product.getPrice())
                        .bind("description", product.getDescription())
                        .bind("status", product.isStatus() ? 1 : 0)  // Convert boolean to tinyint
                        .execute() > 0
        );
    }

    public boolean update(Product product) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE products SET idCategory = :idCategory, idDiscount = :idDiscount, title = :title, price = :price, description = :description, status = :status, updateAt = NOW() WHERE idProduct = :idProduct")
                        .bind("idProduct", product.getIdProduct())
                        .bind("idCategory", product.getCategory().getIdCategory())
                        .bind("idDiscount", product.getDiscount().getIdDiscount())
                        .bind("title", product.getTitle())
                        .bind("price", product.getPrice())
                        .bind("description", product.getDescription())
                        .bind("status", product.isStatus() ? 1 : 0)
                        .execute() > 0
        );
    }

    public boolean delete(int idProduct) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM products WHERE idProduct = :idProduct")
                        .bind("idProduct", idProduct)
                        .execute() > 0
        );
    }

    public List<Product> getProductsHasDiscount(int limit, int offset) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT
                                        p.idProduct, p.title, p.price, p.description, p.status,
                                        c.idCategory, c.categoryType, c.name AS categoryName, c.description AS categoryDescription,
                                        d.idDiscount, d.discountAmount, d.startDate, d.endDate,
                                        pi.idImage, pi.imageUrl, pi.`order`
                                    FROM products p
                                    JOIN categories c ON p.idCategory = c.idCategory
                                    LEFT JOIN discount d ON p.idDiscount = d.idDiscount
                                    LEFT JOIN product_images pi ON p.idProduct = pi.idProduct AND pi.`order` = 1
                                    WHERE p.idDiscount IS NOT NULL AND d.discountAmount > 0
                                    ORDER BY p.idProduct
                                    LIMIT :limit OFFSET :offset
                                """)
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .reduceRows(new LinkedHashMap<Integer, Product>(), (map, row) -> {
                            int productId = row.getColumn("idProduct", Integer.class);

                            Product product = map.computeIfAbsent(productId, id -> {
                                Category category = new Category(
                                        row.getColumn("idCategory", Integer.class),
                                        row.getColumn("categoryType", String.class),
                                        row.getColumn("categoryName", String.class),
                                        row.getColumn("categoryDescription", String.class)
                                );

                                Discount discount = (row.getColumn("idDiscount", Integer.class) != null) ?
                                        new Discount(
                                                row.getColumn("idDiscount", Integer.class),
                                                row.getColumn("discountAmount", Double.class),
                                                row.getColumn("startDate", LocalDateTime.class),
                                                row.getColumn("endDate", LocalDateTime.class)
                                        ) : null;

                                return new Product(
                                        id,
                                        category,
                                        discount,
                                        row.getColumn("title", String.class),
                                        row.getColumn("price", Double.class),
                                        row.getColumn("description", String.class),
                                        row.getColumn("status", Boolean.class)
                                );
                            });

                            // Thêm ảnh sản phẩm nếu có
                            Integer imageId = row.getColumn("idImage", Integer.class);
                            if (imageId != null) {
                                product.getProductImages().add(new ProductImage(
                                        imageId,
                                        row.getColumn("imageUrl", String.class),
                                        row.getColumn("order", Integer.class)
                                ));
                            }

                            return map;
                        })
                        .values().stream().toList()
        );
    }

    public List<Product> getProductsByCategory(int categoryId, int limit, int offset) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT 
                                        p.idProduct, p.title, p.price, p.description, p.status,
                                        c.idCategory, c.categoryType, c.name AS categoryName, c.description AS categoryDescription,
                                        d.idDiscount, d.discountAmount, d.startDate, d.endDate,
                                        pi.idImage, pi.imageUrl, pi.`order`
                                    FROM products p
                                    JOIN categories c ON p.idCategory = c.idCategory
                                    LEFT JOIN discount d ON p.idDiscount = d.idDiscount
                                    LEFT JOIN product_images pi ON p.idProduct = pi.idProduct AND pi.`order` = 1
                                    WHERE p.idCategory = :categoryId
                                    ORDER BY p.idProduct, pi.`order`
                                    LIMIT :limit OFFSET :offset
                                """)
                        .bind("categoryId", categoryId)
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .reduceRows(new LinkedHashMap<Integer, Product>(), (map, rowView) -> {
                            int productId = rowView.getColumn("idProduct", Integer.class);

                            // Nếu sản phẩm chưa tồn tại trong map, tạo mới
                            Product product = map.computeIfAbsent(productId, id -> {
                                Product p = new Product(
                                        id,
                                        new Category(
                                                rowView.getColumn("idCategory", Integer.class),
                                                rowView.getColumn("categoryType", String.class),
                                                rowView.getColumn("categoryName", String.class),
                                                rowView.getColumn("categoryDescription", String.class)
                                        ),
                                        rowView.getColumn("idDiscount", Integer.class) != null ? new Discount(
                                                rowView.getColumn("idDiscount", Integer.class),
                                                rowView.getColumn("discountAmount", Double.class),
                                                rowView.getColumn("startDate", LocalDateTime.class),
                                                rowView.getColumn("endDate", LocalDateTime.class)
                                        ) : null,
                                        rowView.getColumn("title", String.class),
                                        rowView.getColumn("price", Double.class),
                                        rowView.getColumn("description", String.class),
                                        rowView.getColumn("status", Boolean.class)
                                );
                                p.setProductImages(new ArrayList<>());
                                return p;
                            });

                            // Nếu có hình ảnh, thêm vào danh sách hình ảnh của sản phẩm
                            if (rowView.getColumn("idImage", Integer.class) != null) {
                                product.getProductImages().add(new ProductImage(
                                        rowView.getColumn("idImage", Integer.class),
                                        rowView.getColumn("imageUrl", String.class),
                                        rowView.getColumn("order", Integer.class)
                                ));
                            }

                            return map;
                        })
                        .values().stream().toList()

        );
    }

    // đếm tổng sản phẩm trong cơ sở dữ liệu để chia số sản phẩm cho mỗi trang
    public int getNumberOfRecords() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM products")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public List<Product> getProducts(int offset, int pageSize) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT 
                                        p.idProduct, p.title, p.price, p.description, p.status, p.createAt, p.updateAt,
                                        c.idCategory, c.categoryType, c.name AS categoryName, c.description AS categoryDescription,
                                        d.idDiscount, d.discountAmount, d.startDate, d.endDate,
                                        pi.idImage, pi.imageUrl, pi.`order`
                                    FROM products p
                                    JOIN categories c ON p.idCategory = c.idCategory
                                    LEFT JOIN discount d ON p.idDiscount = d.idDiscount
                                    LEFT JOIN product_images pi ON p.idProduct = pi.idProduct AND pi.`order` = 1
                                    ORDER BY p.idProduct
                                    LIMIT :pageSize OFFSET :offset
                                """)
                        .bind("pageSize", pageSize)
                        .bind("offset", offset)
                        .reduceRows(new LinkedHashMap<Integer, Product>(), (map, row) -> {
                            int productId = row.getColumn("idProduct", Integer.class);
                            Product product = map.computeIfAbsent(productId, id -> {
                                // Sử dụng Product constructor đã có sẵn mà không tạo mới mỗi lần
                                return new Product(
                                        id,
                                        new Category(
                                                row.getColumn("idCategory", Integer.class),
                                                row.getColumn("categoryType", String.class),
                                                row.getColumn("categoryName", String.class),
                                                row.getColumn("categoryDescription", String.class)
                                        ),
                                        row.getColumn("idDiscount", Integer.class) != null ? new Discount(
                                                row.getColumn("idDiscount", Integer.class),
                                                row.getColumn("discountAmount", Double.class),
                                                row.getColumn("startDate", LocalDateTime.class),
                                                row.getColumn("endDate", LocalDateTime.class)
                                        ) : null,
                                        row.getColumn("title", String.class),
                                        row.getColumn("price", Double.class),
                                        row.getColumn("description", String.class),
                                        row.getColumn("status", Boolean.class),
                                        row.getColumn("createAt", LocalDateTime.class),
                                        row.getColumn("updateAt", LocalDateTime.class)
                                );
                            });
                            if (row.getColumn("idImage", Integer.class) != null) {
                                List<ProductImage> images = product.getProductImages();
                                if (images == null) {
                                    images = new ArrayList<>();
                                }
                                ProductImage productImage = new ProductImage();  // Tạo đối tượng ProductImage mới bằng constructor mặc định
                                productImage.setIdImage(row.getColumn("idImage", Integer.class));
                                productImage.setProduct(product);  // Gán đối tượng product
                                productImage.setImageUrl(row.getColumn("imageUrl", String.class));
                                productImage.setOrder(row.getColumn("order", Integer.class));
                                images.add(productImage);  // Thêm hình ảnh vào danh sách
                                product.setProductImages(images);  // Cập nhật danh sách hình ảnh cho sản phẩm
                            }
                            return map;
                        }).values().stream().toList()
        );
    }

    public List<Product> getProductsByCategoryRange(Integer idCategory, int boy_or_girl, int offset, int pageSize) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT 
                                        p.idProduct, p.title, p.price, p.description, p.status, p.createAt, p.updateAt,
                                        c.idCategory, c.categoryType, c.name AS categoryName, c.description AS categoryDescription,
                                        d.idDiscount, d.discountAmount, d.startDate, d.endDate,
                                        pi.idImage, pi.imageUrl, pi.`order`
                                    FROM products p
                                    JOIN categories c ON p.idCategory = c.idCategory
                                    LEFT JOIN discount d ON p.idDiscount = d.idDiscount
                                    LEFT JOIN product_images pi ON p.idProduct = pi.idProduct AND pi.`order` = 1
                                    WHERE p.boy_or_girl = :boy_or_girl
                                    AND (:idCategory = 0 OR p.idCategory = :idCategory) -- Không sử dụng NULL
                                    ORDER BY p.createAt DESC
                                    LIMIT :pageSize OFFSET :offset
                                """)
                        .bind("boy_or_girl", boy_or_girl)
                        .bind("idCategory", idCategory)  // Không bind NULL
                        .bind("pageSize", pageSize)
                        .bind("offset", offset)
                        .reduceRows(new LinkedHashMap<Integer, Product>(), (map, row) -> {
                            int productId = row.getColumn("idProduct", Integer.class);
                            Product product = map.computeIfAbsent(productId, id -> new Product(
                                    id,
                                    new Category(
                                            row.getColumn("idCategory", Integer.class),
                                            row.getColumn("categoryType", String.class),
                                            row.getColumn("categoryName", String.class),
                                            row.getColumn("categoryDescription", String.class)
                                    ),
                                    row.getColumn("idDiscount", Integer.class) != null ? new Discount(
                                            row.getColumn("idDiscount", Integer.class),
                                            row.getColumn("discountAmount", Double.class),
                                            row.getColumn("startDate", LocalDateTime.class),
                                            row.getColumn("endDate", LocalDateTime.class)
                                    ) : null,
                                    row.getColumn("title", String.class),
                                    row.getColumn("price", Double.class),
                                    row.getColumn("description", String.class),
                                    row.getColumn("status", Boolean.class),
                                    row.getColumn("createAt", LocalDateTime.class),
                                    row.getColumn("updateAt", LocalDateTime.class)
                            ));

                            if (row.getColumn("idImage", Integer.class) != null) {
                                List<ProductImage> images = product.getProductImages();
                                if (images == null) {
                                    images = new ArrayList<>();
                                }
                                ProductImage productImage = new ProductImage();
                                productImage.setIdImage(row.getColumn("idImage", Integer.class));
                                productImage.setProduct(product);
                                productImage.setImageUrl(row.getColumn("imageUrl", String.class));
                                productImage.setOrder(row.getColumn("order", Integer.class));

                                images.add(productImage);
                                product.setProductImages(images);
                            }

                            return map;
                        })
                        .values()
                        .stream()
                        .toList()
        );
    }

    public List<Product> getProductsByBoyOrGirl(int boy_or_girl, int offset, int pageSize) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT 
                                        p.idProduct, p.title, p.price, p.description, p.status, p.createAt, p.updateAt,
                                        c.idCategory, c.categoryType, c.name AS categoryName, c.description AS categoryDescription,
                                        d.idDiscount, d.discountAmount, d.startDate, d.endDate,
                                        pi.idImage, pi.imageUrl, pi.`order`
                                    FROM products p
                                    JOIN categories c ON p.idCategory = c.idCategory
                                    LEFT JOIN discount d ON p.idDiscount = d.idDiscount
                                    LEFT JOIN product_images pi ON p.idProduct = pi.idProduct AND pi.`order` = 1
                                    WHERE p.boy_or_girl = :boy_or_girl  -- CHẮC CHẮN LỌC THEO boy_or_girl
                                    ORDER BY p.createAt DESC
                                    LIMIT :pageSize OFFSET :offset
                                """)
                        .bind("boy_or_girl", boy_or_girl)  // Đảm bảo bind đúng giá trị
                        .bind("pageSize", pageSize)
                        .bind("offset", offset)
                        .reduceRows(new LinkedHashMap<Integer, Product>(), (map, row) -> {
                            int productId = row.getColumn("idProduct", Integer.class);
                            Product product = map.computeIfAbsent(productId, id -> new Product(
                                    id,
                                    new Category(
                                            row.getColumn("idCategory", Integer.class),
                                            row.getColumn("categoryType", String.class),
                                            row.getColumn("categoryName", String.class),
                                            row.getColumn("categoryDescription", String.class)
                                    ),
                                    row.getColumn("idDiscount", Integer.class) != null ? new Discount(
                                            row.getColumn("idDiscount", Integer.class),
                                            row.getColumn("discountAmount", Double.class),
                                            row.getColumn("startDate", LocalDateTime.class),
                                            row.getColumn("endDate", LocalDateTime.class)
                                    ) : null,
                                    row.getColumn("title", String.class),
                                    row.getColumn("price", Double.class),
                                    row.getColumn("description", String.class),
                                    row.getColumn("status", Boolean.class),
                                    row.getColumn("createAt", LocalDateTime.class),
                                    row.getColumn("updateAt", LocalDateTime.class)
                            ));

                            // Xử lý danh sách hình ảnh
                            if (row.getColumn("idImage", Integer.class) != null) {
                                List<ProductImage> images = product.getProductImages();
                                if (images == null) {
                                    images = new ArrayList<>();
                                }
                                ProductImage productImage = new ProductImage();
                                productImage.setIdImage(row.getColumn("idImage", Integer.class));
                                productImage.setProduct(product);
                                productImage.setImageUrl(row.getColumn("imageUrl", String.class));
                                productImage.setOrder(row.getColumn("order", Integer.class));

                                images.add(productImage);
                                product.setProductImages(images);
                            }

                            return map;
                        })
                        .values()
                        .stream()
                        .toList()
        );
    }

    public int getTotalProductsByBoyOrGirl(int boy_or_girl) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT COUNT(*) FROM products 
                                    WHERE boy_or_girl = :boy_or_girl
                                """)
                        .bind("boy_or_girl", boy_or_girl)
                        .mapTo(Integer.class)
                        .one()
        );
    }

    // Hàm lấy tổng số sản phẩm theo idCategory
    public int getTotalProductsByCategoryRange(int idCategory, int boy_or_girl) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                        SELECT COUNT(*) 
                                        FROM products p
                                        WHERE p.idCategory = :idCategory
                                """)
                        .bind("idCategory", idCategory)
                        .mapTo(int.class)
                        .first()
        );
    }

    // lấy ta
    public List<Product> getAllProducts() {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("""
                                SELECT 
                                    p.idProduct,
                                    p.idCategory,
                                    p.idDiscount,
                                    p.title AS productTitle,
                                    p.price,
                                    p.description AS productDescription,
                                    p.status AS productStatus,
                                    p.createAt,
                                    p.updateAt,
                                    c.categoryType,
                                    c.name AS categoryName,
                                    c.description AS categoryDescription,
                                    d.discountAmount,
                                    d.startDate AS discountStartDate,
                                    d.endDate AS discountEndDate,
                                    s.size AS size,
                                    s.idSize AS idSize,
                                    cl.color AS color,
                                    cl.hexCode AS colorHexCode,
                                    pv.idVariant,  
                                    pv.idProduct AS variantIdProduct,
                                    cl.idColor,
                                    pv.stockQuantity,
                                    pi.idImage,
                                    pi.imageUrl AS productImageUrl,
                                    pi.order AS imageOrder,
                                    p.boy_or_girl  -- Thêm trường boy_or_girl ở đây
                                FROM 
                                    products p
                                LEFT JOIN 
                                    categories c ON p.idCategory = c.idCategory
                                LEFT JOIN 
                                    discount d ON p.idDiscount = d.idDiscount
                                LEFT JOIN 
                                    product_variants pv ON p.idProduct = pv.idProduct
                                LEFT JOIN 
                                    sizes s ON pv.idSize = s.idSize
                                LEFT JOIN 
                                    colors cl ON pv.idColor = cl.idColor
                                LEFT JOIN 
                                    product_images pi ON p.idProduct = pi.idProduct;
                            """)
                    .map((rs, ctx) -> {
                        Product product = new Product();
                        product.setIdProduct(rs.getInt("idProduct"));

                        // Ánh xạ Category
                        Category category = new Category();
                        category.setIdCategory(rs.getInt("idCategory"));
                        category.setCategoryType(rs.getString("categoryType"));
                        category.setName(rs.getString("categoryName"));
                        category.setDescription(rs.getString("categoryDescription"));
                        product.setCategory(category);

                        // Ánh xạ Discount
                        Discount discount = new Discount();
                        discount.setIdDiscount(rs.getInt("idDiscount"));
                        discount.setDiscountAmount(rs.getDouble("discountAmount"));
                        discount.setStartDate(rs.getTimestamp("discountStartDate").toLocalDateTime());
                        if (rs.getTimestamp("discountEndDate") != null) {
                            discount.setEndDate(rs.getTimestamp("discountEndDate").toLocalDateTime());
                        }
                        product.setDiscount(discount);

                        // Các trường khác của Product
                        product.setTitle(rs.getString("productTitle"));
                        product.setPrice(rs.getDouble("price"));
                        product.setDescription(rs.getString("productDescription"));
                        product.setStatus(rs.getBoolean("productStatus"));
                        product.setCreatedAt(rs.getTimestamp("createAt").toLocalDateTime());
                        product.setUpdatedAt(rs.getTimestamp("updateAt").toLocalDateTime());

                        // Ánh xạ Product Variant (Size, Color, Stock Quantity)
                        ProductVariant variant = new ProductVariant();
                        variant.setIdvariant(rs.getInt("idVariant"));
                        variant.setIdProduct(rs.getInt("variantIdProduct"));

                        if (rs.getString("size") != null) {
                            Size size = new Size();
                            size.setSize(rs.getString("size"));
                            size.setIdSize(rs.getInt("idSize"));
                            variant.setSize(size);
                        }
                        if (rs.getString("color") != null) {
                            Color color = new Color();
                            color.setColor(rs.getString("color"));
                            color.setHexcode(rs.getString("colorHexCode"));
                            color.setIdColor(rs.getInt("idColor"));
                            variant.setColor(color);
                        }
                        variant.setStockQuantity(rs.getInt("stockQuantity"));

                        // Thêm variant vào danh sách
                        if (product.getProductVariants() == null) {
                            product.setProductVariants(new ArrayList<>());
                        }
                        product.getProductVariants().add(variant);

                        // Ánh xạ Product Image
                        ProductImage image = new ProductImage();
                        image.setIdImage(rs.getInt("idImage"));
                        image.setImageUrl(rs.getString("productImageUrl"));
                        image.setOrder(rs.getInt("imageOrder"));

                        // Thêm image vào danh sách
                        if (product.getProductImages() == null) {
                            product.setProductImages(new ArrayList<>());
                        }
                        product.getProductImages().add(image);

                        // Ánh xạ boy_or_girl
                        product.setBoy_or_girl(rs.getInt("boy_or_girl"));  // Ánh xạ trường boy_or_girl

                        return product;
                    })
                    .list(); // Trả về danh sách sản phẩm
        });
    }

    public Product getProductById(int idProduct) {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                                    SELECT
                                        p.idProduct, p.title, p.price, p.description, p.status, p.createAt, p.updateAt,
                                        c.idCategory, c.categoryType, c.name AS categoryName, c.description AS categoryDescription,
                                        d.idDiscount, d.discountAmount, d.startDate, d.endDate,
                                        pi.idImage, pi.imageUrl, pi.`order`
                                    FROM products p
                                    JOIN categories c ON p.idCategory = c.idCategory
                                    LEFT JOIN discount d ON p.idDiscount = d.idDiscount
                                    LEFT JOIN product_images pi ON p.idProduct = pi.idProduct             
                                    WHERE p.idProduct = :idProduct
                                """)
                        .bind("idProduct", idProduct)
                        .map((rs, ctx) -> {
                            Product product = new Product();
                            product.setIdProduct(rs.getInt("idProduct"));
                            product.setTitle(rs.getString("title"));
                            product.setPrice(rs.getDouble("price"));
                            product.setDescription(rs.getString("description"));
                            product.setStatus(rs.getBoolean("status"));
                            product.setCreatedAt(rs.getTimestamp("createAt").toLocalDateTime());
                            product.setUpdatedAt(rs.getTimestamp("updateAt").toLocalDateTime());

                            // Danh mục
                            Category category = new Category(
                                    rs.getInt("idCategory"),
                                    rs.getString("categoryType"),
                                    rs.getString("categoryName"),
                                    rs.getString("categoryDescription")
                            );
                            product.setCategory(category);

                            // Giảm giá (nếu có)
                            if (rs.getInt("idDiscount") != 0) {
                                Discount discount = new Discount(
                                        rs.getInt("idDiscount"),
                                        rs.getDouble("discountAmount"),
                                        rs.getTimestamp("startDate").toLocalDateTime(),
                                        rs.getTimestamp("endDate").toLocalDateTime()
                                );
                                product.setDiscount(discount);
                            }

                            // Hình ảnh sản phẩm
                            List<ProductImage> images = new ArrayList<>();
                            if (rs.getInt("idImage") != 0) {
                                ProductImage image = new ProductImage();
                                image.setIdImage(rs.getInt("idImage"));
                                image.setImageUrl(rs.getString("imageUrl"));
                                image.setOrder(rs.getInt("order"));
                                images.add(image);
                            }
                            product.setProductImages(images);

                            return product;
                        })
                        .findOnly()
        );
    }


    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getAllProducts();
        // In các sản phẩm ra console
        products.forEach(product -> {
            System.out.println(product);
        });
    }

}
