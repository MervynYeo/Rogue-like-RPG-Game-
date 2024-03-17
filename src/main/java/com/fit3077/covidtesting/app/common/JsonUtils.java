package com.fit3077.covidtesting.app.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.INDENT_OUTPUT, true)
            .setSerializationInclusion(JsonInclude.Include.ALWAYS);

    public static <T> T toObject(String jsonString, Class<T> cls) {
        try {
            if (jsonString.isEmpty()) {
                return null;
            }
            return OBJECT_MAPPER.readValue(jsonString, cls);
        } catch (Exception e) {
            System.out.println("Error in JsonUtils.toObject: " + e.getMessage());
            return null;
        }
    }

    public static <T> T toObjectList(String jsonArrayString, TypeReference<T> typeReference) {
        try {
            if (jsonArrayString.isEmpty()) {
                return null;
            }
            return OBJECT_MAPPER.readValue(jsonArrayString, typeReference);
        } catch (Exception e) {
            System.out.println("Error in JsonUtils.toObjectList: " + e.getMessage());
            return null;
        }
    }

    public static <T> String toJsonString(T object) {
        try {
            if (object == null) {
                return null;
            }
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            System.out.println("Error in JsonUtils.toJsonString: " + e.getMessage());
            return null;
        }
    }

}
