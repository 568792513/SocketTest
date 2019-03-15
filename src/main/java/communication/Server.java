package communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            //����һ��ServerSocket�����˿�8082
            ServerSocket server = new ServerSocket(8082);
            //�ȴ�����
             Socket socket = server.accept();
            // ���յ������ʹ��socket����ͨ�ţ�����BufferedReader���ڶ�ȡ����
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = is.readLine();
            System.out.println("receivef from client: " + line);
            //����PrintWriter�����ڷ�������
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("receivef data: " + line);
            pw.flush();
            //�ر���Դ
            pw.close();
            is.close();
            socket.close();
            server.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
