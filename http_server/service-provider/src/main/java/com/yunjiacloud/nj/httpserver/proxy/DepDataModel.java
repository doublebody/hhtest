package com.yunjiacloud.nj.httpserver.proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DepDataModel {

    private Map<String, Object> eventObjDataMap = new HashMap<String, Object>();
    private Map<String, Object> eventObjSizeMap = new HashMap<String, Object>();
    private Map<String, String> protocalMap = new HashMap<String, String>();

    private String reqProtocal;

    private Date acceptReqTime;
    private Date recvRespTime;

    private int acceptReqDataSize;
    private int recvRespDataSize;

    public int getAcceptReqDataSize() {
        return acceptReqDataSize;
    }

    public void setAcceptReqDataSize(int acceptReqDataSize) {
        this.acceptReqDataSize = acceptReqDataSize;
    }

    public int getRecvRespDataSize() {
        return recvRespDataSize;
    }

    public void setRecvRespDataSize(int recvRespDataSize) {
        this.recvRespDataSize = recvRespDataSize;
    }

    public void addEventObjDataMap(String key, Object obj) {
        eventObjDataMap.put(key, obj);
    }

    public void addEventObjSizeMap(String key, Object obj) {
        eventObjSizeMap.put(key, obj);
    }

    public Date getAcceptReqTime() {
        return acceptReqTime;
    }

    public void setAcceptReqTime(Date acceptReqTime) {
        this.acceptReqTime = acceptReqTime;
    }

    public Date getRecvRespTime() {
        return recvRespTime;
    }

    public void setRecvRespTime(Date recvRespTime) {
        this.recvRespTime = recvRespTime;
    }

    public String getReqProtocal() {
        return reqProtocal;
    }

    public void setReqProtocal(String reqProtocal) {
        this.reqProtocal = reqProtocal;
    }

    public void printAll() {
        StringBuffer sb = new StringBuffer();
        sb.append("acceptReqTime:" + acceptReqTime).append(",");
        sb.append("recvRspTime:" + recvRespTime).append(",");
        sb.append("protocal:").append(protocalMap).append(",");
        sb.append("data:").append(eventObjDataMap).append(",");
        sb.append("size:").append(eventObjSizeMap).append(",");

        System.out.println(sb);

    }

    public void addProtocalMap(String eventName, String value) {
        protocalMap.put(eventName, value);
    }

}
