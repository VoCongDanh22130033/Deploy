package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import vn.edu.hcmuaf.st.web.entity.Role;
import vn.edu.hcmuaf.st.web.entity.User;
import vn.edu.hcmuaf.st.web.service.AccountService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddStaffController", urlPatterns = "/admin/add-staff")
public class AddStaffController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("user"); // ✅ sửa tại đây

        // Kiểm tra quyền admin
        if (currentUser == null || currentUser.getIdRole() != 1 ) {
            request.getRequestDispatcher("/view-admin/admin-user/403.jsp").forward(request, response);
            return;

        }

        // Log để kiểm tra
        System.out.println("doGet - currentUser: username = " + currentUser.getUsername() + ", idRole = " + currentUser.getIdRole());

        List<Role> roles = accountService.getAllRoles();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/view-admin/admin-user/add-staff.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        User currentUser = (User) request.getSession().getAttribute("user"); // ✅ sửa tại đây

        // Kiểm tra quyền admin
        if (currentUser == null || currentUser.getIdRole() != 1) {
            request.getRequestDispatcher("/view-admin/admin-user/add-staff.jsp").forward(request, response);
            return;
        }


        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");
        int idRole = Integer.parseInt(request.getParameter("idRole"));
        String rawPassword = request.getParameter("password");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setIdRole(idRole);
        user.setPassword(encodedPassword);

        accountService.addStaff(user);

        // Log kiểm tra
        System.out.println("doPost - currentUser: username = " + currentUser.getUsername() + ", idRole = " + currentUser.getIdRole());

        response.sendRedirect(request.getContextPath() + "/admin/staff-list");
    }
}
