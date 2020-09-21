package com.netty.handlerdemo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/21
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/21              lishanglei      v1.0.0           Created
 */
public class ServerHander extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {

        int length =msg.getLength();
        byte[] content =msg.getContent();
        System.out.println("服务端接收到的数据: ");
        System.out.println("长度: "+length);
        System.out.println("内容: "+content);
        System.out.println("服务端接收到消息调地数量: "+(++this.count));

        String responseMessage = UUID.randomUUID().toString();
        int responseLength = responseMessage.getBytes("utf-8").length;
        byte[] responseContent = responseMessage.getBytes("utf-8");

        PersonProtocol responsePerson =new PersonProtocol();
        responsePerson.setLength(responseLength);
        responsePerson.setContent(responseContent);

        ctx.writeAndFlush(responsePerson);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
