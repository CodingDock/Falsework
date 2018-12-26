package org.xmm.netty.PiplineOrder;

import java.net.SocketAddress;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.xmm.netty.NettyUtil;

public class WangOuthandler1 extends ChannelOutboundHandlerAdapter{

    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress,ChannelPromise promise) throws Exception {
        System.out.println(" WangOuthandler1 bind localAddress:"+localAddress.toString());
        super.bind(ctx, localAddress, promise);
    }
    
            @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,SocketAddress localAddress, ChannelPromise promise)throws Exception {
        System.out.println(" WangOuthandler1 connect remoteAddress:"+remoteAddress.toString()+" localAddress:"+localAddress.toString());
        super.connect(ctx, remoteAddress, localAddress, promise);
    }
    
            @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise)
            throws Exception {
        System.out.println(" WangOuthandler1 ctx");
        super.disconnect(ctx, promise);
    }
    
            public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.out.println(" WangOuthandler1 close");
        super.close(ctx, promise);
    }
    
            @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise)
            throws Exception {
        System.out.println(" WangOuthandler1 deregister");
        super.deregister(ctx, promise);
    }
    
            public void read(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" WangOuthandler1 read");
        super.read(ctx);
    }
    
            @Override
    public void write(ChannelHandlerContext ctx, Object msg,
            ChannelPromise promise) throws Exception {
        System.out.println(" WangOuthandler1 write");
        System.out.println("WangOuthandler1 receive " +NettyUtil.parseByteBufMsg(msg));
        System.out.println("WangOuthandler1 send WangOuthandler1");
        ctx.writeAndFlush(NettyUtil.packageStringToByteBuf("WangOuthandler1"));
//        super.write(ctx, msg, promise);
    }
    
            public void flush(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" WangOuthandler1 flush");
        super.flush(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }
}
