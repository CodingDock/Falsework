package org.xmm.netty.PiplineOrder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.xmm.netty.NettyUtil;

public class WangInHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler2  channelRead");
        System.out.println(" WangInHandler2 receive:"+ NettyUtil.parseByteBufMsg(msg));
//        ctx.writeAndFlush("WangInHandler2  hahahahah");
        ctx.fireChannelRead(NettyUtil.packageStringToByteBuf("I am handler2"));
        System.out.println(" WangInHandler2  channelRead over");

    }
    
            @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler2  channelReadComplete");
        super.channelReadComplete(ctx);
        
    }
    
            @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler2  exceptionCaught");
        super.exceptionCaught(ctx, cause);
        
    }
    
            @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler2  channelActive");
        super.channelActive(ctx);
        
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler2  channelInactive");
        super.channelInactive(ctx);
        
    }
    
            @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler2  channelRegistered");
        super.channelRegistered(ctx);
        
    }
    
            @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler2  channelUnregistered");
        super.channelUnregistered(ctx);
        
    }
    
            @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler2  channelWritabilityChanged");
        super.channelWritabilityChanged(ctx);
        
    }
    
            @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println(" WangInHandler2  userEventTriggered");
        super.userEventTriggered(ctx, evt);
        
    }
}