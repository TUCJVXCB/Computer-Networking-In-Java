package P119.T1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用Java开发一个简易的Web服务器
 */
public class WebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);

        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = reader1.readLine();

            String[] split = readLine.split(" ");
            if (split.length != 3) {
                return;
            }
            String path = split[1].substring(1);
            File file = new File(path);

            if (!file.exists()) {
                StringBuilder error = new StringBuilder();
                error.append("HTTP/1.1 404 file not found\n");
                error.append("Content-Type:text/html\r\n");
                error.append("Content-Length:20\n").append("\n");
                error.append("<h1>File Not Found..</h1>");

                try {
                    outputStream.write(error.toString().getBytes());
                    outputStream.flush();
                    outputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            BufferedReader reader2 = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;

            sb.append("HTTP/1.1 200 OK\n");
            sb.append("Content-Type:text/html\n");
            sb.append("Content-Length:" + file.length() + "\n");
            sb.append("\n");

            while ((line = reader2.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println(sb.toString());
            outputStream.write(sb.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }
}
