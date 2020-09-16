package zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
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
public class NewNIOServer {

    public static void main(String[] args) throws IOException {

        InetSocketAddress socketAddress = new InetSocketAddress(8899);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        //一个tcp连接被关闭后,仍有一小段时间处于timeWait状态,这段时间是不能在被连接的,设置为true允许在TimeWait状态建立连接
        serverSocket.setReuseAddress(true);
        serverSocket.bind(socketAddress);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {

            /**
             * 接收一个向这个socket发起连接的请求.
             * 如果这个channel处于非阻塞状态且没有挂起的连接会立即返回null,否则会一直处于阻塞状态只要一个新的连接可用或I/O发生异常
             *
             * 如果这个方法返回一个SocketChannel,那么这个SocketChannel一定处于阻塞模式,不论ServerSocketChannel处于何种状态
             */
            SocketChannel socketChannel = serverSocketChannel.accept();
            //默认就是阻塞的
            socketChannel.configureBlocking(true);

            int readCount = 0;
            int total =0;
            while (-1 != readCount) {
                System.out.println("===================");
                try {
                    readCount = socketChannel.read(byteBuffer);
                    total+=readCount;
                    //  position = 0;
                    //  mark = -1;
                    byteBuffer.rewind();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
            System.out.println(total);
        }

    }
}
