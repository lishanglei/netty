package com.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/4
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/4              lishanglei      v1.0.0           Created
 */
public class NioTest1 {
    public static void main(String[] args) {


        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i <5; i++) {

            int randomNumber =new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());
        //反转
        buffer.flip();
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());
        while(buffer.hasRemaining()){
            System.out.println("limit:"+buffer.limit());
            System.out.println("position:"+buffer.position());

            buffer.get();
        }
        System.out.println();
        /**
         * limit=position;
         * position=0;
         */
        buffer.flip();
        while(buffer.hasRemaining()){
            System.out.println("limit:"+buffer.limit());
            System.out.println("position:"+buffer.position());

            buffer.get();
        }
    }
}
