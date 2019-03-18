package xmlutils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.PrintWriter;

public class CreateTest {
    public static void main(String[] args) {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");

        Element product = root.addElement("product");
        product.addAttribute("name", "QQ");
        product.setText("text");

        Element account = product.addElement("account");
        account.addAttribute("id", "123456789");

        Element nickname = account.addElement("nickname");
        nickname.setText("QQ-account-1");

        Element password = account.addElement("password");
        password.setText("123asd21qda");

        Element level = account.addElement("level");
        level.setText("56");

        PrintWriter pWriter = null;
        XMLWriter xWriter = null;
        try
        {
            String result = doc.asXML();
            System.out.println(result);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
//                xWriter.flush();
//                xWriter.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
