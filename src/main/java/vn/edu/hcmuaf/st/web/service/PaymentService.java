package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.PaymentDao;
import vn.edu.hcmuaf.st.web.entity.Payment;

public class PaymentService {

    private final PaymentDao paymentDao;

    public PaymentService() {
        this.paymentDao = new PaymentDao();
    }

    public void createPayment(Payment payment) {
        paymentDao.insert(payment);
    }

    public Payment getPaymentByOrderId(int orderId) {
        return paymentDao.getPaymentByOrderId(orderId);
    }
}
