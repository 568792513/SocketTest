package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMultithreading {
    /**
     * Socket�����
     */
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("��������������ȴ��ͻ�������..");

            while (true) {
                Socket socket = serverSocket.accept();// ���������ܵ����׽��ֵ�����,����һ��Socket����
                SocketThread socketThread = new SocketThread(socket);
                socketThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}