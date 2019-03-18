package newcomm;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import xmlutils.FileContentReader;
import xmlutils.MsgUnpack;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /*
     * channelAction
     *
     * channel ͨ�� action ��Ծ��
     *
     * ���ͻ����������ӷ���˵����Ӻ����ͨ�����ǻ�Ծ���ˡ�Ҳ���ǿͻ��������˽�����ͨ��ͨ�����ҿ��Դ�������
     *
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress().toString() + " ͨ���Ѽ��");
    }

    /*
     * channelInactive
     *
     * channel ͨ�� Inactive ����Ծ��
     *
     * ���ͻ��������Ͽ�����˵����Ӻ����ͨ�����ǲ���Ծ�ġ�Ҳ����˵�ͻ��������˵Ĺر���ͨ��ͨ�����Ҳ����Դ�������
     *
     */
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress().toString() + " ͨ������Ծ��");
        // �ر���

    }

    /**
     *
     * @author Taowd
     * �˴����������յ��������к������ĵ�ʱ  �������������
     * 2017��8��31�� ����7:57:28
     * @param buf
     * @return
     */
    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ���ܣ���ȡ���������͹�������Ϣ
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // ��һ�֣������ַ���ʱ�Ĵ���
        ByteBuf buf = (ByteBuf) msg;
        String rev = getMessage(buf);
        System.out.println("�ͻ����յ�����������:" + rev);
        // ���ݽ��յ��ı��Ļ�ȡ������
        String transCode = MsgUnpack.getTransCode(rev);
//        MsgUnpack.dealXmlRequest(rev);

        // ͨ���������Ľ������ȡ�ļ�
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(transCode);
        ctx.write(response);
        ctx.flush();
    }

    /**
     * ���ܣ���ȡ��Ͽͻ��˷��͹���������֮��Ĳ���
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("����˽����������..");
        // ��һ�ַ�����дһ���յ�buf����ˢ��д��������ɺ�ر�sock channel���ӡ�
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        // ctx.flush();
        // �ڶ��ַ�������client�˹ر�channel���ӣ������Ļ����ᴥ������channelReadComplete������
        // ctx.flush().close().sync(); // �����֣��ĳ�����д��Ҳ���ԣ���������д����û�е�һ�ַ����ĺá�
    }

    /**
     * ���ܣ�����˷����쳣�Ĳ���
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("�쳣��Ϣ��\r\n" + cause.getMessage());
    }
}
