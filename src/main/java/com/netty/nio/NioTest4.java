package com.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/7
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/7              lishanglei      v1.0.0           Created
 */
public class NioTest4 {
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream =new FileInputStream("input.txt");
        FileOutputStream fileOutputStream =new FileOutputStream("output.txt");

        FileChannel inputChannel =fileInputStream.getChannel();
        FileChannel outputChannel=fileOutputStream.getChannel();

        ByteBuffer buffer =ByteBuffer.allocate(512);
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());
        while
        (true){
            buffer.clear();//这一行不能注释
            /**
             * 当limit==position时,read返回0
             */
            int read =inputChannel.read(buffer);
            System.out.println("read:"+read);
            if(read==-1){
                break;
            }
            buffer.flip();
            outputChannel.write(buffer);
            System.out.println(buffer.limit());
            System.out.println(buffer.position());
            int read1 =inputChannel.read(buffer);
            System.out.println("read:"+read1);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
