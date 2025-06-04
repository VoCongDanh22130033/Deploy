package vn.edu.hcmuaf.st.web.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Order {

    private int idOrder;
    private User user;
    private Address address;
    private Integer idCoupon;
    private double totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private double amountDelivery;  // Phí vận chuyển
    private double finalPrice;      // Tổng thanh toán (đã cộng phí vận chuyển và trừ giảm giá nếu có)

    // Thêm các trường sau để lưu thông tin GHN
    private String ghnOrderCode;    // Mã vận đơn GHN
    private String ghnStatus;       // Trạng thái vận chuyển GHN
    private LocalDateTime lastUpdateStatus; // Thời điểm cập nhật trạng thái GHN cuối cùng

    public Order() {

    }

    public Order(int idOrder, User user, Address address, double totalPrice, String status) {
        this.idOrder = idOrder;
        this.user = user;
        this.address = address;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getter và Setter cho ghnOrderCode
    public String getGhnOrderCode() {
        return ghnOrderCode;
    }

    public void setGhnOrderCode(String ghnOrderCode) {
        this.ghnOrderCode = ghnOrderCode;
    }

    // Getter và Setter cho ghnStatus
    public String getGhnStatus() {
        return ghnStatus;
    }

    public void setGhnStatus(String ghnStatus) {
        this.ghnStatus = ghnStatus;
    }

    // Getter và Setter cho lastUpdateStatus
    public LocalDateTime getLastUpdateStatus() {
        return lastUpdateStatus;
    }

    public void setLastUpdateStatus(LocalDateTime lastUpdateStatus) {
        this.lastUpdateStatus = lastUpdateStatus;
    }

    public Date getCreatedAtAsDate() {
        return Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getAmountDelivery() {
        return amountDelivery;
    }

    public void setAmountDelivery(double amountDelivery) {
        this.amountDelivery = amountDelivery;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getIdCoupon() {
        return idCoupon;
    }

    public void setIdCoupon(Integer idCoupon) {
        this.idCoupon = idCoupon;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", user=" + user +
                ", address=" + address +
                ", idCoupon=" + idCoupon +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", amountDelivery=" + amountDelivery +
                ", finalPrice=" + finalPrice +
                '}';
    }
    private Discount discount;

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setDiscountAmount(double discount) {
    }


}
