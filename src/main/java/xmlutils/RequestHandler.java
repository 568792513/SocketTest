package xmlutils;

import common.PropertyUtil;
import enity.request.ApplyCPubKeyRequest;
import enity.request.CommonRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class RequestHandler {

    /**
     * �������Ĺ�Կ
     * @param xmlText
     * @throws Exception
     */
    public static void ApplyCPubKey(String xmlText) throws Exception {

        ApplyCPubKeyRequest applyCPubKeyRequest = new ApplyCPubKeyRequest();

        // ������������
        CommonRequest commonRequest = MsgUnpack.dealXmlCommon(xmlText);
        // ��������ֵ��������
        PropertyUtil.fatherToChild(commonRequest, applyCPubKeyRequest);
        // �����ǹ�������
        try {
            Document doc = null;
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            // ��ȡ���ڵ��µ��ӽڵ�
            String publicKey = rootEle.elementTextTrim("PublicKey");
            applyCPubKeyRequest.setPublicKey(publicKey);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
