package com.yunjiacloud.nj.httpserver.service;

import com.yunjiacloud.nj.httpserver.dto.ProductChanelDto;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.yunjiacloud.nj.httpserver.domain.ProductDo;
import com.yunjiacloud.nj.httpserver.dao.ProductDao;

@Service
public class ProductService{

    @Resource
    private ProductDao productDao;

    public int insert(ProductDo pojo){
        return productDao.insert(pojo);
    }

    public int insertSelective(ProductDo pojo){
        return productDao.insertSelective(pojo);
    }

    public int insertList(List<ProductDo> pojos){
        return productDao.insertList(pojos);
    }

    public int update(ProductDo pojo){
        return productDao.update(pojo);
    }

    public ProductChanelDto findBySysCode(String code){
        return productDao.findBySysCode(code);
    }

}
