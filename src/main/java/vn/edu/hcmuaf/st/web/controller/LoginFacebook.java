package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/login-fb")
public class LoginFacebook  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Xử lý logic chung cho cả GET và POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        System.out.println(code);

        // Logic lấy token từ Facebook
        Facebook FB = new Facebook();
        String access_token = FB.getToken(code);
        System.out.println(access_token);

        // Kiểm tra nếu access_token hợp lệ (đăng nhập thành công)
        if (access_token != null && !access_token.isEmpty()) {
            // Lấy thông tin người dùng (ví dụ: tên người dùng) từ Facebook thông qua API
            String facebookName = FB.getUsername(access_token); // Giả sử có một phương thức để lấy tên người dùng từ token
            // Lưu tên người dùng vào session
            HttpSession session = request.getSession();
            session.setAttribute("facebookName", facebookName); // Lưu tên người dùng vào session

            // Nếu đăng nhập thành công, chuyển hướng đến trang /home
            response.sendRedirect(request.getContextPath()+"/home"); // Chuyển hướng đến /home
        } else {
            // Nếu không thành công, có thể chuyển hướng đến trang lỗi hoặc đăng nhập lại
            response.sendRedirect("/login-error"); // Chuyển hướng đến trang lỗi
        }
    }
}
