package com.csyl.dto;

import com.csyl.domain.UUser;
import com.csyl.factory.DoFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * @author 霖
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable, DoFactory<UserDTO, UUser> {
    /**
     *
     */
    private Long id;

    /**
     * 登录状态
     */
    private Integer loginStatus;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录别名
     */
    private String loginAliasName;

    /**
     * 登录密码
     */
    private String loginPassword;

    private static final long serialVersionUID = 1L;

    @Override
    public UUser bulider(UserDTO userDTO) {
        UUser uUser = new UUser();
        uUser.setLoginName(userDTO.getLoginName().getBytes(StandardCharsets.UTF_8));
        uUser.setLoginPassword(userDTO.getLoginPassword().getBytes(StandardCharsets.UTF_8));
        uUser.setLoginAliasName(userDTO.getLoginAliasName().getBytes(StandardCharsets.UTF_8));
        uUser.setLoginStatus(userDTO.getLoginStatus());
        return uUser;
    }

    @Override
    public UserDTO reverse(UUser uUser) {
        this.setId(uUser.getId());
        this.setLoginName(new String(uUser.getLoginName()));
        this.setLoginPassword(new String(uUser.getLoginPassword()));
        this.setLoginAliasName(new String(uUser.getLoginAliasName()));
        this.setLoginStatus(uUser.getLoginStatus());
        return this;
    }
}