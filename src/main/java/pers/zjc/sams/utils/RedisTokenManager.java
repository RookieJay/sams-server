package pers.zjc.sams.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import pers.zjc.sams.po.TokenModel;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component("redisTokenManager")
public class RedisTokenManager implements TokenManager {
    //注入redisTemplate
    @Resource(name="redisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    public TokenModel createToken(int admin_id) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(admin_id, token);
        //存储到redis并设置过期时间
        String adminId=String.valueOf(admin_id);
        redisTemplate.boundValueOps(adminId).set(token, 24 * 60 * 60 * 60, TimeUnit.HOURS);
        return model;
    }
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        Integer admin_id = Integer.parseInt(param[0]);
        String token = param[1];
        return new TokenModel(admin_id, token);
    }
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String adminId=String.valueOf(model.getUserId());
        String token = redisTemplate.boundValueOps(adminId).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redisTemplate.boundValueOps(adminId).expire(Const.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }
    public void deleteToken(int admin_id) {
        String adminId=String.valueOf(admin_id);
        redisTemplate.delete(adminId);
    }
}
