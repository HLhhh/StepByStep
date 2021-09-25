package com.csyl.service;

import com.csyl.domain.UUser;
import com.csyl.email.SendEmail;
import com.csyl.mapper.UUserMapper;
import com.csyl.random.MathRandom;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author 霖
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UUserMapper userMapper;

    @Override
    public boolean put(UserProxy proxy) {
        //验证
        String authedToken = stringRedisTemplate.opsForValue().get(new String(proxy.getUUser().getLoginAliasName()));
        if (StringUtils.isBlank(authedToken) || !authedToken.equals(proxy.getAuthedToken())) {
            return false;
        }
        return userMapper.insert(proxy.getUUser()) > 0;
    }

    @Override
    public UUser getUser(String loginName) {
        return userMapper.selectByLoginName(loginName);
    }

    @Override
    public UserProxy login(String loginName, String loginPassword) {
        Assert.notNull(loginName, "loginName not null");
        Assert.notNull(loginPassword, "loginPassword not null");
        UUser uUser = userMapper.selectByLoginNameAndLoginPassword(loginName, loginPassword);
        if (uUser == null) {
            return UserProxy.unLoginPass();
        }
        return UserProxy.loginPass(uUser);
    }

    @SneakyThrows
    @Override
    public String authentication(String email) {
        String authedToken = String.valueOf(MathRandom.creartRandom());
        stringRedisTemplate.opsForValue().set(email, authedToken);
        stringRedisTemplate.expire(email, Duration.ofMinutes(1));
        SendEmail.send(email);
        return authedToken;
    }

}
