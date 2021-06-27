package com.csyl.service;

import com.csyl.domain.UUser;
import com.csyl.mapper.UUserMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author 霖
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UUserMapper userMapper;

    @Override
    public void put(UUser uUser) {
        userMapper.insert(uUser);
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
