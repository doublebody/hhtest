package com.yunjiacloud.nj.httpserver.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.yunjiacloud.nj.httpserver.domain.HttpLogDo;
import com.yunjiacloud.nj.httpserver.dao.HttpLogDao;

@Service
public class HttpLogService{

    @Resource
    private HttpLogDao httpLogDao;

    public int insert(HttpLogDo pojo){
        return httpLogDao.insert(pojo);
    }

    public int insertSelective(HttpLogDo pojo){
        return httpLogDao.insertSelective(pojo);
    }

    public int insertList(List<HttpLogDo> pojos){
        return httpLogDao.insertList(pojos);
    }

    public int update(HttpLogDo pojo){
        return httpLogDao.update(pojo);
    }
}
