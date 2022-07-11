package com.example.tddstudy.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HelloRepository {

    @Select("SELECT FORMATDATETIME(now(), 'yyyy-MM-dd') FROM DUAL")
    public String helloGetNow();
}
