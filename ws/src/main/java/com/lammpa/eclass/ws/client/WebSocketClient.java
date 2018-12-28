package com.lammpa.eclass.ws.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.xmm.netty.NettyUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class WebSocketClient {
    
    public static void connect(String ip,int port){
        log.info("WebSocketClient start......");
        EventLoopGroup worker = new NioEventLoopGroup();
        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("ping&pong",new IdleStateHandler(10,30,0));
                            
                            ch.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                                    System.out.println("msg from server:"+ NettyUtil.parseByteBufMsg(msg));
                                }
                            });
                        }
                    });
            Channel channel = bootstrap.connect(ip, port).sync().channel();

            System.out.println("链接成功……");

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String msg = console.readLine();
                if (msg == null) {
                    continue;
                } else if ("bye".equals(msg.toLowerCase())) {
                    channel.writeAndFlush(NettyUtil.packageStringToByteBuf("bye"));
                    channel.closeFuture().sync();
                    break;
                } else {
                    channel.writeAndFlush(NettyUtil.packageStringToByteBuf(msg));
                }
            }

//            channel.closeFuture().sync();

        } catch (Exception e) {
            log.error("WebSocketClient 连接失败",e);
            throw new RuntimeException("WebSocketClient 连接失败",e);
        }finally {
            worker.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        connect("127.0.0.1", 8085);
    }
    
}
