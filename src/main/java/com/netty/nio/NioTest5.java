package com.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/7
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/7              lishanglei      v1.0.0           Created
 */
public class NioTest5 {

    public static void main(String[] args) {

        ByteBuffer buffer =ByteBuffer.allocate(64);
        buffer.putInt(15);
        buffer.putLong(50000000000000L);
        buffer.putDouble(14.123456);
        buffer.putChar('你');
        buffer.putShort((short) 2);
        buffer.putChar('我');

        buffer.flip();
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}
