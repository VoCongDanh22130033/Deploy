package vn.edu.hcmuaf.st.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.json.JSONObject;
import vn.edu.hcmuaf.st.web.constant.IconstantFacebook;
import vn.edu.hcmuaf.st.web.entity.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Facebook {
    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(IconstantFacebook.FACEBOOK_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", IconstantFacebook.FACEBOOK_CLIENT_ID)
                                .add("client_secret", IconstantFacebook.FACEBOOK_CLIENT_SECRET)
                                .add("redirect_uri", IconstantFacebook.FACEBOOK_REDIRECT_URI)
                                .add("code", code)
                                .build()
                )
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }
    public static Account getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = IconstantFacebook.FACEBOOK_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        Account fbAccount= new Gson().fromJson(response, Account .class);
        return fbAccount;
    }

    // Lấy tên người dùng từ Facebook API
    public String getUsername(String accessToken) {
        String facebookName = "";
        try {
            // URL của Facebook Graph API để lấy thông tin người dùng
            String urlString = "https://graph.facebook.com/me?access_token=" + accessToken;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // Đọc kết quả trả về từ API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            // Phân tích kết quả JSON để lấy tên người dùng
            JSONObject jsonResponse = new JSONObject(response.toString());
            facebookName = jsonResponse.getString("name"); // Lấy tên người dùng từ JSON
        } catch (Exception e) {
            e.printStackTrace();
        }
        return facebookName;
    }
}
