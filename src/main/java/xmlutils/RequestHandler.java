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
     * 申请中心公钥
     * @param xmlText
     * @throws Exception
     */
    public static void ApplyCPubKey(String xmlText) throws Exception {

        ApplyCPubKeyRequest applyCPubKeyRequest = new ApplyCPubKeyRequest();

        // 解析公共部分
        CommonRequest commonRequest = MsgUnpack.dealXmlCommon(xmlText);
        // 将解析的值付给子类
        PropertyUtil.fatherToChild(commonRequest, applyCPubKeyRequest);
        // 解析非公共部分
        try {
            Document doc = null;
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            // 获取根节点下的子节点
            String publicKey = rootEle.elementTextTrim("PublicKey");
            applyCPubKeyRequest.setPublicKey(publicKey);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
