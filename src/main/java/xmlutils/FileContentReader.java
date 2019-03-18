package xmlutils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class FileContentReader {

    public synchronized String readFile(String fileName) {
        // ����һ���ɱ䳤��stringBuffer����
        StringBuilder sb = new StringBuilder("");
        try {

            /*
             * ��ȡ�����ļ�
             */
            Reader reader = new FileReader("H:\\SocketTest\\src\\main\\resources\\"+ fileName + ".txt");
            // ���������õ����ַ�������BufferedReader��
            BufferedReader bufferedReader = new BufferedReader(reader);
            String string = null;
            // ���ж�ȡ���������ж����Ƿ�Ϊnull�����ֽڻ����ַ���ȡʱ�����ı�־��-1
            while ((string = bufferedReader.readLine()) != null) {
                sb.append(string);
                System.out.println(string);
            }
            // ע���������رյ�˳��
            bufferedReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        FileContentReader fcr = new FileContentReader();
        fcr.readFile("H:\\SocketTest\\src\\main\\resources\\180006.txt");
    }
}
