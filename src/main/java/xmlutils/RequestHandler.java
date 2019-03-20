package xmlutils;

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

    /**
     * �������Ĺ�Կ
     * @param xmlText
     * @throws Exception
     */
    public static String ApplyCPubKey(String xmlText) throws Exception {

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
        // ͨ���������Ľ������ȡ�ļ�
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(applyCPubKeyRequest.getFunctionCode());
        return response;
    }

    /**
     * ����TMK
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ApplyTMK(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            parseCommonXml(rootEle, tempValueMap);

            // �����ǹ�������
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

        // ͨ���������Ľ������ȡ�ļ�
//        FileContentReader fcr = new FileContentReader();
//        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
//        return response;
    }


    /**
     * ����WMK
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ApplyWMK(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            parseCommonXml(rootEle, tempValueMap);

            // �����ǹ�������

        } catch (DocumentException e) {
            e.printStackTrace();
        }


        // ͨ���������Ľ������ȡ�ļ�
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * ����DMK
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ApplyDMK(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            parseCommonXml(rootEle, tempValueMap);

            // �����ǹ�������

        } catch (DocumentException e) {
            e.printStackTrace();
        }


        // ͨ���������Ľ������ȡ�ļ�
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * ���뿪����
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ApplyOTC(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            parseCommonXml(rootEle, tempValueMap);

            // �����ǹ�������
//            �ӳ�ԱAID
            String OperAId = rootEle.elementTextTrim("OperAId");
            tempValueMap.put("OperAId",OperAId);
//            �ӳ�ԱA����
            String OperAPwd = rootEle.elementTextTrim("OperAPwd");
            tempValueMap.put("OperAPwd",OperAPwd);
//            �ӳ�ԱAָ��
            String OperAFinger = rootEle.elementTextTrim("OperAFinger");
            tempValueMap.put("OperAFinger",OperAFinger);
//            �ӳ�ԱBID
            String OperBId = rootEle.elementTextTrim("OperBId");
            tempValueMap.put("OperBId",OperBId);
//            �ӳ�ԱB����
            String OperBPwd = rootEle.elementTextTrim("OperBPwd");
            tempValueMap.put("OperBPwd",OperBPwd);
//            �ӳ�ԱBָ��
            String OperBFinger = rootEle.elementTextTrim("OperBFinger");
            tempValueMap.put("OperBFinger",OperBFinger);
//            ���������
            String LockRD = rootEle.elementTextTrim("LockRD");
            tempValueMap.put("LockRD",LockRD);
//            �Ƿ�в��
            String ByForce = rootEle.elementTextTrim("ByForce");
            tempValueMap.put("ByForce",ByForce);


        } catch (DocumentException e) {
            e.printStackTrace();
        }


        // ͨ���������Ľ������ȡ�ļ�
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }


    /**
     * �������߲���
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ApplyLockConfig(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            parseCommonXml(rootEle, tempValueMap);

            // �����ǹ�������

        } catch (DocumentException e) {
            e.printStackTrace();
        }


        // ͨ���������Ľ������ȡ�ļ�
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * �ϴ�������Ϣ
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String SendActiveInfo(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            parseCommonXml(rootEle, tempValueMap);

            // �����ǹ�������
//            ������Ϣ
            String ActiveCode = rootEle.elementTextTrim("ActiveCode");
            tempValueMap.put("ActiveCode",ActiveCode);
//            ������
            String CloseInfo = rootEle.elementTextTrim("CloseInfo");
            tempValueMap.put("CloseInfo",CloseInfo);


        } catch (DocumentException e) {
            e.printStackTrace();
        }


        // ͨ���������Ľ������ȡ�ļ�
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * ����״̬�ϱ�
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ReportStatus(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            parseCommonXml(rootEle, tempValueMap);

            // �����ǹ�������
//            ����״̬
            String LockStatus = rootEle.elementTextTrim("LockStatus");
            tempValueMap.put("LockStatus",LockStatus);



        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // ͨ���������Ľ������ȡ�ļ�
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * ʱ��ͬ��
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String SyncTime(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            parseCommonXml(rootEle, tempValueMap);

            // �����ǹ�������


        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // ͨ���������Ľ������ȡ�ļ�
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }

    /**
     * �ϴ�������
     * @param xmlText
     * @return
     * @throws Exception
     */
    public static String ReportCloseCode(String xmlText) throws Exception {

        Map<String, String> tempValueMap = new LinkedHashMap<String, String>();

        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            parseCommonXml(rootEle, tempValueMap);

            // �����ǹ�������
            // ������
            String Ex_CloseCode = rootEle.elementTextTrim("Ex_CloseCode");
            tempValueMap.put("Ex_CloseCode",Ex_CloseCode);
            // ����״̬
            String LockStatus = rootEle.elementTextTrim("LockStatus");
            tempValueMap.put("LockStatus",LockStatus);



        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // ͨ���������Ľ������ȡ�ļ�
        FileContentReader fcr = new FileContentReader();
        String response = fcr.readFile(tempValueMap.get("FunctionCode"));
        return response;
    }


    private static void parseCommonXml(Element rootEle, Map<String, String> tempValueMap) {
        // ��ȡ���ڵ��µ��ӽڵ�
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


