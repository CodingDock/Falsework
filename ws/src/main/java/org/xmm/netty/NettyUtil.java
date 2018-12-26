package org.xmm.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;

public class NettyUtil {
    
    public static String parseByteBufMsg(Object msg) throws UnsupportedEncodingException {
        ByteBuf buf=(ByteBuf) msg;
        return new String(ByteBufUtil.getBytes(buf),"UTF-8");
    }
    
    
    public static ByteBuf packageStringToByteBuf(String msg) throws UnsupportedEncodingException {
        return Unpooled.copiedBuffer(msg.getBytes("UTF-8"));
    }
    
}
