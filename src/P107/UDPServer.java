package P107;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        byte[] data = new byte[1024];
        DatagramSocket socket = new DatagramSocket(8888);


        while (true) {
            DatagramPacket packet1 = new DatagramPacket(data, data.length);
            socket.receive(packet1);

            InetAddress address = packet1.getAddress();
            int port = packet1.getPort();

            byte[] sendData = new String(data, 0, packet1.getLength()).toUpperCase().getBytes();
            DatagramPacket packet2 = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(packet2);
        }
    }
}
