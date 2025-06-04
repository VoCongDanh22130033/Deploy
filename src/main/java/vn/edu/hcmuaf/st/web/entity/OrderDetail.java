package vn.edu.hcmuaf.st.web.entity;

public class OrderDetail {

    private int idDetail;
    private int idOrder;
    private int idVariant;
    private String nameProduct; //mới thêm vào
    private int quantity;
    private double price;
    private double discountPrice;

    public OrderDetail() {
    }

    public OrderDetail(int idDetail, double discountPrice, double price, int quantity, int idVariant, int idOrder, String nameProduct) {
        this.idDetail = idDetail;
        this.discountPrice = discountPrice;
        this.price = price;
        this.quantity = quantity;
        this.idVariant = idVariant;
        this.idOrder = idOrder;
        this.nameProduct = nameProduct;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdVariant() {
        return idVariant;
    }

    public void setIdVariant(int idVariant) {
        this.idVariant = idVariant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "idDetail=" + idDetail +
                ", idOrder=" + idOrder +
                ", idVariant=" + idVariant +
                ", nameProduct='" + nameProduct + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
