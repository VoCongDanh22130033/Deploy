document.addEventListener("DOMContentLoaded", function () {
    const applyBtn = document.getElementById("applyCoupon");
    const input = document.getElementById("discountCode");
    const notification = document.getElementById("error-notification");

    applyBtn.addEventListener("click", function () {
        const code = input.value.trim();
        if (!code) {
            showMessage("Vui lòng nhập mã giảm giá.");
            return;
        }

        fetch("/web/cart", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                "X-Requested-With": "XMLHttpRequest"
            },
            body: "action=coupon&code=" + encodeURIComponent(code)
        })
            .then(res => {
                if (!res.ok) throw new Error("Lỗi mạng");
                return res.json();
            })
            .then(data => {
                if (data.success) {
                    updatePrice(data.discountAmount, data.finalTotal);
                    showMessage(data.message, true);
                } else {
                    updatePrice(0, data.finalTotal);
                    showMessage(data.message, false);
                }
            })
            .catch(err => {
                console.error("❌ Lỗi khi fetch áp mã:", err);
                showMessage("Có lỗi xảy ra khi áp dụng mã.", false);
            });
    });

    function showMessage(msg, success = false) {
        notification.textContent = msg;
        notification.style.backgroundColor = success ? "#d4edda" : "#f8d7da";
        notification.style.color = success ? "#155724" : "#842029";
        notification.style.borderColor = success ? "#c3e6cb" : "#f5c2c7";
        notification.style.display = "block";
        setTimeout(() => {
            notification.style.display = "none";
        }, 4000);
    }

    function updatePrice(discount, finalTotal) {
        function formatCurrency(amount) {
            return new Intl.NumberFormat("vi-VN", {
                style: "currency",
                currency: "VND"
            }).format(amount);
        }
        document.getElementById("discount-amount").textContent = formatCurrency(discount);
        document.getElementById("final-total").textContent = formatCurrency(finalTotal);
    }
});
