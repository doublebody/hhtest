package com.yunjiacloud.nj.httpserver.dto;

import lombok.Data;

/**
 * @author 黄浩(huanghao@yunjiacloud.com)
 * @date 2017/6/6 0006.
 */
@Data
public class ProductChanelDto {
    private Long id;
    private String code;
    private String name;
    private String ip;
    private String port;
    private String protocol;
}
