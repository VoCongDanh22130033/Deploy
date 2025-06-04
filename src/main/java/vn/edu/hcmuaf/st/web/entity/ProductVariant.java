package vn.edu.hcmuaf.st.web.entity;


import java.io.Serializable;

public class ProductVariant implements Serializable {

    private int idvariant;
    private int idProduct;
    private Size size;
    private Color color;
    private int stockQuantity;

    public ProductVariant() {
    }

    public ProductVariant(int idvariant, int idProduct, Size size, Color color, int stockQuantity) {
        this.idvariant = idvariant;
        this.idProduct = idProduct;
        this.size = size;
        this.color = color;
        this.stockQuantity = stockQuantity;
    }

    //Get & Set
    public int getIdvariant() {
        return idvariant;
    }

    public void setIdvariant(int idvariant) {
        this.idvariant = idvariant;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "ProductVariant{" +
                "idvariant=" + idvariant +
                ", idProduct=" + idProduct +
                ", size=" + size +
                ", color=" + color +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
