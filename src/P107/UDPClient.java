package P107;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getByName("localhost");
        int port = 8888;

        DatagramSocket socket = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            //从键盘获得数据
            byte[] data = scanner.nextLine().getBytes();
            DatagramPacket packet1 = new DatagramPacket(data, data.length, inetAddress, port);
            socket.send(packet1);

            byte[] receiveData = new byte[1024];
            DatagramPacket packet2 =  new DatagramPacket(receiveData, receiveData.length);
            socket.receive(packet2);

            String res = new String(receiveData, 0, packet2.getLength());
            System.out.println("从服务器端修改过的数据: " + res);
        }
    }
}
