package vn.edu.hcmuaf.st.web.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import vn.edu.hcmuaf.st.web.entity.ProductImage;

public class Product implements Serializable {

    private int idProduct;
    private Category category;
    private Discount discount;
    private String title;
    private double price;
    private String description;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ProductImage> productImages = new ArrayList<>();
    private List<ProductVariant> productVariants = new ArrayList<>();
    private int boy_or_girl;


    public Product(int idProduct, String title, double price, double discountAmount, double priceAfterDiscount) {
    }



    public Product() {
    }

    public Product(int idProduct, String title, double price, String imageUrl) {
        this.idProduct = idProduct;
        this.title = title;
        this.price = price;
        this.productImages = new ArrayList<>();
        this.productImages.add(new ProductImage(imageUrl)); // Giả sử ProductImage có constructor nhận imageUrl
    }
    public Product(int idProduct, Category category, Discount discount, String title, double price, String description, boolean status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idProduct = idProduct;
        this.category = category;
        this.discount = discount;
        this.title = title;
        this.price = price;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Product(int idProduct, Category category, Discount discount, String title, double price, String description, boolean status) {
        this.idProduct = idProduct;
        this.category = category;
        this.discount = discount;
        this.title = title;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    public void addVariant(ProductVariant productVariant) {
        productVariants.add(productVariant);
    }

    public boolean hasDiscount() {
        return discount != null && discount.getDiscountAmount() > 0;
    }

    public double getFinalPrice() {
        if (hasDiscount()) {
            return price * (1 - discount.getDiscountAmount() / 100.0);
        }
        return price;
    }

    public List<Color> getColors() {
        List<Color> colors = new ArrayList<>();
        for(ProductVariant productVariant : productVariants) {
            colors.add(productVariant.getColor());
        }
        return colors;
    }

    public List<Size> getSizes() {
        List<Size> sizes = new ArrayList<>();
        for(ProductVariant productVariant : productVariants) {
            sizes.add(productVariant.getSize());
        }
        return sizes;
    }

    public int getBoy_or_girl() {
        return boy_or_girl;
    }

    public void setBoy_or_girl(int boy_or_girl) {
        this.boy_or_girl = boy_or_girl;
    }


    //Get & Set
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ProductImage> getProductImages() {
        if (productImages == null) {
            productImages = new ArrayList<>();
        }
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<ProductVariant> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<ProductVariant> productVariants) {
        this.productVariants = productVariants;
    }


    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", category=" + category +
                ", discount=" + discount +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", productImages=" + productImages +
                ", productVariants=" + productVariants +
                ", boy_or_girl=" + boy_or_girl +
                '}';
    }




    public List<ProductVariant> filterBySize(Size size) {
        List<ProductVariant> filteredVariants = new ArrayList<>();
        for (ProductVariant variant : productVariants) {
            if (variant.getSize() != null && variant.getSize().equals(size)) {
                filteredVariants.add(variant);
            }
        }
        return filteredVariants;
    }
}
