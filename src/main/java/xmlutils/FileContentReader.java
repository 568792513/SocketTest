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
        // 声明一个可变长的stringBuffer对象
        StringBuilder sb = new StringBuilder("");
        try {

            /*
             * 读取完整文件
             */
            Reader reader = new FileReader("H:\\SocketTest\\src\\main\\resources\\"+ fileName + ".txt");
            // 这里我们用到了字符操作的BufferedReader类
            BufferedReader bufferedReader = new BufferedReader(reader);
            String string = null;
            // 按行读取，结束的判断是是否为null，按字节或者字符读取时结束的标志是-1
            while ((string = bufferedReader.readLine()) != null) {
                sb.append(string);
                System.out.println(string);
            }
            // 注意这两个关闭的顺序
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
