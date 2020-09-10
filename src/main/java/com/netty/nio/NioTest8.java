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
public class NioTest8 {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream =new FileInputStream("input2.txt");
        FileOutputStream fileOutputStream =new FileOutputStream("output2.txt");

        FileChannel inputChannel =fileInputStream.getChannel();
        FileChannel outputChannel=fileOutputStream.getChannel();

        ByteBuffer buffer =ByteBuffer.allocateDirect(512);

        while (true){
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
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
