package com.yunjiacloud.nj.httpserver.proxy.impl;

import com.alibaba.druid.util.StringUtils;
import com.yunjiacloud.nj.httpserver.domain.HttpLogDo;
import com.yunjiacloud.nj.httpserver.dto.ProductChanelDto;
import com.yunjiacloud.nj.httpserver.service.HttpLogService;
import com.yunjiacloud.nj.httpserver.service.ProductService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;

public class RestWebServiceHandler extends ChannelInboundHandlerAdapter {
    private ProductService productService;
    private HttpLogService httpLogService;

    public RestWebServiceHandler(ProductService productService,HttpLogService httpLogService){
        this.productService = productService;
        this.httpLogService = httpLogService;

    }

    private String getProductAddr(String path, String channelType) {
        ProductChanelDto productChanelDto = productService.findBySysCode("WSXT");

        String[] paths = path.split("/");
        if(paths.length == 0){
            throw new RuntimeException("req path is not /user/uri");
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("http://").append(productChanelDto.getIp()).append(":")
                .append(productChanelDto.getPort()).append(path);
//        return "http://192.168.100.127:8088/api/staticData/getByType?codeType=CASE_TYPE";
        return buffer.toString();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HttpLogDo httpLogDo = new HttpLogDo();

        InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                .remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        httpLogDo.setRemoteAddr(clientIP);
        String body = (String) msg;

        System.out.println("request content:\n" + body);
        if (body != null) {
            int reqDataSize = body.getBytes().length;
            httpLogDo.setBodyBytesSent(Long.valueOf(reqDataSize));
            String[] reqDatas = body.split("\r\n");
            String reqPro = reqDatas[0]; // POST / HTTP/1.1
            String[] reqPros = reqPro.split("\\s");
            String path = reqPros[1];
            httpLogDo.setRequest(path);

            String url =getProductAddr(path,"1");
            int startIdx = body.indexOf("{");
            int endIdx = body.indexOf("}");
            String reqData ="";
            if(startIdx >0 && endIdx>0 && endIdx>startIdx) {
                reqData = body.substring(startIdx, endIdx + 1);
                System.out.println(reqData);
            }
            String product_code ="";
            for(String reqdata :reqDatas){
                if(reqdata.startsWith("product_code")){
                    product_code = reqdata.substring(13,reqdata.length());
                    System.out.println(product_code);
                }

            }
            String reqMethod = reqPros[0]; // POST GET
            httpLogDo.setMethod(reqMethod);
            String rtn = "";
            httpLogDo.setBodyBytesReceive(Long.valueOf(rtn.getBytes().length));
            httpLogDo.setStatus(200L);
            httpLogDo.setTimeLocal(new Date());
            rtn =sendRestReq(url,reqData,reqMethod);
            resp(ctx, rtn);
            httpLogService.insertSelective(httpLogDo);
        } else {
            String reuslts = "HTTP/1.1 500 OK";
            reuslts += "\r\n";
            reuslts+=
            reuslts += "\r\n";
            resp(ctx, reuslts);
        }
    }

    public String sendRestReq(String url, String reqData, String reqMethod)
            throws IOException {
        HttpURLConnection connection = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL getUrl = new URL(url);
            connection = (HttpURLConnection) getUrl.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            if(!StringUtils.isEmpty(reqData)) {
                connection.getOutputStream().write(reqData.getBytes());
                connection.getOutputStream().flush();
                connection.getOutputStream().close();
            }
            connection.setRequestMethod(reqMethod);
            connection.connect();

            if(connection.getResponseCode()==200) {
                isr = new InputStreamReader(connection.getInputStream(), "utf-8");
                reader = new BufferedReader(isr);

                sb.append("HTTP/1.1 " + connection.getResponseCode() + " " + connection.getResponseMessage());
                sb.append("\r\n");
                sb.append("Content-Type: application/json;charset=UTF-8");
                sb.append("\r\n");
                sb.append("\r\n");

                String lines = "";
                while ((lines = reader.readLine()) != null) {
                    sb.append(lines);
                }
            }
            else {
                sb.append("HTTP/1.1 " + connection.getResponseCode() + " " + connection.getResponseMessage());
                sb.append("\r\n");
                sb.append("Content-Type: application/json;charset=UTF-8");
                sb.append("\r\n");
                sb.append("\r\n");
                sb.append("出错了");
            }
        } catch (Exception ex) {

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return sb.toString();
    }



    public static void main(String[] args) throws IOException {
    }

    /**
     * 
     * @param xml
     */
    private void resp(ChannelHandlerContext ctx, String xml) {
        // System.out.println(xml);
        ByteBuf resp = null;
        Charset charset = Charset.forName("UTF-8");
        resp = Unpooled.copiedBuffer(xml,charset);
        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
    }

    public static boolean equal(String var, String cons) {
        return isNotEmpty(var) && cons.equals(var);
    }

    private static boolean isNotEmpty(String s) {
        return (null != s && !"".equals(s));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // super.exceptionCaught(ctx, cause);
        ctx.close();
    }

}
