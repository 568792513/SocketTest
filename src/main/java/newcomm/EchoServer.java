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
        EventLoopGroup bossGroup = new NioEventLoopGroup();

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

                            System.out.println("��Ϣ����һ�ͻ������ӵ��������" + "IP:" + ch.localAddress().getHostName() + "Port:" + ch.localAddress().getPort());
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

        new EchoServer(8888).start(); // ����
    }
}