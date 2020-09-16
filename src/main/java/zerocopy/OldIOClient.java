package zerocopy;

import java.io.*;
import java.net.Socket;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/10              lishanglei      v1.0.0           Created
 */
public class OldIOClient {

    public static void main(String[] args) throws IOException {

        Socket socket =new Socket("127.0.0.1",8899);
        String fileName ="C:\\Users\\10025\\Downloads\\分布式检索ES课程资料.zip";
        InputStream inputStream =new FileInputStream(new File(fileName));

        DataOutputStream dataOutputStream =new DataOutputStream(socket.getOutputStream());
        byte[] buffer =new byte[4096];
        long readCount =0;
        long total =0;

        Long startTime =System.currentTimeMillis();
        while((readCount=inputStream.read(buffer))>=0){

            total+=readCount;
            dataOutputStream.write(buffer);
        }
        //发送总字节数: 710867460 ,耗时: 2866
        System.out.println("发送总字节数: "+total+" ,耗时: "+(System.currentTimeMillis()-startTime));
    }
}
