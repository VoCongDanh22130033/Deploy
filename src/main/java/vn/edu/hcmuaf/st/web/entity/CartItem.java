package vn.edu.hcmuaf.st.web.entity;

import java.io.Serializable;
import java.util.List;

public class CartItem implements Serializable {

    private int idVariant;
    private String productTitle;
    private Size size;
    private Color color;
    private double price;
    private double discountPrice;
    private int quantity;
    private String imageUrl;
    private List<Color> availableColors; // Danh sách màu có sẵn
    private List<Size> availableSizes;   // Danh sách size có sẵn

    public CartItem() {
    }

    public CartItem(int idVariant, String productTitle, Size size, Color color, double price, double discountPrice, int quantity, String imageUrl) {
        this.idVariant = idVariant;
        this.productTitle = productTitle;
        this.size = size;
        this.color = color;
        this.price = price;
        this.discountPrice = discountPrice;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public int getIdVariant() {
        return idVariant;
    }

    public void setIdVariant(int idVariant) {
        this.idVariant = idVariant;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Color> getAvailableColors() {
        return availableColors;
    }

    public void setAvailableColors(List<Color> availableColors) {
        this.availableColors = availableColors;
    }

    public List<Size> getAvailableSizes() {
        return availableSizes;
    }

    public void setAvailableSizes(List<Size> availableSizes) {
        this.availableSizes = availableSizes;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "idVariant=" + idVariant +
                ", productTitle='" + productTitle + '\'' +
                ", size=" + size +
                ", color=" + color +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
