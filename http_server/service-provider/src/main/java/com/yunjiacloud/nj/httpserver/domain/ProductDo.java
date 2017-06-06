package com.yunjiacloud.nj.httpserver.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author 黄浩(huanghao@yunjiacloud.com)
 * @date 2017/6/6 0006.
 */
@Data
public class ProductDo {
    private Long id;
    private String name;
    private String code;
    private String netRegion;
    private String ip;
    private String port;
    private String username;
    private String password;
    private Integer productStatus;
    private Integer isStartAudit;
    private Date startAuditTime;
    private Integer isStartMonitor;
    private Date startMonitorTime;
    private Date gmtCreate;
    private Date gmtModified;
    private Long createUserId;
    private Long updateUserId;
}
