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

            // ��ȡ������XML�ĵ�
            // SAXReader����һ���ܵ�����һ�����ķ�ʽ����xml�ļ�������
            //
            // SAXReader reader = new SAXReader(); //User.hbm.xml��ʾ��Ҫ������xml�ĵ�
            // Document document = reader.read(new File("User.hbm.xml"));

            // �������ͨ������xml�ַ�����
            doc = DocumentHelper.parseText(xml); // ���ַ���תΪXML

            Element rootElt = doc.getRootElement(); // ��ȡ���ڵ�
            System.out.println("���ڵ㣺" + rootElt.getName()); // �õ����ڵ������

            Iterator iter = rootElt.elementIterator("head"); // ��ȡ���ڵ��µ��ӽڵ�head

            // ����head�ڵ�
            while (iter.hasNext()) {

                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("yx_TrCode"); // �õ�head�ڵ��µ��ӽڵ�titleֵ
                System.out.println("yx_TrCode:" + title);

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

    public static void main(String[] args) {
        test test = new test();
//        test.readStringXml("<?xml\tversion=\"1.0\"\tencoding=\"GBK\"?>\n" +
//                "<root> \n" +
//                "<head>\n" +
//                "<yx_TrCode>220064</yx_TrCode>\n" +
//                "<yx_BankId>088889001</yx_BankId>\n" +
//                "</head>\n" +
//                "<body><yx_CurrNo>156</yx_CurrNo>\n" +
//                "<yx_Mode>0</yx_Mode>\n" +
//                "<yx_CashBatch>0</yx_CashBatch>\n" +
//                "</body>\n" +
//                "</root>");
        FileContentReader fcr = new FileContentReader();
    }

}
