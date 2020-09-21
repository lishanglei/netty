package com.netty.handlerdemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/1
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/1              lishanglei      v1.0.0           Created
 */
public class MyServerHandler extends SimpleChannelInboundHandler<Long> {

    protected void channelRead0(ChannelHandlerContext ctx, Long s) throws Exception {
        System.out.println("服务端收到: "+s);
        ctx.writeAndFlush(987654321L);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }


}
