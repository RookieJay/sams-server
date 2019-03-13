package pers.zjc.sams.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.zjc.sams.jackson.CustomMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义返回数据结构类
 */
public class Result {

	// 定义jackson对象
    private static final CustomMapper MAPPER = new CustomMapper();

    // 响应业务状态
    private String code;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static Result build(String code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result ok() {
        return new Result(null);
    }

    public static Result ok(Map map) {
        return new Result(Const.HttpStatusCode.HttpStatus_200, "查询列表成功", map);
    }

    public static Result ok(String msg) {
        return new Result(Const.HttpStatusCode.HttpStatus_200, msg, new Object());
    }

    public Result() {

    }

    public static Result build(String code, String msg) {
        return new Result(code, msg, null);
    }

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Object data) {
        this.code = Const.HttpStatusCode.HttpStatus_200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为Result对象
     * 
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static Result formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, Result.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").toString(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static Result format(String json) {
        try {
            return MAPPER.readValue(json, Result.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static Result formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("code").toString(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static Result fail_500(String msg) {
        return build(Const.HttpStatusCode.HttpStatus_500, msg);
    }

    public static Result fail_500() {
        return build(Const.HttpStatusCode.HttpStatus_500, "服务端未知错误");
    }

    public static Result fail_obj_500(String key) {
        Map map = new HashMap();
        map.put(key, new Object());
        return build(Const.HttpStatusCode.HttpStatus_500, "服务端未知错误", map);
    }

    public static Result fail_array_500(String key) {
        Map map = new HashMap();
        map.put(key, new ArrayList<>());
        return build(Const.HttpStatusCode.HttpStatus_500, "服务端未知错误", map);
    }

    public static Result fail_500(Map map) {
        return build(Const.HttpStatusCode.HttpStatus_500, "服务端未知错误", map);
    }
}
