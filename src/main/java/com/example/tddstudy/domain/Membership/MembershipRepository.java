package com.example.tddstudy.domain.Membership;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MembershipRepository {

    @Insert("INSERT INTO MEMBERSHIP (MEMBERSHIP_NAME, USER_ID, POINT) VALUES(#{membershipName}, #{userId}, #{point})")
    public int save(Membership membership);
}
