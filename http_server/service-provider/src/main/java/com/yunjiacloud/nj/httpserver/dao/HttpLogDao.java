package com.yunjiacloud.nj.httpserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.yunjiacloud.nj.httpserver.domain.HttpLogDo;

@Mapper
public interface HttpLogDao {
    int insert(@Param("pojo") HttpLogDo pojo);

    int insertSelective(@Param("pojo") HttpLogDo pojo);

    int insertList(@Param("pojos") List<HttpLogDo> pojo);

    int update(@Param("pojo") HttpLogDo pojo);
}
