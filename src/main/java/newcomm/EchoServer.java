package newcomm;


import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        // nio�߳��� ���ڽ������Կͻ��˵�����
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // nio�߳��� ���ڴ����ѱ����յ�����
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            sb.group(group, bossGroup) // ���̳߳�
                    .channel(NioServerSocketChannel.class) // ָ��ʹ�õ�channel
                    .localAddress(this.port)// �󶨼����˿�
                    .childHandler(new ChannelInitializer<SocketChannel>() { // �󶨿ͻ�������ʱ�򴥷�����

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            System.out.println("��Ϣ����һ�ͻ������ӵ��������" + "IP:" + ch.remoteAddress().toString() + "Port:" + ch.localAddress().getPort());
                            ch.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
                            ch.pipeline().addLast(new EchoServerHandler()); // �ͻ��˴�������
                            ch.pipeline().addLast(new ByteArrayEncoder());
                        }
                    });
            ChannelFuture cf = sb.bind().sync(); // �������첽������
            System.out.println(EchoServer.class + " �������ڼ����� " + cf.channel().localAddress());
            cf.channel().closeFuture().sync(); // �رշ�����ͨ��
        } finally {
            group.shutdownGracefully().sync(); // �ͷ��̳߳���Դ
            bossGroup.shutdownGracefully().sync();
        }
    }

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
        new EchoServer(port).start(); // ����
    }
}