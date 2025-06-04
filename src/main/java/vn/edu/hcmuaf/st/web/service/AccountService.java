package vn.edu.hcmuaf.st.web.service;

import org.mindrot.jbcrypt.BCrypt;
import vn.edu.hcmuaf.st.web.controller.SocialLogin;
import vn.edu.hcmuaf.st.web.dao.AccountRepository;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import vn.edu.hcmuaf.st.web.entity.GoogleAccount;
import vn.edu.hcmuaf.st.web.entity.Role;
import vn.edu.hcmuaf.st.web.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    // Đăng Nhập
    public boolean login(String username, String password) {
        return accountRepository.validateUser(username, password);
    }

    // Đăng ký tài khoản
    public boolean register(String username, String password, String fullname, String email, String phoneNumber) {
        if (accountRepository.isUsernameExists(username)) {
            System.out.println("Username already exists!");
            return false;
        }
        // Mã hóa mật khẩu bằng BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        // Tạo tài khoản mới (truyền các tham số cần thiết)
        return accountRepository.addUser(username, hashedPassword, fullname, email, phoneNumber);
    }

    // Dịch vụ gửi email
    public int generateOTP() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);
    }

    // gửi otp
    public void sendOTP(String userEmail, int otpvalue) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("danhv5879@gmail.com", "bvhofdvukcetvrdm");
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("danhv5879@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP is: " + otpvalue);

        Transport.send(message);
    }

    // đổi mật khẩu
    public boolean updatePassword(String email, String newPassword) {
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        return accountRepository.updatePasswordByEmail(email, hashedPassword);
    }

    // hiển thị tên khi đăng nhập thành công
    public User getUserByUsername(String username) {
        return accountRepository.getUserByUsername(username);
    }

    // Lấy thông tin người dùng theo username ( hiển thị trong profile.jsp)
    public User getUserByUsernameAndAddress(String email) {
        return accountRepository.getUserByEmailAndAddress(email);  // Gọi phương thức từ DAO
    }



    // đăng nhập google
    public GoogleAccount handleGoogleLogin(String code) throws Exception {
        // Lấy thông tin tài khoản Google
        SocialLogin gg = new SocialLogin();
        String accessToken = gg.getToken(code);
        GoogleAccount googleAccount = gg.getUserInfo(accessToken);
        // Thêm mới hoặc cập nhật người dùng
        accountRepository.insertOrUpdateUser(googleAccount);  // Thêm hoặc cập nhật người dùng trong CSDL
        return googleAccount;
    }

    // cập nhật thông tin người dùng
    public boolean updateUserInfo(int idUser, String fullName, String phoneNumber, String email,
                                  String address, String ward, String district, String province,
                                  java.util.Date birthDate) {
        return accountRepository.updateUserInfo(idUser, fullName, phoneNumber, email, address, ward, district, province, birthDate);
    }

    public User insertOrUpdateUserAndReturn(GoogleAccount googleAccount) {
        accountRepository.insertOrUpdateUser(googleAccount);
        return accountRepository.getUserByEmail(googleAccount.getEmail());
    }

    public User getUserByEmail(String email) {
        return accountRepository.getUserByEmail(email);
    }


    public void lockUserForDuration(String username, int minutes) {
        LocalDateTime lockedUntil = LocalDateTime.now().plusMinutes(minutes);
        accountRepository.setUserLockedUntil(username, lockedUntil);
    }
    // mở khóa
    public void unlockUserIfTimePassed(String username) {
        User user = accountRepository.getUserByUsername(username);
        if (user == null || user.getLockedUntil() == null) return;

        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(user.getLockedUntil())) {
            accountRepository.unlockUser(username);
        }
    }

    public boolean isUserLocked(String username) {
        User user = accountRepository.getUserByUsername(username);
        if (user == null || user.getLockedUntil() == null) return false;

        return LocalDateTime.now().isBefore(user.getLockedUntil());
    }

    public void unlockUser(String username) {
        accountRepository.unlockUser(username);
    }

    public void resetLoginAttempts(String username) {
        accountRepository.updateLoginAttempts(username, 0);
    }
    //
    public void incrementLoginAttempts(String username) {
        User user = accountRepository.getUserByUsername(username);
        if (user != null) {
            int attempts = user.getLoginAttempts() + 1;
            accountRepository.updateLoginAttempts(username, attempts);
        }
    }
    // kiểm tra đăng nhập
    public boolean checkLogin(String username, String password) {
        return accountRepository.checkLogin(username, password);
    }

    // lấy dữ liệu nhân viên
    public List<User> getUsersWithRoles(List<Integer> roleIds) {
        return accountRepository.getUsersWithRoles(roleIds);
    }
    // xóa nhân viên
    public void deleteUserById(int id) {
        accountRepository.deleteStaffById(id);
    }
    // lấy thông tin nhân viên qua id
    public User getStaffById(int id) {
        return accountRepository.getStaffById(id);
    }
    // cập nhật thông tin nhân viên
    public void updateStaff(User user) {
        accountRepository.updateStaff(user);
    }

    // hiển thị tất cả vai trò
    public List<Role> getAllRoles() {
        return accountRepository.getAllRoles();
    }

    public int addStaff(User user) {
        return accountRepository.addStaff(user);
    }
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        User user = accountService.getUserByUsername("hatest123");
        System.out.println(user.getIdUser());
    }

    public boolean isEmailExists(String email) {
        return accountRepository.isEmailExists(email);
    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        return accountRepository.isPhoneNumberExists(phoneNumber);
    }

    public User createUserFromGoogleAccount(GoogleAccount googleAccount) {
        // Thêm mới hoặc cập nhật user dựa trên GoogleAccount
        accountRepository.insertOrUpdateUser(googleAccount);
        // Lấy User từ email Google
        return accountRepository.getUserByEmail(googleAccount.getEmail());
    }

    public User getUserByEmailAndAddress(String email) {
        return accountRepository.getUserByEmailAndAddress(email);
    }
}

