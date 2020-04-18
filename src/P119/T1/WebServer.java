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
            OutputStream out = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = reader.readLine();
            String[] split = readLine.split(" ");
            if (split.length != 3) {
                return;
            }
            //相对地址
            String path = split[1];

            File file = new File(path.substring(1));
            if (!file.exists()) {
                StringBuffer error = new StringBuffer();
                error.append("HTTP/1.1 404 file not found\r\n");
                error.append("Content-Type:text/html \r\n");
                error.append("Content-Length:20 \r\n").append("\r\n");
                error.append("<h1 >File Not Found..</h1>");

                try {
                    out.write(error.toString().getBytes());
                    out.flush();
                    out.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            BufferedReader reader1 = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader1.readLine()) != null) {
                sb.append(line).append("\r\n");
            }

            System.out.println(sb);

            StringBuilder result = new StringBuilder();
            result.append("HTTP/1.1 200 OK \r\n");
            result.append("Content-Type:text/html \r\n");
            result.append("Content-Length:" + file.length() + "\r\n");
            result.append("\r\n" + sb.toString());
            System.out.println(result);
            out.write(result.toString().getBytes());
            out.flush();
            out.close();
        }
    }
}
