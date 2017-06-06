package com.yunjiacloud.nj.httpserver.nettyThread;

import com.yunjiacloud.nj.httpserver.proxy.TcpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author 黄浩(huanghao@yunjiacloud.com)
 * @date 2017/6/6 0006.
 */
@Component
@Slf4j
public class RestThread {

    @Value(value = "${restPort}")
    private String restPort;

    @Resource
    private TcpServer tcpServer;

    @PostConstruct
    public void start(){
        try {
            tcpServer.bind(Integer.parseInt(restPort));
        } catch (Exception e) {
            log.error("启动rest通道失败",e.getMessage());
        }
    }
}
