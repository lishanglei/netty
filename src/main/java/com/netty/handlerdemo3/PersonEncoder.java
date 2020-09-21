package com.netty.handlerdemo3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/21
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/21              lishanglei      v1.0.0           Created
 */
public class PersonEncoder extends MessageToByteEncoder<PersonProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {

        System.out.println("PersonEncoder invoked");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());

    }
}
