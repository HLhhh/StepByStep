package com.csyl.service;

import com.csyl.domain.UUser;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 霖
 */
@Getter
@Setter
public class UserProxy {

    private Boolean pass;
    private UUser uUser;
    private String authedToken;

    private UserProxy(Boolean pass, UUser uUser) {
        this.pass = pass;
        this.uUser = uUser;
    }

    private UserProxy(Boolean pass, UUser uUser, String authedToken) {
        this.pass = pass;
        this.uUser = uUser;
        this.authedToken = authedToken;
    }

    private UserProxy() {
    }

    public static UserProxy loginPass(UUser uUser) {
        return new UserProxy(true, uUser);
    }

    public static UserProxy unLoginPass() {
        return new UserProxy(false, new UUser());
    }

    public static UserProxy authedTokenUser(UUser uUser, String authedToken) {
        return new UserProxy(true, uUser, authedToken);
    }
}
