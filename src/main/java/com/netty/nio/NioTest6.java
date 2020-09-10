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
public class NioTest6 {

    public static void main(String[] args) {

        ByteBuffer buffer =ByteBuffer.allocate(10);
        for(int i=0;i<buffer.capacity();i++){
            buffer.put((byte)i);
        }

        buffer.position(2);
        buffer.limit(6);
        /**
         * slice()方法会截取原有buffer生成一个新的buffer,但是原buffer和新buffer共享底层数组,所以不管修改哪个buffer都会另一个buffer
         * 两个buffer的position/limit/capacity各自独立
         */
        ByteBuffer sliceBuffer =buffer.slice();

        for(int i=0;i<sliceBuffer.capacity();i++){

            byte b =sliceBuffer.get(i);
            System.out.println((short)b);
            b*=2;
            sliceBuffer.put(i,b);
        }

        System.out.println();
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }
}
