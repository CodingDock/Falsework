package org.xmm.netty.PiplineOrder;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloClient {
    
            
            public void connect(String ip,int port) throws InterruptedException{
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println("msg from server:"+msg);
                            }
                        });
                    }
                });
            Channel channel = bootstrap.connect(ip, port).sync().channel();

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String msg = console.readLine();
                if (msg == null) {
                    continue;
                } else if ("bye".equals(msg.toLowerCase())) {
                    channel.writeAndFlush("bye");
                    channel.closeFuture().sync();
                    break;
                } else {
                    channel.writeAndFlush(msg);
                }
            }

//            channel.closeFuture().sync();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
        
    }
    
            public static void main(String[] args) {
        //问题，
//      1、服务端使用的是ServerBootStrap ，而客户端使用的是BootStrap
//      2、服务端的Channel使用的是NioServerSocketchannel，而客户端使用的是NioSocketChannel
//      3、handler()中server端使用的是ServerSocketChannel,而客户端使用的是SocketChannel
//      4、future.channel().closeFuture().sync(),要写sync()，否则就直接退出了
//      5、ChannelInboundHandlerAdapter中 channelActive如果是客户端，在这里就尅发送消息，ctx.channel()可以获得Socket
        HelloClient client = new HelloClient();
        try {
            client.connect("127.0.0.1", 8085);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}