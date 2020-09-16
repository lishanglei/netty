package zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
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
public class OldIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket =new ServerSocket(8899);

        while(true){
            Socket socket =serverSocket.accept();
            DataInputStream  dataInputStream =new DataInputStream(socket.getInputStream());

            try {
                byte[] bytes =new byte[4096];
                while(true){
                    int read = dataInputStream.read(bytes, 0, bytes.length);
                    if(read==-1){
                        break;
                    }


                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
