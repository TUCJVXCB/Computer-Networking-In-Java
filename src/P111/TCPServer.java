package P111;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        DataInputStream in = new DataInputStream(socket.getInputStream());

        while (true) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(in.readUTF().toUpperCase());
        }
    }
}
