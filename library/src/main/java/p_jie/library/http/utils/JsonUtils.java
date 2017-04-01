package p_jie.library.http.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import p_jie.library.utils.LogUtils;

/**
 * Created by jj on 2017/3/23.
 */

public class JsonUtils {

    /**
     * json管理
     */
    public static <T> T object(String json, Class<T> classOfT) {
        return JSON.parseObject(json, classOfT);
    }

    public static List<Map<String, Object>> listJson(String json, String root) {
        JSONObject jo = JSON.parseObject(json);
        List<Object> list = new ArrayList<>();
        Map<String, Object> mapJson = new HashMap<>();
        List<Map<String, Object>> ListJson = new ArrayList<>();

        if (jo.getString(root) != null) {

            try {
                list = JSONArray
                        .parseArray(jo.get(root).toString(), Object.class);
            } catch (Exception e) {
                LogUtils.jj("json不包含List");

                try {
                    mapJson = JSON.parseObject(
                            jo.get(root).toString(), new TypeReference<Map<String, Object>>() {
                            });
                } catch (Exception e1) {
                    LogUtils.jj("json解析错误");
                    return ListJson;
                }
            }
        }
        if (list.size() > 0) {
            for (Object obj : list) {
                Map<String, Object> item = (Map<String, Object>) obj;
                ListJson.add(item);
            }
        } else if (mapJson.size() > 0) {
            ListJson.add(mapJson);
        }


        LogUtils.jj(ListJson.toString());
        return ListJson;
    }



    public static Map<String, Object> MapJson(String json) {

        Map<String, Object> mapJson = new HashMap<>();

        try {
            mapJson = JSON.parseObject(
                    json, new TypeReference<Map<String, Object>>() {
                    });
        } catch (Exception e1) {
            LogUtils.jj("json解析错误");
            return mapJson;
        }

        LogUtils.jj(mapJson.toString());
        return mapJson;
    }

    public static String string(Object object) {
        return JSON.toJSON(object).toString();
    }

}
