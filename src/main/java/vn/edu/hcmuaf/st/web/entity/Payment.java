package vn.edu.hcmuaf.st.web.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Payment implements Serializable {

    private int idPayment;
    private Order order;
    private String paymentMethod;
    private String transactionId;
    private double amount;
    private String status;
    private LocalDate createAt;
    private LocalDate updateAt;
    private double amountDelivery = 0; // mới thêm

    public Payment() {
    }

    public Payment(int idPayment, Order order, String paymentMethod, double amount, String status) {
        this.idPayment = idPayment;
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
    }


    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public double getAmountDelivery() {
        return amountDelivery;
    }

    public void setAmountDelivery(double amountDelivery) {
        this.amountDelivery = amountDelivery;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "idPayment=" + idPayment +
                ", order=" + order +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
