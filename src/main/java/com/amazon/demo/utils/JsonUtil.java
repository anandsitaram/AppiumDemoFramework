package com.amazon.demo.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Anand B S
 * @date 30 Jun 2021
 */
public class JsonUtil {

    private static String getFilePath() {
        String filePath = PropertyUtil.getPropertyValue("dataPath");
        String file = Thread.currentThread().getStackTrace()[4].getFileName().replaceAll(".java", "");
        return filePath + File.separator + file + ".json";
    }

    private static Map<String, String> getJsonToMap(JSONObject jsonObject) {
        Map<String, String> data = new HashMap<>();
        jsonObject.keySet().forEach(keyStr ->
        {
            Object keyvalue = jsonObject.get(keyStr);
            data.put(keyStr.toString(), keyvalue.toString());

        });
        return data;
    }

    private static List<Map<String, String>> getJsonToList(JSONObject jsonObject, String key) {
        List<Map<String, String>> dataList = new ArrayList<>();
        jsonObject.keySet().forEach(keyStr ->
        {
            Object keyvalue = jsonObject.get(keyStr);
            if (keyvalue instanceof JSONObject && keyStr.toString().equals(key)) {
                dataList.add(getJsonToMap((JSONObject) keyvalue));
            }
        });
        return dataList;

    }

    private static JSONObject getJsonObjects() {
        JSONParser parser = new JSONParser();
        Object object = null;
        String path = getFilePath();
        try {
            object = parser
                    .parse(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) object;
        return jsonObject;
    }

    public static Map<String, String> getMapData(String key) {

        JSONObject jsonObject = getJsonObjects();
        return getJsonToList(jsonObject, key).get(0);


    }

}
