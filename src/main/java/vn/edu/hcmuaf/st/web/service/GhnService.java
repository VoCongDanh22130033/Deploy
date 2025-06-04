package vn.edu.hcmuaf.st.web.service;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GhnService {

    private static final String TOKEN = "16a9a441-3f8e-11f0-9b81-222185cb68c8";
    private static final String SHOP_ID = "196766";
    private static final int FROM_DISTRICT_ID = 1443; // mã quận nơi bạn gửi hàng (quận kho hàng)

    public static int getShippingFee(int districtId, String wardCode, int weight, int serviceId) throws Exception {
        URL url = new URL("https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Token", TOKEN);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("ShopId", SHOP_ID);
        conn.setDoOutput(true);

        JSONObject json = new JSONObject();
        json.put("from_district_id", FROM_DISTRICT_ID);
        json.put("service_id", serviceId); // 👈 truyền vào đây
        json.put("to_district_id", districtId);
        json.put("to_ward_code", wardCode);
        json.put("weight", weight);
        json.put("length", 30);
        json.put("width", 20);
        json.put("height", 10);
        json.put("insurance_value", 100000);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.toString().getBytes());
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) response.append(scanner.nextLine());
        scanner.close();

        JSONObject responseObject = new JSONObject(response.toString());
        return responseObject.getJSONObject("data").getInt("total");
    }


}
