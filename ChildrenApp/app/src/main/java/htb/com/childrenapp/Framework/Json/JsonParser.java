package htb.com.childrenapp.Framework.Json;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by weidong_wu on 15/11/14.
 * 邮箱:wwdhao163@163.com
 */
public class JsonParser {
    private static  JsonParser jsonParser;
    private JsonParser(){}

    public static  synchronized  JsonParser instance(){

        if (jsonParser==null)
            jsonParser = new JsonParser();

        return jsonParser;
    }

    /**
     * 解析json字符串
     * @param jsonString
     * @return
     */
    public JSONObject ParserString(String jsonString){
        JSONTokener jsonTokener = new JSONTokener(jsonString);
        JSONObject object = null;
        try {
            object = (JSONObject) jsonTokener.nextValue();
        }catch (JSONException EX){
            EX.printStackTrace();
        }
        return object;
    }
}
