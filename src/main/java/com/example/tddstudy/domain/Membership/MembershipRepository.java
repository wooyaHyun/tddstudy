package com.example.tddstudy.domain.Membership;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MembershipRepository {

    @Insert("INSERT INTO MEMBERSHIP (MEMBERSHIP_NAME, USER_ID, POINT) VALUES(#{membershipName}, #{userId}, #{point})")
    public int save(Membership membership);

    @Select("SELECT * FROM MEMBERSHIP WHERE USER_ID=#{id} AND MEMBERSHIP_NAME=#{membershipName}")
    public Membership findByUserIdAndMembershipType(@Param("id") String id, @Param("membershipName") MembershipType membershipName) ;
}
