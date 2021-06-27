package com.csyl.domain;

import lombok.Data;

import java.io.Serializable;


/**
 * @author 霖
 */
@Data
public class UUser implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Integer loginStatus;

    /**
     * 登录名
     */
    private byte[] loginName;

    /**
     * 登录别名
     */
    private byte[] loginAliasName;

    /**
     * 登录密码
     */
    private byte[] loginPassword;

    private static final long serialVersionUID = 1L;
}