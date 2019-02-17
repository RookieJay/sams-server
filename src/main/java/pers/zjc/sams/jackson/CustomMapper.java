package pers.zjc.sams.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.text.SimpleDateFormat;

// 继承 ObjectMapper 类
public class CustomMapper extends ObjectMapper {
    public CustomMapper() {
//        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
//        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //序列化的时候序列对象的所有属性
        this.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //反序列化的时候如果多了其他属性,不抛出异常
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //如果是空对象的时候,不抛异常
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeString("");
            }
        });
    }
}