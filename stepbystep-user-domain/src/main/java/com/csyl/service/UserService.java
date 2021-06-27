package com.csyl.service;

import com.csyl.domain.UUser;

/**
 * @author 霖
 */
public interface UserService {

    /**
     * 注册
     *
     * @param userProxy
     */
    boolean put(UserProxy userProxy);

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
    UserProxy login(String loginName, String loginPassword);


    /**
     * 身份验证
     *
     * @param email 邮箱
     * @return
     */
    String authentication(String email);
}
