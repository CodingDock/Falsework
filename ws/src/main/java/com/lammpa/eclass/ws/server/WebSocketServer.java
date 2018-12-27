package com.lammpa.eclass.ws.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.extern.slf4j.Slf4j;
import org.xmm.netty.PiplineOrder.*;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

@Slf4j
public class WebSocketServer {

//    static final boolean SSL = System.getProperty("ssl") != null;
//    static final int PORT = Integer.parseInt(System.getProperty("port", SSL? "8443" : "8080"));


    public void start(int port){
        log.info("WebSocketServer 启动中......");

       
        final SslContext sslCtx = null;
         /* 是否开启SSL
        if (SSL) {
            try {
                SelfSignedCertificate ssc = new SelfSignedCertificate();
                sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
            } catch (Exception e) {
                log.info("SSL 配置错误，SSL不启用！",e);
            }
        } else {
            sslCtx = null;
        }*/
        
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
//                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_RCVBUF, 10 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 10 * 1024)
                    .option(ChannelOption.TCP_NODELAY, true)
                    
                    /*//使用对象池，重用缓冲区。进行压测看看会不会OOM后再打开
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)*/

                    .childHandler(new WebSocketServerInitializer(sslCtx));
                    
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                protected void initChannel(SocketChannel sc) throws Exception {
//                    
//                    sc.pipeline().addLast(new WangOuthandler1());
//                    sc.pipeline().addLast(new WangOuthandler2());
//                    sc.pipeline().addLast(new WangOuthandler3());
//
//                    sc.pipeline().addLast(new WangInHandler1());
//                    sc.pipeline().addLast(new WangInHandler2());
//                    sc.pipeline().addLast(new WangInHandler3());
//
//                }
//            });
                    
            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("WebSocketServer 启动成功！");

            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("WebSocketServer 启动失败",e);
            throw new RuntimeException("WebSocketServer 启动失败",e);
        }finally{
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        WangNetty4Out3Server server = new WangNetty4Out3Server();
        server.start(8085);
    }
    
}
