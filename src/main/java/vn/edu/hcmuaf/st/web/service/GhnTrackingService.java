package vn.edu.hcmuaf.st.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GhnTrackingService {

    private static final String TOKEN_GHN = "16a9a441-3f8e-11f0-9b81-222185cb68c8";

    public static String getGhnOrderStatus(String ghnOrderCode) throws IOException, InterruptedException {
        String url = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/detail?order_code=" + ghnOrderCode;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Token", TOKEN_GHN)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());

        if(root.path("code").asInt() == 200) {
            return root.path("data").path("status_name").asText();
        } else {
            throw new RuntimeException("Lỗi lấy trạng thái GHN: " + root.path("message").asText());
        }
    }

}
