package communication;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTest {
    public static void main(String args[]) throws Exception {
        // ����ָ���Ķ˿�
        int port = 8888;
        ServerSocket server = new ServerSocket(port);
        // server��һֱ�ȴ����ӵĵ���
        System.out.println("server��һֱ�ȴ����ӵĵ���");

        //���ʹ�ö��̣߳��Ǿ���Ҫ�̳߳أ���ֹ��������ʱ���������̺߳ľ���Դ
        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        while (true) {
            Socket socket = server.accept();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        // ���������Ӻ󣬴�socket�л�ȡ�����������������������ж�ȡ
                        InputStream inputStream = socket.getInputStream();
                        byte[] bytes = new byte[1024];
                        int len;
                        StringBuilder sb = new StringBuilder();
                        while ((len = inputStream.read(bytes)) != -1) {
                            // ע��ָ�������ʽ�����ͷ��ͽ��շ�һ��Ҫͳһ������ʹ��UTF-8
                            sb.append(new String(bytes, 0, len, "UTF-8"));
                        }
                        System.out.println("get message from client: " + sb);
                        inputStream.close();
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.submit(runnable);
        }
    }
}