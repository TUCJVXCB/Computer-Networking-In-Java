package P111;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            out.writeUTF(str);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("客户端收到了被服务器修改过的数据: " + in.readUTF());
        }
    }
}
