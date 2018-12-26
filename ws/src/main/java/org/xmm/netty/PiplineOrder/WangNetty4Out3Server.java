package org.xmm.netty.PiplineOrder;

import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
  * 这个用于测试handler的传递功能
  * @author wangzg
  * 
  * 本例子使用的是 实现的handler中，各个函数都是先super，然后打印，除了handler1中的channelRead,这样打印出的顺序是，先2后1,也就是最后加进去的，
  * 先做，先进去的后做，跟网上的不一致
  * 
  * 本例子中各个handler中的方法，都在最后使用了super()的方法，顺序和网上的一致
  *
  * 本例子将用于数据解析
  * 本例的例子是inhandler2 和inhandler3都发送了数据，但是客户断接收的时候，是连在一起的，因此这里需要想办法，也就是连包拆包
  * 
  * 本例子发现个问题，在Inhandler中都可以使用ctx.writeAndflush(msg),但这样的做法会造成，如果有outhandler的时候，发送回客户端的数据，有多个
  * outhandler发回的数据（都是一样的，个数是inhander中ctx.writeAndFlush()个数），如何解决的，既然需要msg向后传递，又不能
  * 有多个重复数据发回，那就需要在inhandler的channelRead()不能使用super.channelread(),而是使用ctx.fireChannelRead(加工过的msg)
  * 每一次inhandler的super()或者ctx.firechannelread，操作触发outhandler的write()操作和下一个inhandler的channelread操作，每一次
  * inhandler的ctx.write()操作，触发outhandler的write()中的ctx.write()操作
  */
public class WangNetty4Out3Server {

    private static final Logger LOGGER = LoggerFactory.getLogger(WangNetty4Out3Server.class);
    
            public void start(int port){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(boss, work)
            .channel(NioServerSocketChannel.class)
            .handler(new LoggingHandler(LogLevel.DEBUG))
            .option(ChannelOption.SO_BACKLOG, 1024)
//            .option(ChannelOption.SO_TIMEOUT, 5 * 1000)
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            .childOption(ChannelOption.SO_RCVBUF, 10 * 1024)
            .childOption(ChannelOption.SO_SNDBUF, 10 * 1024)
            .childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel sc) throws Exception {
                    sc.pipeline().addLast(new WangOuthandler1());
                    sc.pipeline().addLast(new WangOuthandler2());
                    sc.pipeline().addLast(new WangOuthandler3());
                    
                    sc.pipeline().addLast(new WangInHandler1());
                    sc.pipeline().addLast(new WangInHandler2());
                    sc.pipeline().addLast(new WangInHandler3());
                    
//                    sc.pipeline().addLast(new WangOuthandler1());
                    
                }
            });
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            LOGGER.error("server 启动失败",e);
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