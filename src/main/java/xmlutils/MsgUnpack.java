package xmlutils;

import enity.request.CommonRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Method;
import java.util.Iterator;

public class MsgUnpack {

    /**
     * ���Ĵ���
     *
     * @param xmlText
     */
    public static void dealXmlRequest(String xmlText) throws Exception {
        // ����������
        String transCode = getTransCode(xmlText);
        if (transCode == null || "".equals(transCode)) {
            System.out.println("transCode Ϊ��");
            return;
        }

        // ������������
        String funcName = getFuncName(xmlText);
        if (funcName == null || "".equals(funcName)) {
            System.out.println("funcName Ϊ��");
            return;
        }

        // ������ݽ����ķ�����ȥִ�з���
        Class<?> c = Class.forName("xmlutils.RequestHandler");
        Object obj = c.newInstance();
        //��һ������д���Ƿ�����,�ڶ���\������\...д���Ƿ��������б��в���������
        Method method = c.getMethod(funcName, String.class);
        //invoke��ִ�и÷���,��Я������ֵ
        method.invoke(obj, xmlText);
//        switch (transCode) {
//            case "6001":
//                RequestHandler.applyCPubKey(xmlText);
//                break;
//            default:
//                break;
//        }
    }

    /**
     * �������󹫹�����
     *
     * @param xmlText
     */
    public static CommonRequest dealXmlCommon(String xmlText) {
        CommonRequest commonRequest = new CommonRequest();
        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            // ��ȡ���ڵ��µ��ӽڵ�
            String functionName = rootEle.elementTextTrim("FunctionName");
            String functionCode = rootEle.elementTextTrim("FunctionCode");
            String dateTime = rootEle.elementTextTrim("DateTime");
            String lockID = rootEle.elementTextTrim("LockID");

            commonRequest.setFunctionName(functionName);
            commonRequest.setFunctionCode(functionCode);
            commonRequest.setDateTime(dateTime);
            commonRequest.setLockID(lockID);

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return commonRequest;
    }


    public void readStringXml(String xml) {
        Document doc = null;
        try {

            // ��ȡ������XML�ĵ�
            // SAXReader����һ���ܵ�����һ�����ķ�ʽ����xml�ļ�������
            //
            // SAXReader reader = new SAXReader(); //User.hbm.xml��ʾ��Ҫ������xml�ĵ�
            // Document document = reader.read(new File("User.hbm.xml"));

            // �������ͨ������xml�ַ�����
            doc = DocumentHelper.parseText(xml); // ���ַ���תΪXML

            // ������������
            Element rootElt = doc.getRootElement(); // ��ȡ���ڵ�
            System.out.println("���ڵ㣺" + rootElt.getName()); // �õ����ڵ������

            Iterator iter = rootElt.elementIterator("head"); // ��ȡ���ڵ��µ��ӽڵ�head

            // ����head�ڵ�
            while (iter.hasNext()) {

                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("scode"); // �õ�head�ڵ��µ��ӽڵ�titleֵ
                System.out.println("scode:" + title);

//                Iterator iters = recordEle.elementIterator("script"); // ��ȡ�ӽڵ�head�µ��ӽڵ�script
//
//                // ����Header�ڵ��µ�Response�ڵ�
//                while (iters.hasNext()) {
//
//                    Element itemEle = (Element) iters.next();
//
//                    String username = itemEle.elementTextTrim("username"); // �õ�head�µ��ӽڵ�script�µ��ֽڵ�username��ֵ
//                    String password = itemEle.elementTextTrim("password");
//
//                    System.out.println("username:" + username);
//                    System.out.println("password:" + password);
//                }
            }
            Iterator iterss = rootElt.elementIterator("body"); ///��ȡ���ڵ��µ��ӽڵ�body
            // ����body�ڵ�
            while (iterss.hasNext()) {

                Element recordEless = (Element) iterss.next();
                String result = recordEless.elementTextTrim("data1"); // �õ�body�ڵ��µ��ӽڵ�resultֵ
                System.out.println("data1:" + result);

//                Iterator itersElIterator = recordEless.elementIterator("form"); // ��ȡ�ӽڵ�body�µ��ӽڵ�form
//                // ����Header�ڵ��µ�Response�ڵ�
//                while (itersElIterator.hasNext()) {
//
//                    Element itemEle = (Element) itersElIterator.next();
//
//                    String banlce = itemEle.elementTextTrim("banlce"); // �õ�body�µ��ӽڵ�form�µ��ֽڵ�banlce��ֵ
//                    String subID = itemEle.elementTextTrim("subID");
//
//                    System.out.println("banlce:" + banlce);
//                    System.out.println("subID:" + subID);
//                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * �ӱ����л�ȡtranscode
     *
     * @param xmlText
     * @return
     */
    public static String getTransCode(String xmlText) {
        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            transCode = rootEle.elementTextTrim("FunctionCode"); // ��ȡ���ڵ��µ��ӽڵ�

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return transCode;
    }

    /**
     * �ӱ����л�ȡFunctionName
     *
     * @param xmlText
     * @return
     */
    public static String getFuncName(String xmlText) {
        Document doc = null;
        String transCode = "";
        try {
            // ��xml�ַ�������Ϊxml����
            doc = DocumentHelper.parseText(xmlText);
            // ������������
            Element rootEle = doc.getRootElement(); // ��ȡ���ڵ�
            transCode = rootEle.elementTextTrim("FunctionName"); // ��ȡ���ڵ��µ��ӽڵ�

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return transCode;
    }

}
