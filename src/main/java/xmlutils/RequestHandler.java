package xmlutils;

import com.union.api.UnionCSSP;
import common.PropertyUtil;
import enity.request.ApplyCPubKeyRequest;
import enity.request.CommonRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.LinkedHashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestHandler {

    private static UnionCSSP unionCSSP;
    private static Integer resultCode;
    /**
     * 申请中心公钥
     * @param xmlText
     * @throws Exception
     */
    public static String ApplyCPubKey(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();
//        ApplyCPubKeyRequest applyCPubKeyRequest = new ApplyCPubKeyRequest();
        // 解析公共部分
//        CommonRequest commonRequest = MsgUnpack.dealXmlCommon(xmlText);
        // 将解析的值付给子类
//        PropertyUtil.fatherToChild(commonRequest, applyCPubKeyRequest);
        // 解析非公共部分
        try {
            Document doc = null;
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            // 获取根节点下的子节点
//            String publicKey = rootEle.elementTextTrim("PublicKey");
//            applyCPubKeyRequest.setPublicKey(publicKey);
//            applyCPubKeyRequest.getLockID();
//          解析公共部分
            parseCommonXml(rootEle, tempValueMap);

            // 解析非公共部分
            String PublicKey = rootEle.elementTextTrim("PublicKey");
            tempValueMap.put("PublicKey",PublicKey);

            // 获取锁具ID
            String LockID = tempValueMap.get("LockID");
            // 调用  获取中心公钥
            UnionCSSP.Recv recv = unionCSSP.UnionGetCenterPK( LockID + ".PK");
            String pkValue = recv.getPkValue();

            // 调用  导入机构SM2公钥
            UnionCSSP.Recv recv1 = unionCSSP.UnionGenAgent( LockID + ".SM2", LockID + ".ZMK", LockID + ".ZEK", LockID + ".ZEK2", pkValue);
            resultCode = recv1.getResponseCode();

            // 添加返回字段值
            // 返回码  错误信息
            if (resultCode != 0) {
                tempValueMap.put("ReturnCode", "0000");
                tempValueMap.put("ReturnMsg", "success");

            } else {
                tempValueMap.put("ReturnCode", resultCode.toString());
                tempValueMap.put("ReturnMsg", "error");
            }

            // 公钥信息
            tempValueMap.put("PublicKey", pkValue);

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return MsgPack.createReturnMessage(tempValueMap);
    }

    /**
     * 申请TMK
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ApplyTMK(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            parseCommonXml(rootEle, tempValueMap);

            // 解析非公共部分
            String HashID = rootEle.elementTextTrim("HashID");
            tempValueMap.put("HashID",HashID);
            String UserID = rootEle.elementTextTrim("UserID");
            tempValueMap.put("UserID",UserID);
            String cipherData = rootEle.elementTextTrim("cipherData");
            tempValueMap.put("cipherData",cipherData);
            String LockSignByVK = rootEle.elementTextTrim("LockSignByVK");
            tempValueMap.put("LockSignByVK",LockSignByVK);
            String LockSignData = rootEle.elementTextTrim("LockSignData");
            tempValueMap.put("LockSignData",LockSignData);

            // do things


        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return MsgPack.createReturnMessage(tempValueMap);

        // 通过解析到的交易码读取文件
//        FileContentReader fcr = new FileContentReader();
//        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
//        return response;
    }


    /**
     * 申请WMK
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ApplyWMK(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            parseCommonXml(rootEle, tempValueMap);

            // 解析非公共部分

        } catch (DocumentException e) {
            e.printStackTrace();
        }


        // 通过解析到的交易码读取文件
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * 申请DMK
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ApplyDMK(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            parseCommonXml(rootEle, tempValueMap);

            // 解析非公共部分

        } catch (DocumentException e) {
            e.printStackTrace();
        }


        // 通过解析到的交易码读取文件
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * 申请开锁码
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ApplyOTC(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            parseCommonXml(rootEle, tempValueMap);

            // 解析非公共部分
//            加钞员AID
            String OperAId = rootEle.elementTextTrim("OperAId");
            tempValueMap.put("OperAId",OperAId);
//            加钞员A密码
            String OperAPwd = rootEle.elementTextTrim("OperAPwd");
            tempValueMap.put("OperAPwd",OperAPwd);
//            加钞员A指纹
            String OperAFinger = rootEle.elementTextTrim("OperAFinger");
            tempValueMap.put("OperAFinger",OperAFinger);
//            加钞员BID
            String OperBId = rootEle.elementTextTrim("OperBId");
            tempValueMap.put("OperBId",OperBId);
//            加钞员B密码
            String OperBPwd = rootEle.elementTextTrim("OperBPwd");
            tempValueMap.put("OperBPwd",OperBPwd);
//            加钞员B指纹
            String OperBFinger = rootEle.elementTextTrim("OperBFinger");
            tempValueMap.put("OperBFinger",OperBFinger);
//            锁具随机数
            String LockRD = rootEle.elementTextTrim("LockRD");
            tempValueMap.put("LockRD",LockRD);
//            是否胁迫
            String ByForce = rootEle.elementTextTrim("ByForce");
            tempValueMap.put("ByForce",ByForce);


        } catch (DocumentException e) {
            e.printStackTrace();
        }


        // 通过解析到的交易码读取文件
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }


    /**
     * 申请锁具参数
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ApplyLockConfig(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            parseCommonXml(rootEle, tempValueMap);

            // 解析非公共部分

        } catch (DocumentException e) {
            e.printStackTrace();
        }


        // 通过解析到的交易码读取文件
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * 上传激活信息
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String SendActiveInfo(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            parseCommonXml(rootEle, tempValueMap);

            // 解析非公共部分
//            激活信息
            String ActiveCode = rootEle.elementTextTrim("ActiveCode");
            tempValueMap.put("ActiveCode",ActiveCode);
//            闭锁码
            String CloseInfo = rootEle.elementTextTrim("CloseInfo");
            tempValueMap.put("CloseInfo",CloseInfo);


        } catch (DocumentException e) {
            e.printStackTrace();
        }


        // 通过解析到的交易码读取文件
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * 锁具状态上报
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ReportStatus(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            parseCommonXml(rootEle, tempValueMap);

            // 解析非公共部分
//            锁具状态
            String LockStatus = rootEle.elementTextTrim("LockStatus");
            tempValueMap.put("LockStatus",LockStatus);



        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // 通过解析到的交易码读取文件
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * 时间同步
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String SyncTime(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            parseCommonXml(rootEle, tempValueMap);

            // 解析非公共部分


        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // 通过解析到的交易码读取文件
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * 上传闭锁码
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ReportCloseCode(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            parseCommonXml(rootEle, tempValueMap);

            // 解析非公共部分
            // 闭锁码
            String Ex_CloseCode = rootEle.elementTextTrim("Ex_CloseCode");
            tempValueMap.put("Ex_CloseCode",Ex_CloseCode);
            // 锁具状态
            String LockStatus = rootEle.elementTextTrim("LockStatus");
            tempValueMap.put("LockStatus",LockStatus);



        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // 通过解析到的交易码读取文件
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }


    private static void parseCommonXml(Element rootEle, Map<String, String> tempValueMap) {
        // 获取根节点下的子节点
        String functionName = rootEle.elementTextTrim("FunctionName");
        tempValueMap.put("FunctionName",functionName);
        String functionCode = rootEle.elementTextTrim("FunctionCode");
        tempValueMap.put("FunctionCode",functionCode);
        String dateTime = rootEle.elementTextTrim("DateTime");
        tempValueMap.put("DateTime",dateTime);
        String lockID = rootEle.elementTextTrim("LockID");
        tempValueMap.put("LockID",lockID);
    }
}


