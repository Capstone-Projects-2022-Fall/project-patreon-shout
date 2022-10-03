package com.patreonshout.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JSONObject convertStringToJSONObject(String jString) {
        JSONObject json = new JSONObject();

        try {
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(jString);
        } catch (ParseException e) {
            e.printStackTrace();
            json.put("failure", e.getLocalizedMessage());
        }

        return json;
    }

    public static <T> Object getBeanFromJSON(String json, Class<T> beanClass) {
        try {
            if (json != null) {
                return mapper.readValue(json, beanClass);
            }
            else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
