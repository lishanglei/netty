package zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/10              lishanglei      v1.0.0           Created
 */
public class NewNioClient {

    public static void main(String[] args) throws IOException {

            SocketChannel socketChannel =SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost",8899));
            socketChannel.configureBlocking(true);

            String fileName ="C:\\Users\\10025\\Downloads\\分布式检索ES课程资料.zip";
            FileChannel fileChannel =new FileInputStream(fileName).getChannel();

            long startTime =System.currentTimeMillis();
            /**
             * 将给定的文件内容,从0开始,一直读取文件长度的内容写入到指定的 WritableByteChannel当中,
             * 但是如果文件过大,可能读取不了文件整个长度的内容
             */
            int position=0;
            long count =fileChannel.size();
            long total =0;
            while(count>0){
                System.out.println("=======================");
                long transfer = fileChannel.transferTo(position, count, socketChannel);
                if(transfer>0){
                    position+=transfer;
                }
                count-=transfer;
                total+=transfer;
            }
            //发送总字节数: 710867460 ,耗时: 17
            System.out.println("发送总字节数: "+fileChannel.size()+",transfer:"+total+" ,耗时: "+(System.currentTimeMillis()-startTime));
            fileChannel.close();
    }
}
