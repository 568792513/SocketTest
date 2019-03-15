package xmlutils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;

public class MsgUnpack {

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


}
