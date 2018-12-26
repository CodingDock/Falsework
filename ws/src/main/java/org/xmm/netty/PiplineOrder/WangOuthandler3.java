package org.xmm.netty.PiplineOrder;

import java.net.SocketAddress;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.xmm.netty.NettyUtil;

public class WangOuthandler3 extends ChannelOutboundHandlerAdapter{

       @Override
       public void bind(ChannelHandlerContext ctx, SocketAddress localAddress,ChannelPromise promise) throws Exception {
              System.out.println(" WangOuthandler3 bind localAddress:"+localAddress.toString());
              super.bind(ctx, localAddress, promise);
       }
       
               @Override
       public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,SocketAddress localAddress, ChannelPromise promise)throws Exception {
              System.out.println(" WangOuthandler3 connect remoteAddress:"+remoteAddress.toString()+" localAddress:"+localAddress.toString());
              super.connect(ctx, remoteAddress, localAddress, promise);
       }
       
               @Override
       public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise)
                     throws Exception {
              System.out.println(" WangOuthandler3 ctx");
              super.disconnect(ctx, promise);
       }
       
               public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
              System.out.println(" WangOuthandler3 close");
              super.close(ctx, promise);
       }
       
               @Override
       public void deregister(ChannelHandlerContext ctx, ChannelPromise promise)
                     throws Exception {
              System.out.println(" WangOuthandler3 deregister");
              super.deregister(ctx, promise);
       }
       
               public void read(ChannelHandlerContext ctx) throws Exception {
              System.out.println(" WangOuthandler3 read");
              super.read(ctx);
       }
       
               @Override
       public void write(ChannelHandlerContext ctx, Object msg,
                     ChannelPromise promise) throws Exception {
              System.out.println(" WangOuthandler3 write");
              System.out.println("WangOuthandler3 receive " + NettyUtil.parseByteBufMsg(msg));
              System.out.println("WangOuthandler3 send WangOuthandler3");
//              ctx.writeAndFlush(NettyUtil.packageStringToByteBuf("WangOuthandler3"));
//               ctx.write(NettyUtil.packageStringToByteBuf("WangOuthandler3"), promise);
               ctx.write(NettyUtil.packageStringToByteBuf("WangOuthandler3"), promise);
//              super.write(ctx, msg, promise);
       }
       
               public void flush(ChannelHandlerContext ctx) throws Exception {
              System.out.println(" WangOuthandler3 flush");
              super.flush(ctx);
       }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}