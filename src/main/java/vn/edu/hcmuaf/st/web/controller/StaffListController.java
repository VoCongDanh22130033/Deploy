package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.st.web.entity.User;
import vn.edu.hcmuaf.st.web.service.AccountService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "staffListController", urlPatterns = "/admin/staff-list")
public class StaffListController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Integer> roleIds = List.of(0,1);
        List<User> staffList = accountService.getUsersWithRoles(roleIds);
        request.setAttribute("staffList", staffList);
        request.getRequestDispatcher("/view-admin/admin-user/staff-list.jsp").forward(request, response);
    }
}
