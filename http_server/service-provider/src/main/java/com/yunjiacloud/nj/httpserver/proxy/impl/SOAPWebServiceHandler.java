package com.yunjiacloud.nj.httpserver.proxy.impl;


import com.yunjiacloud.nj.httpserver.proxy.biz.Const;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class SOAPWebServiceHandler extends ChannelInboundHandlerAdapter {

    private int rsp_code;
    
    private String getProductAddr(String path) {
        String channelType = System.getProperty(Const.CHANNEL_TYPE_D, Const.WS_TYPE);
        String[] paths = path.split("/");
        if (paths.length == 0) {
            throw new RuntimeException("req path is not /user/uri");
        }
        return "http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx?disco";
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("request content:\n" + body);

        if (body != null) {
            int reqDataSize = body.getBytes().length;
            String[] reqDatas = body.split("\r\n");
            String reqPro = reqDatas[0]; // POST / HTTP/1.1
            String[] reqPros = reqPro.split("\\s");
            String path = reqPros[1]; //

            String addr = getProductAddr(path);
            String reqMethod = reqPros[0]; // POST GET
            String rtn = "";
            String soapAction = null;
            int startIdx = body.indexOf("<soapenv:Envelope");
            for (String reqData : reqDatas) {
                if (reqData.startsWith("SOAPAction:")) {
                    soapAction = reqData.substring(reqData.indexOf("\"") + 1, reqData.lastIndexOf("\""));
                    break;
                }
            }
            String endstr = "</soapenv:Envelope>";
            int endIdx = body.indexOf(endstr);
//            body = body.substring(startIdx, endIdx + endstr.length());

            // 调用目标地址，发送消息，返回消息封装
            rtn = sendSOAPReq(addr, "", soapAction);
            
            //req ip
            
            //addr
            //reqMethod
            //reqDataSize
            //rsp_code
            
            
            // 响应
            resp(ctx, rtn);

        } else {
            String reuslts = "HTTP/1.1 500 OK";
            reuslts += "\r\n";
            reuslts += "\r\n";
            resp(ctx, reuslts);
        }

    }

    public String sendRestReq(String url, String reqData, String reqMethod, Map<String, String> heads)
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
            connection.setRequestMethod(reqMethod);
            // connection.setRequestProperty("Content-type", "text/xml");
            // connection.setRequestProperty("Accept", "text/xml");
            // connection.setRequestProperty("User-Agent", "JAX-WS RI
            // 2.1.3-hudson-390-");
            // connection.setRequestProperty("SOAPAction", soapAction);

            connection.getOutputStream().write(reqData.getBytes());
            connection.getOutputStream().flush();
            connection.getOutputStream().close();
            connection.connect();

            isr = new InputStreamReader(connection.getInputStream(), "utf-8");
            reader = new BufferedReader(isr);

            // System.out.println(connection.getResponseCode());
            // System.out.println(connection.getResponseMessage());
            rsp_code = connection.getResponseCode();
            
            sb.append("HTTP/1.1 " + connection.getResponseCode() + " " + connection.getResponseMessage());
            sb.append("\r\n");
            sb.append("\r\n");

            String lines = "";
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
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

    public static String sendSOAPReq(String wsdl, String soapXML, String soapAction) throws IOException {
        HttpURLConnection connection = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        String reuslts = "";
        try {
            URL getUrl = new URL(wsdl);
            connection = (HttpURLConnection) getUrl.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "text/xml");
            connection.setRequestProperty("Accept", "text/xml");
            // connection.setRequestProperty("User-Agent", "JAX-WS RI
            // 2.1.3-hudson-390-");
            connection.setRequestProperty("SOAPAction", soapAction);

            connection.getOutputStream().write(soapXML.getBytes());
            connection.getOutputStream().flush();
            connection.getOutputStream().close();
            connection.connect();

            isr = new InputStreamReader(connection.getInputStream(), "utf-8");
            reader = new BufferedReader(isr);

            // System.out.println(connection.getResponseCode());
            // System.out.println(connection.getResponseMessage());
            reuslts += "HTTP/1.1 " + connection.getResponseCode() + " " + connection.getResponseMessage();
            reuslts += "\r\n";
            reuslts += "\r\n";

            String lines = "";
            while ((lines = reader.readLine()) != null) {
                reuslts += lines.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
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
        System.out.println(reuslts);
        return reuslts;
    }

    public static void main(String[] args) throws IOException {
        String soapXML = "";

         sendSOAPReq("",
         "", "");
    }

    /**
     * 
     * @param xml
     */
    private void resp(ChannelHandlerContext ctx, String xml) {
        // System.out.println(xml);
        ByteBuf resp = Unpooled.copiedBuffer(xml.getBytes());
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
