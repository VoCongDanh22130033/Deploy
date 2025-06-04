$(document).ready(function () {
    $(".menu-link").click(function () {
        // Xóa class 'active' khỏi tất cả menu items
        $(".menu-link").removeClass("active");

        // Thêm class 'active' cho phần tử được click
        $(".menu-link").addClass("active");
    });
});
