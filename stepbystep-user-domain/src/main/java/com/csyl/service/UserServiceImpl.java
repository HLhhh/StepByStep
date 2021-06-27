package com.csyl.service;

import com.csyl.domain.UUser;
import com.csyl.mapper.UUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 霖
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UUserMapper userMapper;

    @Override
    public void put(UUser uUser) {
        userMapper.insert(uUser);
    }

    @Override
    public UUser getUUser(String loginName) {
        return userMapper.selectByLoginName(loginName);
    }
}
