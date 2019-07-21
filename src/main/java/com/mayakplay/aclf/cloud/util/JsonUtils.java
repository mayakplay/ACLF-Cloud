package com.mayakplay.aclf.cloud.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.jetbrains.annotations.Nullable;

/**
 * @author mayakplay
 * @version 0.0.1
 * @since 21.07.2019.
 */
public class JsonUtils {

    private static Gson gson = new Gson();

    @Nullable
    public static <T> T toObject(String jsonString, Class<T> type) {
        try {
            return gson.fromJson(jsonString, type);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

}
