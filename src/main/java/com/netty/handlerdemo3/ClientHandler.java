package com.netty.handlerdemo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/21
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/21              lishanglei      v1.0.0           Created
 */
public class ClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {

        int length =msg.getLength();
        byte[] content =msg.getContent();
        System.out.println("客户端接收到的数据: ");
        System.out.println("长度: "+length);
        System.out.println("内容: "+new String(content));
        System.out.println("客户端接收到消息调地数量: "+(++this.count));


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0;i<10;i++){

            String messageToBeSent ="send  from  client";
            byte[] content =messageToBeSent.getBytes(Charset.forName("utf-8"));
            int length =content.length;

            PersonProtocol personProtocol =new PersonProtocol();
            personProtocol.setLength(length);
            personProtocol.setContent(content);

            ctx.writeAndFlush(personProtocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
