package xmlutils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.PrintWriter;
import java.util.Map;

public class MsgPack {

    public static String createReturnMessage(Map<String, String> map) {

        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        // ±éÀúmapÉú³Éxml×Ö·û´®
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Element ele = root.addElement(entry.getKey());
            ele.setText(entry.getValue());
        }
        return doc.asXML();
    }
}
