package com.demo.token;

import com.demo.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateToken {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 生成令牌
     *
     * @param keyPrefix
     *            令牌key前缀
     * @param redisValue
     *            redis存放的值
     * @return 返回token
     */
    public String createToken(String keyPrefix, String redisValue) {
        if (StringUtils.isEmpty(redisValue)) {
            new Exception("redisValue Not null");
        }
        String token = keyPrefix + UUID.randomUUID().toString().replace("-", "");
        redisUtil.setString(token, redisValue);
        return token;
    }

    /**
     * 根据token获取redis中的value值
     *
     * @param token
     * @return
     */
    public String getToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String value = redisUtil.getString(token);
        return value;
    }

    /**
     * 移除token
     *
     * @param token
     * @return
     */
    public Boolean removeToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return redisUtil.delKey(token);

    }
}
