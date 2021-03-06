package xmlutils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;

public class test {
    private void readStringXml(String xml) {
        Document doc = null;
        try {

            // 读取并解析XML文档
            // SAXReader就是一个管道，用一个流的方式，把xml文件读出来
            //
            // SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
            // Document document = reader.read(new File("User.hbm.xml"));

            // 下面的是通过解析xml字符串的
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML

            Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
            System.out.println(rootElt.elementTextTrim("data"));
            Iterator iterator = rootElt.elementIterator();
            // 遍历head节点
            while (iterator.hasNext()) {
                Element recordEle = (Element) iterator.next();
                String title = recordEle.elementTextTrim("data"); // 拿到head节点下的子节点title值
                System.out.println("data:" + title);
            }

            Iterator iter = rootElt.elementIterator("head"); // 获取根节点下的子节点head

            // 遍历head节点
            while (iter.hasNext()) {

                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("yx_TrCode"); // 拿到head节点下的子节点title值
                System.out.println("yx_TrCode:" + title);

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

    public static void main(String[] args) {
        test test = new test();
        test.readStringXml("<?xml\tversion=\"1.0\"\tencoding=\"GBK\"?>\n" +
                "<root> \n" +
                "<head>\n" +
                "<yx_TrCode>220064</yx_TrCode>\n" +
                "<yx_BankId>088889001</yx_BankId>\n" +
                "</head>\n" +
                "<body><yx_CurrNo>156</yx_CurrNo>\n" +
                "<yx_Mode>0</yx_Mode>\n" +
                "<yx_CashBatch>0</yx_CashBatch>\n" +
                "</body>\n" +
                "<data>abc</data>" +
                "</root>");

    }

}
