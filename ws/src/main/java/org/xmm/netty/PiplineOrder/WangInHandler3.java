package org.xmm.netty.PiplineOrder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.xmm.netty.NettyUtil;

public class WangInHandler3 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler3  channelRead");
        System.out.println("WangInHandler3  channelRead receive:"+NettyUtil.parseByteBufMsg(msg));
        ctx.writeAndFlush(NettyUtil.packageStringToByteBuf(" I am inHandler3"));//最后一个indhandler需要ctx.write()而不能使用下面2行
        System.out.println(" WangInHandler3  channelRead over");

    }
    
            @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler3  channelReadComplete");
        super.channelReadComplete(ctx);
        
    }
    
            @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler3  exceptionCaught");
        super.exceptionCaught(ctx, cause);
        
    }
    
            @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler3  channelActive");
        super.channelActive(ctx);
        
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler3  channelInactive");
        super.channelInactive(ctx);
        
    }
    
            @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler3  channelRegistered");
        super.channelRegistered(ctx);
        
    }
    
            @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler3  channelUnregistered");
        super.channelUnregistered(ctx);
        
    }
    
            @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler3  channelWritabilityChanged");
        super.channelWritabilityChanged(ctx);
        
    }
    
            @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler3  userEventTriggered");
        super.userEventTriggered(ctx, evt);
    }
}
