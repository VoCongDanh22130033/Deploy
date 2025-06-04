package vn.edu.hcmuaf.st.web.controller;
import vn.edu.hcmuaf.st.web.entity.Role;
import vn.edu.hcmuaf.st.web.entity.User;
import vn.edu.hcmuaf.st.web.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "EditStaffController", urlPatterns = "/admin/edit-staff")
public class EditStaffController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User currentUser = (User) request.getSession().getAttribute("user");

        // Kiểm tra quyền admin (role = 1)
        if (currentUser == null || currentUser.getIdRole() != 1) {
            request.getRequestDispatcher("/view-admin/admin-user/403.jsp").forward(request, response);
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        User user = accountService.getStaffById(id);
        request.setAttribute("user", user);

        // Giả sử bạn có RoleService hoặc RoleRepository để lấy roles
        List<Role> roles = accountService.getAllRoles();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/view-admin/admin-user/edit-staff.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        User currentUser = (User) request.getSession().getAttribute("user");

        // Kiểm tra quyền admin (role = 1)
        if (currentUser == null || currentUser.getIdRole() != 1) {
            request.getRequestDispatcher("/view-admin/admin-user/403.jsp").forward(request, response);
            return;
        }

        int id = Integer.parseInt(request.getParameter("idUser"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");
        int idRole = Integer.parseInt(request.getParameter("idRole"));

        User user = new User();
        user.setIdUser(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setIdRole(idRole);
        accountService.updateStaff(user);
        response.sendRedirect(request.getContextPath() + "/admin/staff-list");
    }
}




