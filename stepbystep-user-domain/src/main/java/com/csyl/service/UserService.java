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
     * @param loginName
     * @return
     */
    UUser getUUser(String loginName);


}
