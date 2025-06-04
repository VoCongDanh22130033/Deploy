package vn.edu.hcmuaf.st.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import vn.edu.hcmuaf.st.web.entity.Order;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GhnShippingService {

    private static final String TOKEN_GHN = "16a9a441-3f8e-11f0-9b81-222185cb68c8";

    public static String createShippingOrder(Order order) throws IOException, InterruptedException {

        // Chuyển finalPrice (double) sang int, ví dụ làm tròn xuống hoặc lên
        int codAmount = (int) Math.floor(order.getFinalPrice()); // hoặc Math.ceil()

        String senderPhone = "0347318239";  // Thay bằng số điện thoại người gửi thực tế, đúng định dạng

        String requiredNote = "CHOTHUHANG";

        // Build JSON body theo tài liệu GHN (bạn cần bổ sung thông tin thật)
        String jsonBody = "{"
                + "\"from_name\": \"Tên người gửi\","
                + "\"from_phone\": \"" + senderPhone + "\","
                + "\"from_address\": \"Địa chỉ người gửi\","
                + "\"to_name\": \"" + order.getUser().getFullName() + "\","
                + "\"to_phone\": \"" + order.getUser().getPhoneNumber() + "\","
                + "\"to_address\": \"" + order.getAddress().getAddress() + "\","
                + "\"to_ward_code\": \"" + order.getAddress().getWard() + "\","
                + "\"to_district_id\": " + getDistrictIdFromName(order.getAddress().getDistrict()) + ","
                + "\"cod_amount\": " + codAmount + ","
                + "\"service_id\": 53320,"
                + "\"weight\": 1000,"
                + "\"content\": \"Đơn hàng #" + order.getIdOrder() + "\","
                + "\"required_note\": \"" + requiredNote + "\","
                + "\"payment_type_id\": 2"
                + "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create"))
                .header("Token", TOKEN_GHN)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());

        if (root.path("code").asInt() == 200) {
            return root.path("data").path("order_code").asText();
        } else {
            throw new RuntimeException("Lỗi tạo đơn GHN: " + root.path("message").asText());
        }
    }

    // Hàm ví dụ tìm districtId từ tên quận (bạn cần làm map này theo dữ liệu GHN hoặc DB)
    private static int getDistrictIdFromName(String districtName) {
        // TODO: Map tên quận => districtId GHN
        return 1234; // ví dụ tạm
    }

    public static JsonNode getShippingServices(int shopId) throws IOException, InterruptedException {
        String jsonBody = "{\"shop_id\": " + shopId + "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/get-services"))
                .header("Token", TOKEN_GHN)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());

        if (root.path("code").asInt() == 200) {
            return root.path("data");
        } else {
            throw new RuntimeException("Lỗi lấy dịch vụ GHN: " + root.path("message").asText());
        }
    }

    public static void main(String[] args) {

    }


}
