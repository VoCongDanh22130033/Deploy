package vn.edu.hcmuaf.st.web.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import vn.edu.hcmuaf.st.web.entity.Address;
import vn.edu.hcmuaf.st.web.entity.GoogleAccount;
import vn.edu.hcmuaf.st.web.entity.User;
import vn.edu.hcmuaf.st.web.service.AccountService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.text.SimpleDateFormat;
import java.util.Random;

@WebServlet(urlPatterns = {"/sign", "/register", "/forgot-password", "/enter-otp", "/login", "/reset-password", "/logout", "/profile"})
public class AccountController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        commonSettings(request, response);
        String action = request.getServletPath();
        switch (action) {
            case "/register":
                request.getRequestDispatcher("/view/view-account/register.jsp").forward(request, response);
                break;
            case "/forgot-password":
                request.getRequestDispatcher("/view/view-account/forgot-password.jsp").forward(request, response);
                break;
            case "/enter-otp":
                request.getRequestDispatcher("/view/view-account/enter-otp.jsp").forward(request, response);
                break;
            case "/sign":
                String captchaText = generateCaptchaText(6);
                request.getSession().setAttribute("captcha", captchaText);
                request.setAttribute("captchaText", captchaText);
                request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
                break;
            case "/login":
                handleGoogleLogin(request, response);
                break;
            case "/reset-password":
                request.getRequestDispatcher("/view/view-account/reset-password.jsp").forward(request, response);
                break;
            case "/logout":
                handleLogout(request, response);
                break;
            case "/profile":
                viewProfile(request, response);  // Hiển thị thông tin người dùng
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        commonSettings(request, response);
        String action = request.getServletPath();
        switch (action) {
            case "/register":
                handleRegister(request, response);
                break;
            case "/sign":
                handleLogin(request, response);
                break;
            case "/forgot-password":
                handleForgotPassword(request, response);
                break;
            case "/enter-otp":
                handleOtpValidation(request, response);
                break;
            case "/reset-password":
                handleResetPassword(request, response);
                break;
            case "/logout":
                handleLogout(request, response);
                break;
            case "/profile":
                handleProfileUpdate(request, response);
                break;
            default:
                response.sendRedirect("/sign");
                break;
        }
    }

    // UTF8
    private void commonSettings(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    }

    // Mã Capcha
    private String generateCaptchaText(int length) {
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789@#$%";
        Random rand = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < length; i++) {
            captcha.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return captcha.toString();
    }
    // làm mới capcha
    private void setCaptchaForRequest(HttpServletRequest request) {
        String captchaText = generateCaptchaText(6);
        request.getSession().setAttribute("captcha", captchaText);
        request.setAttribute("captchaText", captchaText);
    }
    //Đăng Nhập
    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captchaInput = request.getParameter("captchaInput");
        String captchaSession = (String) request.getSession().getAttribute("captcha");


        // Luôn tạo captcha mới mỗi lần vào xử lý login
        setCaptchaForRequest(request);

        // Kiểm tra mã xác nhận
        if (captchaSession == null || !captchaSession.equalsIgnoreCase(captchaInput)) {
            request.setAttribute("error", "Mã xác nhận không đúng!");
            request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
            return;
        }

        // Kiểm tra username và password
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
            return;
        }

        User user = accountService.getUserByUsername(username);

        if (user == null) {
            request.setAttribute("error", "Tài khoản không tồn tại!");
            request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
            return;
        }

        // Mở khóa nếu đã hết thời gian khóa
        accountService.unlockUserIfTimePassed(username);

        // Kiểm tra xem tài khoản có đang bị khóa tạm thời không
        if (accountService.isUserLocked(username)) {
            request.setAttribute("error", "Tài khoản đang bị khóa! Vui lòng thử lại sau 1 phút.");
            request.setAttribute("isLocked", true);  // Gửi cờ về JSP để disable input
            request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
            return;
        }


        if (accountService.checkLogin(username, password)) {
            // Đăng nhập thành công: reset số lần sai và xóa trạng thái khóa
            accountService.resetLoginAttempts(username);
            accountService.unlockUser(username);

            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("fullname", user.getFullName());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("phoneNumber", user.getPhoneNumber());
            session.setAttribute("birthDate", user.getBirthDate());
            session.setAttribute("image", user.getImage());
            session.setAttribute("user", user);

            System.out.println(">> Logged in user ID = " + user.getIdUser());
            System.out.println(">> Logged in Role = " + user.getIdRole());

            int idRole = user.getIdRole();
            if (idRole == 1 || idRole ==0) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else if (idRole == 2) {
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                request.setAttribute("error", "Tài khoản không được phân quyền hợp lệ.");
                request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
            }

        } else {
            // Đăng nhập thất bại: tăng số lần sai
            int currentAttempts = user.getLoginAttempts();
            currentAttempts++;
            accountService.incrementLoginAttempts(username);

            if (currentAttempts >= 3) {
                accountService.lockUserForDuration(username, 1);
                request.setAttribute("error", "Tài khoản của bạn đã bị khóa 1 phút do đăng nhập sai quá 3 lần.");
            } else {
                int remaining = 3 - currentAttempts;
                request.setAttribute("error", "Sai mật khẩu! Bạn còn " + remaining + " lần thử.");
            }

            request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
        }
    }

    // Đăng Ký
    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        if (username == null || password == null || confirmPassword == null ||
                fullname == null || email == null || phoneNumber == null ||
                username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                fullname.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin!");
            request.getRequestDispatcher("/view/view-account/register.jsp").forward(request, response);
            return;
        }
        if (accountService.isEmailExists(email)) {
            request.setAttribute("emailError", "Email đã được sử dụng!");
            request.getRequestDispatcher("/view/view-account/register.jsp").forward(request, response);
            return;
        }

        if (accountService.isPhoneNumberExists(phoneNumber)) {
            request.setAttribute("phoneError", "Số điện thoại đã được sử dụng!");
            request.getRequestDispatcher("/view/view-account/register.jsp").forward(request, response);
            return;
        }


        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu xác nhận không trùng khớp!");
            request.getRequestDispatcher("/view/view-account/register.jsp").forward(request, response);
            return;
        }

        boolean isRegistered = accountService.register(username, password, fullname, email, phoneNumber);
        if (isRegistered) {
            response.sendRedirect(request.getContextPath() + "/sign");
        } else {
            request.setAttribute("usernameError", "Tên đăng nhập đã tồn tại!");
            request.getRequestDispatcher("/view/view-account/register.jsp").forward(request, response);
        }
    }

    // Quên MK
    private void handleForgotPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userEmail = request.getParameter("email");
        HttpSession mySession = request.getSession();

        if (userEmail == null || userEmail.trim().isEmpty()) {
            response.sendRedirect("forgot-password.jsp?error=Invalid Email");
            return;
        }

        int otpvalue = accountService.generateOTP();
        try {
            accountService.sendOTP(userEmail, otpvalue);
            request.setAttribute("message", "OTP đã được gửi tới email của bạn.");
            mySession.setAttribute("otp", otpvalue);
            mySession.setAttribute("email", userEmail);
            request.getRequestDispatcher("/view/view-account/enter-otp.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Lỗi khi gửi OTP qua email", e);
        }
    }

    // Xử lý xác thực OTP
    public void handleOtpValidation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int value = Integer.parseInt(request.getParameter("otp"));
        HttpSession session = request.getSession();
        int otp = (int) session.getAttribute("otp");
        RequestDispatcher dispatcher;

        if (value == otp) {
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");
            dispatcher = request.getRequestDispatcher("/view/view-account/reset-password.jsp");
        } else {
            request.setAttribute("message", "wrong otp");
            dispatcher = request.getRequestDispatcher("/view/view-account/enter-otp.jsp");
        }
        dispatcher.forward(request, response);
    }

    // Tạo Mk mới
    private void handleResetPassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        String email = (String) session.getAttribute("email");
        RequestDispatcher dispatcher;

        // Kiểm tra các trường mật khẩu và xác nhận mật khẩu
        if (password == null || confPassword == null || email == null || !password.equals(confPassword)) {
            request.setAttribute("status", "invalidInput");  // Hiển thị lỗi nếu không hợp lệ
            dispatcher = request.getRequestDispatcher("/view/view-account/reset-password.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Cập nhật mật khẩu vào cơ sở dữ liệu
        boolean isUpdated = accountService.updatePassword(email, password);

        if (isUpdated) {
            response.sendRedirect(request.getContextPath() + "/sign?status=resetSuccess");
            return;
        } else {
            request.setAttribute("status", "resetFailed");
            request.getRequestDispatcher("/view/view-account/reset-password.jsp").forward(request, response);
        }
    }

    // Đăng nhập Bằng gg
//    private void handleGoogleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            HttpSession session = request.getSession();
//            GoogleAccount googleAccount = (GoogleAccount) session.getAttribute("googleAccount");
//
//            // Nếu đã đăng nhập, chuyển hướng về trang home thay vì đăng nhập lại
//            if (googleAccount != null) {
//                response.sendRedirect(request.getContextPath() + "/home");
//                return;
//            }
//
//            // Lấy mã code từ request
//            String code = request.getParameter("code");
//            if (code == null || code.isEmpty()) {
//                response.sendRedirect(request.getContextPath() + "/view/view-account/signin.jsp?error=missing_code");
//                return;
//            }
//
//            // Gọi service để xử lý đăng nhập Google
//            googleAccount = accountService.handleGoogleLogin(code);
//
//            // Lưu vào session
//            session.setAttribute("googleAccount", googleAccount);
//
//            // Chuyển hướng đến trang home sau khi đăng nhập thành công
//            response.sendRedirect(request.getContextPath() + "/home");
//
//        } catch (Exception e) {
//            response.sendRedirect(request.getContextPath() + "/view/view-account/signin.jsp?error=true");
//        }
//    }
    private void handleGoogleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();

            // Lấy mã code từ request
            String code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/view/view-account/signin.jsp?error=missing_code");
                return;
            }

            // Gọi service để xử lý đăng nhập Google, trả về GoogleAccount
            GoogleAccount googleAccount = accountService.handleGoogleLogin(code);

            if (googleAccount == null) {
                response.sendRedirect(request.getContextPath() + "/view/view-account/signin.jsp?error=google_login_failed");
                return;
            }

            // Lấy hoặc tạo User tương ứng dựa trên email googleAccount
            User user = accountService.getUserByEmail(googleAccount.getEmail());
            if (user == null) {
                // Nếu user chưa có trong db, có thể tạo mới hoặc báo lỗi
                user = accountService.createUserFromGoogleAccount(googleAccount);
            }

            // Lưu User vào session (key "user"), dùng chung cho các phần khác
            session.setAttribute("user", user);

            // Xóa googleAccount nếu không cần thiết nữa hoặc cũng lưu nếu bạn muốn
            session.removeAttribute("googleAccount");

            // Chuyển hướng đến trang home
            response.sendRedirect(request.getContextPath() + "/home");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/view/view-account/signin.jsp?error=true");
        }
    }

    // Đăng Xuất Bằng Tk
    private void handleLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // Xóa session khi đăng xuất
        }
        // Đảm bảo response chưa bị committed trước khi gửi redirect
        response.setStatus(HttpServletResponse.SC_OK);  // Đảm bảo không có lỗi status
        // Sau khi logout, chuyển hướng về trang đăng nhập
        response.sendRedirect(request.getContextPath() + "/sign");

    }
    // Cập Nhật Tài Khoản
    private void handleProfileUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            response.sendRedirect("/sign");
            return;
        }
        // Lấy thông tin từ form
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String ward = request.getParameter("ward");
        String district = request.getParameter("district");
        String province = request.getParameter("province");
        String birthDateStr = request.getParameter("birthDate");
        java.sql.Date birthDate = null;
        if (birthDateStr != null && !birthDateStr.trim().isEmpty()) {
            try {
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
                birthDate = new java.sql.Date(utilDate.getTime());
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }
        // Kiểm tra dữ liệu bắt buộc
        if (birthDate == null || address == null || address.trim().isEmpty()) {
            request.setAttribute("error", "Ngày sinh hoặc địa chỉ không hợp lệ.");
            request.getRequestDispatcher("/view/view-account/profile.jsp").forward(request, response);
            return;
        }
        // Cập nhật thông tin người dùng trong cơ sở dữ liệu
        AccountService service = new AccountService();
        boolean success = service.updateUserInfo(
                currentUser.getIdUser(),
                fullName,
                phoneNumber,
                email,
                address,
                ward,
                district,
                province,
                birthDate
        );
        // Nếu cập nhật thành công, cập nhật lại thông tin trong session
        if (success) {
            currentUser.setFullName(fullName);
            currentUser.setPhoneNumber(phoneNumber);
            currentUser.setEmail(email);
            currentUser.setBirthDate(String.valueOf(birthDate));
            // Cập nhật thông tin địa chỉ vào đối tượng Address nếu có
            if (currentUser.getAddress() == null) {
                currentUser.setAddress(new Address());
            }
            Address addr = currentUser.getAddress();
            addr.setAddress(address);
            addr.setWard(ward);
            addr.setDistrict(district);
            addr.setProvince(province);
            session.setAttribute("user", currentUser);
            request.setAttribute("user", currentUser); // để JSP đọc lại dữ liệu
            request.setAttribute("message", "Cập nhật thông tin thành công!");
            request.getRequestDispatcher("/view/view-account/profile.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Cập nhật thất bại. Vui lòng thử lại.");
            request.getRequestDispatcher("/view/view-account/profile.jsp").forward(request, response);
        }
    }
    // xem tài khoản
    private void viewProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Lấy User từ session
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser == null) {
            // Nếu chưa đăng nhập thì chuyển sang trang đăng nhập
            request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
            return;
        }

        // Truy vấn lại user từ DB theo email
        String email = sessionUser.getEmail();
        User userFromDB = accountService.getUserByEmailAndAddress(email); // Đúng hàm

        if (userFromDB != null) {
            request.setAttribute("user", userFromDB);
            request.getRequestDispatcher("/view/view-account/profile.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Không tìm thấy thông tin người dùng.");
            request.getRequestDispatcher("/view/view-account/signin.jsp").forward(request, response);
        }
    }



}