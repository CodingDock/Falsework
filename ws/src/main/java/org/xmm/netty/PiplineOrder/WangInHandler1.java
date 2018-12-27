package org.xmm.netty.PiplineOrder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.xmm.netty.NettyUtil;

public class WangInHandler1 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        
        System.out.println(" WangInHandler1  channelRead");
        String ss=NettyUtil.parseByteBufMsg(msg);
        System.out.println(" WangInHandler1 receive:"+ ss);
        if("a".equals(ss)){
//            ctx.writeAndFlush(NettyUtil.packageStringToByteBuf("WangInHandler1   hahahahah"));
            ctx.writeAndFlush("WangInHandler1   hahahahah");
            System.out.println("WangInHandler1 return ");
            ReferenceCountUtil.release(msg);
            return;
        }
        ctx.write(NettyUtil.packageStringToByteBuf("WangInHandler1   hahahahah"));
        ctx.fireChannelRead(msg);
        ctx.write(NettyUtil.packageStringToByteBuf("WangInHandler1   2222"));
//        ctx.writeAndFlush("WangInHandler1   hahahahah");
        System.out.println(" WangInHandler1  channelRead over");
    }
    
            @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        
        System.out.println(" WangInHandler1  channelReadComplete");
        super.channelReadComplete(ctx);
        
    }
    
            @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        
        System.out.println(" WangInHandler1  exceptionCaught");
        super.exceptionCaught(ctx, cause);
        
    }
    
            @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        
        System.out.println(" WangInHandler1  channelActive");
        super.channelActive(ctx);
        
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        
        System.out.println(" WangInHandler1  channelInactive");
        super.channelInactive(ctx);
        
    }
    
            @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        
        System.out.println(" WangInHandler1  channelRegistered");
        super.channelRegistered(ctx);
        
    }
    
            @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        
        System.out.println(" WangInHandler1  channelUnregistered");
        super.channelUnregistered(ctx);
        
    }
    
            @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx)
            throws Exception {
        
        System.out.println(" WangInHandler1  channelWritabilityChanged");
        super.channelWritabilityChanged(ctx);
        
    }
    
            @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        
        System.out.println(" WangInHandler1  userEventTriggered");
        super.userEventTriggered(ctx, evt);
        
    }
}
