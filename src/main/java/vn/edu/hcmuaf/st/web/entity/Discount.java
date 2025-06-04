package vn.edu.hcmuaf.st.web.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Discount implements Serializable {

    private int idDiscount;
    private double discountAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Discount() {}

    public Discount(int idDiscount, double discountAmount, LocalDateTime startDate) {
        this.idDiscount = idDiscount;
        this.discountAmount = discountAmount;
        this.startDate = startDate;
    }

    public Discount(int idDiscount, double discountAmount, LocalDateTime startDate, LocalDateTime endDate) {
        this.idDiscount = idDiscount;
        this.discountAmount = discountAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Get & Set
    public int getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "idDiscount=" + idDiscount +
                ", discountAmount=" + discountAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
