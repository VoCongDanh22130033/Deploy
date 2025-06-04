document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".nav-category").forEach(nav => {
        const category = nav.dataset.category;
        const tabs = nav.querySelectorAll(".tab");

        if (tabs.length > 0) {
            tabs[0].classList.add("active");
            showTab(tabs[0].dataset.tab, category);
        }

        tabs.forEach(tab => {
            tab.addEventListener("click", function () {
                tabs.forEach(item => item.classList.remove("active"));
                this.classList.add("active");
                showTab(this.dataset.tab, category);
            });
        });
    });
});

function showTab(tabName, category) {
    // Ẩn tất cả danh mục sản phẩm trong cùng phần (bé trai hoặc bé gái)
    document.querySelectorAll(`.products-category`).forEach(product => {
        if (product.closest(".section-name").querySelector(`.nav-category[data-category="${category}"]`)) {
            product.style.display = "none";
        }
    });

    // Hiển thị danh mục sản phẩm được chọn
    const selectedTab = document.getElementById(tabName);
    if (selectedTab) {
        selectedTab.style.display = "flex";
    }

}

$(document).ready(function () {
    $(".btn-load").click(function () {
        let type = $(this).data("type");
        let category = $(".nav-category[data-category='" + type + "'] .tab.active").data("tab");

        let amount = 0;
        if (type === "discount") {
            amount = $("#" + type + " .product").length; // Lấy tổng số sản phẩm discount
        } else {
            amount = $("#" + type + " ." + category + " .product").length;
        }

        console.log("Load more:", "Type =", type, "Current amount =", amount, "Category =", category);

        $.get("/web/loadmore", { total: amount, type: type, category: category })
            .done(function(data) {
                $("#" + type).append(data);
            })
            .fail(function() {
                alert("Lỗi khi tải thêm sản phẩm!");
            });
    });
});


