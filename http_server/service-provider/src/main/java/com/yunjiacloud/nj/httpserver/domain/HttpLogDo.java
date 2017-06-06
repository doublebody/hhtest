package com.yunjiacloud.nj.httpserver.domain;

import lombok.Data;
import java.util.Date;

/**
 * @author 黄浩(huanghao@yunjiacloud.com)
 * @date 2017/6/6 0006.
 */
@Data
public class HttpLogDo {
    private Long id;
    private String remoteAddr;
    private String request;
    private Date timeLocal;
    private String method;
    private Long bodyBytesSent;
    private Long bodyBytesReceive;
    private Long status;
}
