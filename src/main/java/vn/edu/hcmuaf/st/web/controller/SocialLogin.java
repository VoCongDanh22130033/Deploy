package vn.edu.hcmuaf.st.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.mindrot.jbcrypt.BCrypt;
import vn.edu.hcmuaf.st.web.entity.GoogleAccount;
import vn.edu.hcmuaf.st.web.constant.Iconstant;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class SocialLogin {
    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                                .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                                .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)
                                .add("code", code)
                                .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE)
                                .build()
                )
                .execute().returnContent().asString();
        System.out.println("Google Token Response: " + response); // Debug
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        if (!jobj.has("access_token")) {
            System.out.println("Lỗi: Không tìm thấy access_token trong phản hồi.");
            return null;
        }
        String accessToken = jobj.get("access_token").getAsString();
        return accessToken;
    }
// Phương thức để lấy thông tin người dùng từ Google API
public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
    String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
    String response = Request.Get(link).execute().returnContent().asString();

    // Đọc dữ liệu từ JSON và chuyển thành GoogleAccount
    JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

    // Truyền "name" từ Google API thành "fullName" trong GoogleAccount
    String fullName = jobj.has("name") ? jobj.get("name").getAsString() : "";
    String image = jobj.has("picture") ? jobj.get("picture").getAsString() : "";
    boolean verifiedEmail = jobj.has("verified_email") && jobj.get("verified_email").getAsBoolean();

    // Giả sử bạn lấy idRole từ một nguồn nào đó (có thể là một giá trị mặc định hoặc từ DB)
    int idRole = 2;  // Ví dụ: Gán giá trị mặc định cho idRole

    // Lấy email và tạo username từ email
    String email = jobj.get("email").getAsString();
    String username = email.split("@")[0];  // Lấy phần trước dấu "@" từ email

    // Sinh mật khẩu từ email và mã hóa bằng BCrypt
    String hashedPassword = generateBCryptPassword(email); // Dùng email để sinh mật khẩu và mã hóa

    // Tạo đối tượng GoogleAccount với thông tin đã lấy
    GoogleAccount googleAccount = new GoogleAccount(
            jobj.get("id").getAsString(),
            jobj.get("email").getAsString(),
            fullName,
            image,
            verifiedEmail,
            idRole,
            username,
            hashedPassword
    );

    return googleAccount;
}

    // Phương thức để tạo mật khẩu từ email và mã hóa bằng BCrypt
    public static String generateBCryptPassword(String email) {
        // Tạo mật khẩu từ email (bao gồm phần username + một chuỗi ngẫu nhiên)
        String password = generatePasswordFromEmail(email);

        // Mã hóa mật khẩu bằng BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }

    // Phương thức để tạo mật khẩu từ email
    private static String generatePasswordFromEmail(String email) {
        // Lấy phần trước dấu "@" trong email
        String usernamePart = email.split("@")[0];

        // Thêm phần ngẫu nhiên để tạo mật khẩu mạnh hơn
        String passwordChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder randomPart = new StringBuilder();
        SecureRandom random = new SecureRandom(); // Sử dụng SecureRandom thay cho Math.random()

        // Thêm phần ngẫu nhiên dài 6 ký tự
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(passwordChars.length());
            randomPart.append(passwordChars.charAt(randomIndex));
        }

        // Kết hợp username và phần ngẫu nhiên để tạo mật khẩu
        return usernamePart + randomPart.toString();
    }
}
