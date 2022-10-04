package com.patreonshout.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * JSON Utilities class to provide helpful functions relating to JSON
 */
public class JSONUtil {

    /**
     * mapper that helps map json strings into json objects
     */
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * converts a json string into a {@link org.json.simple.JSONObject}
     *
     * @param jString is the json string
     * @return a {@link org.json.simple.JSONObject} created from the json string
     */
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

    /**
     * Creates a class from the json variables provided in the json string
     *
     * @param json is the json in string format
     * @param beanClass is the class we want to turn the json into
     * @param <T> is the generic typing so we can use this for multiple classes
     * @return an object of type T with values corresponding to the json provided
     */
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
