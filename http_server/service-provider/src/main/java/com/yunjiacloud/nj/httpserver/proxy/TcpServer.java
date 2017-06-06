package com.yunjiacloud.nj.httpserver.proxy;

import com.yunjiacloud.nj.httpserver.proxy.biz.Const;
import com.yunjiacloud.nj.httpserver.proxy.impl.RestWebServiceHandler;
import com.yunjiacloud.nj.httpserver.service.HttpLogService;
import com.yunjiacloud.nj.httpserver.service.ProductService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TcpServer {
    @Resource
    private ProductService productService;

    @Resource
    private HttpLogService httpLogService;



    public void bind(int port) throws Exception {

        // 配置服务端Nio线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            // 绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();

        } finally {
            // 退出时释放资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            ChannelPipeline pipeline = channel.pipeline();
            pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
            pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
//            pipeline.addLast(new HttpServerCodec());
//            pipeline.addLast(new HttpObjectAggregator(2048));
            String channelType = System.getProperty(Const.CHANNEL_TYPE_D, Const.WS_TYPE);
            if (Const.WS_TYPE.equals(channelType)) {
                pipeline.addLast(new RestWebServiceHandler(productService,httpLogService));
            } else {
                pipeline.addLast(new RestWebServiceHandler(productService,httpLogService));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8280;
        new TcpServer().bind(port);
    }

}
