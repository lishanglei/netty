package com.netty.handlerdemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/21
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/21              lishanglei      v1.0.0           Created
 */

public class LongToStringDecoder extends MessageToMessageDecoder<Long> {//泛型 -> 输入数据的类型
    @Override
    protected void decode(ChannelHandlerContext ctx, Long msg, List<Object> out) throws Exception {

        System.out.println("LongToStringDecoder invoked :" +msg);
        out.add(String.valueOf(msg));

    }
}
