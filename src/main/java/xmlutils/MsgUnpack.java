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
     * 报文处理
     *
     * @param xmlText
     */
    public static void dealXmlRequest(String xmlText) throws Exception {
        // 解析交易码
        String transCode = getTransCode(xmlText);
        if (transCode == null || "".equals(transCode)) {
            System.out.println("transCode 为空");
            return;
        }

        // 解析功能名称
        String funcName = getFuncName(xmlText);
        if (funcName == null || "".equals(funcName)) {
            System.out.println("funcName 为空");
            return;
        }

        // 反射根据解析的方法名去执行方法
        Class<?> c = Class.forName("xmlutils.RequestHandler");
        Object obj = c.newInstance();
        //第一个参数写的是方法名,第二个\第三个\...写的是方法参数列表中参数的类型
        Method method = c.getMethod(funcName, String.class);
        //invoke是执行该方法,并携带参数值
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
     * 解析请求公共部分
     *
     * @param xmlText
     */
    public static CommonRequest dealXmlCommon(String xmlText) {
        CommonRequest commonRequest = new CommonRequest();
        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            // 获取根节点下的子节点
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

            // 读取并解析XML文档
            // SAXReader就是一个管道，用一个流的方式，把xml文件读出来
            //
            // SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
            // Document document = reader.read(new File("User.hbm.xml"));

            // 下面的是通过解析xml字符串的
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML

            // 解析公共报文
            Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称

            Iterator iter = rootElt.elementIterator("head"); // 获取根节点下的子节点head

            // 遍历head节点
            while (iter.hasNext()) {

                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("scode"); // 拿到head节点下的子节点title值
                System.out.println("scode:" + title);

//                Iterator iters = recordEle.elementIterator("script"); // 获取子节点head下的子节点script
//
//                // 遍历Header节点下的Response节点
//                while (iters.hasNext()) {
//
//                    Element itemEle = (Element) iters.next();
//
//                    String username = itemEle.elementTextTrim("username"); // 拿到head下的子节点script下的字节点username的值
//                    String password = itemEle.elementTextTrim("password");
//
//                    System.out.println("username:" + username);
//                    System.out.println("password:" + password);
//                }
            }
            Iterator iterss = rootElt.elementIterator("body"); ///获取根节点下的子节点body
            // 遍历body节点
            while (iterss.hasNext()) {

                Element recordEless = (Element) iterss.next();
                String result = recordEless.elementTextTrim("data1"); // 拿到body节点下的子节点result值
                System.out.println("data1:" + result);

//                Iterator itersElIterator = recordEless.elementIterator("form"); // 获取子节点body下的子节点form
//                // 遍历Header节点下的Response节点
//                while (itersElIterator.hasNext()) {
//
//                    Element itemEle = (Element) itersElIterator.next();
//
//                    String banlce = itemEle.elementTextTrim("banlce"); // 拿到body下的子节点form下的字节点banlce的值
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
     * 从报文中获取transcode
     *
     * @param xmlText
     * @return
     */
    public static String getTransCode(String xmlText) {
        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            transCode = rootEle.elementTextTrim("FunctionCode"); // 获取根节点下的子节点

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return transCode;
    }

    /**
     * 从报文中获取FunctionName
     *
     * @param xmlText
     * @return
     */
    public static String getFuncName(String xmlText) {
        Document doc = null;
        String transCode = "";
        try {
            // 将xml字符串解析为xml对象
            doc = DocumentHelper.parseText(xmlText);
            // 解析公共报文
            Element rootEle = doc.getRootElement(); // 获取根节点
            transCode = rootEle.elementTextTrim("FunctionName"); // 获取根节点下的子节点

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return transCode;
    }

}
