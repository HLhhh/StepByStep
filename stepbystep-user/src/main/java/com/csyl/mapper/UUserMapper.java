package com.csyl.mapper;

import com.csyl.domain.UUser;

/**
 * @Entity com.csyl.domain.UUser
 */
public interface UUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);

}




