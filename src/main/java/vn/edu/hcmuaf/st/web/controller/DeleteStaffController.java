package vn.edu.hcmuaf.st.web.controller;

import vn.edu.hcmuaf.st.web.service.AccountService;
import vn.edu.hcmuaf.st.web.entity.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet(name = "DeleteStaffController", urlPatterns = "/admin/delete-staff")
public class DeleteStaffController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Lấy người dùng hiện tại từ session
        User currentUser = (User) request.getSession().getAttribute("user");

        // Kiểm tra quyền admin
        if (currentUser == null || currentUser.getIdRole() != 1) {
            // Không có quyền thì chuyển đến trang 403
            request.getRequestDispatcher("/view-admin/admin-user/403.jsp").forward(request, response);
            return;
        }

        // Nếu có quyền thì tiếp tục xóa
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            accountService.deleteUserById(id);

            // Log để kiểm tra
            System.out.println("deleteStaff - currentUser: username = " + currentUser.getUsername() + ", idRole = " + currentUser.getIdRole());

            response.sendRedirect(request.getContextPath() + "/admin/staff-list");
        } catch (NumberFormatException e) {
            // Nếu id không hợp lệ
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid staff ID.");
        }
    }
}
