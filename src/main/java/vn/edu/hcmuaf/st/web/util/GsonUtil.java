package vn.edu.hcmuaf.st.web.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GsonUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (com.google.gson.JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) ->
                    jsonSerializationContext.serialize(localDateTime.format(formatter)))
            .registerTypeAdapter(LocalDateTime.class, (com.google.gson.JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) ->
                    LocalDateTime.parse(jsonElement.getAsString(), formatter))
            .create();

    public static Gson getGson() {
        return gson;
    }
}
