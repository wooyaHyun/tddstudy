package com.example.tddstudy.domain.Membership;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface MembershipRepository {

    @Insert("INSERT INTO MEMBERSHIP (MEMBERSHIP_NAME, USER_ID, POINT) VALUES(#{membershipName}, #{userId}, #{point})")
    public int save(Map<String, Object> param);

    @Select("SELECT * FROM MEMBERSHIP WHERE USER_ID=#{id} AND MEMBERSHIP_NAME=#{membershipName}")
    public Map<String, Object> findByUserIdAndMembershipType(@Param("id") String id, @Param("membershipName") MembershipType membershipName) ;
}
