package com.yunjiacloud.nj.httpserver.dao;

import com.yunjiacloud.nj.httpserver.dto.ProductChanelDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.yunjiacloud.nj.httpserver.domain.ProductDo;

@Mapper
public interface ProductDao {
    int insert(@Param("pojo") ProductDo pojo);

    int insertSelective(@Param("pojo") ProductDo pojo);

    int insertList(@Param("pojos") List<ProductDo> pojo);

    int update(@Param("pojo") ProductDo pojo);

    ProductChanelDto findBySysCode(@Param("code") String code);
}
