package communication;

import java.net.ServerSocket;

public class TimeServer {
    public static void main(String[] args) throws Exception {
        int port = 8888;
        // ���ݴ���Ĳ���ȷ���˿ں�  Ĭ��8888
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            serverSocket.accept();

        }




    }
}
