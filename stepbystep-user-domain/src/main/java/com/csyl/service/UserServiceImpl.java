package com.csyl.service;

import com.csyl.domain.UUser;
import com.csyl.mapper.UUserMapper;
import com.csyl.random.MathRandom;
import lombok.Getter;
import lombok.Setter;
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
    public boolean put(UUser uUser) {
        //验证
        String authedToken = stringRedisTemplate.opsForValue().get(uUser.getLoginAliasName());
        if (StringUtils.isBlank(authedToken)) {
            return false;
        }
        return userMapper.insert(uUser) > 0;
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

    @Override
    public String authentication(String email) {
        String authedToken = String.valueOf(MathRandom.creartRandom());
        stringRedisTemplate.opsForValue().set(email, authedToken);
        stringRedisTemplate.expire(email, Duration.ofMinutes(1));
        return authedToken;
    }

    @Getter
    @Setter
    public static class UserProxy {
        private Boolean pass;
        private UUser uUser;

        private UserProxy(Boolean pass, UUser uUser) {
            this.pass = pass;
            this.uUser = uUser;
        }

        private UserProxy() {
        }

        public static UserProxy loginPass(UUser uUser) {
            return new UserProxy(true, uUser);
        }

        public static UserProxy unLoginPass() {
            return new UserProxy(false, new UUser());
        }
    }
}
