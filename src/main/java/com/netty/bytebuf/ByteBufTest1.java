package com.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/18
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/18              lishanglei      v1.0.0           Created
 */
public class ByteBufTest1 {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world 李", Charset.forName("utf-8"));

        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();
            System.out.println(new String(array, Charset.forName("utf-8")));
        }

    }
}
