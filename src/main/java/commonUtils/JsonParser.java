package commonUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonUtils.configUtils.PropertiesUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JsonParser {

    /**
     * This method will parse the json file and convert into JSONObject Type
     *
     * @param jsonFileName with Path
     * @return jsonObject
     */
    public static JSONObject jsonParser(String jsonFileName) {
        JSONParser jsonParser = new JSONParser();
        Reader reader = new InputStreamReader(PropertiesUtil.loadResource(jsonFileName));
        Object obj = null;
        try {
            obj = jsonParser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return (JSONObject) obj;

    }

    /**
     * This method will convert json string into map
     *
     * @param json : String format
     * @return Map
     */
    public static Map<String, ?> convertJsonToMap(String json) {
        Map<String, ?> map = new HashMap<>();
        ObjectMapper objmap = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objmap.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        TypeReference<HashMap<String, String>> typeref = new TypeReference<HashMap<String, String>>() {
        };
        try {
            map = objmap.readValue(json, typeref);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }

    /**
     * this method will update the key with a new value or values in a JsonString
     *
     * @param json  :  JsonObject
     * @param key   : Key
     * @param value : value
     */
    @SuppressWarnings("unchecked")
    public static void updateJson(JSONObject json, String key, String value) {
        for (Object item : json.keySet()) {
            if (item.equals(key)) {
                if (json.get(item) instanceof JSONArray) {
                    JSONArray arrCreate = new JSONArray();
                    arrCreate.addAll(Arrays.asList(value.split(",")));
                    json.put(key, arrCreate);
                } else {
                    json.put(key, value);
                }
                return;
            } else if (json.get(item) instanceof JSONObject) {
                JSONObject obj = (JSONObject) json.get(item);
                updateJson(obj, key, value);
            } else if (json.get(item) instanceof JSONArray) {
                JSONArray arr = ((JSONArray) json.get(item));
                for (Object o : arr) {
                    if (o instanceof JSONObject) {
                        updateJson((JSONObject) o, key, value);
                    }
                }
            }
        }
    }
}
