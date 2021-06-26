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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UUser other = (UUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoginStatus() == null ? other.getLoginStatus() == null : this.getLoginStatus().equals(other.getLoginStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoginStatus() == null) ? 0 : getLoginStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginStatus=").append(loginStatus);
        sb.append(", loginName=").append(loginName);
        sb.append(", loginAliasName=").append(loginAliasName);
        sb.append(", loginPassword=").append(loginPassword);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}