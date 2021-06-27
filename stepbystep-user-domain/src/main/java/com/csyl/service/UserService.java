package com.csyl.service;

import com.csyl.domain.UUser;

/**
 * @author 霖
 */
public interface UserService {

    /**
     * 注册
     *
     * @param t
     */
    void put(UUser t);

    /**
     * 获取
     *
     * @param loginName
     * @return
     */
    UUser getUser(String loginName);

    /**
     * 登录
     *
     * @param loginName     用户名
     * @param loginPassword 密码
     * @return
     */
    UserServiceImpl.UserProxy login(String loginName, String loginPassword);
}
